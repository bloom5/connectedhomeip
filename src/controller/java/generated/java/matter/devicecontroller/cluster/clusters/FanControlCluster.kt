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

class FanControlCluster(private val endpointId: UShort) {
  class PercentSettingAttribute(val value: UByte?)

  class SpeedSettingAttribute(val value: UByte?)

  class GeneratedCommandListAttribute(val value: ArrayList<UInt>)

  class AcceptedCommandListAttribute(val value: ArrayList<UInt>)

  class EventListAttribute(val value: ArrayList<UInt>)

  class AttributeListAttribute(val value: ArrayList<UInt>)

  suspend fun step(direction: UInt, wrap: Boolean?, lowestOff: Boolean?) {
    // Implementation needs to be added here
  }

  suspend fun step(
    direction: UInt,
    wrap: Boolean?,
    lowestOff: Boolean?,
    timedInvokeTimeoutMs: Int
  ) {
    // Implementation needs to be added here
  }

  suspend fun readFanModeAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun writeFanModeAttribute(value: UInt) {
    // Implementation needs to be added here
  }

  suspend fun writeFanModeAttribute(value: UInt, timedWriteTimeoutMs: Int) {
    // Implementation needs to be added here
  }

  suspend fun subscribeFanModeAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readFanModeSequenceAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun writeFanModeSequenceAttribute(value: UInt) {
    // Implementation needs to be added here
  }

  suspend fun writeFanModeSequenceAttribute(value: UInt, timedWriteTimeoutMs: Int) {
    // Implementation needs to be added here
  }

  suspend fun subscribeFanModeSequenceAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readPercentSettingAttribute(): PercentSettingAttribute {
    // Implementation needs to be added here
  }

  suspend fun writePercentSettingAttribute(value: UByte) {
    // Implementation needs to be added here
  }

  suspend fun writePercentSettingAttribute(value: UByte, timedWriteTimeoutMs: Int) {
    // Implementation needs to be added here
  }

  suspend fun subscribePercentSettingAttribute(
    minInterval: Int,
    maxInterval: Int
  ): PercentSettingAttribute {
    // Implementation needs to be added here
  }

  suspend fun readPercentCurrentAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun subscribePercentCurrentAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readSpeedMaxAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun subscribeSpeedMaxAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readSpeedSettingAttribute(): SpeedSettingAttribute {
    // Implementation needs to be added here
  }

  suspend fun writeSpeedSettingAttribute(value: UByte) {
    // Implementation needs to be added here
  }

  suspend fun writeSpeedSettingAttribute(value: UByte, timedWriteTimeoutMs: Int) {
    // Implementation needs to be added here
  }

  suspend fun subscribeSpeedSettingAttribute(
    minInterval: Int,
    maxInterval: Int
  ): SpeedSettingAttribute {
    // Implementation needs to be added here
  }

  suspend fun readSpeedCurrentAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun subscribeSpeedCurrentAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readRockSupportAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun subscribeRockSupportAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readRockSettingAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun writeRockSettingAttribute(value: UInt) {
    // Implementation needs to be added here
  }

  suspend fun writeRockSettingAttribute(value: UInt, timedWriteTimeoutMs: Int) {
    // Implementation needs to be added here
  }

  suspend fun subscribeRockSettingAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readWindSupportAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun subscribeWindSupportAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readWindSettingAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun writeWindSettingAttribute(value: UInt) {
    // Implementation needs to be added here
  }

  suspend fun writeWindSettingAttribute(value: UInt, timedWriteTimeoutMs: Int) {
    // Implementation needs to be added here
  }

  suspend fun subscribeWindSettingAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readAirflowDirectionAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun writeAirflowDirectionAttribute(value: UInt) {
    // Implementation needs to be added here
  }

  suspend fun writeAirflowDirectionAttribute(value: UInt, timedWriteTimeoutMs: Int) {
    // Implementation needs to be added here
  }

  suspend fun subscribeAirflowDirectionAttribute(minInterval: Int, maxInterval: Int): Integer {
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
    const val CLUSTER_ID: UInt = 514u
  }
}
