/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.protocol;

import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.clusters.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Code is auto-generated. Modifications may be overwritten!
 */
public enum ZclClusterType {
    BASIC(0x0000, ZclProfileType.HOME_AUTOMATION, ZclBasicCluster.class, "Basic"),
    POWER_CONFIGURATION(0x0001, ZclProfileType.HOME_AUTOMATION, ZclPowerConfigurationCluster.class, "Power configuration"),
    DEVICE_TEMPERATURE_CONFIGURATION(0x0002, ZclProfileType.HOME_AUTOMATION, ZclDeviceTemperatureConfigurationCluster.class, "Device Temperature Configuration"),
    IDENTIFY(0x0003, ZclProfileType.HOME_AUTOMATION, ZclIdentifyCluster.class, "Identify"),
    GROUPS(0x0004, ZclProfileType.HOME_AUTOMATION, ZclGroupsCluster.class, "Groups"),
    SCENES(0x0005, ZclProfileType.HOME_AUTOMATION, ZclScenesCluster.class, "Scenes"),
    ON_OFF(0x0006, ZclProfileType.HOME_AUTOMATION, ZclOnOffCluster.class, "On/Off"),
    ON_OFF_SWITCH_CONFIGURATION(0x0007, ZclProfileType.HOME_AUTOMATION, ZclOnOffSwitchConfigurationCluster.class, "On/off Switch Configuration"),
    LEVEL_CONTROL(0x0008, ZclProfileType.HOME_AUTOMATION, ZclLevelControlCluster.class, "Level Control"),
    ALARMS(0x0009, ZclProfileType.HOME_AUTOMATION, ZclAlarmsCluster.class, "Alarms"),
    TIME(0x000A, ZclProfileType.HOME_AUTOMATION, ZclTimeCluster.class, "Time"),
    RSSI_LOCATION(0x000B, ZclProfileType.HOME_AUTOMATION, ZclRssiLocationCluster.class, "RSSI Location"),
    ANALOG_INPUT__BASIC(0x000C, ZclProfileType.HOME_AUTOMATION, ZclAnalogInputBasicCluster.class, "Analog Input (Basic)"),
    ANALOG_OUTPUT__BASIC(0x000D, ZclProfileType.HOME_AUTOMATION, ZclAnalogOutputBasicCluster.class, "Analog Output (Basic)"),
    ANALOG_VALUE__BASIC(0x000E, ZclProfileType.HOME_AUTOMATION, ZclAnalogValueBasicCluster.class, "Analog Value (Basic)"),
    BINARY_INPUT__BASIC(0x000F, ZclProfileType.HOME_AUTOMATION, ZclBinaryInputBasicCluster.class, "Binary Input (Basic)"),
    BINARY_OUTPUT__BASIC(0x0010, ZclProfileType.HOME_AUTOMATION, ZclBinaryOutputBasicCluster.class, "Binary Output (Basic)"),
    BINARY_VALUE__BASIC(0x0011, ZclProfileType.HOME_AUTOMATION, ZclBinaryValueBasicCluster.class, "Binary Value (Basic)"),
    MULTISTATE_INPUT__BASIC(0x0012, ZclProfileType.HOME_AUTOMATION, ZclMultistateInputBasicCluster.class, "Multistate Input (Basic)"),
    MULTISTATE_OUTPUT__BASIC(0x0013, ZclProfileType.HOME_AUTOMATION, ZclMultistateOutputBasicCluster.class, "Multistate Output (Basic)"),
    MULTISTATE_VALUE__BASIC(0x0014, ZclProfileType.HOME_AUTOMATION, ZclMultistateValueBasicCluster.class, "Multistate Value (Basic)"),
    COMMISSIONING(0x0015, ZclProfileType.HOME_AUTOMATION, ZclCommissioningCluster.class, "Commissioning"),
    OTA_UPGRADE(0x0019, ZclProfileType.HOME_AUTOMATION, ZclOtaUpgradeCluster.class, "OTA Upgrade"),
    SHADE_CONFIGURATION(0x0100, ZclProfileType.HOME_AUTOMATION, ZclShadeConfigurationCluster.class, "Shade Configuration"),
    DOOR_LOCK(0x0101, ZclProfileType.HOME_AUTOMATION, ZclDoorLockCluster.class, "Door Lock"),
    PUMP_CONFIGURATION_AND_CONTROL(0x0200, ZclProfileType.HOME_AUTOMATION, ZclPumpConfigurationAndControlCluster.class, "Pump Configuration and Control"),
    THERMOSTAT(0x0201, ZclProfileType.HOME_AUTOMATION, ZclThermostatCluster.class, "Thermostat"),
    FAN_CONTROL(0x0202, ZclProfileType.HOME_AUTOMATION, ZclFanControlCluster.class, "Fan Control"),
    DEHUMIDIFICATION_CONTROL(0x0203, ZclProfileType.HOME_AUTOMATION, ZclDehumidificationControlCluster.class, "Dehumidification Control"),
    THERMOSTAT_USER_INTERFACE_CONFIGURATION(0x0204, ZclProfileType.HOME_AUTOMATION, ZclThermostatUserInterfaceConfigurationCluster.class, "Thermostat User Interface Configuration"),
    COLOR_CONTROL(0x0300, ZclProfileType.HOME_AUTOMATION, ZclColorControlCluster.class, "Color control"),
    BALLAST_CONFIGURATION(0x0301, ZclProfileType.HOME_AUTOMATION, ZclBallastConfigurationCluster.class, "Ballast Configuration"),
    ILLUMINANCE_MEASUREMENT(0x0400, ZclProfileType.HOME_AUTOMATION, ZclIlluminanceMeasurementCluster.class, "Illuminance measurement"),
    ILLUMINANCE_LEVEL_SENSING(0x0401, ZclProfileType.HOME_AUTOMATION, ZclIlluminanceLevelSensingCluster.class, "Illuminance level sensing"),
    TEMPERATURE_MEASUREMENT(0x0402, ZclProfileType.HOME_AUTOMATION, ZclTemperatureMeasurementCluster.class, "Temperature measurement"),
    PRESSURE_MEASUREMENT(0x0403, ZclProfileType.HOME_AUTOMATION, ZclPressureMeasurementCluster.class, "Pressure measurement"),
    FLOW_MEASUREMENT(0x0404, ZclProfileType.HOME_AUTOMATION, ZclFlowMeasurementCluster.class, "Flow measurement"),
    RELATIVE_HUMIDITY_MEASUREMENT(0x0405, ZclProfileType.HOME_AUTOMATION, ZclRelativeHumidityMeasurementCluster.class, "Relative humidity measurement"),
    OCCUPANCY_SENSING(0x0406, ZclProfileType.HOME_AUTOMATION, ZclOccupancySensingCluster.class, "Occupancy sensing"),
    IAS_ZONE(0x0500, ZclProfileType.HOME_AUTOMATION, ZclIasZoneCluster.class, "IAS Zone"),
    IAS_ACE(0x0501, ZclProfileType.HOME_AUTOMATION, ZclIasAceCluster.class, "IAS ACE"),
    IAS_WD(0x0502, ZclProfileType.HOME_AUTOMATION, ZclIasWdCluster.class, "IAS WD"),
    GENERIC_TUNNEL(0x0600, ZclProfileType.HOME_AUTOMATION, ZclGenericTunnelCluster.class, "Generic Tunnel"),
    BACNET_PROTOCOL_TUNNEL(0x0601, ZclProfileType.HOME_AUTOMATION, ZclBaCnetProtocolTunnelCluster.class, "BACnet Protocol Tunnel"),
    ANALOG_INPUT__BACNET_REGULAR(0x0602, ZclProfileType.HOME_AUTOMATION, ZclAnalogInputBaCnetRegularCluster.class, "Analog Input (BACnet Regular)"),
    ANALOG_INPUT__BACNET_EXTENDED(0x0603, ZclProfileType.HOME_AUTOMATION, ZclAnalogInputBaCnetExtendedCluster.class, "Analog Input (BACnet Extended)"),
    ANALOG_OUTPUT__BACNET_REGULAR(0x0604, ZclProfileType.HOME_AUTOMATION, ZclAnalogOutputBaCnetRegularCluster.class, "Analog Output (BACnet Regular)"),
    ANALOG_OUTPUT__BACNET_EXTENDED(0x0605, ZclProfileType.HOME_AUTOMATION, ZclAnalogOutputBaCnetExtendedCluster.class, "Analog Output (BACnet Extended)"),
    ANALOG_VALUE__BACNET_REGULAR(0x0606, ZclProfileType.HOME_AUTOMATION, ZclAnalogValueBaCnetRegularCluster.class, "Analog Value (BACnet Regular)"),
    ANALOG_VALUE__BACNET_EXTENDED(0x0607, ZclProfileType.HOME_AUTOMATION, ZclAnalogValueBaCnetExtendedCluster.class, "Analog Value (BACnet Extended)"),
    BINARY_INPUT__BACNET_REGULAR(0x0608, ZclProfileType.HOME_AUTOMATION, ZclBinaryInputBaCnetRegularCluster.class, "Binary Input (BACnet Regular)"),
    BINARY_INPUT__BACNET_EXTENDED(0x0609, ZclProfileType.HOME_AUTOMATION, ZclBinaryInputBaCnetExtendedCluster.class, "Binary Input (BACnet Extended)"),
    BINARY_OUTPUT__BACNET_REGULAR(0x060A, ZclProfileType.HOME_AUTOMATION, ZclBinaryOutputBaCnetRegularCluster.class, "Binary Output (BACnet Regular)"),
    BINARY_OUTPUT__BACNET_EXTENDED(0x060B, ZclProfileType.HOME_AUTOMATION, ZclBinaryOutputBaCnetExtendedCluster.class, "Binary Output (BACnet Extended)"),
    BINARY_VALUE__BACNET_REGULAR(0x060C, ZclProfileType.HOME_AUTOMATION, ZclBinaryValueBaCnetRegularCluster.class, "Binary Value (BACnet Regular)"),
    BINARY_VALUE__BACNET_EXTENDED(0x060D, ZclProfileType.HOME_AUTOMATION, ZclBinaryValueBaCnetExtendedCluster.class, "Binary Value (BACnet Extended)"),
    MULTISTATE_INPUT__BACNET_REGULAR(0x060E, ZclProfileType.HOME_AUTOMATION, ZclMultistateInputBaCnetRegularCluster.class, "Multistate Input (BACnet Regular)"),
    MULTISTATE_INPUT__BACNET_EXTENDED(0x060F, ZclProfileType.HOME_AUTOMATION, ZclMultistateInputBaCnetExtendedCluster.class, "Multistate Input (BACnet Extended)"),
    MULTISTATE_OUTPUT__BACNET_REGULAR(0x0610, ZclProfileType.HOME_AUTOMATION, ZclMultistateOutputBaCnetRegularCluster.class, "Multistate Output (BACnet Regular)"),
    MULTISTATE_OUTPUT__BACNET_EXTENDED(0x0611, ZclProfileType.HOME_AUTOMATION, ZclMultistateOutputBaCnetExtendedCluster.class, "Multistate Output (BACnet Extended)"),
    MULTISTATE_VALUE__BACNET_REGULAR(0x0612, ZclProfileType.HOME_AUTOMATION, ZclMultistateValueBaCnetRegularCluster.class, "Multistate Value (BACnet Regular)"),
    MULTISTATE_VALUE__BACNET_EXTENDED(0x0613, ZclProfileType.HOME_AUTOMATION, ZclMultistateValueBaCnetExtendedCluster.class, "Multistate Value (BACnet Extended)"),
    GENERAL(0xFFFF, ZclProfileType.HOME_AUTOMATION, ZclGeneralCluster.class, "General");

    private static final Map<Integer, ZclClusterType> idValueMap = new HashMap<Integer, ZclClusterType>();

    private final int id;
    private final ZclProfileType profileType;
    private final String label;
    private final Class<? extends ZclCluster> clusterClass;

    ZclClusterType(final int id, final ZclProfileType profileType, final Class<? extends ZclCluster>clusterClass, final String label) {
        this.id = id;
        this.profileType = profileType;
        this.clusterClass = clusterClass;
        this.label = label;
    }

    static {
        for (final ZclClusterType value : values()) {
            idValueMap.put(value.id, value);
        }
    }

    public int getId() {
        return id;
    }

    public ZclProfileType getProfileType() {
        return profileType;
    }

    public String getLabel() {
        return label;
    }

    public Class<? extends ZclCluster> getClusterClass() {
        return clusterClass;
    }

    public static ZclClusterType getValueById(final int id) {
        return idValueMap.get(id);
    }

}
