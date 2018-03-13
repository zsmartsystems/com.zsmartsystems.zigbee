/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.ember.internal.ash.AshFrameData;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspAddEndpointResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspAddOrUpdateKeyTableEntryResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspAddTransientLinkKeyResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspBecomeTrustCenterResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspBindingIsActiveResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspCallbackResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspChildJoinHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspClearBindingTableResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspClearKeyTableResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspClearTransientLinkKeysResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspCounterRolloverHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspDGpSendResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspDGpSentHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspDeleteBindingResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspEnergyScanRequestResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspEnergyScanResultHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspErraseKeyTableEntryResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspFindKeyTableEntryResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspFormNetworkResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetAddressTableRemoteEui64Response;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetBindingRemoteNodeIdResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetBindingResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetChildDataResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetConfigurationValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetCurrentSecurityStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetEui64Response;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetExtendedTimeoutResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetKeyResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetKeyTableEntryResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetNeighborResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetNetworkParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetNodeIdResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetParentChildParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetPolicyResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetRouteTableEntryResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetStandaloneBootloaderVersionPlatMicroPhyResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetXncpInfoResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGpProxyTableLookupResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGpProxyTableProcessGpPairingResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGpepIncomingMessageHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspIdConflictHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspIncomingMessageHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspIncomingRouteErrorHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspIncomingRouteRecordHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspInvalidCommandResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspJoinNetworkResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspLaunchStandaloneBootloaderResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspLeaveNetworkResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspLookupEui64ByNodeIdResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspLookupNodeIdByEui64Response;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspMacFilterMatchMessageHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspMessageSentHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspMfglibEndResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspMfglibGetChannelResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspMfglibGetPowerResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspMfglibRxHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspMfglibSendPacketResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspMfglibSetChannelResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspMfglibSetPowerResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspMfglibStartResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspMfglibStartStreamResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspMfglibStartToneResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspMfglibStopStreamResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspNeighborCountResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspNetworkFoundHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspNetworkInitResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspNetworkStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspNoCallbacksResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspPermitJoiningResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspPollHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspReadAndClearCountersResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspReadCountersResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspRemoteDeleteBindingHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspRemoteSetBindingHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspRemoveDeviceResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspResetToFactoryDefaultsResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspScanCompleteHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSendBroadcastResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSendManyToOneRouteRequestResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSendMulticastResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSendReplyResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSendUnicastResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetBindingRemoteNodeIdResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetBindingResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetConcentratorResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetConfigurationValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetExtendedTimeoutResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetInitialSecurityStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetKeyTableEntryResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetPolicyResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetRadioChannelResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetSourceRouteResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspStackStatusHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspStartScanResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspStopScanResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSwitchNetworkKeyHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspTrustCenterJoinHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspVersionResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspZigbeeKeyEstablishmentHandler;

