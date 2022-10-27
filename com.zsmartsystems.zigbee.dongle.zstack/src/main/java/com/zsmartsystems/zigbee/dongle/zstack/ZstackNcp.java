/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackResponseCode;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfRegisterSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfRegisterSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbAddInstallcodeSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbAddInstallcodeSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetActiveDefaultCentralizedKeySreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetActiveDefaultCentralizedKeySrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetChannelSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetChannelSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetTcRequireKeyExchangeSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetTcRequireKeyExchangeSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfSetAllowrejoinTcPolicySreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfSetAllowrejoinTcPolicySrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackCentralizedLinkKeyMode;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackInstallCodeFormat;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackConfigId;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackResetType;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysNvReadSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysNvReadSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysOsalNvReadSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysOsalNvReadSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysOsalNvWriteSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysOsalNvWriteSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysPingSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysPingSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysResetIndAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysResetReqAcmd;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysSetTxPowerSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysSetTxPowerSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysVersionSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysVersionSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSystemCapabilities;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetDeviceInfoSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetDeviceInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetNvInfoSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetNvInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoExtNwkInfoSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoExtNwkInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoMsgCbRegisterSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoMsgCbRegisterSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoStartupFromAppSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoStartupFromAppSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.internal.ZstackProtocolHandler;
import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 * This class provides utility methods for accessing the ZStack NCP and returning specific data for use in the
 * application. It ensures the correct correlation of the requests and responses, and checks for errors.
 *
 * @author Chris Jackson - Initial contribution
 *
 */
