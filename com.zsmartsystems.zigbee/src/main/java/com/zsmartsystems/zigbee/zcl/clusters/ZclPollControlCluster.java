/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.CheckInCommand;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.CheckInResponse;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.FastPollStopCommand;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.SetLongPollIntervalCommand;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.SetShortPollIntervalCommand;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.ZclPollControlCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Poll Control</b> cluster implementation (<i>Cluster ID 0x0020</i>).
 * <p>
 * This cluster provides a mechanism for the management of an end device’s MAC Data Request
 * rate. For the purposes of this cluster, the term “poll” always refers to the sending of a MAC
 * Data Request from the end device to the end device’s parent. This cluster can be used for
 * instance by a configuration device to make an end device responsive for a certain period of
 * time so that the device can be managed by the controller. This cluster is composed of a client
 * and server. The end device implements the server side of this cluster. The server side
 * contains several attributes related to the MAC Data Request rate for the device. The client
 * side implements commands used to manage the poll rate for the device. The end device which
 * implements the server side of this cluster sends a query to the client on a predetermined
 * interval to see if the client would like to manage the poll period of the end device in
 * question. When the client side of the cluster hears from the server it has the opportunity to
 * respond with configuration data to either put the end device in a short poll mode or let the end
 * device continue to function normally.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-04-23T10:36:53Z")
