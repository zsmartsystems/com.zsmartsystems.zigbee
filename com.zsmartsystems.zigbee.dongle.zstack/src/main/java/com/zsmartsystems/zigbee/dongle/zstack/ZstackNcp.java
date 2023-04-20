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

import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackResponseCode;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfRegisterSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfRegisterSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetActiveDefaultCentralizedKeySreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetActiveDefaultCentralizedKeySrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetChannelSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetChannelSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackCentralizedLinkKeyMode;
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
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysVersionSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysVersionSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSystemCapabilities;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetDeviceInfoSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetDeviceInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoExtNwkInfoSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoExtNwkInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoMsgCbRegisterSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoMsgCbRegisterSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoStartupFromAppSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoStartupFromAppSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.internal.ZstackProtocolHandler;

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

    /**
     * Reads the current logical channel from non volatile memory. It is in the configuration ID for the NIB.
     */
    public ZigBeeChannel readChannelFromNV() {
        ZstackSysNvReadSreq request = new ZstackSysNvReadSreq();
        request.setSysId(1);
        request.setItemId(0);
        request.setSubId(0x21);
        request.setOffset(24);
        request.setLength(1);
        ZstackSysNvReadSrsp response = protocolHandler.sendTransaction(request, ZstackSysNvReadSrsp.class);
        if (response == null || response.getValue() == null || response.getValue().length == 0) {
            return ZigBeeChannel.UNKNOWN;
        } else {
            return ZigBeeChannel.create(response.getValue()[0]);
        }
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

    public ZstackSysVersionSrsp getVersion() {
        return protocolHandler.sendTransaction(new ZstackSysVersionSreq(), ZstackSysVersionSrsp.class);
    }

    public ZstackUtilGetDeviceInfoSrsp getDeviceInfo() {
        return protocolHandler.sendTransaction(new ZstackUtilGetDeviceInfoSreq(), ZstackUtilGetDeviceInfoSrsp.class);
    }

    public ZstackZdoExtNwkInfoSrsp getNetworkInfo() {
        return protocolHandler.sendTransaction(new ZstackZdoExtNwkInfoSreq(), ZstackZdoExtNwkInfoSrsp.class);
    }
}
