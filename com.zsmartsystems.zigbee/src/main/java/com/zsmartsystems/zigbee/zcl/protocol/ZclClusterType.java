/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.protocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclAlarmsCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBinaryInputBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclColorControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclDehumidificationControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclDemandResponseAndLoadControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclDiagnosticsCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclDoorLockCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclElectricalMeasurementCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclFanControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclFlowMeasurementCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclGroupsCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIasAceCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIasWdCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIasZoneCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIdentifyCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIlluminanceLevelSensingCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIlluminanceMeasurementCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclKeyEstablishmentCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclLevelControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclMessagingCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclMeteringCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclMultistateInputBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclMultistateOutputBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclMultistateValueBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOccupancySensingCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffSwitchConfigurationCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOtaUpgradeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclPollControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclPowerConfigurationCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclPrepaymentCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclPressureMeasurementCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclPriceCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclRelativeHumidityMeasurementCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclRssiLocationCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclScenesCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclSmartEnergyTunnelingCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclTemperatureMeasurementCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclThermostatCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclThermostatUserInterfaceConfigurationCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclTimeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclWindowCoveringCluster;

/**
 * Enumeration of ZigBee Clusters
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson

 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T19:25:17Z")
public enum ZclClusterType {
    BASIC(0x0000, ZclBasicCluster.class, "Basic"),
    POWER_CONFIGURATION(0x0001, ZclPowerConfigurationCluster.class, "Power Configuration"),
    IDENTIFY(0x0003, ZclIdentifyCluster.class, "Identify"),
    GROUPS(0x0004, ZclGroupsCluster.class, "Groups"),
    SCENES(0x0005, ZclScenesCluster.class, "Scenes"),
    ON_OFF(0x0006, ZclOnOffCluster.class, "On/Off"),
    ON_OFF_SWITCH_CONFIGURATION(0x0007, ZclOnOffSwitchConfigurationCluster.class, "On / Off Switch Configuration"),
    LEVEL_CONTROL(0x0008, ZclLevelControlCluster.class, "Level Control"),
    ALARMS(0x0009, ZclAlarmsCluster.class, "Alarms"),
    TIME(0x000A, ZclTimeCluster.class, "Time"),
    RSSI_LOCATION(0x000B, ZclRssiLocationCluster.class, "RSSI Location"),
    BINARY_INPUT_BASIC(0x000F, ZclBinaryInputBasicCluster.class, "Binary Input (Basic)"),
    MULTISTATE_INPUT_BASIC(0x0012, ZclMultistateInputBasicCluster.class, "Multistate Input (Basic)"),
    MULTISTATE_OUTPUT_BASIC(0x0013, ZclMultistateOutputBasicCluster.class, "Multistate Output (Basic)"),
    MULTISTATE_VALUE_BASIC(0x0014, ZclMultistateValueBasicCluster.class, "Multistate Value (Basic)"),
    OTA_UPGRADE(0x0019, ZclOtaUpgradeCluster.class, "Ota Upgrade"),
    POLL_CONTROL(0x0020, ZclPollControlCluster.class, "Poll Control"),
    DOOR_LOCK(0x0101, ZclDoorLockCluster.class, "Door Lock"),
    WINDOW_COVERING(0x0102, ZclWindowCoveringCluster.class, "Window Covering"),
    THERMOSTAT(0x0201, ZclThermostatCluster.class, "Thermostat"),
    FAN_CONTROL(0x0202, ZclFanControlCluster.class, "Fan Control"),
    DEHUMIDIFICATION_CONTROL(0x0203, ZclDehumidificationControlCluster.class, "Dehumidification Control"),
    THERMOSTAT_USER_INTERFACE_CONFIGURATION(0x0204, ZclThermostatUserInterfaceConfigurationCluster.class, "Thermostat User Interface Configuration"),
    COLOR_CONTROL(0x0300, ZclColorControlCluster.class, "Color Control"),
    ILLUMINANCE_MEASUREMENT(0x0400, ZclIlluminanceMeasurementCluster.class, "Illuminance Measurement"),
    ILLUMINANCE_LEVEL_SENSING(0x0401, ZclIlluminanceLevelSensingCluster.class, "Illuminance Level Sensing"),
    TEMPERATURE_MEASUREMENT(0x0402, ZclTemperatureMeasurementCluster.class, "Temperature Measurement"),
    PRESSURE_MEASUREMENT(0x0403, ZclPressureMeasurementCluster.class, "Pressure Measurement"),
    FLOW_MEASUREMENT(0x0404, ZclFlowMeasurementCluster.class, "Flow Measurement"),
    RELATIVE_HUMIDITY_MEASUREMENT(0x0405, ZclRelativeHumidityMeasurementCluster.class, "Relative Humidity Measurement"),
    OCCUPANCY_SENSING(0x0406, ZclOccupancySensingCluster.class, "Occupancy Sensing"),
    IAS_ZONE(0x0500, ZclIasZoneCluster.class, "IAS Zone"),
    IAS_ACE(0x0501, ZclIasAceCluster.class, "IAS ACE"),
    IAS_WD(0x0502, ZclIasWdCluster.class, "IAS WD"),
    PRICE(0x0700, ZclPriceCluster.class, "Price"),
    DEMAND_RESPONSE_AND_LOAD_CONTROL(0x0701, ZclDemandResponseAndLoadControlCluster.class, "Demand Response And Load Control"),
    METERING(0x0702, ZclMeteringCluster.class, "Metering"),
    MESSAGING(0x0703, ZclMessagingCluster.class, "Messaging"),
    SMART_ENERGY_TUNNELING(0x0704, ZclSmartEnergyTunnelingCluster.class, "Smart Energy Tunneling"),
    PREPAYMENT(0x0705, ZclPrepaymentCluster.class, "Prepayment"),
    KEY_ESTABLISHMENT(0x0800, ZclKeyEstablishmentCluster.class, "Key Establishment"),
    ELECTRICAL_MEASUREMENT(0x0B04, ZclElectricalMeasurementCluster.class, "Electrical Measurement"),
    DIAGNOSTICS(0x0B05, ZclDiagnosticsCluster.class, "Diagnostics");

    private static final Map<Integer, ZclClusterType> idValueMap = new ConcurrentHashMap<>();

    private final int clusterId;
    private final String label;
    private final Class<? extends ZclCluster> clusterClass;

    ZclClusterType(final int clusterId, final Class<? extends ZclCluster>clusterClass, final String label) {
        this.clusterId = clusterId;
        this.clusterClass = clusterClass;
        this.label = label;
    }

    static {
        for (final ZclClusterType value : values()) {
            idValueMap.put(value.clusterId, value);
        }
    }

    public int getId() {
        return clusterId;
    }

    public String getLabel() {
        return label;
    }

    public Class<? extends ZclCluster> getClusterClass() {
        return clusterClass;
    }

    public static ZclClusterType getValueById(final int clusterId) {
        return idValueMap.get(clusterId);
    }

}
