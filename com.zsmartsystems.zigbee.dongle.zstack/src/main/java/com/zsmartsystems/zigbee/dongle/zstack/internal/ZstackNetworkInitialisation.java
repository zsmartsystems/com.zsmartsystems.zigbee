/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.internal;

import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.dongle.zstack.ZstackNcp;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameResponse;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackResponseCode;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysResetIndAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackZdoState;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetDeviceInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoStateChangeIndAreq;
import com.zsmartsystems.zigbee.transport.DeviceType;
import com.zsmartsystems.zigbee.transport.ZigBeePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * This class provides utility functions to establish a ZStack ZigBee network
 *
 * @author Chris Jackson
 *
 */
public class ZstackNetworkInitialisation {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZstackNetworkInitialisation.class);

    /**
     * Number of milliseconds to wait for the bootloader to exit
     */
    public static final int BOOTLOAD_TIMEOUT = 3000;

    /**
     * Number of milliseconds to wait for the NCP to come online
     */
    public static final int ONLINE_TIMEOUT = 5000;

    /**
     * The default magic number used to make the dongle exit the bootloader
     */
    public static final int MAGIC_NUMBER_DEFAULT = 0xEF;

    /**
     * The frame handler used to send the ZStack frames to the NCP
     */
    private ZstackProtocolHandler protocolHandler;

    /**
     * The magic number used to make the dongle exit the bootloader
     */
    private int magicNumber = MAGIC_NUMBER_DEFAULT;

    /**
     * @param protocolHandler the {@link ZstackProtocolHandler} used to communicate with the NCP
     */
    public ZstackNetworkInitialisation(ZstackProtocolHandler protocolHandler) {
        this.protocolHandler = protocolHandler;
    }

    /**
     * Different hardware may use a different "Magic Number" to skip waiting in the bootloader. Otherwise
     * the dongle may wait in the bootloader for 60 seconds after it's powered on or reset.
     * <p>
     * This method allows the user to change the magic number which may be required when using different
     * sticks.
     *
     * @param magicNumber
     */
    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    /**
     * This method performs the initial initialisation of the dongle application firmware. This simply starts the
     * dongle, ensuring that it enters the application (ie exiting the bootloader) and sets the configuration to use the
     * defaults. From here we have a known configuration on which to start our session.
     * <p>
     * The dongle is not reset completely, thus allowing it to be placed back into the previous network.
     *
     * @param initialize set to true to reset the dongle and erase all current network settings
     * @param serialPort
     */
    public ZigBeeStatus initializeNcp(boolean initialize, ZigBeePort serialPort) {
        logger.debug("ZStack Initialisation: Initialise");
        ZstackNcp ncp = new ZstackNcp(protocolHandler);

        // The reset failed - assume we're in the bootloader and try and exit
        if (exitBootloader(serialPort) == false) {
            logger.debug("ZStack Initialisation: Failed to exit bootloader");
            return ZigBeeStatus.COMMUNICATION_ERROR;
        }

        if (initialize) {
            return resetNcp(ncp, true);
        }
        return ZigBeeStatus.SUCCESS;
    }

    /**
     * Form a new network as the coordinator.
     * <p>
     * This assumes that the network configuration parameters have been set prior to calling
     *
     * @return {@link ZigBeeStatus} defining the result
     */
    public ZigBeeStatus formNetwork() {
        logger.debug("ZStack forming new network");
        ZstackNcp ncp = new ZstackNcp(protocolHandler);

        if (ncp.setDeviceType(DeviceType.COORDINATOR) != ZstackResponseCode.SUCCESS) {
            logger.debug("ZStack forming network: Error setting NCP to coordinator");
            return ZigBeeStatus.COMMUNICATION_ERROR;
        }

        return ZigBeeStatus.SUCCESS;
    }

    /**
     * Join an existing network as a Router
     * <p>
     * This assumes that the network configuration parameters have been set prior to calling
     *
     * @return {@link ZigBeeStatus} defining the result
     */
    public ZigBeeStatus joinNetwork() {
        logger.debug("ZStack joining new network");
        ZstackNcp ncp = new ZstackNcp(protocolHandler);

        if (ncp.setDeviceType(DeviceType.ROUTER) != ZstackResponseCode.SUCCESS) {
            logger.debug("ZStack forming network: Error setting NCP to router");
            return ZigBeeStatus.COMMUNICATION_ERROR;
        }

        return ZigBeeStatus.SUCCESS;
    }

    /**
     * Starts the NCP application, placing it on the network as previously configured.
     *
     * @return {@link ZigBeeStatus} defining the result
     */
    public ZigBeeStatus startNetwork() {
        logger.debug("ZStack starting network");
        ZstackNcp ncp = new ZstackNcp(protocolHandler);

        ncp.setNetworkSecurity(true);

        ncp.zdoRegisterCallback(0x0006);// MatchDescriptorRequest
        ncp.zdoRegisterCallback(0x0013);// DeviceAnnounce
        ncp.zdoRegisterCallback(0x0036);// ManagementPermitJoiningRequest
        ncp.zdoRegisterCallback(0x8000);// NetworkAddressResponse() {
        ncp.zdoRegisterCallback(0x8001);// IeeeAddressResponse() {
        ncp.zdoRegisterCallback(0x8002);// NodeDescriptorResponse.);
        ncp.zdoRegisterCallback(0x8003);// PowerDescriptorResponse
        ncp.zdoRegisterCallback(0x8004);// SimpleDescriptorResponse
        ncp.zdoRegisterCallback(0x8005);// ActiveEndpointsResponse
        ncp.zdoRegisterCallback(0x8006);// MatchDescriptorResponse
        ncp.zdoRegisterCallback(0x8011);// UserDescriptorResponse
        ncp.zdoRegisterCallback(0x8020);// EndDeviceBindResponse
        ncp.zdoRegisterCallback(0x8021);// BindResponse
        ncp.zdoRegisterCallback(0x8022);// UnbindResponse
        ncp.zdoRegisterCallback(0x8031);// ManagementLqiResponse
        ncp.zdoRegisterCallback(0x8032);// ManagementRoutingResponse
        ncp.zdoRegisterCallback(0x8033);// ManagementBindResponse
        ncp.zdoRegisterCallback(0x8034);// ManagementLeaveResponse
        ncp.zdoRegisterCallback(0x8036);// ManagementPermitJoiningResponse

        // Now start the NCP
        if (ncp.startupApplication() != ZstackResponseCode.SUCCESS) {
            return ZigBeeStatus.COMMUNICATION_ERROR;
        }

        return ZigBeeStatus.SUCCESS;
    }

    /**
     * Attempts to exit the bootloader by sending the "magic number" and waiting for the {@link ZstackSysResetIndAreq}
     * to be received to confirm that the NCP application firmware has started.
     *
     * https://www.ti.com/lit/an/swra466d/swra466d.pdf
     * https://www.ti.com/lit/ug/swcu185d/swcu185d.pdf
     *
     * @return true if the {@link ZstackSysResetIndAreq} was received, otherwise false
     * @param serialPort Serial port
     */
    private boolean exitBootloader(ZigBeePort serialPort) {
        // FIXME this does not work in the OpenHAB binding, as it seems their write() implementation blocks
        /*
        protocolHandler.sendRaw(magicNumber);
        if (waitForBoot("Magicnumber")) {
            return true;
        }
         */

        serialPort.setDtr(false);

        serialPort.setRts(false);
        serialPort.setRts(true);
        serialPort.setRts(false);

        return waitForBoot("Hardware reset");
    }

    private boolean waitForBoot(String resetMode) {
        Future<ZstackFrameResponse> waiter = protocolHandler.waitForEvent(ZstackSysResetIndAreq.class);
        try {
            ZstackFrameResponse response = waiter.get(BOOTLOAD_TIMEOUT, TimeUnit.MILLISECONDS);
            logger.debug("ZStack Initialisation: Bootloader reset via {} response {}", resetMode, response);
            return true;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            logger.debug("ZStack Initialisation: Bootloader reset via {} failed", resetMode, e);
            return false;
        }
    }

    /**
     * Resets the NCP and optionally clears the existing network information.
     *
     * @param ncp the {@link ZstackNcp}
     * @param initialise true to remove all current network information
     * @return a {@link ZigBeeStatus} defining the success of reason for failure
     */
    private ZigBeeStatus resetNcp(ZstackNcp ncp, boolean initialise) {
        // Ensure the start options are set to reset the configuration to defaults so we start in a known config
        if (ncp.setStartupOptions(true, initialise) != ZstackResponseCode.SUCCESS) {
            logger.debug("ZStack Initialisation: Failed to set startup options");
            return ZigBeeStatus.COMMUNICATION_ERROR;
        }

        return ZigBeeStatus.SUCCESS;
    }

    /**
     * Waits for the NCP to come online.
     *
     * @param ncp the {@link ZstackNcp}
     * @return true if the NCP is online, false if the method times out while waiting
     */
    public boolean waitForNcpOnline(ZstackNcp ncp) {
        long waitTime = System.currentTimeMillis() + ONLINE_TIMEOUT;
        while (waitTime > System.currentTimeMillis()) {
            logger.debug("ZStack dongle waiting for NCP to come online");
            Future<ZstackFrameResponse> stateChangeFuture = protocolHandler
                    .waitForEvent(ZstackZdoStateChangeIndAreq.class);
            try {
                ZstackZdoStateChangeIndAreq stateChange = (ZstackZdoStateChangeIndAreq) stateChangeFuture.get(500,
                        TimeUnit.MILLISECONDS);
                if (isStackOnline(stateChange.getState())) {
                    return true;
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                // Eat this exception
            }
        }

        // It's possible to get here with the NCP being online if we miss the event while we weren't listening
        // Do one last check
        ZstackUtilGetDeviceInfoSrsp deviceInfo = ncp.getDeviceInfo();
        return isStackOnline(deviceInfo.getDeviceState());
    }

    private boolean isStackOnline(ZstackZdoState state) {
        switch (state) {
            case DEV_NWK_ORPHAN:
            case DEV_ROUTER:
            case DEV_ZB_COORD:
            case DEV_END_DEVICE:
                // NCP is now on the network
                return true;
            default:
                return false;
        }
    }
}
