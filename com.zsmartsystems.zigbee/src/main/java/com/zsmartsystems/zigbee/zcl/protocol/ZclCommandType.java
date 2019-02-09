/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.protocol;

import java.lang.reflect.Constructor;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.AlarmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.GetAlarmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.GetAlarmResponse;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.ResetAlarmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.ResetAlarmLogCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.ResetAllAlarmsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.basic.ResetToFactoryDefaultsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.ColorLoopSetCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.EnhancedMoveToHueAndSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.EnhancedMoveToHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.EnhancedStepHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToColorTemperatureCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToHueAndSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol.CancelAllLoadControlEvents;
import com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol.CancelLoadControlEvent;
import com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol.GetScheduledEvents;
import com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol.LoadControlEventCommand;
import com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol.ReportEventStatus;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.LockDoorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.LockDoorResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.Toggle;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ToggleResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockDoorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockDoorResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockWithTimeout;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockWithTimeoutResponse;
import com.zsmartsystems.zigbee.zcl.clusters.electricalmeasurement.GetMeasurementProfileCommand;
import com.zsmartsystems.zigbee.zcl.clusters.electricalmeasurement.GetMeasurementProfileResponseCommand;
import com.zsmartsystems.zigbee.zcl.clusters.electricalmeasurement.GetProfileInfoCommand;
import com.zsmartsystems.zigbee.zcl.clusters.electricalmeasurement.GetProfileInfoResponseCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesExtended;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesExtendedResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverCommandsGenerated;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverCommandsGeneratedResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverCommandsReceived;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverCommandsReceivedResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesStructuredCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadReportingConfigurationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadReportingConfigurationResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReportAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesNoResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesStructuredCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesStructuredResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesUndividedCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupIfIdentifyingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.RemoveAllGroupsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.RemoveGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.RemoveGroupResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupResponse;
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
import com.zsmartsystems.zigbee.zcl.clusters.iaswd.Squawk;
import com.zsmartsystems.zigbee.zcl.clusters.iaswd.StartWarningCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.InitiateNormalOperationModeCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.InitiateTestModeCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneStatusChangeNotificationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.identify.IdentifyCommand;
import com.zsmartsystems.zigbee.zcl.clusters.identify.IdentifyQueryCommand;
import com.zsmartsystems.zigbee.zcl.clusters.identify.IdentifyQueryResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.ConfirmKeyDataRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.ConfirmKeyResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.EphemeralDataRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.EphemeralDataResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.InitiateKeyEstablishmentRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.InitiateKeyEstablishmentResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.TerminateKeyEstablishment;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveToLevelCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveToLevelWithOnOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveWithOnOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.StepCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.StepWithOnOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.Stop2Command;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.StopCommand;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.CancelAllMessages;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.CancelAllMessagesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.CancelMessageCommand;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.DisplayMessageCommand;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.DisplayProtectedMessageCommand;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.GetLastMessage;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.GetMessageCancellation;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.MessageConfirmation;
import com.zsmartsystems.zigbee.zcl.clusters.metering.ChangeSupply;
import com.zsmartsystems.zigbee.zcl.clusters.metering.ConfigureMirror;
import com.zsmartsystems.zigbee.zcl.clusters.metering.ConfigureNotificationFlags;
import com.zsmartsystems.zigbee.zcl.clusters.metering.ConfigureNotificationScheme;
import com.zsmartsystems.zigbee.zcl.clusters.metering.GetNotifiedMessage;
import com.zsmartsystems.zigbee.zcl.clusters.metering.GetProfile;
import com.zsmartsystems.zigbee.zcl.clusters.metering.GetProfileResponse;
import com.zsmartsystems.zigbee.zcl.clusters.metering.GetSampledData;
import com.zsmartsystems.zigbee.zcl.clusters.metering.GetSampledDataResponse;
import com.zsmartsystems.zigbee.zcl.clusters.metering.GetSnapshot;
import com.zsmartsystems.zigbee.zcl.clusters.metering.LocalChangeSupply;
import com.zsmartsystems.zigbee.zcl.clusters.metering.MirrorRemoved;
import com.zsmartsystems.zigbee.zcl.clusters.metering.MirrorReportAttributeResponse;
import com.zsmartsystems.zigbee.zcl.clusters.metering.PublishSnapshot;
import com.zsmartsystems.zigbee.zcl.clusters.metering.RemoveMirror;
import com.zsmartsystems.zigbee.zcl.clusters.metering.RequestFastPollMode;
import com.zsmartsystems.zigbee.zcl.clusters.metering.RequestFastPollModeResponse;
import com.zsmartsystems.zigbee.zcl.clusters.metering.RequestMirror;
import com.zsmartsystems.zigbee.zcl.clusters.metering.RequestMirrorResponse;
import com.zsmartsystems.zigbee.zcl.clusters.metering.ResetLoadLimitCounter;
import com.zsmartsystems.zigbee.zcl.clusters.metering.ScheduleSnapshot;
import com.zsmartsystems.zigbee.zcl.clusters.metering.ScheduleSnapshotResponse;
import com.zsmartsystems.zigbee.zcl.clusters.metering.SetSupplyStatus;
import com.zsmartsystems.zigbee.zcl.clusters.metering.SetUncontrolledFlowThreshold;
import com.zsmartsystems.zigbee.zcl.clusters.metering.StartSampling;
import com.zsmartsystems.zigbee.zcl.clusters.metering.StartSamplingResponse;
import com.zsmartsystems.zigbee.zcl.clusters.metering.SupplyStatusResponse;
import com.zsmartsystems.zigbee.zcl.clusters.metering.TakeSnapshot;
import com.zsmartsystems.zigbee.zcl.clusters.metering.TakeSnapshotResponse;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffWithEffectCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnWithRecallGlobalSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnWithTimedOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.ToggleCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImageBlockCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImageBlockResponse;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImageNotifyCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImagePageCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QueryNextImageCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QueryNextImageResponse;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QuerySpecificFileCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QuerySpecificFileResponse;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.UpgradeEndCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.UpgradeEndResponse;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.CheckInCommand;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.CheckInResponse;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.FastPollStopCommand;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.SetLongPollIntervalCommand;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.SetShortPollIntervalCommand;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.ChangeDebt;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.ChangePaymentMode;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.ChangePaymentModeResponse;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.ConsumerTopUp;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.ConsumerTopUpResponse;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.CreditAdjustment;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.EmergencyCreditSetup;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.GetDebtRepaymentLog;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.GetPrepaySnapshot;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.GetTopUpLog;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.PublishDebtLog;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.PublishPrepaySnapshot;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.PublishTopUpLog;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.SelectAvailableEmergencyCredit;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.SetLowCreditWarningLevel;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.SetMaximumCreditLimit;
import com.zsmartsystems.zigbee.zcl.clusters.prepayment.SetOverallDebtCap;
import com.zsmartsystems.zigbee.zcl.clusters.price.CancelTariffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.CppEventResponse;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetBillingPeriodCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetBlockPeriodCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetBlockThresholdsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetCalorificValueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetCo2ValueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetConsolidatedBillCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetConversionFactorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetCreditPaymentCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetCurrencyConversionCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetCurrentPriceCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetPriceMatrixCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetScheduledPricesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetTariffCancellationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetTariffInformationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.GetTierLabelsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PriceAcknowledgementCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishBillingPeriodCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishBlockPeriodCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishBlockThresholdsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishCalorificValueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishCo2ValueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishConsolidatedBillCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishConversionFactorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishCppEventCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishCreditPaymentCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishCurrencyConversionCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishPriceCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishPriceMatrixCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishTariffInformationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.price.PublishTierLabelsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.AnchorNodeAnnounceCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.CompactLocationDataNotificationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.DeviceConfigurationResponse;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.GetDeviceConfigurationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.GetLocationDataCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.LocationDataNotificationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.LocationDataResponse;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.ReportRssiMeasurementsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.RequestOwnLocationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.RssiPingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.RssiRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.RssiResponse;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.SendPingsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.SetAbsoluteLocationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.SetDeviceConfigurationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.AddSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.AddSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.GetSceneMembershipCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.GetSceneMembershipResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RecallSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RemoveAllScenesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RemoveAllScenesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RemoveSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RemoveSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.StoreSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.StoreSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.ViewSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.ViewSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.AckTransferDataClientToServer;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.AckTransferDataServerToClient;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.CloseTunnel;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.GetSupportedTunnelProtocols;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.ReadyDataClientToServer;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.ReadyDataServerToClient;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.RequestTunnel;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.RequestTunnelResponse;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.SupportedTunnelProtocolsResponse;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.TransferDataClientToServer;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.TransferDataErrorClientToServer;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.TransferDataErrorServerToClient;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.TransferDataServerToClient;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.TunnelClosureNotification;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.ClearWeeklySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.GetRelayStatusLog;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.GetRelayStatusLogResponse;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.GetWeeklySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.GetWeeklyScheduleResponse;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.SetWeeklySchedule;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.SetpointRaiseLowerCommand;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringDownClose;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringGoToLiftPercentage;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringGoToLiftValue;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringGoToTiltPercentage;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringGoToTiltValue;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringStop;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringUpOpen;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public enum ZclCommandType {
    /**
     * ALARM_COMMAND: Alarm Command
     * <p>
     * See {@link AlarmCommand}
     */
    ALARM_COMMAND(0x0009, 0, AlarmCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_ALARM_COMMAND: Get Alarm Command
     * <p>
     * See {@link GetAlarmCommand}
     */
    GET_ALARM_COMMAND(0x0009, 2, GetAlarmCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_ALARM_RESPONSE: Get Alarm Response
     * <p>
     * See {@link GetAlarmResponse}
     */
    GET_ALARM_RESPONSE(0x0009, 1, GetAlarmResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * RESET_ALARM_COMMAND: Reset Alarm Command
     * <p>
     * See {@link ResetAlarmCommand}
     */
    RESET_ALARM_COMMAND(0x0009, 0, ResetAlarmCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * RESET_ALARM_LOG_COMMAND: Reset Alarm Log Command
     * <p>
     * See {@link ResetAlarmLogCommand}
     */
    RESET_ALARM_LOG_COMMAND(0x0009, 3, ResetAlarmLogCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * RESET_ALL_ALARMS_COMMAND: Reset All Alarms Command
     * <p>
     * See {@link ResetAllAlarmsCommand}
     */
    RESET_ALL_ALARMS_COMMAND(0x0009, 1, ResetAllAlarmsCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * RESET_TO_FACTORY_DEFAULTS_COMMAND: Reset To Factory Defaults Command
     * <p>
     * See {@link ResetToFactoryDefaultsCommand}
     */
    RESET_TO_FACTORY_DEFAULTS_COMMAND(0x0000, 0, ResetToFactoryDefaultsCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * COLOR_LOOP_SET_COMMAND: Color Loop Set Command
     * <p>
     * See {@link ColorLoopSetCommand}
     */
    COLOR_LOOP_SET_COMMAND(0x0300, 67, ColorLoopSetCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ENHANCED_MOVE_TO_HUE_AND_SATURATION_COMMAND: Enhanced Move To Hue And Saturation Command
     * <p>
     * See {@link EnhancedMoveToHueAndSaturationCommand}
     */
    ENHANCED_MOVE_TO_HUE_AND_SATURATION_COMMAND(0x0300, 66, EnhancedMoveToHueAndSaturationCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ENHANCED_MOVE_TO_HUE_COMMAND: Enhanced Move To Hue Command
     * <p>
     * See {@link EnhancedMoveToHueCommand}
     */
    ENHANCED_MOVE_TO_HUE_COMMAND(0x0300, 64, EnhancedMoveToHueCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ENHANCED_STEP_HUE_COMMAND: Enhanced Step Hue Command
     * <p>
     * See {@link EnhancedStepHueCommand}
     */
    ENHANCED_STEP_HUE_COMMAND(0x0300, 65, EnhancedStepHueCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * MOVE_COLOR_COMMAND: Move Color Command
     * <p>
     * See {@link MoveColorCommand}
     */
    MOVE_COLOR_COMMAND(0x0300, 8, MoveColorCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * MOVE_HUE_COMMAND: Move Hue Command
     * <p>
     * See {@link MoveHueCommand}
     */
    MOVE_HUE_COMMAND(0x0300, 1, MoveHueCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * MOVE_SATURATION_COMMAND: Move Saturation Command
     * <p>
     * See {@link MoveSaturationCommand}
     */
    MOVE_SATURATION_COMMAND(0x0300, 4, MoveSaturationCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * MOVE_TO_COLOR_COMMAND: Move To Color Command
     * <p>
     * See {@link MoveToColorCommand}
     */
    MOVE_TO_COLOR_COMMAND(0x0300, 7, MoveToColorCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * MOVE_TO_COLOR_TEMPERATURE_COMMAND: Move To Color Temperature Command
     * <p>
     * See {@link MoveToColorTemperatureCommand}
     */
    MOVE_TO_COLOR_TEMPERATURE_COMMAND(0x0300, 10, MoveToColorTemperatureCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * MOVE_TO_HUE_AND_SATURATION_COMMAND: Move To Hue And Saturation Command
     * <p>
     * See {@link MoveToHueAndSaturationCommand}
     */
    MOVE_TO_HUE_AND_SATURATION_COMMAND(0x0300, 6, MoveToHueAndSaturationCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * MOVE_TO_HUE_COMMAND: Move To Hue Command
     * <p>
     * See {@link MoveToHueCommand}
     */
    MOVE_TO_HUE_COMMAND(0x0300, 0, MoveToHueCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * MOVE_TO_SATURATION_COMMAND: Move To Saturation Command
     * <p>
     * See {@link MoveToSaturationCommand}
     */
    MOVE_TO_SATURATION_COMMAND(0x0300, 3, MoveToSaturationCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * STEP_COLOR_COMMAND: Step Color Command
     * <p>
     * See {@link StepColorCommand}
     */
    STEP_COLOR_COMMAND(0x0300, 9, StepColorCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * STEP_HUE_COMMAND: Step Hue Command
     * <p>
     * See {@link StepHueCommand}
     */
    STEP_HUE_COMMAND(0x0300, 2, StepHueCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * STEP_SATURATION_COMMAND: Step Saturation Command
     * <p>
     * See {@link StepSaturationCommand}
     */
    STEP_SATURATION_COMMAND(0x0300, 5, StepSaturationCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * CANCEL_ALL_LOAD_CONTROL_EVENTS: Cancel All Load Control Events
     * <p>
     * See {@link CancelAllLoadControlEvents}
     */
    CANCEL_ALL_LOAD_CONTROL_EVENTS(0x0701, 2, CancelAllLoadControlEvents.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * CANCEL_LOAD_CONTROL_EVENT: Cancel Load Control Event
     * <p>
     * See {@link CancelLoadControlEvent}
     */
    CANCEL_LOAD_CONTROL_EVENT(0x0701, 1, CancelLoadControlEvent.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_SCHEDULED_EVENTS: Get Scheduled Events
     * <p>
     * See {@link GetScheduledEvents}
     */
    GET_SCHEDULED_EVENTS(0x0701, 1, GetScheduledEvents.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * LOAD_CONTROL_EVENT_COMMAND: Load Control Event Command
     * <p>
     * See {@link LoadControlEventCommand}
     */
    LOAD_CONTROL_EVENT_COMMAND(0x0701, 0, LoadControlEventCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * REPORT_EVENT_STATUS: Report Event Status
     * <p>
     * See {@link ReportEventStatus}
     */
    REPORT_EVENT_STATUS(0x0701, 0, ReportEventStatus.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * LOCK_DOOR_COMMAND: Lock Door Command
     * <p>
     * See {@link LockDoorCommand}
     */
    LOCK_DOOR_COMMAND(0x0101, 0, LockDoorCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * LOCK_DOOR_RESPONSE: Lock Door Response
     * <p>
     * See {@link LockDoorResponse}
     */
    LOCK_DOOR_RESPONSE(0x0101, 0, LockDoorResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * TOGGLE: Toggle
     * <p>
     * See {@link Toggle}
     */
    TOGGLE(0x0101, 2, Toggle.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * TOGGLE_RESPONSE: Toggle Response
     * <p>
     * See {@link ToggleResponse}
     */
    TOGGLE_RESPONSE(0x0101, 2, ToggleResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * UNLOCK_DOOR_COMMAND: Unlock Door Command
     * <p>
     * See {@link UnlockDoorCommand}
     */
    UNLOCK_DOOR_COMMAND(0x0101, 1, UnlockDoorCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * UNLOCK_DOOR_RESPONSE: Unlock Door Response
     * <p>
     * See {@link UnlockDoorResponse}
     */
    UNLOCK_DOOR_RESPONSE(0x0101, 1, UnlockDoorResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * UNLOCK_WITH_TIMEOUT: Unlock With Timeout
     * <p>
     * See {@link UnlockWithTimeout}
     */
    UNLOCK_WITH_TIMEOUT(0x0101, 3, UnlockWithTimeout.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * UNLOCK_WITH_TIMEOUT_RESPONSE: Unlock With Timeout Response
     * <p>
     * See {@link UnlockWithTimeoutResponse}
     */
    UNLOCK_WITH_TIMEOUT_RESPONSE(0x0101, 3, UnlockWithTimeoutResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_MEASUREMENT_PROFILE_COMMAND: Get Measurement Profile Command
     * <p>
     * See {@link GetMeasurementProfileCommand}
     */
    GET_MEASUREMENT_PROFILE_COMMAND(0x0B04, 1, GetMeasurementProfileCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_MEASUREMENT_PROFILE_RESPONSE_COMMAND: Get Measurement Profile Response Command
     * <p>
     * See {@link GetMeasurementProfileResponseCommand}
     */
    GET_MEASUREMENT_PROFILE_RESPONSE_COMMAND(0x0B04, 1, GetMeasurementProfileResponseCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_PROFILE_INFO_COMMAND: Get Profile Info Command
     * <p>
     * See {@link GetProfileInfoCommand}
     */
    GET_PROFILE_INFO_COMMAND(0x0B04, 0, GetProfileInfoCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_PROFILE_INFO_RESPONSE_COMMAND: Get Profile Info Response Command
     * <p>
     * See {@link GetProfileInfoResponseCommand}
     */
    GET_PROFILE_INFO_RESPONSE_COMMAND(0x0B04, 0, GetProfileInfoResponseCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * CONFIGURE_REPORTING_COMMAND: Configure Reporting Command
     * <p>
     * See {@link ConfigureReportingCommand}
     */
    CONFIGURE_REPORTING_COMMAND(0xFFFF, 6, ConfigureReportingCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * CONFIGURE_REPORTING_RESPONSE: Configure Reporting Response
     * <p>
     * See {@link ConfigureReportingResponse}
     */
    CONFIGURE_REPORTING_RESPONSE(0xFFFF, 7, ConfigureReportingResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * DEFAULT_RESPONSE: Default Response
     * <p>
     * See {@link DefaultResponse}
     */
    DEFAULT_RESPONSE(0xFFFF, 11, DefaultResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * DISCOVER_ATTRIBUTES_COMMAND: Discover Attributes Command
     * <p>
     * See {@link DiscoverAttributesCommand}
     */
    DISCOVER_ATTRIBUTES_COMMAND(0xFFFF, 12, DiscoverAttributesCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * DISCOVER_ATTRIBUTES_EXTENDED: Discover Attributes Extended
     * <p>
     * See {@link DiscoverAttributesExtended}
     */
    DISCOVER_ATTRIBUTES_EXTENDED(0xFFFF, 21, DiscoverAttributesExtended.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * DISCOVER_ATTRIBUTES_EXTENDED_RESPONSE: Discover Attributes Extended Response
     * <p>
     * See {@link DiscoverAttributesExtendedResponse}
     */
    DISCOVER_ATTRIBUTES_EXTENDED_RESPONSE(0xFFFF, 22, DiscoverAttributesExtendedResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * DISCOVER_ATTRIBUTES_RESPONSE: Discover Attributes Response
     * <p>
     * See {@link DiscoverAttributesResponse}
     */
    DISCOVER_ATTRIBUTES_RESPONSE(0xFFFF, 13, DiscoverAttributesResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * DISCOVER_COMMANDS_GENERATED: Discover Commands Generated
     * <p>
     * See {@link DiscoverCommandsGenerated}
     */
    DISCOVER_COMMANDS_GENERATED(0xFFFF, 19, DiscoverCommandsGenerated.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * DISCOVER_COMMANDS_GENERATED_RESPONSE: Discover Commands Generated Response
     * <p>
     * See {@link DiscoverCommandsGeneratedResponse}
     */
    DISCOVER_COMMANDS_GENERATED_RESPONSE(0xFFFF, 20, DiscoverCommandsGeneratedResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * DISCOVER_COMMANDS_RECEIVED: Discover Commands Received
     * <p>
     * See {@link DiscoverCommandsReceived}
     */
    DISCOVER_COMMANDS_RECEIVED(0xFFFF, 17, DiscoverCommandsReceived.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * DISCOVER_COMMANDS_RECEIVED_RESPONSE: Discover Commands Received Response
     * <p>
     * See {@link DiscoverCommandsReceivedResponse}
     */
    DISCOVER_COMMANDS_RECEIVED_RESPONSE(0xFFFF, 18, DiscoverCommandsReceivedResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * READ_ATTRIBUTES_COMMAND: Read Attributes Command
     * <p>
     * See {@link ReadAttributesCommand}
     */
    READ_ATTRIBUTES_COMMAND(0xFFFF, 0, ReadAttributesCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * READ_ATTRIBUTES_RESPONSE: Read Attributes Response
     * <p>
     * See {@link ReadAttributesResponse}
     */
    READ_ATTRIBUTES_RESPONSE(0xFFFF, 1, ReadAttributesResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * READ_ATTRIBUTES_STRUCTURED_COMMAND: Read Attributes Structured Command
     * <p>
     * See {@link ReadAttributesStructuredCommand}
     */
    READ_ATTRIBUTES_STRUCTURED_COMMAND(0xFFFF, 14, ReadAttributesStructuredCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * READ_REPORTING_CONFIGURATION_COMMAND: Read Reporting Configuration Command
     * <p>
     * See {@link ReadReportingConfigurationCommand}
     */
    READ_REPORTING_CONFIGURATION_COMMAND(0xFFFF, 8, ReadReportingConfigurationCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * READ_REPORTING_CONFIGURATION_RESPONSE: Read Reporting Configuration Response
     * <p>
     * See {@link ReadReportingConfigurationResponse}
     */
    READ_REPORTING_CONFIGURATION_RESPONSE(0xFFFF, 9, ReadReportingConfigurationResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * REPORT_ATTRIBUTES_COMMAND: Report Attributes Command
     * <p>
     * See {@link ReportAttributesCommand}
     */
    REPORT_ATTRIBUTES_COMMAND(0xFFFF, 10, ReportAttributesCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * WRITE_ATTRIBUTES_COMMAND: Write Attributes Command
     * <p>
     * See {@link WriteAttributesCommand}
     */
    WRITE_ATTRIBUTES_COMMAND(0xFFFF, 2, WriteAttributesCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * WRITE_ATTRIBUTES_NO_RESPONSE: Write Attributes No Response
     * <p>
     * See {@link WriteAttributesNoResponse}
     */
    WRITE_ATTRIBUTES_NO_RESPONSE(0xFFFF, 5, WriteAttributesNoResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * WRITE_ATTRIBUTES_RESPONSE: Write Attributes Response
     * <p>
     * See {@link WriteAttributesResponse}
     */
    WRITE_ATTRIBUTES_RESPONSE(0xFFFF, 4, WriteAttributesResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * WRITE_ATTRIBUTES_STRUCTURED_COMMAND: Write Attributes Structured Command
     * <p>
     * See {@link WriteAttributesStructuredCommand}
     */
    WRITE_ATTRIBUTES_STRUCTURED_COMMAND(0xFFFF, 15, WriteAttributesStructuredCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * WRITE_ATTRIBUTES_STRUCTURED_RESPONSE: Write Attributes Structured Response
     * <p>
     * See {@link WriteAttributesStructuredResponse}
     */
    WRITE_ATTRIBUTES_STRUCTURED_RESPONSE(0xFFFF, 16, WriteAttributesStructuredResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * WRITE_ATTRIBUTES_UNDIVIDED_COMMAND: Write Attributes Undivided Command
     * <p>
     * See {@link WriteAttributesUndividedCommand}
     */
    WRITE_ATTRIBUTES_UNDIVIDED_COMMAND(0xFFFF, 3, WriteAttributesUndividedCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ADD_GROUP_COMMAND: Add Group Command
     * <p>
     * See {@link AddGroupCommand}
     */
    ADD_GROUP_COMMAND(0x0004, 0, AddGroupCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ADD_GROUP_IF_IDENTIFYING_COMMAND: Add Group If Identifying Command
     * <p>
     * See {@link AddGroupIfIdentifyingCommand}
     */
    ADD_GROUP_IF_IDENTIFYING_COMMAND(0x0004, 5, AddGroupIfIdentifyingCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ADD_GROUP_RESPONSE: Add Group Response
     * <p>
     * See {@link AddGroupResponse}
     */
    ADD_GROUP_RESPONSE(0x0004, 0, AddGroupResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_GROUP_MEMBERSHIP_COMMAND: Get Group Membership Command
     * <p>
     * See {@link GetGroupMembershipCommand}
     */
    GET_GROUP_MEMBERSHIP_COMMAND(0x0004, 2, GetGroupMembershipCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_GROUP_MEMBERSHIP_RESPONSE: Get Group Membership Response
     * <p>
     * See {@link GetGroupMembershipResponse}
     */
    GET_GROUP_MEMBERSHIP_RESPONSE(0x0004, 2, GetGroupMembershipResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * REMOVE_ALL_GROUPS_COMMAND: Remove All Groups Command
     * <p>
     * See {@link RemoveAllGroupsCommand}
     */
    REMOVE_ALL_GROUPS_COMMAND(0x0004, 4, RemoveAllGroupsCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * REMOVE_GROUP_COMMAND: Remove Group Command
     * <p>
     * See {@link RemoveGroupCommand}
     */
    REMOVE_GROUP_COMMAND(0x0004, 3, RemoveGroupCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * REMOVE_GROUP_RESPONSE: Remove Group Response
     * <p>
     * See {@link RemoveGroupResponse}
     */
    REMOVE_GROUP_RESPONSE(0x0004, 3, RemoveGroupResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * VIEW_GROUP_COMMAND: View Group Command
     * <p>
     * See {@link ViewGroupCommand}
     */
    VIEW_GROUP_COMMAND(0x0004, 1, ViewGroupCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * VIEW_GROUP_RESPONSE: View Group Response
     * <p>
     * See {@link ViewGroupResponse}
     */
    VIEW_GROUP_RESPONSE(0x0004, 1, ViewGroupResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * ARM_COMMAND: Arm Command
     * <p>
     * See {@link ArmCommand}
     */
    ARM_COMMAND(0x0501, 0, ArmCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ARM_RESPONSE: Arm Response
     * <p>
     * See {@link ArmResponse}
     */
    ARM_RESPONSE(0x0501, 0, ArmResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * BYPASS_COMMAND: Bypass Command
     * <p>
     * See {@link BypassCommand}
     */
    BYPASS_COMMAND(0x0501, 1, BypassCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * BYPASS_RESPONSE: Bypass Response
     * <p>
     * See {@link BypassResponse}
     */
    BYPASS_RESPONSE(0x0501, 7, BypassResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * EMERGENCY_COMMAND: Emergency Command
     * <p>
     * See {@link EmergencyCommand}
     */
    EMERGENCY_COMMAND(0x0501, 2, EmergencyCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * FIRE_COMMAND: Fire Command
     * <p>
     * See {@link FireCommand}
     */
    FIRE_COMMAND(0x0501, 3, FireCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_BYPASSED_ZONE_LIST_COMMAND: Get Bypassed Zone List Command
     * <p>
     * See {@link GetBypassedZoneListCommand}
     */
    GET_BYPASSED_ZONE_LIST_COMMAND(0x0501, 8, GetBypassedZoneListCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_PANEL_STATUS_COMMAND: Get Panel Status Command
     * <p>
     * See {@link GetPanelStatusCommand}
     */
    GET_PANEL_STATUS_COMMAND(0x0501, 7, GetPanelStatusCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_PANEL_STATUS_RESPONSE: Get Panel Status Response
     * <p>
     * See {@link GetPanelStatusResponse}
     */
    GET_PANEL_STATUS_RESPONSE(0x0501, 5, GetPanelStatusResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_ZONE_ID_MAP_COMMAND: Get Zone ID Map Command
     * <p>
     * See {@link GetZoneIdMapCommand}
     */
    GET_ZONE_ID_MAP_COMMAND(0x0501, 5, GetZoneIdMapCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_ZONE_ID_MAP_RESPONSE: Get Zone ID Map Response
     * <p>
     * See {@link GetZoneIdMapResponse}
     */
    GET_ZONE_ID_MAP_RESPONSE(0x0501, 1, GetZoneIdMapResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_ZONE_INFORMATION_COMMAND: Get Zone Information Command
     * <p>
     * See {@link GetZoneInformationCommand}
     */
    GET_ZONE_INFORMATION_COMMAND(0x0501, 6, GetZoneInformationCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_ZONE_INFORMATION_RESPONSE: Get Zone Information Response
     * <p>
     * See {@link GetZoneInformationResponse}
     */
    GET_ZONE_INFORMATION_RESPONSE(0x0501, 2, GetZoneInformationResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_ZONE_STATUS_COMMAND: Get Zone Status Command
     * <p>
     * See {@link GetZoneStatusCommand}
     */
    GET_ZONE_STATUS_COMMAND(0x0501, 9, GetZoneStatusCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_ZONE_STATUS_RESPONSE: Get Zone Status Response
     * <p>
     * See {@link GetZoneStatusResponse}
     */
    GET_ZONE_STATUS_RESPONSE(0x0501, 8, GetZoneStatusResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PANEL_STATUS_CHANGED_COMMAND: Panel Status Changed Command
     * <p>
     * See {@link PanelStatusChangedCommand}
     */
    PANEL_STATUS_CHANGED_COMMAND(0x0501, 4, PanelStatusChangedCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PANIC_COMMAND: Panic Command
     * <p>
     * See {@link PanicCommand}
     */
    PANIC_COMMAND(0x0501, 4, PanicCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * SET_BYPASSED_ZONE_LIST_COMMAND: Set Bypassed Zone List Command
     * <p>
     * See {@link SetBypassedZoneListCommand}
     */
    SET_BYPASSED_ZONE_LIST_COMMAND(0x0501, 6, SetBypassedZoneListCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * ZONE_STATUS_CHANGED_COMMAND: Zone Status Changed Command
     * <p>
     * See {@link ZoneStatusChangedCommand}
     */
    ZONE_STATUS_CHANGED_COMMAND(0x0501, 3, ZoneStatusChangedCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * SQUAWK: Squawk
     * <p>
     * See {@link Squawk}
     */
    SQUAWK(0x0502, 1, Squawk.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * START_WARNING_COMMAND: Start Warning Command
     * <p>
     * See {@link StartWarningCommand}
     */
    START_WARNING_COMMAND(0x0502, 0, StartWarningCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * INITIATE_NORMAL_OPERATION_MODE_COMMAND: Initiate Normal Operation Mode Command
     * <p>
     * See {@link InitiateNormalOperationModeCommand}
     */
    INITIATE_NORMAL_OPERATION_MODE_COMMAND(0x0500, 1, InitiateNormalOperationModeCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * INITIATE_TEST_MODE_COMMAND: Initiate Test Mode Command
     * <p>
     * See {@link InitiateTestModeCommand}
     */
    INITIATE_TEST_MODE_COMMAND(0x0500, 2, InitiateTestModeCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ZONE_ENROLL_REQUEST_COMMAND: Zone Enroll Request Command
     * <p>
     * See {@link ZoneEnrollRequestCommand}
     */
    ZONE_ENROLL_REQUEST_COMMAND(0x0500, 1, ZoneEnrollRequestCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * ZONE_ENROLL_RESPONSE: Zone Enroll Response
     * <p>
     * See {@link ZoneEnrollResponse}
     */
    ZONE_ENROLL_RESPONSE(0x0500, 0, ZoneEnrollResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ZONE_STATUS_CHANGE_NOTIFICATION_COMMAND: Zone Status Change Notification Command
     * <p>
     * See {@link ZoneStatusChangeNotificationCommand}
     */
    ZONE_STATUS_CHANGE_NOTIFICATION_COMMAND(0x0500, 0, ZoneStatusChangeNotificationCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * IDENTIFY_COMMAND: Identify Command
     * <p>
     * See {@link IdentifyCommand}
     */
    IDENTIFY_COMMAND(0x0003, 0, IdentifyCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * IDENTIFY_QUERY_COMMAND: Identify Query Command
     * <p>
     * See {@link IdentifyQueryCommand}
     */
    IDENTIFY_QUERY_COMMAND(0x0003, 1, IdentifyQueryCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * IDENTIFY_QUERY_RESPONSE: Identify Query Response
     * <p>
     * See {@link IdentifyQueryResponse}
     */
    IDENTIFY_QUERY_RESPONSE(0x0003, 0, IdentifyQueryResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * CONFIRM_KEY_DATA_REQUEST_COMMAND: Confirm Key Data Request Command
     * <p>
     * See {@link ConfirmKeyDataRequestCommand}
     */
    CONFIRM_KEY_DATA_REQUEST_COMMAND(0x0800, 2, ConfirmKeyDataRequestCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * CONFIRM_KEY_RESPONSE: Confirm Key Response
     * <p>
     * See {@link ConfirmKeyResponse}
     */
    CONFIRM_KEY_RESPONSE(0x0800, 2, ConfirmKeyResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * EPHEMERAL_DATA_REQUEST_COMMAND: Ephemeral Data Request Command
     * <p>
     * See {@link EphemeralDataRequestCommand}
     */
    EPHEMERAL_DATA_REQUEST_COMMAND(0x0800, 1, EphemeralDataRequestCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * EPHEMERAL_DATA_RESPONSE: Ephemeral Data Response
     * <p>
     * See {@link EphemeralDataResponse}
     */
    EPHEMERAL_DATA_RESPONSE(0x0800, 1, EphemeralDataResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * INITIATE_KEY_ESTABLISHMENT_REQUEST_COMMAND: Initiate Key Establishment Request Command
     * <p>
     * See {@link InitiateKeyEstablishmentRequestCommand}
     */
    INITIATE_KEY_ESTABLISHMENT_REQUEST_COMMAND(0x0800, 0, InitiateKeyEstablishmentRequestCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * INITIATE_KEY_ESTABLISHMENT_RESPONSE: Initiate Key Establishment Response
     * <p>
     * See {@link InitiateKeyEstablishmentResponse}
     */
    INITIATE_KEY_ESTABLISHMENT_RESPONSE(0x0800, 0, InitiateKeyEstablishmentResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * TERMINATE_KEY_ESTABLISHMENT: Terminate Key Establishment
     * <p>
     * See {@link TerminateKeyEstablishment}
     */
    TERMINATE_KEY_ESTABLISHMENT(0x0800, 3, TerminateKeyEstablishment.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * MOVE_COMMAND: Move Command
     * <p>
     * See {@link MoveCommand}
     */
    MOVE_COMMAND(0x0008, 1, MoveCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * MOVE_TO_LEVEL_COMMAND: Move To Level Command
     * <p>
     * See {@link MoveToLevelCommand}
     */
    MOVE_TO_LEVEL_COMMAND(0x0008, 0, MoveToLevelCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * MOVE_TO_LEVEL_WITH_ON_OFF_COMMAND: Move To Level (with On/Off) Command
     * <p>
     * See {@link MoveToLevelWithOnOffCommand}
     */
    MOVE_TO_LEVEL_WITH_ON_OFF_COMMAND(0x0008, 4, MoveToLevelWithOnOffCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * MOVE_WITH_ON_OFF_COMMAND: Move (with On/Off) Command
     * <p>
     * See {@link MoveWithOnOffCommand}
     */
    MOVE_WITH_ON_OFF_COMMAND(0x0008, 5, MoveWithOnOffCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * STEP_COMMAND: Step Command
     * <p>
     * See {@link StepCommand}
     */
    STEP_COMMAND(0x0008, 2, StepCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * STEP_WITH_ON_OFF_COMMAND: Step (with On/Off) Command
     * <p>
     * See {@link StepWithOnOffCommand}
     */
    STEP_WITH_ON_OFF_COMMAND(0x0008, 6, StepWithOnOffCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * STOP_2_COMMAND: Stop 2 Command
     * <p>
     * See {@link Stop2Command}
     */
    STOP_2_COMMAND(0x0008, 7, Stop2Command.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * STOP_COMMAND: Stop Command
     * <p>
     * See {@link StopCommand}
     */
    STOP_COMMAND(0x0008, 3, StopCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * CANCEL_ALL_MESSAGES: Cancel All Messages
     * <p>
     * See {@link CancelAllMessages}
     */
    CANCEL_ALL_MESSAGES(0x0703, 3, CancelAllMessages.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * CANCEL_ALL_MESSAGES_COMMAND: Cancel All Messages Command
     * <p>
     * See {@link CancelAllMessagesCommand}
     */
    CANCEL_ALL_MESSAGES_COMMAND(0x0703, 3, CancelAllMessagesCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * CANCEL_MESSAGE_COMMAND: Cancel Message Command
     * <p>
     * See {@link CancelMessageCommand}
     */
    CANCEL_MESSAGE_COMMAND(0x0703, 1, CancelMessageCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * DISPLAY_MESSAGE_COMMAND: Display Message Command
     * <p>
     * See {@link DisplayMessageCommand}
     */
    DISPLAY_MESSAGE_COMMAND(0x0703, 0, DisplayMessageCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * DISPLAY_PROTECTED_MESSAGE_COMMAND: Display Protected Message Command
     * <p>
     * See {@link DisplayProtectedMessageCommand}
     */
    DISPLAY_PROTECTED_MESSAGE_COMMAND(0x0703, 2, DisplayProtectedMessageCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_LAST_MESSAGE: Get Last Message
     * <p>
     * See {@link GetLastMessage}
     */
    GET_LAST_MESSAGE(0x0703, 0, GetLastMessage.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_MESSAGE_CANCELLATION: Get Message Cancellation
     * <p>
     * See {@link GetMessageCancellation}
     */
    GET_MESSAGE_CANCELLATION(0x0703, 2, GetMessageCancellation.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * MESSAGE_CONFIRMATION: Message Confirmation
     * <p>
     * See {@link MessageConfirmation}
     */
    MESSAGE_CONFIRMATION(0x0703, 1, MessageConfirmation.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * CHANGE_SUPPLY: Change Supply
     * <p>
     * See {@link ChangeSupply}
     */
    CHANGE_SUPPLY(0x0702, 11, ChangeSupply.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * CONFIGURE_MIRROR: Configure Mirror
     * <p>
     * See {@link ConfigureMirror}
     */
    CONFIGURE_MIRROR(0x0702, 8, ConfigureMirror.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * CONFIGURE_NOTIFICATION_FLAGS: Configure Notification Flags
     * <p>
     * See {@link ConfigureNotificationFlags}
     */
    CONFIGURE_NOTIFICATION_FLAGS(0x0702, 10, ConfigureNotificationFlags.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * CONFIGURE_NOTIFICATION_SCHEME: Configure Notification Scheme
     * <p>
     * See {@link ConfigureNotificationScheme}
     */
    CONFIGURE_NOTIFICATION_SCHEME(0x0702, 9, ConfigureNotificationScheme.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_NOTIFIED_MESSAGE: Get Notified Message
     * <p>
     * See {@link GetNotifiedMessage}
     */
    GET_NOTIFIED_MESSAGE(0x0702, 11, GetNotifiedMessage.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_PROFILE: Get Profile
     * <p>
     * See {@link GetProfile}
     */
    GET_PROFILE(0x0702, 0, GetProfile.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_PROFILE_RESPONSE: Get Profile Response
     * <p>
     * See {@link GetProfileResponse}
     */
    GET_PROFILE_RESPONSE(0x0702, 0, GetProfileResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_SAMPLED_DATA: Get Sampled Data
     * <p>
     * See {@link GetSampledData}
     */
    GET_SAMPLED_DATA(0x0702, 8, GetSampledData.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_SAMPLED_DATA_RESPONSE: Get Sampled Data Response
     * <p>
     * See {@link GetSampledDataResponse}
     */
    GET_SAMPLED_DATA_RESPONSE(0x0702, 7, GetSampledDataResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_SNAPSHOT: Get Snapshot
     * <p>
     * See {@link GetSnapshot}
     */
    GET_SNAPSHOT(0x0702, 6, GetSnapshot.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * LOCAL_CHANGE_SUPPLY: Local Change Supply
     * <p>
     * See {@link LocalChangeSupply}
     */
    LOCAL_CHANGE_SUPPLY(0x0702, 12, LocalChangeSupply.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * MIRROR_REMOVED: Mirror Removed
     * <p>
     * See {@link MirrorRemoved}
     */
    MIRROR_REMOVED(0x0702, 2, MirrorRemoved.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * MIRROR_REPORT_ATTRIBUTE_RESPONSE: Mirror Report Attribute Response
     * <p>
     * See {@link MirrorReportAttributeResponse}
     */
    MIRROR_REPORT_ATTRIBUTE_RESPONSE(0x0702, 9, MirrorReportAttributeResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * PUBLISH_SNAPSHOT: Publish Snapshot
     * <p>
     * See {@link PublishSnapshot}
     */
    PUBLISH_SNAPSHOT(0x0702, 6, PublishSnapshot.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * REMOVE_MIRROR: Remove Mirror
     * <p>
     * See {@link RemoveMirror}
     */
    REMOVE_MIRROR(0x0702, 2, RemoveMirror.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * REQUEST_FAST_POLL_MODE: Request Fast Poll Mode
     * <p>
     * See {@link RequestFastPollMode}
     */
    REQUEST_FAST_POLL_MODE(0x0702, 3, RequestFastPollMode.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * REQUEST_FAST_POLL_MODE_RESPONSE: Request Fast Poll Mode Response
     * <p>
     * See {@link RequestFastPollModeResponse}
     */
    REQUEST_FAST_POLL_MODE_RESPONSE(0x0702, 3, RequestFastPollModeResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * REQUEST_MIRROR: Request Mirror
     * <p>
     * See {@link RequestMirror}
     */
    REQUEST_MIRROR(0x0702, 1, RequestMirror.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * REQUEST_MIRROR_RESPONSE: Request Mirror Response
     * <p>
     * See {@link RequestMirrorResponse}
     */
    REQUEST_MIRROR_RESPONSE(0x0702, 1, RequestMirrorResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * RESET_LOAD_LIMIT_COUNTER: Reset Load Limit Counter
     * <p>
     * See {@link ResetLoadLimitCounter}
     */
    RESET_LOAD_LIMIT_COUNTER(0x0702, 10, ResetLoadLimitCounter.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * SCHEDULE_SNAPSHOT: Schedule Snapshot
     * <p>
     * See {@link ScheduleSnapshot}
     */
    SCHEDULE_SNAPSHOT(0x0702, 4, ScheduleSnapshot.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * SCHEDULE_SNAPSHOT_RESPONSE: Schedule Snapshot Response
     * <p>
     * See {@link ScheduleSnapshotResponse}
     */
    SCHEDULE_SNAPSHOT_RESPONSE(0x0702, 4, ScheduleSnapshotResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * SET_SUPPLY_STATUS: Set Supply Status
     * <p>
     * See {@link SetSupplyStatus}
     */
    SET_SUPPLY_STATUS(0x0702, 13, SetSupplyStatus.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * SET_UNCONTROLLED_FLOW_THRESHOLD: Set Uncontrolled Flow Threshold
     * <p>
     * See {@link SetUncontrolledFlowThreshold}
     */
    SET_UNCONTROLLED_FLOW_THRESHOLD(0x0702, 14, SetUncontrolledFlowThreshold.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * START_SAMPLING: Start Sampling
     * <p>
     * See {@link StartSampling}
     */
    START_SAMPLING(0x0702, 7, StartSampling.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * START_SAMPLING_RESPONSE: Start Sampling Response
     * <p>
     * See {@link StartSamplingResponse}
     */
    START_SAMPLING_RESPONSE(0x0702, 13, StartSamplingResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * SUPPLY_STATUS_RESPONSE: Supply Status Response
     * <p>
     * See {@link SupplyStatusResponse}
     */
    SUPPLY_STATUS_RESPONSE(0x0702, 12, SupplyStatusResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * TAKE_SNAPSHOT: Take Snapshot
     * <p>
     * See {@link TakeSnapshot}
     */
    TAKE_SNAPSHOT(0x0702, 5, TakeSnapshot.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * TAKE_SNAPSHOT_RESPONSE: Take Snapshot Response
     * <p>
     * See {@link TakeSnapshotResponse}
     */
    TAKE_SNAPSHOT_RESPONSE(0x0702, 5, TakeSnapshotResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * OFF_COMMAND: Off Command
     * <p>
     * See {@link OffCommand}
     */
    OFF_COMMAND(0x0006, 0, OffCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * OFF_WITH_EFFECT_COMMAND: Off With Effect Command
     * <p>
     * See {@link OffWithEffectCommand}
     */
    OFF_WITH_EFFECT_COMMAND(0x0006, 64, OffWithEffectCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ON_COMMAND: On Command
     * <p>
     * See {@link OnCommand}
     */
    ON_COMMAND(0x0006, 1, OnCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ON_WITH_RECALL_GLOBAL_SCENE_COMMAND: On With Recall Global Scene Command
     * <p>
     * See {@link OnWithRecallGlobalSceneCommand}
     */
    ON_WITH_RECALL_GLOBAL_SCENE_COMMAND(0x0006, 65, OnWithRecallGlobalSceneCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ON_WITH_TIMED_OFF_COMMAND: On With Timed Off Command
     * <p>
     * See {@link OnWithTimedOffCommand}
     */
    ON_WITH_TIMED_OFF_COMMAND(0x0006, 66, OnWithTimedOffCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * TOGGLE_COMMAND: Toggle Command
     * <p>
     * See {@link ToggleCommand}
     */
    TOGGLE_COMMAND(0x0006, 2, ToggleCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * IMAGE_BLOCK_COMMAND: Image Block Command
     * <p>
     * See {@link ImageBlockCommand}
     */
    IMAGE_BLOCK_COMMAND(0x0019, 3, ImageBlockCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * IMAGE_BLOCK_RESPONSE: Image Block Response
     * <p>
     * See {@link ImageBlockResponse}
     */
    IMAGE_BLOCK_RESPONSE(0x0019, 5, ImageBlockResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * IMAGE_NOTIFY_COMMAND: Image Notify Command
     * <p>
     * See {@link ImageNotifyCommand}
     */
    IMAGE_NOTIFY_COMMAND(0x0019, 0, ImageNotifyCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * IMAGE_PAGE_COMMAND: Image Page Command
     * <p>
     * See {@link ImagePageCommand}
     */
    IMAGE_PAGE_COMMAND(0x0019, 4, ImagePageCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * QUERY_NEXT_IMAGE_COMMAND: Query Next Image Command
     * <p>
     * See {@link QueryNextImageCommand}
     */
    QUERY_NEXT_IMAGE_COMMAND(0x0019, 1, QueryNextImageCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * QUERY_NEXT_IMAGE_RESPONSE: Query Next Image Response
     * <p>
     * See {@link QueryNextImageResponse}
     */
    QUERY_NEXT_IMAGE_RESPONSE(0x0019, 2, QueryNextImageResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * QUERY_SPECIFIC_FILE_COMMAND: Query Specific File Command
     * <p>
     * See {@link QuerySpecificFileCommand}
     */
    QUERY_SPECIFIC_FILE_COMMAND(0x0019, 8, QuerySpecificFileCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * QUERY_SPECIFIC_FILE_RESPONSE: Query Specific File Response
     * <p>
     * See {@link QuerySpecificFileResponse}
     */
    QUERY_SPECIFIC_FILE_RESPONSE(0x0019, 9, QuerySpecificFileResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * UPGRADE_END_COMMAND: Upgrade End Command
     * <p>
     * See {@link UpgradeEndCommand}
     */
    UPGRADE_END_COMMAND(0x0019, 6, UpgradeEndCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * UPGRADE_END_RESPONSE: Upgrade End Response
     * <p>
     * See {@link UpgradeEndResponse}
     */
    UPGRADE_END_RESPONSE(0x0019, 7, UpgradeEndResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * CHECK_IN_COMMAND: Check In Command
     * <p>
     * See {@link CheckInCommand}
     */
    CHECK_IN_COMMAND(0x0020, 0, CheckInCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * CHECK_IN_RESPONSE: Check In Response
     * <p>
     * See {@link CheckInResponse}
     */
    CHECK_IN_RESPONSE(0x0020, 0, CheckInResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * FAST_POLL_STOP_COMMAND: Fast Poll Stop Command
     * <p>
     * See {@link FastPollStopCommand}
     */
    FAST_POLL_STOP_COMMAND(0x0020, 1, FastPollStopCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * SET_LONG_POLL_INTERVAL_COMMAND: Set Long Poll Interval Command
     * <p>
     * See {@link SetLongPollIntervalCommand}
     */
    SET_LONG_POLL_INTERVAL_COMMAND(0x0020, 2, SetLongPollIntervalCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * SET_SHORT_POLL_INTERVAL_COMMAND: Set Short Poll Interval Command
     * <p>
     * See {@link SetShortPollIntervalCommand}
     */
    SET_SHORT_POLL_INTERVAL_COMMAND(0x0020, 3, SetShortPollIntervalCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * CHANGE_DEBT: Change Debt
     * <p>
     * See {@link ChangeDebt}
     */
    CHANGE_DEBT(0x0705, 2, ChangeDebt.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * CHANGE_PAYMENT_MODE: Change Payment Mode
     * <p>
     * See {@link ChangePaymentMode}
     */
    CHANGE_PAYMENT_MODE(0x0705, 6, ChangePaymentMode.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * CHANGE_PAYMENT_MODE_RESPONSE: Change Payment Mode Response
     * <p>
     * See {@link ChangePaymentModeResponse}
     */
    CHANGE_PAYMENT_MODE_RESPONSE(0x0705, 2, ChangePaymentModeResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * CONSUMER_TOP_UP: Consumer Top Up
     * <p>
     * See {@link ConsumerTopUp}
     */
    CONSUMER_TOP_UP(0x0705, 4, ConsumerTopUp.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * CONSUMER_TOP_UP_RESPONSE: Consumer Top Up Response
     * <p>
     * See {@link ConsumerTopUpResponse}
     */
    CONSUMER_TOP_UP_RESPONSE(0x0705, 3, ConsumerTopUpResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * CREDIT_ADJUSTMENT: Credit Adjustment
     * <p>
     * See {@link CreditAdjustment}
     */
    CREDIT_ADJUSTMENT(0x0705, 5, CreditAdjustment.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * EMERGENCY_CREDIT_SETUP: Emergency Credit Setup
     * <p>
     * See {@link EmergencyCreditSetup}
     */
    EMERGENCY_CREDIT_SETUP(0x0705, 3, EmergencyCreditSetup.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_DEBT_REPAYMENT_LOG: Get Debt Repayment Log
     * <p>
     * See {@link GetDebtRepaymentLog}
     */
    GET_DEBT_REPAYMENT_LOG(0x0705, 10, GetDebtRepaymentLog.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_PREPAY_SNAPSHOT: Get Prepay Snapshot
     * <p>
     * See {@link GetPrepaySnapshot}
     */
    GET_PREPAY_SNAPSHOT(0x0705, 7, GetPrepaySnapshot.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_TOP_UP_LOG: Get Top Up Log
     * <p>
     * See {@link GetTopUpLog}
     */
    GET_TOP_UP_LOG(0x0705, 8, GetTopUpLog.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * PUBLISH_DEBT_LOG: Publish Debt Log
     * <p>
     * See {@link PublishDebtLog}
     */
    PUBLISH_DEBT_LOG(0x0705, 6, PublishDebtLog.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_PREPAY_SNAPSHOT: Publish Prepay Snapshot
     * <p>
     * See {@link PublishPrepaySnapshot}
     */
    PUBLISH_PREPAY_SNAPSHOT(0x0705, 1, PublishPrepaySnapshot.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_TOP_UP_LOG: Publish Top Up Log
     * <p>
     * See {@link PublishTopUpLog}
     */
    PUBLISH_TOP_UP_LOG(0x0705, 5, PublishTopUpLog.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * SELECT_AVAILABLE_EMERGENCY_CREDIT: Select Available Emergency Credit
     * <p>
     * See {@link SelectAvailableEmergencyCredit}
     */
    SELECT_AVAILABLE_EMERGENCY_CREDIT(0x0705, 0, SelectAvailableEmergencyCredit.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * SET_LOW_CREDIT_WARNING_LEVEL: Set Low Credit Warning Level
     * <p>
     * See {@link SetLowCreditWarningLevel}
     */
    SET_LOW_CREDIT_WARNING_LEVEL(0x0705, 9, SetLowCreditWarningLevel.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * SET_MAXIMUM_CREDIT_LIMIT: Set Maximum Credit Limit
     * <p>
     * See {@link SetMaximumCreditLimit}
     */
    SET_MAXIMUM_CREDIT_LIMIT(0x0705, 11, SetMaximumCreditLimit.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * SET_OVERALL_DEBT_CAP: Set Overall Debt Cap
     * <p>
     * See {@link SetOverallDebtCap}
     */
    SET_OVERALL_DEBT_CAP(0x0705, 12, SetOverallDebtCap.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * CANCEL_TARIFF_COMMAND: Cancel Tariff Command
     * <p>
     * See {@link CancelTariffCommand}
     */
    CANCEL_TARIFF_COMMAND(0x0700, 14, CancelTariffCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * CPP_EVENT_RESPONSE: Cpp Event Response
     * <p>
     * See {@link CppEventResponse}
     */
    CPP_EVENT_RESPONSE(0x0700, 13, CppEventResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_BILLING_PERIOD_COMMAND: Get Billing Period Command
     * <p>
     * See {@link GetBillingPeriodCommand}
     */
    GET_BILLING_PERIOD_COMMAND(0x0700, 11, GetBillingPeriodCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_BLOCK_PERIOD_COMMAND: Get Block Period Command
     * <p>
     * See {@link GetBlockPeriodCommand}
     */
    GET_BLOCK_PERIOD_COMMAND(0x0700, 3, GetBlockPeriodCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_BLOCK_THRESHOLDS_COMMAND: Get Block Thresholds Command
     * <p>
     * See {@link GetBlockThresholdsCommand}
     */
    GET_BLOCK_THRESHOLDS_COMMAND(0x0700, 8, GetBlockThresholdsCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_CALORIFIC_VALUE_COMMAND: Get Calorific Value Command
     * <p>
     * See {@link GetCalorificValueCommand}
     */
    GET_CALORIFIC_VALUE_COMMAND(0x0700, 5, GetCalorificValueCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_CO2_VALUE_COMMAND: Get CO2 Value Command
     * <p>
     * See {@link GetCo2ValueCommand}
     */
    GET_CO2_VALUE_COMMAND(0x0700, 9, GetCo2ValueCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_CONSOLIDATED_BILL_COMMAND: Get Consolidated Bill Command
     * <p>
     * See {@link GetConsolidatedBillCommand}
     */
    GET_CONSOLIDATED_BILL_COMMAND(0x0700, 12, GetConsolidatedBillCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_CONVERSION_FACTOR_COMMAND: Get Conversion Factor Command
     * <p>
     * See {@link GetConversionFactorCommand}
     */
    GET_CONVERSION_FACTOR_COMMAND(0x0700, 4, GetConversionFactorCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_CREDIT_PAYMENT_COMMAND: Get Credit Payment Command
     * <p>
     * See {@link GetCreditPaymentCommand}
     */
    GET_CREDIT_PAYMENT_COMMAND(0x0700, 14, GetCreditPaymentCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_CURRENCY_CONVERSION_COMMAND: Get Currency Conversion Command
     * <p>
     * See {@link GetCurrencyConversionCommand}
     */
    GET_CURRENCY_CONVERSION_COMMAND(0x0700, 15, GetCurrencyConversionCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_CURRENT_PRICE_COMMAND: Get Current Price Command
     * <p>
     * See {@link GetCurrentPriceCommand}
     */
    GET_CURRENT_PRICE_COMMAND(0x0700, 0, GetCurrentPriceCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_PRICE_MATRIX_COMMAND: Get Price Matrix Command
     * <p>
     * See {@link GetPriceMatrixCommand}
     */
    GET_PRICE_MATRIX_COMMAND(0x0700, 7, GetPriceMatrixCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_SCHEDULED_PRICES_COMMAND: Get Scheduled Prices Command
     * <p>
     * See {@link GetScheduledPricesCommand}
     */
    GET_SCHEDULED_PRICES_COMMAND(0x0700, 1, GetScheduledPricesCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_TARIFF_CANCELLATION_COMMAND: Get Tariff Cancellation Command
     * <p>
     * See {@link GetTariffCancellationCommand}
     */
    GET_TARIFF_CANCELLATION_COMMAND(0x0700, 16, GetTariffCancellationCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_TARIFF_INFORMATION_COMMAND: Get Tariff Information Command
     * <p>
     * See {@link GetTariffInformationCommand}
     */
    GET_TARIFF_INFORMATION_COMMAND(0x0700, 6, GetTariffInformationCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_TIER_LABELS_COMMAND: Get Tier Labels Command
     * <p>
     * See {@link GetTierLabelsCommand}
     */
    GET_TIER_LABELS_COMMAND(0x0700, 10, GetTierLabelsCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * PRICE_ACKNOWLEDGEMENT_COMMAND: Price Acknowledgement Command
     * <p>
     * See {@link PriceAcknowledgementCommand}
     */
    PRICE_ACKNOWLEDGEMENT_COMMAND(0x0700, 2, PriceAcknowledgementCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * PUBLISH_BILLING_PERIOD_COMMAND: Publish Billing Period Command
     * <p>
     * See {@link PublishBillingPeriodCommand}
     */
    PUBLISH_BILLING_PERIOD_COMMAND(0x0700, 9, PublishBillingPeriodCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_BLOCK_PERIOD_COMMAND: Publish Block Period Command
     * <p>
     * See {@link PublishBlockPeriodCommand}
     */
    PUBLISH_BLOCK_PERIOD_COMMAND(0x0700, 1, PublishBlockPeriodCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_BLOCK_THRESHOLDS_COMMAND: Publish Block Thresholds Command
     * <p>
     * See {@link PublishBlockThresholdsCommand}
     */
    PUBLISH_BLOCK_THRESHOLDS_COMMAND(0x0700, 6, PublishBlockThresholdsCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_CALORIFIC_VALUE_COMMAND: Publish Calorific Value Command
     * <p>
     * See {@link PublishCalorificValueCommand}
     */
    PUBLISH_CALORIFIC_VALUE_COMMAND(0x0700, 3, PublishCalorificValueCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_CO2_VALUE_COMMAND: Publish CO2 Value Command
     * <p>
     * See {@link PublishCo2ValueCommand}
     */
    PUBLISH_CO2_VALUE_COMMAND(0x0700, 7, PublishCo2ValueCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_CONSOLIDATED_BILL_COMMAND: Publish Consolidated Bill Command
     * <p>
     * See {@link PublishConsolidatedBillCommand}
     */
    PUBLISH_CONSOLIDATED_BILL_COMMAND(0x0700, 10, PublishConsolidatedBillCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_CONVERSION_FACTOR_COMMAND: Publish Conversion Factor Command
     * <p>
     * See {@link PublishConversionFactorCommand}
     */
    PUBLISH_CONVERSION_FACTOR_COMMAND(0x0700, 2, PublishConversionFactorCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_CPP_EVENT_COMMAND: Publish Cpp Event Command
     * <p>
     * See {@link PublishCppEventCommand}
     */
    PUBLISH_CPP_EVENT_COMMAND(0x0700, 11, PublishCppEventCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_CREDIT_PAYMENT_COMMAND: Publish Credit Payment Command
     * <p>
     * See {@link PublishCreditPaymentCommand}
     */
    PUBLISH_CREDIT_PAYMENT_COMMAND(0x0700, 12, PublishCreditPaymentCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_CURRENCY_CONVERSION_COMMAND: Publish Currency Conversion Command
     * <p>
     * See {@link PublishCurrencyConversionCommand}
     */
    PUBLISH_CURRENCY_CONVERSION_COMMAND(0x0700, 13, PublishCurrencyConversionCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_PRICE_COMMAND: Publish Price Command
     * <p>
     * See {@link PublishPriceCommand}
     */
    PUBLISH_PRICE_COMMAND(0x0700, 0, PublishPriceCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_PRICE_MATRIX_COMMAND: Publish Price Matrix Command
     * <p>
     * See {@link PublishPriceMatrixCommand}
     */
    PUBLISH_PRICE_MATRIX_COMMAND(0x0700, 5, PublishPriceMatrixCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_TARIFF_INFORMATION_COMMAND: Publish Tariff Information Command
     * <p>
     * See {@link PublishTariffInformationCommand}
     */
    PUBLISH_TARIFF_INFORMATION_COMMAND(0x0700, 4, PublishTariffInformationCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * PUBLISH_TIER_LABELS_COMMAND: Publish Tier Labels Command
     * <p>
     * See {@link PublishTierLabelsCommand}
     */
    PUBLISH_TIER_LABELS_COMMAND(0x0700, 8, PublishTierLabelsCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * ANCHOR_NODE_ANNOUNCE_COMMAND: Anchor Node Announce Command
     * <p>
     * See {@link AnchorNodeAnnounceCommand}
     */
    ANCHOR_NODE_ANNOUNCE_COMMAND(0x000B, 6, AnchorNodeAnnounceCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * COMPACT_LOCATION_DATA_NOTIFICATION_COMMAND: Compact Location Data Notification Command
     * <p>
     * See {@link CompactLocationDataNotificationCommand}
     */
    COMPACT_LOCATION_DATA_NOTIFICATION_COMMAND(0x000B, 3, CompactLocationDataNotificationCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * DEVICE_CONFIGURATION_RESPONSE: Device Configuration Response
     * <p>
     * See {@link DeviceConfigurationResponse}
     */
    DEVICE_CONFIGURATION_RESPONSE(0x000B, 0, DeviceConfigurationResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_DEVICE_CONFIGURATION_COMMAND: Get Device Configuration Command
     * <p>
     * See {@link GetDeviceConfigurationCommand}
     */
    GET_DEVICE_CONFIGURATION_COMMAND(0x000B, 2, GetDeviceConfigurationCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_LOCATION_DATA_COMMAND: Get Location Data Command
     * <p>
     * See {@link GetLocationDataCommand}
     */
    GET_LOCATION_DATA_COMMAND(0x000B, 3, GetLocationDataCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * LOCATION_DATA_NOTIFICATION_COMMAND: Location Data Notification Command
     * <p>
     * See {@link LocationDataNotificationCommand}
     */
    LOCATION_DATA_NOTIFICATION_COMMAND(0x000B, 2, LocationDataNotificationCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * LOCATION_DATA_RESPONSE: Location Data Response
     * <p>
     * See {@link LocationDataResponse}
     */
    LOCATION_DATA_RESPONSE(0x000B, 1, LocationDataResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * REPORT_RSSI_MEASUREMENTS_COMMAND: Report RSSI Measurements Command
     * <p>
     * See {@link ReportRssiMeasurementsCommand}
     */
    REPORT_RSSI_MEASUREMENTS_COMMAND(0x000B, 6, ReportRssiMeasurementsCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * REQUEST_OWN_LOCATION_COMMAND: Request Own Location Command
     * <p>
     * See {@link RequestOwnLocationCommand}
     */
    REQUEST_OWN_LOCATION_COMMAND(0x000B, 7, RequestOwnLocationCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * RSSI_PING_COMMAND: RSSI Ping Command
     * <p>
     * See {@link RssiPingCommand}
     */
    RSSI_PING_COMMAND(0x000B, 4, RssiPingCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * RSSI_REQUEST_COMMAND: RSSI Request Command
     * <p>
     * See {@link RssiRequestCommand}
     */
    RSSI_REQUEST_COMMAND(0x000B, 5, RssiRequestCommand.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * RSSI_RESPONSE: RSSI Response
     * <p>
     * See {@link RssiResponse}
     */
    RSSI_RESPONSE(0x000B, 4, RssiResponse.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * SEND_PINGS_COMMAND: Send Pings Command
     * <p>
     * See {@link SendPingsCommand}
     */
    SEND_PINGS_COMMAND(0x000B, 5, SendPingsCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * SET_ABSOLUTE_LOCATION_COMMAND: Set Absolute Location Command
     * <p>
     * See {@link SetAbsoluteLocationCommand}
     */
    SET_ABSOLUTE_LOCATION_COMMAND(0x000B, 0, SetAbsoluteLocationCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * SET_DEVICE_CONFIGURATION_COMMAND: Set Device Configuration Command
     * <p>
     * See {@link SetDeviceConfigurationCommand}
     */
    SET_DEVICE_CONFIGURATION_COMMAND(0x000B, 1, SetDeviceConfigurationCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ADD_SCENE_COMMAND: Add Scene Command
     * <p>
     * See {@link AddSceneCommand}
     */
    ADD_SCENE_COMMAND(0x0005, 0, AddSceneCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ADD_SCENE_RESPONSE: Add Scene Response
     * <p>
     * See {@link AddSceneResponse}
     */
    ADD_SCENE_RESPONSE(0x0005, 0, AddSceneResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_SCENE_MEMBERSHIP_COMMAND: Get Scene Membership Command
     * <p>
     * See {@link GetSceneMembershipCommand}
     */
    GET_SCENE_MEMBERSHIP_COMMAND(0x0005, 6, GetSceneMembershipCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_SCENE_MEMBERSHIP_RESPONSE: Get Scene Membership Response
     * <p>
     * See {@link GetSceneMembershipResponse}
     */
    GET_SCENE_MEMBERSHIP_RESPONSE(0x0005, 5, GetSceneMembershipResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * RECALL_SCENE_COMMAND: Recall Scene Command
     * <p>
     * See {@link RecallSceneCommand}
     */
    RECALL_SCENE_COMMAND(0x0005, 5, RecallSceneCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * REMOVE_ALL_SCENES_COMMAND: Remove All Scenes Command
     * <p>
     * See {@link RemoveAllScenesCommand}
     */
    REMOVE_ALL_SCENES_COMMAND(0x0005, 3, RemoveAllScenesCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * REMOVE_ALL_SCENES_RESPONSE: Remove All Scenes Response
     * <p>
     * See {@link RemoveAllScenesResponse}
     */
    REMOVE_ALL_SCENES_RESPONSE(0x0005, 3, RemoveAllScenesResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * REMOVE_SCENE_COMMAND: Remove Scene Command
     * <p>
     * See {@link RemoveSceneCommand}
     */
    REMOVE_SCENE_COMMAND(0x0005, 2, RemoveSceneCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * REMOVE_SCENE_RESPONSE: Remove Scene Response
     * <p>
     * See {@link RemoveSceneResponse}
     */
    REMOVE_SCENE_RESPONSE(0x0005, 2, RemoveSceneResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * STORE_SCENE_COMMAND: Store Scene Command
     * <p>
     * See {@link StoreSceneCommand}
     */
    STORE_SCENE_COMMAND(0x0005, 4, StoreSceneCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * STORE_SCENE_RESPONSE: Store Scene Response
     * <p>
     * See {@link StoreSceneResponse}
     */
    STORE_SCENE_RESPONSE(0x0005, 4, StoreSceneResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * VIEW_SCENE_COMMAND: View Scene Command
     * <p>
     * See {@link ViewSceneCommand}
     */
    VIEW_SCENE_COMMAND(0x0005, 1, ViewSceneCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * VIEW_SCENE_RESPONSE: View Scene Response
     * <p>
     * See {@link ViewSceneResponse}
     */
    VIEW_SCENE_RESPONSE(0x0005, 1, ViewSceneResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * ACK_TRANSFER_DATA_CLIENT_TO_SERVER: Ack Transfer Data Client To Server
     * <p>
     * See {@link AckTransferDataClientToServer}
     */
    ACK_TRANSFER_DATA_CLIENT_TO_SERVER(0x0704, 4, AckTransferDataClientToServer.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * ACK_TRANSFER_DATA_SERVER_TO_CLIENT: Ack Transfer Data Server To Client
     * <p>
     * See {@link AckTransferDataServerToClient}
     */
    ACK_TRANSFER_DATA_SERVER_TO_CLIENT(0x0704, 3, AckTransferDataServerToClient.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * CLOSE_TUNNEL: Close Tunnel
     * <p>
     * See {@link CloseTunnel}
     */
    CLOSE_TUNNEL(0x0704, 1, CloseTunnel.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_SUPPORTED_TUNNEL_PROTOCOLS: Get Supported Tunnel Protocols
     * <p>
     * See {@link GetSupportedTunnelProtocols}
     */
    GET_SUPPORTED_TUNNEL_PROTOCOLS(0x0704, 6, GetSupportedTunnelProtocols.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * READY_DATA_CLIENT_TO_SERVER: Ready Data Client To Server
     * <p>
     * See {@link ReadyDataClientToServer}
     */
    READY_DATA_CLIENT_TO_SERVER(0x0704, 5, ReadyDataClientToServer.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * READY_DATA_SERVER_TO_CLIENT: Ready Data Server To Client
     * <p>
     * See {@link ReadyDataServerToClient}
     */
    READY_DATA_SERVER_TO_CLIENT(0x0704, 4, ReadyDataServerToClient.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * REQUEST_TUNNEL: Request Tunnel
     * <p>
     * See {@link RequestTunnel}
     */
    REQUEST_TUNNEL(0x0704, 0, RequestTunnel.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * REQUEST_TUNNEL_RESPONSE: Request Tunnel Response
     * <p>
     * See {@link RequestTunnelResponse}
     */
    REQUEST_TUNNEL_RESPONSE(0x0704, 0, RequestTunnelResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * SUPPORTED_TUNNEL_PROTOCOLS_RESPONSE: Supported Tunnel Protocols Response
     * <p>
     * See {@link SupportedTunnelProtocolsResponse}
     */
    SUPPORTED_TUNNEL_PROTOCOLS_RESPONSE(0x0704, 5, SupportedTunnelProtocolsResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * TRANSFER_DATA_CLIENT_TO_SERVER: Transfer Data Client To Server
     * <p>
     * See {@link TransferDataClientToServer}
     */
    TRANSFER_DATA_CLIENT_TO_SERVER(0x0704, 2, TransferDataClientToServer.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * TRANSFER_DATA_ERROR_CLIENT_TO_SERVER: Transfer Data Error Client To Server
     * <p>
     * See {@link TransferDataErrorClientToServer}
     */
    TRANSFER_DATA_ERROR_CLIENT_TO_SERVER(0x0704, 3, TransferDataErrorClientToServer.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * TRANSFER_DATA_ERROR_SERVER_TO_CLIENT: Transfer Data Error Server To Client
     * <p>
     * See {@link TransferDataErrorServerToClient}
     */
    TRANSFER_DATA_ERROR_SERVER_TO_CLIENT(0x0704, 2, TransferDataErrorServerToClient.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * TRANSFER_DATA_SERVER_TO_CLIENT: Transfer Data Server To Client
     * <p>
     * See {@link TransferDataServerToClient}
     */
    TRANSFER_DATA_SERVER_TO_CLIENT(0x0704, 1, TransferDataServerToClient.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * TUNNEL_CLOSURE_NOTIFICATION: Tunnel Closure Notification
     * <p>
     * See {@link TunnelClosureNotification}
     */
    TUNNEL_CLOSURE_NOTIFICATION(0x0704, 6, TunnelClosureNotification.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * CLEAR_WEEKLY_SCHEDULE: Clear Weekly Schedule
     * <p>
     * See {@link ClearWeeklySchedule}
     */
    CLEAR_WEEKLY_SCHEDULE(0x0201, 3, ClearWeeklySchedule.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_RELAY_STATUS_LOG: Get Relay Status Log
     * <p>
     * See {@link GetRelayStatusLog}
     */
    GET_RELAY_STATUS_LOG(0x0201, 4, GetRelayStatusLog.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_RELAY_STATUS_LOG_RESPONSE: Get Relay Status Log Response
     * <p>
     * See {@link GetRelayStatusLogResponse}
     */
    GET_RELAY_STATUS_LOG_RESPONSE(0x0201, 1, GetRelayStatusLogResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * GET_WEEKLY_SCHEDULE: Get Weekly Schedule
     * <p>
     * See {@link GetWeeklySchedule}
     */
    GET_WEEKLY_SCHEDULE(0x0201, 2, GetWeeklySchedule.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * GET_WEEKLY_SCHEDULE_RESPONSE: Get Weekly Schedule Response
     * <p>
     * See {@link GetWeeklyScheduleResponse}
     */
    GET_WEEKLY_SCHEDULE_RESPONSE(0x0201, 0, GetWeeklyScheduleResponse.class, ZclCommandDirection.SERVER_TO_CLIENT),
    /**
     * SET_WEEKLY_SCHEDULE: Set Weekly Schedule
     * <p>
     * See {@link SetWeeklySchedule}
     */
    SET_WEEKLY_SCHEDULE(0x0201, 1, SetWeeklySchedule.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * SETPOINT_RAISE_LOWER_COMMAND: Setpoint Raise/Lower Command
     * <p>
     * See {@link SetpointRaiseLowerCommand}
     */
    SETPOINT_RAISE_LOWER_COMMAND(0x0201, 0, SetpointRaiseLowerCommand.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * WINDOW_COVERING_DOWN_CLOSE: Window Covering Down Close
     * <p>
     * See {@link WindowCoveringDownClose}
     */
    WINDOW_COVERING_DOWN_CLOSE(0x0102, 1, WindowCoveringDownClose.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * WINDOW_COVERING_GO_TO_LIFT_PERCENTAGE: Window Covering Go To Lift Percentage
     * <p>
     * See {@link WindowCoveringGoToLiftPercentage}
     */
    WINDOW_COVERING_GO_TO_LIFT_PERCENTAGE(0x0102, 5, WindowCoveringGoToLiftPercentage.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * WINDOW_COVERING_GO_TO_LIFT_VALUE: Window Covering Go To Lift Value
     * <p>
     * See {@link WindowCoveringGoToLiftValue}
     */
    WINDOW_COVERING_GO_TO_LIFT_VALUE(0x0102, 4, WindowCoveringGoToLiftValue.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * WINDOW_COVERING_GO_TO_TILT_PERCENTAGE: Window Covering Go To Tilt Percentage
     * <p>
     * See {@link WindowCoveringGoToTiltPercentage}
     */
    WINDOW_COVERING_GO_TO_TILT_PERCENTAGE(0x0102, 8, WindowCoveringGoToTiltPercentage.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * WINDOW_COVERING_GO_TO_TILT_VALUE: Window Covering Go To Tilt Value
     * <p>
     * See {@link WindowCoveringGoToTiltValue}
     */
    WINDOW_COVERING_GO_TO_TILT_VALUE(0x0102, 7, WindowCoveringGoToTiltValue.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * WINDOW_COVERING_STOP: Window Covering Stop
     * <p>
     * See {@link WindowCoveringStop}
     */
    WINDOW_COVERING_STOP(0x0102, 2, WindowCoveringStop.class, ZclCommandDirection.CLIENT_TO_SERVER),
    /**
     * WINDOW_COVERING_UP_OPEN: Window Covering Up Open
     * <p>
     * See {@link WindowCoveringUpOpen}
     */
    WINDOW_COVERING_UP_OPEN(0x0102, 0, WindowCoveringUpOpen.class, ZclCommandDirection.CLIENT_TO_SERVER);

    private final int commandId;
    private final int clusterType;
    private final Class<? extends ZclCommand> commandClass;
    private final ZclCommandDirection direction;

    ZclCommandType(final int clusterType, final int commandId, final Class<? extends ZclCommand> commandClass, final ZclCommandDirection direction) {
        this.clusterType = clusterType;
        this.commandId = commandId;
        this.commandClass = commandClass;
        this.direction = direction;
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

    public ZclCommandDirection getDirection() {
        return direction;
    }

    public Class<? extends ZclCommand> getCommandClass() {
        return commandClass;
    }

        public static ZclCommandType getCommandType(final int clusterType, final int commandId,

            ZclCommandDirection direction) {

        for (final ZclCommandType value : values()) {

            if (value.direction == direction && value.clusterType == clusterType && value.commandId == commandId) {

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
