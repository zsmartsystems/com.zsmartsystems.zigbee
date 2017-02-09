package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.basic.ResetToFactoryDefaultsCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>Basic</b> cluster implementation (<i>Cluster ID 0x0000</i>).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclBasicCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x0000;

    // Cluster Name
    public static final String CLUSTER_NAME = "Basic";

    // Attribute constants
    public static final int ATTR_ZCLVERSION = 0x0000;
    public static final int ATTR_APPLICATIONVERSION = 0x0001;
    public static final int ATTR_STACKVERSION = 0x0002;
    public static final int ATTR_HWVERSION = 0x0003;
    public static final int ATTR_MANUFACTURERNAME = 0x0004;
    public static final int ATTR_MODELIDENTIFIER = 0x0005;
    public static final int ATTR_DATECODE = 0x0006;
    public static final int ATTR_POWERSOURCE = 0x0007;
    public static final int ATTR_LOCATIONDESCRIPTION = 0x0010;
    public static final int ATTR_PHYSICALENVIRONMENT = 0x0011;
    public static final int ATTR_DEVICEENABLED = 0x0012;
    public static final int ATTR_ALARMMASK = 0x0013;
    public static final int ATTR_DISABLELOCALCONFIG = 0x0014;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(13);

        attributeMap.put(ATTR_ZCLVERSION, new ZclAttribute(0, "ZCLVersion", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APPLICATIONVERSION, new ZclAttribute(1, "ApplicationVersion", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_STACKVERSION, new ZclAttribute(2, "StackVersion", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_HWVERSION, new ZclAttribute(3, "HWVersion", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MANUFACTURERNAME, new ZclAttribute(4, "ManufacturerName", ZclDataType.CHARACTER_STRING, true, true, false, false));
        attributeMap.put(ATTR_MODELIDENTIFIER, new ZclAttribute(5, "ModelIdentifier", ZclDataType.CHARACTER_STRING, true, true, false, false));
        attributeMap.put(ATTR_DATECODE, new ZclAttribute(6, "DateCode", ZclDataType.CHARACTER_STRING, true, true, false, false));
        attributeMap.put(ATTR_POWERSOURCE, new ZclAttribute(7, "PowerSource", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_LOCATIONDESCRIPTION, new ZclAttribute(16, "LocationDescription", ZclDataType.CHARACTER_STRING, true, true, true, false));
        attributeMap.put(ATTR_PHYSICALENVIRONMENT, new ZclAttribute(17, "PhysicalEnvironment", ZclDataType.ENUMERATION_8_BIT, true, true, true, false));
        attributeMap.put(ATTR_DEVICEENABLED, new ZclAttribute(18, "DeviceEnabled", ZclDataType.BOOLEAN, true, true, true, false));
        attributeMap.put(ATTR_ALARMMASK, new ZclAttribute(19, "AlarmMask", ZclDataType.BITMAP_8_BIT, true, true, true, false));
        attributeMap.put(ATTR_DISABLELOCALCONFIG, new ZclAttribute(20, "DisableLocalConfig", ZclDataType.BITMAP_8_BIT, true, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclBasicCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * <p>
     * Get the <i>ZCLVersion</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The ZCLVersion attribute is 8 bits in length and specifies the version number of
     * the ZigBee Cluster Library that all clusters on this endpoint conform to.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getZclVersionAsync() {
        return read(attributes.get(ATTR_ZCLVERSION));
    }


    /**
     * <p>
     * Synchronously get the <i>ZCLVersion</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The ZCLVersion attribute is 8 bits in length and specifies the version number of
     * the ZigBee Cluster Library that all clusters on this endpoint conform to.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getZclVersion() {
        return (Integer) readSync(attributes.get(ATTR_ZCLVERSION));
    }

    /**
     * <p>
     * Get the <i>ApplicationVersion</i> attribute [attribute ID <b>1</b>].
     * <p>
     * <br>
     * The ApplicationVersion attribute is 8 bits in length and specifies the version
     * number of the application software contained in the device. The usage of this
     * attribute is manufacturer dependent.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getApplicationVersionAsync() {
        return read(attributes.get(ATTR_APPLICATIONVERSION));
    }


    /**
     * <p>
     * Synchronously get the <i>ApplicationVersion</i> attribute [attribute ID <b>1</b>].
     * <p>
     * <br>
     * The ApplicationVersion attribute is 8 bits in length and specifies the version
     * number of the application software contained in the device. The usage of this
     * attribute is manufacturer dependent.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getApplicationVersion() {
        return (Integer) readSync(attributes.get(ATTR_APPLICATIONVERSION));
    }

    /**
     * <p>
     * Get the <i>StackVersion</i> attribute [attribute ID <b>2</b>].
     * <p>
     * <br>
     * The StackVersion attribute is 8 bits in length and specifies the version number
     * of the implementation of the ZigBee stack contained in the device. The usage of
     * this attribute is manufacturer dependent.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getStackVersionAsync() {
        return read(attributes.get(ATTR_STACKVERSION));
    }


    /**
     * <p>
     * Synchronously get the <i>StackVersion</i> attribute [attribute ID <b>2</b>].
     * <p>
     * <br>
     * The StackVersion attribute is 8 bits in length and specifies the version number
     * of the implementation of the ZigBee stack contained in the device. The usage of
     * this attribute is manufacturer dependent.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getStackVersion() {
        return (Integer) readSync(attributes.get(ATTR_STACKVERSION));
    }

    /**
     * <p>
     * Get the <i>HWVersion</i> attribute [attribute ID <b>3</b>].
     * <p>
     * <br>
     * The HWVersion attribute is 8 bits in length and specifies the version number of
     * the hardware of the device. The usage of this attribute is manufacturer dependent.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getHwVersionAsync() {
        return read(attributes.get(ATTR_HWVERSION));
    }


    /**
     * <p>
     * Synchronously get the <i>HWVersion</i> attribute [attribute ID <b>3</b>].
     * <p>
     * <br>
     * The HWVersion attribute is 8 bits in length and specifies the version number of
     * the hardware of the device. The usage of this attribute is manufacturer dependent.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getHwVersion() {
        return (Integer) readSync(attributes.get(ATTR_HWVERSION));
    }

    /**
     * <p>
     * Get the <i>ManufacturerName</i> attribute [attribute ID <b>4</b>].
     * <p>
     * <br>
     * The ManufacturerName attribute is a maximum of 32 bytes in length and specifies
     * the name of the manufacturer as a ZigBee character string.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getManufacturerNameAsync() {
        return read(attributes.get(ATTR_MANUFACTURERNAME));
    }


    /**
     * <p>
     * Synchronously get the <i>ManufacturerName</i> attribute [attribute ID <b>4</b>].
     * <p>
     * <br>
     * The ManufacturerName attribute is a maximum of 32 bytes in length and specifies
     * the name of the manufacturer as a ZigBee character string.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link String} attribute value, or null on error
     */
    public String getManufacturerName() {
        return (String) readSync(attributes.get(ATTR_MANUFACTURERNAME));
    }

    /**
     * <p>
     * Get the <i>ModelIdentifier</i> attribute [attribute ID <b>5</b>].
     * <p>
     * <br>
     * The ModelIdentifier attribute is a maximum of 32 bytes in length and specifies the
     * model number (or other identifier) assigned by the manufacturer as a ZigBee character string.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getModelIdentifierAsync() {
        return read(attributes.get(ATTR_MODELIDENTIFIER));
    }


    /**
     * <p>
     * Synchronously get the <i>ModelIdentifier</i> attribute [attribute ID <b>5</b>].
     * <p>
     * <br>
     * The ModelIdentifier attribute is a maximum of 32 bytes in length and specifies the
     * model number (or other identifier) assigned by the manufacturer as a ZigBee character string.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link String} attribute value, or null on error
     */
    public String getModelIdentifier() {
        return (String) readSync(attributes.get(ATTR_MODELIDENTIFIER));
    }

    /**
     * <p>
     * Get the <i>DateCode</i> attribute [attribute ID <b>6</b>].
     * <p>
     * <br>
     * The DateCode attribute is a ZigBee character string with a maximum length of 16 bytes.
     * The first 8 characters specify the date of manufacturer of the device in international
     * date notation according to ISO 8601, i.e. YYYYMMDD, e.g. 20060814.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDateCodeAsync() {
        return read(attributes.get(ATTR_DATECODE));
    }


    /**
     * <p>
     * Synchronously get the <i>DateCode</i> attribute [attribute ID <b>6</b>].
     * <p>
     * <br>
     * The DateCode attribute is a ZigBee character string with a maximum length of 16 bytes.
     * The first 8 characters specify the date of manufacturer of the device in international
     * date notation according to ISO 8601, i.e. YYYYMMDD, e.g. 20060814.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link String} attribute value, or null on error
     */
    public String getDateCode() {
        return (String) readSync(attributes.get(ATTR_DATECODE));
    }

    /**
     * <p>
     * Get the <i>PowerSource</i> attribute [attribute ID <b>7</b>].
     * <p>
     * <br>
     * The PowerSource attribute is 8 bits in length and specifies the source(s) of power
     * available to the device. Bits b0–b6 of this attribute represent the primary power
     * source of the device and bit b7 indicates whether the device has a secondary power
     * source in the form of a battery backup.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPowerSourceAsync() {
        return read(attributes.get(ATTR_POWERSOURCE));
    }


    /**
     * <p>
     * Synchronously get the <i>PowerSource</i> attribute [attribute ID <b>7</b>].
     * <p>
     * <br>
     * The PowerSource attribute is 8 bits in length and specifies the source(s) of power
     * available to the device. Bits b0–b6 of this attribute represent the primary power
     * source of the device and bit b7 indicates whether the device has a secondary power
     * source in the form of a battery backup.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getPowerSource() {
        return (Integer) readSync(attributes.get(ATTR_POWERSOURCE));
    }


    /**
     * <p>
     * Set the <i>LocationDescription</i> attribute [attribute ID <b>16</b>].
     * <p>
     * <br>
     * The LocationDescription attribute is a maximum of 16 bytes in length and describes
     * the physical location of the device as a ZigBee character string.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param locationDescription the {@link String} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setLocationDescription(final Object value) {
        return write(attributes.get(ATTR_LOCATIONDESCRIPTION), value);
    }

    /**
     * <p>
     * Get the <i>LocationDescription</i> attribute [attribute ID <b>16</b>].
     * <p>
     * <br>
     * The LocationDescription attribute is a maximum of 16 bytes in length and describes
     * the physical location of the device as a ZigBee character string.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLocationDescriptionAsync() {
        return read(attributes.get(ATTR_LOCATIONDESCRIPTION));
    }


    /**
     * <p>
     * Synchronously get the <i>LocationDescription</i> attribute [attribute ID <b>16</b>].
     * <p>
     * <br>
     * The LocationDescription attribute is a maximum of 16 bytes in length and describes
     * the physical location of the device as a ZigBee character string.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link String} attribute value, or null on error
     */
    public String getLocationDescription() {
        return (String) readSync(attributes.get(ATTR_LOCATIONDESCRIPTION));
    }


    /**
     * <p>
     * Set the <i>PhysicalEnvironment</i> attribute [attribute ID <b>17</b>].
     * <p>
     * <br>
     * The PhysicalEnvironment attribute is 8 bits in length and specifies the type of
     * physical environment in which the device will operate.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param physicalEnvironment the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPhysicalEnvironment(final Object value) {
        return write(attributes.get(ATTR_PHYSICALENVIRONMENT), value);
    }

    /**
     * <p>
     * Get the <i>PhysicalEnvironment</i> attribute [attribute ID <b>17</b>].
     * <p>
     * <br>
     * The PhysicalEnvironment attribute is 8 bits in length and specifies the type of
     * physical environment in which the device will operate.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPhysicalEnvironmentAsync() {
        return read(attributes.get(ATTR_PHYSICALENVIRONMENT));
    }


    /**
     * <p>
     * Synchronously get the <i>PhysicalEnvironment</i> attribute [attribute ID <b>17</b>].
     * <p>
     * <br>
     * The PhysicalEnvironment attribute is 8 bits in length and specifies the type of
     * physical environment in which the device will operate.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getPhysicalEnvironment() {
        return (Integer) readSync(attributes.get(ATTR_PHYSICALENVIRONMENT));
    }


    /**
     * <p>
     * Set the <i>DeviceEnabled</i> attribute [attribute ID <b>18</b>].
     * <p>
     * <br>
     * The DeviceEnabled attribute is a boolean and specifies whether the device is enabled
     * or disabled.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param deviceEnabled the {@link Boolean} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDeviceEnabled(final Object value) {
        return write(attributes.get(ATTR_DEVICEENABLED), value);
    }

    /**
     * <p>
     * Get the <i>DeviceEnabled</i> attribute [attribute ID <b>18</b>].
     * <p>
     * <br>
     * The DeviceEnabled attribute is a boolean and specifies whether the device is enabled
     * or disabled.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDeviceEnabledAsync() {
        return read(attributes.get(ATTR_DEVICEENABLED));
    }


    /**
     * <p>
     * Synchronously get the <i>DeviceEnabled</i> attribute [attribute ID <b>18</b>].
     * <p>
     * <br>
     * The DeviceEnabled attribute is a boolean and specifies whether the device is enabled
     * or disabled.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Boolean} attribute value, or null on error
     */
    public Boolean getDeviceEnabled() {
        return (Boolean) readSync(attributes.get(ATTR_DEVICEENABLED));
    }


    /**
     * <p>
     * Set the <i>AlarmMask</i> attribute [attribute ID <b>19</b>].
     * <p>
     * <br>
     * The AlarmMask attribute is 8 bits in length and specifies which of a number of general
     * alarms may be generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param alarmMask the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setAlarmMask(final Object value) {
        return write(attributes.get(ATTR_ALARMMASK), value);
    }

    /**
     * <p>
     * Get the <i>AlarmMask</i> attribute [attribute ID <b>19</b>].
     * <p>
     * <br>
     * The AlarmMask attribute is 8 bits in length and specifies which of a number of general
     * alarms may be generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getAlarmMaskAsync() {
        return read(attributes.get(ATTR_ALARMMASK));
    }


    /**
     * <p>
     * Synchronously get the <i>AlarmMask</i> attribute [attribute ID <b>19</b>].
     * <p>
     * <br>
     * The AlarmMask attribute is 8 bits in length and specifies which of a number of general
     * alarms may be generated.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getAlarmMask() {
        return (Integer) readSync(attributes.get(ATTR_ALARMMASK));
    }


    /**
     * <p>
     * Set the <i>DisableLocalConfig</i> attribute [attribute ID <b>20</b>].
     * <p>
     * <br>
     * The DisableLocalConfig attribute allows a number of local device configuration
     * functions to be disabled.
     * <br>
     * The intention of this attribute is to allow disabling of any local configuration
     * user interface, for example to prevent reset or binding buttons being activated by
     * unauthorised persons in a public building.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param disableLocalConfig the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDisableLocalConfig(final Object value) {
        return write(attributes.get(ATTR_DISABLELOCALCONFIG), value);
    }

    /**
     * <p>
     * Get the <i>DisableLocalConfig</i> attribute [attribute ID <b>20</b>].
     * <p>
     * <br>
     * The DisableLocalConfig attribute allows a number of local device configuration
     * functions to be disabled.
     * <br>
     * The intention of this attribute is to allow disabling of any local configuration
     * user interface, for example to prevent reset or binding buttons being activated by
     * unauthorised persons in a public building.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDisableLocalConfigAsync() {
        return read(attributes.get(ATTR_DISABLELOCALCONFIG));
    }


    /**
     * <p>
     * Synchronously get the <i>DisableLocalConfig</i> attribute [attribute ID <b>20</b>].
     * <p>
     * <br>
     * The DisableLocalConfig attribute allows a number of local device configuration
     * functions to be disabled.
     * <br>
     * The intention of this attribute is to allow disabling of any local configuration
     * user interface, for example to prevent reset or binding buttons being activated by
     * unauthorised persons in a public building.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getDisableLocalConfig() {
        return (Integer) readSync(attributes.get(ATTR_DISABLELOCALCONFIG));
    }

    /**
     * The Reset to Factory Defaults Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> resetToFactoryDefaultsCommand() {
        ResetToFactoryDefaultsCommand command = new ResetToFactoryDefaultsCommand();

        return send(command);
    }

    /**
     * Add a binding for this cluster to the local node
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> bind() {
        return bind();
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // RESET_TO_FACTORY_DEFAULTS_COMMAND
                return new ResetToFactoryDefaultsCommand();
            default:
                return null;
        }
    }
}