public class ZclPollControlCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0020;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Poll Control";

    // Attribute constants
    /**
     * The Poll Control server is responsible for checking in with the poll control client
     * periodically to see if the poll control client wants to modify the poll rate of the poll
     * control server. This is due to the fact that the PollControl server is implemented on an
     * end device that may have an unpredictable sleep-wake cycle. The CheckinInterval
     * represents the default amount of time between check-ins by the poll control server with
     * the poll control client. The CheckinInterval is measured in quarter-seconds. A value
     * of 0 indicates that the Poll Control Server is turned off and the poll control server will
     * not check-in with the poll control client. The Poll Control Server checks in with the
     * Poll Control Client by sending a Checkin command to the Client. This value SHOULDbe
     * longer than the LongPoll Interval attribute. If the Client writes an invalid attribute
     * value (Example: Out of Range or a value smaller than the optional
     * Check-inIntervalMinattribute value or a value smaller than the LongPollInterval
     * attribute value), the Server should return Write Attributes Response with an error
     * status not equal to ZCL_SUCCESS. The Poll Control Client will hold onto the actions or
     * messages for the Poll Control Server at the application level until the Poll Control
     * Server checks in with the Poll Control Client.
     */
    public static final int ATTR_CHECKININTERVAL = 0x0000;
    /**
     * An end device that implements the Poll Control server may optionally expose a
     * LongPollInterval attribute. The Long Poll Interval represents the maximum amount of
     * time in quarter-seconds between MAC Data Requests from the end device to its parent.
     * <p>
     * The LongPollInterval defines the frequency of polling that an end device does when it is
     * NOT in fast poll mode. The LongPollInterval should be longer than the
     * ShortPollInterval attribute but shorter than the CheckinInterval attribute.A value
     * of 0xffffffff is reserved to indicate that the device does not have or does not know its
     * long poll interval
     */
    public static final int ATTR_LONGPOLLINTERVAL = 0x0001;
    /**
     * An end device that implements the Poll Control server may optionally expose the
     * ShortPollInterval attribute. The ShortPollIntervalrepresents the number of
     * quarterseconds that an end device waits between MAC Data Requests to its parent when it
     * is expecting data (i.e.,in fast poll mode).
     */
    public static final int ATTR_SHORTPOLLINTERVAL = 0x0002;
    /**
     * The FastPollTimeout attribute represents the number of quarterseconds that an end
     * device will stay in fast poll mode by default. It is suggested that the
     * FastPollTimeoutattribute value be greater than 7.68 seconds.The Poll Control
     * Cluster Client MAYoverride this value by indicating a different value in the Fast Poll
     * Duration argument in the Check-in Response command. If the Client writes a value out of
     * range or greater than the optional FastPollTimeoutMax attribute value if supported,
     * the Server should return a Write Attributes Response with a status of INVALID_VALUE30.
     * An end device that implements the Poll Control server can be put into a fast poll mode
     * during which it will send MAC Data Requests to its parent at the frequency of its
     * configured ShortPollInterval attribute. During this period of time, fast polling is
     * considered active. When the device goes into fast poll mode, it is required to send MAC
     * DataRequests to its parent at an accelerated rate and is thus more responsive on the
     * network and can receive data asynchronously from the device implementing the Poll
     * Control Cluster Client.
     */
    public static final int ATTR_FASTPOLLTIMEOUT = 0x0003;
    /**
     * The Poll Control Server may optionally provide its own minimum value for the
     * Check-inInterval to protect against the Check-inInterval being set too low and
     * draining the battery on the end device implementing the Poll Control Server.
     */
    public static final int ATTR_CHECKININTERVALMIN = 0x0004;
    /**
     * The Poll Control Server MAYoptionally provide its own minimum value for the
     * LongPollIntervalto protect against another device setting the value to too short a
     * time resulting in an inadvertent power drain on the device.
     */
    public static final int ATTR_LONGPOLLINTERVALMIN = 0x0005;
    /**
     * The Poll Control Server may optionally provide its own maximum value for the
     * FastPollTimeout to avoid it being set to too high a value resulting in an inadvertent
     * power drain on the device.
     */
    public static final int ATTR_FASTPOLLTIMEOUTMAX = 0x0006;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        attributeMap.put(ATTR_CHECKININTERVAL, new ZclAttribute(this, ATTR_CHECKININTERVAL, "Checkin Interval", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, true, true));
        attributeMap.put(ATTR_LONGPOLLINTERVAL, new ZclAttribute(this, ATTR_LONGPOLLINTERVAL, "Long Poll Interval", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_SHORTPOLLINTERVAL, new ZclAttribute(this, ATTR_SHORTPOLLINTERVAL, "Short Poll Interval", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_FASTPOLLTIMEOUT, new ZclAttribute(this, ATTR_FASTPOLLTIMEOUT, "Fast Poll Timeout", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_CHECKININTERVALMIN, new ZclAttribute(this, ATTR_CHECKININTERVALMIN, "Checkin Interval Min", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_LONGPOLLINTERVALMIN, new ZclAttribute(this, ATTR_LONGPOLLINTERVALMIN, "Long Poll Interval Min", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_FASTPOLLTIMEOUTMAX, new ZclAttribute(this, ATTR_FASTPOLLTIMEOUTMAX, "Fast Poll Timeout Max", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, CheckInCommand.class);

        return commandMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, CheckInResponse.class);
        commandMap.put(0x0001, FastPollStopCommand.class);
        commandMap.put(0x0002, SetLongPollIntervalCommand.class);
        commandMap.put(0x0003, SetShortPollIntervalCommand.class);

        return commandMap;
    }

    /**
     * Default constructor to create a Poll Control cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclPollControlCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Sends a {@link ZclPollControlCommand} and returns the {@link Future} to the result which will complete when the remote
     * device response is received, or the request times out.
     *
     * @param command the {@link ZclPollControlCommand} to send
     * @return the command result future
     */
    public Future<CommandResult> sendCommand(ZclPollControlCommand command) {
        return super.sendCommand(command);
    }

    /**
     * Sends a response to the command. This method sets all the common elements of the response based on the command -
     * eg transactionId, direction, address...
     *
     * @param command the {@link ZclPollControlCommand} to which the response is being sent
     * @param response the {@link ZclPollControlCommand} to send
     */
    public Future<CommandResult> sendResponse(ZclPollControlCommand command, ZclPollControlCommand response) {
        return super.sendResponse(command, response);
    }

    /**
     * Set the <i>Checkin Interval</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The Poll Control server is responsible for checking in with the poll control client
     * periodically to see if the poll control client wants to modify the poll rate of the poll
     * control server. This is due to the fact that the PollControl server is implemented on an
     * end device that may have an unpredictable sleep-wake cycle. The CheckinInterval
     * represents the default amount of time between check-ins by the poll control server with
     * the poll control client. The CheckinInterval is measured in quarter-seconds. A value
     * of 0 indicates that the Poll Control Server is turned off and the poll control server will
     * not check-in with the poll control client. The Poll Control Server checks in with the
     * Poll Control Client by sending a Checkin command to the Client. This value SHOULDbe
     * longer than the LongPoll Interval attribute. If the Client writes an invalid attribute
     * value (Example: Out of Range or a value smaller than the optional
     * Check-inIntervalMinattribute value or a value smaller than the LongPollInterval
     * attribute value), the Server should return Write Attributes Response with an error
     * status not equal to ZCL_SUCCESS. The Poll Control Client will hold onto the actions or
     * messages for the Poll Control Server at the application level until the Poll Control
     * Server checks in with the Poll Control Client.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param checkinInterval the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setCheckinInterval(final Integer value) {
        return write(serverAttributes.get(ATTR_CHECKININTERVAL), value);
    }

    /**
     * Get the <i>Checkin Interval</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The Poll Control server is responsible for checking in with the poll control client
     * periodically to see if the poll control client wants to modify the poll rate of the poll
     * control server. This is due to the fact that the PollControl server is implemented on an
     * end device that may have an unpredictable sleep-wake cycle. The CheckinInterval
     * represents the default amount of time between check-ins by the poll control server with
     * the poll control client. The CheckinInterval is measured in quarter-seconds. A value
     * of 0 indicates that the Poll Control Server is turned off and the poll control server will
     * not check-in with the poll control client. The Poll Control Server checks in with the
     * Poll Control Client by sending a Checkin command to the Client. This value SHOULDbe
     * longer than the LongPoll Interval attribute. If the Client writes an invalid attribute
     * value (Example: Out of Range or a value smaller than the optional
     * Check-inIntervalMinattribute value or a value smaller than the LongPollInterval
     * attribute value), the Server should return Write Attributes Response with an error
     * status not equal to ZCL_SUCCESS. The Poll Control Client will hold onto the actions or
     * messages for the Poll Control Server at the application level until the Poll Control
     * Server checks in with the Poll Control Client.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getCheckinIntervalAsync() {
        return read(serverAttributes.get(ATTR_CHECKININTERVAL));
    }

    /**
     * Synchronously get the <i>Checkin Interval</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The Poll Control server is responsible for checking in with the poll control client
     * periodically to see if the poll control client wants to modify the poll rate of the poll
     * control server. This is due to the fact that the PollControl server is implemented on an
     * end device that may have an unpredictable sleep-wake cycle. The CheckinInterval
     * represents the default amount of time between check-ins by the poll control server with
     * the poll control client. The CheckinInterval is measured in quarter-seconds. A value
     * of 0 indicates that the Poll Control Server is turned off and the poll control server will
     * not check-in with the poll control client. The Poll Control Server checks in with the
     * Poll Control Client by sending a Checkin command to the Client. This value SHOULDbe
     * longer than the LongPoll Interval attribute. If the Client writes an invalid attribute
     * value (Example: Out of Range or a value smaller than the optional
     * Check-inIntervalMinattribute value or a value smaller than the LongPollInterval
     * attribute value), the Server should return Write Attributes Response with an error
     * status not equal to ZCL_SUCCESS. The Poll Control Client will hold onto the actions or
     * messages for the Poll Control Server at the application level until the Poll Control
     * Server checks in with the Poll Control Client.
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
    public Integer getCheckinInterval(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_CHECKININTERVAL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_CHECKININTERVAL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_CHECKININTERVAL));
    }

    /**
     * Set reporting for the <i>Checkin Interval</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The Poll Control server is responsible for checking in with the poll control client
     * periodically to see if the poll control client wants to modify the poll rate of the poll
     * control server. This is due to the fact that the PollControl server is implemented on an
     * end device that may have an unpredictable sleep-wake cycle. The CheckinInterval
     * represents the default amount of time between check-ins by the poll control server with
     * the poll control client. The CheckinInterval is measured in quarter-seconds. A value
     * of 0 indicates that the Poll Control Server is turned off and the poll control server will
     * not check-in with the poll control client. The Poll Control Server checks in with the
     * Poll Control Client by sending a Checkin command to the Client. This value SHOULDbe
     * longer than the LongPoll Interval attribute. If the Client writes an invalid attribute
     * value (Example: Out of Range or a value smaller than the optional
     * Check-inIntervalMinattribute value or a value smaller than the LongPollInterval
     * attribute value), the Server should return Write Attributes Response with an error
     * status not equal to ZCL_SUCCESS. The Poll Control Client will hold onto the actions or
     * messages for the Poll Control Server at the application level until the Poll Control
     * Server checks in with the Poll Control Client.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setCheckinIntervalReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_CHECKININTERVAL), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Long Poll Interval</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * An end device that implements the Poll Control server may optionally expose a
     * LongPollInterval attribute. The Long Poll Interval represents the maximum amount of
     * time in quarter-seconds between MAC Data Requests from the end device to its parent.
     * <p>
     * The LongPollInterval defines the frequency of polling that an end device does when it is
     * NOT in fast poll mode. The LongPollInterval should be longer than the
     * ShortPollInterval attribute but shorter than the CheckinInterval attribute.A value
     * of 0xffffffff is reserved to indicate that the device does not have or does not know its
     * long poll interval
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLongPollIntervalAsync() {
        return read(serverAttributes.get(ATTR_LONGPOLLINTERVAL));
    }

    /**
     * Synchronously get the <i>Long Poll Interval</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * An end device that implements the Poll Control server may optionally expose a
     * LongPollInterval attribute. The Long Poll Interval represents the maximum amount of
     * time in quarter-seconds between MAC Data Requests from the end device to its parent.
     * <p>
     * The LongPollInterval defines the frequency of polling that an end device does when it is
     * NOT in fast poll mode. The LongPollInterval should be longer than the
     * ShortPollInterval attribute but shorter than the CheckinInterval attribute.A value
     * of 0xffffffff is reserved to indicate that the device does not have or does not know its
     * long poll interval
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
    public Integer getLongPollInterval(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LONGPOLLINTERVAL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LONGPOLLINTERVAL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LONGPOLLINTERVAL));
    }

    /**
     * Set reporting for the <i>Long Poll Interval</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * An end device that implements the Poll Control server may optionally expose a
     * LongPollInterval attribute. The Long Poll Interval represents the maximum amount of
     * time in quarter-seconds between MAC Data Requests from the end device to its parent.
     * <p>
     * The LongPollInterval defines the frequency of polling that an end device does when it is
     * NOT in fast poll mode. The LongPollInterval should be longer than the
     * ShortPollInterval attribute but shorter than the CheckinInterval attribute.A value
     * of 0xffffffff is reserved to indicate that the device does not have or does not know its
     * long poll interval
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setLongPollIntervalReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_LONGPOLLINTERVAL), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Short Poll Interval</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * An end device that implements the Poll Control server may optionally expose the
     * ShortPollInterval attribute. The ShortPollIntervalrepresents the number of
     * quarterseconds that an end device waits between MAC Data Requests to its parent when it
     * is expecting data (i.e.,in fast poll mode).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getShortPollIntervalAsync() {
        return read(serverAttributes.get(ATTR_SHORTPOLLINTERVAL));
    }

    /**
     * Synchronously get the <i>Short Poll Interval</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * An end device that implements the Poll Control server may optionally expose the
     * ShortPollInterval attribute. The ShortPollIntervalrepresents the number of
     * quarterseconds that an end device waits between MAC Data Requests to its parent when it
     * is expecting data (i.e.,in fast poll mode).
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
    public Integer getShortPollInterval(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_SHORTPOLLINTERVAL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_SHORTPOLLINTERVAL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_SHORTPOLLINTERVAL));
    }

    /**
     * Set reporting for the <i>Short Poll Interval</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * An end device that implements the Poll Control server may optionally expose the
     * ShortPollInterval attribute. The ShortPollIntervalrepresents the number of
     * quarterseconds that an end device waits between MAC Data Requests to its parent when it
     * is expecting data (i.e.,in fast poll mode).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setShortPollIntervalReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_SHORTPOLLINTERVAL), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Fast Poll Timeout</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The FastPollTimeout attribute represents the number of quarterseconds that an end
     * device will stay in fast poll mode by default. It is suggested that the
     * FastPollTimeoutattribute value be greater than 7.68 seconds.The Poll Control
     * Cluster Client MAYoverride this value by indicating a different value in the Fast Poll
     * Duration argument in the Check-in Response command. If the Client writes a value out of
     * range or greater than the optional FastPollTimeoutMax attribute value if supported,
     * the Server should return a Write Attributes Response with a status of INVALID_VALUE30.
     * An end device that implements the Poll Control server can be put into a fast poll mode
     * during which it will send MAC Data Requests to its parent at the frequency of its
     * configured ShortPollInterval attribute. During this period of time, fast polling is
     * considered active. When the device goes into fast poll mode, it is required to send MAC
     * DataRequests to its parent at an accelerated rate and is thus more responsive on the
     * network and can receive data asynchronously from the device implementing the Poll
     * Control Cluster Client.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getFastPollTimeoutAsync() {
        return read(serverAttributes.get(ATTR_FASTPOLLTIMEOUT));
    }

    /**
     * Synchronously get the <i>Fast Poll Timeout</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The FastPollTimeout attribute represents the number of quarterseconds that an end
     * device will stay in fast poll mode by default. It is suggested that the
     * FastPollTimeoutattribute value be greater than 7.68 seconds.The Poll Control
     * Cluster Client MAYoverride this value by indicating a different value in the Fast Poll
     * Duration argument in the Check-in Response command. If the Client writes a value out of
     * range or greater than the optional FastPollTimeoutMax attribute value if supported,
     * the Server should return a Write Attributes Response with a status of INVALID_VALUE30.
     * An end device that implements the Poll Control server can be put into a fast poll mode
     * during which it will send MAC Data Requests to its parent at the frequency of its
     * configured ShortPollInterval attribute. During this period of time, fast polling is
     * considered active. When the device goes into fast poll mode, it is required to send MAC
     * DataRequests to its parent at an accelerated rate and is thus more responsive on the
     * network and can receive data asynchronously from the device implementing the Poll
     * Control Cluster Client.
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
    public Integer getFastPollTimeout(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_FASTPOLLTIMEOUT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_FASTPOLLTIMEOUT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_FASTPOLLTIMEOUT));
    }

    /**
     * Set reporting for the <i>Fast Poll Timeout</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The FastPollTimeout attribute represents the number of quarterseconds that an end
     * device will stay in fast poll mode by default. It is suggested that the
     * FastPollTimeoutattribute value be greater than 7.68 seconds.The Poll Control
     * Cluster Client MAYoverride this value by indicating a different value in the Fast Poll
     * Duration argument in the Check-in Response command. If the Client writes a value out of
     * range or greater than the optional FastPollTimeoutMax attribute value if supported,
     * the Server should return a Write Attributes Response with a status of INVALID_VALUE30.
     * An end device that implements the Poll Control server can be put into a fast poll mode
     * during which it will send MAC Data Requests to its parent at the frequency of its
     * configured ShortPollInterval attribute. During this period of time, fast polling is
     * considered active. When the device goes into fast poll mode, it is required to send MAC
     * DataRequests to its parent at an accelerated rate and is thus more responsive on the
     * network and can receive data asynchronously from the device implementing the Poll
     * Control Cluster Client.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setFastPollTimeoutReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_FASTPOLLTIMEOUT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Checkin Interval Min</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The Poll Control Server may optionally provide its own minimum value for the
     * Check-inInterval to protect against the Check-inInterval being set too low and
     * draining the battery on the end device implementing the Poll Control Server.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getCheckinIntervalMinAsync() {
        return read(serverAttributes.get(ATTR_CHECKININTERVALMIN));
    }

    /**
     * Synchronously get the <i>Checkin Interval Min</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The Poll Control Server may optionally provide its own minimum value for the
     * Check-inInterval to protect against the Check-inInterval being set too low and
     * draining the battery on the end device implementing the Poll Control Server.
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
    public Integer getCheckinIntervalMin(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_CHECKININTERVALMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_CHECKININTERVALMIN).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_CHECKININTERVALMIN));
    }

    /**
     * Set reporting for the <i>Checkin Interval Min</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The Poll Control Server may optionally provide its own minimum value for the
     * Check-inInterval to protect against the Check-inInterval being set too low and
     * draining the battery on the end device implementing the Poll Control Server.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setCheckinIntervalMinReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_CHECKININTERVALMIN), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Long Poll Interval Min</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The Poll Control Server MAYoptionally provide its own minimum value for the
     * LongPollIntervalto protect against another device setting the value to too short a
     * time resulting in an inadvertent power drain on the device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLongPollIntervalMinAsync() {
        return read(serverAttributes.get(ATTR_LONGPOLLINTERVALMIN));
    }

    /**
     * Synchronously get the <i>Long Poll Interval Min</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The Poll Control Server MAYoptionally provide its own minimum value for the
     * LongPollIntervalto protect against another device setting the value to too short a
     * time resulting in an inadvertent power drain on the device.
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
    public Integer getLongPollIntervalMin(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LONGPOLLINTERVALMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_LONGPOLLINTERVALMIN).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_LONGPOLLINTERVALMIN));
    }

    /**
     * Set reporting for the <i>Long Poll Interval Min</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The Poll Control Server MAYoptionally provide its own minimum value for the
     * LongPollIntervalto protect against another device setting the value to too short a
     * time resulting in an inadvertent power drain on the device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setLongPollIntervalMinReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_LONGPOLLINTERVALMIN), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Fast Poll Timeout Max</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The Poll Control Server may optionally provide its own maximum value for the
     * FastPollTimeout to avoid it being set to too high a value resulting in an inadvertent
     * power drain on the device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getFastPollTimeoutMaxAsync() {
        return read(serverAttributes.get(ATTR_FASTPOLLTIMEOUTMAX));
    }

    /**
     * Synchronously get the <i>Fast Poll Timeout Max</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The Poll Control Server may optionally provide its own maximum value for the
     * FastPollTimeout to avoid it being set to too high a value resulting in an inadvertent
     * power drain on the device.
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
    public Integer getFastPollTimeoutMax(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_FASTPOLLTIMEOUTMAX).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_FASTPOLLTIMEOUTMAX).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_FASTPOLLTIMEOUTMAX));
    }

    /**
     * Set reporting for the <i>Fast Poll Timeout Max</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The Poll Control Server may optionally provide its own maximum value for the
     * FastPollTimeout to avoid it being set to too high a value resulting in an inadvertent
     * power drain on the device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setFastPollTimeoutMaxReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_FASTPOLLTIMEOUTMAX), minInterval, maxInterval, reportableChange);
    }

    /**
     * The Check In Response
     * <p>
     * The Check-in Response is sent in response to the receipt of a Check-in command. The
     * Check-in Response is used by the Poll Control Client to indicate whether it would like
     * the device implementing the Poll Control Cluster Server to go into a fast poll mode and
     * for how long. If the Poll Control Cluster Client indicates that it would like the device
     * to go into a fast poll mode, it is responsible for telling the device to stop fast polling
     * when it is done sending messages to the fast polling device. <br> If the Poll Control
     * Server receives a Check-In Response from a client for which there is no binding
     * (unbound), it should respond with a Default Response with a status value indicating
     * ACTION_DENIED. <br> If the Poll Control Server receives a Check-In Response from a
     * client for which there is a binding (bound) with an invalid fast poll interval it should
     * respond with a Default Response with status INVALID_VALUE. <br> If the Poll Control
     * Server receives a Check-In Response from a bound client after temporary fast poll mode
     * is completed it should respond with a Default Response with a status value indicating
     * TIMEOUT. <br> In all of the above cases, the Server shall respond with a Default Response
     * not equal to ZCL_SUCCESS.
     *
     * @param startFastPolling {@link Boolean} Start Fast Polling
     * @param fastPollTimeout {@link Integer} Fast Poll Timeout
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.checkInResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new checkInResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> checkInResponse(Boolean startFastPolling, Integer fastPollTimeout) {
        CheckInResponse command = new CheckInResponse();

        // Set the fields
        command.setStartFastPolling(startFastPolling);
        command.setFastPollTimeout(fastPollTimeout);

        return sendCommand(command);
    }

    /**
     * The Fast Poll Stop Command
     * <p>
     * The Fast Poll Stop command is used to stop the fast poll mode initiated by the Check-in
     * response. The Fast Poll Stop command has no payload. <br> If the Poll Control Server
     * receives a Fast Poll Stop from an unbound client it should send back a DefaultResponse
     * with a value field indicating “ACTION_DENIED” . The Server shall respond with a
     * DefaultResponse not equal to ZCL_SUCCESS. <br> If the Poll Control Server receives a
     * Fast Poll Stop command from a bound client but it is unable to stop fast polling due to the
     * fact that there is another bound client which has requested that polling continue it
     * should respond with a Default Response with a status of “ACTION_DENIED” <br> If a Poll
     * Control Server receives a Fast Poll Stop command from a bound client but it is not
     * FastPolling it should respond with a Default Response with a status of ACTION_DENIED.
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.fastPollStopCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new fastPollStopCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> fastPollStopCommand() {
        return sendCommand(new FastPollStopCommand());
    }

    /**
     * The Set Long Poll Interval Command
     * <p>
     * The Set Long Poll Interval command is used to set the Read Only LongPollInterval
     * attribute. <br> When the Poll Control Server receives the Set Long Poll Interval
     * Command, it should check its internal minimal limit and the attributes relationship if
     * the new Long Poll Interval is acceptable. If the new value is acceptable, the new value
     * shall be saved to the LongPollInterval attribute. If the new value is not acceptable,
     * the Poll Control Server shall send a default response of INVALID_VALUE and the
     * LongPollInterval attribute value is not updated.
     *
     * @param newLongPollInterval {@link Integer} New Long Poll Interval
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setLongPollIntervalCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new setLongPollIntervalCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setLongPollIntervalCommand(Integer newLongPollInterval) {
        SetLongPollIntervalCommand command = new SetLongPollIntervalCommand();

        // Set the fields
        command.setNewLongPollInterval(newLongPollInterval);

        return sendCommand(command);
    }

    /**
     * The Set Short Poll Interval Command
     * <p>
     * The Set Short Poll Interval command is used to set the Read Only ShortPollInterval
     * attribute. <br> When the Poll Control Server receives the Set Short Poll Interval
     * Command, it should check its internal minimal limit and the attributes relationship if
     * the new Short Poll Interval is acceptable. If the new value is acceptable, the new value
     * shall be saved to the ShortPollInterval attribute. If the new value is not acceptable,
     * the Poll Control Server shall send a default response of INVALID_VALUE and the
     * ShortPollInterval attribute value is not updated.
     *
     * @param newShortPollInterval {@link Integer} New Short Poll Interval
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.setShortPollIntervalCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new setShortPollIntervalCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> setShortPollIntervalCommand(Integer newShortPollInterval) {
        SetShortPollIntervalCommand command = new SetShortPollIntervalCommand();

        // Set the fields
        command.setNewShortPollInterval(newShortPollInterval);

        return sendCommand(command);
    }

    /**
     * The Check In Command
     * <p>
     * The Poll Control Cluster server sends out a Check-in command to the devices to which it is
     * paired based on the server’s Check-inInterval attribute. It does this to find out if any
     * of the Poll Control Cluster Clients with which it is paired are interested in having it
     * enter fast poll mode so that it can be managed. This request is sent out based on either the
     * Check-inInterval, or the next Check-in value in the Fast Poll Stop Request generated by
     * the Poll Control Cluster Client. <br> The Check-in command expects a Check-in Response
     * command to be sent back from the Poll Control Client. If the Poll Control Server does not
     * receive a Check-in response back from the Poll Control Client up to 7.68 seconds it is
     * free to return to polling according to the LongPollInterval.
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.checkInCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new checkInCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> checkInCommand() {
        return sendCommand(new CheckInCommand());
    }
}
