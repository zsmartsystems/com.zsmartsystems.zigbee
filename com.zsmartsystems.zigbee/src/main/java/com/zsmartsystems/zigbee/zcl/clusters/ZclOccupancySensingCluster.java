/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Occupancy Sensing</b> cluster implementation (<i>Cluster ID 0x0406</i>).
 * <p>
 * The cluster provides an interface to occupancy sensing functionality, including
 * configuration and provision of notifications of occupancy status.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-10-12T13:08:25Z")
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
     * The OccupancySensorTypeBitmap attribute specifies the types of the occupancy
     * sensor, as listed below; a ‘1’ in each bit position indicates this type is implemented.
     */
    public static final int ATTR_OCCUPANCYSENSORTYPEBITMAP = 0x0002;
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
     * The UltrasonicOccupiedToUnoccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its occupied state when the sensed
     * area becomes unoccupied. This attribute, along with
     * UltraSonicUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter' when
     * used in an area where occupation changes frequently.
     */
    public static final int ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY = 0x0020;
    /**
     * The UltrsonicUnoccupiedToOccupiedTime attribute specifies the time delay, in
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
    /**
     * The PhysicalContactOccupiedToUnoccupiedDelay attribute is 16 bits in length and
     * specifies the time delay, in seconds, before the physical contact occupancy sensor
     * changes to its unoccupied state after detecting the unoccupied event. The value of
     * 0xffff indicates the sensor does not report occupied to unoccupied transition.
     */
    public static final int ATTR_PHYSICALCONTACTOCCUPIEDTOUNOCCUPIEDDELAY = 0x0030;
    /**
     * The PhysicalContactUnoccupiedToOccupiedDelay attribute is 16 bits in length and
     * specifies the time delay, in seconds, before the physical contact sensor changes to its
     * occupied state after the detection of the occupied event.
     */
    public static final int ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDDELAY = 0x0031;
    /**
     * The PhysicalContactUnoccupiedToOccupiedThreshold attribute is 8 bits in length and
     * specifies the number of movement detection events that must occur in the period
     * PhysicalContactUnoccupiedToOccupiedDelay, before the PIR sensor changes to its
     * occupied state. This attribute is mandatory if the
     * PhysicalContactUnoccupiedToOccupiedDelay attribute is implemented.
     */
    public static final int ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDTHRESHOLD = 0x0032;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeClientAttributes();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeServerAttributes();

        attributeMap.put(ATTR_OCCUPANCY, new ZclAttribute(this, ATTR_OCCUPANCY, "Occupancy", ZclDataType.BITMAP_8_BIT, true, true, false, true));
        attributeMap.put(ATTR_OCCUPANCYSENSORTYPE, new ZclAttribute(this, ATTR_OCCUPANCYSENSORTYPE, "Occupancy Sensor Type", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_OCCUPANCYSENSORTYPEBITMAP, new ZclAttribute(this, ATTR_OCCUPANCYSENSORTYPEBITMAP, "Occupancy Sensor Type Bitmap", ZclDataType.BITMAP_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY, new ZclAttribute(this, ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY, "PIR Occupied To Unoccupied Delay", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY, new ZclAttribute(this, ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY, "PIR Unoccupied To Occupied Delay", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD, new ZclAttribute(this, ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD, "PIR Unoccupied To Occupied Threshold", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY, new ZclAttribute(this, ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY, "Ultrasonic Occupied To Unoccupied Delay", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY, new ZclAttribute(this, ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY, "Ultrasonic Unoccupied To Occupied Delay", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD, new ZclAttribute(this, ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD, "Ultrasonic Unoccupied To Occupied Threshold", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_PHYSICALCONTACTOCCUPIEDTOUNOCCUPIEDDELAY, new ZclAttribute(this, ATTR_PHYSICALCONTACTOCCUPIEDTOUNOCCUPIEDDELAY, "Physical Contact Occupied To Unoccupied Delay", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDDELAY, new ZclAttribute(this, ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDDELAY, "Physical Contact Unoccupied To Occupied Delay", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDTHRESHOLD, new ZclAttribute(this, ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDTHRESHOLD, "Physical Contact Unoccupied To Occupied Threshold", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));

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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOccupancyAsync() {
        return read(serverAttributes.get(ATTR_OCCUPANCY));
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getOccupancy(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OCCUPANCY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OCCUPANCY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OCCUPANCY));
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
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setOccupancyReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_OCCUPANCY), minInterval, maxInterval);
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOccupancySensorTypeAsync() {
        return read(serverAttributes.get(ATTR_OCCUPANCYSENSORTYPE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getOccupancySensorType(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OCCUPANCYSENSORTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OCCUPANCYSENSORTYPE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OCCUPANCYSENSORTYPE));
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
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setOccupancySensorTypeReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_OCCUPANCYSENSORTYPE), minInterval, maxInterval);
    }

    /**
     * Get the <i>Occupancy Sensor Type Bitmap</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The OccupancySensorTypeBitmap attribute specifies the types of the occupancy
     * sensor, as listed below; a ‘1’ in each bit position indicates this type is implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOccupancySensorTypeBitmapAsync() {
        return read(serverAttributes.get(ATTR_OCCUPANCYSENSORTYPEBITMAP));
    }

    /**
     * Synchronously get the <i>Occupancy Sensor Type Bitmap</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The OccupancySensorTypeBitmap attribute specifies the types of the occupancy
     * sensor, as listed below; a ‘1’ in each bit position indicates this type is implemented.
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
    public Integer getOccupancySensorTypeBitmap(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OCCUPANCYSENSORTYPEBITMAP).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OCCUPANCYSENSORTYPEBITMAP).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OCCUPANCYSENSORTYPEBITMAP));
    }

    /**
     * Set reporting for the <i>Occupancy Sensor Type Bitmap</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The OccupancySensorTypeBitmap attribute specifies the types of the occupancy
     * sensor, as listed below; a ‘1’ in each bit position indicates this type is implemented.
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
    public Future<CommandResult> setOccupancySensorTypeBitmapReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_OCCUPANCYSENSORTYPEBITMAP), minInterval, maxInterval);
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
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setPirOccupiedToUnoccupiedDelay(final Integer pirOccupiedToUnoccupiedDelay) {
        return write(serverAttributes.get(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY), pirOccupiedToUnoccupiedDelay);
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPirOccupiedToUnoccupiedDelayAsync() {
        return read(serverAttributes.get(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY));
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getPirOccupiedToUnoccupiedDelay(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_PIROCCUPIEDTOUNOCCUPIEDDELAY));
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
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setPirUnoccupiedToOccupiedDelay(final Integer pirUnoccupiedToOccupiedDelay) {
        return write(serverAttributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY), pirUnoccupiedToOccupiedDelay);
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPirUnoccupiedToOccupiedDelayAsync() {
        return read(serverAttributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY));
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getPirUnoccupiedToOccupiedDelay(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDDELAY));
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
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setPirUnoccupiedToOccupiedThreshold(final Integer pirUnoccupiedToOccupiedThreshold) {
        return write(serverAttributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD), pirUnoccupiedToOccupiedThreshold);
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPirUnoccupiedToOccupiedThresholdAsync() {
        return read(serverAttributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD));
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getPirUnoccupiedToOccupiedThreshold(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_PIRUNOCCUPIEDTOOCCUPIEDTHRESHOLD));
    }

    /**
     * Set the <i>Ultrasonic Occupied To Unoccupied Delay</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The UltrasonicOccupiedToUnoccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its occupied state when the sensed
     * area becomes unoccupied. This attribute, along with
     * UltraSonicUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter' when
     * used in an area where occupation changes frequently.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param ultrasonicOccupiedToUnoccupiedDelay the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setUltrasonicOccupiedToUnoccupiedDelay(final Integer ultrasonicOccupiedToUnoccupiedDelay) {
        return write(serverAttributes.get(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY), ultrasonicOccupiedToUnoccupiedDelay);
    }

    /**
     * Get the <i>Ultrasonic Occupied To Unoccupied Delay</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The UltrasonicOccupiedToUnoccupiedTime attribute specifies the time delay, in
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getUltrasonicOccupiedToUnoccupiedDelayAsync() {
        return read(serverAttributes.get(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY));
    }

    /**
     * Synchronously get the <i>Ultrasonic Occupied To Unoccupied Delay</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The UltrasonicOccupiedToUnoccupiedTime attribute specifies the time delay, in
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getUltrasonicOccupiedToUnoccupiedDelay(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ULTRASONICOCCUPIEDTOUNOCCUPIEDDELAY));
    }

    /**
     * Set the <i>Ultrasonic Unoccupied To Occupied Delay</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * The UltrsonicUnoccupiedToOccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its unoccupied state when the sensed
     * area becomes occupied.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param ultrasonicUnoccupiedToOccupiedDelay the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setUltrasonicUnoccupiedToOccupiedDelay(final Integer ultrasonicUnoccupiedToOccupiedDelay) {
        return write(serverAttributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY), ultrasonicUnoccupiedToOccupiedDelay);
    }

    /**
     * Get the <i>Ultrasonic Unoccupied To Occupied Delay</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * The UltrsonicUnoccupiedToOccupiedTime attribute specifies the time delay, in
     * seconds, before the ultrasonic sensor changes to its unoccupied state when the sensed
     * area becomes occupied.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getUltrasonicUnoccupiedToOccupiedDelayAsync() {
        return read(serverAttributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY));
    }

    /**
     * Synchronously get the <i>Ultrasonic Unoccupied To Occupied Delay</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * The UltrsonicUnoccupiedToOccupiedTime attribute specifies the time delay, in
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getUltrasonicUnoccupiedToOccupiedDelay(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDDELAY));
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
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setUltrasonicUnoccupiedToOccupiedThreshold(final Integer ultrasonicUnoccupiedToOccupiedThreshold) {
        return write(serverAttributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD), ultrasonicUnoccupiedToOccupiedThreshold);
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
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getUltrasonicUnoccupiedToOccupiedThresholdAsync() {
        return read(serverAttributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD));
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getUltrasonicUnoccupiedToOccupiedThreshold(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ULTRASONICUNOCCUPIEDTOOCCUPIEDTHRESHOLD));
    }

    /**
     * Set the <i>Physical Contact Occupied To Unoccupied Delay</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * The PhysicalContactOccupiedToUnoccupiedDelay attribute is 16 bits in length and
     * specifies the time delay, in seconds, before the physical contact occupancy sensor
     * changes to its unoccupied state after detecting the unoccupied event. The value of
     * 0xffff indicates the sensor does not report occupied to unoccupied transition.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param physicalContactOccupiedToUnoccupiedDelay the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setPhysicalContactOccupiedToUnoccupiedDelay(final Integer physicalContactOccupiedToUnoccupiedDelay) {
        return write(serverAttributes.get(ATTR_PHYSICALCONTACTOCCUPIEDTOUNOCCUPIEDDELAY), physicalContactOccupiedToUnoccupiedDelay);
    }

    /**
     * Get the <i>Physical Contact Occupied To Unoccupied Delay</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * The PhysicalContactOccupiedToUnoccupiedDelay attribute is 16 bits in length and
     * specifies the time delay, in seconds, before the physical contact occupancy sensor
     * changes to its unoccupied state after detecting the unoccupied event. The value of
     * 0xffff indicates the sensor does not report occupied to unoccupied transition.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPhysicalContactOccupiedToUnoccupiedDelayAsync() {
        return read(serverAttributes.get(ATTR_PHYSICALCONTACTOCCUPIEDTOUNOCCUPIEDDELAY));
    }

    /**
     * Synchronously get the <i>Physical Contact Occupied To Unoccupied Delay</i> attribute [attribute ID <b>0x0030</b>].
     * <p>
     * The PhysicalContactOccupiedToUnoccupiedDelay attribute is 16 bits in length and
     * specifies the time delay, in seconds, before the physical contact occupancy sensor
     * changes to its unoccupied state after detecting the unoccupied event. The value of
     * 0xffff indicates the sensor does not report occupied to unoccupied transition.
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
    public Integer getPhysicalContactOccupiedToUnoccupiedDelay(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_PHYSICALCONTACTOCCUPIEDTOUNOCCUPIEDDELAY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_PHYSICALCONTACTOCCUPIEDTOUNOCCUPIEDDELAY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_PHYSICALCONTACTOCCUPIEDTOUNOCCUPIEDDELAY));
    }

    /**
     * Set the <i>Physical Contact Unoccupied To Occupied Delay</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * The PhysicalContactUnoccupiedToOccupiedDelay attribute is 16 bits in length and
     * specifies the time delay, in seconds, before the physical contact sensor changes to its
     * occupied state after the detection of the occupied event.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param physicalContactUnoccupiedToOccupiedDelay the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setPhysicalContactUnoccupiedToOccupiedDelay(final Integer physicalContactUnoccupiedToOccupiedDelay) {
        return write(serverAttributes.get(ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDDELAY), physicalContactUnoccupiedToOccupiedDelay);
    }

    /**
     * Get the <i>Physical Contact Unoccupied To Occupied Delay</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * The PhysicalContactUnoccupiedToOccupiedDelay attribute is 16 bits in length and
     * specifies the time delay, in seconds, before the physical contact sensor changes to its
     * occupied state after the detection of the occupied event.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPhysicalContactUnoccupiedToOccupiedDelayAsync() {
        return read(serverAttributes.get(ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDDELAY));
    }

    /**
     * Synchronously get the <i>Physical Contact Unoccupied To Occupied Delay</i> attribute [attribute ID <b>0x0031</b>].
     * <p>
     * The PhysicalContactUnoccupiedToOccupiedDelay attribute is 16 bits in length and
     * specifies the time delay, in seconds, before the physical contact sensor changes to its
     * occupied state after the detection of the occupied event.
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
    public Integer getPhysicalContactUnoccupiedToOccupiedDelay(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDDELAY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDDELAY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDDELAY));
    }

    /**
     * Set the <i>Physical Contact Unoccupied To Occupied Threshold</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * The PhysicalContactUnoccupiedToOccupiedThreshold attribute is 8 bits in length and
     * specifies the number of movement detection events that must occur in the period
     * PhysicalContactUnoccupiedToOccupiedDelay, before the PIR sensor changes to its
     * occupied state. This attribute is mandatory if the
     * PhysicalContactUnoccupiedToOccupiedDelay attribute is implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param physicalContactUnoccupiedToOccupiedThreshold the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setPhysicalContactUnoccupiedToOccupiedThreshold(final Integer physicalContactUnoccupiedToOccupiedThreshold) {
        return write(serverAttributes.get(ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDTHRESHOLD), physicalContactUnoccupiedToOccupiedThreshold);
    }

    /**
     * Get the <i>Physical Contact Unoccupied To Occupied Threshold</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * The PhysicalContactUnoccupiedToOccupiedThreshold attribute is 8 bits in length and
     * specifies the number of movement detection events that must occur in the period
     * PhysicalContactUnoccupiedToOccupiedDelay, before the PIR sensor changes to its
     * occupied state. This attribute is mandatory if the
     * PhysicalContactUnoccupiedToOccupiedDelay attribute is implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getPhysicalContactUnoccupiedToOccupiedThresholdAsync() {
        return read(serverAttributes.get(ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDTHRESHOLD));
    }

    /**
     * Synchronously get the <i>Physical Contact Unoccupied To Occupied Threshold</i> attribute [attribute ID <b>0x0032</b>].
     * <p>
     * The PhysicalContactUnoccupiedToOccupiedThreshold attribute is 8 bits in length and
     * specifies the number of movement detection events that must occur in the period
     * PhysicalContactUnoccupiedToOccupiedDelay, before the PIR sensor changes to its
     * occupied state. This attribute is mandatory if the
     * PhysicalContactUnoccupiedToOccupiedDelay attribute is implemented.
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
    public Integer getPhysicalContactUnoccupiedToOccupiedThreshold(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDTHRESHOLD).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDTHRESHOLD).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_PHYSICALCONTACTUNOCCUPIEDTOOCCUPIEDTHRESHOLD));
    }
}
