#!/usr/bin/env python
# Copyright (c) 2023 Project CHIP Authors
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

import dataclasses
import enum
import logging
import os
from typing import List, Optional, Set

from matter_idl.generators import CodeGenerator, GeneratorStorage
from matter_idl.generators.types import (BasicInteger, BasicString, FundamentalType, IdlBitmapType, IdlEnumType, IdlType,
                                         ParseDataType, TypeLookupContext)
from matter_idl.matter_idl_types import (Attribute, Cluster, ClusterSide, Command, DataType, Field, FieldQuality, Idl, Struct,
                                         StructQuality, StructTag)
from stringcase import capitalcase


@dataclasses.dataclass
class GenerateTarget:
    template: str
    output_name: str


@dataclasses.dataclass
class GlobalType:
    name: str      # java name
    cpp_type: str  # underlying type
    idl_type: str  # assumed IDL type


# types that java should see globally
_GLOBAL_TYPES = [
    GlobalType("Boolean", "bool", "boolean"),
    GlobalType("CharString", "const chip::CharSpan", "char_string"),
    GlobalType("Double", "double", "double"),
    GlobalType("Float", "float", "single"),
    GlobalType("Int8s", "int8_t", "int8s"),
    GlobalType("Int8u", "uint8_t", "int8u"),
    GlobalType("Int16s", "int16_t", "int16s"),
    GlobalType("Int16u", "uint16_t", "int16u"),
    GlobalType("Int32s", "int32_t", "int32s"),
    GlobalType("Int32u", "uint32_t", "int32u"),
    GlobalType("Int64s", "int64_t", "int64s"),
    GlobalType("Int64u", "uint64_t", "int64u"),
    GlobalType("OctetString", "const chip::ByteSpan", "octet_string"),
]


def _UnderlyingType(field: Field, context: TypeLookupContext) -> Optional[str]:
    actual = ParseDataType(field.data_type, context)
    if isinstance(actual, (IdlEnumType, IdlBitmapType)):
        actual = actual.base_type

    if isinstance(actual, BasicString):
        if actual.is_binary:
            return 'OctetString'
        else:
            return 'CharString'
    elif isinstance(actual, BasicInteger):
        if actual.is_signed:
            return "Int{}s".format(actual.power_of_two_bits)
        else:
            return "Int{}u".format(actual.power_of_two_bits)
    elif isinstance(actual, FundamentalType):
        if actual == FundamentalType.BOOL:
            return 'Boolean'
        elif actual == FundamentalType.FLOAT:
            return 'Float'
        elif actual == FundamentalType.DOUBLE:
            return 'Double'
        else:
            logging.warn('Unknown fundamental type: %r' % actual)

    return None


def FieldToGlobalName(field: Field, context: TypeLookupContext) -> Optional[str]:
    """Global names are used for generic callbacks shared across
    all clusters (e.g. for bool/float/uint32 and similar)
    """
    if field.is_list:
        return None  # lists are always specific per cluster

    if FieldQuality.NULLABLE & field.qualities:
        return None

    return _UnderlyingType(field, context)


# Based on atomicType in ZAP:
#  src-electron/generator/matter/app/zap-templates/common/override.js
_KNOWN_DECODABLE_TYPES = {
    'action_id': 'chip::ActionId',
    'attrib_id': 'chip::AttributeId',
    'cluster_id': 'chip::ClusterId',
    'command_id': 'chip::CommandId',
    'data_ver': 'chip::DataVersion',
    'devtype_id': 'chip::DeviceTypeId',
    'endpoint_no': 'chip::EndpointId',
    'eui64': 'chip::NodeId',
    'event_id': 'chip::EventId',
    'event_no': 'chip::EventNumber',
    'fabric_id': 'chip::FabricId',
    'fabric_idx': 'chip::FabricIndex',
    'fabric_idx': 'chip::FabricIndex',
    'field_id': 'chip::FieldId',
    'group_id': 'chip::GroupId',
    'node_id': 'chip::NodeId',
    'percent': 'chip::Percent',
    'percent100ths': 'chip::Percent100ths',
    'transaction_id': 'chip::TransactionId',
    'vendor_id': 'chip::VendorId',

    # non-named enums
    'enum8': 'uint8_t',
    'enum16': 'uint16_t',
    'enum32': 'uint32_t',
    'enum64': 'uint64_t',
}


