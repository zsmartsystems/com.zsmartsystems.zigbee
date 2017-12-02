/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.ArmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.ArmResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.BypassCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.EmergencyCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.FireCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetZoneIdMapCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetZoneIdMapResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetZoneInformationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetZoneInformationResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.PanicCommand;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * <b>IAS ACE</b> cluster implementation (<i>Cluster ID 0x0501</i>).
 * <p>
 * The IAS ACE cluster defines an interface to the functionality of any Ancillary
 * Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE
 * device can access a IAS CIE device and manipulate the IAS system, on behalf of a
 * level-2 user.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclIasAceCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0501;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "IAS ACE";

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(0);

        return attributeMap;
    }

    /**
     * Default constructor to create a IAS ACE cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclIasAceCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * The Arm Command
     *
     * @param armMode {@link Integer} Arm Mode
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> armCommand(Integer armMode) {
        ArmCommand command = new ArmCommand();

        // Set the fields
        command.setArmMode(armMode);

        return send(command);
    }

    /**
     * The Bypass Command
     *
     * @param numberOfZones {@link Integer} Number of Zones
     * @param zoneIDs {@link List<Integer>} Zone IDs
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> bypassCommand(Integer numberOfZones, List<Integer> zoneIDs) {
        BypassCommand command = new BypassCommand();

        // Set the fields
        command.setNumberOfZones(numberOfZones);
        command.setZoneIDs(zoneIDs);

        return send(command);
    }

    /**
     * The Emergency Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> emergencyCommand() {
        EmergencyCommand command = new EmergencyCommand();

        return send(command);
    }

    /**
     * The Fire Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> fireCommand() {
        FireCommand command = new FireCommand();

        return send(command);
    }

    /**
     * The Panic Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> panicCommand() {
        PanicCommand command = new PanicCommand();

        return send(command);
    }

    /**
     * The Get Zone ID Map Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getZoneIdMapCommand() {
        GetZoneIdMapCommand command = new GetZoneIdMapCommand();

        return send(command);
    }

    /**
     * The Get Zone Information Command
     *
     * @param zoneId {@link Integer} Zone ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getZoneInformationCommand(Integer zoneId) {
        GetZoneInformationCommand command = new GetZoneInformationCommand();

        // Set the fields
        command.setZoneId(zoneId);

        return send(command);
    }

    /**
     * The Arm Response
     *
     * @param armNotification {@link Integer} Arm Notification
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> armResponse(Integer armNotification) {
        ArmResponse command = new ArmResponse();

        // Set the fields
        command.setArmNotification(armNotification);

        return send(command);
    }

    /**
     * The Get Zone ID Map Response
     *
     * @param zoneIdMapSection0 {@link Integer} Zone ID Map section 0
     * @param zoneIdMapSection1 {@link Integer} Zone ID Map section 1
     * @param zoneIdMapSection2 {@link Integer} Zone ID Map section 2
     * @param zoneIdMapSection3 {@link Integer} Zone ID Map section 3
     * @param zoneIdMapSection4 {@link Integer} Zone ID Map section 4
     * @param zoneIdMapSection5 {@link Integer} Zone ID Map section 5
     * @param zoneIdMapSection6 {@link Integer} Zone ID Map section 6
     * @param zoneIdMapSection7 {@link Integer} Zone ID Map section 7
     * @param zoneIdMapSection8 {@link Integer} Zone ID Map section 8
     * @param zoneIdMapSection9 {@link Integer} Zone ID Map section 9
     * @param zoneIdMapSection10 {@link Integer} Zone ID Map section 10
     * @param zoneIdMapSection11 {@link Integer} Zone ID Map section 11
     * @param zoneIdMapSection12 {@link Integer} Zone ID Map section 12
     * @param zoneIdMapSection13 {@link Integer} Zone ID Map section 13
     * @param zoneIdMapSection14 {@link Integer} Zone ID Map section 14
     * @param zoneIdMapSection15 {@link Integer} Zone ID Map section 15
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getZoneIdMapResponse(Integer zoneIdMapSection0, Integer zoneIdMapSection1, Integer zoneIdMapSection2, Integer zoneIdMapSection3, Integer zoneIdMapSection4, Integer zoneIdMapSection5, Integer zoneIdMapSection6, Integer zoneIdMapSection7, Integer zoneIdMapSection8, Integer zoneIdMapSection9, Integer zoneIdMapSection10, Integer zoneIdMapSection11, Integer zoneIdMapSection12, Integer zoneIdMapSection13, Integer zoneIdMapSection14, Integer zoneIdMapSection15) {
        GetZoneIdMapResponse command = new GetZoneIdMapResponse();

        // Set the fields
        command.setZoneIdMapSection0(zoneIdMapSection0);
        command.setZoneIdMapSection1(zoneIdMapSection1);
        command.setZoneIdMapSection2(zoneIdMapSection2);
        command.setZoneIdMapSection3(zoneIdMapSection3);
        command.setZoneIdMapSection4(zoneIdMapSection4);
        command.setZoneIdMapSection5(zoneIdMapSection5);
        command.setZoneIdMapSection6(zoneIdMapSection6);
        command.setZoneIdMapSection7(zoneIdMapSection7);
        command.setZoneIdMapSection8(zoneIdMapSection8);
        command.setZoneIdMapSection9(zoneIdMapSection9);
        command.setZoneIdMapSection10(zoneIdMapSection10);
        command.setZoneIdMapSection11(zoneIdMapSection11);
        command.setZoneIdMapSection12(zoneIdMapSection12);
        command.setZoneIdMapSection13(zoneIdMapSection13);
        command.setZoneIdMapSection14(zoneIdMapSection14);
        command.setZoneIdMapSection15(zoneIdMapSection15);

        return send(command);
    }

    /**
     * The Get Zone Information Response
     *
     * @param zoneId {@link Integer} Zone ID
     * @param zoneType {@link Integer} Zone Type
     * @param ieeeAddress {@link IeeeAddress} IEEE address
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getZoneInformationResponse(Integer zoneId, Integer zoneType, IeeeAddress ieeeAddress) {
        GetZoneInformationResponse command = new GetZoneInformationResponse();

        // Set the fields
        command.setZoneId(zoneId);
        command.setZoneType(zoneType);
        command.setIeeeAddress(ieeeAddress);

        return send(command);
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // ARM_COMMAND
                return new ArmCommand();
            case 1: // BYPASS_COMMAND
                return new BypassCommand();
            case 2: // EMERGENCY_COMMAND
                return new EmergencyCommand();
            case 3: // FIRE_COMMAND
                return new FireCommand();
            case 4: // PANIC_COMMAND
                return new PanicCommand();
            case 5: // GET_ZONE_ID_MAP_COMMAND
                return new GetZoneIdMapCommand();
            case 6: // GET_ZONE_INFORMATION_COMMAND
                return new GetZoneInformationCommand();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 0: // ARM_RESPONSE
                return new ArmResponse();
            case 1: // GET_ZONE_ID_MAP_RESPONSE
                return new GetZoneIdMapResponse();
            case 2: // GET_ZONE_INFORMATION_RESPONSE
                return new GetZoneInformationResponse();
            default:
                return null;
        }
    }
}
