/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveToLevelCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveToLevelWithOnOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveWithOnOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.StepCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.StepWithOnOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.StopCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.StopWithOnOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.ZclLevelControlCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Level Control</b> cluster implementation (<i>Cluster ID 0x0008</i>).
 * <p>
 * This cluster provides an interface for controlling a characteristic of a device that can be
 * set to a level, for example the brightness of a light, the degree of closure of a door, or the
 * power output of a heater.
 * <p>
 * For many applications, a close relationship between this cluster and the OnOff cluster is
 * needed. This section describes the dependencies that are required when an endpoint that
 * implements the Level Control server cluster also implements the On/Off server cluster.
 * <p>
 * The OnOff attribute of the On/Off cluster and the CurrentLevel attribute of the Level
 * Control cluster are intrinsically independent variables, as they are on different
 * clusters. However, when both clusters are implemented on the same endpoint, dependencies
 * may be introduced between them. Facilities are provided to introduce dependencies if
 * required.
 * <p>
 * There are two sets of commands provided in the Level Control cluster. These are identical,
 * except that the first set (Move to Level, Move and Step) shall NOT affect the OnOff attribute,
 * whereas the second set ('with On/Off' variants) SHALL.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T20:27:57Z")
public class ZclLevelControlCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0008;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Level Control";

    // Attribute constants
    /**
     * The CurrentLevel attribute represents the current level of this device. The meaning of
     * 'level' is device dependent. Value is between 0 and 254.
     */
    public static final int ATTR_CURRENTLEVEL = 0x0000;
    /**
     * The RemainingTime attribute represents the time remaining until the current command
     * is complete - it is specified in 1/10ths of a second.
     */
    public static final int ATTR_REMAININGTIME = 0x0001;
    /**
     * The MinLevel attribute indicates the minimum value of CurrentLevel that is capable of
     * being assigned.
     */
    public static final int ATTR_MINIMUMLEVEL = 0x0002;
    /**
     * The MaxLevel attribute indicates the maximum value of CurrentLevel that is capable of
     * being assigned.
     */
    public static final int ATTR_MAXIMUMLEVEL = 0x0003;
    /**
     * The CurrentFrequency attribute represents the frequency that the devices is at
     * CurrentLevel. A CurrentFrequency of 0 is unknown.
     */
    public static final int ATTR_CURRENTFREQUENCY = 0x0004;
    /**
     * The MinFrequency attribute indicates the minimum value of CurrentFrequency that is
     * capable of being assigned. MinFrequency shall be less than or equal to MaxFrequency. A
     * value of 0 indicates undefined.
     */
    public static final int ATTR_MINIMUMFREQUENCY = 0x0005;
    /**
     * The MaxFrequency attribute indicates the maximum value of CurrentFrequency that is
     * capable of being assigned. MaxFrequency shall be greater than or equal to
     * MinFrequency. A value of 0 indicates undefined.
     */
    public static final int ATTR_MAXIMUMFREQUENCY = 0x0006;
    public static final int ATTR_OPTIONS = 0x000F;
    /**
     * The OnOffTransitionTime attribute represents the time taken to move to or from the
     * target level when On of Off commands are received by an On/Off cluster on the same
     * endpoint. It is specified in 1/10ths of a second.
     * <p>
     * The actual time taken should be as close to OnOffTransitionTime as the device is able.
     * N.B. If the device is not able to move at a variable rate, the OnOffTransitionTime
     * attribute should not be implemented.
     */
    public static final int ATTR_ONOFFTRANSITIONTIME = 0x0010;
    /**
     * The OnLevel attribute determines the value that the CurrentLevel attribute is set to
     * when the OnOff attribute of an On/Off cluster on the same endpoint is set to On. If the
     * OnLevel attribute is not implemented, or is set to 0xff, it has no effect.
     */
    public static final int ATTR_ONLEVEL = 0x0011;
    /**
     * The OnTransitionTime attribute represents the time taken to move the current level
     * from the minimum level to the maximum level when an On command is received by an On/Off
     * cluster on the same endpoint. It is specified in 10ths of a second. If this command is not
     * implemented, or contains a value of 0xffff, the OnOffTransitionTime will be used
     * instead.
     */
    public static final int ATTR_ONTRANSITIONTIME = 0x0012;
    /**
     * The OffTransitionTime attribute represents the time taken to move the current level
     * from the maximum level to the minimum level when an Off command is received by an On/Off
     * cluster on the same endpoint. It is specified in 10ths of a second. If this command is not
     * implemented, or contains a value of 0xffff, the OnOffTransitionTime will be used
     * instead.
     */
    public static final int ATTR_OFFTRANSITIONTIME = 0x0013;
    /**
     * The DefaultMoveRate attribute determines the movement rate, in units per second, when
     * a Move command is received with a Rate parameter of 0xFF.
     */
    public static final int ATTR_DEFAULTMOVERATE = 0x0014;
    public static final int ATTR_STARTUPCURRENTLEVEL = 0x4000;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeClientAttributes();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeServerAttributes();

        attributeMap.put(ATTR_CURRENTLEVEL, new ZclAttribute(this, ATTR_CURRENTLEVEL, "Current Level", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_REMAININGTIME, new ZclAttribute(this, ATTR_REMAININGTIME, "Remaining Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MINIMUMLEVEL, new ZclAttribute(this, ATTR_MINIMUMLEVEL, "Minimum Level", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAXIMUMLEVEL, new ZclAttribute(this, ATTR_MAXIMUMLEVEL, "Maximum Level", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CURRENTFREQUENCY, new ZclAttribute(this, ATTR_CURRENTFREQUENCY, "Current Frequency", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_MINIMUMFREQUENCY, new ZclAttribute(this, ATTR_MINIMUMFREQUENCY, "Minimum Frequency", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_MAXIMUMFREQUENCY, new ZclAttribute(this, ATTR_MAXIMUMFREQUENCY, "Maximum Frequency", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_OPTIONS, new ZclAttribute(this, ATTR_OPTIONS, "Options", ZclDataType.BITMAP_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_ONOFFTRANSITIONTIME, new ZclAttribute(this, ATTR_ONOFFTRANSITIONTIME, "On Off Transition Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_ONLEVEL, new ZclAttribute(this, ATTR_ONLEVEL, "On Level", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_ONTRANSITIONTIME, new ZclAttribute(this, ATTR_ONTRANSITIONTIME, "On Transition Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_OFFTRANSITIONTIME, new ZclAttribute(this, ATTR_OFFTRANSITIONTIME, "Off Transition Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_DEFAULTMOVERATE, new ZclAttribute(this, ATTR_DEFAULTMOVERATE, "Default Move Rate", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_STARTUPCURRENTLEVEL, new ZclAttribute(this, ATTR_STARTUPCURRENTLEVEL, "Start Up Current Level", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, MoveToLevelCommand.class);
        commandMap.put(0x0001, MoveCommand.class);
        commandMap.put(0x0002, StepCommand.class);
        commandMap.put(0x0003, StopCommand.class);
        commandMap.put(0x0004, MoveToLevelWithOnOffCommand.class);
        commandMap.put(0x0005, MoveWithOnOffCommand.class);
        commandMap.put(0x0006, StepWithOnOffCommand.class);
        commandMap.put(0x0007, StopWithOnOffCommand.class);

        return commandMap;
    }

    /**
     * Default constructor to create a Level Control cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclLevelControlCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Sends a {@link ZclLevelControlCommand} and returns the {@link Future} to the result which will complete when the remote
     * device response is received, or the request times out.
     *
     * @param command the {@link ZclLevelControlCommand} to send
     * @return the command result future
     */
    public Future<CommandResult> sendCommand(ZclLevelControlCommand command) {
        return super.sendCommand(command);
    }

    /**
     * Sends a response to the command. This method sets all the common elements of the response based on the command -
     * eg transactionId, direction, address...
     *
     * @param command the {@link ZclLevelControlCommand} to which the response is being sent
     * @param response the {@link ZclLevelControlCommand} to send
     */
    public Future<CommandResult> sendResponse(ZclLevelControlCommand command, ZclLevelControlCommand response) {
        return super.sendResponse(command, response);
    }

    /**
     * Get the <i>Current Level</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The CurrentLevel attribute represents the current level of this device. The meaning of
     * 'level' is device dependent. Value is between 0 and 254.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getCurrentLevelAsync() {
        return read(serverAttributes.get(ATTR_CURRENTLEVEL));
    }

    /**
     * Synchronously get the <i>Current Level</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The CurrentLevel attribute represents the current level of this device. The meaning of
     * 'level' is device dependent. Value is between 0 and 254.
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
    public Integer getCurrentLevel(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_CURRENTLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_CURRENTLEVEL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_CURRENTLEVEL));
    }

    /**
     * Set reporting for the <i>Current Level</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The CurrentLevel attribute represents the current level of this device. The meaning of
     * 'level' is device dependent. Value is between 0 and 254.
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
    public Future<CommandResult> setCurrentLevelReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_CURRENTLEVEL), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Remaining Time</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The RemainingTime attribute represents the time remaining until the current command
     * is complete - it is specified in 1/10ths of a second.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getRemainingTimeAsync() {
        return read(serverAttributes.get(ATTR_REMAININGTIME));
    }

    /**
     * Synchronously get the <i>Remaining Time</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The RemainingTime attribute represents the time remaining until the current command
     * is complete - it is specified in 1/10ths of a second.
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
    public Integer getRemainingTime(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_REMAININGTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_REMAININGTIME).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_REMAININGTIME));
    }

    /**
     * Get the <i>Minimum Level</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The MinLevel attribute indicates the minimum value of CurrentLevel that is capable of
     * being assigned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMinimumLevelAsync() {
        return read(serverAttributes.get(ATTR_MINIMUMLEVEL));
    }

    /**
     * Synchronously get the <i>Minimum Level</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The MinLevel attribute indicates the minimum value of CurrentLevel that is capable of
     * being assigned.
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
    public Integer getMinimumLevel(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MINIMUMLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MINIMUMLEVEL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MINIMUMLEVEL));
    }

    /**
     * Get the <i>Maximum Level</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The MaxLevel attribute indicates the maximum value of CurrentLevel that is capable of
     * being assigned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMaximumLevelAsync() {
        return read(serverAttributes.get(ATTR_MAXIMUMLEVEL));
    }

    /**
     * Synchronously get the <i>Maximum Level</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The MaxLevel attribute indicates the maximum value of CurrentLevel that is capable of
     * being assigned.
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
    public Integer getMaximumLevel(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MAXIMUMLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MAXIMUMLEVEL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MAXIMUMLEVEL));
    }

    /**
     * Get the <i>Current Frequency</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The CurrentFrequency attribute represents the frequency that the devices is at
     * CurrentLevel. A CurrentFrequency of 0 is unknown.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getCurrentFrequencyAsync() {
        return read(serverAttributes.get(ATTR_CURRENTFREQUENCY));
    }

    /**
     * Synchronously get the <i>Current Frequency</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The CurrentFrequency attribute represents the frequency that the devices is at
     * CurrentLevel. A CurrentFrequency of 0 is unknown.
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
    public Integer getCurrentFrequency(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_CURRENTFREQUENCY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_CURRENTFREQUENCY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_CURRENTFREQUENCY));
    }

    /**
     * Get the <i>Minimum Frequency</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The MinFrequency attribute indicates the minimum value of CurrentFrequency that is
     * capable of being assigned. MinFrequency shall be less than or equal to MaxFrequency. A
     * value of 0 indicates undefined.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMinimumFrequencyAsync() {
        return read(serverAttributes.get(ATTR_MINIMUMFREQUENCY));
    }

    /**
     * Synchronously get the <i>Minimum Frequency</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The MinFrequency attribute indicates the minimum value of CurrentFrequency that is
     * capable of being assigned. MinFrequency shall be less than or equal to MaxFrequency. A
     * value of 0 indicates undefined.
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
    public Integer getMinimumFrequency(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MINIMUMFREQUENCY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MINIMUMFREQUENCY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MINIMUMFREQUENCY));
    }

    /**
     * Get the <i>Maximum Frequency</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The MaxFrequency attribute indicates the maximum value of CurrentFrequency that is
     * capable of being assigned. MaxFrequency shall be greater than or equal to
     * MinFrequency. A value of 0 indicates undefined.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getMaximumFrequencyAsync() {
        return read(serverAttributes.get(ATTR_MAXIMUMFREQUENCY));
    }

    /**
     * Synchronously get the <i>Maximum Frequency</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The MaxFrequency attribute indicates the maximum value of CurrentFrequency that is
     * capable of being assigned. MaxFrequency shall be greater than or equal to
     * MinFrequency. A value of 0 indicates undefined.
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
    public Integer getMaximumFrequency(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_MAXIMUMFREQUENCY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_MAXIMUMFREQUENCY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_MAXIMUMFREQUENCY));
    }

    /**
     * Get the <i>Options</i> attribute [attribute ID <b>0x000F</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOptionsAsync() {
        return read(serverAttributes.get(ATTR_OPTIONS));
    }

    /**
     * Synchronously get the <i>Options</i> attribute [attribute ID <b>0x000F</b>].
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
    public Integer getOptions(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OPTIONS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OPTIONS).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OPTIONS));
    }

    /**
     * Set the <i>On Off Transition Time</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The OnOffTransitionTime attribute represents the time taken to move to or from the
     * target level when On of Off commands are received by an On/Off cluster on the same
     * endpoint. It is specified in 1/10ths of a second.
     * <p>
     * The actual time taken should be as close to OnOffTransitionTime as the device is able.
     * N.B. If the device is not able to move at a variable rate, the OnOffTransitionTime
     * attribute should not be implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param onOffTransitionTime the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setOnOffTransitionTime(final Integer onOffTransitionTime) {
        return write(serverAttributes.get(ATTR_ONOFFTRANSITIONTIME), onOffTransitionTime);
    }

    /**
     * Get the <i>On Off Transition Time</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The OnOffTransitionTime attribute represents the time taken to move to or from the
     * target level when On of Off commands are received by an On/Off cluster on the same
     * endpoint. It is specified in 1/10ths of a second.
     * <p>
     * The actual time taken should be as close to OnOffTransitionTime as the device is able.
     * N.B. If the device is not able to move at a variable rate, the OnOffTransitionTime
     * attribute should not be implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOnOffTransitionTimeAsync() {
        return read(serverAttributes.get(ATTR_ONOFFTRANSITIONTIME));
    }

    /**
     * Synchronously get the <i>On Off Transition Time</i> attribute [attribute ID <b>0x0010</b>].
     * <p>
     * The OnOffTransitionTime attribute represents the time taken to move to or from the
     * target level when On of Off commands are received by an On/Off cluster on the same
     * endpoint. It is specified in 1/10ths of a second.
     * <p>
     * The actual time taken should be as close to OnOffTransitionTime as the device is able.
     * N.B. If the device is not able to move at a variable rate, the OnOffTransitionTime
     * attribute should not be implemented.
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
    public Integer getOnOffTransitionTime(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ONOFFTRANSITIONTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ONOFFTRANSITIONTIME).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ONOFFTRANSITIONTIME));
    }

    /**
     * Set the <i>On Level</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The OnLevel attribute determines the value that the CurrentLevel attribute is set to
     * when the OnOff attribute of an On/Off cluster on the same endpoint is set to On. If the
     * OnLevel attribute is not implemented, or is set to 0xff, it has no effect.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param onLevel the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setOnLevel(final Integer onLevel) {
        return write(serverAttributes.get(ATTR_ONLEVEL), onLevel);
    }

    /**
     * Get the <i>On Level</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The OnLevel attribute determines the value that the CurrentLevel attribute is set to
     * when the OnOff attribute of an On/Off cluster on the same endpoint is set to On. If the
     * OnLevel attribute is not implemented, or is set to 0xff, it has no effect.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOnLevelAsync() {
        return read(serverAttributes.get(ATTR_ONLEVEL));
    }

    /**
     * Synchronously get the <i>On Level</i> attribute [attribute ID <b>0x0011</b>].
     * <p>
     * The OnLevel attribute determines the value that the CurrentLevel attribute is set to
     * when the OnOff attribute of an On/Off cluster on the same endpoint is set to On. If the
     * OnLevel attribute is not implemented, or is set to 0xff, it has no effect.
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
    public Integer getOnLevel(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ONLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ONLEVEL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ONLEVEL));
    }

    /**
     * Set the <i>On Transition Time</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The OnTransitionTime attribute represents the time taken to move the current level
     * from the minimum level to the maximum level when an On command is received by an On/Off
     * cluster on the same endpoint. It is specified in 10ths of a second. If this command is not
     * implemented, or contains a value of 0xffff, the OnOffTransitionTime will be used
     * instead.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param onTransitionTime the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setOnTransitionTime(final Integer onTransitionTime) {
        return write(serverAttributes.get(ATTR_ONTRANSITIONTIME), onTransitionTime);
    }

    /**
     * Get the <i>On Transition Time</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The OnTransitionTime attribute represents the time taken to move the current level
     * from the minimum level to the maximum level when an On command is received by an On/Off
     * cluster on the same endpoint. It is specified in 10ths of a second. If this command is not
     * implemented, or contains a value of 0xffff, the OnOffTransitionTime will be used
     * instead.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOnTransitionTimeAsync() {
        return read(serverAttributes.get(ATTR_ONTRANSITIONTIME));
    }

    /**
     * Synchronously get the <i>On Transition Time</i> attribute [attribute ID <b>0x0012</b>].
     * <p>
     * The OnTransitionTime attribute represents the time taken to move the current level
     * from the minimum level to the maximum level when an On command is received by an On/Off
     * cluster on the same endpoint. It is specified in 10ths of a second. If this command is not
     * implemented, or contains a value of 0xffff, the OnOffTransitionTime will be used
     * instead.
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
    public Integer getOnTransitionTime(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_ONTRANSITIONTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_ONTRANSITIONTIME).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_ONTRANSITIONTIME));
    }

    /**
     * Set the <i>Off Transition Time</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The OffTransitionTime attribute represents the time taken to move the current level
     * from the maximum level to the minimum level when an Off command is received by an On/Off
     * cluster on the same endpoint. It is specified in 10ths of a second. If this command is not
     * implemented, or contains a value of 0xffff, the OnOffTransitionTime will be used
     * instead.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param offTransitionTime the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setOffTransitionTime(final Integer offTransitionTime) {
        return write(serverAttributes.get(ATTR_OFFTRANSITIONTIME), offTransitionTime);
    }

    /**
     * Get the <i>Off Transition Time</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The OffTransitionTime attribute represents the time taken to move the current level
     * from the maximum level to the minimum level when an Off command is received by an On/Off
     * cluster on the same endpoint. It is specified in 10ths of a second. If this command is not
     * implemented, or contains a value of 0xffff, the OnOffTransitionTime will be used
     * instead.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getOffTransitionTimeAsync() {
        return read(serverAttributes.get(ATTR_OFFTRANSITIONTIME));
    }

    /**
     * Synchronously get the <i>Off Transition Time</i> attribute [attribute ID <b>0x0013</b>].
     * <p>
     * The OffTransitionTime attribute represents the time taken to move the current level
     * from the maximum level to the minimum level when an Off command is received by an On/Off
     * cluster on the same endpoint. It is specified in 10ths of a second. If this command is not
     * implemented, or contains a value of 0xffff, the OnOffTransitionTime will be used
     * instead.
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
    public Integer getOffTransitionTime(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_OFFTRANSITIONTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_OFFTRANSITIONTIME).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_OFFTRANSITIONTIME));
    }

    /**
     * Set the <i>Default Move Rate</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The DefaultMoveRate attribute determines the movement rate, in units per second, when
     * a Move command is received with a Rate parameter of 0xFF.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param defaultMoveRate the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setDefaultMoveRate(final Integer defaultMoveRate) {
        return write(serverAttributes.get(ATTR_DEFAULTMOVERATE), defaultMoveRate);
    }

    /**
     * Get the <i>Default Move Rate</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The DefaultMoveRate attribute determines the movement rate, in units per second, when
     * a Move command is received with a Rate parameter of 0xFF.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getDefaultMoveRateAsync() {
        return read(serverAttributes.get(ATTR_DEFAULTMOVERATE));
    }

    /**
     * Synchronously get the <i>Default Move Rate</i> attribute [attribute ID <b>0x0014</b>].
     * <p>
     * The DefaultMoveRate attribute determines the movement rate, in units per second, when
     * a Move command is received with a Rate parameter of 0xFF.
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
    public Integer getDefaultMoveRate(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_DEFAULTMOVERATE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_DEFAULTMOVERATE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_DEFAULTMOVERATE));
    }

    /**
     * Set the <i>Start Up Current Level</i> attribute [attribute ID <b>0x4000</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param startUpCurrentLevel the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setStartUpCurrentLevel(final Integer startUpCurrentLevel) {
        return write(serverAttributes.get(ATTR_STARTUPCURRENTLEVEL), startUpCurrentLevel);
    }

    /**
     * Get the <i>Start Up Current Level</i> attribute [attribute ID <b>0x4000</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getStartUpCurrentLevelAsync() {
        return read(serverAttributes.get(ATTR_STARTUPCURRENTLEVEL));
    }

    /**
     * Synchronously get the <i>Start Up Current Level</i> attribute [attribute ID <b>0x4000</b>].
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
    public Integer getStartUpCurrentLevel(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_STARTUPCURRENTLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_STARTUPCURRENTLEVEL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_STARTUPCURRENTLEVEL));
    }

    /**
     * The Move To Level Command
     * <p>
     * On receipt of this command, a device shall move from its current level to the value given
     * in the Level field. The meaning of ‘level’ is device dependent –e.g., for a light it may
     * mean brightness level.The movement shall be as continuous as technically practical,
     * i.e., not a step function, and the time taken to move to the new level shall be equal to the
     * value of the Transition time field, in tenths of a second, or as close to this as the device
     * is able.If the Transition time field takes the value 0xffff then the time taken to move to
     * the new level shall instead be determined by the OnOffTransitionTimeattribute. If
     * OnOffTransitionTime, which is an optional attribute, is not present, the device shall
     * move to its new level as fast as it is able.
     *
     * @param level {@link Integer} Level
     * @param transitionTime {@link Integer} Transition Time
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.moveToLevelCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new moveToLevelCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> moveToLevelCommand(Integer level, Integer transitionTime) {
        MoveToLevelCommand command = new MoveToLevelCommand();

        // Set the fields
        command.setLevel(level);
        command.setTransitionTime(transitionTime);

        return sendCommand(command);
    }

    /**
     * The Move Command
     *
     * @param moveMode {@link Integer} Move Mode
     * @param rate {@link Integer} Rate
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.moveCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new moveCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> moveCommand(Integer moveMode, Integer rate) {
        MoveCommand command = new MoveCommand();

        // Set the fields
        command.setMoveMode(moveMode);
        command.setRate(rate);

        return sendCommand(command);
    }

    /**
     * The Step Command
     *
     * @param stepMode {@link Integer} Step Mode
     * @param stepSize {@link Integer} Step Size
     * @param transitionTime {@link Integer} Transition Time
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.stepCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new stepCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> stepCommand(Integer stepMode, Integer stepSize, Integer transitionTime) {
        StepCommand command = new StepCommand();

        // Set the fields
        command.setStepMode(stepMode);
        command.setStepSize(stepSize);
        command.setTransitionTime(transitionTime);

        return sendCommand(command);
    }

    /**
     * The Stop Command
     * <p>
     * Upon receipt of this command, any Move to Level, Move or Step command (and their 'with
     * On/Off' variants) currently in process shall be terminated. The value of CurrentLevel
     * shall be left at its value upon receipt of the Stop command, and RemainingTime shall be
     * set to zero.
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.stopCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new stopCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> stopCommand() {
        return sendCommand(new StopCommand());
    }

    /**
     * The Move To Level (with On/Off) Command
     * <p>
     * On receipt of this command, a device shall move from its current level to the value given
     * in the Level field. The meaning of ‘level’ is device dependent –e.g., for a light it may
     * mean brightness level.The movement shall be as continuous as technically practical,
     * i.e., not a step function, and the time taken to move to the new level shall be equal to the
     * value of the Transition time field, in tenths of a second, or as close to this as the device
     * is able.If the Transition time field takes the value 0xffff then the time taken to move to
     * the new level shall instead be determined by the OnOffTransitionTimeattribute. If
     * OnOffTransitionTime, which is an optional attribute, is not present, the device shall
     * move to its new level as fast as it is able.
     *
     * @param level {@link Integer} Level
     * @param transitionTime {@link Integer} Transition Time
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.moveToLevelWithOnOffCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new moveToLevelWithOnOffCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> moveToLevelWithOnOffCommand(Integer level, Integer transitionTime) {
        MoveToLevelWithOnOffCommand command = new MoveToLevelWithOnOffCommand();

        // Set the fields
        command.setLevel(level);
        command.setTransitionTime(transitionTime);

        return sendCommand(command);
    }

    /**
     * The Move (with On/Off) Command
     *
     * @param moveMode {@link Integer} Move Mode
     * @param rate {@link Integer} Rate
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.moveWithOnOffCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new moveWithOnOffCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> moveWithOnOffCommand(Integer moveMode, Integer rate) {
        MoveWithOnOffCommand command = new MoveWithOnOffCommand();

        // Set the fields
        command.setMoveMode(moveMode);
        command.setRate(rate);

        return sendCommand(command);
    }

    /**
     * The Step (with On/Off) Command
     *
     * @param stepMode {@link Integer} Step Mode
     * @param stepSize {@link Integer} Step Size
     * @param transitionTime {@link Integer} Transition Time
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.stepWithOnOffCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new stepWithOnOffCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> stepWithOnOffCommand(Integer stepMode, Integer stepSize, Integer transitionTime) {
        StepWithOnOffCommand command = new StepWithOnOffCommand();

        // Set the fields
        command.setStepMode(stepMode);
        command.setStepSize(stepSize);
        command.setTransitionTime(transitionTime);

        return sendCommand(command);
    }

    /**
     * The Stop (with On/Off) Command
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.stopWithOnOffCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new stopWithOnOffCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> stopWithOnOffCommand() {
        return sendCommand(new StopWithOnOffCommand());
    }
}