def GlobalNameToJavaName(name: str) -> str:
    if name in {'Int8u', 'Int8s', 'Int16u', 'Int16s'}:
        return 'Integer'

    if name.startswith('Int'):
        return 'Long'

    # Double/Float/Booleans/CharString/OctetString
    return name


def DelegatedCallbackName(attr: Attribute, context: TypeLookupContext) -> str:
    """
    Figure out what callback name to use for delegate callback construction.
    """
    global_name = FieldToGlobalName(attr.definition, context)

    if global_name:
        return 'Delegated{}AttributeCallback'.format(GlobalNameToJavaName(global_name))

    return 'Delegated{}Cluster{}AttributeCallback'.format(context.cluster.name, capitalcase(attr.definition.name))


def ChipClustersCallbackName(attr: Attribute, context: TypeLookupContext) -> str:
    """
    Figure out what callback name to use when building a ChipCluster.*AttributeCallback
    in java codegen.
    """
    global_name = FieldToGlobalName(attr.definition, context)

    if global_name:
        return 'ChipClusters.{}AttributeCallback'.format(GlobalNameToJavaName(global_name))

    return 'ChipClusters.{}Cluster.{}AttributeCallback'.format(context.cluster.name, capitalcase(attr.definition.name))


def CallbackName(attr: Attribute, context: TypeLookupContext) -> str:
    """
    Figure out what callback name to use when a variable requires a read callback.

    These are split into native types, like Boolean/Float/Double/CharString, where
    one callback type can support anything.

    For specific types (e.g. A struct) codegen will generate its own callback name
    specific to that type.
    """
    global_name = FieldToGlobalName(attr.definition, context)

    if global_name:
        return 'CHIP{}AttributeCallback'.format(capitalcase(global_name))

    return 'CHIP{}{}AttributeCallback'.format(
        capitalcase(context.cluster.name),
        capitalcase(attr.definition.name)
    )


def CommandCallbackName(command: Command, cluster: Cluster):
    if command.output_param.lower() == 'defaultsuccess':
        return 'DefaultSuccess'
    return '{}Cluster{}'.format(cluster.name, command.output_param)


def JavaCommandCallbackName(command: Command):
    if command.output_param.lower() == 'defaultsuccess':
        return 'DefaultCluster'
    return '{}'.format(command.output_param)


def IsCommandNotDefaultCallback(command: Command) -> bool:
    return command.output_param.lower() != 'defaultsuccess'


def JavaAttributeCallbackName(attr: Attribute, context: TypeLookupContext) -> str:
    """
    Figure out what callback name to use when building a *AttributeCallback
    in java codegen.
    """
    global_name = FieldToGlobalName(attr.definition, context)

    if global_name:
        return '{}'.format(GlobalNameToJavaName(global_name))

    return '{}Attribute'.format(capitalcase(attr.definition.name))


def IsFieldGlobalName(field: Field, context: TypeLookupContext) -> bool:
    global_name = FieldToGlobalName(field, context)
    if global_name:
        return True

    return False


def attributesWithSupportedCallback(attrs, context: TypeLookupContext):
    for attr in attrs:
        # Attributes will be generated for all types
        # except non-list structures
        if not attr.definition.is_list:
            underlying = ParseDataType(attr.definition.data_type, context)
            if isinstance(underlying, IdlType):
                continue

        yield attr


def _IsUsingGlobalCallback(field: Field, context: TypeLookupContext):
    """Test to determine if the data type of a field can use one of
    the global callbacks (i.e. it is a basic double/integer/bool etc.)
    """
    if field.is_list:
        return False

    if field.is_nullable:
        return False

    return field.data_type.name in {
        "boolean",
        "single",
        "double",
        "int8s",
        "int8u",
        "int16s",
        "int16u",
        "int24s",
        "int24u",
        "int32s",
        "int32u",
        "int40s",
        "int40u",
        "int48s",
        "int48u",
        "int56s",
        "int56u",
        "int64s",
        "int64u",
        "enum8",
        "enum16",
        "enum32",
        "enum64",
        "bitmap8",
        "bitmap16",
        "bitmap32",
        "bitmap64",
        "char_string",
        "long_char_string",
        "octet_string",
        "long_octet_string",
    }


