/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.protocol;

import java.lang.reflect.Constructor;

import com.zsmartsystems.zigbee.zcl.ZclCommand;

import com.zsmartsystems.zigbee.zcl.clusters.basic.ResetToFactoryDefaultsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.identify.IdentifyCommand;
import com.zsmartsystems.zigbee.zcl.clusters.identify.IdentifyQueryResponse;
import com.zsmartsystems.zigbee.zcl.clusters.identify.IdentifyQueryCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.RemoveGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.RemoveGroupResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.RemoveAllGroupsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupIfIdentifyingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.AddSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.AddSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.ViewSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.ViewSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RemoveSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RemoveSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RemoveAllScenesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RemoveAllScenesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.StoreSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.StoreSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RecallSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.GetSceneMembershipResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.GetSceneMembershipCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.ToggleCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveToLevelCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.StepCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.StopCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveToLevelWithOnOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveWithOnOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.StepWithOnOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.Stop2Command;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.ResetAlarmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.AlarmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.ResetAllAlarmsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.GetAlarmResponse;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.GetAlarmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.ResetAlarmLogCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.SetAbsoluteLocationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.DeviceConfigurationResponse;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.SetDeviceConfigurationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.LocationDataResponse;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.GetDeviceConfigurationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.LocationDataNotificationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.GetLocationDataCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.CompactLocationDataNotificationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.RssiResponse;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.RssiPingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.SendPingsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.RssiRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.AnchorNodeAnnounceCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.ReportRssiMeasurementsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.RequestOwnLocationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.RestartDeviceCommand;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.RestartDeviceResponseResponse;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.SaveStartupParametersCommand;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.SaveStartupParametersResponse;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.RestoreStartupParametersCommand;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.RestoreStartupParametersResponse;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.ResetStartupParametersCommand;
import com.zsmartsystems.zigbee.zcl.clusters.commissioning.ResetStartupParametersResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.LockDoorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.LockDoorResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockDoorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockDoorResponse;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.SetpointRaiseLowerCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToHueAndSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToColorTemperatureCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneStatusChangeNotificationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.ArmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.ArmResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.BypassCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetZoneIdMapResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.EmergencyCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetZoneInformationResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.FireCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.PanicCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetZoneIdMapCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.GetZoneInformationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaswd.StartWarningCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaswd.SquawkCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesUndividedCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesNoResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadReportingConfigurationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadReportingConfigurationResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReportAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesStructuredCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesStructuredCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesStructuredResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverCommandsReceived;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverCommandsReceivedResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverCommandsGenerated;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverCommandsGeneratedResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesExtended;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesExtendedResponse;


/**
 * Code is auto-generated. Modifications may be overwritten!
 */
