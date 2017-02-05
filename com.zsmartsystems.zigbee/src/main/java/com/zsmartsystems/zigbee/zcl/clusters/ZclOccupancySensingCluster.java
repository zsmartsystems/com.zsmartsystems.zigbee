package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>Occupancy sensing</b> cluster implementation (<i>Cluster ID 0x0406</i>).
 * <p>
 * The cluster provides an interface to occupancy sensing functionality,
 * including configuration and provision of notifications of occupancy status.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclOccupancySensingCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x0406;

    // Cluster Name
    public static final String CLUSTER_NAME = "Occupancy sensing";

    // Attribute constants
    public static final int ATTR_OCCUPANCY = 0x0000;
    public static final int ATTR_OCCUPANCYSENSORTYPE = 0x0001;
    public static final int ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY = 0x0010;
    public static final int ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY = 0x0011;
    public static final int ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY = 0x0020;
    public static final int ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY = 0x0021;
    public static final int ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD = 0x0022;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(7);

        attributeMap.put(ATTR_OCCUPANCY, new ZclAttribute(0, "Occupancy", ZclDataType.BITMAP_8_BIT, true, true, false, true));
        attributeMap.put(ATTR_OCCUPANCYSENSORTYPE, new ZclAttribute(1, "OccupancySensorType", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY, new ZclAttribute(16, "PIROccupiedToUnoccupiedDelay", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY, new ZclAttribute(17, "PIRUnoccupiedToOccupiedDelay", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY, new ZclAttribute(32, "UltraSonicOccupiedToUnoccupiedDelay", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY, new ZclAttribute(33, "UltraSonicUnoccupiedToOccupiedDelay", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD, new ZclAttribute(34, "UltrasonicUnoccupiedToOccupiedThreshold", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclOccupancySensingCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * <p>
     * Get the <i>Occupancy</i> attribute [Attribute ID <b>0</b>].
     * <p>
     * <p>
     * The Occupancy attribute is a bitmap.
     * <br>
     * Bit 0 specifies the sensed occupancy as follows: 1 = occupied, 0 = unoccupied.
     * All other bits are reserved.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getOccupancyAsync() {
        return read(attributes.get(ATTR_OCCUPANCY));
    }


    /**
     * <p>
     * Synchronously get the <i>Occupancy</i> attribute [Attribute ID <b>0</b>].
     * <p>
     * <p>
     * The Occupancy attribute is a bitmap.
     * <br>
     * Bit 0 specifies the sensed occupancy as follows: 1 = occupied, 0 = unoccupied.
     * All other bits are reserved.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getOccupancy() {
        return (Integer) readSync(attributes.get(ATTR_OCCUPANCY));
    }


    /**
     * <p>
     * Configure reporting for the <i>Occupancy</i> attribute [Attribute ID <b>0</b>].
     * <p>
     * <p>
     * The Occupancy attribute is a bitmap.
     * <br>
     * Bit 0 specifies the sensed occupancy as follows: 1 = occupied, 0 = unoccupied.
     * All other bits are reserved.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> configOccupancyReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return report(ATTR_OCCUPANCY, minInterval, maxInterval, reportableChange);
    }

    /**
     * <p>
     * Get the <i>OccupancySensorType</i> attribute [Attribute ID <b>1</b>].
     * <p>
     * <p>
     * <br>
     * The OccupancySensorType attribute specifies the type of the occupancy sensor.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getOccupancySensorTypeAsync() {
        return read(attributes.get(ATTR_OCCUPANCYSENSORTYPE));
    }


    /**
     * <p>
     * Synchronously get the <i>OccupancySensorType</i> attribute [Attribute ID <b>1</b>].
     * <p>
     * <p>
     * <br>
     * The OccupancySensorType attribute specifies the type of the occupancy sensor.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getOccupancySensorType() {
        return (Integer) readSync(attributes.get(ATTR_OCCUPANCYSENSORTYPE));
    }


    /**
     * <p>
     * Set the <i>PIROccupiedToUnoccupiedDelay</i> attribute [Attribute ID <b>16</b>].
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param pirOccupiedToUnoccupiedDelay the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPirOccupiedToUnoccupiedDelay(final Object value) {
        return write(attributes.get(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY), value);
    }

    /**
     * <p>
     * Get the <i>PIROccupiedToUnoccupiedDelay</i> attribute [Attribute ID <b>16</b>].
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPirOccupiedToUnoccupiedDelayAsync() {
        return read(attributes.get(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY));
    }


    /**
     * <p>
     * Synchronously get the <i>PIROccupiedToUnoccupiedDelay</i> attribute [Attribute ID <b>16</b>].
     * <p>
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getPirOccupiedToUnoccupiedDelay() {
        return (Integer) readSync(attributes.get(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY));
    }


    /**
     * <p>
     * Set the <i>PIRUnoccupiedToOccupiedDelay</i> attribute [Attribute ID <b>17</b>].
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param pirUnoccupiedToOccupiedDelay the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPirUnoccupiedToOccupiedDelay(final Object value) {
        return write(attributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY), value);
    }

    /**
     * <p>
     * Get the <i>PIRUnoccupiedToOccupiedDelay</i> attribute [Attribute ID <b>17</b>].
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPirUnoccupiedToOccupiedDelayAsync() {
        return read(attributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY));
    }


    /**
     * <p>
     * Synchronously get the <i>PIRUnoccupiedToOccupiedDelay</i> attribute [Attribute ID <b>17</b>].
     * <p>
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getPirUnoccupiedToOccupiedDelay() {
        return (Integer) readSync(attributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY));
    }


    /**
     * <p>
     * Set the <i>UltraSonicOccupiedToUnoccupiedDelay</i> attribute [Attribute ID <b>32</b>].
     * <p>
     * <p>
     * <br>
     * The UltraSonicOccupiedToUnoccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its occupied state when the
     * sensed area becomes unoccupied. This attribute, along with
     * UltraSonicUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter'
     * when used in an area where occupation changes frequently.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param ultraSonicOccupiedToUnoccupiedDelay the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setUltraSonicOccupiedToUnoccupiedDelay(final Object value) {
        return write(attributes.get(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY), value);
    }

    /**
     * <p>
     * Get the <i>UltraSonicOccupiedToUnoccupiedDelay</i> attribute [Attribute ID <b>32</b>].
     * <p>
     * <p>
     * <br>
     * The UltraSonicOccupiedToUnoccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its occupied state when the
     * sensed area becomes unoccupied. This attribute, along with
     * UltraSonicUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter'
     * when used in an area where occupation changes frequently.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getUltraSonicOccupiedToUnoccupiedDelayAsync() {
        return read(attributes.get(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY));
    }


    /**
     * <p>
     * Synchronously get the <i>UltraSonicOccupiedToUnoccupiedDelay</i> attribute [Attribute ID <b>32</b>].
     * <p>
     * <p>
     * <br>
     * The UltraSonicOccupiedToUnoccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its occupied state when the
     * sensed area becomes unoccupied. This attribute, along with
     * UltraSonicUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter'
     * when used in an area where occupation changes frequently.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getUltraSonicOccupiedToUnoccupiedDelay() {
        return (Integer) readSync(attributes.get(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY));
    }


    /**
     * <p>
     * Set the <i>UltraSonicUnoccupiedToOccupiedDelay</i> attribute [Attribute ID <b>33</b>].
     * <p>
     * <p>
     * <br>
     * The UltraSonicUnoccupiedToOccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its unoccupied state when the
     * sensed area becomes occupied.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param ultraSonicUnoccupiedToOccupiedDelay the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setUltraSonicUnoccupiedToOccupiedDelay(final Object value) {
        return write(attributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY), value);
    }

    /**
     * <p>
     * Get the <i>UltraSonicUnoccupiedToOccupiedDelay</i> attribute [Attribute ID <b>33</b>].
     * <p>
     * <p>
     * <br>
     * The UltraSonicUnoccupiedToOccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its unoccupied state when the
     * sensed area becomes occupied.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getUltraSonicUnoccupiedToOccupiedDelayAsync() {
        return read(attributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY));
    }


    /**
     * <p>
     * Synchronously get the <i>UltraSonicUnoccupiedToOccupiedDelay</i> attribute [Attribute ID <b>33</b>].
     * <p>
     * <p>
     * <br>
     * The UltraSonicUnoccupiedToOccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its unoccupied state when the
     * sensed area becomes occupied.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getUltraSonicUnoccupiedToOccupiedDelay() {
        return (Integer) readSync(attributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY));
    }


    /**
     * <p>
     * Set the <i>UltrasonicUnoccupiedToOccupiedThreshold</i> attribute [Attribute ID <b>34</b>].
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param ultrasonicUnoccupiedToOccupiedThreshold the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setUltrasonicUnoccupiedToOccupiedThreshold(final Object value) {
        return write(attributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD), value);
    }

    /**
     * <p>
     * Get the <i>UltrasonicUnoccupiedToOccupiedThreshold</i> attribute [Attribute ID <b>34</b>].
     * <p>
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getUltrasonicUnoccupiedToOccupiedThresholdAsync() {
        return read(attributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD));
    }


    /**
     * <p>
     * Synchronously get the <i>UltrasonicUnoccupiedToOccupiedThreshold</i> attribute [Attribute ID <b>34</b>].
     * <p>
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getUltrasonicUnoccupiedToOccupiedThreshold() {
        return (Integer) readSync(attributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD));
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
