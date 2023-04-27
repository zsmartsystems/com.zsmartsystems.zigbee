/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Map;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Ballast Configuration</b> cluster implementation (<i>Cluster ID 0x0301</i>).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2022-05-28T21:15:34Z")
public class ZclBallastConfigurationCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0301;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Ballast Configuration";

    // Attribute constants
    /**
     * The PhysicalMinLevel attribute is 8 bits in length and specifies the minimum light
     * output95 the ballast can achieve. This attribute shall be specified in the range 0x01 to
     * 0xfe, and specifies the light output of the ballast according to the dimming light curve
     */
    public static final int ATTR_PHYSICALMINLEVEL = 0x0000;
    /**
     * The PhysicalMaxLevel attribute is 8 bits in length and specifies the maximum light
     * output96 the ballast can achieve according to the dimming light curve
     */
    public static final int ATTR_PHYSICALMAXLEVEL = 0x0001;
    /**
     * The BallastStatus attribute is 8 bits in length and specifies the activity status of the
     * ballast functions. The usage of the bits is specified in Table 5-31. Where a function is
     * active, the corresponding bit shall be set to 1. Where a function is not active, the
     * corresponding bit shall be set to 0.
     */
    public static final int ATTR_BALLASTSTATUS = 0x0002;
    /**
     * The MinLevel attribute is 8 bits in length and specifies the light output of the ballast
     * according to the dimming light curve when the Level Control Cluster’s CurrentLevel
     * attribute equals to 0x01 (1) (and the On/Off Clusters’s OnOff attribute equals to 0x01)
     * <p>
     * The value of this attribute shall be both greater than or equal to PhysicalMinLevel and
     * less than or equal to MaxLevel. If an attempt is made to set this attribute to a level where
     * these conditions are not met, a default response command shall be returned with status
     * code set to INVALID_VALUE, and the level shall not be set.
     */
    public static final int ATTR_MINLEVEL = 0x0010;
    /**
     * The MaxLevel attribute is 8 bits in length and specifies the light output of the ballast
     * according to the dimming light curve when the Level Control Cluster’s CurrentLevel
     * attribute equals to 0xfe (254) (and the On/Off Cluster’s OnOff attribute equals to
     * 0x01)
     * <p>
     * The value of this attribute shall be both less than or equal to PhysicalMaxLevel and
     * greater than or equal to MinLevel. If an attempt is made to set this attribute to a level
     * where these conditions are not met, a default response command shall be returned with
     * status code set to INVALID_VALUE, and the level shall not be set.
     */
    public static final int ATTR_MAXLEVEL = 0x0011;
    /**
     * Deprecated
     */
    public static final int ATTR_POWERONLEVEL = 0x0012;
    /**
     * Deprecated
     */
    public static final int ATTR_POWERONFADETIME = 0x0013;
    /**
     * The IntrinsicBallastFactor attribute is 8 bits in length and specifies as a percentage
     * the ballast factor of the ballast/lamp combination, prior to any adjustment.
     * <p>
     * A value of 0xff indicates in invalid value.
     */
    public static final int ATTR_INTRINSICBALLASTFACTOR = 0x0014;
    /**
     * The BallastFactorAdjustment attribute is 8 bits in length and specifies the
     * multiplication factor, as a percentage, to be applied to the configured light output of
     * the lamps. A typical usage of this mechanism is to compensate for reduction in
     * efficiency over the lifetime of a lamp.
     * <p>
     * The range for this attribute is manufacturer dependent. If an attempt is made to set this
     * attribute to a level that cannot be supported, a default response command shall be
     * returned with status code set to INVALID_VALUE, and the level shall not be set. The value
     * 0xff indicates that ballast factor scaling is not in use.
     */
    public static final int ATTR_BALLASTFACTORADJUSTMENT = 0x0015;
    /**
     * The LampQuantity attribute is 8 bits in length and specifies the number of lamps
     * connected to this ballast. (Note: this number does not take into account whether lamps
     * are actually in their sockets or not).
     */
    public static final int ATTR_LAMPQUANTITY = 0x0020;
    /**
     * The LampType attribute is a character string of up to 16 bytes in length. It specifies the
     * type of lamps (including their wattage) connected to the ballast.
     */
    public static final int ATTR_LAMPTYPE = 0x0030;
    /**
     * The LampManufacturer attribute is a character string of up to 16 bytes in length. It
     * specifies the name of the manufacturer of the currently connected lamps.
     */
    public static final int ATTR_LAMPMANUFACTURER = 0x0031;
    /**
     * The LampRatedHours attribute is 24 bits in length and specifies the number of hours of
     * use the lamps are rated for by the manufacturer.
     * <p>
     * A value of 0xffffff indicates an invalid or unknown time.
     */
    public static final int ATTR_LAMPRATEDHOURS = 0x0032;
    /**
     * The LampBurnHours attribute is 24 bits in length and specifies the length of time, in
     * hours, the currently connected lamps have been operated, cumulative since the last
     * re-lamping. Burn hours shall not be accumulated if the lamps are off.
     * <p>
     * This attribute should be reset to zero (e.g., remotely) when the lamp(s) are changed. If
     * partially used lamps are connected, LampBurnHours should be updated to reflect the
     * burn hours of the lamps.
     * <p>
     * A value of 0xffffff indicates an invalid or unknown time.
     */
    public static final int ATTR_LAMPBURNHOURS = 0x0033;
    /**
     * The LampsAlarmMode attribute is 8 bits in length and specifies which attributes may
     * cause an alarm notification to be generated. A ‘1’ in each bit position causes its
     * associated attribute to be able to generate an alarm. (Note: All alarms are also logged
     * in the alarm table – see Alarms cluster).
     */
    public static final int ATTR_LAMPALARMMODE = 0x0034;
    /**
     * The LampBurnHoursTripPoint attribute is 24 bits in length and specifies the number of
     * hours the LampBurnHours attribute may reach before an alarm is generated.
     * <p>
     * If the Alarms cluster is not present on the same device this attribute is not used and thus
     * may be omitted
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x01.
     * <p>
     * If this attribute takes the value 0xffffff then this alarm shall not be generated.
     */
    public static final int ATTR_LAMPBURNHOURSTRIPPOINT = 0x0033;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeClientAttributes();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeServerAttributes();

        attributeMap.put(ATTR_PHYSICALMINLEVEL, new ZclAttribute(this, ATTR_PHYSICALMINLEVEL, "Physical Min Level", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_PHYSICALMAXLEVEL, new ZclAttribute(this, ATTR_PHYSICALMAXLEVEL, "Physical Max Level", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_BALLASTSTATUS, new ZclAttribute(this, ATTR_BALLASTSTATUS, "Ballast Status", ZclDataType.BITMAP_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_MINLEVEL, new ZclAttribute(this, ATTR_MINLEVEL, "Min Level", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_MAXLEVEL, new ZclAttribute(this, ATTR_MAXLEVEL, "Max Level", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_POWERONLEVEL, new ZclAttribute(this, ATTR_POWERONLEVEL, "Power On Level", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_POWERONFADETIME, new ZclAttribute(this, ATTR_POWERONFADETIME, "Power On Fade Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_INTRINSICBALLASTFACTOR, new ZclAttribute(this, ATTR_INTRINSICBALLASTFACTOR, "Intrinsic Ballast Factor", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_BALLASTFACTORADJUSTMENT, new ZclAttribute(this, ATTR_BALLASTFACTORADJUSTMENT, "Ballast Factor Adjustment", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_LAMPQUANTITY, new ZclAttribute(this, ATTR_LAMPQUANTITY, "Lamp Quantity", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_LAMPTYPE, new ZclAttribute(this, ATTR_LAMPTYPE, "Lamp Type", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_LAMPMANUFACTURER, new ZclAttribute(this, ATTR_LAMPMANUFACTURER, "Lamp Manufacturer", ZclDataType.CHARACTER_STRING, false, true, true, false));
        attributeMap.put(ATTR_LAMPRATEDHOURS, new ZclAttribute(this, ATTR_LAMPRATEDHOURS, "Lamp Rated Hours", ZclDataType.SIGNED_24_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_LAMPBURNHOURS, new ZclAttribute(this, ATTR_LAMPBURNHOURS, "Lamp Burn Hours", ZclDataType.SIGNED_24_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_LAMPALARMMODE, new ZclAttribute(this, ATTR_LAMPALARMMODE, "Lamp Alarm Mode", ZclDataType.BITMAP_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_LAMPBURNHOURSTRIPPOINT, new ZclAttribute(this, ATTR_LAMPBURNHOURSTRIPPOINT, "Lamp Burn Hours Trip Point", ZclDataType.SIGNED_24_BIT_INTEGER, false, true, true, false));

        return attributeMap;
    }


    /**
     * Default constructor to create a Ballast Configuration cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclBallastConfigurationCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Physical Min Level</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The PhysicalMinLevel attribute is 8 bits in length and specifies the minimum light
     * output95 the ballast can achieve. This attribute shall be specified in the range 0x01 to
     * 0xfe, and specifies the light output of the ballast according to the dimming light curve
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPhysicalMinLevelAsync() {
        return read(serverAttributes.get(ATTR_PHYSICALMINLEVEL));
    }

    /**
     * Synchronously get the <i>Physical Min Level</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The PhysicalMinLevel attribute is 8 bits in length and specifies the minimum light
     * output95 the ballast can achieve. This attribute shall be specified in the range 0x01 to
     * 0xfe, and specifies the light output of the ballast according to the dimming light curve
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getPhysicalMinLevel(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_PHYSICALMINLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_PHYSICALMINLEVEL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_PHYSICALMINLEVEL));
    }

    /**
     * Get the <i>Physical Max Level</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The PhysicalMaxLevel attribute is 8 bits in length and specifies the maximum light
     * output96 the ballast can achieve according to the dimming light curve
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPhysicalMaxLevelAsync() {
        return read(serverAttributes.get(ATTR_PHYSICALMAXLEVEL));
    }

    /**
     * Synchronously get the <i>Physical Max Level</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The PhysicalMaxLevel attribute is 8 bits in length and specifies the maximum light
     * output96 the ballast can achieve according to the dimming light curve
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getPhysicalMaxLevel(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_PHYSICALMAXLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_PHYSICALMAXLEVEL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_PHYSICALMAXLEVEL));
    }

    /**
     * Get the <i>Ballast Status</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The BallastStatus attribute is 8 bits in length and specifies the activity status of the
     * ballast functions. The usage of the bits is specified in Table 5-31. Where a function is
     * active, the corresponding bit shall be set to 1. Where a function is not active, the
     * corresponding bit shall be set to 0.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getBallastStatusAsync() {
        return read(serverAttributes.get(ATTR_BALLASTSTATUS));
    }

    /**
     * Synchronously get the <i>Ballast Status</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The BallastStatus attribute is 8 bits in length and specifies the activity status of the
     * ballast functions. The usage of the bits is specified in Table 5-31. Where a function is
     * active, the corresponding bit shall be set to 1. Where a function is not active, the
     * corresponding bit shall be set to 0.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getBallastStatus(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_BALLASTSTATUS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_BALLASTSTATUS).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_BALLASTSTATUS));
    }

    /**
     * Set reporting for the <i>Ballast Status</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The BallastStatus attribute is 8 bits in length and specifies the activity status of the
     * ballast functions. The usage of the bits is specified in Table 5-31. Where a function is
     * active, the corresponding bit shall be set to 1. Where a function is not active, the
     * corresponding bit shall be set to 0.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setBallastStatusReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_BALLASTSTATUS), minInterval, maxInterval);
    }

    /**
     * Set the <i>Min Level</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The MinLevel attribute is 8 bits in length and specifies the light output of the ballast
     * according to the dimming light curve when the Level Control Cluster’s CurrentLevel
     * attribute equals to 0x01 (1) (and the On/Off Clusters’s OnOff attribute equals to 0x01)
     * <p>
     * The value of this attribute shall be both greater than or equal to PhysicalMinLevel and
     * less than or equal to MaxLevel. If an attempt is made to set this attribute to a level where
     * these conditions are not met, a default response command shall be returned with status
     * code set to INVALID_VALUE, and the level shall not be set.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param minLevel the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setMinLevel(final Integer value) {
        return write(serverAttributes.get(ATTR_MINLEVEL), value);
    }

    /**
     * Get the <i>Min Level</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The MinLevel attribute is 8 bits in length and specifies the light output of the ballast
     * according to the dimming light curve when the Level Control Cluster’s CurrentLevel
     * attribute equals to 0x01 (1) (and the On/Off Clusters’s OnOff attribute equals to 0x01)
     * <p>
     * The value of this attribute shall be both greater than or equal to PhysicalMinLevel and
     * less than or equal to MaxLevel. If an attempt is made to set this attribute to a level where
     * these conditions are not met, a default response command shall be returned with status
     * code set to INVALID_VALUE, and the level shall not be set.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMinLevelAsync() {
        return read(serverAttributes.get(ATTR_MINLEVEL));
    }

    /**
     * Synchronously get the <i>Min Level</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The MinLevel attribute is 8 bits in length and specifies the light output of the ballast
     * according to the dimming light curve when the Level Control Cluster’s CurrentLevel
     * attribute equals to 0x01 (1) (and the On/Off Clusters’s OnOff attribute equals to 0x01)
     * <p>
     * The value of this attribute shall be both greater than or equal to PhysicalMinLevel and
     * less than or equal to MaxLevel. If an attempt is made to set this attribute to a level where
     * these conditions are not met, a default response command shall be returned with status
     * code set to INVALID_VALUE, and the level shall not be set.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getMinLevel(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MINLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MINLEVEL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MINLEVEL));
    }

    /**
     * Set the <i>Max Level</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The MaxLevel attribute is 8 bits in length and specifies the light output of the ballast
     * according to the dimming light curve when the Level Control Cluster’s CurrentLevel
     * attribute equals to 0xfe (254) (and the On/Off Cluster’s OnOff attribute equals to
     * 0x01)
     * <p>
     * The value of this attribute shall be both less than or equal to PhysicalMaxLevel and
     * greater than or equal to MinLevel. If an attempt is made to set this attribute to a level
     * where these conditions are not met, a default response command shall be returned with
     * status code set to INVALID_VALUE, and the level shall not be set.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param maxLevel the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setMaxLevel(final Integer value) {
        return write(serverAttributes.get(ATTR_MAXLEVEL), value);
    }

    /**
     * Get the <i>Max Level</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The MaxLevel attribute is 8 bits in length and specifies the light output of the ballast
     * according to the dimming light curve when the Level Control Cluster’s CurrentLevel
     * attribute equals to 0xfe (254) (and the On/Off Cluster’s OnOff attribute equals to
     * 0x01)
     * <p>
     * The value of this attribute shall be both less than or equal to PhysicalMaxLevel and
     * greater than or equal to MinLevel. If an attempt is made to set this attribute to a level
     * where these conditions are not met, a default response command shall be returned with
     * status code set to INVALID_VALUE, and the level shall not be set.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMaxLevelAsync() {
        return read(serverAttributes.get(ATTR_MAXLEVEL));
    }

    /**
     * Synchronously get the <i>Max Level</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The MaxLevel attribute is 8 bits in length and specifies the light output of the ballast
     * according to the dimming light curve when the Level Control Cluster’s CurrentLevel
     * attribute equals to 0xfe (254) (and the On/Off Cluster’s OnOff attribute equals to
     * 0x01)
     * <p>
     * The value of this attribute shall be both less than or equal to PhysicalMaxLevel and
     * greater than or equal to MinLevel. If an attempt is made to set this attribute to a level
     * where these conditions are not met, a default response command shall be returned with
     * status code set to INVALID_VALUE, and the level shall not be set.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getMaxLevel(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MAXLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MAXLEVEL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MAXLEVEL));
    }

    /**
     * Set the <i>Power On Level</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * Deprecated
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param powerOnLevel the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setPowerOnLevel(final Integer value) {
        return write(serverAttributes.get(ATTR_POWERONLEVEL), value);
    }

    /**
     * Get the <i>Power On Level</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * Deprecated
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPowerOnLevelAsync() {
        return read(serverAttributes.get(ATTR_POWERONLEVEL));
    }

    /**
     * Synchronously get the <i>Power On Level</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * Deprecated
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getPowerOnLevel(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_POWERONLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_POWERONLEVEL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_POWERONLEVEL));
    }

    /**
     * Set the <i>Power On Fade Time</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * Deprecated
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param powerOnFadeTime the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setPowerOnFadeTime(final Integer value) {
        return write(serverAttributes.get(ATTR_POWERONFADETIME), value);
    }

    /**
     * Get the <i>Power On Fade Time</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * Deprecated
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPowerOnFadeTimeAsync() {
        return read(serverAttributes.get(ATTR_POWERONFADETIME));
    }

    /**
     * Synchronously get the <i>Power On Fade Time</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * Deprecated
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getPowerOnFadeTime(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_POWERONFADETIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_POWERONFADETIME).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_POWERONFADETIME));
    }

    /**
     * Set the <i>Intrinsic Ballast Factor</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The IntrinsicBallastFactor attribute is 8 bits in length and specifies as a percentage
     * the ballast factor of the ballast/lamp combination, prior to any adjustment.
     * <p>
     * A value of 0xff indicates in invalid value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param intrinsicBallastFactor the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setIntrinsicBallastFactor(final Integer value) {
        return write(serverAttributes.get(ATTR_INTRINSICBALLASTFACTOR), value);
    }

    /**
     * Get the <i>Intrinsic Ballast Factor</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The IntrinsicBallastFactor attribute is 8 bits in length and specifies as a percentage
     * the ballast factor of the ballast/lamp combination, prior to any adjustment.
     * <p>
     * A value of 0xff indicates in invalid value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getIntrinsicBallastFactorAsync() {
        return read(serverAttributes.get(ATTR_INTRINSICBALLASTFACTOR));
    }

    /**
     * Synchronously get the <i>Intrinsic Ballast Factor</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The IntrinsicBallastFactor attribute is 8 bits in length and specifies as a percentage
     * the ballast factor of the ballast/lamp combination, prior to any adjustment.
     * <p>
     * A value of 0xff indicates in invalid value.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getIntrinsicBallastFactor(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_INTRINSICBALLASTFACTOR).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_INTRINSICBALLASTFACTOR).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_INTRINSICBALLASTFACTOR));
    }

    /**
     * Set the <i>Ballast Factor Adjustment</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The BallastFactorAdjustment attribute is 8 bits in length and specifies the
     * multiplication factor, as a percentage, to be applied to the configured light output of
     * the lamps. A typical usage of this mechanism is to compensate for reduction in
     * efficiency over the lifetime of a lamp.
     * <p>
     * The range for this attribute is manufacturer dependent. If an attempt is made to set this
     * attribute to a level that cannot be supported, a default response command shall be
     * returned with status code set to INVALID_VALUE, and the level shall not be set. The value
     * 0xff indicates that ballast factor scaling is not in use.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param ballastFactorAdjustment the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setBallastFactorAdjustment(final Integer value) {
        return write(serverAttributes.get(ATTR_BALLASTFACTORADJUSTMENT), value);
    }

    /**
     * Get the <i>Ballast Factor Adjustment</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The BallastFactorAdjustment attribute is 8 bits in length and specifies the
     * multiplication factor, as a percentage, to be applied to the configured light output of
     * the lamps. A typical usage of this mechanism is to compensate for reduction in
     * efficiency over the lifetime of a lamp.
     * <p>
     * The range for this attribute is manufacturer dependent. If an attempt is made to set this
     * attribute to a level that cannot be supported, a default response command shall be
     * returned with status code set to INVALID_VALUE, and the level shall not be set. The value
     * 0xff indicates that ballast factor scaling is not in use.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getBallastFactorAdjustmentAsync() {
        return read(serverAttributes.get(ATTR_BALLASTFACTORADJUSTMENT));
    }

    /**
     * Synchronously get the <i>Ballast Factor Adjustment</i> attribute [attribute ID <b>0x0015</b>].
     * <p>
     * The BallastFactorAdjustment attribute is 8 bits in length and specifies the
     * multiplication factor, as a percentage, to be applied to the configured light output of
     * the lamps. A typical usage of this mechanism is to compensate for reduction in
     * efficiency over the lifetime of a lamp.
     * <p>
     * The range for this attribute is manufacturer dependent. If an attempt is made to set this
     * attribute to a level that cannot be supported, a default response command shall be
     * returned with status code set to INVALID_VALUE, and the level shall not be set. The value
     * 0xff indicates that ballast factor scaling is not in use.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getBallastFactorAdjustment(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_BALLASTFACTORADJUSTMENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_BALLASTFACTORADJUSTMENT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_BALLASTFACTORADJUSTMENT));
    }

    /**
     * Set the <i>Lamp Quantity</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The LampQuantity attribute is 8 bits in length and specifies the number of lamps
     * connected to this ballast. (Note: this number does not take into account whether lamps
     * are actually in their sockets or not).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param lampQuantity the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setLampQuantity(final Integer value) {
        return write(serverAttributes.get(ATTR_LAMPQUANTITY), value);
    }

    /**
     * Get the <i>Lamp Quantity</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The LampQuantity attribute is 8 bits in length and specifies the number of lamps
     * connected to this ballast. (Note: this number does not take into account whether lamps
     * are actually in their sockets or not).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLampQuantityAsync() {
        return read(serverAttributes.get(ATTR_LAMPQUANTITY));
    }

    /**
     * Synchronously get the <i>Lamp Quantity</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The LampQuantity attribute is 8 bits in length and specifies the number of lamps
     * connected to this ballast. (Note: this number does not take into account whether lamps
     * are actually in their sockets or not).
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getLampQuantity(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LAMPQUANTITY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LAMPQUANTITY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LAMPQUANTITY));
    }

    /**
     * Set the <i>Lamp Type</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * The LampType attribute is a character string of up to 16 bytes in length. It specifies the
     * type of lamps (including their wattage) connected to the ballast.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param lampType the {@link String} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setLampType(final String value) {
        return write(serverAttributes.get(ATTR_LAMPTYPE), value);
    }

    /**
     * Get the <i>Lamp Type</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * The LampType attribute is a character string of up to 16 bytes in length. It specifies the
     * type of lamps (including their wattage) connected to the ballast.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLampTypeAsync() {
        return read(serverAttributes.get(ATTR_LAMPTYPE));
    }

    /**
     * Synchronously get the <i>Lamp Type</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * The LampType attribute is a character string of up to 16 bytes in length. It specifies the
     * type of lamps (including their wattage) connected to the ballast.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public String getLampType(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LAMPTYPE).isLastValueCurrent(refreshPeriod)) {
            return (String) serverAttributes.get(ATTR_LAMPTYPE).getLastValue();
        }

        return (String) readSync(serverAttributes.get(ATTR_LAMPTYPE));
    }

    /**
     * Set the <i>Lamp Manufacturer</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * The LampManufacturer attribute is a character string of up to 16 bytes in length. It
     * specifies the name of the manufacturer of the currently connected lamps.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param lampManufacturer the {@link String} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setLampManufacturer(final String value) {
        return write(serverAttributes.get(ATTR_LAMPMANUFACTURER), value);
    }

    /**
     * Get the <i>Lamp Manufacturer</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * The LampManufacturer attribute is a character string of up to 16 bytes in length. It
     * specifies the name of the manufacturer of the currently connected lamps.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLampManufacturerAsync() {
        return read(serverAttributes.get(ATTR_LAMPMANUFACTURER));
    }

    /**
     * Synchronously get the <i>Lamp Manufacturer</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * The LampManufacturer attribute is a character string of up to 16 bytes in length. It
     * specifies the name of the manufacturer of the currently connected lamps.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public String getLampManufacturer(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LAMPMANUFACTURER).isLastValueCurrent(refreshPeriod)) {
            return (String) serverAttributes.get(ATTR_LAMPMANUFACTURER).getLastValue();
        }

        return (String) readSync(serverAttributes.get(ATTR_LAMPMANUFACTURER));
    }

    /**
     * Set the <i>Lamp Rated Hours</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * The LampRatedHours attribute is 24 bits in length and specifies the number of hours of
     * use the lamps are rated for by the manufacturer.
     * <p>
     * A value of 0xffffff indicates an invalid or unknown time.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param lampRatedHours the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setLampRatedHours(final Integer value) {
        return write(serverAttributes.get(ATTR_LAMPRATEDHOURS), value);
    }

    /**
     * Get the <i>Lamp Rated Hours</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * The LampRatedHours attribute is 24 bits in length and specifies the number of hours of
     * use the lamps are rated for by the manufacturer.
     * <p>
     * A value of 0xffffff indicates an invalid or unknown time.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLampRatedHoursAsync() {
        return read(serverAttributes.get(ATTR_LAMPRATEDHOURS));
    }

    /**
     * Synchronously get the <i>Lamp Rated Hours</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * The LampRatedHours attribute is 24 bits in length and specifies the number of hours of
     * use the lamps are rated for by the manufacturer.
     * <p>
     * A value of 0xffffff indicates an invalid or unknown time.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getLampRatedHours(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LAMPRATEDHOURS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LAMPRATEDHOURS).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LAMPRATEDHOURS));
    }

    /**
     * Set the <i>Lamp Burn Hours</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * The LampBurnHours attribute is 24 bits in length and specifies the length of time, in
     * hours, the currently connected lamps have been operated, cumulative since the last
     * re-lamping. Burn hours shall not be accumulated if the lamps are off.
     * <p>
     * This attribute should be reset to zero (e.g., remotely) when the lamp(s) are changed. If
     * partially used lamps are connected, LampBurnHours should be updated to reflect the
     * burn hours of the lamps.
     * <p>
     * A value of 0xffffff indicates an invalid or unknown time.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param lampBurnHours the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setLampBurnHours(final Integer value) {
        return write(serverAttributes.get(ATTR_LAMPBURNHOURS), value);
    }

    /**
     * Get the <i>Lamp Burn Hours</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * The LampBurnHours attribute is 24 bits in length and specifies the length of time, in
     * hours, the currently connected lamps have been operated, cumulative since the last
     * re-lamping. Burn hours shall not be accumulated if the lamps are off.
     * <p>
     * This attribute should be reset to zero (e.g., remotely) when the lamp(s) are changed. If
     * partially used lamps are connected, LampBurnHours should be updated to reflect the
     * burn hours of the lamps.
     * <p>
     * A value of 0xffffff indicates an invalid or unknown time.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLampBurnHoursAsync() {
        return read(serverAttributes.get(ATTR_LAMPBURNHOURS));
    }

    /**
     * Synchronously get the <i>Lamp Burn Hours</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * The LampBurnHours attribute is 24 bits in length and specifies the length of time, in
     * hours, the currently connected lamps have been operated, cumulative since the last
     * re-lamping. Burn hours shall not be accumulated if the lamps are off.
     * <p>
     * This attribute should be reset to zero (e.g., remotely) when the lamp(s) are changed. If
     * partially used lamps are connected, LampBurnHours should be updated to reflect the
     * burn hours of the lamps.
     * <p>
     * A value of 0xffffff indicates an invalid or unknown time.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getLampBurnHours(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LAMPBURNHOURS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LAMPBURNHOURS).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LAMPBURNHOURS));
    }

    /**
     * Set the <i>Lamp Alarm Mode</i> attribute [attribute ID <b>0x0034</b>].
     * <p>
     * The LampsAlarmMode attribute is 8 bits in length and specifies which attributes may
     * cause an alarm notification to be generated. A ‘1’ in each bit position causes its
     * associated attribute to be able to generate an alarm. (Note: All alarms are also logged
     * in the alarm table – see Alarms cluster).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param lampAlarmMode the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setLampAlarmMode(final Integer value) {
        return write(serverAttributes.get(ATTR_LAMPALARMMODE), value);
    }

    /**
     * Get the <i>Lamp Alarm Mode</i> attribute [attribute ID <b>0x0034</b>].
     * <p>
     * The LampsAlarmMode attribute is 8 bits in length and specifies which attributes may
     * cause an alarm notification to be generated. A ‘1’ in each bit position causes its
     * associated attribute to be able to generate an alarm. (Note: All alarms are also logged
     * in the alarm table – see Alarms cluster).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLampAlarmModeAsync() {
        return read(serverAttributes.get(ATTR_LAMPALARMMODE));
    }

    /**
     * Synchronously get the <i>Lamp Alarm Mode</i> attribute [attribute ID <b>0x0034</b>].
     * <p>
     * The LampsAlarmMode attribute is 8 bits in length and specifies which attributes may
     * cause an alarm notification to be generated. A ‘1’ in each bit position causes its
     * associated attribute to be able to generate an alarm. (Note: All alarms are also logged
     * in the alarm table – see Alarms cluster).
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getLampAlarmMode(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LAMPALARMMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LAMPALARMMODE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LAMPALARMMODE));
    }

    /**
     * Set the <i>Lamp Burn Hours Trip Point</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * The LampBurnHoursTripPoint attribute is 24 bits in length and specifies the number of
     * hours the LampBurnHours attribute may reach before an alarm is generated.
     * <p>
     * If the Alarms cluster is not present on the same device this attribute is not used and thus
     * may be omitted
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x01.
     * <p>
     * If this attribute takes the value 0xffffff then this alarm shall not be generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param lampBurnHoursTripPoint the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setLampBurnHoursTripPoint(final Integer value) {
        return write(serverAttributes.get(ATTR_LAMPBURNHOURSTRIPPOINT), value);
    }

    /**
     * Get the <i>Lamp Burn Hours Trip Point</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * The LampBurnHoursTripPoint attribute is 24 bits in length and specifies the number of
     * hours the LampBurnHours attribute may reach before an alarm is generated.
     * <p>
     * If the Alarms cluster is not present on the same device this attribute is not used and thus
     * may be omitted
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x01.
     * <p>
     * If this attribute takes the value 0xffffff then this alarm shall not be generated.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLampBurnHoursTripPointAsync() {
        return read(serverAttributes.get(ATTR_LAMPBURNHOURSTRIPPOINT));
    }

    /**
     * Synchronously get the <i>Lamp Burn Hours Trip Point</i> attribute [attribute ID <b>0x0033</b>].
     * <p>
     * The LampBurnHoursTripPoint attribute is 24 bits in length and specifies the number of
     * hours the LampBurnHours attribute may reach before an alarm is generated.
     * <p>
     * If the Alarms cluster is not present on the same device this attribute is not used and thus
     * may be omitted
     * <p>
     * The Alarm Code field included in the generated alarm shall be 0x01.
     * <p>
     * If this attribute takes the value 0xffffff then this alarm shall not be generated.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getLampBurnHoursTripPoint(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LAMPBURNHOURSTRIPPOINT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LAMPBURNHOURSTRIPPOINT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LAMPBURNHOURSTRIPPOINT));
    }
}
