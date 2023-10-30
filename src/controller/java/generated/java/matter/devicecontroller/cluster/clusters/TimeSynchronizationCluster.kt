/*
 *
 *    Copyright (c) 2023 Project CHIP Authors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package matter.devicecontroller.cluster.clusters

import java.util.ArrayList

class TimeSynchronizationCluster(private val endpointId: UShort) {
  class SetTimeZoneResponse(val DSTOffsetRequired: Boolean)

  class UTCTimeAttribute(val value: ULong?)

  class TrustedTimeSourceAttribute(
    val value: ChipStructs.TimeSynchronizationClusterTrustedTimeSourceStruct?
  )

  class DefaultNTPAttribute(val value: String?)

  class TimeZoneAttribute(
    val value: ArrayList<ChipStructs.TimeSynchronizationClusterTimeZoneStruct>?
  )

  class DSTOffsetAttribute(
    val value: ArrayList<ChipStructs.TimeSynchronizationClusterDSTOffsetStruct>?
  )

  class LocalTimeAttribute(val value: ULong?)

  class GeneratedCommandListAttribute(val value: ArrayList<UInt>)

  class AcceptedCommandListAttribute(val value: ArrayList<UInt>)

  class EventListAttribute(val value: ArrayList<UInt>)

  class AttributeListAttribute(val value: ArrayList<UInt>)

  suspend fun setUTCTime(UTCTime: ULong, granularity: UInt, timeSource: UInt?) {
    // Implementation needs to be added here
  }

  suspend fun setUTCTime(
    UTCTime: ULong,
    granularity: UInt,
    timeSource: UInt?,
    timedInvokeTimeoutMs: Int
  ) {
    // Implementation needs to be added here
  }

  suspend fun setTrustedTimeSource(
    trustedTimeSource: ChipStructs.TimeSynchronizationClusterFabricScopedTrustedTimeSourceStruct?
  ) {
    // Implementation needs to be added here
  }

  suspend fun setTrustedTimeSource(
    trustedTimeSource: ChipStructs.TimeSynchronizationClusterFabricScopedTrustedTimeSourceStruct?,
    timedInvokeTimeoutMs: Int
  ) {
    // Implementation needs to be added here
  }

  suspend fun setTimeZone(
    timeZone: ArrayList<ChipStructs.TimeSynchronizationClusterTimeZoneStruct>
  ): SetTimeZoneResponse {
    // Implementation needs to be added here
  }

  suspend fun setTimeZone(
    timeZone: ArrayList<ChipStructs.TimeSynchronizationClusterTimeZoneStruct>,
    timedInvokeTimeoutMs: Int
  ): SetTimeZoneResponse {
    // Implementation needs to be added here
  }

  suspend fun setDSTOffset(
    DSTOffset: ArrayList<ChipStructs.TimeSynchronizationClusterDSTOffsetStruct>
  ) {
    // Implementation needs to be added here
  }

  suspend fun setDSTOffset(
    DSTOffset: ArrayList<ChipStructs.TimeSynchronizationClusterDSTOffsetStruct>,
    timedInvokeTimeoutMs: Int
  ) {
    // Implementation needs to be added here
  }

  suspend fun setDefaultNTP(defaultNTP: String?) {
    // Implementation needs to be added here
  }

  suspend fun setDefaultNTP(defaultNTP: String?, timedInvokeTimeoutMs: Int) {
    // Implementation needs to be added here
  }

  suspend fun readUTCTimeAttribute(): UTCTimeAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribeUTCTimeAttribute(minInterval: Int, maxInterval: Int): UTCTimeAttribute {
    // Implementation needs to be added here
  }

  suspend fun readGranularityAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun subscribeGranularityAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readTimeSourceAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun subscribeTimeSourceAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readTrustedTimeSourceAttribute(): TrustedTimeSourceAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribeTrustedTimeSourceAttribute(
    minInterval: Int,
    maxInterval: Int
  ): TrustedTimeSourceAttribute {
    // Implementation needs to be added here
  }

  suspend fun readDefaultNTPAttribute(): DefaultNTPAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribeDefaultNTPAttribute(
    minInterval: Int,
    maxInterval: Int
  ): DefaultNTPAttribute {
    // Implementation needs to be added here
  }

  suspend fun readTimeZoneAttribute(): TimeZoneAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribeTimeZoneAttribute(minInterval: Int, maxInterval: Int): TimeZoneAttribute {
    // Implementation needs to be added here
  }

  suspend fun readDSTOffsetAttribute(): DSTOffsetAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribeDSTOffsetAttribute(minInterval: Int, maxInterval: Int): DSTOffsetAttribute {
    // Implementation needs to be added here
  }

  suspend fun readLocalTimeAttribute(): LocalTimeAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribeLocalTimeAttribute(minInterval: Int, maxInterval: Int): LocalTimeAttribute {
    // Implementation needs to be added here
  }

  suspend fun readTimeZoneDatabaseAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun subscribeTimeZoneDatabaseAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readNTPServerAvailableAttribute(): Boolean {
    // Implementation needs to be added here
  }

  suspend fun subscribeNTPServerAvailableAttribute(minInterval: Int, maxInterval: Int): Boolean {
    // Implementation needs to be added here
  }

  suspend fun readTimeZoneListMaxSizeAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun subscribeTimeZoneListMaxSizeAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readDSTOffsetListMaxSizeAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun subscribeDSTOffsetListMaxSizeAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readSupportsDNSResolveAttribute(): Boolean {
    // Implementation needs to be added here
  }

  suspend fun subscribeSupportsDNSResolveAttribute(minInterval: Int, maxInterval: Int): Boolean {
    // Implementation needs to be added here
  }

  suspend fun readGeneratedCommandListAttribute(): GeneratedCommandListAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribeGeneratedCommandListAttribute(
    minInterval: Int,
    maxInterval: Int
  ): GeneratedCommandListAttribute {
    // Implementation needs to be added here
  }

  suspend fun readAcceptedCommandListAttribute(): AcceptedCommandListAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribeAcceptedCommandListAttribute(
    minInterval: Int,
    maxInterval: Int
  ): AcceptedCommandListAttribute {
    // Implementation needs to be added here
  }

  suspend fun readEventListAttribute(): EventListAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribeEventListAttribute(minInterval: Int, maxInterval: Int): EventListAttribute {
    // Implementation needs to be added here
  }

  suspend fun readAttributeListAttribute(): AttributeListAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribeAttributeListAttribute(
    minInterval: Int,
    maxInterval: Int
  ): AttributeListAttribute {
    // Implementation needs to be added here
  }

  suspend fun readFeatureMapAttribute(): Long {
    // Implementation needs to be added here
  }

  suspend fun subscribeFeatureMapAttribute(minInterval: Int, maxInterval: Int): Long {
    // Implementation needs to be added here
  }

  suspend fun readClusterRevisionAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun subscribeClusterRevisionAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  companion object {
    const val CLUSTER_ID: UInt = 56u
  }
}
