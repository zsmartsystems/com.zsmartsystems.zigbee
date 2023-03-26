/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api;

import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfDataConfirmAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfDataRequestSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfIncomingMsgAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfRegisterSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbAddInstallcodeSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbCommissioningNotificationAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetActiveDefaultCentralizedKeySrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetChannelSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetJoinusesinstallcodekeySrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetTcRequireKeyExchangeSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfSetAllowrejoinTcPolicySrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.mac.ZstackMacScanReqSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sapi.ZstackZbGetDeviceInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sapi.ZstackZbReadConfigurationSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sapi.ZstackZbWriteConfigurationSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sbl.ZstackSbHandshakeCmdSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sbl.ZstackSbWriteCmdSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysGetExtAddrSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysNvReadSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysOsalNvReadSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysOsalNvWriteSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysPingSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysResetIndAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysSetExtAddrSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysSetTxPowerSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysVersionSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysZdiagsClearStatsSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysZdiagsGetStatsSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysZdiagsInitStatsSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysZdiagsRestoreStatsNvSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysZdiagsSaveStatsToNvSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilApsmeLinkKeyDataGetSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilApsmeLinkKeyNvIdGetSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetDeviceInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetNvInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilLedControlSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilSetChannelsSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilSetPanidSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilSetPrecfgkeySrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilSetSeclevelSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoExtNwkInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoGetLinkKeySrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoLeaveIndAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoMsgCbIncomingAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoMsgCbRegisterSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoNwkDiscoveryReqSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoRemoveLinkKeySrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoSetLinkKeySrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoStartupFromAppSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoStateChangeIndAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoTcDevIndAreq;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Factory class to create Z-Stack commands from incoming data. This will only create {@link ZstackFrameResponse}s.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackFrameFactory {
    /**
     * Logger
     */
    private static Logger logger = LoggerFactory.getLogger(ZstackFrameFactory.class);

    /*
     * Subsystem definitions
     */
    public static int ZSTACK_RPC = 0x0000;
    public static int ZSTACK_SYS = 0x0100;
    public static int ZSTACK_MAC = 0x0200;
    public static int ZSTACK_AF = 0x0400;
    public static int ZSTACK_ZDO = 0x0500;
    public static int ZSTACK_SAPI = 0x0600;
    public static int ZSTACK_UTIL = 0x0700;
    public static int ZSTACK_APP_CNF = 0x0F00;
    public static int ZSTACK_SBL = 0x0D00;

    /**
     * Subsystem definition mask
     */
    private static int ZSTACK_SUBSYSTEM_MASK = 0x1F;

    private static final int AF_DATA_CONFIRM = 0x80;
    private static final int AF_DATA_REQUEST = 0x01;
    private static final int AF_INCOMING_MSG = 0x81;
    private static final int AF_REGISTER = 0x00;
    private static final int APP_CNF_BDB_ADD_INSTALLCODE = 0x04;
    private static final int APP_CNF_BDB_COMMISSIONING_NOTIFICATION = 0x80;
    private static final int APP_CNF_BDB_SET_ACTIVE_DEFAULT_CENTRALIZED_KEY = 0x07;
    private static final int APP_CNF_BDB_SET_CHANNEL = 0x08;
    private static final int APP_CNF_BDB_SET_JOINUSESINSTALLCODEKEY = 0x06;
    private static final int APP_CNF_BDB_SET_TC_REQUIRE_KEY_EXCHANGE = 0x09;
    private static final int APP_CNF_SET_ALLOWREJOIN_TC_POLICY = 0x03;
    private static final int MAC_SCAN_REQ = 0x0C;
    private static final int RPC_SREQ_ERROR = 0x00;
    private static final int SB_HANDSHAKE_CMD = 0x04;
    private static final int SB_WRITE_CMD = 0x00;
    private static final int SYS_GET_EXT_ADDR = 0x04;
    private static final int SYS_NV_READ = 0x33;
    private static final int SYS_OSAL_NV_READ = 0x08;
    private static final int SYS_OSAL_NV_WRITE = 0x09;
    private static final int SYS_PING = 0x01;
    private static final int SYS_RESET_IND = 0x80;
    private static final int SYS_SET_EXT_ADDR = 0x03;
    private static final int SYS_SET_TX_POWER = 0x14;
    private static final int SYS_VERSION = 0x02;
    private static final int SYS_ZDIAGS_CLEAR_STATS = 0x18;
    private static final int SYS_ZDIAGS_GET_STATS = 0x19;
    private static final int SYS_ZDIAGS_INIT_STATS = 0x17;
    private static final int SYS_ZDIAGS_RESTORE_STATS_NV = 0x1A;
    private static final int SYS_ZDIAGS_SAVE_STATS_TO_NV = 0x1B;
    private static final int UTIL_APSME_LINK_KEY_DATA_GET = 0x44;
    private static final int UTIL_APSME_LINK_KEY_NV_ID_GET = 0x45;
    private static final int UTIL_GET_DEVICE_INFO = 0x00;
    private static final int UTIL_GET_NV_INFO = 0x01;
    private static final int UTIL_LED_CONTROL = 0x09;
    private static final int UTIL_SET_CHANNELS = 0x03;
    private static final int UTIL_SET_PANID = 0x02;
    private static final int UTIL_SET_PRECFGKEY = 0x05;
    private static final int UTIL_SET_SECLEVEL = 0x04;
    private static final int ZB_GET_DEVICE_INFO = 0x06;
    private static final int ZB_READ_CONFIGURATION = 0x04;
    private static final int ZB_WRITE_CONFIGURATION = 0x05;
    private static final int ZDO_EXT_NWK_INFO = 0x50;
    private static final int ZDO_GET_LINK_KEY = 0x25;
    private static final int ZDO_LEAVE_IND = 0xC9;
    private static final int ZDO_MSG_CB_INCOMING = 0xFF;
    private static final int ZDO_MSG_CB_REGISTER = 0x3E;
    private static final int ZDO_NWK_DISCOVERY_REQ = 0x26;
    private static final int ZDO_REMOVE_LINK_KEY = 0x24;
    private static final int ZDO_SET_LINK_KEY = 0x23;
    private static final int ZDO_STARTUP_FROM_APP = 0x40;
    private static final int ZDO_STATE_CHANGE_IND = 0xC0;
    private static final int ZDO_TC_DEV_IND = 0xCA;

    private static Map<Integer, Class<?>> zstackFrameMap = new HashMap<Integer, Class<?>>();

    static {
        zstackFrameMap.put(ZSTACK_AF + AF_DATA_CONFIRM, ZstackAfDataConfirmAreq.class);
        zstackFrameMap.put(ZSTACK_AF + AF_DATA_REQUEST, ZstackAfDataRequestSrsp.class);
        zstackFrameMap.put(ZSTACK_AF + AF_INCOMING_MSG, ZstackAfIncomingMsgAreq.class);
        zstackFrameMap.put(ZSTACK_AF + AF_REGISTER, ZstackAfRegisterSrsp.class);
        zstackFrameMap.put(ZSTACK_APP_CNF + APP_CNF_BDB_ADD_INSTALLCODE, ZstackAppCnfBdbAddInstallcodeSrsp.class);
        zstackFrameMap.put(ZSTACK_APP_CNF + APP_CNF_BDB_COMMISSIONING_NOTIFICATION, ZstackAppCnfBdbCommissioningNotificationAreq.class);
        zstackFrameMap.put(ZSTACK_APP_CNF + APP_CNF_BDB_SET_ACTIVE_DEFAULT_CENTRALIZED_KEY, ZstackAppCnfBdbSetActiveDefaultCentralizedKeySrsp.class);
        zstackFrameMap.put(ZSTACK_APP_CNF + APP_CNF_BDB_SET_CHANNEL, ZstackAppCnfBdbSetChannelSrsp.class);
        zstackFrameMap.put(ZSTACK_APP_CNF + APP_CNF_BDB_SET_JOINUSESINSTALLCODEKEY, ZstackAppCnfBdbSetJoinusesinstallcodekeySrsp.class);
        zstackFrameMap.put(ZSTACK_APP_CNF + APP_CNF_BDB_SET_TC_REQUIRE_KEY_EXCHANGE, ZstackAppCnfBdbSetTcRequireKeyExchangeSrsp.class);
        zstackFrameMap.put(ZSTACK_APP_CNF + APP_CNF_SET_ALLOWREJOIN_TC_POLICY, ZstackAppCnfSetAllowrejoinTcPolicySrsp.class);
        zstackFrameMap.put(ZSTACK_MAC + MAC_SCAN_REQ, ZstackMacScanReqSrsp.class);
        zstackFrameMap.put(ZSTACK_RPC + RPC_SREQ_ERROR, ZstackRpcSreqErrorSrsp.class);
        zstackFrameMap.put(ZSTACK_SBL + SB_HANDSHAKE_CMD, ZstackSbHandshakeCmdSrsp.class);
        zstackFrameMap.put(ZSTACK_SBL + SB_WRITE_CMD, ZstackSbWriteCmdSrsp.class);
        zstackFrameMap.put(ZSTACK_SYS + SYS_GET_EXT_ADDR, ZstackSysGetExtAddrSrsp.class);
        zstackFrameMap.put(ZSTACK_SYS + SYS_NV_READ, ZstackSysNvReadSrsp.class);
        zstackFrameMap.put(ZSTACK_SYS + SYS_OSAL_NV_READ, ZstackSysOsalNvReadSrsp.class);
        zstackFrameMap.put(ZSTACK_SYS + SYS_OSAL_NV_WRITE, ZstackSysOsalNvWriteSrsp.class);
        zstackFrameMap.put(ZSTACK_SYS + SYS_PING, ZstackSysPingSrsp.class);
        zstackFrameMap.put(ZSTACK_SYS + SYS_RESET_IND, ZstackSysResetIndAreq.class);
        zstackFrameMap.put(ZSTACK_SYS + SYS_SET_EXT_ADDR, ZstackSysSetExtAddrSrsp.class);
        zstackFrameMap.put(ZSTACK_SYS + SYS_SET_TX_POWER, ZstackSysSetTxPowerSrsp.class);
        zstackFrameMap.put(ZSTACK_SYS + SYS_VERSION, ZstackSysVersionSrsp.class);
        zstackFrameMap.put(ZSTACK_SYS + SYS_ZDIAGS_CLEAR_STATS, ZstackSysZdiagsClearStatsSrsp.class);
        zstackFrameMap.put(ZSTACK_SYS + SYS_ZDIAGS_GET_STATS, ZstackSysZdiagsGetStatsSrsp.class);
        zstackFrameMap.put(ZSTACK_SYS + SYS_ZDIAGS_INIT_STATS, ZstackSysZdiagsInitStatsSrsp.class);
        zstackFrameMap.put(ZSTACK_SYS + SYS_ZDIAGS_RESTORE_STATS_NV, ZstackSysZdiagsRestoreStatsNvSrsp.class);
        zstackFrameMap.put(ZSTACK_SYS + SYS_ZDIAGS_SAVE_STATS_TO_NV, ZstackSysZdiagsSaveStatsToNvSrsp.class);
        zstackFrameMap.put(ZSTACK_UTIL + UTIL_APSME_LINK_KEY_DATA_GET, ZstackUtilApsmeLinkKeyDataGetSrsp.class);
        zstackFrameMap.put(ZSTACK_UTIL + UTIL_APSME_LINK_KEY_NV_ID_GET, ZstackUtilApsmeLinkKeyNvIdGetSrsp.class);
        zstackFrameMap.put(ZSTACK_UTIL + UTIL_GET_DEVICE_INFO, ZstackUtilGetDeviceInfoSrsp.class);
        zstackFrameMap.put(ZSTACK_UTIL + UTIL_GET_NV_INFO, ZstackUtilGetNvInfoSrsp.class);
        zstackFrameMap.put(ZSTACK_UTIL + UTIL_LED_CONTROL, ZstackUtilLedControlSrsp.class);
        zstackFrameMap.put(ZSTACK_UTIL + UTIL_SET_CHANNELS, ZstackUtilSetChannelsSrsp.class);
        zstackFrameMap.put(ZSTACK_UTIL + UTIL_SET_PANID, ZstackUtilSetPanidSrsp.class);
        zstackFrameMap.put(ZSTACK_UTIL + UTIL_SET_PRECFGKEY, ZstackUtilSetPrecfgkeySrsp.class);
        zstackFrameMap.put(ZSTACK_UTIL + UTIL_SET_SECLEVEL, ZstackUtilSetSeclevelSrsp.class);
        zstackFrameMap.put(ZSTACK_SAPI + ZB_GET_DEVICE_INFO, ZstackZbGetDeviceInfoSrsp.class);
        zstackFrameMap.put(ZSTACK_SAPI + ZB_READ_CONFIGURATION, ZstackZbReadConfigurationSrsp.class);
        zstackFrameMap.put(ZSTACK_SAPI + ZB_WRITE_CONFIGURATION, ZstackZbWriteConfigurationSrsp.class);
        zstackFrameMap.put(ZSTACK_ZDO + ZDO_EXT_NWK_INFO, ZstackZdoExtNwkInfoSrsp.class);
        zstackFrameMap.put(ZSTACK_ZDO + ZDO_GET_LINK_KEY, ZstackZdoGetLinkKeySrsp.class);
        zstackFrameMap.put(ZSTACK_ZDO + ZDO_LEAVE_IND, ZstackZdoLeaveIndAreq.class);
        zstackFrameMap.put(ZSTACK_ZDO + ZDO_MSG_CB_INCOMING, ZstackZdoMsgCbIncomingAreq.class);
        zstackFrameMap.put(ZSTACK_ZDO + ZDO_MSG_CB_REGISTER, ZstackZdoMsgCbRegisterSrsp.class);
        zstackFrameMap.put(ZSTACK_ZDO + ZDO_NWK_DISCOVERY_REQ, ZstackZdoNwkDiscoveryReqSrsp.class);
        zstackFrameMap.put(ZSTACK_ZDO + ZDO_REMOVE_LINK_KEY, ZstackZdoRemoveLinkKeySrsp.class);
        zstackFrameMap.put(ZSTACK_ZDO + ZDO_SET_LINK_KEY, ZstackZdoSetLinkKeySrsp.class);
        zstackFrameMap.put(ZSTACK_ZDO + ZDO_STARTUP_FROM_APP, ZstackZdoStartupFromAppSrsp.class);
        zstackFrameMap.put(ZSTACK_ZDO + ZDO_STATE_CHANGE_IND, ZstackZdoStateChangeIndAreq.class);
        zstackFrameMap.put(ZSTACK_ZDO + ZDO_TC_DEV_IND, ZstackZdoTcDevIndAreq.class);
    }

    /**
     * Creates and {@link ZstackFrameResponse} from the incoming data.
     *
     * @param data the int[] containing the ZStack data from which to generate the frame
     * @return the {@link ZstackFrameResponse} or null if the response can't be created.
     */
    public static ZstackFrameResponse createFrame(int[] data) {
        if (data.length < 2) {
            return null;
        }

        int cmdId = ((data[0] & ZSTACK_SUBSYSTEM_MASK) << 8) + data[1];
        Class<?> zstackClass = zstackFrameMap.get(cmdId);

        if (zstackClass == null) {
            return null;
        }

        Constructor<?> ctor;
        try {
            ctor = zstackClass.getConstructor(int[].class);
            return (ZstackFrameResponse) ctor.newInstance(data);
        } catch (SecurityException | NoSuchMethodException | IllegalArgumentException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            logger.debug("Error creating instance of ZstackCommand", e);
        }

        return null;
    }


}
