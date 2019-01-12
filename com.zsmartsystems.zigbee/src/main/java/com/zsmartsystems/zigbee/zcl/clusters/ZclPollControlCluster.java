/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

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
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import javax.annotation.Generated;

/**
 * <b>Poll Control</b> cluster implementation (<i>Cluster ID 0x0020</i>).
 * <p>
 * This cluster provides a mechanism for the management of an end device’s MAC Data Request rate.
 * For the purposes of this cluster, the term “poll” always refers to the sending of a MAC Data
 * Request from the end device to the end device’s parent. This cluster can be used for instance
 * by a configuration device to make an end device responsive for a certain period of time so that
 * the device can be managed by the controller. This cluster is composed of a client and server. The end device implements the server side of this
 * cluster. The server side contains several attributes related to the MAC Data Request rate for the device. The client side implements
 * commands used to manage the poll rate for the device. The end device which implements the server side of this cluster sends a query to the
 * client on a predetermined interval to see if the client would like to manage the poll period of the end device in question. When the client side
 * of the cluster hears from the server it has the opportunity to respond with configuration data to either put the end device in a short poll mode
 * or let the end device continue to function normally.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-10-24T19:40:52Z")
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
     * The Poll Control server is responsible for checking in with the poll control client periodically to see if the poll control  client wants to
     * modify the poll rate of the poll control server.  This is due to the fact that  the  PollControl server is implemented on an end device that MAY
     * have an unpredictable sleep-wake cycle. The CheckinInterval represents the default amount of time between check-ins by the poll control
     * server with the poll control client. The CheckinInterval is measured in quarter-seconds. A value of 0 indicates that the Poll Control
     * Server is turned off and the poll control server will not check-in with the poll control client. The Poll Control Server checks in with the
     * Poll Control Client by sending a Checkin command to the Client. This value SHOULDbe longer than the LongPoll Interval attribute. If the
     * Client writes an invalid attribute value (Example: Out of Range or a value smaller than the optional Check-inIntervalMinattribute value
     * or a value smaller than the LongPollInterval attribute value), the Server SHOULD return Write Attributes Response with an error status not
     * equal to ZCL_SUCCESS. The Poll Control Client will hold onto the actions or messages for the Poll Control Server at the application level
     * until the Poll Control Server checks in with the Poll Control Client.
     */
    public static final int ATTR_CHECKININTERVAL = 0x0000;
    /**
     * An end device that implements the Poll Control server MAY optionally expose a LongPollInterval attribute.
     * The Long Poll Interval represents the maximum amount of time in quarter-seconds between MAC Data Requests
     * from the end device to its parent.
     * <p>
     * The LongPollInterval defines the frequency of polling that an end device does when it is NOT in fast poll mode.  The LongPollInterval SHOULD
     * be longer than the ShortPollInterval attribute but shorter than the CheckinInterval attribute.A  value of 0xffffffff is reserved to
     * indicate that the device does not have or does not know its long poll interval
     */
    public static final int ATTR_LONGPOLLINTERVAL = 0x0001;
    /**
     * An  end  device  that  implements  the  Poll  Control  server MAY optionally  expose the ShortPollInterval attribute.  The
     * ShortPollIntervalrepresents  the  number  of  quarterseconds  that  an  end  device  waits  between MAC Data Requests to its parent when it is
     * expecting data (i.e.,in fast poll mode).
     */
    public static final int ATTR_SHORTPOLLINTERVAL = 0x0002;
    /**
     * The FastPollTimeout attribute represents the number of quarterseconds that an end device will stay in fast poll mode by default. It is
     * suggested that the FastPollTimeoutattribute value be greater than 7.68 seconds.The Poll Control Cluster  Client MAYoverride  this  value 
     * by  indicating  a  different  value  in  the  Fast  Poll Duration argument in the Check-in Response command. If the Client writes a value out of range
     * or greater  than  the  optional FastPollTimeoutMax attribute  value  if  supported, the Server SHOULD return a  Write  Attributes  Response with a
     * status of  INVALID_VALUE30.  An  end  device  that implements the  Poll Control server can be  put into a  fast poll  mode during  which it will send MAC
     * Data Requests  to  its  parent  at  the  frequency  of  its  configured ShortPollInterval attribute.  During this  period  of time, fast polling is
     * considered active. When the device goes into fast poll mode, it is required to send MAC DataRequests to its parent at an accelerated rate and
     * is thus more responsive on the network and can receive data asynchronously from the device implementing the Poll Control Cluster Client.
     */
    public static final int ATTR_FASTPOLLTIMEOUT = 0x0003;
    /**
     * The Poll Control Server MAY optionally provide its own minimum value for the Check-inInterval to protect against the Check-inInterval
     * being set too low and draining the battery on the end device implementing the Poll Control Server.
     */
    public static final int ATTR_CHECKININTERVALMIN = 0x0004;
    /**
     * The Poll Control Server MAYoptionally provide its own minimum value for the LongPollIntervalto protect against  another  device  setting 
     * the  value  to  too  short  a  time  resulting  in  an  inadvertent  power  drain  on  the device.
     */
    public static final int ATTR_LONGPOLLINTERVALMIN = 0x0005;
    /**
     * The Poll Control Server MAY optionally provide its own maximum value for the FastPollTimeout to avoid it being set to too high a value
     * resulting in an inadvertent power drain on the device.
     */
    public static final int ATTR_FASTPOLLTIMEOUTMIN = 0x0006;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(7);

        attributeMap.put(ATTR_CHECKININTERVAL, new ZclAttribute(ZclClusterType.POLL_CONTROL, ATTR_CHECKININTERVAL, "CheckinInterval", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, true, true));
        attributeMap.put(ATTR_LONGPOLLINTERVAL, new ZclAttribute(ZclClusterType.POLL_CONTROL, ATTR_LONGPOLLINTERVAL, "LongPollInterval", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_SHORTPOLLINTERVAL, new ZclAttribute(ZclClusterType.POLL_CONTROL, ATTR_SHORTPOLLINTERVAL, "ShortPollInterval", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_FASTPOLLTIMEOUT, new ZclAttribute(ZclClusterType.POLL_CONTROL, ATTR_FASTPOLLTIMEOUT, "FastPollTimeout", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_CHECKININTERVALMIN, new ZclAttribute(ZclClusterType.POLL_CONTROL, ATTR_CHECKININTERVALMIN, "CheckinIntervalMin", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_LONGPOLLINTERVALMIN, new ZclAttribute(ZclClusterType.POLL_CONTROL, ATTR_LONGPOLLINTERVALMIN, "LongPollIntervalMin", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_FASTPOLLTIMEOUTMIN, new ZclAttribute(ZclClusterType.POLL_CONTROL, ATTR_FASTPOLLTIMEOUTMIN, "FastPollTimeoutMin", ZclDataType.UNSIGNED_32_BIT_INTEGER, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Poll Control cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclPollControlCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Set the <i>CheckinInterval</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The Poll Control server is responsible for checking in with the poll control client periodically to see if the poll control  client wants to
     * modify the poll rate of the poll control server.  This is due to the fact that  the  PollControl server is implemented on an end device that MAY
     * have an unpredictable sleep-wake cycle. The CheckinInterval represents the default amount of time between check-ins by the poll control
     * server with the poll control client. The CheckinInterval is measured in quarter-seconds. A value of 0 indicates that the Poll Control
     * Server is turned off and the poll control server will not check-in with the poll control client. The Poll Control Server checks in with the
     * Poll Control Client by sending a Checkin command to the Client. This value SHOULDbe longer than the LongPoll Interval attribute. If the
     * Client writes an invalid attribute value (Example: Out of Range or a value smaller than the optional Check-inIntervalMinattribute value
     * or a value smaller than the LongPollInterval attribute value), the Server SHOULD return Write Attributes Response with an error status not
     * equal to ZCL_SUCCESS. The Poll Control Client will hold onto the actions or messages for the Poll Control Server at the application level
     * until the Poll Control Server checks in with the Poll Control Client.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param checkinInterval the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCheckinInterval(final Object value) {
        return write(attributes.get(ATTR_CHECKININTERVAL), value);
    }

    /**
     * Get the <i>CheckinInterval</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The Poll Control server is responsible for checking in with the poll control client periodically to see if the poll control  client wants to
     * modify the poll rate of the poll control server.  This is due to the fact that  the  PollControl server is implemented on an end device that MAY
     * have an unpredictable sleep-wake cycle. The CheckinInterval represents the default amount of time between check-ins by the poll control
     * server with the poll control client. The CheckinInterval is measured in quarter-seconds. A value of 0 indicates that the Poll Control
     * Server is turned off and the poll control server will not check-in with the poll control client. The Poll Control Server checks in with the
     * Poll Control Client by sending a Checkin command to the Client. This value SHOULDbe longer than the LongPoll Interval attribute. If the
     * Client writes an invalid attribute value (Example: Out of Range or a value smaller than the optional Check-inIntervalMinattribute value
     * or a value smaller than the LongPollInterval attribute value), the Server SHOULD return Write Attributes Response with an error status not
     * equal to ZCL_SUCCESS. The Poll Control Client will hold onto the actions or messages for the Poll Control Server at the application level
     * until the Poll Control Server checks in with the Poll Control Client.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCheckinIntervalAsync() {
        return read(attributes.get(ATTR_CHECKININTERVAL));
    }

    /**
     * Synchronously get the <i>CheckinInterval</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The Poll Control server is responsible for checking in with the poll control client periodically to see if the poll control  client wants to
     * modify the poll rate of the poll control server.  This is due to the fact that  the  PollControl server is implemented on an end device that MAY
     * have an unpredictable sleep-wake cycle. The CheckinInterval represents the default amount of time between check-ins by the poll control
     * server with the poll control client. The CheckinInterval is measured in quarter-seconds. A value of 0 indicates that the Poll Control
     * Server is turned off and the poll control server will not check-in with the poll control client. The Poll Control Server checks in with the
     * Poll Control Client by sending a Checkin command to the Client. This value SHOULDbe longer than the LongPoll Interval attribute. If the
     * Client writes an invalid attribute value (Example: Out of Range or a value smaller than the optional Check-inIntervalMinattribute value
     * or a value smaller than the LongPollInterval attribute value), the Server SHOULD return Write Attributes Response with an error status not
     * equal to ZCL_SUCCESS. The Poll Control Client will hold onto the actions or messages for the Poll Control Server at the application level
     * until the Poll Control Server checks in with the Poll Control Client.
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
    public Integer getCheckinInterval(final long refreshPeriod) {
        if (attributes.get(ATTR_CHECKININTERVAL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CHECKININTERVAL).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CHECKININTERVAL));
    }

    /**
     * Set reporting for the <i>CheckinInterval</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The Poll Control server is responsible for checking in with the poll control client periodically to see if the poll control  client wants to
     * modify the poll rate of the poll control server.  This is due to the fact that  the  PollControl server is implemented on an end device that MAY
     * have an unpredictable sleep-wake cycle. The CheckinInterval represents the default amount of time between check-ins by the poll control
     * server with the poll control client. The CheckinInterval is measured in quarter-seconds. A value of 0 indicates that the Poll Control
     * Server is turned off and the poll control server will not check-in with the poll control client. The Poll Control Server checks in with the
     * Poll Control Client by sending a Checkin command to the Client. This value SHOULDbe longer than the LongPoll Interval attribute. If the
     * Client writes an invalid attribute value (Example: Out of Range or a value smaller than the optional Check-inIntervalMinattribute value
     * or a value smaller than the LongPollInterval attribute value), the Server SHOULD return Write Attributes Response with an error status not
     * equal to ZCL_SUCCESS. The Poll Control Client will hold onto the actions or messages for the Poll Control Server at the application level
     * until the Poll Control Server checks in with the Poll Control Client.
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
    public Future<CommandResult> setCheckinIntervalReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CHECKININTERVAL), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>LongPollInterval</i> attribute [attribute ID <b>1</b>].
     * <p>
     * An end device that implements the Poll Control server MAY optionally expose a LongPollInterval attribute.
     * The Long Poll Interval represents the maximum amount of time in quarter-seconds between MAC Data Requests
     * from the end device to its parent.
     * <p>
     * The LongPollInterval defines the frequency of polling that an end device does when it is NOT in fast poll mode.  The LongPollInterval SHOULD
     * be longer than the ShortPollInterval attribute but shorter than the CheckinInterval attribute.A  value of 0xffffffff is reserved to
     * indicate that the device does not have or does not know its long poll interval
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLongPollIntervalAsync() {
        return read(attributes.get(ATTR_LONGPOLLINTERVAL));
    }

    /**
     * Synchronously get the <i>LongPollInterval</i> attribute [attribute ID <b>1</b>].
     * <p>
     * An end device that implements the Poll Control server MAY optionally expose a LongPollInterval attribute.
     * The Long Poll Interval represents the maximum amount of time in quarter-seconds between MAC Data Requests
     * from the end device to its parent.
     * <p>
     * The LongPollInterval defines the frequency of polling that an end device does when it is NOT in fast poll mode.  The LongPollInterval SHOULD
     * be longer than the ShortPollInterval attribute but shorter than the CheckinInterval attribute.A  value of 0xffffffff is reserved to
     * indicate that the device does not have or does not know its long poll interval
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
    public Integer getLongPollInterval(final long refreshPeriod) {
        if (attributes.get(ATTR_LONGPOLLINTERVAL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_LONGPOLLINTERVAL).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_LONGPOLLINTERVAL));
    }

    /**
     * Set reporting for the <i>LongPollInterval</i> attribute [attribute ID <b>1</b>].
     * <p>
     * An end device that implements the Poll Control server MAY optionally expose a LongPollInterval attribute.
     * The Long Poll Interval represents the maximum amount of time in quarter-seconds between MAC Data Requests
     * from the end device to its parent.
     * <p>
     * The LongPollInterval defines the frequency of polling that an end device does when it is NOT in fast poll mode.  The LongPollInterval SHOULD
     * be longer than the ShortPollInterval attribute but shorter than the CheckinInterval attribute.A  value of 0xffffffff is reserved to
     * indicate that the device does not have or does not know its long poll interval
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
    public Future<CommandResult> setLongPollIntervalReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_LONGPOLLINTERVAL), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>ShortPollInterval</i> attribute [attribute ID <b>2</b>].
     * <p>
     * An  end  device  that  implements  the  Poll  Control  server MAY optionally  expose the ShortPollInterval attribute.  The
     * ShortPollIntervalrepresents  the  number  of  quarterseconds  that  an  end  device  waits  between MAC Data Requests to its parent when it is
     * expecting data (i.e.,in fast poll mode).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getShortPollIntervalAsync() {
        return read(attributes.get(ATTR_SHORTPOLLINTERVAL));
    }

    /**
     * Synchronously get the <i>ShortPollInterval</i> attribute [attribute ID <b>2</b>].
     * <p>
     * An  end  device  that  implements  the  Poll  Control  server MAY optionally  expose the ShortPollInterval attribute.  The
     * ShortPollIntervalrepresents  the  number  of  quarterseconds  that  an  end  device  waits  between MAC Data Requests to its parent when it is
     * expecting data (i.e.,in fast poll mode).
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
    public Integer getShortPollInterval(final long refreshPeriod) {
        if (attributes.get(ATTR_SHORTPOLLINTERVAL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_SHORTPOLLINTERVAL).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_SHORTPOLLINTERVAL));
    }

    /**
     * Set reporting for the <i>ShortPollInterval</i> attribute [attribute ID <b>2</b>].
     * <p>
     * An  end  device  that  implements  the  Poll  Control  server MAY optionally  expose the ShortPollInterval attribute.  The
     * ShortPollIntervalrepresents  the  number  of  quarterseconds  that  an  end  device  waits  between MAC Data Requests to its parent when it is
     * expecting data (i.e.,in fast poll mode).
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
    public Future<CommandResult> setShortPollIntervalReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_SHORTPOLLINTERVAL), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>FastPollTimeout</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The FastPollTimeout attribute represents the number of quarterseconds that an end device will stay in fast poll mode by default. It is
     * suggested that the FastPollTimeoutattribute value be greater than 7.68 seconds.The Poll Control Cluster  Client MAYoverride  this  value 
     * by  indicating  a  different  value  in  the  Fast  Poll Duration argument in the Check-in Response command. If the Client writes a value out of range
     * or greater  than  the  optional FastPollTimeoutMax attribute  value  if  supported, the Server SHOULD return a  Write  Attributes  Response with a
     * status of  INVALID_VALUE30.  An  end  device  that implements the  Poll Control server can be  put into a  fast poll  mode during  which it will send MAC
     * Data Requests  to  its  parent  at  the  frequency  of  its  configured ShortPollInterval attribute.  During this  period  of time, fast polling is
     * considered active. When the device goes into fast poll mode, it is required to send MAC DataRequests to its parent at an accelerated rate and
     * is thus more responsive on the network and can receive data asynchronously from the device implementing the Poll Control Cluster Client.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getFastPollTimeoutAsync() {
        return read(attributes.get(ATTR_FASTPOLLTIMEOUT));
    }

    /**
     * Synchronously get the <i>FastPollTimeout</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The FastPollTimeout attribute represents the number of quarterseconds that an end device will stay in fast poll mode by default. It is
     * suggested that the FastPollTimeoutattribute value be greater than 7.68 seconds.The Poll Control Cluster  Client MAYoverride  this  value 
     * by  indicating  a  different  value  in  the  Fast  Poll Duration argument in the Check-in Response command. If the Client writes a value out of range
     * or greater  than  the  optional FastPollTimeoutMax attribute  value  if  supported, the Server SHOULD return a  Write  Attributes  Response with a
     * status of  INVALID_VALUE30.  An  end  device  that implements the  Poll Control server can be  put into a  fast poll  mode during  which it will send MAC
     * Data Requests  to  its  parent  at  the  frequency  of  its  configured ShortPollInterval attribute.  During this  period  of time, fast polling is
     * considered active. When the device goes into fast poll mode, it is required to send MAC DataRequests to its parent at an accelerated rate and
     * is thus more responsive on the network and can receive data asynchronously from the device implementing the Poll Control Cluster Client.
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
    public Integer getFastPollTimeout(final long refreshPeriod) {
        if (attributes.get(ATTR_FASTPOLLTIMEOUT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_FASTPOLLTIMEOUT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_FASTPOLLTIMEOUT));
    }

    /**
     * Set reporting for the <i>FastPollTimeout</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The FastPollTimeout attribute represents the number of quarterseconds that an end device will stay in fast poll mode by default. It is
     * suggested that the FastPollTimeoutattribute value be greater than 7.68 seconds.The Poll Control Cluster  Client MAYoverride  this  value 
     * by  indicating  a  different  value  in  the  Fast  Poll Duration argument in the Check-in Response command. If the Client writes a value out of range
     * or greater  than  the  optional FastPollTimeoutMax attribute  value  if  supported, the Server SHOULD return a  Write  Attributes  Response with a
     * status of  INVALID_VALUE30.  An  end  device  that implements the  Poll Control server can be  put into a  fast poll  mode during  which it will send MAC
     * Data Requests  to  its  parent  at  the  frequency  of  its  configured ShortPollInterval attribute.  During this  period  of time, fast polling is
     * considered active. When the device goes into fast poll mode, it is required to send MAC DataRequests to its parent at an accelerated rate and
     * is thus more responsive on the network and can receive data asynchronously from the device implementing the Poll Control Cluster Client.
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
    public Future<CommandResult> setFastPollTimeoutReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_FASTPOLLTIMEOUT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>CheckinIntervalMin</i> attribute [attribute ID <b>4</b>].
     * <p>
     * The Poll Control Server MAY optionally provide its own minimum value for the Check-inInterval to protect against the Check-inInterval
     * being set too low and draining the battery on the end device implementing the Poll Control Server.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is 
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCheckinIntervalMinAsync() {
        return read(attributes.get(ATTR_CHECKININTERVALMIN));
    }

    /**
     * Synchronously get the <i>CheckinIntervalMin</i> attribute [attribute ID <b>4</b>].
     * <p>
     * The Poll Control Server MAY optionally provide its own minimum value for the Check-inInterval to protect against the Check-inInterval
     * being set too low and draining the battery on the end device implementing the Poll Control Server.
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
     * The implementation of this attribute by a device is 
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getCheckinIntervalMin(final long refreshPeriod) {
        if (attributes.get(ATTR_CHECKININTERVALMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CHECKININTERVALMIN).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CHECKININTERVALMIN));
    }

    /**
     * Get the <i>LongPollIntervalMin</i> attribute [attribute ID <b>5</b>].
     * <p>
     * The Poll Control Server MAYoptionally provide its own minimum value for the LongPollIntervalto protect against  another  device  setting 
     * the  value  to  too  short  a  time  resulting  in  an  inadvertent  power  drain  on  the device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is 
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLongPollIntervalMinAsync() {
        return read(attributes.get(ATTR_LONGPOLLINTERVALMIN));
    }

    /**
     * Synchronously get the <i>LongPollIntervalMin</i> attribute [attribute ID <b>5</b>].
     * <p>
     * The Poll Control Server MAYoptionally provide its own minimum value for the LongPollIntervalto protect against  another  device  setting 
     * the  value  to  too  short  a  time  resulting  in  an  inadvertent  power  drain  on  the device.
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
     * The implementation of this attribute by a device is 
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getLongPollIntervalMin(final long refreshPeriod) {
        if (attributes.get(ATTR_LONGPOLLINTERVALMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_LONGPOLLINTERVALMIN).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_LONGPOLLINTERVALMIN));
    }

    /**
     * Get the <i>FastPollTimeoutMin</i> attribute [attribute ID <b>6</b>].
     * <p>
     * The Poll Control Server MAY optionally provide its own maximum value for the FastPollTimeout to avoid it being set to too high a value
     * resulting in an inadvertent power drain on the device.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is 
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getFastPollTimeoutMinAsync() {
        return read(attributes.get(ATTR_FASTPOLLTIMEOUTMIN));
    }

    /**
     * Synchronously get the <i>FastPollTimeoutMin</i> attribute [attribute ID <b>6</b>].
     * <p>
     * The Poll Control Server MAY optionally provide its own maximum value for the FastPollTimeout to avoid it being set to too high a value
     * resulting in an inadvertent power drain on the device.
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
     * The implementation of this attribute by a device is 
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getFastPollTimeoutMin(final long refreshPeriod) {
        if (attributes.get(ATTR_FASTPOLLTIMEOUTMIN).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_FASTPOLLTIMEOUTMIN).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_FASTPOLLTIMEOUTMIN));
    }

    /**
     * The Check In Response
     * <p>
     * The Check-in Response is sent in response to the receipt of a Check-in command. The Check-in Response is used by the Poll Control Client to
     * indicate whether it would like the device implementing the Poll Control Cluster Server to go into a fast poll mode and for how long. If the Poll
     * Control Cluster Client indicates that it would like the device to go into a fast poll mode, it is responsible for telling the device to stop
     * fast polling when it is done sending messages to the fast polling device.
     * <br>
     * If the Poll Control Server receives a Check-In Response from a client for which there is no binding (unbound), it SHOULD respond with a
     * Default Response with a status value indicating ACTION_DENIED.
     * <br>
     * If the Poll Control Server receives a Check-In Response from a client for which there is a binding (bound) with an invalid fast poll interval
     * it SHOULD respond with a Default Response with status INVALID_VALUE.
     * <br>
     * If the Poll Control Server receives a Check-In Response from a bound client after temporary fast poll mode is completed it SHOULD respond
     * with a Default Response with a status value indicating TIMEOUT.
     * <br>
     * In all of the above cases, the Server SHALL respond with a Default Response not equal to ZCL_SUCCESS.
     *
     * @param startFastPolling {@link Boolean} Start Fast Polling
     * @param fastPollTimeout {@link Integer} Fast Poll Timeout
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> checkInResponse(Boolean startFastPolling, Integer fastPollTimeout) {
        CheckInResponse command = new CheckInResponse();

        // Set the fields
        command.setStartFastPolling(startFastPolling);
        command.setFastPollTimeout(fastPollTimeout);

        return send(command);
    }

    /**
     * The Fast Poll Stop Command
     * <p>
     * The Fast Poll Stop command is used to stop the fast poll mode initiated by the Check-in response. The Fast Poll Stop command has no payload.
     * <br>
     * If the Poll Control Server receives a Fast Poll Stop from an unbound client it SHOULD send back a DefaultResponse with a value field
     * indicating “ACTION_DENIED” . The Server SHALL respond with a DefaultResponse not equal to ZCL_SUCCESS.
     * <br>
     * If the Poll Control Server receives a Fast Poll Stop command from a bound client but it is unable to stop fast polling due to the fact that there
     * is another bound client which has requested that polling continue it SHOULD respond with a Default Response with a status of
     * “ACTION_DENIED”
     * <br>
     * If a Poll Control Server receives a Fast Poll Stop command from a bound client but it is not FastPolling it SHOULD respond with a Default
     * Response with a status of ACTION_DENIED.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> fastPollStopCommand() {
        FastPollStopCommand command = new FastPollStopCommand();

        return send(command);
    }

    /**
     * The Set Long Poll Interval Command
     * <p>
     * The Set Long Poll Interval command is used to set the Read Only LongPollInterval attribute.
     * <br>
     * When the Poll Control Server receives the Set Long Poll Interval Command, it SHOULD check its internal minimal limit and the attributes
     * relationship if the new Long Poll Interval is acceptable. If the new value is acceptable, the new value SHALL be saved to the
     * LongPollInterval attribute. If the new value is not acceptable, the Poll Control Server SHALL send a default response of INVALID_VALUE and
     * the LongPollInterval attribute value is not updated.
     *
     * @param newLongPollInterval {@link Integer} New Long Poll Interval
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setLongPollIntervalCommand(Integer newLongPollInterval) {
        SetLongPollIntervalCommand command = new SetLongPollIntervalCommand();

        // Set the fields
        command.setNewLongPollInterval(newLongPollInterval);

        return send(command);
    }

    /**
     * The Set Short Poll Interval Command
     * <p>
     * The Set Short Poll Interval command is used to set the Read Only ShortPollInterval attribute.
     * <br>
     * When the Poll Control Server receives the Set Short Poll Interval Command, it SHOULD check its internal minimal limit and the attributes
     * relationship if the new Short Poll Interval is acceptable. If the new value is acceptable, the new value SHALL be saved to the
     * ShortPollInterval attribute. If the new value is not acceptable, the Poll Control Server SHALL send a default response of INVALID_VALUE
     * and the ShortPollInterval attribute value is not updated.
     *
     * @param newShortPollInterval {@link Integer} New Short Poll Interval
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setShortPollIntervalCommand(Integer newShortPollInterval) {
        SetShortPollIntervalCommand command = new SetShortPollIntervalCommand();

        // Set the fields
        command.setNewShortPollInterval(newShortPollInterval);

        return send(command);
    }

    /**
     * The Check In Command
     * <p>
     * The Poll Control Cluster server sends out a Check-in command to the devices to which it is paired based on the server’s Check-inInterval
     * attribute. It does this to find out if any of the Poll Control Cluster Clients with which it is paired are interested in having it enter fast
     * poll mode so that it can be managed. This request is sent out based on either the Check-inInterval, or the next Check-in value in the Fast Poll
     * Stop Request generated by the Poll Control Cluster Client.
     * <br>
     * The Check-in command expects a Check-in Response command to be sent back from the Poll Control Client. If the Poll Control Server does not
     * receive a Check-in response back from the Poll Control Client up to 7.68 seconds it is free to return to polling according to the
     * LongPollInterval.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> checkInCommand() {
        CheckInCommand command = new CheckInCommand();

        return send(command);
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // CHECK_IN_RESPONSE
                return new CheckInResponse();
            case 1: // FAST_POLL_STOP_COMMAND
                return new FastPollStopCommand();
            case 2: // SET_LONG_POLL_INTERVAL_COMMAND
                return new SetLongPollIntervalCommand();
            case 3: // SET_SHORT_POLL_INTERVAL_COMMAND
                return new SetShortPollIntervalCommand();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 0: // CHECK_IN_COMMAND
                return new CheckInCommand();
            default:
                return null;
        }
    }
}
