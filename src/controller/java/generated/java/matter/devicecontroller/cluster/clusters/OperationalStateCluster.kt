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

class OperationalStateCluster(private val endpointId: UShort) {
  class OperationalCommandResponse(
    val commandResponseState: ChipStructs.OperationalStateClusterErrorStateStruct
  )

  class PhaseListAttribute(val value: ArrayList<String>?)

  class CurrentPhaseAttribute(val value: UByte?)

  class CountdownTimeAttribute(val value: UInt?)

  class OperationalStateListAttribute(
    val value: ArrayList<ChipStructs.OperationalStateClusterOperationalStateStruct>
  )

  class OperationalErrorAttribute(val value: ChipStructs.OperationalStateClusterErrorStateStruct)

  class GeneratedCommandListAttribute(val value: ArrayList<UInt>)

  class AcceptedCommandListAttribute(val value: ArrayList<UInt>)

  class EventListAttribute(val value: ArrayList<UInt>)

  class AttributeListAttribute(val value: ArrayList<UInt>)

  suspend fun pause(): OperationalCommandResponse {
    // Implementation needs to be added here
  }

  suspend fun pause(timedInvokeTimeoutMs: Int): OperationalCommandResponse {
    // Implementation needs to be added here
  }

  suspend fun stop(): OperationalCommandResponse {
    // Implementation needs to be added here
  }

  suspend fun stop(timedInvokeTimeoutMs: Int): OperationalCommandResponse {
    // Implementation needs to be added here
  }

  suspend fun start(): OperationalCommandResponse {
    // Implementation needs to be added here
  }

  suspend fun start(timedInvokeTimeoutMs: Int): OperationalCommandResponse {
    // Implementation needs to be added here
  }

  suspend fun resume(): OperationalCommandResponse {
    // Implementation needs to be added here
  }

  suspend fun resume(timedInvokeTimeoutMs: Int): OperationalCommandResponse {
    // Implementation needs to be added here
  }

  suspend fun readPhaseListAttribute(): PhaseListAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribePhaseListAttribute(minInterval: Int, maxInterval: Int): PhaseListAttribute {
    // Implementation needs to be added here
  }

  suspend fun readCurrentPhaseAttribute(): CurrentPhaseAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribeCurrentPhaseAttribute(
    minInterval: Int,
    maxInterval: Int
  ): CurrentPhaseAttribute {
    // Implementation needs to be added here
  }

  suspend fun readCountdownTimeAttribute(): CountdownTimeAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribeCountdownTimeAttribute(
    minInterval: Int,
    maxInterval: Int
  ): CountdownTimeAttribute {
    // Implementation needs to be added here
  }

  suspend fun readOperationalStateListAttribute(): OperationalStateListAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribeOperationalStateListAttribute(
    minInterval: Int,
    maxInterval: Int
  ): OperationalStateListAttribute {
    // Implementation needs to be added here
  }

  suspend fun readOperationalStateAttribute(): Integer {
    // Implementation needs to be added here
  }

  suspend fun subscribeOperationalStateAttribute(minInterval: Int, maxInterval: Int): Integer {
    // Implementation needs to be added here
  }

  suspend fun readOperationalErrorAttribute(): OperationalErrorAttribute {
    // Implementation needs to be added here
  }

  suspend fun subscribeOperationalErrorAttribute(
    minInterval: Int,
    maxInterval: Int
  ): OperationalErrorAttribute {
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
    const val CLUSTER_ID: UInt = 96u
  }
}
