/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.protocol;

import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.clusters.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Enumeration of ZigBee Clusters
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-04-07T17:43:10Z")
public enum ZclClusterType {
    BASIC(0x0000, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclBasicCluster.class, "Basic"),
    POWER_CONFIGURATION(0x0001, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclPowerConfigurationCluster.class, "Power configuration"),
    DEVICE_TEMPERATURE_CONFIGURATION(0x0002, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclDeviceTemperatureConfigurationCluster.class, "Device Temperature Configuration"),
    IDENTIFY(0x0003, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclIdentifyCluster.class, "Identify"),
    GROUPS(0x0004, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclGroupsCluster.class, "Groups"),
    SCENES(0x0005, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclScenesCluster.class, "Scenes"),
    ON_OFF(0x0006, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclOnOffCluster.class, "On/Off"),
    ON_OFF_SWITCH_CONFIGURATION(0x0007, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclOnOffSwitchConfigurationCluster.class, "On/off Switch Configuration"),
    LEVEL_CONTROL(0x0008, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclLevelControlCluster.class, "Level Control"),
    ALARMS(0x0009, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclAlarmsCluster.class, "Alarms"),
    TIME(0x000A, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclTimeCluster.class, "Time"),
    RSSI_LOCATION(0x000B, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclRssiLocationCluster.class, "RSSI Location"),
    ANALOG_INPUT__BASIC(0x000C, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclAnalogInputBasicCluster.class, "Analog Input (Basic)"),
    ANALOG_OUTPUT__BASIC(0x000D, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclAnalogOutputBasicCluster.class, "Analog Output (Basic)"),
    ANALOG_VALUE__BASIC(0x000E, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclAnalogValueBasicCluster.class, "Analog Value (Basic)"),
    BINARY_INPUT__BASIC(0x000F, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclBinaryInputBasicCluster.class, "Binary Input (Basic)"),
    BINARY_OUTPUT__BASIC(0x0010, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclBinaryOutputBasicCluster.class, "Binary Output (Basic)"),
    BINARY_VALUE__BASIC(0x0011, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclBinaryValueBasicCluster.class, "Binary Value (Basic)"),
    MULTISTATE_INPUT__BASIC(0x0012, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclMultistateInputBasicCluster.class, "Multistate Input (Basic)"),
    MULTISTATE_OUTPUT__BASIC(0x0013, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclMultistateOutputBasicCluster.class, "Multistate Output (Basic)"),
    MULTISTATE_VALUE__BASIC(0x0014, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclMultistateValueBasicCluster.class, "Multistate Value (Basic)"),
    COMMISSIONING(0x0015, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclCommissioningCluster.class, "Commissioning"),
    OTA_UPGRADE(0x0019, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclOtaUpgradeCluster.class, "OTA Upgrade"),
    APPLIANCE_CONTROL(0x001B, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclApplianceControlCluster.class, "Appliance Control"),
    POLL_CONTROL(0x0020, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclPollControlCluster.class, "Poll Control"),
    SHADE_CONFIGURATION(0x0100, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclShadeConfigurationCluster.class, "Shade Configuration"),
    DOOR_LOCK(0x0101, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclDoorLockCluster.class, "Door Lock"),
    PUMP_CONFIGURATION_AND_CONTROL(0x0200, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclPumpConfigurationAndControlCluster.class, "Pump Configuration and Control"),
    THERMOSTAT(0x0201, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclThermostatCluster.class, "Thermostat"),
    FAN_CONTROL(0x0202, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclFanControlCluster.class, "Fan Control"),
    DEHUMIDIFICATION_CONTROL(0x0203, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclDehumidificationControlCluster.class, "Dehumidification Control"),
    THERMOSTAT_USER_INTERFACE_CONFIGURATION(0x0204, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclThermostatUserInterfaceConfigurationCluster.class, "Thermostat User Interface Configuration"),
    COLOR_CONTROL(0x0300, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclColorControlCluster.class, "Color Control"),
    BALLAST_CONFIGURATION(0x0301, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclBallastConfigurationCluster.class, "Ballast Configuration"),
    ILLUMINANCE_MEASUREMENT(0x0400, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclIlluminanceMeasurementCluster.class, "Illuminance measurement"),
    ILLUMINANCE_LEVEL_SENSING(0x0401, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclIlluminanceLevelSensingCluster.class, "Illuminance level sensing"),
    TEMPERATURE_MEASUREMENT(0x0402, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclTemperatureMeasurementCluster.class, "Temperature measurement"),
    PRESSURE_MEASUREMENT(0x0403, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclPressureMeasurementCluster.class, "Pressure measurement"),
    FLOW_MEASUREMENT(0x0404, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclFlowMeasurementCluster.class, "Flow measurement"),
    RELATIVE_HUMIDITY_MEASUREMENT(0x0405, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclRelativeHumidityMeasurementCluster.class, "Relative humidity measurement"),
    OCCUPANCY_SENSING(0x0406, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclOccupancySensingCluster.class, "Occupancy sensing"),
    IAS_ZONE(0x0500, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclIasZoneCluster.class, "IAS Zone"),
    IAS_ACE(0x0501, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclIasAceCluster.class, "IAS ACE"),
    IAS_WD(0x0502, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclIasWdCluster.class, "IAS WD"),
    GENERIC_TUNNEL(0x0600, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclGenericTunnelCluster.class, "Generic Tunnel"),
    BACNET_PROTOCOL_TUNNEL(0x0601, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclBaCnetProtocolTunnelCluster.class, "BACnet Protocol Tunnel"),
    ANALOG_INPUT__BACNET_REGULAR(0x0602, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclAnalogInputBaCnetRegularCluster.class, "Analog Input (BACnet Regular)"),
    ANALOG_INPUT__BACNET_EXTENDED(0x0603, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclAnalogInputBaCnetExtendedCluster.class, "Analog Input (BACnet Extended)"),
    ANALOG_OUTPUT__BACNET_REGULAR(0x0604, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclAnalogOutputBaCnetRegularCluster.class, "Analog Output (BACnet Regular)"),
    ANALOG_OUTPUT__BACNET_EXTENDED(0x0605, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclAnalogOutputBaCnetExtendedCluster.class, "Analog Output (BACnet Extended)"),
    ANALOG_VALUE__BACNET_REGULAR(0x0606, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclAnalogValueBaCnetRegularCluster.class, "Analog Value (BACnet Regular)"),
    ANALOG_VALUE__BACNET_EXTENDED(0x0607, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclAnalogValueBaCnetExtendedCluster.class, "Analog Value (BACnet Extended)"),
    BINARY_INPUT__BACNET_REGULAR(0x0608, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclBinaryInputBaCnetRegularCluster.class, "Binary Input (BACnet Regular)"),
    BINARY_INPUT__BACNET_EXTENDED(0x0609, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclBinaryInputBaCnetExtendedCluster.class, "Binary Input (BACnet Extended)"),
    BINARY_OUTPUT__BACNET_REGULAR(0x060A, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclBinaryOutputBaCnetRegularCluster.class, "Binary Output (BACnet Regular)"),
    BINARY_OUTPUT__BACNET_EXTENDED(0x060B, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclBinaryOutputBaCnetExtendedCluster.class, "Binary Output (BACnet Extended)"),
    BINARY_VALUE__BACNET_REGULAR(0x060C, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclBinaryValueBaCnetRegularCluster.class, "Binary Value (BACnet Regular)"),
    BINARY_VALUE__BACNET_EXTENDED(0x060D, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclBinaryValueBaCnetExtendedCluster.class, "Binary Value (BACnet Extended)"),
    MULTISTATE_INPUT__BACNET_REGULAR(0x060E, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclMultistateInputBaCnetRegularCluster.class, "Multistate Input (BACnet Regular)"),
    MULTISTATE_INPUT__BACNET_EXTENDED(0x060F, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclMultistateInputBaCnetExtendedCluster.class, "Multistate Input (BACnet Extended)"),
    MULTISTATE_OUTPUT__BACNET_REGULAR(0x0610, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclMultistateOutputBaCnetRegularCluster.class, "Multistate Output (BACnet Regular)"),
    MULTISTATE_OUTPUT__BACNET_EXTENDED(0x0611, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclMultistateOutputBaCnetExtendedCluster.class, "Multistate Output (BACnet Extended)"),
    MULTISTATE_VALUE__BACNET_REGULAR(0x0612, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclMultistateValueBaCnetRegularCluster.class, "Multistate Value (BACnet Regular)"),
    MULTISTATE_VALUE__BACNET_EXTENDED(0x0613, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclMultistateValueBaCnetExtendedCluster.class, "Multistate Value (BACnet Extended)"),
    PRICE(0x0700, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclPriceCluster.class, "Price"),
    DEMAND_RESPONSE_AND_LOAD_CONTROL(0x0701, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclDemandResponseAndLoadControlCluster.class, "Demand Response and Load Control"),
    METERING(0x0702, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclMeteringCluster.class, "Metering"),
    MESSAGING(0x0703, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclMessagingCluster.class, "Messaging"),
    TUNNELING(0x0704, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclTunnelingCluster.class, "Tunneling"),
    KEY_ESTABLISHMENT(0x0800, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclKeyEstablishmentCluster.class, "Key Establishment"),
    APPLIANCE_IDENTIFICATION(0x0B00, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclApplianceIdentificationCluster.class, "Appliance Identification"),
    APPLIANCE_EVENTS_AND_ALERTS(0x0B02, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclApplianceEventsAndAlertsCluster.class, "Appliance Events and Alerts"),
    APPLIANCE_STATISTICS(0x0B03, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclApplianceStatisticsCluster.class, "Appliance Statistics"),
    ELECTRICAL_MEASUREMENT(0x0B04, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclElectricalMeasurementCluster.class, "Electrical Measurement"),
    DIAGNOSTICS(0x0B05, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclDiagnosticsCluster.class, "Diagnostics"),
    GENERAL(0xFFFF, ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION, ZclGeneralCluster.class, "General"),
    TOUCHLINK(0x1000, ZigBeeProfileType.ZIGBEE_LIGHT_LINK, ZclTouchlinkCluster.class, "Touchlink");

    private static final Map<Integer, ZclClusterType> idValueMap = new HashMap<Integer, ZclClusterType>();

    private final int clusterId;
    private final ZigBeeProfileType profileType;
    private final String label;
    private final Class<? extends ZclCluster> clusterClass;

    ZclClusterType(final int clusterId, final ZigBeeProfileType profileType, final Class<? extends ZclCluster>clusterClass, final String label) {
        this.clusterId = clusterId;
        this.profileType = profileType;
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

    public ZigBeeProfileType getProfileType() {
        return profileType;
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
