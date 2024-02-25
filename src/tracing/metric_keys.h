/*
 *    Copyright (c) 2024 Project CHIP Authors
 *    All rights reserved.
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
#pragma once

#include <matter/tracing/build_config.h>

namespace chip {
namespace Tracing {

/**
 * Defines the key type use to identity a specific metric
 */
typedef const char * MetricKey;

/**
 * List of supported metric keys
 */
constexpr MetricKey kMetricDiscoveryOverBLE      = "disc-over-ble";
constexpr MetricKey kMetricDiscoveryOnNetwork    = "disc-on-nw";
constexpr MetricKey kMetricPASESession           = "pase-session";
constexpr MetricKey kMetricPASESessionPair       = "pase-session-pair";
constexpr MetricKey kMetricPASESessionBLE        = "pase-session-ble";
constexpr MetricKey kMetricAttestationResult     = "attestation-result";
constexpr MetricKey kMetricAttestationOverridden = "attestation-overridden";
constexpr MetricKey kMetricCASESession           = "case-session";
constexpr MetricKey kMetricCASESessionEstState   = "case-conn-est";
constexpr MetricKey kMetricWiFiRSSI              = "wifi_rssi";

} // namespace Tracing
} // namespace chip