/**
 * The EmberZNet Serial Protocol (EZSP) is the protocol used by a host application processor to interact with the
 * EmberZNet PRO stack running on a Network CoProcessor (NCP).
 * <p>
 * Reference: UG100: EZSP Reference Guide
 * <p>
 * An EZSP V4 Frame is made up as follows -:
 * <ul>
 * <li>Sequence : 1 byte sequence number
 * <li>Frame Control: 1 byte
 * <li>Frame ID : 1 byte
 * <li>Parameters : variable length
 * </ul>
 * <p>
 * An EZSP V5+ Frame is made up as follows -:
 * <ul>
 * <li>Sequence : 1 byte sequence number
 * <li>Frame Control: 1 byte
 * <li>Legacy Frame ID : 1 byte
 * <li>Extended Frame Control : 1 byte
 * <li>Frame ID : 1 byte
 * <li>Parameters : variable length
 * </ul>
 *
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public abstract class EzspFrame {
    /**
     * The logger
     */
    private final static Logger logger = LoggerFactory.getLogger(EzspFrame.class);

    /**
     * The minimum supported version of EZSP
     */
    private static final int EZSP_MIN_VERSION = 4;

    /**
     * The maximum supported version of EZSP
     */
    private static final int EZSP_MAX_VERSION = 6;

    /**
     * The current version of EZSP being used
     */
    protected static int ezspVersion = EZSP_MIN_VERSION;

    /**
     * The maximum supported version of EZSP
     */
    private static final int MAX_EZSP_VERSION = 6;

    /**
     * Legacy frame ID for EZSP 5+
     */
    protected final int EZSP_LEGACY_FRAME_ID = 0xFF;

    /**
     * EZSP Frame Control Request flag
     */
    protected final int EZSP_FC_REQUEST = 0x00;

    /**
     * EZSP Frame Control Response flag
     */
    protected final int EZSP_FC_RESPONSE = 0x80;

    protected static final int FRAME_ID_ADD_ENDPOINT = 0x02;
    protected static final int FRAME_ID_ADD_OR_UPDATE_KEY_TABLE_ENTRY = 0x66;
    protected static final int FRAME_ID_ADD_TRANSIENT_LINK_KEY = 0xAF;
    protected static final int FRAME_ID_BECOME_TRUST_CENTER = 0x77;
    protected static final int FRAME_ID_BINDING_IS_ACTIVE = 0x2E;
    protected static final int FRAME_ID_CALLBACK = 0x06;
    protected static final int FRAME_ID_CHILD_JOIN_HANDLER = 0x23;
    protected static final int FRAME_ID_CLEAR_BINDING_TABLE = 0x2A;
    protected static final int FRAME_ID_CLEAR_KEY_TABLE = 0xB1;
    protected static final int FRAME_ID_CLEAR_TRANSIENT_LINK_KEYS = 0x6B;
    protected static final int FRAME_ID_COUNTER_ROLLOVER_HANDLER = 0xF2;
    protected static final int FRAME_ID_D_GP_SEND = 0xC6;
    protected static final int FRAME_ID_D_GP_SENT_HANDLER = 0xC7;
    protected static final int FRAME_ID_DELETE_BINDING = 0x2D;
    protected static final int FRAME_ID_ENERGY_SCAN_REQUEST = 0x9C;
    protected static final int FRAME_ID_ENERGY_SCAN_RESULT_HANDLER = 0x48;
    protected static final int FRAME_ID_ERRASE_KEY_TABLE_ENTRY = 0x76;
    protected static final int FRAME_ID_FIND_KEY_TABLE_ENTRY = 0x75;
    protected static final int FRAME_ID_FORM_NETWORK = 0x1E;
    protected static final int FRAME_ID_GET_ADDRESS_TABLE_REMOTE_EUI64 = 0x5E;
    protected static final int FRAME_ID_GET_BINDING = 0x2C;
    protected static final int FRAME_ID_GET_BINDING_REMOTE_NODE_ID = 0x2F;
    protected static final int FRAME_ID_GET_CHILD_DATA = 0x4A;
    protected static final int FRAME_ID_GET_CONFIGURATION_VALUE = 0x52;
    protected static final int FRAME_ID_GET_CURRENT_SECURITY_STATE = 0x69;
    protected static final int FRAME_ID_GET_EUI64 = 0x26;
    protected static final int FRAME_ID_GET_EXTENDED_TIMEOUT = 0x7F;
    protected static final int FRAME_ID_GET_KEY = 0x6A;
    protected static final int FRAME_ID_GET_KEY_TABLE_ENTRY = 0x71;
    protected static final int FRAME_ID_GET_NEIGHBOR = 0x79;
    protected static final int FRAME_ID_GET_NETWORK_PARAMETERS = 0x28;
    protected static final int FRAME_ID_GET_NODE_ID = 0x27;
    protected static final int FRAME_ID_GET_PARENT_CHILD_PARAMETERS = 0x29;
    protected static final int FRAME_ID_GET_POLICY = 0x56;
    protected static final int FRAME_ID_GET_ROUTE_TABLE_ENTRY = 0x7B;
    protected static final int FRAME_ID_GET_STANDALONE_BOOTLOADER_VERSION_PLAT_MICRO_PHY = 0x91;
    protected static final int FRAME_ID_GET_VALUE = 0xAA;
    protected static final int FRAME_ID_GET_XNCP_INFO = 0x13;
    protected static final int FRAME_ID_GP_PROXY_TABLE_LOOKUP = 0xC0;
    protected static final int FRAME_ID_GP_PROXY_TABLE_PROCESS_GP_PAIRING = 0xC9;
    protected static final int FRAME_ID_GPEP_INCOMING_MESSAGE_HANDLER = 0xC5;
    protected static final int FRAME_ID_ID_CONFLICT_HANDLER = 0x7C;
    protected static final int FRAME_ID_INCOMING_MESSAGE_HANDLER = 0x45;
    protected static final int FRAME_ID_INCOMING_ROUTE_ERROR_HANDLER = 0x80;
    protected static final int FRAME_ID_INCOMING_ROUTE_RECORD_HANDLER = 0x59;
    protected static final int FRAME_ID_INVALID_COMMAND = 0x58;
    protected static final int FRAME_ID_JOIN_NETWORK = 0x1F;
    protected static final int FRAME_ID_LAUNCH_STANDALONE_BOOTLOADER = 0x8F;
    protected static final int FRAME_ID_LEAVE_NETWORK = 0x20;
    protected static final int FRAME_ID_LOOKUP_EUI64_BY_NODE_ID = 0x61;
    protected static final int FRAME_ID_LOOKUP_NODE_ID_BY_EUI64 = 0x60;
    protected static final int FRAME_ID_MAC_FILTER_MATCH_MESSAGE_HANDLER = 0x46;
    protected static final int FRAME_ID_MESSAGE_SENT_HANDLER = 0x3F;
    protected static final int FRAME_ID_MFGLIB_END = 0x84;
    protected static final int FRAME_ID_MFGLIB_GET_CHANNEL = 0x8B;
    protected static final int FRAME_ID_MFGLIB_GET_POWER = 0x8D;
    protected static final int FRAME_ID_MFGLIB_RX_HANDLER = 0x8E;
    protected static final int FRAME_ID_MFGLIB_SEND_PACKET = 0x89;
    protected static final int FRAME_ID_MFGLIB_SET_CHANNEL = 0x8A;
    protected static final int FRAME_ID_MFGLIB_SET_POWER = 0x8C;
    protected static final int FRAME_ID_MFGLIB_START = 0x83;
    protected static final int FRAME_ID_MFGLIB_START_STREAM = 0x87;
    protected static final int FRAME_ID_MFGLIB_START_TONE = 0x86;
    protected static final int FRAME_ID_MFGLIB_STOP_STREAM = 0x88;
    protected static final int FRAME_ID_NEIGHBOR_COUNT = 0x7A;
    protected static final int FRAME_ID_NETWORK_FOUND_HANDLER = 0x1B;
    protected static final int FRAME_ID_NETWORK_INIT = 0x17;
    protected static final int FRAME_ID_NETWORK_STATE = 0x18;
    protected static final int FRAME_ID_NO_CALLBACKS = 0x07;
    protected static final int FRAME_ID_PERMIT_JOINING = 0x22;
    protected static final int FRAME_ID_POLL_HANDLER = 0x44;
    protected static final int FRAME_ID_READ_AND_CLEAR_COUNTERS = 0x65;
    protected static final int FRAME_ID_READ_COUNTERS = 0xF1;
    protected static final int FRAME_ID_REMOTE_DELETE_BINDING_HANDLER = 0x32;
    protected static final int FRAME_ID_REMOTE_SET_BINDING_HANDLER = 0x31;
    protected static final int FRAME_ID_REMOVE_DEVICE = 0xA8;
    protected static final int FRAME_ID_RESET_TO_FACTORY_DEFAULTS = 0xCC;
    protected static final int FRAME_ID_SCAN_COMPLETE_HANDLER = 0x1C;
    protected static final int FRAME_ID_SEND_BROADCAST = 0x36;
    protected static final int FRAME_ID_SEND_MANY_TO_ONE_ROUTE_REQUEST = 0x41;
    protected static final int FRAME_ID_SEND_MULTICAST = 0x38;
    protected static final int FRAME_ID_SEND_REPLY = 0x39;
    protected static final int FRAME_ID_SEND_UNICAST = 0x34;
    protected static final int FRAME_ID_SET_BINDING = 0x2B;
    protected static final int FRAME_ID_SET_BINDING_REMOTE_NODE_ID = 0x30;
    protected static final int FRAME_ID_SET_CONCENTRATOR = 0x10;
    protected static final int FRAME_ID_SET_CONFIGURATION_VALUE = 0x53;
    protected static final int FRAME_ID_SET_EXTENDED_TIMEOUT = 0x7E;
    protected static final int FRAME_ID_SET_INITIAL_SECURITY_STATE = 0x68;
    protected static final int FRAME_ID_SET_KEY_TABLE_ENTRY = 0x72;
    protected static final int FRAME_ID_SET_POLICY = 0x55;
    protected static final int FRAME_ID_SET_RADIO_CHANNEL = 0x9A;
    protected static final int FRAME_ID_SET_SOURCE_ROUTE = 0x5A;
    protected static final int FRAME_ID_SET_VALUE = 0xAB;
    protected static final int FRAME_ID_STACK_STATUS_HANDLER = 0x19;
    protected static final int FRAME_ID_START_SCAN = 0x1A;
    protected static final int FRAME_ID_STOP_SCAN = 0x1D;
    protected static final int FRAME_ID_SWITCH_NETWORK_KEY_HANDLER = 0x6E;
    protected static final int FRAME_ID_TRUST_CENTER_JOIN_HANDLER = 0x24;
    protected static final int FRAME_ID_VERSION = 0x00;
    protected static final int FRAME_ID_ZIGBEE_KEY_ESTABLISHMENT_HANDLER = 0x9B;

    protected int sequenceNumber;
    protected int frameControl;
    protected int frameId = 0;
    protected boolean isResponse = false;

    private static Map<Integer, Class<?>> ezspHandlerMap = new HashMap<Integer, Class<?>>();
    static {
        ezspHandlerMap.put(FRAME_ID_ADD_ENDPOINT, EzspAddEndpointResponse.class);
        ezspHandlerMap.put(FRAME_ID_ADD_OR_UPDATE_KEY_TABLE_ENTRY, EzspAddOrUpdateKeyTableEntryResponse.class);
        ezspHandlerMap.put(FRAME_ID_ADD_TRANSIENT_LINK_KEY, EzspAddTransientLinkKeyResponse.class);
        ezspHandlerMap.put(FRAME_ID_BECOME_TRUST_CENTER, EzspBecomeTrustCenterResponse.class);
        ezspHandlerMap.put(FRAME_ID_BINDING_IS_ACTIVE, EzspBindingIsActiveResponse.class);
        ezspHandlerMap.put(FRAME_ID_CALLBACK, EzspCallbackResponse.class);
        ezspHandlerMap.put(FRAME_ID_CHILD_JOIN_HANDLER, EzspChildJoinHandler.class);
        ezspHandlerMap.put(FRAME_ID_CLEAR_BINDING_TABLE, EzspClearBindingTableResponse.class);
        ezspHandlerMap.put(FRAME_ID_CLEAR_KEY_TABLE, EzspClearKeyTableResponse.class);
        ezspHandlerMap.put(FRAME_ID_CLEAR_TRANSIENT_LINK_KEYS, EzspClearTransientLinkKeysResponse.class);
        ezspHandlerMap.put(FRAME_ID_COUNTER_ROLLOVER_HANDLER, EzspCounterRolloverHandler.class);
        ezspHandlerMap.put(FRAME_ID_D_GP_SEND, EzspDGpSendResponse.class);
        ezspHandlerMap.put(FRAME_ID_D_GP_SENT_HANDLER, EzspDGpSentHandler.class);
        ezspHandlerMap.put(FRAME_ID_DELETE_BINDING, EzspDeleteBindingResponse.class);
        ezspHandlerMap.put(FRAME_ID_ENERGY_SCAN_REQUEST, EzspEnergyScanRequestResponse.class);
        ezspHandlerMap.put(FRAME_ID_ENERGY_SCAN_RESULT_HANDLER, EzspEnergyScanResultHandler.class);
        ezspHandlerMap.put(FRAME_ID_ERRASE_KEY_TABLE_ENTRY, EzspErraseKeyTableEntryResponse.class);
        ezspHandlerMap.put(FRAME_ID_FIND_KEY_TABLE_ENTRY, EzspFindKeyTableEntryResponse.class);
        ezspHandlerMap.put(FRAME_ID_FORM_NETWORK, EzspFormNetworkResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_ADDRESS_TABLE_REMOTE_EUI64, EzspGetAddressTableRemoteEui64Response.class);
        ezspHandlerMap.put(FRAME_ID_GET_BINDING, EzspGetBindingResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_BINDING_REMOTE_NODE_ID, EzspGetBindingRemoteNodeIdResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_CHILD_DATA, EzspGetChildDataResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_CONFIGURATION_VALUE, EzspGetConfigurationValueResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_CURRENT_SECURITY_STATE, EzspGetCurrentSecurityStateResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_EUI64, EzspGetEui64Response.class);
        ezspHandlerMap.put(FRAME_ID_GET_EXTENDED_TIMEOUT, EzspGetExtendedTimeoutResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_KEY, EzspGetKeyResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_KEY_TABLE_ENTRY, EzspGetKeyTableEntryResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_NEIGHBOR, EzspGetNeighborResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_NETWORK_PARAMETERS, EzspGetNetworkParametersResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_NODE_ID, EzspGetNodeIdResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_PARENT_CHILD_PARAMETERS, EzspGetParentChildParametersResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_POLICY, EzspGetPolicyResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_ROUTE_TABLE_ENTRY, EzspGetRouteTableEntryResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_STANDALONE_BOOTLOADER_VERSION_PLAT_MICRO_PHY, EzspGetStandaloneBootloaderVersionPlatMicroPhyResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_VALUE, EzspGetValueResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_XNCP_INFO, EzspGetXncpInfoResponse.class);
        ezspHandlerMap.put(FRAME_ID_GP_PROXY_TABLE_LOOKUP, EzspGpProxyTableLookupResponse.class);
        ezspHandlerMap.put(FRAME_ID_GP_PROXY_TABLE_PROCESS_GP_PAIRING, EzspGpProxyTableProcessGpPairingResponse.class);
        ezspHandlerMap.put(FRAME_ID_GPEP_INCOMING_MESSAGE_HANDLER, EzspGpepIncomingMessageHandler.class);
        ezspHandlerMap.put(FRAME_ID_ID_CONFLICT_HANDLER, EzspIdConflictHandler.class);
        ezspHandlerMap.put(FRAME_ID_INCOMING_MESSAGE_HANDLER, EzspIncomingMessageHandler.class);
        ezspHandlerMap.put(FRAME_ID_INCOMING_ROUTE_ERROR_HANDLER, EzspIncomingRouteErrorHandler.class);
        ezspHandlerMap.put(FRAME_ID_INCOMING_ROUTE_RECORD_HANDLER, EzspIncomingRouteRecordHandler.class);
        ezspHandlerMap.put(FRAME_ID_INVALID_COMMAND, EzspInvalidCommandResponse.class);
        ezspHandlerMap.put(FRAME_ID_JOIN_NETWORK, EzspJoinNetworkResponse.class);
        ezspHandlerMap.put(FRAME_ID_LAUNCH_STANDALONE_BOOTLOADER, EzspLaunchStandaloneBootloaderResponse.class);
        ezspHandlerMap.put(FRAME_ID_LEAVE_NETWORK, EzspLeaveNetworkResponse.class);
        ezspHandlerMap.put(FRAME_ID_LOOKUP_EUI64_BY_NODE_ID, EzspLookupEui64ByNodeIdResponse.class);
        ezspHandlerMap.put(FRAME_ID_LOOKUP_NODE_ID_BY_EUI64, EzspLookupNodeIdByEui64Response.class);
        ezspHandlerMap.put(FRAME_ID_MAC_FILTER_MATCH_MESSAGE_HANDLER, EzspMacFilterMatchMessageHandler.class);
        ezspHandlerMap.put(FRAME_ID_MESSAGE_SENT_HANDLER, EzspMessageSentHandler.class);
        ezspHandlerMap.put(FRAME_ID_MFGLIB_END, EzspMfglibEndResponse.class);
        ezspHandlerMap.put(FRAME_ID_MFGLIB_GET_CHANNEL, EzspMfglibGetChannelResponse.class);
        ezspHandlerMap.put(FRAME_ID_MFGLIB_GET_POWER, EzspMfglibGetPowerResponse.class);
        ezspHandlerMap.put(FRAME_ID_MFGLIB_RX_HANDLER, EzspMfglibRxHandler.class);
        ezspHandlerMap.put(FRAME_ID_MFGLIB_SEND_PACKET, EzspMfglibSendPacketResponse.class);
        ezspHandlerMap.put(FRAME_ID_MFGLIB_SET_CHANNEL, EzspMfglibSetChannelResponse.class);
        ezspHandlerMap.put(FRAME_ID_MFGLIB_SET_POWER, EzspMfglibSetPowerResponse.class);
        ezspHandlerMap.put(FRAME_ID_MFGLIB_START, EzspMfglibStartResponse.class);
        ezspHandlerMap.put(FRAME_ID_MFGLIB_START_STREAM, EzspMfglibStartStreamResponse.class);
        ezspHandlerMap.put(FRAME_ID_MFGLIB_START_TONE, EzspMfglibStartToneResponse.class);
        ezspHandlerMap.put(FRAME_ID_MFGLIB_STOP_STREAM, EzspMfglibStopStreamResponse.class);
        ezspHandlerMap.put(FRAME_ID_NEIGHBOR_COUNT, EzspNeighborCountResponse.class);
        ezspHandlerMap.put(FRAME_ID_NETWORK_FOUND_HANDLER, EzspNetworkFoundHandler.class);
        ezspHandlerMap.put(FRAME_ID_NETWORK_INIT, EzspNetworkInitResponse.class);
        ezspHandlerMap.put(FRAME_ID_NETWORK_STATE, EzspNetworkStateResponse.class);
        ezspHandlerMap.put(FRAME_ID_NO_CALLBACKS, EzspNoCallbacksResponse.class);
        ezspHandlerMap.put(FRAME_ID_PERMIT_JOINING, EzspPermitJoiningResponse.class);
        ezspHandlerMap.put(FRAME_ID_POLL_HANDLER, EzspPollHandler.class);
        ezspHandlerMap.put(FRAME_ID_READ_AND_CLEAR_COUNTERS, EzspReadAndClearCountersResponse.class);
        ezspHandlerMap.put(FRAME_ID_READ_COUNTERS, EzspReadCountersResponse.class);
        ezspHandlerMap.put(FRAME_ID_REMOTE_DELETE_BINDING_HANDLER, EzspRemoteDeleteBindingHandler.class);
        ezspHandlerMap.put(FRAME_ID_REMOTE_SET_BINDING_HANDLER, EzspRemoteSetBindingHandler.class);
        ezspHandlerMap.put(FRAME_ID_REMOVE_DEVICE, EzspRemoveDeviceResponse.class);
        ezspHandlerMap.put(FRAME_ID_RESET_TO_FACTORY_DEFAULTS, EzspResetToFactoryDefaultsResponse.class);
        ezspHandlerMap.put(FRAME_ID_SCAN_COMPLETE_HANDLER, EzspScanCompleteHandler.class);
        ezspHandlerMap.put(FRAME_ID_SEND_BROADCAST, EzspSendBroadcastResponse.class);
        ezspHandlerMap.put(FRAME_ID_SEND_MANY_TO_ONE_ROUTE_REQUEST, EzspSendManyToOneRouteRequestResponse.class);
        ezspHandlerMap.put(FRAME_ID_SEND_MULTICAST, EzspSendMulticastResponse.class);
        ezspHandlerMap.put(FRAME_ID_SEND_REPLY, EzspSendReplyResponse.class);
        ezspHandlerMap.put(FRAME_ID_SEND_UNICAST, EzspSendUnicastResponse.class);
        ezspHandlerMap.put(FRAME_ID_SET_BINDING, EzspSetBindingResponse.class);
        ezspHandlerMap.put(FRAME_ID_SET_BINDING_REMOTE_NODE_ID, EzspSetBindingRemoteNodeIdResponse.class);
        ezspHandlerMap.put(FRAME_ID_SET_CONCENTRATOR, EzspSetConcentratorResponse.class);
        ezspHandlerMap.put(FRAME_ID_SET_CONFIGURATION_VALUE, EzspSetConfigurationValueResponse.class);
        ezspHandlerMap.put(FRAME_ID_SET_EXTENDED_TIMEOUT, EzspSetExtendedTimeoutResponse.class);
        ezspHandlerMap.put(FRAME_ID_SET_INITIAL_SECURITY_STATE, EzspSetInitialSecurityStateResponse.class);
        ezspHandlerMap.put(FRAME_ID_SET_KEY_TABLE_ENTRY, EzspSetKeyTableEntryResponse.class);
        ezspHandlerMap.put(FRAME_ID_SET_POLICY, EzspSetPolicyResponse.class);
        ezspHandlerMap.put(FRAME_ID_SET_RADIO_CHANNEL, EzspSetRadioChannelResponse.class);
        ezspHandlerMap.put(FRAME_ID_SET_SOURCE_ROUTE, EzspSetSourceRouteResponse.class);
        ezspHandlerMap.put(FRAME_ID_SET_VALUE, EzspSetValueResponse.class);
        ezspHandlerMap.put(FRAME_ID_STACK_STATUS_HANDLER, EzspStackStatusHandler.class);
        ezspHandlerMap.put(FRAME_ID_START_SCAN, EzspStartScanResponse.class);
        ezspHandlerMap.put(FRAME_ID_STOP_SCAN, EzspStopScanResponse.class);
        ezspHandlerMap.put(FRAME_ID_SWITCH_NETWORK_KEY_HANDLER, EzspSwitchNetworkKeyHandler.class);
        ezspHandlerMap.put(FRAME_ID_TRUST_CENTER_JOIN_HANDLER, EzspTrustCenterJoinHandler.class);
        ezspHandlerMap.put(FRAME_ID_VERSION, EzspVersionResponse.class);
        ezspHandlerMap.put(FRAME_ID_ZIGBEE_KEY_ESTABLISHMENT_HANDLER, EzspZigbeeKeyEstablishmentHandler.class);
    }

    /**
     * Sets the 8 bit transaction sequence number
     *
     * @param sequenceNumber
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets the 8 bit transaction sequence number
     *
     * @return sequence number
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Checks if this frame is a response frame
     *
     * @return true if this is a response
     */
    public boolean isResponse() {
        return isResponse;
    }

    /**
     * Gets the Ember frame ID for this frame
     *
     * @return the Ember frame Id
     */
    public int getFrameId() {
        return frameId;
    }

    /**
     * Creates and {@link EzspFrameResponse} from the {@link AshFrameData}.
     *
     * @return the {@link EzspFrameResponse} or null if the response can't be created.
     */
    public static EzspFrameResponse createHandler(AshFrameData data) {
        Class<?> ezspClass;
        if (data.getDataBuffer()[2] != 0xFF) {
            ezspClass = ezspHandlerMap.get(data.getDataBuffer()[2]);
        } else {
            ezspClass = ezspHandlerMap.get(data.getDataBuffer()[4]);
        }

        if (ezspClass == null) {
            return null;
        }

        Constructor<?> ctor;
        try {
            ctor = ezspClass.getConstructor(int[].class);
            EzspFrameResponse ezspFrame = (EzspFrameResponse) ctor.newInstance(data.getDataBuffer());
            return ezspFrame;
        } catch (SecurityException | NoSuchMethodException | IllegalArgumentException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            logger.debug("Error creating instance of EzspFrame", e);
        }

        return null;
    }

    /**
     * Set the EZSP version to use
     *
     * @param ezspVersion the EZSP protocol version
     * @return true if the version is supported
     */
    public static boolean setEzspVersion(int ezspVersion) {
        if (ezspVersion <= EZSP_MAX_VERSION && ezspVersion >= EZSP_MIN_VERSION) {
            EzspFrame.ezspVersion = ezspVersion;
            return true;
        }

        return false;
    }

    /**
     * Gets the current version of EZSP that is in use. This will default to the minimum supported version on startup
     *
     * @return the current version of EZSP
     */
    public static int getEzspVersion() {
        return EzspFrame.ezspVersion;
    }
}