def NamedFilter(choices: List, name: str):
    for choice in choices:
        if choice.name == name:
            return choice
    raise Exception("No item named %s in %r" % (name, choices))


def ToBoxedJavaType(field: Field):
    if field.is_optional:
        return 'jobject'
    elif field.data_type.name.lower() in ['octet_string', 'long_octet_string']:
        return 'jbyteArray'
    elif field.data_type.name.lower() in ['char_string', 'long_char_string']:
        return 'jstring'
    else:
        return 'jobject'


def LowercaseFirst(name: str) -> str:
    """
    Change the first letter of a string to lowercase as long as the 2nd
    letter is not uppercase.

    Can be used for variable naming, eg insider structures, codegen will
    call things "Foo foo" (notice variable name is lowercase).
    """
    if len(name) > 1 and name[1].lower() != name[1]:
        # Odd workaround: PAKEVerifier should not become pAKEVerifier
        return name
    return name[0].lower() + name[1:]


class EncodableValueAttr(enum.Enum):
    LIST = enum.auto()
    NULLABLE = enum.auto()
    OPTIONAL = enum.auto()


class EncodableValue:
    """
    Contains helpers for encoding values, specifically lookups
    for optionality, lists and recursive data type lookups within
    the IDL and cluster

    Intended use is to be able to:
      - derive types (see clone and without_* methods) such that codegen
        can implement things like 'if x != null { treat non-null x}'
      - Java specific conversions: get boxed types and JNI string signautes
        for the underlying types.
    """

    def __init__(self, context: TypeLookupContext, data_type: DataType, attrs: Set[EncodableValueAttr]):
        self.context = context
        self.data_type = data_type
        self.attrs = attrs

    @property
    def is_nullable(self):
        return EncodableValueAttr.NULLABLE in self.attrs

    @property
    def is_optional(self):
        return EncodableValueAttr.OPTIONAL in self.attrs

    @property
    def is_list(self):
        return EncodableValueAttr.LIST in self.attrs

    @property
    def is_octet_string(self):
        return self.data_type.name.lower() in ['octet_string', 'long_octet_string']

    @property
    def is_char_string(self):
        return self.data_type.name.lower() in ['char_string', 'long_char_string']

    @property
    def is_struct(self):
        return self.context.is_struct_type(self.data_type.name)

    @property
    def is_enum(self):
        return self.context.is_enum_type(self.data_type.name)

    @property
    def is_bitmap(self):
        return self.context.is_bitmap_type(self.data_type.name)

    @property
    def is_untyped_bitmap(self):
        return self.context.is_untyped_bitmap_type(self.data_type.name)

    def clone(self):
        return EncodableValue(self.context, self.data_type, self.attrs)

    def without_nullable(self):
        result = self.clone()
        result.attrs.remove(EncodableValueAttr.NULLABLE)
        return result

    def without_optional(self):
        result = self.clone()
        result.attrs.remove(EncodableValueAttr.OPTIONAL)
        return result

    def without_list(self):
        result = self.clone()
        result.attrs.remove(EncodableValueAttr.LIST)
        return result

    def get_underlying_struct(self):
        s = self.context.find_struct(self.data_type.name)
        if not s:
            raise Exception("Struct %s not found" % self.data_type.name)
        return s

    def get_underlying_enum(self):
        e = self.context.find_enum(self.data_type.name)
        if not e:
            raise Exception("Enum %s not found" % self.data_type.name)
        return e

    @property
    def kotlin_type(self):
        t = ParseDataType(self.data_type, self.context)

        if isinstance(t, FundamentalType):
            if t == FundamentalType.BOOL:
                return "Boolean"
            elif t == FundamentalType.FLOAT:
                return "Float"
            elif t == FundamentalType.DOUBLE:
                return "Double"
            else:
                raise Exception("Unknown fundamental type")
        elif isinstance(t, BasicInteger):
            if t.is_signed:
                if t.byte_count <= 1:
                    return "Byte"
                if t.byte_count <= 2:
                    return "Short"
                if t.byte_count <= 4:
                    return "Int"
                return "Long"
            else:  # unsigned
                if t.byte_count <= 1:
                    return "UByte"
                if t.byte_count <= 2:
                    return "UShort"
                if t.byte_count <= 4:
                    return "UInt"
                return "ULong"
        elif isinstance(t, BasicString):
            if t.is_binary:
                return "ByteArray"
            else:
                return "String"
        elif isinstance(t, IdlEnumType):
            if t.base_type.byte_count >= 3:
                return "ULong"
            else:
                return "UInt"
        elif isinstance(t, IdlBitmapType):
            if t.base_type.byte_count >= 3:
                return "ULong"
            else:
                return "UInt"
        else:
            return "Any"

    @property
    def unboxed_java_signature(self):
        if self.is_optional or self.is_list:
            raise Exception("Not a basic type: %r" % self)

        t = ParseDataType(self.data_type, self.context)

        if isinstance(t, FundamentalType):
            if t == FundamentalType.BOOL:
                return "Z"
            elif t == FundamentalType.FLOAT:
                return "F"
            elif t == FundamentalType.DOUBLE:
                return "D"
            else:
                raise Exception("Unknown fundamental type")
        elif isinstance(t, BasicInteger):
            if t.byte_count >= 3:
                return "J"
            else:
                return "I"
        else:
            raise Exception("Not a basic type: %r" % self)

    @property
    def boxed_java_signature(self):
        # Optional takes precedence over list - Optional<ArrayList> compiles down to just java.util.Optional.
        if self.is_optional:
            return "Ljava/util/Optional;"

        if self.is_list:
            return "Ljava/util/ArrayList;"

        t = ParseDataType(self.data_type, self.context)

        if isinstance(t, FundamentalType):
            if t == FundamentalType.BOOL:
                return "Ljava/lang/Boolean;"
            elif t == FundamentalType.FLOAT:
                return "Ljava/lang/Float;"
            elif t == FundamentalType.DOUBLE:
                return "Ljava/lang/Double;"
            else:
                raise Exception("Unknown fundamental type")
        elif isinstance(t, BasicInteger):
            if t.byte_count >= 3:
                return "Ljava/lang/Long;"
            else:
                return "Ljava/lang/Integer;"
        elif isinstance(t, BasicString):
            if t.is_binary:
                return "[B"
            else:
                return "Ljava/lang/String;"
        elif isinstance(t, IdlEnumType):
            if t.base_type.byte_count >= 3:
                return "Ljava/lang/Long;"
            else:
                return "Ljava/lang/Integer;"
        elif isinstance(t, IdlBitmapType):
            if t.base_type.byte_count >= 3:
                return "Ljava/lang/Long;"
            else:
                return "Ljava/lang/Integer;"
        else:
            return "Lchip/devicecontroller/ChipStructs${}Cluster{};".format(self.context.cluster.name, self.data_type.name)


