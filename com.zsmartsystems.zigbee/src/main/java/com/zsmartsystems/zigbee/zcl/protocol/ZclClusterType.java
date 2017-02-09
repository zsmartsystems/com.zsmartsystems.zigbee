package com.zsmartsystems.zigbee.zcl.protocol;

import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.clusters.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Code is auto-generated. Modifications may be overwritten!
 */
public enum ZclClusterType {
    BASIC(0, ZclProfileType.HOME_AUTOMATION, ZclBasicCluster.class, "Basic"),
    POWER_CONFIGURATION(1, ZclProfileType.HOME_AUTOMATION, ZclPowerConfigurationCluster.class, "Power configuration"),
    DEVICE_TEMPERATURE_CONFIGURATION(2, ZclProfileType.HOME_AUTOMATION, ZclDeviceTemperatureConfigurationCluster.class, "Device Temperature Configuration"),
    IDENTIFY(3, ZclProfileType.HOME_AUTOMATION, ZclIdentifyCluster.class, "Identify"),
    GROUPS(4, ZclProfileType.HOME_AUTOMATION, ZclGroupsCluster.class, "Groups"),
    SCENES(5, ZclProfileType.HOME_AUTOMATION, ZclScenesCluster.class, "Scenes"),
    ON_OFF(6, ZclProfileType.HOME_AUTOMATION, ZclOnOffCluster.class, "On/Off"),
    ON_OFF_SWITCH_CONFIGURATION(7, ZclProfileType.HOME_AUTOMATION, ZclOnOffSwitchConfigurationCluster.class, "On/off Switch Configuration"),
    LEVEL_CONTROL(8, ZclProfileType.HOME_AUTOMATION, ZclLevelControlCluster.class, "Level Control"),
    ALARMS(9, ZclProfileType.HOME_AUTOMATION, ZclAlarmsCluster.class, "Alarms"),
    TIME(10, ZclProfileType.HOME_AUTOMATION, ZclTimeCluster.class, "Time"),
    RSSI_LOCATION(11, ZclProfileType.HOME_AUTOMATION, ZclRssiLocationCluster.class, "RSSI Location"),
    ANALOG_INPUT__BASIC(12, ZclProfileType.HOME_AUTOMATION, ZclAnalogInputBasicCluster.class, "Analog Input (Basic)"),
    ANALOG_OUTPUT__BASIC(13, ZclProfileType.HOME_AUTOMATION, ZclAnalogOutputBasicCluster.class, "Analog Output (Basic)"),
    ANALOG_VALUE__BASIC(14, ZclProfileType.HOME_AUTOMATION, ZclAnalogValueBasicCluster.class, "Analog Value (Basic)"),
    BINARY_INPUT__BASIC(15, ZclProfileType.HOME_AUTOMATION, ZclBinaryInputBasicCluster.class, "Binary Input (Basic)"),
    BINARY_OUTPUT__BASIC(16, ZclProfileType.HOME_AUTOMATION, ZclBinaryOutputBasicCluster.class, "Binary Output (Basic)"),
    BINARY_VALUE__BASIC(17, ZclProfileType.HOME_AUTOMATION, ZclBinaryValueBasicCluster.class, "Binary Value (Basic)"),
    MULTISTATE_INPUT__BASIC(18, ZclProfileType.HOME_AUTOMATION, ZclMultistateInputBasicCluster.class, "Multistate Input (Basic)"),
    MULTISTATE_OUTPUT__BASIC(19, ZclProfileType.HOME_AUTOMATION, ZclMultistateOutputBasicCluster.class, "Multistate Output (Basic)"),
    MULTISTATE_VALUE__BASIC(20, ZclProfileType.HOME_AUTOMATION, ZclMultistateValueBasicCluster.class, "Multistate Value (Basic)"),
    COMMISSIONING(21, ZclProfileType.HOME_AUTOMATION, ZclCommissioningCluster.class, "Commissioning"),
    SHADE_CONFIGURATION(256, ZclProfileType.HOME_AUTOMATION, ZclShadeConfigurationCluster.class, "Shade Configuration"),
    DOOR_LOCK(257, ZclProfileType.HOME_AUTOMATION, ZclDoorLockCluster.class, "Door Lock"),
    PUMP_CONFIGURATION_AND_CONTROL(512, ZclProfileType.HOME_AUTOMATION, ZclPumpConfigurationAndControlCluster.class, "Pump Configuration and Control"),
    THERMOSTAT(513, ZclProfileType.HOME_AUTOMATION, ZclThermostatCluster.class, "Thermostat"),
    FAN_CONTROL(514, ZclProfileType.HOME_AUTOMATION, ZclFanControlCluster.class, "Fan Control"),
    DEHUMIDIFICATION_CONTROL(515, ZclProfileType.HOME_AUTOMATION, ZclDehumidificationControlCluster.class, "Dehumidification Control"),
    THERMOSTAT_USER_INTERFACE_CONFIGURATION(516, ZclProfileType.HOME_AUTOMATION, ZclThermostatUserInterfaceConfigurationCluster.class, "Thermostat User Interface Configuration"),
    COLOR_CONTROL(768, ZclProfileType.HOME_AUTOMATION, ZclColorControlCluster.class, "Color control"),
    BALLAST_CONFIGURATION(769, ZclProfileType.HOME_AUTOMATION, ZclBallastConfigurationCluster.class, "Ballast Configuration"),
    ILLUMINANCE_MEASUREMENT(1024, ZclProfileType.HOME_AUTOMATION, ZclIlluminanceMeasurementCluster.class, "Illuminance measurement"),
    ILLUMINANCE_LEVEL_SENSING(1025, ZclProfileType.HOME_AUTOMATION, ZclIlluminanceLevelSensingCluster.class, "Illuminance level sensing"),
    TEMPERATURE_MEASUREMENT(1026, ZclProfileType.HOME_AUTOMATION, ZclTemperatureMeasurementCluster.class, "Temperature measurement"),
    PRESSURE_MEASUREMENT(1027, ZclProfileType.HOME_AUTOMATION, ZclPressureMeasurementCluster.class, "Pressure measurement"),
    FLOW_MEASUREMENT(1028, ZclProfileType.HOME_AUTOMATION, ZclFlowMeasurementCluster.class, "Flow measurement"),
    RELATIVE_HUMIDITY_MEASUREMENT(1029, ZclProfileType.HOME_AUTOMATION, ZclRelativeHumidityMeasurementCluster.class, "Relative humidity measurement"),
    OCCUPANCY_SENSING(1030, ZclProfileType.HOME_AUTOMATION, ZclOccupancySensingCluster.class, "Occupancy sensing"),
    IAS_ZONE(1280, ZclProfileType.HOME_AUTOMATION, ZclIasZoneCluster.class, "IAS Zone"),
    IAS_ACE(1281, ZclProfileType.HOME_AUTOMATION, ZclIasAceCluster.class, "IAS ACE"),
    IAS_WD(1282, ZclProfileType.HOME_AUTOMATION, ZclIasWdCluster.class, "IAS WD"),
    GENERIC_TUNNEL(1536, ZclProfileType.HOME_AUTOMATION, ZclGenericTunnelCluster.class, "Generic Tunnel"),
    BACNET_PROTOCOL_TUNNEL(1537, ZclProfileType.HOME_AUTOMATION, ZclBaCnetProtocolTunnelCluster.class, "BACnet Protocol Tunnel"),
    ANALOG_INPUT__BACNET_REGULAR(1538, ZclProfileType.HOME_AUTOMATION, ZclAnalogInputBaCnetRegularCluster.class, "Analog Input (BACnet Regular)"),
    ANALOG_INPUT__BACNET_EXTENDED(1539, ZclProfileType.HOME_AUTOMATION, ZclAnalogInputBaCnetExtendedCluster.class, "Analog Input (BACnet Extended)"),
    ANALOG_OUTPUT__BACNET_REGULAR(1540, ZclProfileType.HOME_AUTOMATION, ZclAnalogOutputBaCnetRegularCluster.class, "Analog Output (BACnet Regular)"),
    ANALOG_OUTPUT__BACNET_EXTENDED(1541, ZclProfileType.HOME_AUTOMATION, ZclAnalogOutputBaCnetExtendedCluster.class, "Analog Output (BACnet Extended)"),
    ANALOG_VALUE__BACNET_REGULAR(1542, ZclProfileType.HOME_AUTOMATION, ZclAnalogValueBaCnetRegularCluster.class, "Analog Value (BACnet Regular)"),
    ANALOG_VALUE__BACNET_EXTENDED(1543, ZclProfileType.HOME_AUTOMATION, ZclAnalogValueBaCnetExtendedCluster.class, "Analog Value (BACnet Extended)"),
    BINARY_INPUT__BACNET_REGULAR(1544, ZclProfileType.HOME_AUTOMATION, ZclBinaryInputBaCnetRegularCluster.class, "Binary Input (BACnet Regular)"),
    BINARY_INPUT__BACNET_EXTENDED(1545, ZclProfileType.HOME_AUTOMATION, ZclBinaryInputBaCnetExtendedCluster.class, "Binary Input (BACnet Extended)"),
    BINARY_OUTPUT__BACNET_REGULAR(1546, ZclProfileType.HOME_AUTOMATION, ZclBinaryOutputBaCnetRegularCluster.class, "Binary Output (BACnet Regular)"),
    BINARY_OUTPUT__BACNET_EXTENDED(1547, ZclProfileType.HOME_AUTOMATION, ZclBinaryOutputBaCnetExtendedCluster.class, "Binary Output (BACnet Extended)"),
    BINARY_VALUE__BACNET_REGULAR(1548, ZclProfileType.HOME_AUTOMATION, ZclBinaryValueBaCnetRegularCluster.class, "Binary Value (BACnet Regular)"),
    BINARY_VALUE__BACNET_EXTENDED(1549, ZclProfileType.HOME_AUTOMATION, ZclBinaryValueBaCnetExtendedCluster.class, "Binary Value (BACnet Extended)"),
    MULTISTATE_INPUT__BACNET_REGULAR(1550, ZclProfileType.HOME_AUTOMATION, ZclMultistateInputBaCnetRegularCluster.class, "Multistate Input (BACnet Regular)"),
    MULTISTATE_INPUT__BACNET_EXTENDED(1551, ZclProfileType.HOME_AUTOMATION, ZclMultistateInputBaCnetExtendedCluster.class, "Multistate Input (BACnet Extended)"),
    MULTISTATE_OUTPUT__BACNET_REGULAR(1552, ZclProfileType.HOME_AUTOMATION, ZclMultistateOutputBaCnetRegularCluster.class, "Multistate Output (BACnet Regular)"),
    MULTISTATE_OUTPUT__BACNET_EXTENDED(1553, ZclProfileType.HOME_AUTOMATION, ZclMultistateOutputBaCnetExtendedCluster.class, "Multistate Output (BACnet Extended)"),
    MULTISTATE_VALUE__BACNET_REGULAR(1554, ZclProfileType.HOME_AUTOMATION, ZclMultistateValueBaCnetRegularCluster.class, "Multistate Value (BACnet Regular)"),
    MULTISTATE_VALUE__BACNET_EXTENDED(1555, ZclProfileType.HOME_AUTOMATION, ZclMultistateValueBaCnetExtendedCluster.class, "Multistate Value (BACnet Extended)"),
    GENERAL(65535, ZclProfileType.HOME_AUTOMATION, ZclGeneralCluster.class, "General");

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

    public int getId() {
        return id;
    }

    public ZclProfileType getProfileType() {
        return profileType;
    }

    public String getLabel() {
        return label;
    }

    public String toString() {
        return label;
    }

    public Class<? extends ZclCluster> getClusterClass() {
        return clusterClass;
    }

    public static ZclClusterType getValueById(final int id) {
        return idValueMap.get(id);
    }

    static {
        for (final ZclClusterType value : values()) {
            idValueMap.put(value.id, value);
        }
    }

}
