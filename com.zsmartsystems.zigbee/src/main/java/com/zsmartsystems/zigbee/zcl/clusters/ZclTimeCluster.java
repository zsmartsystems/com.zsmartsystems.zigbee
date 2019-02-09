/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Time</b> cluster implementation (<i>Cluster ID 0x000A</i>).
 * <p>
 * This cluster provides a basic interface to a real-time clock. The clock time may be read and
 * also written, in order to synchronize the clock (as close as practical) to a time standard.
 * This time standard is the number of seconds since 0 hrs 0 mins 0 sec on 1st January 2000 UTC
 * (Universal Coordinated Time).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ZclTimeCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x000A;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Time";

    // Attribute constants
    /**
     * The Time attribute is 32-bits in length and holds the time value of a real time clock. This
     * attribute has data type UTCTime, but note that it may not actually be synchronised to UTC
     * - see discussion of the TimeStatus attribute below.
     * <p>
     * If the Master bit of the TimeStatus attribute has a value of 0, writing to this attribute
     * shall set the real time clock to the written value, otherwise it cannot be written. The
     * value 0xffffffff indicates an invalid time.
     */
    public static final int ATTR_TIME = 0x0000;
    /**
     * The TimeStatus attribute holds a number of bit fields.
     */
    public static final int ATTR_TIMESTATUS = 0x0001;
    /**
     * The TimeZone attribute indicates the local time zone, as a signed offset in seconds from
     * the Time attribute value. The value 0xffffffff indicates an invalid time zone.
     */
    public static final int ATTR_TIMEZONE = 0x0002;
    /**
     * The DstStart attribute indicates the DST start time in seconds. The value 0xffffffff
     * indicates an invalid DST start time.
     */
    public static final int ATTR_DSTSTART = 0x0003;
    /**
     * The DstEnd attribute indicates the DST end time in seconds. The value 0xffffffff
     * indicates an invalid DST end time.
     * <p>
     * Note that the three attributes DstStart, DstEnd and DstShift are optional, but if any
     * one of them is implemented the other two must also be implemented. Note that this
     * attribute should be set to a new value once every year.
     * <p>
     * Note that this attribute should be set to a new value once every year, and should be
     * written synchronously with the DstStart attribute. The DstEnd attribute indicates
     * the DST end time in seconds. The value 0xffffffff indicates an invalid DST end time.
     * <p>
     * Note that this attribute should be set to a new value once every year, and should be
     * written synchronously with the DstStart attribute
     */
    public static final int ATTR_DSTEND = 0x0004;
    /**
     * The DstShift attribute represents a signed offset in seconds from the standard time, to
     * be applied between the times DstStart and DstEnd to calculate the Local Time. The value
     * 0xffffffff indicates an invalid DST shift.
     * <p>
     * The range of this attribute is +/- one day. Note that the actual range of DST values
     * employed by countries is much smaller than this, so the manufacturer has the option to
     * impose a smaller range.
     */
    public static final int ATTR_DSTSHIFT = 0x0005;
    /**
     * A device may derive the time by reading the Time and TimeZone attributes and adding them
     * together. If implemented however, the optional StandardTime attribute indicates
     * this time directly. The value 0xffffffff indicates an invalid Standard Time.
     */
    public static final int ATTR_STANDARDTIME = 0x0006;
    /**
     * A device may derive the time by reading the Time, TimeZone, DstStart, DstEnd and
     * DstShift attributes and performing the calculation. If implemented however, the
     * optional LocalTime attribute indicates this time directly. The value 0xffffffff
     * indicates an invalid Local Time.
     */
    public static final int ATTR_LOCALTIME = 0x0007;
    /**
     * The LastSetTime attribute indicates the most recent time that the Time attribute was
     * set, either internally or over the ZigBee network (thus it holds a copy of the last value
     * that Time was set to). This attribute is set automatically, so is Read Only. The value
     * 0xffffffff indicates an invalid LastSetTime.
     */
    public static final int ATTR_LASTSETTIME = 0x0008;
    /**
     * The ValidUntilTime attribute indicates a time, later than LastSetTime, up to which the
     * Time attribute may be trusted. ‘Trusted’ means that the difference between the Time
     * attribute and the true UTC time is less than an acceptable error. The acceptable error is
     * not defined by this cluster specification, but may be defined by the application
     * profile in which devices that use this cluster are specified.
     */
    public static final int ATTR_VALIDUNTILTIME = 0x0009;

    // Attribute initialisation
    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(10);

        attributeMap.put(ATTR_TIME, new ZclAttribute(ZclClusterType.TIME, ATTR_TIME, "Time", ZclDataType.UTCTIME, true, true, true, false));
        attributeMap.put(ATTR_TIMESTATUS, new ZclAttribute(ZclClusterType.TIME, ATTR_TIMESTATUS, "Time Status", ZclDataType.BITMAP_8_BIT, false, true, true, false));
        attributeMap.put(ATTR_TIMEZONE, new ZclAttribute(ZclClusterType.TIME, ATTR_TIMEZONE, "Time Zone", ZclDataType.SIGNED_32_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_DSTSTART, new ZclAttribute(ZclClusterType.TIME, ATTR_DSTSTART, "DST Start", ZclDataType.UTCTIME, false, true, true, false));
        attributeMap.put(ATTR_DSTEND, new ZclAttribute(ZclClusterType.TIME, ATTR_DSTEND, "DST End", ZclDataType.UTCTIME, false, true, true, false));
        attributeMap.put(ATTR_DSTSHIFT, new ZclAttribute(ZclClusterType.TIME, ATTR_DSTSHIFT, "DST Shift", ZclDataType.SIGNED_32_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_STANDARDTIME, new ZclAttribute(ZclClusterType.TIME, ATTR_STANDARDTIME, "Standard Time", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_LOCALTIME, new ZclAttribute(ZclClusterType.TIME, ATTR_LOCALTIME, "Local Time", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_LASTSETTIME, new ZclAttribute(ZclClusterType.TIME, ATTR_LASTSETTIME, "Last Set Time", ZclDataType.UTCTIME, false, true, false, false));
        attributeMap.put(ATTR_VALIDUNTILTIME, new ZclAttribute(ZclClusterType.TIME, ATTR_VALIDUNTILTIME, "Valid Until Time", ZclDataType.UTCTIME, false, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Time cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclTimeCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Set the <i>Time</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The Time attribute is 32-bits in length and holds the time value of a real time clock. This
     * attribute has data type UTCTime, but note that it may not actually be synchronised to UTC
     * - see discussion of the TimeStatus attribute below.
     * <p>
     * If the Master bit of the TimeStatus attribute has a value of 0, writing to this attribute
     * shall set the real time clock to the written value, otherwise it cannot be written. The
     * value 0xffffffff indicates an invalid time.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param time the {@link Calendar} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTime(final Calendar value) {
        return write(attributes.get(ATTR_TIME), value);
    }

    /**
     * Get the <i>Time</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The Time attribute is 32-bits in length and holds the time value of a real time clock. This
     * attribute has data type UTCTime, but note that it may not actually be synchronised to UTC
     * - see discussion of the TimeStatus attribute below.
     * <p>
     * If the Master bit of the TimeStatus attribute has a value of 0, writing to this attribute
     * shall set the real time clock to the written value, otherwise it cannot be written. The
     * value 0xffffffff indicates an invalid time.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTimeAsync() {
        return read(attributes.get(ATTR_TIME));
    }

    /**
     * Synchronously get the <i>Time</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The Time attribute is 32-bits in length and holds the time value of a real time clock. This
     * attribute has data type UTCTime, but note that it may not actually be synchronised to UTC
     * - see discussion of the TimeStatus attribute below.
     * <p>
     * If the Master bit of the TimeStatus attribute has a value of 0, writing to this attribute
     * shall set the real time clock to the written value, otherwise it cannot be written. The
     * value 0xffffffff indicates an invalid time.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getTime(final long refreshPeriod) {
        if (attributes.get(ATTR_TIME).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_TIME).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_TIME));
    }

    /**
     * Set reporting for the <i>Time</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The Time attribute is 32-bits in length and holds the time value of a real time clock. This
     * attribute has data type UTCTime, but note that it may not actually be synchronised to UTC
     * - see discussion of the TimeStatus attribute below.
     * <p>
     * If the Master bit of the TimeStatus attribute has a value of 0, writing to this attribute
     * shall set the real time clock to the written value, otherwise it cannot be written. The
     * value 0xffffffff indicates an invalid time.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTimeReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_TIME), minInterval, maxInterval, reportableChange);
    }

    /**
     * Set the <i>Time Status</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The TimeStatus attribute holds a number of bit fields.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param timeStatus the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTimeStatus(final Integer value) {
        return write(attributes.get(ATTR_TIMESTATUS), value);
    }

    /**
     * Get the <i>Time Status</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The TimeStatus attribute holds a number of bit fields.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTimeStatusAsync() {
        return read(attributes.get(ATTR_TIMESTATUS));
    }

    /**
     * Synchronously get the <i>Time Status</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The TimeStatus attribute holds a number of bit fields.
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
     */
    public Integer getTimeStatus(final long refreshPeriod) {
        if (attributes.get(ATTR_TIMESTATUS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIMESTATUS).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIMESTATUS));
    }

    /**
     * Set the <i>Time Zone</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The TimeZone attribute indicates the local time zone, as a signed offset in seconds from
     * the Time attribute value. The value 0xffffffff indicates an invalid time zone.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param timeZone the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTimeZone(final Integer value) {
        return write(attributes.get(ATTR_TIMEZONE), value);
    }

    /**
     * Get the <i>Time Zone</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The TimeZone attribute indicates the local time zone, as a signed offset in seconds from
     * the Time attribute value. The value 0xffffffff indicates an invalid time zone.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getTimeZoneAsync() {
        return read(attributes.get(ATTR_TIMEZONE));
    }

    /**
     * Synchronously get the <i>Time Zone</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The TimeZone attribute indicates the local time zone, as a signed offset in seconds from
     * the Time attribute value. The value 0xffffffff indicates an invalid time zone.
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
     */
    public Integer getTimeZone(final long refreshPeriod) {
        if (attributes.get(ATTR_TIMEZONE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_TIMEZONE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_TIMEZONE));
    }

    /**
     * Set the <i>DST Start</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The DstStart attribute indicates the DST start time in seconds. The value 0xffffffff
     * indicates an invalid DST start time.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param dstStart the {@link Calendar} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDstStart(final Calendar value) {
        return write(attributes.get(ATTR_DSTSTART), value);
    }

    /**
     * Get the <i>DST Start</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The DstStart attribute indicates the DST start time in seconds. The value 0xffffffff
     * indicates an invalid DST start time.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDstStartAsync() {
        return read(attributes.get(ATTR_DSTSTART));
    }

    /**
     * Synchronously get the <i>DST Start</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The DstStart attribute indicates the DST start time in seconds. The value 0xffffffff
     * indicates an invalid DST start time.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getDstStart(final long refreshPeriod) {
        if (attributes.get(ATTR_DSTSTART).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_DSTSTART).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_DSTSTART));
    }

    /**
     * Set the <i>DST End</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The DstEnd attribute indicates the DST end time in seconds. The value 0xffffffff
     * indicates an invalid DST end time.
     * <p>
     * Note that the three attributes DstStart, DstEnd and DstShift are optional, but if any
     * one of them is implemented the other two must also be implemented. Note that this
     * attribute should be set to a new value once every year.
     * <p>
     * Note that this attribute should be set to a new value once every year, and should be
     * written synchronously with the DstStart attribute. The DstEnd attribute indicates
     * the DST end time in seconds. The value 0xffffffff indicates an invalid DST end time.
     * <p>
     * Note that this attribute should be set to a new value once every year, and should be
     * written synchronously with the DstStart attribute
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param dstEnd the {@link Calendar} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDstEnd(final Calendar value) {
        return write(attributes.get(ATTR_DSTEND), value);
    }

    /**
     * Get the <i>DST End</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The DstEnd attribute indicates the DST end time in seconds. The value 0xffffffff
     * indicates an invalid DST end time.
     * <p>
     * Note that the three attributes DstStart, DstEnd and DstShift are optional, but if any
     * one of them is implemented the other two must also be implemented. Note that this
     * attribute should be set to a new value once every year.
     * <p>
     * Note that this attribute should be set to a new value once every year, and should be
     * written synchronously with the DstStart attribute. The DstEnd attribute indicates
     * the DST end time in seconds. The value 0xffffffff indicates an invalid DST end time.
     * <p>
     * Note that this attribute should be set to a new value once every year, and should be
     * written synchronously with the DstStart attribute
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDstEndAsync() {
        return read(attributes.get(ATTR_DSTEND));
    }

    /**
     * Synchronously get the <i>DST End</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The DstEnd attribute indicates the DST end time in seconds. The value 0xffffffff
     * indicates an invalid DST end time.
     * <p>
     * Note that the three attributes DstStart, DstEnd and DstShift are optional, but if any
     * one of them is implemented the other two must also be implemented. Note that this
     * attribute should be set to a new value once every year.
     * <p>
     * Note that this attribute should be set to a new value once every year, and should be
     * written synchronously with the DstStart attribute. The DstEnd attribute indicates
     * the DST end time in seconds. The value 0xffffffff indicates an invalid DST end time.
     * <p>
     * Note that this attribute should be set to a new value once every year, and should be
     * written synchronously with the DstStart attribute
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getDstEnd(final long refreshPeriod) {
        if (attributes.get(ATTR_DSTEND).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_DSTEND).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_DSTEND));
    }

    /**
     * Set the <i>DST Shift</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The DstShift attribute represents a signed offset in seconds from the standard time, to
     * be applied between the times DstStart and DstEnd to calculate the Local Time. The value
     * 0xffffffff indicates an invalid DST shift.
     * <p>
     * The range of this attribute is +/- one day. Note that the actual range of DST values
     * employed by countries is much smaller than this, so the manufacturer has the option to
     * impose a smaller range.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param dstShift the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDstShift(final Integer value) {
        return write(attributes.get(ATTR_DSTSHIFT), value);
    }

    /**
     * Get the <i>DST Shift</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The DstShift attribute represents a signed offset in seconds from the standard time, to
     * be applied between the times DstStart and DstEnd to calculate the Local Time. The value
     * 0xffffffff indicates an invalid DST shift.
     * <p>
     * The range of this attribute is +/- one day. Note that the actual range of DST values
     * employed by countries is much smaller than this, so the manufacturer has the option to
     * impose a smaller range.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDstShiftAsync() {
        return read(attributes.get(ATTR_DSTSHIFT));
    }

    /**
     * Synchronously get the <i>DST Shift</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The DstShift attribute represents a signed offset in seconds from the standard time, to
     * be applied between the times DstStart and DstEnd to calculate the Local Time. The value
     * 0xffffffff indicates an invalid DST shift.
     * <p>
     * The range of this attribute is +/- one day. Note that the actual range of DST values
     * employed by countries is much smaller than this, so the manufacturer has the option to
     * impose a smaller range.
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
     */
    public Integer getDstShift(final long refreshPeriod) {
        if (attributes.get(ATTR_DSTSHIFT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DSTSHIFT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DSTSHIFT));
    }

    /**
     * Get the <i>Standard Time</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * A device may derive the time by reading the Time and TimeZone attributes and adding them
     * together. If implemented however, the optional StandardTime attribute indicates
     * this time directly. The value 0xffffffff indicates an invalid Standard Time.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getStandardTimeAsync() {
        return read(attributes.get(ATTR_STANDARDTIME));
    }

    /**
     * Synchronously get the <i>Standard Time</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * A device may derive the time by reading the Time and TimeZone attributes and adding them
     * together. If implemented however, the optional StandardTime attribute indicates
     * this time directly. The value 0xffffffff indicates an invalid Standard Time.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getStandardTime(final long refreshPeriod) {
        if (attributes.get(ATTR_STANDARDTIME).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_STANDARDTIME).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_STANDARDTIME));
    }

    /**
     * Get the <i>Local Time</i> attribute [attribute ID <b>0x0007</b>].
     * <p>
     * A device may derive the time by reading the Time, TimeZone, DstStart, DstEnd and
     * DstShift attributes and performing the calculation. If implemented however, the
     * optional LocalTime attribute indicates this time directly. The value 0xffffffff
     * indicates an invalid Local Time.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLocalTimeAsync() {
        return read(attributes.get(ATTR_LOCALTIME));
    }

    /**
     * Synchronously get the <i>Local Time</i> attribute [attribute ID <b>0x0007</b>].
     * <p>
     * A device may derive the time by reading the Time, TimeZone, DstStart, DstEnd and
     * DstShift attributes and performing the calculation. If implemented however, the
     * optional LocalTime attribute indicates this time directly. The value 0xffffffff
     * indicates an invalid Local Time.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getLocalTime(final long refreshPeriod) {
        if (attributes.get(ATTR_LOCALTIME).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_LOCALTIME).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_LOCALTIME));
    }

    /**
     * Get the <i>Last Set Time</i> attribute [attribute ID <b>0x0008</b>].
     * <p>
     * The LastSetTime attribute indicates the most recent time that the Time attribute was
     * set, either internally or over the ZigBee network (thus it holds a copy of the last value
     * that Time was set to). This attribute is set automatically, so is Read Only. The value
     * 0xffffffff indicates an invalid LastSetTime.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLastSetTimeAsync() {
        return read(attributes.get(ATTR_LASTSETTIME));
    }

    /**
     * Synchronously get the <i>Last Set Time</i> attribute [attribute ID <b>0x0008</b>].
     * <p>
     * The LastSetTime attribute indicates the most recent time that the Time attribute was
     * set, either internally or over the ZigBee network (thus it holds a copy of the last value
     * that Time was set to). This attribute is set automatically, so is Read Only. The value
     * 0xffffffff indicates an invalid LastSetTime.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getLastSetTime(final long refreshPeriod) {
        if (attributes.get(ATTR_LASTSETTIME).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_LASTSETTIME).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_LASTSETTIME));
    }

    /**
     * Set the <i>Valid Until Time</i> attribute [attribute ID <b>0x0009</b>].
     * <p>
     * The ValidUntilTime attribute indicates a time, later than LastSetTime, up to which the
     * Time attribute may be trusted. ‘Trusted’ means that the difference between the Time
     * attribute and the true UTC time is less than an acceptable error. The acceptable error is
     * not defined by this cluster specification, but may be defined by the application
     * profile in which devices that use this cluster are specified.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param validUntilTime the {@link Calendar} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setValidUntilTime(final Calendar value) {
        return write(attributes.get(ATTR_VALIDUNTILTIME), value);
    }

    /**
     * Get the <i>Valid Until Time</i> attribute [attribute ID <b>0x0009</b>].
     * <p>
     * The ValidUntilTime attribute indicates a time, later than LastSetTime, up to which the
     * Time attribute may be trusted. ‘Trusted’ means that the difference between the Time
     * attribute and the true UTC time is less than an acceptable error. The acceptable error is
     * not defined by this cluster specification, but may be defined by the application
     * profile in which devices that use this cluster are specified.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getValidUntilTimeAsync() {
        return read(attributes.get(ATTR_VALIDUNTILTIME));
    }

    /**
     * Synchronously get the <i>Valid Until Time</i> attribute [attribute ID <b>0x0009</b>].
     * <p>
     * The ValidUntilTime attribute indicates a time, later than LastSetTime, up to which the
     * Time attribute may be trusted. ‘Trusted’ means that the difference between the Time
     * attribute and the true UTC time is less than an acceptable error. The acceptable error is
     * not defined by this cluster specification, but may be defined by the application
     * profile in which devices that use this cluster are specified.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getValidUntilTime(final long refreshPeriod) {
        if (attributes.get(ATTR_VALIDUNTILTIME).isLastValueCurrent(refreshPeriod)) {
            return (Calendar) attributes.get(ATTR_VALIDUNTILTIME).getLastValue();
        }

        return (Calendar) readSync(attributes.get(ATTR_VALIDUNTILTIME));
    }
}
