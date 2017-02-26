package com.zsmartsystems.zigbee.zcl.protocol;

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


/**
 * Code is auto-generated. Modifications may be overwritten!
 */
public enum ZclCommandType {
    /**
     * Add Group Command
     * <p>
     * See {@link AddGroupCommand}
     */
    ADD_GROUP_COMMAND(ZclClusterType.GROUPS, 0, AddGroupCommand.class, true),
    /**
     * Add Group If Identifying Command
     * <p>
     * See {@link AddGroupIfIdentifyingCommand}
     */
    ADD_GROUP_IF_IDENTIFYING_COMMAND(ZclClusterType.GROUPS, 5, AddGroupIfIdentifyingCommand.class, true),
    /**
     * Add Group Response
     * <p>
     * See {@link AddGroupResponse}
     */
    ADD_GROUP_RESPONSE(ZclClusterType.GROUPS, 0, AddGroupResponse.class, false),
    /**
     * Add Scene Command
     * <p>
     * See {@link AddSceneCommand}
     */
    ADD_SCENE_COMMAND(ZclClusterType.SCENES, 0, AddSceneCommand.class, true),
    /**
     * Add Scene Response
     * <p>
     * See {@link AddSceneResponse}
     */
    ADD_SCENE_RESPONSE(ZclClusterType.SCENES, 0, AddSceneResponse.class, false),
    /**
     * Alarm Command
     * <p>
     * See {@link AlarmCommand}
     */
    ALARM_COMMAND(ZclClusterType.ALARMS, 0, AlarmCommand.class, false),
    /**
     * Anchor Node Announce Command
     * <p>
     * See {@link AnchorNodeAnnounceCommand}
     */
    ANCHOR_NODE_ANNOUNCE_COMMAND(ZclClusterType.RSSI_LOCATION, 6, AnchorNodeAnnounceCommand.class, true),
    /**
     * Arm Command
     * <p>
     * See {@link ArmCommand}
     */
    ARM_COMMAND(ZclClusterType.IAS_ACE, 0, ArmCommand.class, true),
    /**
     * Arm Response
     * <p>
     * See {@link ArmResponse}
     */
    ARM_RESPONSE(ZclClusterType.IAS_ACE, 0, ArmResponse.class, false),
    /**
     * Bypass Command
     * <p>
     * See {@link BypassCommand}
     */
    BYPASS_COMMAND(ZclClusterType.IAS_ACE, 1, BypassCommand.class, true),
    /**
     * Compact Location Data Notification Command
     * <p>
     * See {@link CompactLocationDataNotificationCommand}
     */
    COMPACT_LOCATION_DATA_NOTIFICATION_COMMAND(ZclClusterType.RSSI_LOCATION, 3, CompactLocationDataNotificationCommand.class, false),
    /**
     * Configure Reporting Command
     * <p>
     * See {@link ConfigureReportingCommand}
     */
    CONFIGURE_REPORTING_COMMAND(ZclClusterType.GENERAL, 6, ConfigureReportingCommand.class, true),
    /**
     * Configure Reporting Response
     * <p>
     * See {@link ConfigureReportingResponse}
     */
    CONFIGURE_REPORTING_RESPONSE(ZclClusterType.GENERAL, 7, ConfigureReportingResponse.class, true),
    /**
     * Default Response
     * <p>
     * See {@link DefaultResponse}
     */
    DEFAULT_RESPONSE(ZclClusterType.GENERAL, 11, DefaultResponse.class, true),
    /**
     * Device Configuration Response
     * <p>
     * See {@link DeviceConfigurationResponse}
     */
    DEVICE_CONFIGURATION_RESPONSE(ZclClusterType.RSSI_LOCATION, 0, DeviceConfigurationResponse.class, false),
    /**
     * Discover Attributes Command
     * <p>
     * See {@link DiscoverAttributesCommand}
     */
    DISCOVER_ATTRIBUTES_COMMAND(ZclClusterType.GENERAL, 12, DiscoverAttributesCommand.class, true),
    /**
     * Discover Attributes Response
     * <p>
     * See {@link DiscoverAttributesResponse}
     */
    DISCOVER_ATTRIBUTES_RESPONSE(ZclClusterType.GENERAL, 13, DiscoverAttributesResponse.class, true),
    /**
     * Emergency Command
     * <p>
     * See {@link EmergencyCommand}
     */
    EMERGENCY_COMMAND(ZclClusterType.IAS_ACE, 2, EmergencyCommand.class, true),
    /**
     * Fire Command
     * <p>
     * See {@link FireCommand}
     */
    FIRE_COMMAND(ZclClusterType.IAS_ACE, 3, FireCommand.class, true),
    /**
     * Get Alarm Command
     * <p>
     * See {@link GetAlarmCommand}
     */
    GET_ALARM_COMMAND(ZclClusterType.ALARMS, 2, GetAlarmCommand.class, true),
    /**
     * Get Alarm Response
     * <p>
     * See {@link GetAlarmResponse}
     */
    GET_ALARM_RESPONSE(ZclClusterType.ALARMS, 1, GetAlarmResponse.class, false),
    /**
     * Get Device Configuration Command
     * <p>
     * See {@link GetDeviceConfigurationCommand}
     */
    GET_DEVICE_CONFIGURATION_COMMAND(ZclClusterType.RSSI_LOCATION, 2, GetDeviceConfigurationCommand.class, true),
    /**
     * Get Group Membership Command
     * <p>
     * See {@link GetGroupMembershipCommand}
     */
    GET_GROUP_MEMBERSHIP_COMMAND(ZclClusterType.GROUPS, 2, GetGroupMembershipCommand.class, true),
    /**
     * Get Group Membership Response
     * <p>
     * See {@link GetGroupMembershipResponse}
     */
    GET_GROUP_MEMBERSHIP_RESPONSE(ZclClusterType.GROUPS, 2, GetGroupMembershipResponse.class, false),
    /**
     * Get Location Data Command
     * <p>
     * See {@link GetLocationDataCommand}
     */
    GET_LOCATION_DATA_COMMAND(ZclClusterType.RSSI_LOCATION, 3, GetLocationDataCommand.class, true),
    /**
     * Get Scene Membership Command
     * <p>
     * See {@link GetSceneMembershipCommand}
     */
    GET_SCENE_MEMBERSHIP_COMMAND(ZclClusterType.SCENES, 6, GetSceneMembershipCommand.class, true),
    /**
     * Get Scene Membership Response
     * <p>
     * See {@link GetSceneMembershipResponse}
     */
    GET_SCENE_MEMBERSHIP_RESPONSE(ZclClusterType.SCENES, 5, GetSceneMembershipResponse.class, false),
    /**
     * Get Zone ID Map Command
     * <p>
     * See {@link GetZoneIdMapCommand}
     */
    GET_ZONE_ID_MAP_COMMAND(ZclClusterType.IAS_ACE, 5, GetZoneIdMapCommand.class, true),
    /**
     * Get Zone ID Map Response
     * <p>
     * See {@link GetZoneIdMapResponse}
     */
    GET_ZONE_ID_MAP_RESPONSE(ZclClusterType.IAS_ACE, 1, GetZoneIdMapResponse.class, false),
    /**
     * Get Zone Information Command
     * <p>
     * See {@link GetZoneInformationCommand}
     */
    GET_ZONE_INFORMATION_COMMAND(ZclClusterType.IAS_ACE, 6, GetZoneInformationCommand.class, true),
    /**
     * Get Zone Information Response
     * <p>
     * See {@link GetZoneInformationResponse}
     */
    GET_ZONE_INFORMATION_RESPONSE(ZclClusterType.IAS_ACE, 2, GetZoneInformationResponse.class, false),
    /**
     * Identify Command
     * <p>
     * See {@link IdentifyCommand}
     */
    IDENTIFY_COMMAND(ZclClusterType.IDENTIFY, 0, IdentifyCommand.class, true),
    /**
     * Identify Query Command
     * <p>
     * See {@link IdentifyQueryCommand}
     */
    IDENTIFY_QUERY_COMMAND(ZclClusterType.IDENTIFY, 1, IdentifyQueryCommand.class, true),
    /**
     * Identify Query Response
     * <p>
     * See {@link IdentifyQueryResponse}
     */
    IDENTIFY_QUERY_RESPONSE(ZclClusterType.IDENTIFY, 0, IdentifyQueryResponse.class, false),
    /**
     * Location Data Notification Command
     * <p>
     * See {@link LocationDataNotificationCommand}
     */
    LOCATION_DATA_NOTIFICATION_COMMAND(ZclClusterType.RSSI_LOCATION, 2, LocationDataNotificationCommand.class, false),
    /**
     * Location Data Response
     * <p>
     * See {@link LocationDataResponse}
     */
    LOCATION_DATA_RESPONSE(ZclClusterType.RSSI_LOCATION, 1, LocationDataResponse.class, false),
    /**
     * Lock Door Command
     * <p>
     * See {@link LockDoorCommand}
     */
    LOCK_DOOR_COMMAND(ZclClusterType.DOOR_LOCK, 0, LockDoorCommand.class, true),
    /**
     * Lock Door Response
     * <p>
     * See {@link LockDoorResponse}
     */
    LOCK_DOOR_RESPONSE(ZclClusterType.DOOR_LOCK, 0, LockDoorResponse.class, false),
    /**
     * Move Color Command
     * <p>
     * See {@link MoveColorCommand}
     */
    MOVE_COLOR_COMMAND(ZclClusterType.COLOR_CONTROL, 8, MoveColorCommand.class, true),
    /**
     * Move Command
     * <p>
     * See {@link MoveCommand}
     */
    MOVE_COMMAND(ZclClusterType.LEVEL_CONTROL, 1, MoveCommand.class, true),
    /**
     * Move Hue Command
     * <p>
     * See {@link MoveHueCommand}
     */
    MOVE_HUE_COMMAND(ZclClusterType.COLOR_CONTROL, 1, MoveHueCommand.class, true),
    /**
     * Move Saturation Command
     * <p>
     * See {@link MoveSaturationCommand}
     */
    MOVE_SATURATION_COMMAND(ZclClusterType.COLOR_CONTROL, 4, MoveSaturationCommand.class, true),
    /**
     * Move to Color Command
     * <p>
     * See {@link MoveToColorCommand}
     */
    MOVE_TO_COLOR_COMMAND(ZclClusterType.COLOR_CONTROL, 7, MoveToColorCommand.class, true),
    /**
     * Move to Color Temperature Command
     * <p>
     * See {@link MoveToColorTemperatureCommand}
     */
    MOVE_TO_COLOR_TEMPERATURE_COMMAND(ZclClusterType.COLOR_CONTROL, 10, MoveToColorTemperatureCommand.class, true),
    /**
     * Move to Hue and Saturation Command
     * <p>
     * See {@link MoveToHueAndSaturationCommand}
     */
    MOVE_TO_HUE_AND_SATURATION_COMMAND(ZclClusterType.COLOR_CONTROL, 6, MoveToHueAndSaturationCommand.class, true),
    /**
     * Move to Hue Command
     * <p>
     * See {@link MoveToHueCommand}
     */
    MOVE_TO_HUE_COMMAND(ZclClusterType.COLOR_CONTROL, 0, MoveToHueCommand.class, true),
    /**
     * Move to Level Command
     * <p>
     * See {@link MoveToLevelCommand}
     */
    MOVE_TO_LEVEL_COMMAND(ZclClusterType.LEVEL_CONTROL, 0, MoveToLevelCommand.class, true),
    /**
     * Move to Level (with On/Off) Command
     * <p>
     * See {@link MoveToLevelWithOnOffCommand}
     */
    MOVE_TO_LEVEL__WITH_ON_OFF__COMMAND(ZclClusterType.LEVEL_CONTROL, 4, MoveToLevelWithOnOffCommand.class, true),
    /**
     * Move to Saturation Command
     * <p>
     * See {@link MoveToSaturationCommand}
     */
    MOVE_TO_SATURATION_COMMAND(ZclClusterType.COLOR_CONTROL, 3, MoveToSaturationCommand.class, true),
    /**
     * Move (with On/Off) Command
     * <p>
     * See {@link MoveWithOnOffCommand}
     */
    MOVE__WITH_ON_OFF__COMMAND(ZclClusterType.LEVEL_CONTROL, 5, MoveWithOnOffCommand.class, true),
    /**
     * Off Command
     * <p>
     * See {@link OffCommand}
     */
    OFF_COMMAND(ZclClusterType.ON_OFF, 0, OffCommand.class, true),
    /**
     * On Command
     * <p>
     * See {@link OnCommand}
     */
    ON_COMMAND(ZclClusterType.ON_OFF, 1, OnCommand.class, true),
    /**
     * Panic Command
     * <p>
     * See {@link PanicCommand}
     */
    PANIC_COMMAND(ZclClusterType.IAS_ACE, 4, PanicCommand.class, true),
    /**
     * Read Attributes Command
     * <p>
     * See {@link ReadAttributesCommand}
     */
    READ_ATTRIBUTES_COMMAND(ZclClusterType.GENERAL, 0, ReadAttributesCommand.class, true),
    /**
     * Read Attributes Response
     * <p>
     * See {@link ReadAttributesResponse}
     */
    READ_ATTRIBUTES_RESPONSE(ZclClusterType.GENERAL, 1, ReadAttributesResponse.class, true),
    /**
     * Read Attributes Structured Command
     * <p>
     * See {@link ReadAttributesStructuredCommand}
     */
    READ_ATTRIBUTES_STRUCTURED_COMMAND(ZclClusterType.GENERAL, 14, ReadAttributesStructuredCommand.class, true),
    /**
     * Read Reporting Configuration Command
     * <p>
     * See {@link ReadReportingConfigurationCommand}
     */
    READ_REPORTING_CONFIGURATION_COMMAND(ZclClusterType.GENERAL, 8, ReadReportingConfigurationCommand.class, true),
    /**
     * Read Reporting Configuration Response
     * <p>
     * See {@link ReadReportingConfigurationResponse}
     */
    READ_REPORTING_CONFIGURATION_RESPONSE(ZclClusterType.GENERAL, 9, ReadReportingConfigurationResponse.class, true),
    /**
     * Recall Scene Command
     * <p>
     * See {@link RecallSceneCommand}
     */
    RECALL_SCENE_COMMAND(ZclClusterType.SCENES, 5, RecallSceneCommand.class, true),
    /**
     * Remove All Groups Command
     * <p>
     * See {@link RemoveAllGroupsCommand}
     */
    REMOVE_ALL_GROUPS_COMMAND(ZclClusterType.GROUPS, 4, RemoveAllGroupsCommand.class, true),
    /**
     * Remove All Scenes Command
     * <p>
     * See {@link RemoveAllScenesCommand}
     */
    REMOVE_ALL_SCENES_COMMAND(ZclClusterType.SCENES, 3, RemoveAllScenesCommand.class, true),
    /**
     * Remove All Scenes Response
     * <p>
     * See {@link RemoveAllScenesResponse}
     */
    REMOVE_ALL_SCENES_RESPONSE(ZclClusterType.SCENES, 3, RemoveAllScenesResponse.class, false),
    /**
     * Remove Group Command
     * <p>
     * See {@link RemoveGroupCommand}
     */
    REMOVE_GROUP_COMMAND(ZclClusterType.GROUPS, 3, RemoveGroupCommand.class, true),
    /**
     * Remove Group Response
     * <p>
     * See {@link RemoveGroupResponse}
     */
    REMOVE_GROUP_RESPONSE(ZclClusterType.GROUPS, 3, RemoveGroupResponse.class, false),
    /**
     * Remove Scene Command
     * <p>
     * See {@link RemoveSceneCommand}
     */
    REMOVE_SCENE_COMMAND(ZclClusterType.SCENES, 2, RemoveSceneCommand.class, true),
    /**
     * Remove Scene Response
     * <p>
     * See {@link RemoveSceneResponse}
     */
    REMOVE_SCENE_RESPONSE(ZclClusterType.SCENES, 2, RemoveSceneResponse.class, false),
    /**
     * Report Attributes Command
     * <p>
     * See {@link ReportAttributesCommand}
     */
    REPORT_ATTRIBUTES_COMMAND(ZclClusterType.GENERAL, 10, ReportAttributesCommand.class, true),
    /**
     * Report RSSI Measurements Command
     * <p>
     * See {@link ReportRssiMeasurementsCommand}
     */
    REPORT_RSSI_MEASUREMENTS_COMMAND(ZclClusterType.RSSI_LOCATION, 6, ReportRssiMeasurementsCommand.class, false),
    /**
     * Request Own Location Command
     * <p>
     * See {@link RequestOwnLocationCommand}
     */
    REQUEST_OWN_LOCATION_COMMAND(ZclClusterType.RSSI_LOCATION, 7, RequestOwnLocationCommand.class, false),
    /**
     * Reset Alarm Command
     * <p>
     * See {@link ResetAlarmCommand}
     */
    RESET_ALARM_COMMAND(ZclClusterType.ALARMS, 0, ResetAlarmCommand.class, true),
    /**
     * Reset Alarm Log Command
     * <p>
     * See {@link ResetAlarmLogCommand}
     */
    RESET_ALARM_LOG_COMMAND(ZclClusterType.ALARMS, 3, ResetAlarmLogCommand.class, true),
    /**
     * Reset All Alarms Command
     * <p>
     * See {@link ResetAllAlarmsCommand}
     */
    RESET_ALL_ALARMS_COMMAND(ZclClusterType.ALARMS, 1, ResetAllAlarmsCommand.class, true),
    /**
     * Reset Startup Parameters Command
     * <p>
     * See {@link ResetStartupParametersCommand}
     */
    RESET_STARTUP_PARAMETERS_COMMAND(ZclClusterType.COMMISSIONING, 3, ResetStartupParametersCommand.class, true),
    /**
     * Reset Startup Parameters Response
     * <p>
     * See {@link ResetStartupParametersResponse}
     */
    RESET_STARTUP_PARAMETERS_RESPONSE(ZclClusterType.COMMISSIONING, 3, ResetStartupParametersResponse.class, false),
    /**
     * Reset to Factory Defaults Command
     * <p>
     * See {@link ResetToFactoryDefaultsCommand}
     */
    RESET_TO_FACTORY_DEFAULTS_COMMAND(ZclClusterType.BASIC, 0, ResetToFactoryDefaultsCommand.class, true),
    /**
     * Restart Device Command
     * <p>
     * See {@link RestartDeviceCommand}
     */
    RESTART_DEVICE_COMMAND(ZclClusterType.COMMISSIONING, 0, RestartDeviceCommand.class, true),
    /**
     * Restart Device Response Response
     * <p>
     * See {@link RestartDeviceResponseResponse}
     */
    RESTART_DEVICE_RESPONSE_RESPONSE(ZclClusterType.COMMISSIONING, 0, RestartDeviceResponseResponse.class, false),
    /**
     * Restore Startup Parameters Command
     * <p>
     * See {@link RestoreStartupParametersCommand}
     */
    RESTORE_STARTUP_PARAMETERS_COMMAND(ZclClusterType.COMMISSIONING, 2, RestoreStartupParametersCommand.class, true),
    /**
     * Restore Startup Parameters Response
     * <p>
     * See {@link RestoreStartupParametersResponse}
     */
    RESTORE_STARTUP_PARAMETERS_RESPONSE(ZclClusterType.COMMISSIONING, 2, RestoreStartupParametersResponse.class, false),
    /**
     * RSSI Ping Command
     * <p>
     * See {@link RssiPingCommand}
     */
    RSSI_PING_COMMAND(ZclClusterType.RSSI_LOCATION, 4, RssiPingCommand.class, false),
    /**
     * RSSI Request Command
     * <p>
     * See {@link RssiRequestCommand}
     */
    RSSI_REQUEST_COMMAND(ZclClusterType.RSSI_LOCATION, 5, RssiRequestCommand.class, false),
    /**
     * RSSI Response
     * <p>
     * See {@link RssiResponse}
     */
    RSSI_RESPONSE(ZclClusterType.RSSI_LOCATION, 4, RssiResponse.class, true),
    /**
     * Save Startup Parameters Command
     * <p>
     * See {@link SaveStartupParametersCommand}
     */
    SAVE_STARTUP_PARAMETERS_COMMAND(ZclClusterType.COMMISSIONING, 1, SaveStartupParametersCommand.class, true),
    /**
     * Save Startup Parameters Response
     * <p>
     * See {@link SaveStartupParametersResponse}
     */
    SAVE_STARTUP_PARAMETERS_RESPONSE(ZclClusterType.COMMISSIONING, 1, SaveStartupParametersResponse.class, false),
    /**
     * Send Pings Command
     * <p>
     * See {@link SendPingsCommand}
     */
    SEND_PINGS_COMMAND(ZclClusterType.RSSI_LOCATION, 5, SendPingsCommand.class, true),
    /**
     * Setpoint Raise/Lower Command
     * <p>
     * See {@link SetpointRaiseLowerCommand}
     */
    SETPOINT_RAISE_LOWER_COMMAND(ZclClusterType.THERMOSTAT, 0, SetpointRaiseLowerCommand.class, true),
    /**
     * Set Absolute Location Command
     * <p>
     * See {@link SetAbsoluteLocationCommand}
     */
    SET_ABSOLUTE_LOCATION_COMMAND(ZclClusterType.RSSI_LOCATION, 0, SetAbsoluteLocationCommand.class, true),
    /**
     * Set Device Configuration Command
     * <p>
     * See {@link SetDeviceConfigurationCommand}
     */
    SET_DEVICE_CONFIGURATION_COMMAND(ZclClusterType.RSSI_LOCATION, 1, SetDeviceConfigurationCommand.class, true),
    /**
     * Squawk Command
     * <p>
     * See {@link SquawkCommand}
     */
    SQUAWK_COMMAND(ZclClusterType.IAS_WD, 2, SquawkCommand.class, true),
    /**
     * Start Warning Command
     * <p>
     * See {@link StartWarningCommand}
     */
    START_WARNING_COMMAND(ZclClusterType.IAS_WD, 0, StartWarningCommand.class, true),
    /**
     * Step Color Command
     * <p>
     * See {@link StepColorCommand}
     */
    STEP_COLOR_COMMAND(ZclClusterType.COLOR_CONTROL, 9, StepColorCommand.class, true),
    /**
     * Step Command
     * <p>
     * See {@link StepCommand}
     */
    STEP_COMMAND(ZclClusterType.LEVEL_CONTROL, 2, StepCommand.class, true),
    /**
     * Step Hue Command
     * <p>
     * See {@link StepHueCommand}
     */
    STEP_HUE_COMMAND(ZclClusterType.COLOR_CONTROL, 2, StepHueCommand.class, true),
    /**
     * Step Saturation Command
     * <p>
     * See {@link StepSaturationCommand}
     */
    STEP_SATURATION_COMMAND(ZclClusterType.COLOR_CONTROL, 5, StepSaturationCommand.class, true),
    /**
     * Step (with On/Off) Command
     * <p>
     * See {@link StepWithOnOffCommand}
     */
    STEP__WITH_ON_OFF__COMMAND(ZclClusterType.LEVEL_CONTROL, 6, StepWithOnOffCommand.class, true),
    /**
     * Stop 2 Command
     * <p>
     * See {@link Stop2Command}
     */
    STOP_2_COMMAND(ZclClusterType.LEVEL_CONTROL, 7, Stop2Command.class, true),
    /**
     * Stop Command
     * <p>
     * See {@link StopCommand}
     */
    STOP_COMMAND(ZclClusterType.LEVEL_CONTROL, 3, StopCommand.class, true),
    /**
     * Store Scene Command
     * <p>
     * See {@link StoreSceneCommand}
     */
    STORE_SCENE_COMMAND(ZclClusterType.SCENES, 4, StoreSceneCommand.class, true),
    /**
     * Store Scene Response
     * <p>
     * See {@link StoreSceneResponse}
     */
    STORE_SCENE_RESPONSE(ZclClusterType.SCENES, 4, StoreSceneResponse.class, false),
    /**
     * Toggle Command
     * <p>
     * See {@link ToggleCommand}
     */
    TOGGLE_COMMAND(ZclClusterType.ON_OFF, 2, ToggleCommand.class, true),
    /**
     * Unlock Door Command
     * <p>
     * See {@link UnlockDoorCommand}
     */
    UNLOCK_DOOR_COMMAND(ZclClusterType.DOOR_LOCK, 1, UnlockDoorCommand.class, true),
    /**
     * Unlock Door Response
     * <p>
     * See {@link UnlockDoorResponse}
     */
    UNLOCK_DOOR_RESPONSE(ZclClusterType.DOOR_LOCK, 1, UnlockDoorResponse.class, false),
    /**
     * View Group Command
     * <p>
     * See {@link ViewGroupCommand}
     */
    VIEW_GROUP_COMMAND(ZclClusterType.GROUPS, 1, ViewGroupCommand.class, true),
    /**
     * View Group Response
     * <p>
     * See {@link ViewGroupResponse}
     */
    VIEW_GROUP_RESPONSE(ZclClusterType.GROUPS, 1, ViewGroupResponse.class, false),
    /**
     * View Scene Command
     * <p>
     * See {@link ViewSceneCommand}
     */
    VIEW_SCENE_COMMAND(ZclClusterType.SCENES, 1, ViewSceneCommand.class, true),
    /**
     * View Scene Response
     * <p>
     * See {@link ViewSceneResponse}
     */
    VIEW_SCENE_RESPONSE(ZclClusterType.SCENES, 1, ViewSceneResponse.class, false),
    /**
     * Write Attributes Command
     * <p>
     * See {@link WriteAttributesCommand}
     */
    WRITE_ATTRIBUTES_COMMAND(ZclClusterType.GENERAL, 2, WriteAttributesCommand.class, true),
    /**
     * Write Attributes No Response
     * <p>
     * See {@link WriteAttributesNoResponse}
     */
    WRITE_ATTRIBUTES_NO_RESPONSE(ZclClusterType.GENERAL, 5, WriteAttributesNoResponse.class, true),
    /**
     * Write Attributes Response
     * <p>
     * See {@link WriteAttributesResponse}
     */
    WRITE_ATTRIBUTES_RESPONSE(ZclClusterType.GENERAL, 4, WriteAttributesResponse.class, true),
    /**
     * Write Attributes Structured Command
     * <p>
     * See {@link WriteAttributesStructuredCommand}
     */
    WRITE_ATTRIBUTES_STRUCTURED_COMMAND(ZclClusterType.GENERAL, 15, WriteAttributesStructuredCommand.class, true),
    /**
     * Write Attributes Structured Response
     * <p>
     * See {@link WriteAttributesStructuredResponse}
     */
    WRITE_ATTRIBUTES_STRUCTURED_RESPONSE(ZclClusterType.GENERAL, 16, WriteAttributesStructuredResponse.class, true),
    /**
     * Write Attributes Undivided Command
     * <p>
     * See {@link WriteAttributesUndividedCommand}
     */
    WRITE_ATTRIBUTES_UNDIVIDED_COMMAND(ZclClusterType.GENERAL, 3, WriteAttributesUndividedCommand.class, true),
    /**
     * Zone Enroll Request Command
     * <p>
     * See {@link ZoneEnrollRequestCommand}
     */
    ZONE_ENROLL_REQUEST_COMMAND(ZclClusterType.IAS_ZONE, 1, ZoneEnrollRequestCommand.class, false),
    /**
     * Zone Enroll Response
     * <p>
     * See {@link ZoneEnrollResponse}
     */
    ZONE_ENROLL_RESPONSE(ZclClusterType.IAS_ZONE, 0, ZoneEnrollResponse.class, true),
    /**
     * Zone Status Change Notification Command
     * <p>
     * See {@link ZoneStatusChangeNotificationCommand}
     */
    ZONE_STATUS_CHANGE_NOTIFICATION_COMMAND(ZclClusterType.IAS_ZONE, 0, ZoneStatusChangeNotificationCommand.class, false);
    private final int commandId;
    private final ZclClusterType clusterType;
    private final Class<? extends ZclCommand> commandClass;
    private final boolean received;

    ZclCommandType(final ZclClusterType clusterType, final int commandId, final Class<? extends ZclCommand> commandClass, final boolean received) {
        this.clusterType = clusterType;
        this.commandId = commandId;
        this.commandClass = commandClass;
        this.received = received;
    }

    public ZclClusterType getClusterType() { return clusterType; }
    public int getId() { return commandId; }
    public boolean isGeneric() { return clusterType==ZclClusterType.GENERAL; }
    public boolean isReceived() { return received; }
    public Class<? extends ZclCommand> getCommandClass() { return commandClass; }

    public static ZclCommandType getValueById(final ZclClusterType clusterType, final int commandId) {
        for (final ZclCommandType value : values()) {
            if(value.clusterType == clusterType && value.commandId == commandId) {
                return value;
            }
        }
        return null;
    }
}
