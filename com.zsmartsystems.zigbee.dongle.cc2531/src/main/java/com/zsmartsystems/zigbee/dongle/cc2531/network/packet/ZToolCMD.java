/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
   Copyright 2008-2013 ITACA-TSB, http://www.tsb.upv.es/
   Instituto Tecnologico de Aplicaciones de Comunicacion
   Avanzadas - Grupo Tecnologias para la Salud y el
   Bienestar (TSB)


   See the NOTICE file distributed with this work for additional
   information regarding copyright ownership

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.zsmartsystems.zigbee.dongle.cc2531.network.packet;

/**
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class ZToolCMD {

    /**
     * AF Data confirm.
     */
    public static final int AF_DATA_CONFIRM = 0x4480;
    /**
     * This command is used by tester to build and send a data request message
     */
    public static final int AF_DATA_REQUEST = 0x2401;
    /**
     * This command is used by tester to build and send a data request message
     */
    public static final int AF_DATA_REQUEST_EXT = 0x2402;
    /**
     * Response for AF_DATA_REQUEST
     */
    public static final int AF_DATA_SRSP = 0x6401;
    /**
     * Response for AF_DATA_REQUEST
     */
    public static final int AF_DATA_SRSP_EXT = 0x6402;
    /**
     * Incoming AF data.
     */
    public static final int AF_INCOMING_MSG = 0x4481;
    /**
     * This command enables the tester to register an application's endpoint
     * description
     */
    public static final int AF_REGISTER = 0x2400;
    /**
     * Response for AF_REGISTER
     */
    public static final int AF_REGISTER_SRSP = 0x6400;
    /**
     * Use this message to send raw data to an application.
     */
    public static final int APP_MSG = 0x2900;
    /**
     * Status for APP_MSG
     */
    public static final int APP_MSG_RESPONSE = 0x6900;
    /**
     * Response for APP_MSG
     */
    public static final int APP_MSG_RSP = 0x6980;
    /**
     * This command is used by the tester to set the debug threshold level for a
     * particular software component in the target.
     */
    public static final int APP_USER_TEST = 0x2901;
    /**
     * Response for APP_USER_TEST
     */
    public static final int APP_USER_TEST_RESPONSE = 0x6901;
    /**
     * This message is issued by the target APS to the tester to report the
     * results of a request to transfer a data PDU from a local NHLE (Next
     * Higher Layer Entity) to a single peer NHLE.
     */
    public static final int APSDE_DATA_CONFIRMATION = 0x880;
    /**
     * Response for APSME_BIND
     */
    public static final int APSME_BIND_RESPONSE = 0x1804;
    /**
     * This command is used by the tester to set the debug threshold level for a
     * particular software component in the target.
     */
    public static final int DEBUG_SET_DEBUG_THRESHOLD = 0x2800;
    /**
     * Response for SYS_SET_DEBUG_THRESHOLD
     */
    public static final int DEBUG_SET_DEBUG_THRESHOLD_RESPONSE = 0x6800;
    /**
     * Debug message sent by device
     */
    public static final int DEBUG_STRING = 0x4880;
    /**
     * This message is issued by the target NWK to the tester to report the
     * results of a request to transfer a data PDU from a local APS sub-layer
     * entity to a single peer APS sub-layer entity.
     */
    public static final int NLDE_DATA_CONFIRMATION = 0x4380;
    /**
     * This message is issued by the target NWK to the tester to indicate the
     * transfer of a data PDU from the NWK layer to the local APS sub-layer
     * entity.
     */
    public static final int NLDE_DATA_INDICATION = 0x4381;
    /**
     * This command enables the tester to request the transfer of data from the
     * local APS sub-layer to a peer APS sublayer entity.
     */
    public static final int NLDE_DATA_REQUEST = 0x2301;
    /**
     * Response for NLDE_DATA_REQUEST
     */
    public static final int NLDE_DATA_RESPONSE = 0x6301;
    /**
     * This function will initialize the nwk with the NWK_TASKID.
     */
    public static final int NLDE_NWK_INIT = 0x4300;
    /**
     * NLME direct join request used by tester
     */
    public static final int NLME_DIRECTJOIN_REQUEST = 0x230b;
    /**
     * Response for NLME_DIRECTJOIN_REQUEST
     */
    public static final int NLME_DIRECTJOIN_RESPONSE = 0x630b;
    /**
     * This command is used by tester to make a request (on behalf of the next
     * higher layer) to read the value of an attribute from the NWK information
     * base (NIB).
     */
    public static final int NLME_GET_REQUEST = 0x2307;
    /**
     * Response for NLME_GET_REQUEST
     */
    public static final int NLME_GET_RESPONSE = 0x6307;
    /**
     * This command is issued by the target NWK (to tester) to announce the next
     * higher layer of the results of its request to join itself or another
     * device to a network.
     */
    public static final int NLME_JOIN_CONFIRMATION = 0x4383;
    /**
     * This message is sent by the target to announce the next higher layer of a
     * remote join request.
     */
    public static final int NLME_JOIN_INDICATION = 0x4384;
    /**
     * This command is used by tester to make a request (on behalf of the next
     * higher layer) to join the device itself or another device to a network.
     */
    public static final int NLME_JOIN_REQUEST = 0x2304;
    /**
     * Response for NLME_JOIN_REQUEST
     */
    public static final int NLME_JOIN_RESPONSE = 0x6304;
    /**
     * This message is sent by the target to indicate to the next higher layer
     * that the device itself or another device is leaving the network.
     */
    public static final int NLME_LEAVE_CONFIRMATION = 0x4385;
    /**
     * This message is sent by the target to indicate a remote leave request to
     * the next higher layer of a coordinator
     */
    public static final int NLME_LEAVE_INDICATION = 0x4386;
    /**
     * This command is used by tester to make a request (on behalf of the next
     * higher layer) that the device itself or another device leave the network.
     */
    public static final int NLME_LEAVE_REQUEST = 0x2305;
    /**
     * Response for NLME_LEAVE_REQUEST
     */
    public static final int NLME_LEAVE_RESPONSE = 0x6305;
    /**
     * This message is used by the target NWK to inform the tester of the result
     * of a previous association request command
     */
    public static final int NLME_NETWORK_FORMATION_CONFIRMATION = 0x4382;
    /**
     * This command is used by tester to request (on behalf of the next higher
     * layer) that the device be initiated as a coordinator.
     */
    public static final int NLME_NETWORK_FORMATION_REQUEST = 0x2302;
    /**
     * Response for NLME_NETWORK_FORMATION_REQUEST
     */
    public static final int NLME_NETWORK_FORMATION_RESPONSE = 0x6302;
    /**
     * This message is sent by the target to indicate network discovery
     * confirmation
     */
    public static final int NLME_NETWORKDISCOVERY_CONFIRMATION = 0x4389;
    /**
     * NLME Network discovery request used by tester
     */
    public static final int NLME_NETWORKDISCOVERY_REQUEST = 0x2309;
    /**
     * Response for NLME_NETWORKDISCOVERY_REQUEST
     */
    public static final int NLME_NETWORKDISCOVERY_RESPONSE = 0x6309;
    /**
     * NLME orphan join request used by tester
     */
    public static final int NLME_ORPHANJOIN_REQUEST = 0x230c;
    /**
     * Response for NLME_ORPHANJOIN_REQUEST
     */
    public static final int NLME_ORPHANJOIN_RESPONSE = 0x630c;
    /**
     * This command is used by the tester to define how the next higher layer of
     * a coordinator device would permit devices to join its network for a fixed
     * period.
     */
    public static final int NLME_PERMITJOINING_REQUEST = 0x2303;
    /**
     * Response for NLME_PERMITJOINING_REQUEST
     */
    public static final int NLME_PERMITJOINING_RESPONSE = 0x6303;
    /**
     * This function reports the results of a polling attempt.
     */
    public static final int NLME_POLL_CONFIRMATION = 0x4387;
    /**
     * This command is used by tester to make a request (on behalf of the next
     * higher layer) that the NWK layer perform a reset operation
     */
    public static final int NLME_RESET_REQUEST = 0x2306;
    /**
     * Response for NLME_RESET_REQUEST
     */
    public static final int NLME_RESET_RESPONSE = 0x6306;
    /**
     * NLME route discovery request used by tester
     */
    public static final int NLME_ROUTEDISCOVERY_REQUEST = 0x230a;
    /**
     * Response for NLME_ROUTEDISCOVERY_REQUEST
     */
    public static final int NLME_ROUTEDISCOVERY_RESPONSE = 0x630a;
    /**
     * This command is used by tester to make a request (on behalf of the next
     * higher layer) to set the value of an attribute in the NWK information
     * base (NIB).
     */
    public static final int NLME_SET_REQUEST = 0x2308;
    /**
     * Response for NLME_SET_REQUEST
     */
    public static final int NLME_SET_RESPONSE = 0x6308;
    /**
     * This message is sent by the target to the next higher layer of the
     * results of its request to start a router
     */
    public static final int NLME_STARTROUTER_CONFIRMATION = 0x438a;
    /**
     * NLME Start router request used by tester
     */
    public static final int NLME_STARTROUTER_REQUEST = 0x230d;
    /**
     * Response for NLME_STARTROUTER_REQUEST
     */
    public static final int NLME_STARTROUTER_RESPONSE = 0x630d;
    /**
     * This message is sent by the target to indicate a sync request to the next
     * higher layer of a coordinator
     */
    public static final int NLME_SYNC_INDICATION = 0x4388;
    /**
     * This function is used to inform the upper layers of the initiating device
     * whether its request to associate was successful or unsuccessful.
     */
    public static final int NWK_ASSOCIATE_CONFIRMATION = 0x2382;
    /**
     * This function is used to indicate the reception of an association request
     * command.
     */
    public static final int NWK_ASSOCIATE_INDICATION = 0x2381;
    /**
     * This function is used to send parameters contained within a beacon frame
     * received by the MAC sublayer to the next higher layer. The function also
     * sends a measure of the link quality and the time the beacon was received.
     **/
    public static final int NWK_BEACON_NOTIFY_INDICATION = 0x2383;
    /**
     * This function reports a comm status error
     */
    public static final int NWK_COMM_STATUS_INDICATION = 0x238d;
    /**
     * This function is used to send the results of a request to transfer a data
     * SPDU (MSDU) from a local SSCS entity to a single peer SSCS entity; or
     * multiple peer SSCS entities.
     */
    public static final int NWK_DATA_CONFIRMATION = 0x2384;
    /**
     * This function indicates the transfer of a data SPDU (MSDU) from the MAC
     * sublayer to the local SSCS entity.
     */
    public static final int NWK_DATA_INDICATION = 0x2385;
    /**
     * This function is sent as the result of a disassociation request.
     */
    public static final int NWK_DISASSOCIATE_CONFIRMATION = 0x2387;
    /**
     * This function is used to indicate the reception of a disassociation
     * notification command.
     */
    public static final int NWK_DISASSOCIATE_INDICATION = 0x2386;
    /**
     * This function allows the MLME to announce the next higher layer of an
     * orphaned device
     */
    public static final int NWK_ORPHAN_INDICATION = 0x238a;
    /**
     * This function reports the results of a polling attempt.
     */
    public static final int NWK_POLL_CONFIRMATION = 0x238b;
    /**
     * This function reports the results of a purge attempt.
     */
    public static final int NWK_PURGE_CONFIRMATION = 0x2390;
    /**
     * This function reports the results of an RX enable attempt.
     */
    public static final int NWK_RX_ENABLE_CONFIRMATION = 0x238f;
    /**
     * This function reports the results of a channel scan request.
     */
    public static final int NWK_SCAN_CONFIRMATION = 0x238c;
    /**
     * This function reports the success of the start request.
     */
    public static final int NWK_START_CONFIRMATION = 0x238e;
    /**
     * This function indicates the loss of synchronization of a network beacon
     */
    public static final int NWK_SYNCHRONIZATION_LOSS_INDICATION = 0x2380;
    /**
     * Stop timer.
     */
    public static final int SYS_ADC_READ = 0x210d;
    /**
     * Response for SYS_ADC_READ
     */
    public static final int SYS_ADC_READ_SRSP = 0x610d;
    /**
     * Configure the accessible GPIO pins
     */
    public static final int SYS_GPIO = 0x210e;
    /**
     * Configure the device RF test modes
     */
    public static final int SYS_TEST_RF = 0x4140;
    /**
     * Test the physical interface
     */
    public static final int SYS_TEST_LOOPBACK = 0x2141;
    /**
     * Response to SYS_GPIO
     */
    public static final int SYS_GPIO_SRSP = 0x610e;
    /**
     * Response to SYS_TEST_LOOPBACK
     */
    public static final int SYS_TEST_LOOPBACK_SRSP = 0x6141;
    /**
     * This command is used by the tester to read a single memory location in
     * the target non-volatile memory. The command accepts an address value and
     * returns the memory value present in the target at that address.
     */
    public static final int SYS_OSAL_NV_READ = 0x2108;
    /**
     * Response for SYS_OSAL_NV_READ
     */
    public static final int SYS_OSAL_NV_READ_SRSP = 0x6108;
    /**
     * This command is used by the tester to write to a particular location in
     * non-volatile memory. The command accepts an address location and a memory
     * value. The memory value is written to the address location in the target.
     */
    public static final int SYS_OSAL_NV_WRITE = 0x2109;
    /**
     * Response for SYS_OSAL_NV_WRITE
     */
    public static final int SYS_OSAL_NV_WRITE_SRSP = 0x6109;
    /**
     * Start timer.
     */
    public static final int SYS_OSAL_START_TIMER = 0x210a;
    /**
     * Response for SYS_OSAL_START_TIMER
     */
    public static final int SYS_OSAL_START_TIMER_SRSP = 0x610a;
    /**
     * Stop timer.
     */
    public static final int SYS_OSAL_STOP_TIMER = 0x210b;
    /**
     * Response for SYS_OSAL_STOP_TIMER
     */
    public static final int SYS_OSAL_STOP_TIMER_SRSP = 0x610b;
    /**
     * OSAL timer expired
     */
    public static final int SYS_OSAL_TIMER_EXPIRED_IND = 0x4181;
    /**
     * This command is used to check for a device
     */
    public static final int SYS_PING = 0x2101;
    /**
     * Response for SYS_PING
     */
    public static final int SYS_PING_RESPONSE = 0x6101;
    /**
     * Generate random number.
     */
    public static final int SYS_RANDOM = 0x210c;
    /**
     * Response for SYS_RANDOM
     */
    public static final int SYS_RANDOM_SRSP = 0x610c;
    /**
     * This command is sent by the tester to the target to reset it
     */
    public static final int SYS_RESET = 0x4100;
    /**
     * Indicates a device has reset.
     */
    public static final int SYS_RESET_RESPONSE = 0x4180;
    /**
     * RPC transport layer error.
     */
    public static final int SYS_RPC_ERROR = 0x6000;
    /**
     * Ask for the device's version string.
     */
    public static final int SYS_VERSION = 0x2102;
    /**
     * Response for SYS_VERSION
     */
    public static final int SYS_VERSION_RESPONSE = 0x6102;
    /**
     * This message is sent to the target in order to test the functions defined
     * for individual applications (which internally use attributes and cluster
     * IDs from various device descriptions).
     */
    public static final int USERTEST_REQUEST = 0xb51;
    /**
     * Response for USERTEST_REQUEST
     */
    public static final int USERTEST_RESPONSE = 0x1b51;
    /**
     * This command subscribes/unsubscribes to layer callbacks.
     */
    public static final int UTIL_CALLBACK_SUBSCRIBE = 0x2706;
    /**
     * Response for UTIL_CALLBACK_SUBSCRIBE
     */
    public static final int UTIL_CALLBACK_SUBSCRIBE_RESPONSE = 0x6706;
    /**
     * This command is used by the tester to read a single memory location in
     * the target non-volatile memory. The command accepts an address value and
     * returns the memory value present in the target at that address.
     */
    public static final int UTIL_GET_DEVICE_INFO = 0x2700;
    /**
     * Response for UTIL_GET_DEVICE_INFO
     */
    public static final int UTIL_GET_DEVICE_INFO_RESPONSE = 0x6700;
    /**
     * Use this message to get the NV information.
     */
    public static final int UTIL_GET_NV_INFO = 0x2701;
    /**
     * Response for UTIL_GET_NV_INFO
     */
    public static final int UTIL_GET_NV_INFO_RESPONSE = 0x6701;
    /**
     * Use this message to get board's time alive.
     */
    public static final int UTIL_GET_TIME_ALIVE = 0x2709;
    /**
     * Response for UTIL_GET_TIME_ALIVE
     */
    public static final int UTIL_GET_TIME_ALIVE_RESPONSE = 0x6709;
    /**
     * Sends a key event to the device registered application. The device
     * register application means that the application registered for key events
     * with OnBoard. Not all application support all keys; so you must know what
     * keys the application supports.
     */
    public static final int UTIL_KEY_EVENT = 0x2707;
    /**
     * Response for UTIL_KEY_EVENT
     */
    public static final int UTIL_KEY_EVENT_RESPONSE = 0x6707;
    /**
     * Use this message to control LEDs on the board.
     */
    public static final int UTIL_LED_CONTROL = 0x270a;
    /**
     * Response for UTIL_LED_CONTROL
     */
    public static final int UTIL_LED_CONTROL_RESPONSE = 0x670a;
    /**
     * Use this message to set the channels.
     */
    public static final int UTIL_SET_CHANNELS = 0x2703;
    /**
     * Response for UTIL_SET_CHANNELS
     */
    public static final int UTIL_SET_CHANNELS_RESPONSE = 0x6703;
    /**
     * Use this message to set PANID.
     */
    public static final int UTIL_SET_PANID = 0x2702;
    /**
     * Response for UTIL_SET_PANID
     */
    public static final int UTIL_SET_PANID_RESPONSE = 0x6702;
    /**
     * Use this message to set the preconfig key.
     */
    public static final int UTIL_SET_PRECONFIG_KEY = 0x2705;
    /**
     * Response for UTIL_SET_PRECONFIG_KEY
     */
    public static final int UTIL_SET_PRECONFIG_KEY_RESPONSE = 0x6705;
    /**
     * Use this message to set the security level.
     */
    public static final int UTIL_SET_SECURITY_LEVEL = 0x2704;
    /**
     * Response for UTIL_SET_SECURITY_LEVEL
     */
    public static final int UTIL_SET_SECURITY_LEVEL_RESPONSE = 0x6704;
    /**
     * Puts the device into the Allow Bind mode (zb_AllowBind).
     */
    public static final int ZB_ALLOW_BIND = 0x2602;
    /**
     * Response for ZB_ALLOW_BIND
     */
    public static final int ZB_ALLOW_BIND_CONFIRM = 0x4682;
    /**
     * Response for ZB_ALLOW_BIND
     */
    public static final int ZB_ALLOW_BIND_RSP = 0x6602;
    /**
     * This command register the device descriptor
     */
    public static final int ZB_APP_REGISTER_REQUEST = 0x260a;
    /**
     * Response for ZB_APP_REGISTER_REQUEST
     */
    public static final int ZB_APP_REGISTER_RSP = 0x660a;
    /**
     * Response for ZB_BIND_DEVICE
     */
    public static final int ZB_BIND_CONFIRM = 0x4681;
    /**
     * Create or remove a binding entry (zb_BindDevice).
     */
    public static final int ZB_BIND_DEVICE = 0x2601;
    /**
     * Response for ZB_BIND_DEVICE
     */
    public static final int ZB_BIND_DEVICE_RSP = 0x6601;
    /**
     * (zb_FindDeviceConfirm)
     */
    public static final int ZB_FIND_DEVICE_CONFIRM = 0x4685;
    /**
     * Search for a device's short address given its IEEE address.
     */
    public static final int ZB_FIND_DEVICE_REQUEST = 0x2607;
    /**
     * Response for ZB_FIND_DEVICE_REQUEST
     */
    public static final int ZB_FIND_DEVICE_REQUEST_RSP = 0x6607;
    /**
     * Reads current device information.
     */
    public static final int ZB_GET_DEVICE_INFO = 0x2606;
    /**
     * Response for ZB_GET_DEVICE_INFO
     */
    public static final int ZB_GET_DEVICE_INFO_RSP = 0x6606;
    /**
     * Enables or disables the joining permissions on the destination device
     * thus controlling the ability of new devices to join the network.
     */
    public static final int ZB_PERMIT_JOINING_REQUEST = 0x2608;
    /**
     * Response for ZB_PERMIT_JOINING_REQUEST
     */
    public static final int ZB_PERMIT_JOINING_REQUEST_RSP = 0x6608;
    /**
     * Reads a configuration property from nonvolatile memory
     * (zb_ReadConfiguration).
     */
    public static final int ZB_READ_CONFIGURATION = 0x2604;
    /**
     *
     */
    public static final int ZB_READ_CONFIGURATION_RSP = 0x6604;
    /**
     * (zb_ReceiveDataIndication)
     */
    public static final int ZB_RECEIVE_DATA_INDICATION = 0x4687;
    /**
     * Response for ZB_SEND_DATA_REQUEST
     */
    public static final int ZB_SEND_DATA_CONFIRM = 0x4683;
    /**
     * Send a data packet to another device (zb_SendDataRequest).
     */
    public static final int ZB_SEND_DATA_REQUEST = 0x2603;
    /**
     * Response for ZB_SEND_DATA_REQUEST
     */
    public static final int ZB_SEND_DATA_REQUEST_RSP = 0x6603;
    /**
     * Response for ZB_START_REQUEST
     */
    public static final int ZB_START_CONFIRM = 0x4680;
    /**
     * Starts the ZigBee stack (zb_StartRequest).
     */
    public static final int ZB_START_REQUEST = 0x2600;
    /**
     * Response for ZB_START_REQUEST
     */
    public static final int ZB_START_REQUEST_RSP = 0x6600;
    /**
     * Reboot the device (zb_SystemReset)
     */
    public static final int ZB_SYSTEM_RESET = 0x4609;
    /**
     * Writes a configuration property to nonvolatile memory
     * (zb_WriteConfiguration).
     */
    public static final int ZB_WRITE_CONFIGURATION = 0x2605;
    /**
     * Response for ZB_WRITE_CONFIGURATION
     */
    public static final int ZB_WRITE_CONFIGURATION_RSP = 0x6605;
    /**
     * This command is generated to request a list of active endpoint from the
     * destination device.
     */
    public static final int ZDO_ACTIVE_EP_REQ = 0x2505;
    /**
     * Response for ZDO_ACTIVE_EP_REQ
     */
    public static final int ZDO_ACTIVE_EP_REQ_SRSP = 0x6505;
    /**
     * This callback message is in response to the ZDO Active Endpoint Request.
     */
    public static final int ZDO_ACTIVE_EP_RSP = 0x4585;
    /**
     * This function will issue a Match Description Request for the requested
     * endpoint outputs. This message will generate a broadcast message.
     */
    public static final int ZDO_AUTO_FIND_DESTINATION = 0x4541;
    /**
     * This command is generated to request a Bind
     */
    public static final int ZDO_BIND_REQ = 0x2521;
    /**
     * Response for ZDO_BIND_REQ
     */
    public static final int ZDO_BIND_REQ_SRSP = 0x6521;
    /**
     * This callback message is in response to the ZDO Bind Request
     */
    public static final int ZDO_BIND_RSP = 0x45a1;
    /**
     * This command is generated to request for the destination device's complex
     * descriptor
     */
    public static final int ZDO_COMPLEX_DESC_REQ = 0x2507;
    /**
     * Response for ZDO_COMPLEX_DESC_REQ
     */
    public static final int ZDO_COMPLEX_DESC_REQ_SRSP = 0x6507;
    /**
     * Response for ZDO_COMPLEX_DESC_REQ
     */
    public static final int ZDO_COMPLEX_DESC_RSP = 0x4587;
    /**
     * This command is generated to request an End Device Announce.
     */
    public static final int ZDO_END_DEVICE_ANNCE = 0x250a;
    /**
     * ZDO end device announce indication.
     */
    public static final int ZDO_END_DEVICE_ANNCE_IND = 0x45c1;
    /**
     * Response for ZDO_END_DEVICE_ANNCE
     */
    public static final int ZDO_END_DEVICE_ANNCE_SRSP = 0x650a;
    /**
     * This command is generated to request an End Device Bind with the
     * destination device
     */
    public static final int ZDO_END_DEVICE_BIND_REQ = 0x2520;
    /**
     * Response for ZDO_END_DEVICE_BIND_REQ
     */
    public static final int ZDO_END_DEVICE_BIND_REQ_SRSP = 0x6520;
    /**
     * This callback message is in response to the ZDO End Device Bind Request
     */
    public static final int ZDO_END_DEVICE_BIND_RSP = 0x45a0;
    /**
     * This command will request a device's IEEE 64-bit address. You must
     * subscribe to 'ZDO IEEE Address Response' to receive the data response to
     * this message. The response message listed below only indicates whether or
     * not the message was received properly.
     */
    public static final int ZDO_IEEE_ADDR_REQ = 0x2501;
    /**
     * Response for ZDO_IEEE_ADDR_REQ
     */
    public static final int ZDO_IEEE_ADDR_REQ_SRSP = 0x6501;
    /**
     * This callback message is in response to the ZDO IEEE Address Request.
     */
    public static final int ZDO_IEEE_ADDR_RSP = 0x4581;
    /**
     * This command is generated to request a list of active endpoint from the
     * destination device
     */
    public static final int ZDO_MATCH_DESC_REQ = 0x2506;
    /**
     * Response for ZDO_MATCH_DESC_REQ
     */
    public static final int ZDO_MATCH_DESC_REQ_SRSP = 0x6506;
    /**
     * This callback message is in response to the ZDO Match Description Request
     */
    public static final int ZDO_MATCH_DESC_RSP = 0x4586;
    /**
     * This command is generated to request a Management Binding Table Request.
     */
    public static final int ZDO_MGMT_BIND_REQ = 0x2533;
    /**
     * Response for ZDO_MGMT_BIND_REQ
     */
    public static final int ZDO_MGMT_BIND_REQ_SRSP = 0x6533;
    /**
     * This callback message is in response to the ZDO Management Binding Table
     * Request
     */
    public static final int ZDO_MGMT_BIND_RSP = 0x45b3;
    /**
     * This command is generated to request a Management Direct Join Request
     */
    public static final int ZDO_MGMT_DIRECT_JOIN_REQ = 0x2535;
    /**
     * Response for ZDO_MGMT_DIRECT_JOIN_REQ
     */
    public static final int ZDO_MGMT_DIRECT_JOIN_REQ_SRSP = 0x6535;
    /**
     * This callback message is in response to the ZDO Management Direct Join
     * Request.
     */
    public static final int ZDO_MGMT_DIRECT_JOIN_RSP = 0x45b5;
    /**
     * This command is generated to request a Management Leave Request
     */
    public static final int ZDO_MGMT_LEAVE_REQ = 0x2534;
    /**
     * Response for ZDO_MGMT_LEAVE_REQ
     */
    public static final int ZDO_MGMT_LEAVE_REQ_SRSP = 0x6534;
    /**
     * This callback message is in response to the ZDO Management Leave Request.
     */
    public static final int ZDO_MGMT_LEAVE_RSP = 0x45b4;
    /**
     * This command is generated to request a Management LQI Request.
     */
    public static final int ZDO_MGMT_LQI_REQ = 0x2531;
    /**
     * Response for ZDO_MGMT_LQI_REQ
     */
    public static final int ZDO_MGMT_LQI_REQ_SRSP = 0x6531;
    /**
     * This callback message is in response to the ZDO Management LQI Request.
     */
    public static final int ZDO_MGMT_LQI_RSP = 0x45b1;
    /**
     * This command is generated to request a Management Network Discovery
     * Request
     */
    public static final int ZDO_MGMT_NWK_DISC_REQ = 0x2530;
    /**
     * Response for ZDO_MGMT_NWK_DISC_REQ
     */
    public static final int ZDO_MGMT_NWK_DISC_REQ_SRSP = 0x6530;
    /**
     * This callback message is in response to the ZDO Management Network
     * Discovery Request
     */
    public static final int ZDO_MGMT_NWK_DISC_RSP = 0x45b0;
    /**
     * This command is provided to allow updating of network configuration
     * parameters or to request information from devices on network conditions
     * in the local operating environment.
     */
    public static final int ZDO_MGMT_NWK_UPDATE_REQ = 0x2537;
    /**
     * Response for ZDO_MGMT_NWK_UPDATE_REQ
     */
    public static final int ZDO_MGMT_NWK_UPDATE_REQ_SRSP = 0x6537;
    /**
     * Response for ZDO_MGMT_PERMIT_JOIN_REQ
     */
    public static final int ZDO_MGMT_PERMIT_JOIN_REQ_SRSP = 0x6536;
    /**
     * This command is generated to request a Management Join Request
     */
    public static final int ZDO_MGMT_PERMIT_JOIN_REQ = 0x2536;
    /**
     * This callback message is in response to the ZDO Management Permit Join
     * Request
     */
    public static final int ZDO_MGMT_PERMIT_JOIN_RSP = 0x45b6;
    /**
     * This command is generated to request a Management Routing Table Request.
     */
    public static final int ZDO_MGMT_RTG_REQ = 0x2532;
    /**
     * Response for ZDO_MGMT_RTG_REQ
     */
    public static final int ZDO_MGMT_RTG_REQ_SRSP = 0x6532;
    /**
     * This callback message is in response to the ZDO Management Routing Table
     * Request.
     */
    public static final int ZDO_MGMT_RTG_RSP = 0x45b2;
    /**
     * This command registers for a ZDO callback.
     */
    public static final int ZDO_MSG_CB_REGISTER = 0x253e;
    /**
     * Response for ZDO_MSG_CB_REGISTER.
     */
    public static final int ZDO_MSG_CB_REGISTER_SRSP = 0x653e;
    /**
     * This callback message contains a ZDO cluster response.
     */
    public static final int ZDO_MSG_CB_INCOMING = 0x45ff;
    /**
     * This command is generated to inquire as to the Node Descriptor of the
     * destination device
     */
    public static final int ZDO_NODE_DESC_REQ = 0x2502;
    /**
     * Response for ZDO_NODE_DESC_REQ
     */
    public static final int ZDO_NODE_DESC_REQ_SRSP = 0x6502;
    /**
     * This callback message is in response to the ZDO Node Descriptor Request.
     */
    public static final int ZDO_NODE_DESC_RSP = 0x4582;
    /**
     * This message will request the device to send a "Network Address Request".
     * This message sends a broadcast message looking for a 16 bit address with
     * a 64 bit address as bait. You must subscribe to
     * "ZDO Network Address Response" to receive the response to this message.
     * The response message listed below only indicates whether or not the
     * message was received properly.
     */
    public static final int ZDO_NWK_ADDR_REQ = 0x2500;
    /**
     * Response for ZDO_NWK_ADDR_REQ
     */
    public static final int ZDO_NWK_ADDR_REQ_SRSP = 0x6500;
    /**
     * This callback message is in response to the ZDO Network Address Request.
     */
    public static final int ZDO_NWK_ADDR_RSP = 0x4580;
    /**
     * This command is generated to inquire as to the Power Descriptor of the
     * destination
     */
    public static final int ZDO_POWER_DESC_REQ = 0x2503;
    /**
     * Response for ZDO_POWER_DESC_REQ
     */
    public static final int ZDO_POWER_DESC_REQ_SRSP = 0x6503;
    /**
     * This callback message is in response to the ZDO Power Descriptor Request.
     */
    public static final int ZDO_POWER_DESC_RSP = 0x4583;
    /**
     * The command is used for local device to discover the location of a
     * particular system server or servers as indicated by the ServerMask
     * parameter. The destination addressing on this request is 'broadcast to
     * all RxOnWhenIdle devices'.
     */
    public static final int ZDO_SERVER_DISC_REQ = 0x250c;
    /**
     * Response for ZDO_SERVER_DISC_REQ
     */
    public static final int ZDO_SERVER_DISC_REQ_SRSP = 0x650c;
    /**
     * This callback message is
     */
    public static final int ZDO_SERVER_DISC_RSP = 0x458a;
    /**
     * This command is generated to inquire as to the Simple Descriptor of the
     * destination device's Endpoint
     */
    public static final int ZDO_SIMPLE_DESC_REQ = 0x2504;
    /**
     * Response for ZDO_SIMPLEDESCRIPTOR_REQUEST
     */
    public static final int ZDO_SIMPLE_DESC_REQ_SRSP = 0x6504;
    /**
     * This callback message is in response to the ZDO Simple Descriptor
     * Request.
     */
    public static final int ZDO_SIMPLE_DESC_RSP = 0x4584;
    /**
     * In the case where compiler flag HOLD_AUTO_START is defined by default;
     * device will start from HOLD state. Issuing this command will trigger the
     * device to leave HOLD state to form or join a network.
     */
    public static final int ZDO_STARTUP_FROM_APP = 0x2540;
    /**
     * Response for ZDO_STARTUP_FROM_APP
     */
    public static final int ZDO_STARTUP_FROM_APP_SRSP = 0x6540;
    /**
     * ZDO state change indication.
     */
    public static final int ZDO_STATE_CHANGE_IND = 0x45c0;
    /**
     * This message is the default message for error status.
     */
    public static final int ZDO_STATUS_ERROR_RSP = 0x45c3;
    /**
     * ZDO Trust Center end device announce indication.
     */
    public static final int ZDO_TC_DEVICE_IND = 0x45ca;
    /**
     * This command is generated to request an UnBind
     */
    public static final int ZDO_UNBIND_REQ = 0x2522;
    /**
     * Response for ZDO_UNBIND_REQ
     */
    public static final int ZDO_UNBIND_REQ_SRSP = 0x6522;
    /**
     * This callback message is in response to the ZDO UnBind Request
     */
    public static final int ZDO_UNBIND_RSP = 0x45a2;
    /**
     * This callback message is in response to the ZDO User Descriptor Set
     * Request.
     */
    public static final int ZDO_USER_DESC_CONF = 0x4589;
    /**
     * This command is generated to request for the destination device's user
     * descriptor
     */
    public static final int ZDO_USER_DESC_REQ = 0x2508;
    /**
     * Response for ZDO_USER_DESC_REQ
     */
    public static final int ZDO_USER_DESC_REQ_SRSP = 0x6508;
    /**
     * This callback message is in response to the ZDO User Description Request.
     */
    public static final int ZDO_USER_DESC_RSP = 0x4588;
    /**
     * This command is generated to request a User Descriptor Set Request
     */
    public static final int ZDO_USER_DESC_SET = 0x250b;
    /**
     * Response for ZDO_USER_DESC_SET
     */
    public static final int ZDO_USER_DESC_SET_SRSP = 0x650b;
    /**
     * This sends an associate confirm command
     */
    public static final int ZMAC_ASSOCIATE_CNF = 0x4282;
    /**
     * This command is used to send (on behalf of the next higher layer) an
     * association indication message
     */
    public static final int ZMAC_ASSOCIATE_IND = 0x4281;
    /**
     * This command is used to request (on behalf of the next higher layer) an
     * association with a coordinator
     */
    public static final int ZMAC_ASSOCIATE_REQUEST = 0x2206;
    /**
     * Response for ZMAC_ASSOCIATE_REQUEST
     */
    public static final int ZMAC_ASSOCIATE_RESPONSE = 0x6206;
    /**
     * Beacon Notify Indication
     */
    public static final int ZMAC_BEACON_NOTIFY_IND = 0x4283;
    /**
     * Communication status indication
     */
    public static final int ZMAC_COMM_STATUS_IND = 0x428d;
    /**
     * Data Request Confirmation
     */
    public static final int ZMAC_DATA_CNF = 0x4284;
    /**
     * Data Request Confirmation
     */
    public static final int ZMAC_DATA_IND = 0x4285;
    /**
     * This command is used to send (on behalf of the next higher layer) MAC
     * Data Frame packet.
     */
    public static final int ZMAC_DATA_REQUEST = 0x2205;
    /**
     * Response for ZMAC_DATA_REQUEST
     */
    public static final int ZMAC_DATA_RESPONSE = 0x6205;
    /**
     * Disassociate Indication
     */
    public static final int ZMAC_DISASSOCIATE_CNF = 0x4287;
    /**
     * Disassociate Indication
     */
    public static final int ZMAC_DISASSOCIATE_IND = 0x4286;
    /**
     * This command is used to request (on behalf of the next higher layer) a
     * disassociation of the device from the coordinator.
     */
    public static final int ZMAC_DISASSOCIATE_REQUEST = 0x2207;
    /**
     * Response for ZMAC_DISASSOCIATE_REQUEST
     */
    public static final int ZMAC_DISASSOCIATE_RESPONSE = 0x6207;
    /**
     * This command is used to read (on behalf of the next higher layer) a MAC
     * PIB attribute.
     */
    public static final int ZMAC_GET_REQUEST = 0x2208;
    /**
     * Response for ZMAC_GET_REQUEST
     */
    public static final int ZMAC_GET_RESPONSE = 0x6208;
    /**
     * This command is used to initialize the ZMAC on the current device (on
     * behalf of the next higher layer).
     */
    public static final int ZMAC_INIT_REQUEST = 0x2202;
    /**
     * >Response for ZMAC_INIT_REQUEST
     */
    public static final int ZMAC_INIT_RESPONSE = 0x6202;
    /**
     * Orphan Indication
     */
    public static final int ZMAC_ORPHAN_IND = 0x428a;
    /**
     * Mac Poll Confirmation
     */
    public static final int ZMAC_POLL_CNF = 0x428b;
    /**
     * This command is used to send a MAC data request poll
     */
    public static final int ZMAC_POLL_REQUEST = 0x220d;
    /**
     * Response for ZMAC_POLL_REQUEST
     */
    public static final int ZMAC_POLL_RESPONSE = 0x620d;
    /**
     * Mac RX enable Confirmation
     */
    public static final int ZMAC_PURGE_CNF = 0x4290;
    /**
     * This command is used to send a request to the device to purge a data
     * frame
     */
    public static final int ZMAC_PURGE_REQUEST = 0x220e;
    /**
     * Response for ZMAC_PURGE_REQUEST
     */
    public static final int ZMAC_PURGE_RESPONSE = 0x620e;
    /**
     * This command is used to send a MAC Reset command to reset MAC state
     * machine
     */
    public static final int ZMAC_RESET_REQUEST = 0x2201;
    /**
     * Response for ZMAC_RESET_REQUEST
     */
    public static final int ZMAC_RESET_RESPONSE = 0x6201;
    /**
     * Mac RX enable Confirmation
     */
    public static final int ZMAC_RX_ENABLE_CNF = 0x428f;
    /**
     * This command contains timing information that tells the device when to
     * enable or disable its receiver; in order to schedule a data transfer
     * between itself and another device. The information is sent from the upper
     * layers directly to the MAC sublayer.
     */
    public static final int ZMAC_RX_ENABLE_REQUEST = 0x220b;
    /**
     * Response for ZMAC_RX_ENABLE_REQUEST
     */
    public static final int ZMAC_RX_ENABLE_RESPONSE = 0x620b;
    /**
     * Scan Confirmation
     */
    public static final int ZMAC_SCAN_CNF = 0x428c;
    /**
     * This command is used to send a request to the device to perform a network
     * scan.
     */
    public static final int ZMAC_SCAN_REQUEST = 0x220c;
    /**
     * Response for ZMAC_SCAN_REQUEST
     */
    public static final int ZMAC_SCAN_RESPONSE = 0x620c;
    /**
     * This command is used to request the device to write a MAC PIB value.
     */
    public static final int ZMAC_SET_REQUEST = 0x2209;
    /**
     * Response for ZMAC_SET_REQUEST
     */
    public static final int ZMAC_SET_RESPONSE = 0x6209;
    /**
     * This command is used to send a request to the device to set Rx gain
     */
    public static final int ZMAC_SET_RX_GAIN_REQUEST = 0x220f;
    /**
     * Response for ZMAC_SET_RX_GAIN_REQUEST
     */
    public static final int ZMAC_SET_RX_GAIN_RESPONSE = 0x620f;
    /**
     * Mac Start Confirmation
     */
    public static final int ZMAC_START_CNF = 0x428e;
    /**
     * This command is used to request the MAC to transmit beacons and become a
     * coordinator
     */
    public static final int ZMAC_START_REQUEST = 0x2203;
    /**
     * Response for ZMAC_START_REQUEST
     */
    public static final int ZMAC_START_RESPONSE = 0x6203;
    /**
     * Indication for sync loss
     */
    public static final int ZMAC_SYNC_LOSS_IND = 0x4280;
    /**
     * This command is used to request synchronization to the current network
     * beacon
     */
    public static final int ZMAC_SYNCHRONIZE_REQUEST = 0x2204;
    /**
     * Response for ZMAC_SYNCHRONIZE_REQUEST
     */
    public static final int ZMAC_SYNCHRONIZE_RESPONSE = 0x6204;

    private ZToolCMD() {
    }

}
