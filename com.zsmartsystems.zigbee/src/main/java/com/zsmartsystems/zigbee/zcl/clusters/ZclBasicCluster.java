/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.basic.ResetToFactoryDefaultsCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * <b>Basic</b> cluster implementation (<i>Cluster ID 0x0000</i>).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclBasicCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0000;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Basic";

    // Attribute constants
    /**
     * The ZCLVersion attribute is 8 bits in length and specifies the version number of
     * the ZigBee Cluster Library that all clusters on this endpoint conform to.
     */
    public static final int ATTR_ZCLVERSION = 0x0000;
    /**
     * The ApplicationVersion attribute is 8 bits in length and specifies the version
     * number of the application software contained in the device. The usage of this
     * attribute is manufacturer dependent.
     */
    public static final int ATTR_APPLICATIONVERSION = 0x0001;
    /**
     * The StackVersion attribute is 8 bits in length and specifies the version number
     * of the implementation of the ZigBee stack contained in the device. The usage of
     * this attribute is manufacturer dependent.
     */
    public static final int ATTR_STACKVERSION = 0x0002;
    /**
     * The HWVersion attribute is 8 bits in length and specifies the version number of
     * the hardware of the device. The usage of this attribute is manufacturer dependent.
     */
    public static final int ATTR_HWVERSION = 0x0003;
    /**
     * The ManufacturerName attribute is a maximum of 32 bytes in length and specifies
     * the name of the manufacturer as a ZigBee character string.
     */
    public static final int ATTR_MANUFACTURERNAME = 0x0004;
    /**
     * The ModelIdentifier attribute is a maximum of 32 bytes in length and specifies the
     * model number (or other identifier) assigned by the manufacturer as a ZigBee character string.
     */
    public static final int ATTR_MODELIDENTIFIER = 0x0005;
    /**
     * The DateCode attribute is a ZigBee character string with a maximum length of 16 bytes.
     * The first 8 characters specify the date of manufacturer of the device in international
     * date notation according to ISO 8601, i.e. YYYYMMDD, e.g. 20060814.
     */
    public static final int ATTR_DATECODE = 0x0006;
    /**
     * The PowerSource attribute is 8 bits in length and specifies the source(s) of power
     * available to the device. Bits b0–b6 of this attribute represent the primary power
     * source of the device and bit b7 indicates whether the device has a secondary power
     * source in the form of a battery backup.
     */
    public static final int ATTR_POWERSOURCE = 0x0007;
    /**
     * The LocationDescription attribute is a maximum of 16 bytes in length and describes
     * the physical location of the device as a ZigBee character string.
     */
    public static final int ATTR_LOCATIONDESCRIPTION = 0x0010;
    /**
     * The PhysicalEnvironment attribute is 8 bits in length and specifies the type of
     * physical environment in which the device will operate.
     */
    public static final int ATTR_PHYSICALENVIRONMENT = 0x0011;
    /**
     * The DeviceEnabled attribute is a boolean and specifies whether the device is enabled
     * or disabled.
     */
    public static final int ATTR_DEVICEENABLED = 0x0012;
    /**
     * The AlarmMask attribute is 8 bits in length and specifies which of a number of general
     * alarms may be generated.
     */
    public static final int ATTR_ALARMMASK = 0x0013;
    /**
     * The DisableLocalConfig attribute allows a number of local device configuration
     * functions to be disabled.
     * <p>
     * The intention of this attribute is to allow disabling of any local configuration
     * user interface, for example to prevent reset or binding buttons being activated by
     * unauthorised persons in a public building.
     */
    public static final int ATTR_DISABLELOCALCONFIG = 0x0014;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(13);

        attributeMap.put(ATTR_ZCLVERSION, new ZclAttribute(ZclClusterType.BASIC, ATTR_ZCLVERSION, "ZCLVersion", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APPLICATIONVERSION, new ZclAttribute(ZclClusterType.BASIC, ATTR_APPLICATIONVERSION, "ApplicationVersion", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_STACKVERSION, new ZclAttribute(ZclClusterType.BASIC, ATTR_STACKVERSION, "StackVersion", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_HWVERSION, new ZclAttribute(ZclClusterType.BASIC, ATTR_HWVERSION, "HWVersion", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MANUFACTURERNAME, new ZclAttribute(ZclClusterType.BASIC, ATTR_MANUFACTURERNAME, "ManufacturerName", ZclDataType.CHARACTER_STRING, true, true, false, false));
        attributeMap.put(ATTR_MODELIDENTIFIER, new ZclAttribute(ZclClusterType.BASIC, ATTR_MODELIDENTIFIER, "ModelIdentifier", ZclDataType.CHARACTER_STRING, true, true, false, false));
        attributeMap.put(ATTR_DATECODE, new ZclAttribute(ZclClusterType.BASIC, ATTR_DATECODE, "DateCode", ZclDataType.CHARACTER_STRING, true, true, false, false));
        attributeMap.put(ATTR_POWERSOURCE, new ZclAttribute(ZclClusterType.BASIC, ATTR_POWERSOURCE, "PowerSource", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_LOCATIONDESCRIPTION, new ZclAttribute(ZclClusterType.BASIC, ATTR_LOCATIONDESCRIPTION, "LocationDescription", ZclDataType.CHARACTER_STRING, true, true, true, false));
        attributeMap.put(ATTR_PHYSICALENVIRONMENT, new ZclAttribute(ZclClusterType.BASIC, ATTR_PHYSICALENVIRONMENT, "PhysicalEnvironment", ZclDataType.ENUMERATION_8_BIT, true, true, true, false));
        attributeMap.put(ATTR_DEVICEENABLED, new ZclAttribute(ZclClusterType.BASIC, ATTR_DEVICEENABLED, "DeviceEnabled", ZclDataType.BOOLEAN, true, true, true, false));
        attributeMap.put(ATTR_ALARMMASK, new ZclAttribute(ZclClusterType.BASIC, ATTR_ALARMMASK, "AlarmMask", ZclDataType.BITMAP_8_BIT, true, true, true, false));
        attributeMap.put(ATTR_DISABLELOCALCONFIG, new ZclAttribute(ZclClusterType.BASIC, ATTR_DISABLELOCALCONFIG, "DisableLocalConfig", ZclDataType.BITMAP_8_BIT, true, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Basic cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclBasicCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
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
     * Synchronously get the <i>ZCLVersion</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The ZCLVersion attribute is 8 bits in length and specifies the version number of
     * the ZigBee Cluster Library that all clusters on this endpoint conform to.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getZclVersion(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_ZCLVERSION).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ZCLVERSION).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ZCLVERSION).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ZCLVERSION));
    }

    /**
     * Get the <i>ApplicationVersion</i> attribute [attribute ID <b>1</b>].
     * <p>
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
     * Synchronously get the <i>ApplicationVersion</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The ApplicationVersion attribute is 8 bits in length and specifies the version
     * number of the application software contained in the device. The usage of this
     * attribute is manufacturer dependent.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getApplicationVersion(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_APPLICATIONVERSION).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_APPLICATIONVERSION).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_APPLICATIONVERSION).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_APPLICATIONVERSION));
    }

    /**
     * Get the <i>StackVersion</i> attribute [attribute ID <b>2</b>].
     * <p>
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
     * Synchronously get the <i>StackVersion</i> attribute [attribute ID <b>2</b>].
     * <p>
     * The StackVersion attribute is 8 bits in length and specifies the version number
     * of the implementation of the ZigBee stack contained in the device. The usage of
     * this attribute is manufacturer dependent.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getStackVersion(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_STACKVERSION).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_STACKVERSION).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_STACKVERSION).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_STACKVERSION));
    }

    /**
     * Get the <i>HWVersion</i> attribute [attribute ID <b>3</b>].
     * <p>
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
     * Synchronously get the <i>HWVersion</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The HWVersion attribute is 8 bits in length and specifies the version number of
     * the hardware of the device. The usage of this attribute is manufacturer dependent.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getHwVersion(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_HWVERSION).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_HWVERSION).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_HWVERSION).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_HWVERSION));
    }

    /**
     * Get the <i>ManufacturerName</i> attribute [attribute ID <b>4</b>].
     * <p>
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
     * Synchronously get the <i>ManufacturerName</i> attribute [attribute ID <b>4</b>].
     * <p>
     * The ManufacturerName attribute is a maximum of 32 bytes in length and specifies
     * the name of the manufacturer as a ZigBee character string.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     */
    public String getManufacturerName(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_MANUFACTURERNAME).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_MANUFACTURERNAME).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (String) attributes.get(ATTR_MANUFACTURERNAME).getLastValue();
            }
        }

        return (String) readSync(attributes.get(ATTR_MANUFACTURERNAME));
    }

    /**
     * Get the <i>ModelIdentifier</i> attribute [attribute ID <b>5</b>].
     * <p>
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
     * Synchronously get the <i>ModelIdentifier</i> attribute [attribute ID <b>5</b>].
     * <p>
     * The ModelIdentifier attribute is a maximum of 32 bytes in length and specifies the
     * model number (or other identifier) assigned by the manufacturer as a ZigBee character string.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     */
    public String getModelIdentifier(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_MODELIDENTIFIER).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_MODELIDENTIFIER).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (String) attributes.get(ATTR_MODELIDENTIFIER).getLastValue();
            }
        }

        return (String) readSync(attributes.get(ATTR_MODELIDENTIFIER));
    }

    /**
     * Get the <i>DateCode</i> attribute [attribute ID <b>6</b>].
     * <p>
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
     * Synchronously get the <i>DateCode</i> attribute [attribute ID <b>6</b>].
     * <p>
     * The DateCode attribute is a ZigBee character string with a maximum length of 16 bytes.
     * The first 8 characters specify the date of manufacturer of the device in international
     * date notation according to ISO 8601, i.e. YYYYMMDD, e.g. 20060814.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     */
    public String getDateCode(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_DATECODE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_DATECODE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (String) attributes.get(ATTR_DATECODE).getLastValue();
            }
        }

        return (String) readSync(attributes.get(ATTR_DATECODE));
    }

    /**
     * Get the <i>PowerSource</i> attribute [attribute ID <b>7</b>].
     * <p>
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
     * Synchronously get the <i>PowerSource</i> attribute [attribute ID <b>7</b>].
     * <p>
     * The PowerSource attribute is 8 bits in length and specifies the source(s) of power
     * available to the device. Bits b0–b6 of this attribute represent the primary power
     * source of the device and bit b7 indicates whether the device has a secondary power
     * source in the form of a battery backup.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getPowerSource(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_POWERSOURCE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_POWERSOURCE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_POWERSOURCE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_POWERSOURCE));
    }


    /**
     * Set the <i>LocationDescription</i> attribute [attribute ID <b>16</b>].
     * <p>
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
     * Get the <i>LocationDescription</i> attribute [attribute ID <b>16</b>].
     * <p>
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
     * Synchronously get the <i>LocationDescription</i> attribute [attribute ID <b>16</b>].
     * <p>
     * The LocationDescription attribute is a maximum of 16 bytes in length and describes
     * the physical location of the device as a ZigBee character string.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     */
    public String getLocationDescription(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_LOCATIONDESCRIPTION).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_LOCATIONDESCRIPTION).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (String) attributes.get(ATTR_LOCATIONDESCRIPTION).getLastValue();
            }
        }

        return (String) readSync(attributes.get(ATTR_LOCATIONDESCRIPTION));
    }


    /**
     * Set the <i>PhysicalEnvironment</i> attribute [attribute ID <b>17</b>].
     * <p>
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
     * Get the <i>PhysicalEnvironment</i> attribute [attribute ID <b>17</b>].
     * <p>
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
     * Synchronously get the <i>PhysicalEnvironment</i> attribute [attribute ID <b>17</b>].
     * <p>
     * The PhysicalEnvironment attribute is 8 bits in length and specifies the type of
     * physical environment in which the device will operate.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getPhysicalEnvironment(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_PHYSICALENVIRONMENT).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_PHYSICALENVIRONMENT).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_PHYSICALENVIRONMENT).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_PHYSICALENVIRONMENT));
    }


    /**
     * Set the <i>DeviceEnabled</i> attribute [attribute ID <b>18</b>].
     * <p>
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
     * Get the <i>DeviceEnabled</i> attribute [attribute ID <b>18</b>].
     * <p>
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
     * Synchronously get the <i>DeviceEnabled</i> attribute [attribute ID <b>18</b>].
     * <p>
     * The DeviceEnabled attribute is a boolean and specifies whether the device is enabled
     * or disabled.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Boolean} attribute value, or null on error
     */
    public Boolean getDeviceEnabled(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_DEVICEENABLED).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_DEVICEENABLED).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Boolean) attributes.get(ATTR_DEVICEENABLED).getLastValue();
            }
        }

        return (Boolean) readSync(attributes.get(ATTR_DEVICEENABLED));
    }


    /**
     * Set the <i>AlarmMask</i> attribute [attribute ID <b>19</b>].
     * <p>
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
     * Get the <i>AlarmMask</i> attribute [attribute ID <b>19</b>].
     * <p>
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
     * Synchronously get the <i>AlarmMask</i> attribute [attribute ID <b>19</b>].
     * <p>
     * The AlarmMask attribute is 8 bits in length and specifies which of a number of general
     * alarms may be generated.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getAlarmMask(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_ALARMMASK).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ALARMMASK).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ALARMMASK).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ALARMMASK));
    }


    /**
     * Set the <i>DisableLocalConfig</i> attribute [attribute ID <b>20</b>].
     * <p>
     * The DisableLocalConfig attribute allows a number of local device configuration
     * functions to be disabled.
     * <p>
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
     * Get the <i>DisableLocalConfig</i> attribute [attribute ID <b>20</b>].
     * <p>
     * The DisableLocalConfig attribute allows a number of local device configuration
     * functions to be disabled.
     * <p>
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
     * Synchronously get the <i>DisableLocalConfig</i> attribute [attribute ID <b>20</b>].
     * <p>
     * The DisableLocalConfig attribute allows a number of local device configuration
     * functions to be disabled.
     * <p>
     * The intention of this attribute is to allow disabling of any local configuration
     * user interface, for example to prevent reset or binding buttons being activated by
     * unauthorised persons in a public building.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getDisableLocalConfig(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_DISABLELOCALCONFIG).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_DISABLELOCALCONFIG).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_DISABLELOCALCONFIG).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_DISABLELOCALCONFIG));
    }

    /**
     * The Reset to Factory Defaults Command
     * <p>
     * On receipt of this command, the device resets all the attributes of all its clusters
     * to their factory defaults.Note that ZigBee networking functionality,bindings, groups
     * or other persistent data are not affected by this command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> resetToFactoryDefaultsCommand() {
        ResetToFactoryDefaultsCommand command = new ResetToFactoryDefaultsCommand();

        return send(command);
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