def GlobalEncodableValueFrom(typeName: str, context: TypeLookupContext) -> EncodableValue:
    """
    Filter to convert a global type name to an encodable value
    """
    return EncodableValue(context, DataType(name=typeName), set())


def EncodableValueFrom(field: Field, context: TypeLookupContext) -> EncodableValue:
    """
    Filter to convert a standard field to an EncodableValue.

    This converts the AST information (field name/info + lookup context) into
    a java-generator specific wrapper that can be manipulated and
    queried for properties like java native name or JNI string signature.
    """
    attrs = set()

    if field.is_optional:
        attrs.add(EncodableValueAttr.OPTIONAL)

    if field.is_nullable:
        attrs.add(EncodableValueAttr.NULLABLE)

    if field.is_list:
        attrs.add(EncodableValueAttr.LIST)

    return EncodableValue(context, field.data_type, attrs)


def CreateLookupContext(idl: Idl, cluster: Optional[Cluster]) -> TypeLookupContext:
    """
    A filter to mark a lookup context to be within a specific cluster.

    This is used to specify how structure/enum/other names are looked up.
    Generally one looks up within the specific cluster then if cluster does
    not contain a definition, we loop at global namespacing.
    """
    return TypeLookupContext(idl, cluster)


def CanGenerateSubscribe(attr: Attribute, lookup: TypeLookupContext) -> bool:
    """
    Filter that returns if an attribute can be subscribed to.

    Uses the given attribute and the lookupContext to figure out the attribute
    type.
    """
    # For backwards compatibility, we do not subscribe to structs
    # (although list of structs is ok ...)
    if attr.definition.is_list:
        return True

    return not lookup.is_struct_type(attr.definition.data_type.name)