public enum ZclCommandType {
    /**
     * ADD_GROUP_COMMAND: Add Group Command
     * <p>
     * See {@link AddGroupCommand}
     */
    ADD_GROUP_COMMAND(0x0004, 0, AddGroupCommand.class, true),
    /**
     * ADD_GROUP_IF_IDENTIFYING_COMMAND: Add Group If Identifying Command
     * <p>
     * See {@link AddGroupIfIdentifyingCommand}
     */
    ADD_GROUP_IF_IDENTIFYING_COMMAND(0x0004, 5, AddGroupIfIdentifyingCommand.class, true),
    /**
     * ADD_GROUP_RESPONSE: Add Group Response
     * <p>
     * See {@link AddGroupResponse}
     */
    ADD_GROUP_RESPONSE(0x0004, 0, AddGroupResponse.class, false),
    /**
     * ADD_SCENE_COMMAND: Add Scene Command
     * <p>
     * See {@link AddSceneCommand}
     */
    ADD_SCENE_COMMAND(0x0005, 0, AddSceneCommand.class, true),
    /**
     * ADD_SCENE_RESPONSE: Add Scene Response
     * <p>
     * See {@link AddSceneResponse}
     */
    ADD_SCENE_RESPONSE(0x0005, 0, AddSceneResponse.class, false),
    /**
     * ALARM_COMMAND: Alarm Command
     * <p>
     * See {@link AlarmCommand}
     */
    ALARM_COMMAND(0x0009, 0, AlarmCommand.class, false),
    /**
     * ANCHOR_NODE_ANNOUNCE_COMMAND: Anchor Node Announce Command
     * <p>
     * See {@link AnchorNodeAnnounceCommand}
     */
    ANCHOR_NODE_ANNOUNCE_COMMAND(0x000B, 6, AnchorNodeAnnounceCommand.class, true),
    /**
     * ARM_COMMAND: Arm Command
     * <p>
     * See {@link ArmCommand}
     */
    ARM_COMMAND(0x0501, 0, ArmCommand.class, true),
    /**
     * ARM_RESPONSE: Arm Response
     * <p>
     * See {@link ArmResponse}
     */
    ARM_RESPONSE(0x0501, 0, ArmResponse.class, false),
    /**
     * BYPASS_COMMAND: Bypass Command
     * <p>
     * See {@link BypassCommand}
     */
    BYPASS_COMMAND(0x0501, 1, BypassCommand.class, true),
    /**
     * COMPACT_LOCATION_DATA_NOTIFICATION_COMMAND: Compact Location Data Notification Command
     * <p>
     * See {@link CompactLocationDataNotificationCommand}
     */
    COMPACT_LOCATION_DATA_NOTIFICATION_COMMAND(0x000B, 3, CompactLocationDataNotificationCommand.class, false),
    /**
     * CONFIGURE_REPORTING_COMMAND: Configure Reporting Command
     * <p>
     * See {@link ConfigureReportingCommand}
     */
    CONFIGURE_REPORTING_COMMAND(0xFFFF, 6, ConfigureReportingCommand.class, true),
    /**
     * CONFIGURE_REPORTING_RESPONSE: Configure Reporting Response
     * <p>
     * See {@link ConfigureReportingResponse}
     */
    CONFIGURE_REPORTING_RESPONSE(0xFFFF, 7, ConfigureReportingResponse.class, true),
    /**
     * DEFAULT_RESPONSE: Default Response
     * <p>
     * See {@link DefaultResponse}
     */
    DEFAULT_RESPONSE(0xFFFF, 11, DefaultResponse.class, true),
    /**
     * DEVICE_CONFIGURATION_RESPONSE: Device Configuration Response
     * <p>
     * See {@link DeviceConfigurationResponse}
     */
    DEVICE_CONFIGURATION_RESPONSE(0x000B, 0, DeviceConfigurationResponse.class, false),
    /**
     * DISCOVER_ATTRIBUTES_COMMAND: Discover Attributes Command
     * <p>
     * See {@link DiscoverAttributesCommand}
     */
    DISCOVER_ATTRIBUTES_COMMAND(0xFFFF, 12, DiscoverAttributesCommand.class, true),
    /**
     * DISCOVER_ATTRIBUTES_EXTENDED: Discover Attributes Extended
     * <p>
     * See {@link DiscoverAttributesExtended}
     */
    DISCOVER_ATTRIBUTES_EXTENDED(0xFFFF, 21, DiscoverAttributesExtended.class, true),
    /**
     * DISCOVER_ATTRIBUTES_EXTENDED_RESPONSE: Discover Attributes Extended Response
     * <p>
     * See {@link DiscoverAttributesExtendedResponse}
     */
    DISCOVER_ATTRIBUTES_EXTENDED_RESPONSE(0xFFFF, 22, DiscoverAttributesExtendedResponse.class, true),
    /**
     * DISCOVER_ATTRIBUTES_RESPONSE: Discover Attributes Response
     * <p>
     * See {@link DiscoverAttributesResponse}
     */
    DISCOVER_ATTRIBUTES_RESPONSE(0xFFFF, 13, DiscoverAttributesResponse.class, true),
    /**
     * DISCOVER_COMMANDS_GENERATED: Discover Commands Generated
     * <p>
     * See {@link DiscoverCommandsGenerated}
     */
    DISCOVER_COMMANDS_GENERATED(0xFFFF, 19, DiscoverCommandsGenerated.class, true),
    /**
     * DISCOVER_COMMANDS_GENERATED_RESPONSE: Discover Commands Generated Response
     * <p>
     * See {@link DiscoverCommandsGeneratedResponse}
     */
    DISCOVER_COMMANDS_GENERATED_RESPONSE(0xFFFF, 20, DiscoverCommandsGeneratedResponse.class, true),
    /**
     * DISCOVER_COMMANDS_RECEIVED: Discover Commands Received
     * <p>
     * See {@link DiscoverCommandsReceived}
     */
    DISCOVER_COMMANDS_RECEIVED(0xFFFF, 17, DiscoverCommandsReceived.class, true),
    /**
     * DISCOVER_COMMANDS_RECEIVED_RESPONSE: Discover Commands Received Response
     * <p>
     * See {@link DiscoverCommandsReceivedResponse}
     */
    DISCOVER_COMMANDS_RECEIVED_RESPONSE(0xFFFF, 18, DiscoverCommandsReceivedResponse.class, true),
    /**
     * EMERGENCY_COMMAND: Emergency Command
     * <p>
     * See {@link EmergencyCommand}
     */
    EMERGENCY_COMMAND(0x0501, 2, EmergencyCommand.class, true),
    /**
     * FIRE_COMMAND: Fire Command
     * <p>
     * See {@link FireCommand}
     */
    FIRE_COMMAND(0x0501, 3, FireCommand.class, true),
    /**
     * GET_ALARM_COMMAND: Get Alarm Command
     * <p>
     * See {@link GetAlarmCommand}
     */
    GET_ALARM_COMMAND(0x0009, 2, GetAlarmCommand.class, true),
    /**
     * GET_ALARM_RESPONSE: Get Alarm Response
     * <p>
     * See {@link GetAlarmResponse}
     */
    GET_ALARM_RESPONSE(0x0009, 1, GetAlarmResponse.class, false),
    /**
     * GET_DEVICE_CONFIGURATION_COMMAND: Get Device Configuration Command
     * <p>
     * See {@link GetDeviceConfigurationCommand}
     */
    GET_DEVICE_CONFIGURATION_COMMAND(0x000B, 2, GetDeviceConfigurationCommand.class, true),
    /**
     * GET_GROUP_MEMBERSHIP_COMMAND: Get Group Membership Command
     * <p>
     * See {@link GetGroupMembershipCommand}
     */
    GET_GROUP_MEMBERSHIP_COMMAND(0x0004, 2, GetGroupMembershipCommand.class, true),
    /**
     * GET_GROUP_MEMBERSHIP_RESPONSE: Get Group Membership Response
     * <p>
     * See {@link GetGroupMembershipResponse}
     */
    GET_GROUP_MEMBERSHIP_RESPONSE(0x0004, 2, GetGroupMembershipResponse.class, false),
    /**
     * GET_LOCATION_DATA_COMMAND: Get Location Data Command
     * <p>
     * See {@link GetLocationDataCommand}
     */
    GET_LOCATION_DATA_COMMAND(0x000B, 3, GetLocationDataCommand.class, true),
    /**
     * GET_SCENE_MEMBERSHIP_COMMAND: Get Scene Membership Command
     * <p>
     * See {@link GetSceneMembershipCommand}
     */
    GET_SCENE_MEMBERSHIP_COMMAND(0x0005, 6, GetSceneMembershipCommand.class, true),
    /**
     * GET_SCENE_MEMBERSHIP_RESPONSE: Get Scene Membership Response
     * <p>
     * See {@link GetSceneMembershipResponse}
     */
    GET_SCENE_MEMBERSHIP_RESPONSE(0x0005, 5, GetSceneMembershipResponse.class, false),
    /**
     * GET_ZONE_ID_MAP_COMMAND: Get Zone ID Map Command
     * <p>
     * See {@link GetZoneIdMapCommand}
     */
    GET_ZONE_ID_MAP_COMMAND(0x0501, 5, GetZoneIdMapCommand.class, true),
    /**
     * GET_ZONE_ID_MAP_RESPONSE: Get Zone ID Map Response
     * <p>
     * See {@link GetZoneIdMapResponse}
     */
    GET_ZONE_ID_MAP_RESPONSE(0x0501, 1, GetZoneIdMapResponse.class, false),
    /**
     * GET_ZONE_INFORMATION_COMMAND: Get Zone Information Command
     * <p>
     * See {@link GetZoneInformationCommand}
     */
    GET_ZONE_INFORMATION_COMMAND(0x0501, 6, GetZoneInformationCommand.class, true),
    /**
     * GET_ZONE_INFORMATION_RESPONSE: Get Zone Information Response
     * <p>
     * See {@link GetZoneInformationResponse}
     */
    GET_ZONE_INFORMATION_RESPONSE(0x0501, 2, GetZoneInformationResponse.class, false),
    /**
     * IDENTIFY_COMMAND: Identify Command
     * <p>
     * See {@link IdentifyCommand}
     */
    IDENTIFY_COMMAND(0x0003, 0, IdentifyCommand.class, true),
    /**
     * IDENTIFY_QUERY_COMMAND: Identify Query Command
     * <p>
     * See {@link IdentifyQueryCommand}
     */
    IDENTIFY_QUERY_COMMAND(0x0003, 1, IdentifyQueryCommand.class, true),
    /**
     * IDENTIFY_QUERY_RESPONSE: Identify Query Response
     * <p>
     * See {@link IdentifyQueryResponse}
     */
    IDENTIFY_QUERY_RESPONSE(0x0003, 0, IdentifyQueryResponse.class, false),
    /**
     * LOCATION_DATA_NOTIFICATION_COMMAND: Location Data Notification Command
     * <p>
     * See {@link LocationDataNotificationCommand}
     */
    LOCATION_DATA_NOTIFICATION_COMMAND(0x000B, 2, LocationDataNotificationCommand.class, false),
    /**
     * LOCATION_DATA_RESPONSE: Location Data Response
     * <p>
     * See {@link LocationDataResponse}
     */
    LOCATION_DATA_RESPONSE(0x000B, 1, LocationDataResponse.class, false),
    /**
     * LOCK_DOOR_COMMAND: Lock Door Command
     * <p>
     * See {@link LockDoorCommand}
     */
    LOCK_DOOR_COMMAND(0x0101, 0, LockDoorCommand.class, true),
    /**
     * LOCK_DOOR_RESPONSE: Lock Door Response
     * <p>
     * See {@link LockDoorResponse}
     */
    LOCK_DOOR_RESPONSE(0x0101, 0, LockDoorResponse.class, false),
    /**
     * MOVE_COLOR_COMMAND: Move Color Command
     * <p>
     * See {@link MoveColorCommand}
     */
    MOVE_COLOR_COMMAND(0x0300, 8, MoveColorCommand.class, true),
    /**
     * MOVE_COMMAND: Move Command
     * <p>
     * See {@link MoveCommand}
     */
    MOVE_COMMAND(0x0008, 1, MoveCommand.class, true),
    /**
     * MOVE_HUE_COMMAND: Move Hue Command
     * <p>
     * See {@link MoveHueCommand}
     */
    MOVE_HUE_COMMAND(0x0300, 1, MoveHueCommand.class, true),
    /**
     * MOVE_SATURATION_COMMAND: Move Saturation Command
     * <p>
     * See {@link MoveSaturationCommand}
     */
    MOVE_SATURATION_COMMAND(0x0300, 4, MoveSaturationCommand.class, true),
    /**
     * MOVE_TO_COLOR_COMMAND: Move to Color Command
     * <p>
     * See {@link MoveToColorCommand}
     */
    MOVE_TO_COLOR_COMMAND(0x0300, 7, MoveToColorCommand.class, true),
    /**
     * MOVE_TO_COLOR_TEMPERATURE_COMMAND: Move to Color Temperature Command
     * <p>
     * See {@link MoveToColorTemperatureCommand}
     */
    MOVE_TO_COLOR_TEMPERATURE_COMMAND(0x0300, 10, MoveToColorTemperatureCommand.class, true),
    /**
     * MOVE_TO_HUE_AND_SATURATION_COMMAND: Move to Hue and Saturation Command
     * <p>
     * See {@link MoveToHueAndSaturationCommand}
     */
    MOVE_TO_HUE_AND_SATURATION_COMMAND(0x0300, 6, MoveToHueAndSaturationCommand.class, true),
    /**
     * MOVE_TO_HUE_COMMAND: Move to Hue Command
     * <p>
     * See {@link MoveToHueCommand}
     */
    MOVE_TO_HUE_COMMAND(0x0300, 0, MoveToHueCommand.class, true),
    /**
     * MOVE_TO_LEVEL_COMMAND: Move to Level Command
     * <p>
     * See {@link MoveToLevelCommand}
     */
    MOVE_TO_LEVEL_COMMAND(0x0008, 0, MoveToLevelCommand.class, true),
    /**
     * MOVE_TO_LEVEL__WITH_ON_OFF__COMMAND: Move to Level (with On/Off) Command
     * <p>
     * See {@link MoveToLevelWithOnOffCommand}
     */
    MOVE_TO_LEVEL__WITH_ON_OFF__COMMAND(0x0008, 4, MoveToLevelWithOnOffCommand.class, true),
    /**
     * MOVE_TO_SATURATION_COMMAND: Move to Saturation Command
     * <p>
     * See {@link MoveToSaturationCommand}
     */
    MOVE_TO_SATURATION_COMMAND(0x0300, 3, MoveToSaturationCommand.class, true),
    /**
     * MOVE__WITH_ON_OFF__COMMAND: Move (with On/Off) Command
     * <p>
     * See {@link MoveWithOnOffCommand}
     */
    MOVE__WITH_ON_OFF__COMMAND(0x0008, 5, MoveWithOnOffCommand.class, true),
    /**
     * OFF_COMMAND: Off Command
     * <p>
     * See {@link OffCommand}
     */
    OFF_COMMAND(0x0006, 0, OffCommand.class, true),
    /**
     * ON_COMMAND: On Command
     * <p>
     * See {@link OnCommand}
     */
    ON_COMMAND(0x0006, 1, OnCommand.class, true),
    /**
     * PANIC_COMMAND: Panic Command
     * <p>
     * See {@link PanicCommand}
     */
    PANIC_COMMAND(0x0501, 4, PanicCommand.class, true),
    /**
     * READ_ATTRIBUTES_COMMAND: Read Attributes Command
     * <p>
     * See {@link ReadAttributesCommand}
     */
    READ_ATTRIBUTES_COMMAND(0xFFFF, 0, ReadAttributesCommand.class, true),
    /**
     * READ_ATTRIBUTES_RESPONSE: Read Attributes Response
     * <p>
     * See {@link ReadAttributesResponse}
     */
    READ_ATTRIBUTES_RESPONSE(0xFFFF, 1, ReadAttributesResponse.class, true),
    /**
     * READ_ATTRIBUTES_STRUCTURED_COMMAND: Read Attributes Structured Command
     * <p>
     * See {@link ReadAttributesStructuredCommand}
     */
    READ_ATTRIBUTES_STRUCTURED_COMMAND(0xFFFF, 14, ReadAttributesStructuredCommand.class, true),
    /**
     * READ_REPORTING_CONFIGURATION_COMMAND: Read Reporting Configuration Command
     * <p>
     * See {@link ReadReportingConfigurationCommand}
     */
    READ_REPORTING_CONFIGURATION_COMMAND(0xFFFF, 8, ReadReportingConfigurationCommand.class, true),
    /**
     * READ_REPORTING_CONFIGURATION_RESPONSE: Read Reporting Configuration Response
     * <p>
     * See {@link ReadReportingConfigurationResponse}
     */
    READ_REPORTING_CONFIGURATION_RESPONSE(0xFFFF, 9, ReadReportingConfigurationResponse.class, true),
    /**
     * RECALL_SCENE_COMMAND: Recall Scene Command
     * <p>
     * See {@link RecallSceneCommand}
     */
    RECALL_SCENE_COMMAND(0x0005, 5, RecallSceneCommand.class, true),
    /**
     * REMOVE_ALL_GROUPS_COMMAND: Remove All Groups Command
     * <p>
     * See {@link RemoveAllGroupsCommand}
     */
    REMOVE_ALL_GROUPS_COMMAND(0x0004, 4, RemoveAllGroupsCommand.class, true),
    /**
     * REMOVE_ALL_SCENES_COMMAND: Remove All Scenes Command
     * <p>
     * See {@link RemoveAllScenesCommand}
     */
    REMOVE_ALL_SCENES_COMMAND(0x0005, 3, RemoveAllScenesCommand.class, true),
    /**
     * REMOVE_ALL_SCENES_RESPONSE: Remove All Scenes Response
     * <p>
     * See {@link RemoveAllScenesResponse}
     */
    REMOVE_ALL_SCENES_RESPONSE(0x0005, 3, RemoveAllScenesResponse.class, false),
    /**
     * REMOVE_GROUP_COMMAND: Remove Group Command
     * <p>
     * See {@link RemoveGroupCommand}
     */
    REMOVE_GROUP_COMMAND(0x0004, 3, RemoveGroupCommand.class, true),
    /**
     * REMOVE_GROUP_RESPONSE: Remove Group Response
     * <p>
     * See {@link RemoveGroupResponse}
     */
    REMOVE_GROUP_RESPONSE(0x0004, 3, RemoveGroupResponse.class, false),
    /**
     * REMOVE_SCENE_COMMAND: Remove Scene Command
     * <p>
     * See {@link RemoveSceneCommand}
     */
    REMOVE_SCENE_COMMAND(0x0005, 2, RemoveSceneCommand.class, true),
    /**
     * REMOVE_SCENE_RESPONSE: Remove Scene Response
     * <p>
     * See {@link RemoveSceneResponse}
     */
    REMOVE_SCENE_RESPONSE(0x0005, 2, RemoveSceneResponse.class, false),
    /**
     * REPORT_ATTRIBUTES_COMMAND: Report Attributes Command
     * <p>
     * See {@link ReportAttributesCommand}
     */
    REPORT_ATTRIBUTES_COMMAND(0xFFFF, 10, ReportAttributesCommand.class, true),
    /**
     * REPORT_RSSI_MEASUREMENTS_COMMAND: Report RSSI Measurements Command
     * <p>
     * See {@link ReportRssiMeasurementsCommand}
     */
    REPORT_RSSI_MEASUREMENTS_COMMAND(0x000B, 6, ReportRssiMeasurementsCommand.class, false),
    /**
     * REQUEST_OWN_LOCATION_COMMAND: Request Own Location Command
     * <p>
     * See {@link RequestOwnLocationCommand}
     */
    REQUEST_OWN_LOCATION_COMMAND(0x000B, 7, RequestOwnLocationCommand.class, false),
    /**
     * RESET_ALARM_COMMAND: Reset Alarm Command
     * <p>
     * See {@link ResetAlarmCommand}
     */
    RESET_ALARM_COMMAND(0x0009, 0, ResetAlarmCommand.class, true),
    /**
     * RESET_ALARM_LOG_COMMAND: Reset Alarm Log Command
     * <p>
     * See {@link ResetAlarmLogCommand}
     */
    RESET_ALARM_LOG_COMMAND(0x0009, 3, ResetAlarmLogCommand.class, true),
    /**
     * RESET_ALL_ALARMS_COMMAND: Reset All Alarms Command
     * <p>
     * See {@link ResetAllAlarmsCommand}
     */
    RESET_ALL_ALARMS_COMMAND(0x0009, 1, ResetAllAlarmsCommand.class, true),
    /**
     * RESET_STARTUP_PARAMETERS_COMMAND: Reset Startup Parameters Command
     * <p>
     * See {@link ResetStartupParametersCommand}
     */
    RESET_STARTUP_PARAMETERS_COMMAND(0x0015, 3, ResetStartupParametersCommand.class, true),
    /**
     * RESET_STARTUP_PARAMETERS_RESPONSE: Reset Startup Parameters Response
     * <p>
     * See {@link ResetStartupParametersResponse}
     */
    RESET_STARTUP_PARAMETERS_RESPONSE(0x0015, 3, ResetStartupParametersResponse.class, false),
    /**
     * RESET_TO_FACTORY_DEFAULTS_COMMAND: Reset to Factory Defaults Command
     * <p>
     * See {@link ResetToFactoryDefaultsCommand}
     */
    RESET_TO_FACTORY_DEFAULTS_COMMAND(0x0000, 0, ResetToFactoryDefaultsCommand.class, true),
    /**
     * RESTART_DEVICE_COMMAND: Restart Device Command
     * <p>
     * See {@link RestartDeviceCommand}
     */
    RESTART_DEVICE_COMMAND(0x0015, 0, RestartDeviceCommand.class, true),
    /**
     * RESTART_DEVICE_RESPONSE_RESPONSE: Restart Device Response Response
     * <p>
     * See {@link RestartDeviceResponseResponse}
     */
    RESTART_DEVICE_RESPONSE_RESPONSE(0x0015, 0, RestartDeviceResponseResponse.class, false),
    /**
     * RESTORE_STARTUP_PARAMETERS_COMMAND: Restore Startup Parameters Command
     * <p>
     * See {@link RestoreStartupParametersCommand}
     */
    RESTORE_STARTUP_PARAMETERS_COMMAND(0x0015, 2, RestoreStartupParametersCommand.class, true),
    /**
     * RESTORE_STARTUP_PARAMETERS_RESPONSE: Restore Startup Parameters Response
     * <p>
     * See {@link RestoreStartupParametersResponse}
     */
    RESTORE_STARTUP_PARAMETERS_RESPONSE(0x0015, 2, RestoreStartupParametersResponse.class, false),
    /**
     * RSSI_PING_COMMAND: RSSI Ping Command
     * <p>
     * See {@link RssiPingCommand}
     */
    RSSI_PING_COMMAND(0x000B, 4, RssiPingCommand.class, false),
    /**
     * RSSI_REQUEST_COMMAND: RSSI Request Command
     * <p>
     * See {@link RssiRequestCommand}
     */
    RSSI_REQUEST_COMMAND(0x000B, 5, RssiRequestCommand.class, false),
    /**
     * RSSI_RESPONSE: RSSI Response
     * <p>
     * See {@link RssiResponse}
     */
    RSSI_RESPONSE(0x000B, 4, RssiResponse.class, true),
    /**
     * SAVE_STARTUP_PARAMETERS_COMMAND: Save Startup Parameters Command
     * <p>
     * See {@link SaveStartupParametersCommand}
     */
    SAVE_STARTUP_PARAMETERS_COMMAND(0x0015, 1, SaveStartupParametersCommand.class, true),
    /**
     * SAVE_STARTUP_PARAMETERS_RESPONSE: Save Startup Parameters Response
     * <p>
     * See {@link SaveStartupParametersResponse}
     */
    SAVE_STARTUP_PARAMETERS_RESPONSE(0x0015, 1, SaveStartupParametersResponse.class, false),
    /**
     * SEND_PINGS_COMMAND: Send Pings Command
     * <p>
     * See {@link SendPingsCommand}
     */
    SEND_PINGS_COMMAND(0x000B, 5, SendPingsCommand.class, true),
    /**
     * SETPOINT_RAISE_LOWER_COMMAND: Setpoint Raise/Lower Command
     * <p>
     * See {@link SetpointRaiseLowerCommand}
     */
    SETPOINT_RAISE_LOWER_COMMAND(0x0201, 0, SetpointRaiseLowerCommand.class, true),
    /**
     * SET_ABSOLUTE_LOCATION_COMMAND: Set Absolute Location Command
     * <p>
     * See {@link SetAbsoluteLocationCommand}
     */
    SET_ABSOLUTE_LOCATION_COMMAND(0x000B, 0, SetAbsoluteLocationCommand.class, true),
    /**
     * SET_DEVICE_CONFIGURATION_COMMAND: Set Device Configuration Command
     * <p>
     * See {@link SetDeviceConfigurationCommand}
     */
    SET_DEVICE_CONFIGURATION_COMMAND(0x000B, 1, SetDeviceConfigurationCommand.class, true),
    /**
     * SQUAWK_COMMAND: Squawk Command
     * <p>
     * See {@link SquawkCommand}
     */
    SQUAWK_COMMAND(0x0502, 2, SquawkCommand.class, true),
    /**
     * START_WARNING_COMMAND: Start Warning Command
     * <p>
     * See {@link StartWarningCommand}
     */
    START_WARNING_COMMAND(0x0502, 0, StartWarningCommand.class, true),
    /**
     * STEP_COLOR_COMMAND: Step Color Command
     * <p>
     * See {@link StepColorCommand}
     */
    STEP_COLOR_COMMAND(0x0300, 9, StepColorCommand.class, true),
    /**
     * STEP_COMMAND: Step Command
     * <p>
     * See {@link StepCommand}
     */
    STEP_COMMAND(0x0008, 2, StepCommand.class, true),
    /**
     * STEP_HUE_COMMAND: Step Hue Command
     * <p>
     * See {@link StepHueCommand}
     */
    STEP_HUE_COMMAND(0x0300, 2, StepHueCommand.class, true),
    /**
     * STEP_SATURATION_COMMAND: Step Saturation Command
     * <p>
     * See {@link StepSaturationCommand}
     */
    STEP_SATURATION_COMMAND(0x0300, 5, StepSaturationCommand.class, true),
    /**
     * STEP__WITH_ON_OFF__COMMAND: Step (with On/Off) Command
     * <p>
     * See {@link StepWithOnOffCommand}
     */
    STEP__WITH_ON_OFF__COMMAND(0x0008, 6, StepWithOnOffCommand.class, true),
    /**
     * STOP_2_COMMAND: Stop 2 Command
     * <p>
     * See {@link Stop2Command}
     */
    STOP_2_COMMAND(0x0008, 7, Stop2Command.class, true),
    /**
     * STOP_COMMAND: Stop Command
     * <p>
     * See {@link StopCommand}
     */
    STOP_COMMAND(0x0008, 3, StopCommand.class, true),
    /**
     * STORE_SCENE_COMMAND: Store Scene Command
     * <p>
     * See {@link StoreSceneCommand}
     */
    STORE_SCENE_COMMAND(0x0005, 4, StoreSceneCommand.class, true),
    /**
     * STORE_SCENE_RESPONSE: Store Scene Response
     * <p>
     * See {@link StoreSceneResponse}
     */
    STORE_SCENE_RESPONSE(0x0005, 4, StoreSceneResponse.class, false),
    /**
     * TOGGLE_COMMAND: Toggle Command
     * <p>
     * See {@link ToggleCommand}
     */
    TOGGLE_COMMAND(0x0006, 2, ToggleCommand.class, true),
    /**
     * UNLOCK_DOOR_COMMAND: Unlock Door Command
     * <p>
     * See {@link UnlockDoorCommand}
     */
    UNLOCK_DOOR_COMMAND(0x0101, 1, UnlockDoorCommand.class, true),
    /**
     * UNLOCK_DOOR_RESPONSE: Unlock Door Response
     * <p>
     * See {@link UnlockDoorResponse}
     */
    UNLOCK_DOOR_RESPONSE(0x0101, 1, UnlockDoorResponse.class, false),
    /**
     * VIEW_GROUP_COMMAND: View Group Command
     * <p>
     * See {@link ViewGroupCommand}
     */
    VIEW_GROUP_COMMAND(0x0004, 1, ViewGroupCommand.class, true),
    /**
     * VIEW_GROUP_RESPONSE: View Group Response
     * <p>
     * See {@link ViewGroupResponse}
     */
    VIEW_GROUP_RESPONSE(0x0004, 1, ViewGroupResponse.class, false),
    /**
     * VIEW_SCENE_COMMAND: View Scene Command
     * <p>
     * See {@link ViewSceneCommand}
     */
    VIEW_SCENE_COMMAND(0x0005, 1, ViewSceneCommand.class, true),
    /**
     * VIEW_SCENE_RESPONSE: View Scene Response
     * <p>
     * See {@link ViewSceneResponse}
     */
    VIEW_SCENE_RESPONSE(0x0005, 1, ViewSceneResponse.class, false),
    /**
     * WRITE_ATTRIBUTES_COMMAND: Write Attributes Command
     * <p>
     * See {@link WriteAttributesCommand}
     */
    WRITE_ATTRIBUTES_COMMAND(0xFFFF, 2, WriteAttributesCommand.class, true),
    /**
     * WRITE_ATTRIBUTES_NO_RESPONSE: Write Attributes No Response
     * <p>
     * See {@link WriteAttributesNoResponse}
     */
    WRITE_ATTRIBUTES_NO_RESPONSE(0xFFFF, 5, WriteAttributesNoResponse.class, true),
    /**
     * WRITE_ATTRIBUTES_RESPONSE: Write Attributes Response
     * <p>
     * See {@link WriteAttributesResponse}
     */
    WRITE_ATTRIBUTES_RESPONSE(0xFFFF, 4, WriteAttributesResponse.class, true),
    /**
     * WRITE_ATTRIBUTES_STRUCTURED_COMMAND: Write Attributes Structured Command
     * <p>
     * See {@link WriteAttributesStructuredCommand}
     */
    WRITE_ATTRIBUTES_STRUCTURED_COMMAND(0xFFFF, 15, WriteAttributesStructuredCommand.class, true),
    /**
     * WRITE_ATTRIBUTES_STRUCTURED_RESPONSE: Write Attributes Structured Response
     * <p>
     * See {@link WriteAttributesStructuredResponse}
     */
    WRITE_ATTRIBUTES_STRUCTURED_RESPONSE(0xFFFF, 16, WriteAttributesStructuredResponse.class, true),
    /**
     * WRITE_ATTRIBUTES_UNDIVIDED_COMMAND: Write Attributes Undivided Command
     * <p>
     * See {@link WriteAttributesUndividedCommand}
     */
    WRITE_ATTRIBUTES_UNDIVIDED_COMMAND(0xFFFF, 3, WriteAttributesUndividedCommand.class, true),
    /**
     * ZONE_ENROLL_REQUEST_COMMAND: Zone Enroll Request Command
     * <p>
     * See {@link ZoneEnrollRequestCommand}
     */
    ZONE_ENROLL_REQUEST_COMMAND(0x0500, 1, ZoneEnrollRequestCommand.class, false),
    /**
     * ZONE_ENROLL_RESPONSE: Zone Enroll Response
     * <p>
     * See {@link ZoneEnrollResponse}
     */
    ZONE_ENROLL_RESPONSE(0x0500, 0, ZoneEnrollResponse.class, true),
    /**
     * ZONE_STATUS_CHANGE_NOTIFICATION_COMMAND: Zone Status Change Notification Command
     * <p>
     * See {@link ZoneStatusChangeNotificationCommand}
     */
    ZONE_STATUS_CHANGE_NOTIFICATION_COMMAND(0x0500, 0, ZoneStatusChangeNotificationCommand.class, false);

