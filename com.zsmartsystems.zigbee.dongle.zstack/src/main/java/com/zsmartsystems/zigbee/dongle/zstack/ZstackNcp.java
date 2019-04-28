/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackResponseCode;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfRegisterSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfRegisterSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbAddInstallcodeSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbAddInstallcodeSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetActiveDefaultCentralizedKeySreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetActiveDefaultCentralizedKeySrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetTcRequireKeyExchangeSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfBdbSetTcRequireKeyExchangeSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfSetAllowrejoinTcPolicySreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackAppCnfSetAllowrejoinTcPolicySrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackCentralizedLinkKeyMode;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackInstallCodeFormat;
import com.zsmartsystems.zigbee.dongle.zstack.api.sapi.ZstackZbReadConfigurationSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sapi.ZstackZbReadConfigurationSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sapi.ZstackZbWriteConfigurationSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sapi.ZstackZbWriteConfigurationSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackConfigId;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackDiagnosticAttribute;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackResetType;
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
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysZdiagsGetStatsSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysZdiagsGetStatsSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSystemCapabilities;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetDeviceInfoSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetDeviceInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetNvInfoSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetNvInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilSetChannelsSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilSetChannelsSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoMsgCbRegisterSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoMsgCbRegisterSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoSetLinkKeySreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoSetLinkKeySrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoStartupFromAppSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoStartupFromAppSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.internal.ZstackFrameHandler;
import com.zsmartsystems.zigbee.dongle.zstack.internal.ZstackProtocolHandler;
import com.zsmartsystems.zigbee.dongle.zstack.internal.transaction.ZstackSingleResponseTransaction;
import com.zsmartsystems.zigbee.dongle.zstack.internal.transaction.ZstackTransaction;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.transport.DeviceType;

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
     * Flag to use the old (deprecated) config functions
     */
    private boolean useOldCfgCalls = true;

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
     * @param protocolHandler the {@link ZstackFrameHandler} used for communicating with the NCP
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
        ZstackSysResetReqAcmd request = new ZstackSysResetReqAcmd();
        request.setType(resetType);
        ZstackTransaction transaction = protocolHandler
                .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackSysResetIndAreq.class));
        ZstackSysResetIndAreq response = (ZstackSysResetIndAreq) transaction.getResponse();
        if (response == null) {
            logger.debug("No response from Reset command");
            return null;
        }
        logger.debug(response.toString());

        return response;
    }

    /**
     * Ping the NCP and return the set of subsystem capabilities compiled into the NCP firmware
     *
     * @return the list of subsystem {@link ZstackSystemCapabilities} from the NCP, or an empty set if there was an
     *         error
     */
    public Set<ZstackSystemCapabilities> pingNcp() {
        ZstackSysPingSreq request = new ZstackSysPingSreq();
        ZstackTransaction transaction = protocolHandler
                .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackSysPingSrsp.class));
        ZstackSysPingSrsp response = (ZstackSysPingSrsp) transaction.getResponse();
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
        ZstackSysVersionSreq request = new ZstackSysVersionSreq();
        ZstackTransaction transaction = protocolHandler
                .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackSysVersionSrsp.class));
        ZstackSysVersionSrsp response = (ZstackSysVersionSrsp) transaction.getResponse();
        if (response == null) {
            logger.debug("No response from Version command");
            return null;
        }
        logger.debug(response.toString());

        return response;
    }

    /**
     * The command reads the device information from the NCP
     *
     * @return the {@link ZstackUtilGetDeviceInfoSrsp}
     */
    public ZstackUtilGetDeviceInfoSrsp getDeviceInfo() {
        ZstackUtilGetDeviceInfoSreq request = new ZstackUtilGetDeviceInfoSreq();
        ZstackTransaction transaction = protocolHandler
                .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackUtilGetDeviceInfoSrsp.class));
        ZstackUtilGetDeviceInfoSrsp response = (ZstackUtilGetDeviceInfoSrsp) transaction.getResponse();
        if (response == null) {
            logger.debug("No response from DeviceInfo command");
            return null;
        }
        logger.debug(response.toString());

        return response;
    }

    /**
     * The command reads the non-volatile device information from the NCP
     *
     * @return the {@link ZstackUtilGetDeviceInfoSrsp}
     */
    public ZstackUtilGetNvInfoSrsp getNvDeviceInfo() {
        ZstackUtilGetNvInfoSreq request = new ZstackUtilGetNvInfoSreq();
        ZstackTransaction transaction = protocolHandler
                .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackUtilGetNvInfoSrsp.class));
        ZstackUtilGetNvInfoSrsp response = (ZstackUtilGetNvInfoSrsp) transaction.getResponse();
        if (response == null) {
            logger.debug("No response from NvInfo command");
            return null;
        }
        logger.debug(response.toString());

        return response;
    }

    /**
     * Gets the {@link IeeeAddress} from the NCP
     *
     * @return the {@link IeeeAddress} local address of the NCP
     */
    public IeeeAddress getIeeeAddress() {
        ZstackUtilGetDeviceInfoSrsp info = getDeviceInfo();
        if (info == null) {
            return null;
        }
        return info.getIeeeAddress();
    }

    /**
     * Gets the current network address of the NCP
     *
     * @return the 16 bit local network address of the NCP or -1 if there was an error
     */
    public int getNwkAddress() {
        ZstackUtilGetDeviceInfoSrsp info = getDeviceInfo();
        if (info == null) {
            return -1;
        }
        return info.getShortAddr();
    }

    /**
     * Gets a diagnostics counter from the NCP
     *
     * @param attributeId {@ link ZstackDiagnosticAttribute} to request
     * @return the 32 bit counter, or null on error
     */
    public Long getDiagnosticsAttribute(ZstackDiagnosticAttribute attributeId) {
        ZstackSysZdiagsGetStatsSreq request = new ZstackSysZdiagsGetStatsSreq();
        request.setAttributeID(attributeId);
        ZstackTransaction transaction = protocolHandler
                .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackSysZdiagsGetStatsSrsp.class));
        ZstackSysZdiagsGetStatsSrsp response = (ZstackSysZdiagsGetStatsSrsp) transaction.getResponse();
        if (response == null) {
            logger.debug("No response from GetDiagnostics command");
            return null;
        }
        logger.debug(response.toString());

        return (long) response.getAttributeValue();
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
        if (useOldCfgCalls) {
            ZstackZbReadConfigurationSreq request = new ZstackZbReadConfigurationSreq();
            request.setConfigId(configId);
            ZstackTransaction transaction = protocolHandler
                    .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackZbReadConfigurationSrsp.class));
            ZstackZbReadConfigurationSrsp response = (ZstackZbReadConfigurationSrsp) transaction.getResponse();
            if (response == null) {
                logger.debug("No response from ReadConfiguration command");
                return null;
            }
            logger.debug(response.toString());
            if (response.getStatus() != ZstackResponseCode.SUCCESS) {
                logger.debug("No response from ReadConfiguration command: {}", response.getStatus());
                return null;
            }
            return response.getValue();
        } else {
            ZstackSysOsalNvReadSreq request = new ZstackSysOsalNvReadSreq();
            request.setId(configId);
            ZstackTransaction transaction = protocolHandler
                    .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackSysOsalNvReadSrsp.class));
            ZstackSysOsalNvReadSrsp response = (ZstackSysOsalNvReadSrsp) transaction.getResponse();
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
    }

    /**
     * Writes a configuration parameter to the NCP
     *
     * @param configId the {@link ZstackConfigId} to read
     * @param value an int array containing the value to be written
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode writeConfiguration(ZstackConfigId configId, int[] value) {
        if (useOldCfgCalls) {
            ZstackZbWriteConfigurationSreq request = new ZstackZbWriteConfigurationSreq();
            request.setConfigId(configId);
            request.setValue(value);
            ZstackTransaction transaction = protocolHandler.sendTransaction(
                    new ZstackSingleResponseTransaction(request, ZstackZbWriteConfigurationSrsp.class));
            ZstackZbWriteConfigurationSrsp response = (ZstackZbWriteConfigurationSrsp) transaction.getResponse();
            if (response == null) {
                logger.debug("No response from WriteConfiguration command");
                return ZstackResponseCode.FAILURE;
            }
            logger.debug(response.toString());
            return response.getStatus();
        } else {
            ZstackSysOsalNvWriteSreq request = new ZstackSysOsalNvWriteSreq();
            request.setId(configId);
            request.setValue(value);
            ZstackTransaction transaction = protocolHandler
                    .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackSysOsalNvWriteSrsp.class));
            ZstackSysOsalNvWriteSrsp response = (ZstackSysOsalNvWriteSrsp) transaction.getResponse();
            if (response == null) {
                logger.debug("No response from WriteConfiguration command");
                return ZstackResponseCode.FAILURE;
            }
            logger.debug(response.toString());
            return response.getStatus();
        }
    }

    /**
     * Sets the ZStack device type
     *
     * @param deviceType the {@link DeviceType} to use
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode setDeviceType(DeviceType deviceType) {
        int value;
        switch (deviceType) {
            case COORDINATOR:
                value = 0;
                break;
            case ROUTER:
                value = 1;
                break;
            default:
                logger.debug("Unknown device type {}", deviceType);
                return ZstackResponseCode.FAILURE;
        }
        return writeConfiguration(ZstackConfigId.ZCD_NV_LOGICAL_TYPE, valueFromUInt8(value));
    }

    /**
     * Sets the PAN ID
     *
     * @param panId the 16 bit PAN ID
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode setPanId(int panId) {
        return writeConfiguration(ZstackConfigId.ZCD_NV_PANID, valueFromUInt16(panId));
    }

    /**
     * Sets the extended PAN ID
     *
     * @param panId the {@link ExtendedPanId} extended PAN ID
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode setExtendedPanId(ExtendedPanId epanId) {
        return writeConfiguration(ZstackConfigId.ZCD_NV_EXTPANID, epanId.getValue());
    }

    /**
     * Sets the network key, and enables its use.
     *
     * @param key the {@link ZigBeeKey} to use for the network key
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode setNetworkKey(ZigBeeKey key) {
        ZstackResponseCode responseCode = writeConfiguration(ZstackConfigId.ZCD_NV_PRECFGKEY, key.getValue());
        if (responseCode != ZstackResponseCode.SUCCESS) {
            return responseCode;
        }
        // Enable the network key
        return writeConfiguration(ZstackConfigId.ZCD_NV_PRECFGKEYS_ENABLE, valueFromUInt8(1));
    }

    public ZstackResponseCode setTcLinkKey(ZigBeeKey key) {
        ZstackZdoSetLinkKeySreq request = new ZstackZdoSetLinkKeySreq();
        request.setIeeeAddr(new IeeeAddress("FFFFFFFFFFFFFFFF"));
        request.setLinkKeyData(key);
        ZstackTransaction transaction = protocolHandler
                .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackZdoSetLinkKeySrsp.class));
        ZstackZdoSetLinkKeySrsp response = (ZstackZdoSetLinkKeySrsp) transaction.getResponse();
        if (response == null) {
            logger.debug("No response from SetLinkKey command");
            return null;
        }
        logger.debug(response.toString());
        return response.getStatus();
    }

    public ZstackResponseCode setNetworkSecurity(boolean enableSecurity) {
        return writeConfiguration(ZstackConfigId.ZCD_NV_SECURITY_MODE, valueFromBoolean(enableSecurity));
    }

    /**
     * Sets the transmit power to be used in the NCP
     *
     * @param txPower the TX power in dBm
     * @return the power returned from the NCP on success or null on error
     */
    public Integer setTxPower(int txPower) {
        ZstackSysSetTxPowerSreq request = new ZstackSysSetTxPowerSreq();
        request.setTxPower(txPower);
        ZstackTransaction transaction = protocolHandler
                .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackSysSetTxPowerSrsp.class));
        ZstackSysSetTxPowerSrsp response = (ZstackSysSetTxPowerSrsp) transaction.getResponse();
        if (response == null) {
            logger.debug("No response from SetTxPower command");
            return null;
        }
        logger.debug(response.toString());
        return response.getTxPower();
    }

    /**
     * Sets the channels that can be used in the NCP
     *
     * @param channelMask the {@link ZigBeeChannelMask} to set
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode setChannelMask(ZigBeeChannelMask channelMask) {
        // TODO: ZCD_NV_CHANLIST
        ZstackUtilSetChannelsSreq request = new ZstackUtilSetChannelsSreq();
        request.setChannels(channelMask.getChannelMask());
        ZstackTransaction transaction = protocolHandler
                .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackUtilSetChannelsSrsp.class));
        ZstackUtilSetChannelsSrsp response = (ZstackUtilSetChannelsSrsp) transaction.getResponse();
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
        ZstackTransaction transaction = protocolHandler
                .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackZdoStartupFromAppSrsp.class));
        ZstackZdoStartupFromAppSrsp response = (ZstackZdoStartupFromAppSrsp) transaction.getResponse();
        if (response == null) {
            logger.debug("No response from startupApplication command");
            return ZstackResponseCode.FAILURE;
        }
        logger.debug(response.toString());
        return response.getStatus();
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
        ZstackTransaction transaction = protocolHandler.sendTransaction(
                new ZstackSingleResponseTransaction(request, ZstackAppCnfBdbSetTcRequireKeyExchangeSrsp.class));
        ZstackAppCnfBdbSetTcRequireKeyExchangeSrsp response = (ZstackAppCnfBdbSetTcRequireKeyExchangeSrsp) transaction
                .getResponse();
        if (response == null) {
            logger.debug("No response from requireKeyExchange command");
            return ZstackResponseCode.FAILURE;
        }
        logger.debug(response.toString());
        return response.getStatus();
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
        ZstackTransaction transaction = protocolHandler.sendTransaction(
                new ZstackSingleResponseTransaction(request, ZstackAppCnfBdbSetActiveDefaultCentralizedKeySrsp.class));
        ZstackAppCnfBdbSetActiveDefaultCentralizedKeySrsp response = (ZstackAppCnfBdbSetActiveDefaultCentralizedKeySrsp) transaction
                .getResponse();
        if (response == null) {
            logger.debug("No response from setCentralisedKey command");
            return ZstackResponseCode.FAILURE;
        }
        logger.debug(response.toString());
        return response.getStatus();
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
        ZstackTransaction transaction = protocolHandler.sendTransaction(
                new ZstackSingleResponseTransaction(request, ZstackAppCnfSetAllowrejoinTcPolicySrsp.class));
        ZstackAppCnfSetAllowrejoinTcPolicySrsp response = (ZstackAppCnfSetAllowrejoinTcPolicySrsp) transaction
                .getResponse();
        if (response == null) {
            logger.debug("No response from allowRejoin command");
            return ZstackResponseCode.FAILURE;
        }
        logger.debug(response.toString());
        return response.getStatus();
    }

    /**
     * Registers for a ZDO callback
     *
     * @return {@link ZstackResponseCode} returned from the NCP
     */
    public ZstackResponseCode zdoRegisterCallback(int clusterId) {
        ZstackZdoMsgCbRegisterSreq request = new ZstackZdoMsgCbRegisterSreq();
        request.setClusterId(clusterId);
        ZstackTransaction transaction = protocolHandler
                .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackZdoMsgCbRegisterSrsp.class));
        ZstackZdoMsgCbRegisterSrsp response = (ZstackZdoMsgCbRegisterSrsp) transaction.getResponse();
        if (response == null) {
            logger.debug("No response from RegisterZdoCallback command");
            return ZstackResponseCode.FAILURE;
        }
        logger.debug(response.toString());
        return response.getStatus();
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
    public ZstackResponseCode addEndpoint(int endpointId, int deviceId, int profileId, int[] inputClusters,
            int[] outputClusters) {
        ZstackAfRegisterSreq request = new ZstackAfRegisterSreq();
        request.setEndPoint(endpointId);
        request.setAppDeviceId(deviceId);
        request.setAppProfId(profileId);
        request.setAppInClusterList(inputClusters);
        request.setAppOutClusterList(outputClusters);
        request.setLatencyReq(0);
        request.setAppDevVer(0);

        ZstackTransaction transaction = protocolHandler
                .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackAfRegisterSrsp.class));
        ZstackAfRegisterSrsp response = (ZstackAfRegisterSrsp) transaction.getResponse();
        if (response == null) {
            logger.debug("No response from RegisterEndpoint command");
            return ZstackResponseCode.FAILURE;
        }
        logger.debug(response.toString());
        return response.getStatus();
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

        ZstackTransaction transaction = protocolHandler
                .sendTransaction(new ZstackSingleResponseTransaction(request, ZstackAppCnfBdbAddInstallcodeSrsp.class));
        ZstackAppCnfBdbAddInstallcodeSrsp response = (ZstackAppCnfBdbAddInstallcodeSrsp) transaction.getResponse();
        if (response == null) {
            logger.debug("No response from AddInstallCode command");
            return ZstackResponseCode.FAILURE;
        }
        logger.debug(response.toString());
        return response.getStatus();
    }

    private int[] valueFromBoolean(boolean value) {
        return new int[] { value ? 1 : 0 };
    }

    private int[] valueFromUInt8(int value) {
        return new int[] { value };
    }

    private int[] valueFromUInt16(int value) {
        return new int[] { value & 0xFF, (value >> 8) & 0xFF };
    }

}