def IsFabricScopedList(attr: Attribute, lookup: TypeLookupContext) -> bool:
    if not attr.definition.is_list:
        return False

    struct = lookup.find_struct(attr.definition.data_type.name)
    return struct and struct.qualities == StructQuality.FABRIC_SCOPED


def CommandHasResponse(command: Command) -> bool:
    """Returns true if a command has a specific response."""
    return command.output_param != "DefaultSuccess"


def IsResponseStruct(s: Struct) -> bool:
    return s.tag == StructTag.RESPONSE


class __KotlinCodeGenerator(CodeGenerator):
    """
    Code generation for kotlin-specific files.

    Registers filters used by all kotlin generators.
    """

    def __init__(self, storage: GeneratorStorage, idl: Idl, **kargs):
        """
        Inintialization is specific for kotlin generation and will add
        filters as required by the kotlin .jinja templates to function.
        """
        super().__init__(storage, idl, fs_loader_searchpath=os.path.dirname(__file__))

        self.jinja_env.filters['attributesWithCallback'] = attributesWithSupportedCallback
        self.jinja_env.filters['callbackName'] = CallbackName
        self.jinja_env.filters['chipClustersCallbackName'] = ChipClustersCallbackName
        self.jinja_env.filters['delegatedCallbackName'] = DelegatedCallbackName
        self.jinja_env.filters['commandCallbackName'] = CommandCallbackName
        self.jinja_env.filters['javaCommandCallbackName'] = JavaCommandCallbackName
        self.jinja_env.filters['isCommandNotDefaultCallback'] = IsCommandNotDefaultCallback
        self.jinja_env.filters['javaAttributeCallbackName'] = JavaAttributeCallbackName
        self.jinja_env.filters['named'] = NamedFilter
        self.jinja_env.filters['toBoxedJavaType'] = ToBoxedJavaType
        self.jinja_env.filters['lowercaseFirst'] = LowercaseFirst
        self.jinja_env.filters['asEncodable'] = EncodableValueFrom
        self.jinja_env.filters['globalAsEncodable'] = GlobalEncodableValueFrom
        self.jinja_env.filters['createLookupContext'] = CreateLookupContext
        self.jinja_env.filters['canGenerateSubscribe'] = CanGenerateSubscribe
        self.jinja_env.filters['isFabricScopedList'] = IsFabricScopedList
        self.jinja_env.filters['hasResponse'] = CommandHasResponse

        self.jinja_env.tests['is_response_struct'] = IsResponseStruct
        self.jinja_env.tests['is_using_global_callback'] = _IsUsingGlobalCallback
        self.jinja_env.tests['is_field_global_name'] = IsFieldGlobalName


class KotlinClassGenerator(__KotlinCodeGenerator):
    """Generates .kt files """

    def __init__(self, *args, **kargs):
        super().__init__(*args, **kargs)

    def internal_render_all(self):
        """
        Renders .kt files required for kotlin matter support
        """

        clientClusters = [
            c for c in self.idl.clusters if c.side == ClusterSide.CLIENT]

        self.internal_render_one_output(
            template_path="MatterFiles_gni.jinja",
            output_file_name="java/matter/devicecontroller/cluster/files.gni",
            vars={
                'idl': self.idl,
                'clientClusters': clientClusters,
            }
        )

        # Generate a `.kt` file for each cluster.
        for cluster in clientClusters:
            output_name = f"java/matter/devicecontroller/cluster/clusters/{cluster.name}Cluster.kt"
            self.internal_render_one_output(
                template_path="MatterClusters.jinja",
                output_file_name=output_name,
                vars={
                    'idl': self.idl,
                    'cluster': cluster,
                }
            )
