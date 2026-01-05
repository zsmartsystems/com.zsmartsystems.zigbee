/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zcl.clusters.iaswd.Squawk;
import com.zsmartsystems.zigbee.zcl.clusters.iaswd.StartWarningCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaswd.ZclIasWdCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>IAS WD</b> cluster implementation (<i>Cluster ID 0x0502</i>).
 * <p>
 * The IAS WD cluster provides an interface to the functionality of any Warning Device
 * equipment of the IAS system. Using this cluster, a ZigBee enabled CIE device can access a
 * ZigBee enabled IAS WD device and issue alarm warning indications (siren, strobe lighting,
 * etc.) when a system alarm condition is detected.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T20:27:57Z")
public class ZclIasWdCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0502;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "IAS WD";

    // Attribute constants
    /**
     * The MaxDuration attribute specifies the maximum time in seconds that the siren will
     * sound continuously, regardless of start/stop commands.
     */
    public static final int ATTR_MAXDURATION = 0x0000;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeClientAttributes();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeServerAttributes();

        attributeMap.put(ATTR_MAXDURATION, new ZclAttribute(this, ATTR_MAXDURATION, "Max Duration", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, true, false));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, StartWarningCommand.class);
        commandMap.put(0x0001, Squawk.class);

        return commandMap;
    }

    /**
     * Default constructor to create a IAS WD cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclIasWdCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Sends a {@link ZclIasWdCommand} and returns the {@link Future} to the result which will complete when the remote
     * device response is received, or the request times out.
     *
     * @param command the {@link ZclIasWdCommand} to send
     * @return the command result future
     */
    public Future<CommandResult> sendCommand(ZclIasWdCommand command) {
        return super.sendCommand(command);
    }

    /**
     * Sends a response to the command. This method sets all the common elements of the response based on the command -
     * eg transactionId, direction, address...
     *
     * @param command the {@link ZclIasWdCommand} to which the response is being sent
     * @param response the {@link ZclIasWdCommand} to send
     */
    public Future<CommandResult> sendResponse(ZclIasWdCommand command, ZclIasWdCommand response) {
        return super.sendResponse(command, response);
    }

    /**
     * Set the <i>Max Duration</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The MaxDuration attribute specifies the maximum time in seconds that the siren will
     * sound continuously, regardless of start/stop commands.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param maxDuration the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setMaxDuration(final Integer maxDuration) {
        return write(serverAttributes.get(ATTR_MAXDURATION), maxDuration);
    }

    /**
     * Get the <i>Max Duration</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The MaxDuration attribute specifies the maximum time in seconds that the siren will
     * sound continuously, regardless of start/stop commands.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMaxDurationAsync() {
        return read(serverAttributes.get(ATTR_MAXDURATION));
    }

    /**
     * Synchronously get the <i>Max Duration</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The MaxDuration attribute specifies the maximum time in seconds that the siren will
     * sound continuously, regardless of start/stop commands.
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
    public Integer getMaxDuration(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MAXDURATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MAXDURATION).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MAXDURATION));
    }

    /**
     * Set reporting for the <i>Max Duration</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The MaxDuration attribute specifies the maximum time in seconds that the siren will
     * sound continuously, regardless of start/stop commands.
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
    public Future<CommandResult> setMaxDurationReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_MAXDURATION), minInterval, maxInterval, reportableChange);
    }

    /**
     * The Start Warning Command
     * <p>
     * This command starts the WD operation. The WD alerts the surrounding area by audible
     * (siren) and visual (strobe) signals. <br> A Start Warning command shall always
     * terminate the effect of any previous command that is still current.
     *
     * @param header {@link Integer} Header
     * @param warningDuration {@link Integer} Warning Duration
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.startWarningCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new startWarningCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> startWarningCommand(Integer header, Integer warningDuration) {
        StartWarningCommand command = new StartWarningCommand();

        // Set the fields
        command.setHeader(header);
        command.setWarningDuration(warningDuration);

        return sendCommand(command);
    }

    /**
     * The Squawk
     * <p>
     * This command uses the WD capabilities to emit a quick audible/visible pulse called a
     * "squawk". The squawk command has no effect if the WD is currently active (warning in
     * progress).
     *
     * @param squawkInfo {@link Integer} Squawk Info
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.squawk(parameters ...)</code>
     * with <code>cluster.sendCommand(new squawk(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> squawk(Integer squawkInfo) {
        Squawk command = new Squawk();

        // Set the fields
        command.setSquawkInfo(squawkInfo);

        return sendCommand(command);
    }
}
