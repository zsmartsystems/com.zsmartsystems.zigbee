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
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.ResetStartupParametersCommand;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.ResetStartupParametersResponse;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.RestartDeviceCommand;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.RestartDeviceResponseResponse;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.RestoreStartupParametersCommand;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.RestoreStartupParametersResponse;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.SaveStartupParametersCommand;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.SaveStartupParametersResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * <b>Commissioning</b> cluster implementation (<i>Cluster ID 0x0015</i>).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclCommissioningCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0015;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Commissioning";

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(0);

        return attributeMap;
    }

    /**
     * Default constructor to create a Commissioning cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclCommissioningCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * The Restart Device Command
     *
     * @param option {@link Integer} Option
     * @param delay {@link Integer} Delay
     * @param jitter {@link Integer} Jitter
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> restartDeviceCommand(Integer option, Integer delay, Integer jitter) {
        RestartDeviceCommand command = new RestartDeviceCommand();

        // Set the fields
        command.setOption(option);
        command.setDelay(delay);
        command.setJitter(jitter);

        return send(command);
    }

    /**
     * The Save Startup Parameters Command
     *
     * @param option {@link Integer} Option
     * @param index {@link Integer} Index
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> saveStartupParametersCommand(Integer option, Integer index) {
        SaveStartupParametersCommand command = new SaveStartupParametersCommand();

        // Set the fields
        command.setOption(option);
        command.setIndex(index);

        return send(command);
    }

    /**
     * The Restore Startup Parameters Command
     *
     * @param option {@link Integer} Option
     * @param index {@link Integer} Index
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> restoreStartupParametersCommand(Integer option, Integer index) {
        RestoreStartupParametersCommand command = new RestoreStartupParametersCommand();

        // Set the fields
        command.setOption(option);
        command.setIndex(index);

        return send(command);
    }

    /**
     * The Reset Startup Parameters Command
     *
     * @param option {@link Integer} Option
     * @param index {@link Integer} Index
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> resetStartupParametersCommand(Integer option, Integer index) {
        ResetStartupParametersCommand command = new ResetStartupParametersCommand();

        // Set the fields
        command.setOption(option);
        command.setIndex(index);

        return send(command);
    }

    /**
     * The Restart Device Response Response
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> restartDeviceResponseResponse(Integer status) {
        RestartDeviceResponseResponse command = new RestartDeviceResponseResponse();

        // Set the fields
        command.setStatus(status);

        return send(command);
    }

    /**
     * The Save Startup Parameters Response
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> saveStartupParametersResponse(Integer status) {
        SaveStartupParametersResponse command = new SaveStartupParametersResponse();

        // Set the fields
        command.setStatus(status);

        return send(command);
    }

    /**
     * The Restore Startup Parameters Response
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> restoreStartupParametersResponse(Integer status) {
        RestoreStartupParametersResponse command = new RestoreStartupParametersResponse();

        // Set the fields
        command.setStatus(status);

        return send(command);
    }

    /**
     * The Reset Startup Parameters Response
     *
     * @param status {@link Integer} Status
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> resetStartupParametersResponse(Integer status) {
        ResetStartupParametersResponse command = new ResetStartupParametersResponse();

        // Set the fields
        command.setStatus(status);

        return send(command);
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // RESTART_DEVICE_COMMAND
                return new RestartDeviceCommand();
            case 1: // SAVE_STARTUP_PARAMETERS_COMMAND
                return new SaveStartupParametersCommand();
            case 2: // RESTORE_STARTUP_PARAMETERS_COMMAND
                return new RestoreStartupParametersCommand();
            case 3: // RESET_STARTUP_PARAMETERS_COMMAND
                return new ResetStartupParametersCommand();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 0: // RESTART_DEVICE_RESPONSE_RESPONSE
                return new RestartDeviceResponseResponse();
            case 1: // SAVE_STARTUP_PARAMETERS_RESPONSE
                return new SaveStartupParametersResponse();
            case 2: // RESTORE_STARTUP_PARAMETERS_RESPONSE
                return new RestoreStartupParametersResponse();
            case 3: // RESET_STARTUP_PARAMETERS_RESPONSE
                return new ResetStartupParametersResponse();
            default:
                return null;
        }
    }
}
