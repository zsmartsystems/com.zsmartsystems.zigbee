/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

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
 * <b>Occupancy Sensing</b> cluster implementation (<i>Cluster ID 0x0406</i>).
 * <p>
 * The cluster provides an interface to occupancy sensing functionality, including
 * configuration and provision of notifications of occupancy status.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ZclOccupancySensingCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0406;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Occupancy Sensing";

    // Attribute constants
    /**
     * The Occupancy attribute is a bitmap.
     * <p>
     * Bit 0 specifies the sensed occupancy as follows: 1 = occupied, 0 = unoccupied. All other
     * bits are reserved.
     */
    public static final int ATTR_OCCUPANCY = 0x0000;
    /**
     * The OccupancySensorType attribute specifies the type of the occupancy sensor.
     */
    public static final int ATTR_OCCUPANCYSENSORTYPE = 0x0001;
    /**
     * The PIROccupiedToUnoccupiedDelay attribute is 8-bits in length and specifies the
     * time delay, in seconds, before the PIR sensor changes to its occupied state when the
     * sensed area becomes unoccupied. This attribute, along with
     * PIRUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter' when used in an
     * area where occupation changes frequently.
     */
    public static final int ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY = 0x0010;
    /**
     * The PIRUnoccupiedToOccupiedDelay attribute is 8-bits in length and specifies the
     * time delay, in seconds, before the PIR sensor changes to its unoccupied state when the
     * sensed area becomes occupied.
     */
    public static final int ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY = 0x0011;
    /**
     * The PIRUnoccupiedToOccupiedThreshold attribute is 8 bits in length and specifies the
     * number of movement detection events that must occur in the period
     * PIRUnoccupiedToOccupiedDelay, before the PIR sensor changes to its occupied state.
     * This attribute is mandatory if the PIRUnoccupiedToOccupiedDelay attribute is
     * implemented.
     */
    public static final int ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD = 0x0012;
    /**
     * The UltraSonicOccupiedToUnoccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its occupied state when the sensed
     * area becomes unoccupied. This attribute, along with
     * UltraSonicUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter' when
     * used in an area where occupation changes frequently.
     */
    public static final int ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY = 0x0020;
    /**
     * The UltraSonicUnoccupiedToOccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its unoccupied state when the sensed
     * area becomes occupied.
     */
    public static final int ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY = 0x0021;
    /**
     * The UltrasonicUnoccupiedToOccupiedThreshold attribute is 8 bits in length and
     * specifies the number of movement detection events that must occur in the period
     * UltrasonicUnoccupiedToOccupiedDelay, before the Ultrasonic sensor changes to its
     * occupied state. This attribute is mandatory if the
     * UltrasonicUnoccupiedToOccupiedDelay attribute is implemented.
     */
    public static final int ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD = 0x0022;

    // Attribute initialisation
    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(8);

        attributeMap.put(ATTR_OCCUPANCY, new ZclAttribute(ZclClusterType.OCCUPANCY_SENSING, ATTR_OCCUPANCY, "Occupancy", ZclDataType.BITMAP_8_BIT, true, true, false, true));
        attributeMap.put(ATTR_OCCUPANCYSENSORTYPE, new ZclAttribute(ZclClusterType.OCCUPANCY_SENSING, ATTR_OCCUPANCYSENSORTYPE, "Occupancy Sensor Type", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY, new ZclAttribute(ZclClusterType.OCCUPANCY_SENSING, ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY, "PIR Occupied To Unoccupied Delay", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY, new ZclAttribute(ZclClusterType.OCCUPANCY_SENSING, ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY, "PIR Unoccupied To Occupied Delay", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD, new ZclAttribute(ZclClusterType.OCCUPANCY_SENSING, ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD, "PIR Unoccupied To Occupied Threshold", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY, new ZclAttribute(ZclClusterType.OCCUPANCY_SENSING, ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY, "Ultra Sonic Occupied To Unoccupied Delay", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY, new ZclAttribute(ZclClusterType.OCCUPANCY_SENSING, ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY, "Ultra Sonic Unoccupied To Occupied Delay", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD, new ZclAttribute(ZclClusterType.OCCUPANCY_SENSING, ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD, "Ultrasonic Unoccupied To Occupied Threshold", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Occupancy Sensing cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclOccupancySensingCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Occupancy</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The Occupancy attribute is a bitmap.
     * <p>
     * Bit 0 specifies the sensed occupancy as follows: 1 = occupied, 0 = unoccupied. All other
     * bits are reserved.
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
     * Synchronously get the <i>Occupancy</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The Occupancy attribute is a bitmap.
     * <p>
     * Bit 0 specifies the sensed occupancy as follows: 1 = occupied, 0 = unoccupied. All other
     * bits are reserved.
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
    public Integer getOccupancy(final long refreshPeriod) {
        if (attributes.get(ATTR_OCCUPANCY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_OCCUPANCY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_OCCUPANCY));
    }

    /**
     * Set reporting for the <i>Occupancy</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The Occupancy attribute is a bitmap.
     * <p>
     * Bit 0 specifies the sensed occupancy as follows: 1 = occupied, 0 = unoccupied. All other
     * bits are reserved.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOccupancyReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_OCCUPANCY), minInterval, maxInterval);
    }

    /**
     * Get the <i>Occupancy Sensor Type</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
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
     * Synchronously get the <i>Occupancy Sensor Type</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The OccupancySensorType attribute specifies the type of the occupancy sensor.
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
    public Integer getOccupancySensorType(final long refreshPeriod) {
        if (attributes.get(ATTR_OCCUPANCYSENSORTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_OCCUPANCYSENSORTYPE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_OCCUPANCYSENSORTYPE));
    }

    /**
     * Set reporting for the <i>Occupancy Sensor Type</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The OccupancySensorType attribute specifies the type of the occupancy sensor.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOccupancySensorTypeReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_OCCUPANCYSENSORTYPE), minInterval, maxInterval);
    }

    /**
     * Set the <i>PIR Occupied To Unoccupied Delay</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The PIROccupiedToUnoccupiedDelay attribute is 8-bits in length and specifies the
     * time delay, in seconds, before the PIR sensor changes to its occupied state when the
     * sensed area becomes unoccupied. This attribute, along with
     * PIRUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter' when used in an
     * area where occupation changes frequently.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param pirOccupiedToUnoccupiedDelay the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPirOccupiedToUnoccupiedDelay(final Integer value) {
        return write(attributes.get(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY), value);
    }

    /**
     * Get the <i>PIR Occupied To Unoccupied Delay</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The PIROccupiedToUnoccupiedDelay attribute is 8-bits in length and specifies the
     * time delay, in seconds, before the PIR sensor changes to its occupied state when the
     * sensed area becomes unoccupied. This attribute, along with
     * PIRUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter' when used in an
     * area where occupation changes frequently.
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
     * Synchronously get the <i>PIR Occupied To Unoccupied Delay</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The PIROccupiedToUnoccupiedDelay attribute is 8-bits in length and specifies the
     * time delay, in seconds, before the PIR sensor changes to its occupied state when the
     * sensed area becomes unoccupied. This attribute, along with
     * PIRUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter' when used in an
     * area where occupation changes frequently.
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
    public Integer getPirOccupiedToUnoccupiedDelay(final long refreshPeriod) {
        if (attributes.get(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY));
    }

    /**
     * Set the <i>PIR Unoccupied To Occupied Delay</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The PIRUnoccupiedToOccupiedDelay attribute is 8-bits in length and specifies the
     * time delay, in seconds, before the PIR sensor changes to its unoccupied state when the
     * sensed area becomes occupied.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param pirUnoccupiedToOccupiedDelay the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPirUnoccupiedToOccupiedDelay(final Integer value) {
        return write(attributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY), value);
    }

    /**
     * Get the <i>PIR Unoccupied To Occupied Delay</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The PIRUnoccupiedToOccupiedDelay attribute is 8-bits in length and specifies the
     * time delay, in seconds, before the PIR sensor changes to its unoccupied state when the
     * sensed area becomes occupied.
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
     * Synchronously get the <i>PIR Unoccupied To Occupied Delay</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The PIRUnoccupiedToOccupiedDelay attribute is 8-bits in length and specifies the
     * time delay, in seconds, before the PIR sensor changes to its unoccupied state when the
     * sensed area becomes occupied.
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
    public Integer getPirUnoccupiedToOccupiedDelay(final long refreshPeriod) {
        if (attributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY));
    }

    /**
     * Set the <i>PIR Unoccupied To Occupied Threshold</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The PIRUnoccupiedToOccupiedThreshold attribute is 8 bits in length and specifies the
     * number of movement detection events that must occur in the period
     * PIRUnoccupiedToOccupiedDelay, before the PIR sensor changes to its occupied state.
     * This attribute is mandatory if the PIRUnoccupiedToOccupiedDelay attribute is
     * implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param pirUnoccupiedToOccupiedThreshold the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPirUnoccupiedToOccupiedThreshold(final Integer value) {
        return write(attributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD), value);
    }

    /**
     * Get the <i>PIR Unoccupied To Occupied Threshold</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The PIRUnoccupiedToOccupiedThreshold attribute is 8 bits in length and specifies the
     * number of movement detection events that must occur in the period
     * PIRUnoccupiedToOccupiedDelay, before the PIR sensor changes to its occupied state.
     * This attribute is mandatory if the PIRUnoccupiedToOccupiedDelay attribute is
     * implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPirUnoccupiedToOccupiedThresholdAsync() {
        return read(attributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD));
    }

    /**
     * Synchronously get the <i>PIR Unoccupied To Occupied Threshold</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The PIRUnoccupiedToOccupiedThreshold attribute is 8 bits in length and specifies the
     * number of movement detection events that must occur in the period
     * PIRUnoccupiedToOccupiedDelay, before the PIR sensor changes to its occupied state.
     * This attribute is mandatory if the PIRUnoccupiedToOccupiedDelay attribute is
     * implemented.
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
    public Integer getPirUnoccupiedToOccupiedThreshold(final long refreshPeriod) {
        if (attributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD));
    }

    /**
     * Set the <i>Ultra Sonic Occupied To Unoccupied Delay</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The UltraSonicOccupiedToUnoccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its occupied state when the sensed
     * area becomes unoccupied. This attribute, along with
     * UltraSonicUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter' when
     * used in an area where occupation changes frequently.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param ultraSonicOccupiedToUnoccupiedDelay the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setUltraSonicOccupiedToUnoccupiedDelay(final Integer value) {
        return write(attributes.get(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY), value);
    }

    /**
     * Get the <i>Ultra Sonic Occupied To Unoccupied Delay</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The UltraSonicOccupiedToUnoccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its occupied state when the sensed
     * area becomes unoccupied. This attribute, along with
     * UltraSonicUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter' when
     * used in an area where occupation changes frequently.
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
     * Synchronously get the <i>Ultra Sonic Occupied To Unoccupied Delay</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The UltraSonicOccupiedToUnoccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its occupied state when the sensed
     * area becomes unoccupied. This attribute, along with
     * UltraSonicUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter' when
     * used in an area where occupation changes frequently.
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
    public Integer getUltraSonicOccupiedToUnoccupiedDelay(final long refreshPeriod) {
        if (attributes.get(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY));
    }

    /**
     * Set the <i>Ultra Sonic Unoccupied To Occupied Delay</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * The UltraSonicUnoccupiedToOccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its unoccupied state when the sensed
     * area becomes occupied.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param ultraSonicUnoccupiedToOccupiedDelay the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setUltraSonicUnoccupiedToOccupiedDelay(final Integer value) {
        return write(attributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY), value);
    }

    /**
     * Get the <i>Ultra Sonic Unoccupied To Occupied Delay</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * The UltraSonicUnoccupiedToOccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its unoccupied state when the sensed
     * area becomes occupied.
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
     * Synchronously get the <i>Ultra Sonic Unoccupied To Occupied Delay</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * The UltraSonicUnoccupiedToOccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its unoccupied state when the sensed
     * area becomes occupied.
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
    public Integer getUltraSonicUnoccupiedToOccupiedDelay(final long refreshPeriod) {
        if (attributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY));
    }

    /**
     * Set the <i>Ultrasonic Unoccupied To Occupied Threshold</i> attribute [attribute ID <b>0x0022</b>].
     * <p>
     * The UltrasonicUnoccupiedToOccupiedThreshold attribute is 8 bits in length and
     * specifies the number of movement detection events that must occur in the period
     * UltrasonicUnoccupiedToOccupiedDelay, before the Ultrasonic sensor changes to its
     * occupied state. This attribute is mandatory if the
     * UltrasonicUnoccupiedToOccupiedDelay attribute is implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param ultrasonicUnoccupiedToOccupiedThreshold the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setUltrasonicUnoccupiedToOccupiedThreshold(final Integer value) {
        return write(attributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD), value);
    }

    /**
     * Get the <i>Ultrasonic Unoccupied To Occupied Threshold</i> attribute [attribute ID <b>0x0022</b>].
     * <p>
     * The UltrasonicUnoccupiedToOccupiedThreshold attribute is 8 bits in length and
     * specifies the number of movement detection events that must occur in the period
     * UltrasonicUnoccupiedToOccupiedDelay, before the Ultrasonic sensor changes to its
     * occupied state. This attribute is mandatory if the
     * UltrasonicUnoccupiedToOccupiedDelay attribute is implemented.
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
     * Synchronously get the <i>Ultrasonic Unoccupied To Occupied Threshold</i> attribute [attribute ID <b>0x0022</b>].
     * <p>
     * The UltrasonicUnoccupiedToOccupiedThreshold attribute is 8 bits in length and
     * specifies the number of movement detection events that must occur in the period
     * UltrasonicUnoccupiedToOccupiedDelay, before the Ultrasonic sensor changes to its
     * occupied state. This attribute is mandatory if the
     * UltrasonicUnoccupiedToOccupiedDelay attribute is implemented.
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
    public Integer getUltrasonicUnoccupiedToOccupiedThreshold(final long refreshPeriod) {
        if (attributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD));
    }
}
