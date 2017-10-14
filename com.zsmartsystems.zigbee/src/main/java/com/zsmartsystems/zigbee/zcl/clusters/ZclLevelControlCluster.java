/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
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
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * <b>Level Control</b> cluster implementation (<i>Cluster ID 0x0008</i>).
 * <p>
 * This cluster provides an interface for controlling a characteristic of a device that
 * can be set to a level, for example the brightness of a light, the degree of closure of
 * a door, or the power output of a heater.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclLevelControlCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x0008;

    // Cluster Name
    public static final String CLUSTER_NAME = "Level Control";

    // Attribute constants
    public static final int ATTR_CURRENTLEVEL = 0x0000;
    public static final int ATTR_REMAININGTIME = 0x0001;
    public static final int ATTR_ONOFFTRANSITIONTIME = 0x0010;
    public static final int ATTR_ONLEVEL = 0x0011;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(4);

        attributeMap.put(ATTR_CURRENTLEVEL, new ZclAttribute(ZclClusterType.LEVEL_CONTROL, ATTR_CURRENTLEVEL, "CurrentLevel", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_REMAININGTIME, new ZclAttribute(ZclClusterType.LEVEL_CONTROL, ATTR_REMAININGTIME, "RemainingTime", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ONOFFTRANSITIONTIME, new ZclAttribute(ZclClusterType.LEVEL_CONTROL, ATTR_ONOFFTRANSITIONTIME, "OnOffTransitionTime", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, false));
        attributeMap.put(ATTR_ONLEVEL, new ZclAttribute(ZclClusterType.LEVEL_CONTROL, ATTR_ONLEVEL, "OnLevel", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Level Control cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeDevice}
     */
    public ZclLevelControlCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * Get the <i>CurrentLevel</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The CurrentLevel attribute represents the current level of this device. The
     * meaning of 'level' is device dependent.
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
     * Synchronously get the <i>CurrentLevel</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The CurrentLevel attribute represents the current level of this device. The
     * meaning of 'level' is device dependent.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_CURRENTLEVEL).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_CURRENTLEVEL).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_CURRENTLEVEL).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTLEVEL));
    }


    /**
     * Set reporting for the <i>CurrentLevel</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The CurrentLevel attribute represents the current level of this device. The
     * meaning of 'level' is device dependent.
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
    public Future<CommandResult> setCurrentLevelReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTLEVEL), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RemainingTime</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The RemainingTime attribute represents the time remaining until the current
     * <p>
     * command is complete - it is specified in 1/10ths of a second.
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
     * Synchronously get the <i>RemainingTime</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The RemainingTime attribute represents the time remaining until the current
     * <p>
     * command is complete - it is specified in 1/10ths of a second.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_REMAININGTIME).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_REMAININGTIME).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_REMAININGTIME).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_REMAININGTIME));
    }


    /**
     * Set the <i>OnOffTransitionTime</i> attribute [attribute ID <b>16</b>].
     * <p>
     * The OnOffTransitionTime attribute represents the time taken to move to or from
     * <p>
     * the target level when On of Off commands are received by an On/Off cluster on
     * the same endpoint. It is specified in 1/10ths of a second.
     * <p>
     * The actual time taken should be as close to OnOffTransitionTime as the device is
     * able. N.B. If the device is not able to move at a variable rate, the
     * OnOffTransitionTime attribute should not be implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param onOffTransitionTime the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOnOffTransitionTime(final Object value) {
        return write(attributes.get(ATTR_ONOFFTRANSITIONTIME), value);
    }

    /**
     * Get the <i>OnOffTransitionTime</i> attribute [attribute ID <b>16</b>].
     * <p>
     * The OnOffTransitionTime attribute represents the time taken to move to or from
     * <p>
     * the target level when On of Off commands are received by an On/Off cluster on
     * the same endpoint. It is specified in 1/10ths of a second.
     * <p>
     * The actual time taken should be as close to OnOffTransitionTime as the device is
     * able. N.B. If the device is not able to move at a variable rate, the
     * OnOffTransitionTime attribute should not be implemented.
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
     * Synchronously get the <i>OnOffTransitionTime</i> attribute [attribute ID <b>16</b>].
     * <p>
     * The OnOffTransitionTime attribute represents the time taken to move to or from
     * <p>
     * the target level when On of Off commands are received by an On/Off cluster on
     * the same endpoint. It is specified in 1/10ths of a second.
     * <p>
     * The actual time taken should be as close to OnOffTransitionTime as the device is
     * able. N.B. If the device is not able to move at a variable rate, the
     * OnOffTransitionTime attribute should not be implemented.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_ONOFFTRANSITIONTIME).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ONOFFTRANSITIONTIME).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ONOFFTRANSITIONTIME).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ONOFFTRANSITIONTIME));
    }


    /**
     * Set the <i>OnLevel</i> attribute [attribute ID <b>17</b>].
     * <p>
     * The OnLevel attribute determines the value that the CurrentLevel attribute is set to
     * <p>
     * when the OnOff attribute of an On/Off cluster on the same endpoint is set to On. If
     * the OnLevel attribute is not implemented, or is set to 0xff, it has no effect.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param onLevel the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setOnLevel(final Object value) {
        return write(attributes.get(ATTR_ONLEVEL), value);
    }

    /**
     * Get the <i>OnLevel</i> attribute [attribute ID <b>17</b>].
     * <p>
     * The OnLevel attribute determines the value that the CurrentLevel attribute is set to
     * <p>
     * when the OnOff attribute of an On/Off cluster on the same endpoint is set to On. If
     * the OnLevel attribute is not implemented, or is set to 0xff, it has no effect.
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
     * Synchronously get the <i>OnLevel</i> attribute [attribute ID <b>17</b>].
     * <p>
     * The OnLevel attribute determines the value that the CurrentLevel attribute is set to
     * <p>
     * when the OnOff attribute of an On/Off cluster on the same endpoint is set to On. If
     * the OnLevel attribute is not implemented, or is set to 0xff, it has no effect.
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
        if(refreshPeriod > 0 && attributes.get(ATTR_ONLEVEL).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_ONLEVEL).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_ONLEVEL).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_ONLEVEL));
    }

    /**
     * The Move to Level Command
     *
     * @param level {@link Integer} Level
     * @param transitionTime {@link Integer} Transition time
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
     * @param moveMode {@link Integer} Move mode
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
     * @param stepMode {@link Integer} Step mode
     * @param stepSize {@link Integer} Step size
     * @param transitionTime {@link Integer} Transition time
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
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> stopCommand() {
        StopCommand command = new StopCommand();

        return send(command);
    }

    /**
     * The Move to Level (with On/Off) Command
     *
     * @param level {@link Integer} Level
     * @param transitionTime {@link Integer} Transition time
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
     * @param moveMode {@link Integer} Move mode
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
     * @param stepMode {@link Integer} Step mode
     * @param stepSize {@link Integer} Step size
     * @param transitionTime {@link Integer} Transition time
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
        Stop2Command command = new Stop2Command();

        return send(command);
    }

    /**
     * Add a binding for this cluster to the local node
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> bind() {
        return bind();
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // MOVE_TO_LEVEL_COMMAND
                return new MoveToLevelCommand();
            case 1: // MOVE_COMMAND
                return new MoveCommand();
            case 2: // STEP_COMMAND
                return new StepCommand();
            case 3: // STOP_COMMAND
                return new StopCommand();
            case 4: // MOVE_TO_LEVEL__WITH_ON_OFF__COMMAND
                return new MoveToLevelWithOnOffCommand();
            case 5: // MOVE__WITH_ON_OFF__COMMAND
                return new MoveWithOnOffCommand();
            case 6: // STEP__WITH_ON_OFF__COMMAND
                return new StepWithOnOffCommand();
            case 7: // STOP_2_COMMAND
                return new Stop2Command();
            default:
                return null;
        }
    }
}
