package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>Time</b> cluster implementation (<i>Cluster ID 0x000A</i>).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclTimeCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x000A;

    // Cluster Name
    public static final String CLUSTER_NAME = "Time";

    // Attribute constants
    public static final int ATTR_TIME = 0x0000;
    public static final int ATTR_TIMESTATUS = 0x0001;
    public static final int ATTR_TIMEZONE = 0x0002;
    public static final int ATTR_DSTSTART = 0x0003;
    public static final int ATTR_DSTEND = 0x0004;
    public static final int ATTR_DSTSHIFT = 0x0005;
    public static final int ATTR_STANDARDTIME = 0x0006;
    public static final int ATTR_LOCALTIME = 0x0007;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(8);

        attributeMap.put(ATTR_TIME, new ZclAttribute(0, "Time", ZclDataType.UTCTIME, true, true, true, false));
        attributeMap.put(ATTR_TIMESTATUS, new ZclAttribute(1, "TimeStatus", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_TIMEZONE, new ZclAttribute(2, "TimeZone", ZclDataType.SIGNED_32_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_DSTSTART, new ZclAttribute(3, "DstStart", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_DSTEND, new ZclAttribute(4, "DstEnd", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_DSTSHIFT, new ZclAttribute(5, "DstShift", ZclDataType.SIGNED_32_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_STANDARDTIME, new ZclAttribute(6, "StandardTime", ZclDataType.SIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_LOCALTIME, new ZclAttribute(7, "LocalTime", ZclDataType.SIGNED_32_BIT_INTEGER, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclTimeCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }



    /**
     * <p>
     * Set the <i>Time</i> attribute.
     * <p>
     * <p>
     * The Time attribute is 32-bits in length and holds the time value of a real time
     * clock. This attribute has data type UTCTime, but note that it may not actually be
     * synchronised to UTC - see discussion of the TimeStatus attribute below.
     * <br>
     * If the Master bit of the TimeStatus attribute has a value of 0, writing to this
     * attribute shall set the real time clock to the written value, otherwise it cannot be
     * written. The value 0xffffffff indicates an invalid time.
     * <p>
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param time the {@link Calendar} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTime(final Object value) {
        return write(attributes.get(ATTR_TIME), value);
    }

    /**
     * <p>
     * Get the <i>Time</i> attribute.
     * <p>
     * <p>
     * The Time attribute is 32-bits in length and holds the time value of a real time
     * clock. This attribute has data type UTCTime, but note that it may not actually be
     * synchronised to UTC - see discussion of the TimeStatus attribute below.
     * <br>
     * If the Master bit of the TimeStatus attribute has a value of 0, writing to this
     * attribute shall set the real time clock to the written value, otherwise it cannot be
     * written. The value 0xffffffff indicates an invalid time.
     * <p>
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
     * <p>
     * Synchronously get the <i>Time</i> attribute.
     * <p>
     * <p>
     * The Time attribute is 32-bits in length and holds the time value of a real time
     * clock. This attribute has data type UTCTime, but note that it may not actually be
     * synchronised to UTC - see discussion of the TimeStatus attribute below.
     * <br>
     * If the Master bit of the TimeStatus attribute has a value of 0, writing to this
     * attribute shall set the real time clock to the written value, otherwise it cannot be
     * written. The value 0xffffffff indicates an invalid time.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Calendar}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Calendar} attribute value, or null on error
     */
    public Calendar getTime() {
        return (Calendar) readSync(attributes.get(ATTR_TIME));
    }


    /**
     * <p>
     * Set the <i>TimeStatus</i> attribute.
     * <p>
     * <p>
     * <br>
     * The TimeStatus attribute holds a number of bit fields.
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param timeStatus the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTimeStatus(final Object value) {
        return write(attributes.get(ATTR_TIMESTATUS), value);
    }

    /**
     * <p>
     * Get the <i>TimeStatus</i> attribute.
     * <p>
     * <p>
     * <br>
     * The TimeStatus attribute holds a number of bit fields.
     * <p>
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
     * <p>
     * Synchronously get the <i>TimeStatus</i> attribute.
     * <p>
     * <p>
     * <br>
     * The TimeStatus attribute holds a number of bit fields.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTimeStatus() {
        return (Integer) readSync(attributes.get(ATTR_TIMESTATUS));
    }


    /**
     * <p>
     * Set the <i>TimeZone</i> attribute.
     * <p>
     * <p>
     * <br>
     * The TimeZone attribute indicates the local time zone, as a signed offset in seconds
     * from the Time attribute value. The value 0xffffffff indicates an invalid time zone.
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param timeZone the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setTimeZone(final Object value) {
        return write(attributes.get(ATTR_TIMEZONE), value);
    }

    /**
     * <p>
     * Get the <i>TimeZone</i> attribute.
     * <p>
     * <p>
     * <br>
     * The TimeZone attribute indicates the local time zone, as a signed offset in seconds
     * from the Time attribute value. The value 0xffffffff indicates an invalid time zone.
     * <p>
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
     * <p>
     * Synchronously get the <i>TimeZone</i> attribute.
     * <p>
     * <p>
     * <br>
     * The TimeZone attribute indicates the local time zone, as a signed offset in seconds
     * from the Time attribute value. The value 0xffffffff indicates an invalid time zone.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getTimeZone() {
        return (Integer) readSync(attributes.get(ATTR_TIMEZONE));
    }


    /**
     * <p>
     * Set the <i>DstStart</i> attribute.
     * <p>
     * <p>
     * <br>
     * The DstStart attribute indicates the DST start time in seconds. The value 0xffffffff
     * indicates an invalid DST start time.
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param dstStart the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDstStart(final Object value) {
        return write(attributes.get(ATTR_DSTSTART), value);
    }

    /**
     * <p>
     * Get the <i>DstStart</i> attribute.
     * <p>
     * <p>
     * <br>
     * The DstStart attribute indicates the DST start time in seconds. The value 0xffffffff
     * indicates an invalid DST start time.
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDstStartAsync() {
        return read(attributes.get(ATTR_DSTSTART));
    }


    /**
     * <p>
     * Synchronously get the <i>DstStart</i> attribute.
     * <p>
     * <p>
     * <br>
     * The DstStart attribute indicates the DST start time in seconds. The value 0xffffffff
     * indicates an invalid DST start time.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getDstStart() {
        return (Integer) readSync(attributes.get(ATTR_DSTSTART));
    }


    /**
     * <p>
     * Set the <i>DstEnd</i> attribute.
     * <p>
     * <p>
     * <br>
     * The DstEnd attribute indicates the DST end time in seconds. The value 0xffffffff
     * indicates an invalid DST end time.
     * <br>
     * Note that the three attributes DstStart, DstEnd and DstShift are optional, but if any
     * one of them is implemented the other two must also be implemented.
     * Note that this attribute should be set to a new value once every year.
     * <br>
     * Note that this attribute should be set to a new value once every year, and should be
     * written synchronously with the DstStart attribute.
     * <br>
     * The DstEnd attribute indicates the DST end time in seconds. The value 0xffffffff
     * indicates an invalid DST end time.
     * <br>
     * Note that this attribute should be set to a new value once every year, and should be
     * written synchronously with the DstStart attribute
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param dstEnd the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDstEnd(final Object value) {
        return write(attributes.get(ATTR_DSTEND), value);
    }

    /**
     * <p>
     * Get the <i>DstEnd</i> attribute.
     * <p>
     * <p>
     * <br>
     * The DstEnd attribute indicates the DST end time in seconds. The value 0xffffffff
     * indicates an invalid DST end time.
     * <br>
     * Note that the three attributes DstStart, DstEnd and DstShift are optional, but if any
     * one of them is implemented the other two must also be implemented.
     * Note that this attribute should be set to a new value once every year.
     * <br>
     * Note that this attribute should be set to a new value once every year, and should be
     * written synchronously with the DstStart attribute.
     * <br>
     * The DstEnd attribute indicates the DST end time in seconds. The value 0xffffffff
     * indicates an invalid DST end time.
     * <br>
     * Note that this attribute should be set to a new value once every year, and should be
     * written synchronously with the DstStart attribute
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDstEndAsync() {
        return read(attributes.get(ATTR_DSTEND));
    }


    /**
     * <p>
     * Synchronously get the <i>DstEnd</i> attribute.
     * <p>
     * <p>
     * <br>
     * The DstEnd attribute indicates the DST end time in seconds. The value 0xffffffff
     * indicates an invalid DST end time.
     * <br>
     * Note that the three attributes DstStart, DstEnd and DstShift are optional, but if any
     * one of them is implemented the other two must also be implemented.
     * Note that this attribute should be set to a new value once every year.
     * <br>
     * Note that this attribute should be set to a new value once every year, and should be
     * written synchronously with the DstStart attribute.
     * <br>
     * The DstEnd attribute indicates the DST end time in seconds. The value 0xffffffff
     * indicates an invalid DST end time.
     * <br>
     * Note that this attribute should be set to a new value once every year, and should be
     * written synchronously with the DstStart attribute
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getDstEnd() {
        return (Integer) readSync(attributes.get(ATTR_DSTEND));
    }


    /**
     * <p>
     * Set the <i>DstShift</i> attribute.
     * <p>
     * <p>
     * <br>
     * The DstShift attribute represents a signed offset in seconds from the standard time,
     * to be applied between the times DstStart and DstEnd to calculate the Local Time.
     * The value 0xffffffff indicates an invalid DST shift.
     * <br>
     * The range of this attribute is +/- one day. Note that the actual range of DST values
     * employed by countries is much smaller than this, so the manufacturer has the
     * option to impose a smaller range.
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param dstShift the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setDstShift(final Object value) {
        return write(attributes.get(ATTR_DSTSHIFT), value);
    }

    /**
     * <p>
     * Get the <i>DstShift</i> attribute.
     * <p>
     * <p>
     * <br>
     * The DstShift attribute represents a signed offset in seconds from the standard time,
     * to be applied between the times DstStart and DstEnd to calculate the Local Time.
     * The value 0xffffffff indicates an invalid DST shift.
     * <br>
     * The range of this attribute is +/- one day. Note that the actual range of DST values
     * employed by countries is much smaller than this, so the manufacturer has the
     * option to impose a smaller range.
     * <p>
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
     * <p>
     * Synchronously get the <i>DstShift</i> attribute.
     * <p>
     * <p>
     * <br>
     * The DstShift attribute represents a signed offset in seconds from the standard time,
     * to be applied between the times DstStart and DstEnd to calculate the Local Time.
     * The value 0xffffffff indicates an invalid DST shift.
     * <br>
     * The range of this attribute is +/- one day. Note that the actual range of DST values
     * employed by countries is much smaller than this, so the manufacturer has the
     * option to impose a smaller range.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getDstShift() {
        return (Integer) readSync(attributes.get(ATTR_DSTSHIFT));
    }

    /**
     * <p>
     * Get the <i>StandardTime</i> attribute.
     * <p>
     * <p>
     * <br>
     * A device may derive the time by reading the Time and TimeZone attributes
     * and adding them together. If implemented however, the optional StandardTime
     * attribute indicates this time directly. The value 0xffffffff indicates an invalid
     * Standard Time.
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getStandardTimeAsync() {
        return read(attributes.get(ATTR_STANDARDTIME));
    }


    /**
     * <p>
     * Synchronously get the <i>StandardTime</i> attribute.
     * <p>
     * <p>
     * <br>
     * A device may derive the time by reading the Time and TimeZone attributes
     * and adding them together. If implemented however, the optional StandardTime
     * attribute indicates this time directly. The value 0xffffffff indicates an invalid
     * Standard Time.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getStandardTime() {
        return (Integer) readSync(attributes.get(ATTR_STANDARDTIME));
    }

    /**
     * <p>
     * Get the <i>LocalTime</i> attribute.
     * <p>
     * <p>
     * <br>
     * A device may derive the time by reading the Time, TimeZone, DstStart, DstEnd
     * and DstShift attributes and performing the calculation. If implemented however,
     * the optional LocalTime attribute indicates this time directly. The value 0xffffffff
     * indicates an invalid Local Time.
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLocalTimeAsync() {
        return read(attributes.get(ATTR_LOCALTIME));
    }


    /**
     * <p>
     * Synchronously get the <i>LocalTime</i> attribute.
     * <p>
     * <p>
     * <br>
     * A device may derive the time by reading the Time, TimeZone, DstStart, DstEnd
     * and DstShift attributes and performing the calculation. If implemented however,
     * the optional LocalTime attribute indicates this time directly. The value 0xffffffff
     * indicates an invalid Local Time.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getLocalTime() {
        return (Integer) readSync(attributes.get(ATTR_LOCALTIME));
    }

    /**
     * Add a binding for this cluster to the local node
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> bind() {
        return bind();
    }
}
