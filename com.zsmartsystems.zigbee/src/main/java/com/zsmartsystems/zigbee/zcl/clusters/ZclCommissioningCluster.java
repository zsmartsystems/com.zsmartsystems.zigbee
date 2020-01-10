/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.ResetStartupParametersCommand;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.ResetStartupParametersResponse;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.RestartDeviceCommand;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.RestartDeviceResponseResponse;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.RestoreStartupParametersCommand;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.RestoreStartupParametersResponse;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.SaveStartupParametersCommand;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.SaveStartupParametersResponse;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.ZclCommissioningCommand;

/**
 * <b>Commissioning</b> cluster implementation (<i>Cluster ID 0x0015</i>).
 * <p>
 * This cluster provides attributes and commands pertaining to the commissioning and
 * management of ZigBee devices operating in a network.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T10:15:41Z")
public class ZclCommissioningCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0015;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Commissioning";

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, RestartDeviceResponseResponse.class);
        commandMap.put(0x0001, SaveStartupParametersResponse.class);
        commandMap.put(0x0002, RestoreStartupParametersResponse.class);
        commandMap.put(0x0003, ResetStartupParametersResponse.class);

        return commandMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, RestartDeviceCommand.class);
        commandMap.put(0x0001, SaveStartupParametersCommand.class);
        commandMap.put(0x0002, RestoreStartupParametersCommand.class);
        commandMap.put(0x0003, ResetStartupParametersCommand.class);

        return commandMap;
    }

    /**
     * Default constructor to create a Commissioning cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclCommissioningCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Sends a {@link ZclCommissioningCommand} and returns the {@link Future} to the result which will complete when the remote
     * device response is received, or the request times out.
     *
     * @param command the {@link ZclCommissioningCommand} to send
     * @return the command result future
     */
    public Future<CommandResult> sendCommand(ZclCommissioningCommand command) {
        return super.sendCommand(command);
    }

    /**
     * Sends a response to the command. This method sets all the common elements of the response based on the command -
     * eg transactionId, direction, address...
     *
     * @param command the {@link ZclCommissioningCommand} to which the response is being sent
     * @param response the {@link ZclCommissioningCommand} to send
     */
    public void sendResponse(ZclCommissioningCommand command, ZclCommissioningCommand response) {
        super.sendResponse(command, response);
    }

    /**
     * The Restart Device Command
     *
     * @param option {@link Integer} Option
     * @param delay {@link Integer} Delay
     * @param jitter {@link Integer} Jitter
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.restartDeviceCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new restartDeviceCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> restartDeviceCommand(Integer option, Integer delay, Integer jitter) {
        RestartDeviceCommand command = new RestartDeviceCommand();

        // Set the fields
        command.setOption(option);
        command.setDelay(delay);
        command.setJitter(jitter);

        return sendCommand(command);
    }

    /**
     * The Save Startup Parameters Command
     *
     * @param option {@link Integer} Option
     * @param index {@link Integer} Index
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.saveStartupParametersCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new saveStartupParametersCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> saveStartupParametersCommand(Integer option, Integer index) {
        SaveStartupParametersCommand command = new SaveStartupParametersCommand();

        // Set the fields
        command.setOption(option);
        command.setIndex(index);

        return sendCommand(command);
    }

    /**
     * The Restore Startup Parameters Command
     *
     * @param option {@link Integer} Option
     * @param index {@link Integer} Index
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.restoreStartupParametersCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new restoreStartupParametersCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> restoreStartupParametersCommand(Integer option, Integer index) {
        RestoreStartupParametersCommand command = new RestoreStartupParametersCommand();

        // Set the fields
        command.setOption(option);
        command.setIndex(index);

        return sendCommand(command);
    }

    /**
     * The Reset Startup Parameters Command
     *
     * @param option {@link Integer} Option
     * @param index {@link Integer} Index
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.resetStartupParametersCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new resetStartupParametersCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> resetStartupParametersCommand(Integer option, Integer index) {
        ResetStartupParametersCommand command = new ResetStartupParametersCommand();

        // Set the fields
        command.setOption(option);
        command.setIndex(index);

        return sendCommand(command);
    }

    /**
     * The Restart Device Response Response
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.restartDeviceResponseResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new restartDeviceResponseResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> restartDeviceResponseResponse(Integer status) {
        RestartDeviceResponseResponse command = new RestartDeviceResponseResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Save Startup Parameters Response
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.saveStartupParametersResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new saveStartupParametersResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> saveStartupParametersResponse(Integer status) {
        SaveStartupParametersResponse command = new SaveStartupParametersResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Restore Startup Parameters Response
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.restoreStartupParametersResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new restoreStartupParametersResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> restoreStartupParametersResponse(Integer status) {
        RestoreStartupParametersResponse command = new RestoreStartupParametersResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }

    /**
     * The Reset Startup Parameters Response
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.resetStartupParametersResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new resetStartupParametersResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> resetStartupParametersResponse(Integer status) {
        ResetStartupParametersResponse command = new ResetStartupParametersResponse();

        // Set the fields
        command.setStatus(status);

        return sendCommand(command);
    }
}
