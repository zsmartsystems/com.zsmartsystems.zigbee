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
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveToLevelCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveToLevelWithOnOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveWithOnOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.StepCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.StepWithOnOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.Stop2Command;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.StopCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-26T21:33:25Z")
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

    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<>(7);

        attributeMap.put(ATTR_CURRENTLEVEL, new ZclAttribute(ZclClusterType.LEVEL_CONTROL, ATTR_CURRENTLEVEL, "Current Level", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_REMAININGTIME, new ZclAttribute(ZclClusterType.LEVEL_CONTROL, ATTR_REMAININGTIME, "Remaining Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ONOFFTRANSITIONTIME, new ZclAttribute(ZclClusterType.LEVEL_CONTROL, ATTR_ONOFFTRANSITIONTIME, "On Off Transition Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_ONLEVEL, new ZclAttribute(ZclClusterType.LEVEL_CONTROL, ATTR_ONLEVEL, "On Level", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_ONTRANSITIONTIME, new ZclAttribute(ZclClusterType.LEVEL_CONTROL, ATTR_ONTRANSITIONTIME, "On Transition Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_OFFTRANSITIONTIME, new ZclAttribute(ZclClusterType.LEVEL_CONTROL, ATTR_OFFTRANSITIONTIME, "Off Transition Time", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_DEFAULTMOVERATE, new ZclAttribute(ZclClusterType.LEVEL_CONTROL, ATTR_DEFAULTMOVERATE, "Default Move Rate", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentHashMap<>(8);

        commandMap.put(0x0000, MoveToLevelCommand.class);
        commandMap.put(0x0001, MoveCommand.class);
        commandMap.put(0x0002, StepCommand.class);
        commandMap.put(0x0003, StopCommand.class);
        commandMap.put(0x0004, MoveToLevelWithOnOffCommand.class);
        commandMap.put(0x0005, MoveWithOnOffCommand.class);
        commandMap.put(0x0006, StepWithOnOffCommand.class);
        commandMap.put(0x0007, Stop2Command.class);

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
     */
    public Future<CommandResult> getCurrentLevelAsync() {
        return read(attributes.get(ATTR_CURRENTLEVEL));
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
     */
    public Integer getCurrentLevel(final long refreshPeriod) {
        if (attributes.get(ATTR_CURRENTLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CURRENTLEVEL).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTLEVEL));
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
     */
    public Future<CommandResult> setCurrentLevelReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTLEVEL), minInterval, maxInterval, reportableChange);
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
     */
    public Future<CommandResult> getRemainingTimeAsync() {
        return read(attributes.get(ATTR_REMAININGTIME));
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
     */
    public Integer getRemainingTime(final long refreshPeriod) {
        if (attributes.get(ATTR_REMAININGTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_REMAININGTIME).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_REMAININGTIME));
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
     */
    public Future<CommandResult> setOnOffTransitionTime(final Integer value) {
        return write(attributes.get(ATTR_ONOFFTRANSITIONTIME), value);
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
     */
    public Future<CommandResult> getOnOffTransitionTimeAsync() {
        return read(attributes.get(ATTR_ONOFFTRANSITIONTIME));
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
     */
    public Integer getOnOffTransitionTime(final long refreshPeriod) {
        if (attributes.get(ATTR_ONOFFTRANSITIONTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ONOFFTRANSITIONTIME).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ONOFFTRANSITIONTIME));
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
     */
    public Future<CommandResult> setOnLevel(final Integer value) {
        return write(attributes.get(ATTR_ONLEVEL), value);
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
     */
    public Future<CommandResult> getOnLevelAsync() {
        return read(attributes.get(ATTR_ONLEVEL));
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
     */
    public Integer getOnLevel(final long refreshPeriod) {
        if (attributes.get(ATTR_ONLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ONLEVEL).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ONLEVEL));
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
     */
    public Future<CommandResult> setOnTransitionTime(final Integer value) {
        return write(attributes.get(ATTR_ONTRANSITIONTIME), value);
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
     */
    public Future<CommandResult> getOnTransitionTimeAsync() {
        return read(attributes.get(ATTR_ONTRANSITIONTIME));
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
     */
    public Integer getOnTransitionTime(final long refreshPeriod) {
        if (attributes.get(ATTR_ONTRANSITIONTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ONTRANSITIONTIME).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ONTRANSITIONTIME));
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
     */
    public Future<CommandResult> setOffTransitionTime(final Integer value) {
        return write(attributes.get(ATTR_OFFTRANSITIONTIME), value);
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
     */
    public Future<CommandResult> getOffTransitionTimeAsync() {
        return read(attributes.get(ATTR_OFFTRANSITIONTIME));
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
     */
    public Integer getOffTransitionTime(final long refreshPeriod) {
        if (attributes.get(ATTR_OFFTRANSITIONTIME).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_OFFTRANSITIONTIME).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_OFFTRANSITIONTIME));
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
     */
    public Future<CommandResult> setDefaultMoveRate(final Integer value) {
        return write(attributes.get(ATTR_DEFAULTMOVERATE), value);
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
     */
    public Future<CommandResult> getDefaultMoveRateAsync() {
        return read(attributes.get(ATTR_DEFAULTMOVERATE));
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
     */
    public Integer getDefaultMoveRate(final long refreshPeriod) {
        if (attributes.get(ATTR_DEFAULTMOVERATE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_DEFAULTMOVERATE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_DEFAULTMOVERATE));
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
     */
    public Future<CommandResult> moveToLevelCommand(Integer level, Integer transitionTime) {
        MoveToLevelCommand command = new MoveToLevelCommand();

        // Set the fields
        command.setLevel(level);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move Command
     *
     * @param moveMode {@link Integer} Move Mode
     * @param rate {@link Integer} Rate
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveCommand(Integer moveMode, Integer rate) {
        MoveCommand command = new MoveCommand();

        // Set the fields
        command.setMoveMode(moveMode);
        command.setRate(rate);

        return send(command);
    }

    /**
     * The Step Command
     *
     * @param stepMode {@link Integer} Step Mode
     * @param stepSize {@link Integer} Step Size
     * @param transitionTime {@link Integer} Transition Time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> stepCommand(Integer stepMode, Integer stepSize, Integer transitionTime) {
        StepCommand command = new StepCommand();

        // Set the fields
        command.setStepMode(stepMode);
        command.setStepSize(stepSize);
        command.setTransitionTime(transitionTime);

        return send(command);
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
     */
    public Future<CommandResult> stopCommand() {
        return send(new StopCommand());
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
     */
    public Future<CommandResult> moveToLevelWithOnOffCommand(Integer level, Integer transitionTime) {
        MoveToLevelWithOnOffCommand command = new MoveToLevelWithOnOffCommand();

        // Set the fields
        command.setLevel(level);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move (with On/Off) Command
     *
     * @param moveMode {@link Integer} Move Mode
     * @param rate {@link Integer} Rate
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveWithOnOffCommand(Integer moveMode, Integer rate) {
        MoveWithOnOffCommand command = new MoveWithOnOffCommand();

        // Set the fields
        command.setMoveMode(moveMode);
        command.setRate(rate);

        return send(command);
    }

    /**
     * The Step (with On/Off) Command
     *
     * @param stepMode {@link Integer} Step Mode
     * @param stepSize {@link Integer} Step Size
     * @param transitionTime {@link Integer} Transition Time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> stepWithOnOffCommand(Integer stepMode, Integer stepSize, Integer transitionTime) {
        StepWithOnOffCommand command = new StepWithOnOffCommand();

        // Set the fields
        command.setStepMode(stepMode);
        command.setStepSize(stepSize);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Stop 2 Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> stop2Command() {
        return send(new Stop2Command());
    }
}
