/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.ArmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.ArmResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.BypassCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.BypassResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.EmergencyCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.FireCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetBypassedZoneListCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetPanelStatusCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetPanelStatusResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetZoneIdMapCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetZoneIdMapResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetZoneInformationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetZoneInformationResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetZoneStatusCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetZoneStatusResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.PanelStatusChangedCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.PanicCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.SetBypassedZoneListCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.ZoneStatusChangedCommand;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import javax.annotation.Generated;

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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-10-24T19:40:52Z")
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
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclIasAceCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * The Arm Command
     * <p>
     * On receipt of this command, the receiving device sets its arm mode according to the value of the Arm Mode field. It
     * is not guaranteed that an Arm command will succeed. Based on the current state of
     * the IAS CIE, and its related devices, the command can be rejected. The device SHALL generate an Arm Response command
     * to indicate the resulting armed state
     *
     * @param armMode {@link Integer} Arm Mode
     * @param armDisarmCode {@link String} Arm/Disarm Code
     * @param zoneId {@link Integer} Zone ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> armCommand(Integer armMode, String armDisarmCode, Integer zoneId) {
        ArmCommand command = new ArmCommand();

        // Set the fields
        command.setArmMode(armMode);
        command.setArmDisarmCode(armDisarmCode);
        command.setZoneId(zoneId);

        return send(command);
    }

    /**
     * The Bypass Command
     * <p>
     * Provides IAS ACE clients with a method to send zone bypass requests to the IAS ACE server.
     * Bypassed zones MAYbe faulted or in alarm but will not trigger the security system to go into alarm.
     * For example, a user MAYwish to allow certain windows in his premises protected by an IAS Zone server to
     * be left open while the user leaves the premises. The user could bypass the IAS Zone server protecting
     * the window on his IAS ACE client (e.g., security keypad), and if the IAS ACE server indicates that zone is
     * successfully by-passed, arm his security system while he is away.
     *
     * @param numberOfZones {@link Integer} Number of Zones
     * @param zoneIDs {@link List<Integer>} Zone IDs
     * @param armDisarmCode {@link String} Arm/Disarm Code
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> bypassCommand(Integer numberOfZones, List<Integer> zoneIDs, String armDisarmCode) {
        BypassCommand command = new BypassCommand();

        // Set the fields
        command.setNumberOfZones(numberOfZones);
        command.setZoneIDs(zoneIDs);
        command.setArmDisarmCode(armDisarmCode);

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
     * The Get Panel Status Command
     * <p>
     * This command is used by ACE clients to request an update to the status (e.g., security
     * system arm state) of the ACE server (i.e., the IAS CIE). In particular, this command is
     * useful for battery-powered ACE clients with polling rates longer than the ZigBee standard
     * check-in rate.
     * <br>
     * On receipt of this command, the ACE server responds with the status of the security system.
     * The IAS ACE server SHALL generate a Get Panel Status Response command.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPanelStatusCommand() {
        GetPanelStatusCommand command = new GetPanelStatusCommand();

        return send(command);
    }

    /**
     * The Get Bypassed Zone List Command
     * <p>
     * Provides IAS ACE clients with a way to retrieve the list of zones to be bypassed. This provides them with the ability
     * to provide greater local functionality (i.e., at the IAS ACE client) for users to modify the Bypassed Zone List and reduce
     * communications to the IAS ACE server when trying to arm the CIE security system.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getBypassedZoneListCommand() {
        GetBypassedZoneListCommand command = new GetBypassedZoneListCommand();

        return send(command);
    }

    /**
     * The Get Zone Status Command
     * <p>
     * This command is used by ACE clients to request an update of the status of the IAS Zone devices managed by the ACE server
     * (i.e., the IAS CIE). In particular, this command is useful for battery-powered ACE clients with polling rates longer than
     * the ZigBee standard check-in rate. The command is similar to the Get Attributes Supported command in that it specifies a
     * starting Zone ID and a number of Zone IDs for which information is requested. Depending on the number of IAS Zone devices
     * managed by the IAS ACE server, sending the Zone Status of all zones MAY not fit into a single Get ZoneStatus Response command.
     * IAS ACE clients MAY need to send multiple Get Zone Status commands in order to get the information they seek.
     *
     * @param startingZoneId {@link Integer} Starting Zone ID
     * @param maxZoneIDs {@link Integer} Max Zone IDs
     * @param zoneStatusMaskFlag {@link Boolean} Zone Status Mask Flag
     * @param zoneStatusMask {@link Integer} Zone Status Mask
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getZoneStatusCommand(Integer startingZoneId, Integer maxZoneIDs, Boolean zoneStatusMaskFlag, Integer zoneStatusMask) {
        GetZoneStatusCommand command = new GetZoneStatusCommand();

        // Set the fields
        command.setStartingZoneId(startingZoneId);
        command.setMaxZoneIDs(maxZoneIDs);
        command.setZoneStatusMaskFlag(zoneStatusMaskFlag);
        command.setZoneStatusMask(zoneStatusMask);

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
     * <p>
     * The 16 fields of the payload indicate whether each of the Zone IDs from 0 to 0xff is allocated or not. If bit n
     * of Zone ID Map section N is set to 1, then Zone ID (16 x N + n ) is allocated, else it is not allocated
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
     * @param zoneLabel {@link String} Zone Label
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getZoneInformationResponse(Integer zoneId, Integer zoneType, IeeeAddress ieeeAddress, String zoneLabel) {
        GetZoneInformationResponse command = new GetZoneInformationResponse();

        // Set the fields
        command.setZoneId(zoneId);
        command.setZoneType(zoneType);
        command.setIeeeAddress(ieeeAddress);
        command.setZoneLabel(zoneLabel);

        return send(command);
    }

    /**
     * The Zone Status Changed Command
     * <p>
     * This command updates ACE clients in the system of changes to zone status recorded by the ACE server (e.g., IAS CIE device).
     * An IAS ACE server SHOULD send a Zone Status Changed command upon a change to an IAS Zone device’s ZoneStatus that it manages (i.e.,
     * IAS ACE server SHOULD send a Zone Status Changed command upon receipt of a Zone Status Change Notification command).
     *
     * @param zoneId {@link Integer} Zone ID
     * @param zoneStatus {@link Integer} Zone Status
     * @param audibleNotification {@link Integer} Audible Notification
     * @param zoneLabel {@link String} Zone Label
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> zoneStatusChangedCommand(Integer zoneId, Integer zoneStatus, Integer audibleNotification, String zoneLabel) {
        ZoneStatusChangedCommand command = new ZoneStatusChangedCommand();

        // Set the fields
        command.setZoneId(zoneId);
        command.setZoneStatus(zoneStatus);
        command.setAudibleNotification(audibleNotification);
        command.setZoneLabel(zoneLabel);

        return send(command);
    }

    /**
     * The Panel Status Changed Command
     * <p>
     * This command updates ACE clients in the system of changes to panel status recorded by the ACE server (e.g., IAS CIE
     * device).Sending the Panel Status Changed command (vs.the Get Panel Status and Get Panel Status Response method) is
     * generally useful only when there are IAS ACE clients that data poll within the retry timeout of the network (e.g., less than
     * 7.68 seconds).
     * <br>
     * An IAS ACE server SHALL send a Panel Status Changed command upon a change to the IAS CIE’s panel status (e.g.,
     * Disarmed to Arming Away/Stay/Night, Arming Away/Stay/Night to Armed, Armed to Disarmed) as defined in the Panel Status field.
     * <br>
     * When Panel Status is Arming Away/Stay/Night, an IAS ACE server SHOULD send Panel Status Changed commands every second in order to
     * update the Seconds Remaining. In some markets (e.g., North America), the final 10 seconds of the Arming Away/Stay/Night sequence
     * requires a separate audible notification (e.g., a double tone).
     *
     * @param panelStatus {@link Integer} Panel Status
     * @param secondsRemaining {@link Integer} Seconds Remaining
     * @param audibleNotification {@link Integer} Audible Notification
     * @param alarmStatus {@link Integer} Alarm Status
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> panelStatusChangedCommand(Integer panelStatus, Integer secondsRemaining, Integer audibleNotification, Integer alarmStatus) {
        PanelStatusChangedCommand command = new PanelStatusChangedCommand();

        // Set the fields
        command.setPanelStatus(panelStatus);
        command.setSecondsRemaining(secondsRemaining);
        command.setAudibleNotification(audibleNotification);
        command.setAlarmStatus(alarmStatus);

        return send(command);
    }

    /**
     * The Get Panel Status Response
     * <p>
     * This command updates requesting IAS ACE clients in the system of changes to the security panel status recorded by
     * the ACE server (e.g., IAS CIE device).
     *
     * @param panelStatus {@link Integer} Panel Status
     * @param secondsRemaining {@link Integer} Seconds Remaining
     * @param audibleNotification {@link Integer} Audible Notification
     * @param alarmStatus {@link Integer} Alarm Status
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPanelStatusResponse(Integer panelStatus, Integer secondsRemaining, Integer audibleNotification, Integer alarmStatus) {
        GetPanelStatusResponse command = new GetPanelStatusResponse();

        // Set the fields
        command.setPanelStatus(panelStatus);
        command.setSecondsRemaining(secondsRemaining);
        command.setAudibleNotification(audibleNotification);
        command.setAlarmStatus(alarmStatus);

        return send(command);
    }

    /**
     * The Set Bypassed Zone List Command
     * <p>
     * Sets the list of bypassed zones on the IAS ACE client. This command can be sent either as a response to the
     * GetBypassedZoneList command or unsolicited when the list of bypassed zones changes on the ACE server.
     *
     * @param zoneId {@link List<Integer>} Zone ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setBypassedZoneListCommand(List<Integer> zoneId) {
        SetBypassedZoneListCommand command = new SetBypassedZoneListCommand();

        // Set the fields
        command.setZoneId(zoneId);

        return send(command);
    }

    /**
     * The Bypass Response
     * <p>
     * Provides the response of the security panel to the request from the IAS ACE client to bypass zones via a Bypass command.
     *
     * @param bypassResult {@link List<Integer>} Bypass Result
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> bypassResponse(List<Integer> bypassResult) {
        BypassResponse command = new BypassResponse();

        // Set the fields
        command.setBypassResult(bypassResult);

        return send(command);
    }

    /**
     * The Get Zone Status Response
     * <p>
     * This command updates requesting IAS ACE clients in the system of changes to the IAS Zone server statuses recorded
     * by the ACE server (e.g., IAS CIE device).
     *
     * @param zoneStatusComplete {@link Boolean} Zone Status Complete
     * @param numberOfZones {@link Integer} Number of zones
     * @param iasAceZoneStatus {@link Integer} Ias Ace Zone Status
     * @param zoneId {@link Integer} Zone Id
     * @param zoneStatus {@link Integer} Zone Status
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getZoneStatusResponse(Boolean zoneStatusComplete, Integer numberOfZones, Integer iasAceZoneStatus, Integer zoneId, Integer zoneStatus) {
        GetZoneStatusResponse command = new GetZoneStatusResponse();

        // Set the fields
        command.setZoneStatusComplete(zoneStatusComplete);
        command.setNumberOfZones(numberOfZones);
        command.setIasAceZoneStatus(iasAceZoneStatus);
        command.setZoneId(zoneId);
        command.setZoneStatus(zoneStatus);

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
            case 7: // GET_PANEL_STATUS_COMMAND
                return new GetPanelStatusCommand();
            case 8: // GET_BYPASSED_ZONE_LIST_COMMAND
                return new GetBypassedZoneListCommand();
            case 9: // GET_ZONE_STATUS_COMMAND
                return new GetZoneStatusCommand();
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
            case 3: // ZONE_STATUS_CHANGED_COMMAND
                return new ZoneStatusChangedCommand();
            case 4: // PANEL_STATUS_CHANGED_COMMAND
                return new PanelStatusChangedCommand();
            case 5: // GET_PANEL_STATUS_RESPONSE
                return new GetPanelStatusResponse();
            case 6: // SET_BYPASSED_ZONE_LIST_COMMAND
                return new SetBypassedZoneListCommand();
            case 7: // BYPASS_RESPONSE
                return new BypassResponse();
            case 8: // GET_ZONE_STATUS_RESPONSE
                return new GetZoneStatusResponse();
            default:
                return null;
        }
    }
}