public class ZstackNcp {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZstackNcp.class);

    /*
     * Startup options bitmap
     */
    private final int STARTOPT_CLEAR_CONFIG = 0x0001;
    private final int STARTOPT_CLEAR_STATE = 0x0002;

    /**
     * The protocol handler used to send and receive ZStack packets
     */
    private ZstackProtocolHandler protocolHandler;

    /**
     * Create the NCP instance
     *
     * @param protocolHandler the {@link ZstackProtocolHandler} used for communicating with the NCP
     */
    public ZstackNcp(ZstackProtocolHandler protocolHandler) {
        this.protocolHandler = protocolHandler;
    }

    /**
     * Resets the NCP
     *
     * @param resetType the {@link ZstackResetType} to request
     * @return the {@link ZstackSysResetIndAreq} from the NCP or null if there was an error
     */
    public ZstackSysResetIndAreq resetNcp(ZstackResetType resetType) {
        final Future<ZstackSysResetIndAreq> resetListener = protocolHandler.waitForEvent(ZstackSysResetIndAreq.class, response -> true);

        ZstackSysResetReqAcmd request = new ZstackSysResetReqAcmd();
        request.setType(resetType);
        protocolHandler.queueFrame(request);

        try {
            ZstackSysResetIndAreq response = resetListener.get();
            if (response == null) {
                logger.debug("No response from Reset command");
                return null;
            } else {
                logger.debug(response.toString());
                return response;
            }
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }
    }

    /**
     * Ping the NCP and return the set of subsystem capabilities compiled into the NCP firmware
     *
     * @return the list of subsystem {@link ZstackSystemCapabilities} from the NCP, or an empty set if there was an
     *         error
     */
    public Set<ZstackSystemCapabilities> pingNcp() {
        ZstackSysPingSreq request = new ZstackSysPingSreq();
        ZstackSysPingSrsp response = protocolHandler.sendTransaction(request, ZstackSysPingSrsp.class);
        if (response == null) {
            logger.debug("No response from Ping command");
            return Collections.emptySet();
        }
        logger.debug(response.toString());

        Set<ZstackSystemCapabilities> capabilities = new HashSet<>();
        for (ZstackSystemCapabilities capability : ZstackSystemCapabilities.values()) {
            if (capability == ZstackSystemCapabilities.UNKNOWN) {
                continue;
            }
            if ((capability.getKey() & response.getCapabilities()) != 0) {
                capabilities.add(capability);
            }
        }

        return capabilities;
    }

    /**
     * The command reads the version information from the stack
     *
     * @return the {@link ZstackSysVersionSrsp}
     */
    public ZstackSysVersionSrsp getVersion() {
        return protocolHandler.sendTransaction(new ZstackSysVersionSreq(), ZstackSysVersionSrsp.class);
    }

    /**
     * This command reads the network info from the device
     * @return the {@link ZstackZdoExtNwkInfoSrsp}
     */
    public ZstackZdoExtNwkInfoSrsp getNetworkInfo() {
        return protocolHandler.sendTransaction(new ZstackZdoExtNwkInfoSreq(), ZstackZdoExtNwkInfoSrsp.class);
    }

    /**
     * The command reads the device information from the NCP
     *
     * @return the {@link ZstackUtilGetDeviceInfoSrsp}
     */
    public ZstackUtilGetDeviceInfoSrsp getDeviceInfo() {
        return protocolHandler.sendTransaction(new ZstackUtilGetDeviceInfoSreq(), ZstackUtilGetDeviceInfoSrsp.class);
    }

    /**
     * The command reads the non-volatile device information from the NCP
     *
     * @return the {@link ZstackUtilGetDeviceInfoSrsp}
     */
    public ZstackUtilGetNvInfoSrsp getNvDeviceInfo() {
        return protocolHandler.sendTransaction(new ZstackUtilGetNvInfoSreq(), ZstackUtilGetNvInfoSrsp.class);
    }

    /**
     * Gets the current network address of the NCP
     *
     * @return the 16 bit local network address of the NCP or -1 if there was an error
     */
    public int getNwkAddress() {
        ZstackUtilGetDeviceInfoSrsp info = getDeviceInfo();

        return info == null ? -1 : info.getShortAddr();
    }

    /**
     * Sets the startup options when the NCP starts.
     * <p>
     * The CC2530-ZNP device has two kinds of information stored in non-volatile memory. The configuration parameters
     * and network state information.
     * <p>
     * The configuration parameters are configured by the user before start of ZigBee operation. The
     * STARTOPT_CLEAR_CONFIG bit is read by the CC2530-ZNP device immediately when it powers up after a reset.
     * When the configuration parameters are restored to defaults, the ZCD_NV_STARTUP_OPTION itself is not restored
     * except for clearing the STARTOPT_CLEAR_CONFIG bit.
     * <p>
     * The network state information is collected by the device after it joins a network and creates bindings etc. (at
     * runtime). This is not set by the application processor. This information is stored so that if the device were to
     * reset accidentally, it can restore itself without going through all the network joining and binding process
     * again.
     * <p>
     * If the application processor does not wish to continue operating in the previous ZigBee network, it needs to
     * instruct the CC2530-ZNP device to clear the network state information and start again based on the configuration
     * parameters. This is done by setting the STARTOPT_CLEAR_STATE bit in the startup option.
     *
     * @param clearConfig STARTOPT_CLEAR_CONFIG – If this option is set, the device will overwrite all the configuration
     *            parameters (except this one) with the “default” values that it is programmed with. This is used to
     *            erase the existing configuration and bring the device into a known state.
     * @param clearState STARTOPT_CLEAR_STATE – If this option is set, the device will clear its previous network state
     *            (which would exist if the device had been operating on a network prior to the reset). This is
     *            typically used during application development. During regular device operation, this flag is typically
     *            not set, so that an accidental device reset will not cause loss of network state.
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode setStartupOptions(boolean clearConfig, boolean clearState) {
        int optionVal = (clearConfig ? STARTOPT_CLEAR_CONFIG : 0) + (clearState ? STARTOPT_CLEAR_STATE : 0);
        return writeConfiguration(ZstackConfigId.ZCD_NV_STARTUP_OPTION, valueFromUInt8(optionVal));
    }

    /**
     * Reads a configuration parameter from the NCP
     *
     * @param configId the {@link ZstackConfigId} to read
     * @return a int[] with the value of the configuration data or null on error
     */
    public int[] readConfiguration(ZstackConfigId configId) {
        ZstackSysOsalNvReadSreq request = new ZstackSysOsalNvReadSreq();
        request.setId(configId);
        ZstackSysOsalNvReadSrsp response = protocolHandler.sendTransaction(request, ZstackSysOsalNvReadSrsp.class);
        if (response == null) {
            logger.debug("No response from ReadConfiguration command");
            return null;
        }
        if (response.getStatus() != ZstackResponseCode.SUCCESS) {
            logger.debug("No response from ReadConfiguration command: {}", response.getStatus());
            return null;
        }
        logger.debug(response.toString());
        return response.getValue();
    }

    /**
     * Writes a configuration parameter to the NCP
     *
     * @param configId the {@link ZstackConfigId} to read
     * @param value an int array containing the value to be written
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode writeConfiguration(ZstackConfigId configId, int[] value) {
        ZstackSysOsalNvWriteSreq request = new ZstackSysOsalNvWriteSreq();
        request.setId(configId);
        request.setValue(value);
        ZstackSysOsalNvWriteSrsp response = protocolHandler.sendTransaction(request, ZstackSysOsalNvWriteSrsp.class);
        if (response == null) {
            logger.debug("No response from WriteConfiguration command");
            return ZstackResponseCode.FAILURE;
        }
        logger.debug(response.toString());
        return response.getStatus();
    }

    public int readChannel() {
        ZstackSysNvReadSreq request = new ZstackSysNvReadSreq();
        request.setSysId(1);
        request.setItemId(0);
        request.setSubId(0x21);
        request.setOffset(24);
        request.setLength(1);
        ZstackSysNvReadSrsp response = protocolHandler.sendTransaction(request, ZstackSysNvReadSrsp.class);
        if (response == null || response.getValue() == null || response.getValue().length == 0) {
            return -1;
        } else {
            return response.getValue()[0];
        }
    }

    /**
     * Gets the network key.
     *
     * @return the {@link ZigBeeKey} returned from the NCP or null on error
     */
    public ZigBeeKey getNetworkKey() {
        int[] response = readConfiguration(ZstackConfigId.ZCD_NV_PRECFGKEY);
        if (response == null) {
            return null;
        }

        return new ZigBeeKey(response);
    }

    /**
     * Sets the transmit power to be used in the NCP
     *
     * @param txPower the TX power in dBm
     * @return the power returned from the NCP on success or null on error
     */
    public ZstackResponseCode setTxPower(int txPower) {
        ZstackSysSetTxPowerSreq request = new ZstackSysSetTxPowerSreq();
        request.setTxPower(txPower);
        ZstackSysSetTxPowerSrsp response = protocolHandler.sendTransaction(request, ZstackSysSetTxPowerSrsp.class);

        return response == null ? null : response.getStatus();
    }

    /**
     * Sets the channels that can be used in the NCP
     *
     * @param channelMask the {@link ZigBeeChannelMask} to set
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode setChannelMask(ZigBeeChannelMask channelMask) {
        final ZstackAppCnfBdbSetChannelSreq request = new ZstackAppCnfBdbSetChannelSreq();
        request.setIsPrimary(true);
        request.setChannels(channelMask.getChannelMask());
        ZstackAppCnfBdbSetChannelSrsp response = protocolHandler.sendTransaction(request, ZstackAppCnfBdbSetChannelSrsp.class);
        if (response == null) {
            logger.debug("No response from SetChannels command");
            return ZstackResponseCode.FAILURE;
        }
        logger.debug(response.toString());
        return response.getStatus();
    }

    /**
     * Starts the application on the NCP. This will put the NCP onto the network.
     *
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode startupApplication() {
        ZstackZdoStartupFromAppSreq request = new ZstackZdoStartupFromAppSreq();
        request.setStartDelay(100);
        ZstackZdoStartupFromAppSrsp response = protocolHandler.sendTransaction(request, ZstackZdoStartupFromAppSrsp.class);

        return response == null ? ZstackResponseCode.FAILURE : response.getStatus();
    }

    /**
     * Sets the policy flag on Trust Center device to mandate or not the TCLK exchange procedure.
     * <p>
     * APP_CNF_BDB_SET_TC_REQUIRE_KEY_EXCHANGE
     *
     * @param required true if the TCLK exchange procedure is required.
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode requireKeyExchange(boolean required) {
        ZstackAppCnfBdbSetTcRequireKeyExchangeSreq request = new ZstackAppCnfBdbSetTcRequireKeyExchangeSreq();
        request.setTrustCenterRequireKeyExchange(required);
        ZstackAppCnfBdbSetTcRequireKeyExchangeSrsp response = protocolHandler.sendTransaction(request, ZstackAppCnfBdbSetTcRequireKeyExchangeSrsp.class);

        return response == null ? ZstackResponseCode.FAILURE : response.getStatus();
    }

    /**
     * Sets the policy to mandate or not the usage of an Install Code upon joining.
     * <p>
     * APP_CNF_BDB_SET_ACTIVE_DEFAULT_CENTRALIZED_KEY
     *
     * @param mode the {@link ZstackCentralizedLinkKeyMode}
     * @param installCode array with the code in the required format
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode setCentralisedKey(ZstackCentralizedLinkKeyMode mode, int[] installCode) {
        ZstackAppCnfBdbSetActiveDefaultCentralizedKeySreq request = new ZstackAppCnfBdbSetActiveDefaultCentralizedKeySreq();
        request.setCentralizedLinkKeyMode(mode);
        request.setInstallCode(installCode);
        ZstackAppCnfBdbSetActiveDefaultCentralizedKeySrsp response = protocolHandler.sendTransaction(request, ZstackAppCnfBdbSetActiveDefaultCentralizedKeySrsp.class);

        return response == null ? ZstackResponseCode.FAILURE : response.getStatus();
    }

    /**
     * Sets the AllowRejoin TC policy.
     * <p>
     * APP_CNF_SET_ALLOWREJOIN_TC_POLICY
     *
     * @param allow true to allow rejoins
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode allowRejoin(boolean allow) {
        ZstackAppCnfSetAllowrejoinTcPolicySreq request = new ZstackAppCnfSetAllowrejoinTcPolicySreq();
        request.setAllowRejoin(allow);
        ZstackAppCnfSetAllowrejoinTcPolicySrsp response = protocolHandler.sendTransaction(request, ZstackAppCnfSetAllowrejoinTcPolicySrsp.class);

        return response == null ? ZstackResponseCode.FAILURE : response.getStatus();
    }

    /**
     * Registers for a ZDO callback
     *
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode zdoRegisterCallback(int clusterId) {
        ZstackZdoMsgCbRegisterSreq request = new ZstackZdoMsgCbRegisterSreq();
        request.setClusterId(clusterId);
        ZstackZdoMsgCbRegisterSrsp response = protocolHandler.sendTransaction(request, ZstackZdoMsgCbRegisterSrsp.class);

        return response == null ? ZstackResponseCode.FAILURE : response.getStatus();
    }

    /**
     * Registers an AF Endpoint in the NCP
     *
     * @param endpointId the endpoint number to add
     * @param deviceId the device id for the endpoint
     * @param profileId the profile id
     * @param inputClusters an array of input clusters supported by the endpoint
     * @param outputClusters an array of output clusters supported by the endpoint
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode addEndpoint(int endpointId, int deviceId, int profileId, int[] inputClusters, int[] outputClusters) {
        ZstackAfRegisterSreq request = new ZstackAfRegisterSreq();
        request.setEndPoint(endpointId);
        request.setAppDeviceId(deviceId);
        request.setAppProfId(profileId);
        request.setAppInClusterList(inputClusters);
        request.setAppOutClusterList(outputClusters);
        request.setLatencyReq(0);
        request.setAppDevVer(0);

        ZstackAfRegisterSrsp response = protocolHandler.sendTransaction(request, ZstackAfRegisterSrsp.class);

        return response == null ? ZstackResponseCode.FAILURE : response.getStatus();
    }

    /**
     * Adds an install code to the NCP. The code must be a ZigBee key - ie not the install code itself.
     *
     * @param ieeeAddress the {@link IeeeAddress} associated with the key
     * @param key the {@link ZigBeeKey} to set
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode addInstallCode(IeeeAddress ieeeAddress, ZigBeeKey key) {
        ZstackAppCnfBdbAddInstallcodeSreq request = new ZstackAppCnfBdbAddInstallcodeSreq();
        request.setInstallCodeFormat(ZstackInstallCodeFormat.DERIVED_KEY);
        request.setIeeeAddress(ieeeAddress);
        request.setInstallCode(key);

        ZstackAppCnfBdbAddInstallcodeSrsp response = protocolHandler.sendTransaction(request, ZstackAppCnfBdbAddInstallcodeSrsp.class);

        return response == null ? ZstackResponseCode.FAILURE : response.getStatus();
    }

    public int[] valueFromBoolean(boolean value) {
        return new int[] { value ? 1 : 0 };
    }

    public int[] valueFromUInt8(int value) {
        return new int[] { value };
    }

    public int[] valueFromUInt16(int value) {
        return new int[] { value & 0xFF, (value >> 8) & 0xFF };
    }

    public int[] valueFromUInt32(int value) {
        return new int[] { value & 0xFF, (value >> 8) & 0xFF, (value >> 16) & 0xFF, (value >> 24) & 0xFF };
    }
}
