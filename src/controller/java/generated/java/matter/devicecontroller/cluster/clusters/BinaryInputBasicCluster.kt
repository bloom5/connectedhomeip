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

class BinaryInputBasicCluster(private val endpointId: UShort) {
  class GeneratedCommandListAttribute(val value: ArrayList<UInt>)

  class AcceptedCommandListAttribute(val value: ArrayList<UInt>)

  class EventListAttribute(val value: ArrayList<UInt>)

  class AttributeListAttribute(val value: ArrayList<UInt>)

  suspend fun readActiveTextAttribute(): CharString {
    // Implementation needs to be added here
  }

  suspend fun writeActiveTextAttribute(value: String) {
    // Implementation needs to be added here
  }

  suspend fun writeActiveTextAttribute(value: String, timedWriteTimeoutMs: Int) {
    // Implementation needs to be added here
  }

  suspend fun subscribeActiveTextAttribute(minInterval: Int, maxInterval: Int): CharString {
    // Implementation needs to be added here
  }

  suspend fun readDescriptionAttribute(): CharString {
    // Implementation needs to be added here
  }

  suspend fun writeDescriptionAttribute(value: String) {
    // Implementation needs to be added here
  }

  suspend fun writeDescriptionAttribute(value: String, timedWriteTimeoutMs: Int) {
    // Implementation needs to be added here
  }

  suspend fun subscribeDescriptionAttribute(minInterval: Int, maxInterval: Int): CharString {
    // Implementation needs to be added here
  }

  suspend fun readInactiveTextAttribute(): CharString {
    // Implementation needs to be added here
  }

  suspend fun writeInactiveTextAttribute(value: String) {
    // Implementation needs to be added here
  }

  suspend fun writeInactiveTextAttribute(value: String, timedWriteTimeoutMs: Int) {
    // Implementation needs to be added here
  }

  suspend fun subscribeInactiveTextAttribute(minInterval: Int, maxInterval: Int): CharString {
    // Implementation needs to be added here
  }

  suspend fun readOutOfServiceAttribute(): Boolean {
    // Implementation needs to be added here
  }

  suspend fun writeOutOfServiceAttribute(value: Boolean) {
    // Implementation needs to be added here
  }

  suspend fun writeOutOfServiceAttribute(value: Boolean, timedWriteTimeoutMs: Int) {
    // Implementation needs to be added here
  }

  suspend fun subscribeOutOfServiceAttribute(minInterval: Int, maxInterval: Int): Boolean {
    // Implementation needs to be added here
  }

  suspend fun readPolarityAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun subscribePolarityAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readPresentValueAttribute(): Boolean {
    // Implementation needs to be added here
  }

  suspend fun writePresentValueAttribute(value: Boolean) {
    // Implementation needs to be added here
  }

  suspend fun writePresentValueAttribute(value: Boolean, timedWriteTimeoutMs: Int) {
    // Implementation needs to be added here
  }

  suspend fun subscribePresentValueAttribute(minInterval: Int, maxInterval: Int): Boolean {
    // Implementation needs to be added here
  }

  suspend fun readReliabilityAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun writeReliabilityAttribute(value: UInt) {
    // Implementation needs to be added here
  }

  suspend fun writeReliabilityAttribute(value: UInt, timedWriteTimeoutMs: Int) {
    // Implementation needs to be added here
  }

  suspend fun subscribeReliabilityAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readStatusFlagsAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun subscribeStatusFlagsAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readApplicationTypeAttribute(): Long {
    // Implementation needs to be added here
  }

  suspend fun subscribeApplicationTypeAttribute(minInterval: Int, maxInterval: Int): Long {
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
    const val CLUSTER_ID: UInt = 15u
  }
}