    private final int commandId;
    private final int clusterType;
    private final Class<? extends ZclCommand> commandClass;
    private final boolean received;

    ZclCommandType(final int clusterType, final int commandId, final Class<? extends ZclCommand> commandClass, final boolean received) {
        this.clusterType = clusterType;
        this.commandId = commandId;
        this.commandClass = commandClass;
        this.received = received;
    }

    public int getClusterType() {
        return clusterType;
    }

    public int getId() {
        return commandId;
    }

    public boolean isGeneric() {
        return clusterType==0xFFFF;
    }

    public boolean isReceived() {
        return received;
    }

    public Class<? extends ZclCommand> getCommandClass() {
        return commandClass;
    }

    public static ZclCommandType getRequest(final int clusterType, final int commandId) {
        for (final ZclCommandType value : values()) {
            if (!value.received && value.clusterType == clusterType && value.commandId == commandId) {
                return value;
            }
        }
        return null;
    }

    public static ZclCommandType getResponse(final int clusterType, final int commandId) {
        for (final ZclCommandType value : values()) {
            if (value.received && value.clusterType == clusterType && value.commandId == commandId) {
                return value;
            }
        }
        return null;
    }

    public static ZclCommandType getGeneric(final int commandId) {
        for (final ZclCommandType value : values()) {
            if (value.clusterType == 0xFFFF && value.commandId == commandId) {
                return value;
            }
        }
        return null;
    }

    public ZclCommand instantiateCommand() {
        Constructor<? extends ZclCommand> cmdConstructor;
        try {
            cmdConstructor = commandClass.getConstructor();
            return cmdConstructor.newInstance();
        } catch (Exception e) {
            // logger.debug("Error instantiating cluster command {}", this);
        }
        return null;
    }
}
