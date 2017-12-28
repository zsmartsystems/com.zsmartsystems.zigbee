/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
   Copyright 2008-2013 CNR-ISTI, http://isti.cnr.it
   Institute of Information Science and Technologies
   of the Italian National Research Council


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
package com.zsmartsystems.zigbee.dongle.cc2531.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.dongle.cc2531.network.impl.BlockingCommandReceiver;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ResponseStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_CONFIRM;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_REQUEST;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_REQUEST_EXT;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_SRSP_EXT;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_INCOMING_MSG;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_REGISTER;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_REGISTER_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.simple.ZB_GET_DEVICE_INFO;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.simple.ZB_GET_DEVICE_INFO_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.simple.ZB_READ_CONFIGURATION;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.simple.ZB_READ_CONFIGURATION_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.simple.ZB_WRITE_CONFIGURATION;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.simple.ZB_WRITE_CONFIGURATION_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.system.SYS_RESET;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.system.SYS_RESET_RESPONSE;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.system.SYS_VERSION;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.system.SYS_VERSION_RESPONSE;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MSG_CB_REGISTER;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MSG_CB_REGISTER_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_STARTUP_FROM_APP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_STARTUP_FROM_APP_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_STATE_CHANGE_IND;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.Integers;

/**
 * The ZigBee network manager implementation.
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:michele.girolami@isti.cnr.it">Michele Girolami</a>
 * @author <a href="mailto:manlio.bacco@isti.cnr.it">Manlio Bacco</a>
 * @author <a href="mailto:tommi.s.e.laukkanen@gmail.com">Tommi S.E. Laukkanen</a>
 * @author <a href="mailto:christopherhattonuk@gmail.com">Chris Hatton</a>
 * @author <a href="mailto:chris@cd-jackson.com">Chris Jackson</a>
 */
public class ZigBeeNetworkManager {

    private final Logger logger = LoggerFactory.getLogger(ZigBeeNetworkManager.class);

    private static final int DEFAULT_TIMEOUT = 8000;
    private static final String TIMEOUT_KEY = "zigbee.driver.cc2531.timeout";

    private static final int RESET_TIMEOUT_DEFAULT = 5000;
    private static final String RESET_TIMEOUT_KEY = "zigbee.driver.cc2531.reset.timeout";

    private static final int STARTUP_TIMEOUT_DEFAULT = 10000;
    private static final String STARTUP_TIMEOUT_KEY = "zigbee.driver.cc2531.startup.timeout";

    private static final int RESEND_TIMEOUT_DEFAULT = 1000;

    private static final int RESEND_MAX_RETRY_DEFAULT = 3;

    private static final boolean RESEND_ONLY_EXCEPTION_DEFAULT = true;
    private static final String RESEND_ONLY_EXCEPTION_KEY = "zigbee.driver.cc2531.resend.exceptionally";

    private static final int BOOTLOADER_MAGIC_BYTE_DEFAULT = 0xef;

    private final int TIMEOUT;
    private final int RESET_TIMEOUT;
    private final int STARTUP_TIMEOUT;
    private final boolean RESEND_ONLY_EXCEPTION;

    private int BOOTLOADER_MAGIC_BYTE = BOOTLOADER_MAGIC_BYTE_DEFAULT;
    private int RESEND_TIMEOUT = RESEND_TIMEOUT_DEFAULT;
    private int RESEND_MAX_RETRY = RESEND_MAX_RETRY_DEFAULT;

    private final int ZNP_DEFAULT_CHANNEL = 0x00000800;

    private final int ZNP_CHANNEL_MASK0 = 0x00;
    private final int ZNP_CHANNEL_MASK1 = 0xf8;
    private final int ZNP_CHANNEL_MASK2 = 0xff;
    private final int ZNP_CHANNEL_MASK3 = 0x0f;

    private final int ZNP_CHANNEL_DEFAULT0 = 0x00;
    private final int ZNP_CHANNEL_DEFAULT1 = 0x08;
    private final int ZNP_CHANNEL_DEFAULT2 = 0x00;
    private final int ZNP_CHANNEL_DEFAULT3 = 0x00;

    // Dongle startup options
    private final int STARTOPT_CLEAR_CONFIG = 0x00000001;
    private final int STARTOPT_CLEAR_STATE = 0x00000002;

    // The dongle will automatically pickup a random, not conflicting PAN ID
    private final short AUTO_PANID = (short) 0xffff;

    private CommandInterface commandInterface;
    private DriverStatus state;
    private NetworkMode mode;
    private int pan = AUTO_PANID;
    private int channel = ZNP_DEFAULT_CHANNEL;
    private ExtendedPanId extendedPanId; // do not initialize to use dongle defaults (the IEEE address)
    private byte[] networkKey; // 16 byte network key
    private boolean distributeNetworkKey = true; // distribute network key in clear (be careful)
    private int securityMode = 1; // int for future extensibility

    private int[] ep;
    private int[] prof;
    private int[] dev;
    private int[] ver;
    private short[][] inp;
    private short[][] out;

    private final NetworkStateListener announceListenerFilter = new NetworkStateListener();

    private final ArrayList<ApplicationFrameworkMessageListener> messageListeners = new ArrayList<ApplicationFrameworkMessageListener>();
    private final AFMessageListenerFilter afMessageListenerFilter = new AFMessageListenerFilter(messageListeners);

    /**
     * The cached current IEEE address read from dongle.
     */
    private long ieeeAddress = -1;
    /**
     * The cached current PAN id read from dongle.
     */
    private int currentPanId = -1;

    private final HashMap<Class<?>, Thread> conversation3Way = new HashMap<Class<?>, Thread>();

    public ZigBeeNetworkManager(CommandInterface commandInterface, NetworkMode mode, long timeout) {
        this.mode = mode;
        this.commandInterface = commandInterface;

        int aux;

        aux = (int) Math.max(DEFAULT_TIMEOUT, timeout);
        try {
            aux = Integer.parseInt(System.getProperty(TIMEOUT_KEY));
            logger.trace("Using TIMEOUT set from enviroment {}", aux);
        } catch (NumberFormatException ex) {
            logger.trace("Using TIMEOUT set as DEFAULT {}ms", aux);
        }
        TIMEOUT = aux;

        aux = (int) Math.max(RESET_TIMEOUT_DEFAULT, timeout);
        try {
            aux = Integer.parseInt(System.getProperty(RESET_TIMEOUT_KEY));
            logger.trace("Using RESET_TIMEOUT set from enviroment {}", aux);
        } catch (NumberFormatException ex) {
            logger.trace("Using RESET_TIMEOUT set as DEFAULT {}ms", aux);
        }
        RESET_TIMEOUT = aux;

        aux = (int) Math.max(STARTUP_TIMEOUT_DEFAULT, timeout);
        try {
            aux = Integer.parseInt(System.getProperty(STARTUP_TIMEOUT_KEY));
            logger.trace("Using STARTUP_TIMEOUT set from enviroment {}", aux);
        } catch (NumberFormatException ex) {
            logger.trace("Using STARTUP_TIMEOUT set as DEFAULT {}ms", aux);
        }
        STARTUP_TIMEOUT = aux;

        boolean b = RESEND_ONLY_EXCEPTION_DEFAULT;
        try {
            b = Boolean.parseBoolean(System.getProperty(RESEND_ONLY_EXCEPTION_KEY));
            logger.trace("Using RESEND_ONLY_EXCEPTION set from enviroment {}", aux);
        } catch (NumberFormatException ex) {
            logger.trace("Using RESEND_ONLY_EXCEPTION set as DEFAULT {}", aux);
        }
        RESEND_ONLY_EXCEPTION = b;

        state = DriverStatus.CLOSED;
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
        BOOTLOADER_MAGIC_BYTE = magicNumber;
    }

    /**
     * Set timeout and retry count
     *
     * @param retries the maximum number of retries to perform
     * @param timeout the maximum timeout between retries
     */
    public void setRetryConfiguration(int retries, int timeout) {
        RESEND_MAX_RETRY = retries;
        if (RESEND_MAX_RETRY < 1 || RESEND_MAX_RETRY > 5) {
            RESEND_MAX_RETRY = RESEND_MAX_RETRY_DEFAULT;
        }

        RESEND_TIMEOUT = timeout;
        if (RESEND_TIMEOUT < 0 || RESEND_TIMEOUT > 5000) {
            RESEND_TIMEOUT = RESEND_TIMEOUT_DEFAULT;
        }
    }

    public String startup() {
        // Called when the network is first started
        if (state != DriverStatus.CLOSED) {
            throw new IllegalStateException("Driver already opened, current status is:" + state);
        }

        state = DriverStatus.CREATED;
        logger.trace("Initializing hardware.");

        // Open the hardware port
        setState(DriverStatus.HARDWARE_INITIALIZING);
        if (!initializeHardware()) {
            shutdown();
            return null;
        }

        // Now reset the dongle
        setState(DriverStatus.HARDWARE_OPEN);
        if (!dongleReset()) {
            logger.warn("Dongle reset failed. Assuming bootloader is running and sending magic byte {}.",
                    String.format("0x%02x", BOOTLOADER_MAGIC_BYTE));
            if (!bootloaderGetOut(BOOTLOADER_MAGIC_BYTE)) {
                logger.warn("Attempt to get out from bootloader failed.");
                shutdown();
                return null;
            }
        }

        setState(DriverStatus.HARDWARE_READY);

        String version = getStackVersion();
        if (version == null) {
            logger.debug("Failed to get CC2531 version");

        } else {
            logger.debug("CC2531 version is {}", version);
        }

        return version;
    }

    public void shutdown() {
        if (state == DriverStatus.CLOSED) {
            logger.debug("Already CLOSED");
            return;
        }
        if (state == DriverStatus.NETWORK_READY) {
            logger.trace("Closing NETWORK");
            setState(DriverStatus.HARDWARE_READY);
        }
        if (state == DriverStatus.HARDWARE_OPEN || state == DriverStatus.HARDWARE_READY
                || state == DriverStatus.NETWORK_INITIALIZING) {
            logger.trace("Closing HARDWARE");
            commandInterface.close();
            setState(DriverStatus.CREATED);
        }
        setState(DriverStatus.CLOSED);
    }

    private boolean initializeHardware() {
        if (commandInterface == null) {
            logger.error("Command interface must be configured");
            return false;
        }

        if (!commandInterface.open()) {
            logger.error("Failed to open the dongle.");
            return false;
        }

        return true;
    }

    public boolean initializeZigBeeNetwork(boolean cleanStatus) {
        logger.trace("Initializing network.");

        setState(DriverStatus.NETWORK_INITIALIZING);

        if (cleanStatus && !configureZigBeeNetwork()) {
            shutdown();
            return false;
        }

        if (!createZigBeeNetwork()) {
            logger.error("Failed to start zigbee network.");
            shutdown();
            return false;
        }
        // if (checkZigBeeNetworkConfiguration()) {
        // logger.error("Dongle configuration does not match the specified configuration.");
        // shutdown();
        // return false;
        // }
        return true;
    }

    private boolean createZigBeeNetwork() {
        createCustomDevicesOnDongle();
        logger.debug("Creating network as {}", mode.toString());

        final int ALL_CLUSTERS = 0xFFFF;

        logger.trace("Reset seq: Trying MSG_CB_REGISTER");
        ZDO_MSG_CB_REGISTER_SRSP responseCb = (ZDO_MSG_CB_REGISTER_SRSP) sendSynchronous(
                new ZDO_MSG_CB_REGISTER(new DoubleByte(ALL_CLUSTERS)));
        if (responseCb == null) {
            return false;
        }

        ZB_WRITE_CONFIGURATION_RSP responseCfg;
        responseCfg = (ZB_WRITE_CONFIGURATION_RSP) sendSynchronous(
                new ZB_WRITE_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_ZDO_DIRECT_CB, new int[] { 1 }));
        if (responseCfg == null) {
            return false;
        }

        final int instantStartup = 0;

        ZDO_STARTUP_FROM_APP_SRSP response = (ZDO_STARTUP_FROM_APP_SRSP) sendSynchronous(
                new ZDO_STARTUP_FROM_APP(instantStartup), STARTUP_TIMEOUT);
        if (response == null) {
            return false;
        }

        switch (response.Status) {
            case 0: {
                logger.info("Initialized ZigBee network with existing network state.");
                return true;
            }
            case 1: {
                logger.info("Initialized ZigBee network with new or reset network state.");
                return true;
            }
            case 2: {
                logger.warn("Initializing ZigBee network failed.");
                return false;
            }
            default: {
                logger.error("Unexpected response state for ZDO_STARTUP_FROM_APP {}", response.Status);
                return false;
            }
        }
    }

    private boolean configureZigBeeNetwork() {
        logger.debug("Resetting network stack.");

        // Make sure we start clearing configuration and state
        if (!dongleSetStartupOption(STARTOPT_CLEAR_CONFIG | STARTOPT_CLEAR_STATE)) {
            logger.error("Unable to set clean state for dongle");
            return false;
        }
        logger.debug("Changing the Network Mode to {}.", mode);
        if (!dongleSetNetworkMode()) {
            logger.error("Unable to set NETWORK_MODE for ZigBee Network");
            return false;
        } else {
            logger.trace("NETWORK_MODE set");
        }
        // A dongle reset is needed to put into effect
        // configuration clear and network mode.
        logger.debug("Resetting CC2531 dongle.");
        if (!dongleReset()) {
            logger.error("Unable to reset dongle");
            return false;
        }

        logger.debug("Setting channel to {}.", channel);
        if (!dongleSetChannel()) {
            logger.error("Unable to set CHANNEL for ZigBee Network");
            return false;
        } else {
            logger.trace("CHANNEL set");
        }

        logger.debug("Setting PAN to {}.", String.format("%04X", pan & 0x0000ffff));
        if (!dongleSetPanId()) {
            logger.error("Unable to set PANID for ZigBee Network");
            return false;
        } else {
            logger.trace("PANID set");
        }
        if (extendedPanId != null) {
            logger.debug("Setting Extended PAN ID to {}.", extendedPanId);
            if (!dongleSetExtendedPanId()) {
                logger.error("Unable to set EXT_PANID for ZigBee Network");
                return false;
            } else {
                logger.trace("EXT_PANID set");
            }
        }
        if (networkKey != null) {
            logger.debug("Setting NETWORK_KEY.");
            if (!dongleSetNetworkKey()) {
                logger.error("Unable to set NETWORK_KEY for ZigBee Network");
                return false;
            } else {
                logger.trace("NETWORK_KEY set");
            }
        }
        logger.debug("Setting Distribute Network Key to {}.", distributeNetworkKey);
        if (!dongleSetDistributeNetworkKey()) {
            logger.error("Unable to set DISTRIBUTE_NETWORK_KEY for ZigBee Network");
            return false;
        } else {
            logger.trace("DISTRIBUTE_NETWORK_KEY set");
        }
        logger.debug("Setting Security Mode to {}.", securityMode);
        if (!dongleSetSecurityMode()) {
            logger.error("Unable to set SECURITY_MODE for ZigBee Network");
            return false;
        } else {
            logger.trace("SECURITY_MODE set");
        }
        return true;
    }

    private void setState(DriverStatus value) {
        logger.trace("{} -> {}", state, value);
        synchronized (this) {
            state = value;
            notifyAll();
        }
        if (state == DriverStatus.HARDWARE_READY) {
            postHardwareEnabled();
        }
    }

    private void postHardwareEnabled() {
        if (!messageListeners.contains(afMessageListenerFilter)) {
            commandInterface.addAsynchronousCommandListener(afMessageListenerFilter);
        }
        // if (!announceListeners.contains(announceListenerFilter)) {
        commandInterface.addAsynchronousCommandListener(announceListenerFilter);
        // }
    }

    private boolean waitForHardware() {
        synchronized (this) {
            while (state == DriverStatus.CREATED || state == DriverStatus.CLOSED) {
                logger.debug("Waiting for hardware to become ready");
                try {
                    wait();
                } catch (InterruptedException ignored) {
                }
            }
            return isHardwareReady();
        }
    }

    private boolean waitForNetwork() {
        long before = System.currentTimeMillis();
        boolean timedOut = false;
        synchronized (this) {
            while (state != DriverStatus.NETWORK_READY && state != DriverStatus.CLOSED && !timedOut) {
                logger.debug("Waiting for network to become ready");
                try {
                    long now = System.currentTimeMillis();
                    long timeout = STARTUP_TIMEOUT - (now - before);
                    if (timeout > 0) {
                        wait(timeout);
                    } else {
                        timedOut = true;
                    }
                } catch (InterruptedException ignored) {
                }

            }
            return isNetworkReady();
        }
    }

    public void setZigBeeNodeMode(NetworkMode networkMode) {
        mode = networkMode;
    }

    public void setZigBeeNetworkKey(byte[] networkKey) {
        this.networkKey = networkKey;
        dongleSetNetworkKey();
    }

    public boolean setZigBeePanId(int panId) {
        pan = panId;

        return dongleSetPanId();
    }

    public boolean setZigBeeChannel(int channel) {
        this.channel = channel;

        return dongleSetChannel();
    }

    public boolean setZigBeeExtendedPanId(ExtendedPanId panId) {
        this.extendedPanId = panId;
        return dongleSetExtendedPanId();
    }

    public boolean setNetworkKey(byte[] networkKey) {
        this.networkKey = networkKey;

        return dongleSetNetworkKey();
    }

    public boolean setDistributeNetworkKey(boolean distributeNetworkKey) {
        this.distributeNetworkKey = distributeNetworkKey;

        return dongleSetDistributeNetworkKey();
    }

    public boolean setSecurityMode(int securityMode) {
        this.securityMode = securityMode;

        return dongleSetSecurityMode();
    }

    public void addAsynchronousCommandListener(final AsynchronousCommandListener asynchronousCommandListener) {
        commandInterface.addAsynchronousCommandListener(asynchronousCommandListener);
    }

    public <REQUEST extends ZToolPacket, RESPONSE extends ZToolPacket> RESPONSE sendLocalRequest(REQUEST request) {
        if (!waitForNetwork()) {
            return null;
        }
        RESPONSE result = (RESPONSE) sendSynchronous(request);
        if (result == null) {
            logger.error("{} timed out waiting for synchronous local response.", request.getClass().getSimpleName());
        }
        return result;
    }

    public <REQUEST extends ZToolPacket, RESPONSE extends ZToolPacket> RESPONSE sendRemoteRequest(REQUEST request) {
        if (!waitForNetwork()) {
            return null;
        }
        RESPONSE result;

        waitAndLock3WayConversation(request);
        final BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.ZDO_MGMT_PERMIT_JOIN_RSP,
                commandInterface);

        logger.trace("Sending {}", request);
        ZToolPacket response = sendSynchronous(request);
        if (response == null) {
            logger.error("{} timed out waiting for synchronous local response.", request.getClass().getSimpleName());
            waiter.cleanup();
            return null;
        } else {
            logger.error("{} timed out waiting for asynchronous remote response.", request.getClass().getSimpleName());
            result = (RESPONSE) waiter.getCommand(TIMEOUT);
            unLock3WayConversation(request);
            return result;
        }
    }

    /**
     * @param request
     */
    private void waitAndLock3WayConversation(ZToolPacket request) {
        synchronized (conversation3Way) {
            Class<?> clz = request.getClass();
            Thread requestor;
            while ((requestor = conversation3Way.get(clz)) != null) {
                if (!requestor.isAlive()) {
                    logger.error("Thread {} whom requested {} DIED before unlocking the conversation");
                    logger.debug(
                            "The thread {} who was waiting for {} to complete DIED, so we have to remove the lock");
                    conversation3Way.put(clz, null);
                    break;
                }
                logger.trace("{} is waiting for {} to complete which was issued by {} to complete",
                        new Object[] { Thread.currentThread(), clz, requestor });
                try {
                    conversation3Way.wait();
                } catch (InterruptedException ex) {
                } catch (IllegalMonitorStateException ex) {
                    logger.error("Error in 3 way conversation.", ex);
                }
            }
            conversation3Way.put(clz, Thread.currentThread());
        }
    }

    /**
     * Release the lock held for the 3-way communication
     *
     * @param request
     */
    private void unLock3WayConversation(ZToolPacket request) {
        Class<?> clz = request.getClass();
        Thread requestor;
        synchronized (conversation3Way) {
            requestor = conversation3Way.get(clz);
            conversation3Way.put(clz, null);
            conversation3Way.notify();
        }
        if (requestor == null) {
            logger.error("LOCKING BROKEN - SOMEONE RELEASE THE LOCK WITHOUT LOCKING IN ADVANCE for {}", clz);
        } else if (requestor != Thread.currentThread()) {
            logger.error("Thread {} stolen the answer of {} waited by {}",
                    new Object[] { Thread.currentThread(), clz, requestor });
        }
    }

    private boolean bootloaderGetOut(int magicByte) {
        final BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.SYS_RESET_RESPONSE,
                commandInterface);

        try {
            commandInterface.sendRaw(new int[] { magicByte });
        } catch (IOException e) {
            logger.error("Failed to send bootloader magic byte", e);
        }

        SYS_RESET_RESPONSE response = (SYS_RESET_RESPONSE) waiter.getCommand(RESET_TIMEOUT);

        return response != null;
    }

    private String getStackVersion() {
        if (!waitForHardware()) {
            logger.info("Failed to reach the {} level: getStackVerion() failed", DriverStatus.NETWORK_READY);
            return null;
        }

        SYS_VERSION_RESPONSE response = (SYS_VERSION_RESPONSE) sendSynchronous(new SYS_VERSION());
        if (response == null) {
            return null;
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("Software=");
            builder.append(response.MajorRel);
            builder.append(".");
            builder.append(response.MinorRel);
            builder.append(" Product=");
            builder.append(response.Product);
            builder.append(" Hardware=");
            builder.append(response.HwRev);
            builder.append(" Transport=");
            builder.append(response.TransportRev);
            return builder.toString();
        }
    }

    private boolean dongleReset() {
        final BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.SYS_RESET_RESPONSE,
                commandInterface);

        try {
            commandInterface.sendAsynchronousCommand(new SYS_RESET(SYS_RESET.RESET_TYPE.SERIAL_BOOTLOADER));
        } catch (IOException e) {
            logger.error("Failed to send SYS_RESET", e);
            return false;
        }

        SYS_RESET_RESPONSE response = (SYS_RESET_RESPONSE) waiter.getCommand(RESET_TIMEOUT);

        return response != null;
    }

    private boolean dongleSetStartupOption(int mask) {
        if ((mask & ~(STARTOPT_CLEAR_CONFIG | STARTOPT_CLEAR_STATE)) != 0) {
            logger.warn("Invalid ZCD_NV_STARTUP_OPTION mask {}.", String.format("%08X", mask));
            return false;
        }

        ZB_WRITE_CONFIGURATION_RSP response;
        response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchronous(
                new ZB_WRITE_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_STARTUP_OPTION, new int[] { mask }));

        if (response == null || response.Status != 0) {
            logger.warn("Couldn't set ZCD_NV_STARTUP_OPTION mask {}", String.format("%08X", mask));
            return false;
        } else {
            logger.trace("Set ZCD_NV_STARTUP_OPTION mask {}", String.format("%08X", mask));
        }

        return true;
    }

    private final int[] buildChannelMask(int channel) {
        if (channel < 11 || channel > 27) {
            return new int[] { 0, 0, 0, 0 };
        }

        int channelMask = 1 << channel;
        int[] mask = new int[4];

        for (int i = 0; i < mask.length; i++) {
            mask[i] = Integers.getByteAsInteger(channelMask, i);
        }
        return mask;
    }

    /**
     * Sets the ZigBee RF channel. The allowable channel range is 11 to 26.
     * <p>
     * This method will sanity check the channel and if the mask is invalid
     * the default channel will be used.
     *
     * @param channelMask
     * @return
     */
    private boolean dongleSetChannel(int[] channelMask) {
        // Error check the channels.
        // Incorrectly setting the channel can cause the stick to hang!!

        // Mask out any invalid channels
        channelMask[0] &= ZNP_CHANNEL_MASK0;
        channelMask[1] &= ZNP_CHANNEL_MASK1;
        channelMask[2] &= ZNP_CHANNEL_MASK2;
        channelMask[3] &= ZNP_CHANNEL_MASK3;

        // If there's no channels set, then we go for the default
        if (channelMask[0] == 0 && channelMask[1] == 0 && channelMask[2] == 0 && channelMask[3] == 0) {
            channelMask[0] = ZNP_CHANNEL_DEFAULT0;
            channelMask[1] = ZNP_CHANNEL_DEFAULT1;
            channelMask[2] = ZNP_CHANNEL_DEFAULT2;
            channelMask[3] = ZNP_CHANNEL_DEFAULT3;
        }

        logger.trace("Setting the channel to {}{}{}{}",
                new Object[] { Integer.toHexString(channelMask[0]), Integer.toHexString(channelMask[1]),
                        Integer.toHexString(channelMask[2]), Integer.toHexString(channelMask[3]) });

        ZB_WRITE_CONFIGURATION_RSP response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchronous(
                new ZB_WRITE_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_CHANLIST, channelMask));

        return response != null && response.Status == 0;
    }

    private boolean dongleSetChannel() {
        int[] channelMask = buildChannelMask(channel);

        return dongleSetChannel(channelMask);
    }

    private boolean dongleSetNetworkMode() {
        ZB_WRITE_CONFIGURATION_RSP response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchronous(new ZB_WRITE_CONFIGURATION(
                ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_LOGICAL_TYPE, new int[] { mode.ordinal() }));

        return response != null && response.Status == 0;
    }

    private boolean dongleSetPanId() {
        currentPanId = -1;

        ZB_WRITE_CONFIGURATION_RSP response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchronous(
                new ZB_WRITE_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_PANID,
                        new int[] { Integers.getByteAsInteger(pan, 0), Integers.getByteAsInteger(pan, 1) }));

        return response != null && response.Status == 0;
    }

    private boolean dongleSetExtendedPanId() {
        ZB_WRITE_CONFIGURATION_RSP response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchronous(
                new ZB_WRITE_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_EXTPANID, extendedPanId.getValue()));

        return response != null && response.Status == 0;
    }

    private boolean dongleSetNetworkKey() {
        ZB_WRITE_CONFIGURATION_RSP response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchronous(new ZB_WRITE_CONFIGURATION(
                ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_PRECFGKEY,
                new int[] { networkKey[0], networkKey[1], networkKey[2], networkKey[3], networkKey[4], networkKey[5],
                        networkKey[6], networkKey[7], networkKey[8], networkKey[9], networkKey[10], networkKey[11],
                        networkKey[12], networkKey[13], networkKey[14], networkKey[15] }));

        return response != null && response.Status == 0;
    }

    private boolean dongleSetDistributeNetworkKey() {
        ZB_WRITE_CONFIGURATION_RSP response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchronous(new ZB_WRITE_CONFIGURATION(
                ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_PRECFGKEYS_ENABLE, new int[] { distributeNetworkKey ? 0 : 1 }));

        return response != null && response.Status == 0;
    }

    private boolean dongleSetSecurityMode() {
        ZB_WRITE_CONFIGURATION_RSP response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchronous(new ZB_WRITE_CONFIGURATION(
                ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_SECURITY_MODE, new int[] { securityMode }));

        return response != null && response.Status == 0;
    }

    /**
     * Sends a command without waiting for the response
     *
     * @param request {@link ZToolPacket}
     */
    public void sendCommand(final ZToolPacket request) {
        sendSynchronous(request);
    }

    private ZToolPacket sendSynchronous(final ZToolPacket request) {
        return sendSynchronous(request, RESEND_TIMEOUT);
    }

    private ZToolPacket sendSynchronous(final ZToolPacket request, int timeout) {
        final ZToolPacket[] response = new ZToolPacket[] { null };
        // final int RESEND_MAX_RETRY = 3;
        int sending = 1;

        logger.trace("{} sending as synchronous command.", request.getClass().getSimpleName());

        SynchronousCommandListener listener = new SynchronousCommandListener() {
            @Override
            public void receivedCommandResponse(ZToolPacket packet) {
                logger.trace(" {} received as synchronous command.", packet.getClass().getSimpleName());
                synchronized (response) {
                    // Do not set response[0] again.
                    response[0] = packet;
                    response.notify();
                }
            }
        };

        while (sending <= RESEND_MAX_RETRY) {
            try {
                try {
                    commandInterface.sendSynchronousCommand(request, listener, timeout);
                } catch (IOException e) {
                    logger.error("Synchronous command send failed due to IO exception. ", e);
                    break;
                } catch (Exception ex) {
                    logger.error("Synchronous command send failed due to unexpected exception.", ex);
                }
                logger.trace("{} sent (synchronous command, attempt {}).", request.getClass().getSimpleName(), sending);
                synchronized (response) {
                    long wakeUpTime = System.currentTimeMillis() + timeout;
                    while (response[0] == null && wakeUpTime > System.currentTimeMillis()) {
                        final long sleeping = wakeUpTime - System.currentTimeMillis();
                        logger.trace("Waiting for synchronous command up to {}ms till {} Unixtime", sleeping,
                                wakeUpTime);
                        if (sleeping <= 0) {
                            break;
                        }
                        try {
                            response.wait(sleeping);
                        } catch (InterruptedException ignored) {
                        }
                    }
                }
                if (response[0] != null) {
                    logger.trace("{} -> {}", request.getClass().getSimpleName(),
                            response[0].getClass().getSimpleName());
                    break; // Break out as we have response.
                } else {
                    logger.debug("{} executed and timed out while waiting for response.",
                            request.getClass().getSimpleName());
                }
                if (RESEND_ONLY_EXCEPTION) {
                    break;
                } else {
                    logger.debug("Failed to send {} [attempt {}]", request.getClass().getSimpleName(), sending);
                    sending++;
                }
            } catch (Exception ignored) {
                logger.debug("Failed to send {} [attempt {}]", request.getClass().getSimpleName(), sending);
                logger.trace("Sending operation failed due to ", ignored);
                sending++;
            }
        }

        return response[0];
    }

    public AF_REGISTER_SRSP sendAFRegister(AF_REGISTER request) {
        if (!waitForNetwork()) {
            return null;
        }

        AF_REGISTER_SRSP response = (AF_REGISTER_SRSP) sendSynchronous(request);
        return response;
    }

    public AF_DATA_CONFIRM sendAFDataRequest(AF_DATA_REQUEST request) {
        if (!waitForNetwork()) {
            return null;
        }
        AF_DATA_CONFIRM result = null;

        waitAndLock3WayConversation(request);
        final BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.AF_DATA_CONFIRM, commandInterface);

        AF_DATA_SRSP response = (AF_DATA_SRSP) sendSynchronous(request);
        if (response == null || response.Status != 0) {
            waiter.cleanup();
        } else {
            result = (AF_DATA_CONFIRM) waiter.getCommand(TIMEOUT);
        }
        unLock3WayConversation(request);

        return result;
    }

    /**
     * Sends an Application Framework data request and waits for the response.
     *
     * @param request {@link AF_DATA_REQUEST}
     */
    public AF_DATA_SRSP_EXT sendAFDataRequestExt(AF_DATA_REQUEST_EXT request) {
        if (!waitForNetwork()) {
            return null;
        }
        AF_DATA_SRSP_EXT response = (AF_DATA_SRSP_EXT) sendSynchronous(request);
        return response;
    }

    /**
     * Removes an Application Framework message listener that was previously added with the addAFMessageListener method
     *
     * @param listener a class that implements the {@link ApplicationFrameworkMessageListener} interface
     * @return true if the listener was added
     */
    public boolean removeAFMessageListener(ApplicationFrameworkMessageListener listener) {
        boolean result;
        synchronized (messageListeners) {
            result = messageListeners.remove(listener);
        }

        if (messageListeners.isEmpty() && isHardwareReady()) {
            if (commandInterface.removeAsynchronousCommandListener(afMessageListenerFilter)) {
                logger.trace("Removed AsynchrounsCommandListener {} to ZigBeeSerialInterface",
                        afMessageListenerFilter.getClass().getName());
            } else {
                logger.warn("Could not remove AsynchrounsCommandListener {} to ZigBeeSerialInterface",
                        afMessageListenerFilter.getClass().getName());
            }
        }
        if (result) {
            logger.trace("Removed ApplicationFrameworkMessageListener {}:{}", listener,
                    listener.getClass().getSimpleName());
            return true;
        } else {
            logger.warn("Could not remove ApplicationFrameworkMessageListener {}:{}", listener,
                    listener.getClass().getSimpleName());
            return false;
        }
    }

    /**
     * Adds an Application Framework message listener
     *
     * @param listener a class that implements the {@link ApplicationFrameworkMessageListener} interface
     * @return true if the listener was added
     */
    public boolean addAFMessageListener(ApplicationFrameworkMessageListener listener) {
        synchronized (messageListeners) {
            if (messageListeners.contains(listener)) {
                return true;
            }
        }
        if (messageListeners.isEmpty() && isHardwareReady()) {
            if (commandInterface.addAsynchronousCommandListener(afMessageListenerFilter)) {
                logger.trace("Added AsynchrounsCommandListener {} to ZigBeeSerialInterface",
                        afMessageListenerFilter.getClass().getSimpleName());
            } else {
                logger.trace("Could not add AsynchrounsCommandListener {} to ZigBeeSerialInterface",
                        afMessageListenerFilter.getClass().getSimpleName());
            }
        }
        boolean result;
        synchronized (messageListeners) {
            result = messageListeners.add(listener);
        }

        if (result) {
            logger.trace("Added ApplicationFrameworkMessageListener {}:{}", listener,
                    listener.getClass().getSimpleName());
            return true;
        } else {
            logger.warn("Could not add ApplicationFrameworkMessageListener {}:{}", listener,
                    listener.getClass().getSimpleName());
            return false;
        }
    }

    private boolean isNetworkReady() {
        synchronized (this) {
            return state.ordinal() >= DriverStatus.NETWORK_READY.ordinal()
                    && state.ordinal() < DriverStatus.CLOSED.ordinal();
        }
    }

    private boolean isHardwareReady() {
        synchronized (this) {
            return state.ordinal() >= DriverStatus.HARDWARE_READY.ordinal()
                    && state.ordinal() < DriverStatus.CLOSED.ordinal();
        }
    }

    /**
     * Gets the extended PAN ID
     *
     * @return the PAN ID or -1 on failure
     */
    public ExtendedPanId getCurrentExtendedPanId() {
        if (!waitForHardware()) {
            logger.info("Failed to reach the {} level: getExtendedPanId() failed", DriverStatus.HARDWARE_READY);
            return new ExtendedPanId();
        }

        int[] result = getDeviceInfo(ZB_GET_DEVICE_INFO.DEV_INFO_TYPE.EXT_PAN_ID);

        if (result == null) {
            // luckily -1 (aka 0xffffffffffffffffL) is not a valid extended PAN ID value
            return new ExtendedPanId();
        } else {
            return new ExtendedPanId(result);
        }
    }

    /**
     * Gets the IEEE address of our node on the network
     *
     * @return the IEEE address as a long or -1 on failure
     */
    public long getIeeeAddress() {
        if (ieeeAddress != -1) {
            return ieeeAddress;
        }

        if (!waitForHardware()) {
            logger.info("Failed to reach the {} level: getIeeeAddress() failed", DriverStatus.HARDWARE_READY);
            return -1;
        }

        int[] result = getDeviceInfo(ZB_GET_DEVICE_INFO.DEV_INFO_TYPE.IEEE_ADDR);

        if (result == null) {
            return -1;
        } else {
            ieeeAddress = Integers.longFromInts(result, 7, 0);
            return ieeeAddress;
        }
    }

    /**
     * Gets the current PAN ID
     *
     * @return current PAN ID as an int or -1 on failure
     */
    public int getCurrentPanId() {
        if (!waitForHardware()) {
            logger.info("Failed to reach the {} level: getCurrentPanId() failed", DriverStatus.NETWORK_READY);
            return -1;
        }

        if (currentPanId != -1) {
            return currentPanId;
        }

        int[] result = getDeviceInfo(ZB_GET_DEVICE_INFO.DEV_INFO_TYPE.PAN_ID);
        if (result == null) {
            return -1;
        } else {
            currentPanId = Integers.shortFromInts(result, 1, 0);
            return Integers.shortFromInts(result, 1, 0);
        }
    }

    /**
     * Gets the current ZigBee channe number
     *
     * @return the current channel as an int, or -1 on failure
     */
    public int getCurrentChannel() {
        if (!waitForHardware()) {
            logger.info("Failed to reach the {} level: getCurrentChannel() failed", DriverStatus.HARDWARE_READY);
            return -1;
        }

        int[] result = getDeviceInfo(ZB_GET_DEVICE_INFO.DEV_INFO_TYPE.CHANNEL);
        if (result == null) {
            return -1;
        } else {
            return result[0];
        }
    }

    private int[] getDeviceInfo(int type) {
        ZB_GET_DEVICE_INFO_RSP response = (ZB_GET_DEVICE_INFO_RSP) sendSynchronous(new ZB_GET_DEVICE_INFO(type));

        if (response == null) {
            logger.warn("Failed getDeviceInfo for {} due to null value", type);
            return null;
        } else if (response.Param != type) {
            logger.warn("Failed getDeviceInfo for {} non matching response returned {}", type, response.Param);
            return null;
        } else {
            logger.trace("getDeviceInfo for {} done", type);
            return response.Value;
        }
    }

    public int getZigBeeNodeMode() {
        ZB_READ_CONFIGURATION_RSP response = (ZB_READ_CONFIGURATION_RSP) sendSynchronous(
                new ZB_READ_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_LOGICAL_TYPE));
        if (response != null && response.Status == 0) {
            return response.Value[0];
        } else {
            return -1;
        }
    }

    public byte[] getZigBeeNetworkKey() {
        ZB_READ_CONFIGURATION_RSP response = (ZB_READ_CONFIGURATION_RSP) sendSynchronous(
                new ZB_READ_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_PRECFGKEY));
        if (response != null && response.Status == 0) {
            byte[] readNetworkKey = new byte[16];
            readNetworkKey[0] = (byte) response.Value[0];
            readNetworkKey[1] = (byte) response.Value[1];
            readNetworkKey[2] = (byte) response.Value[2];
            readNetworkKey[3] = (byte) response.Value[3];
            readNetworkKey[4] = (byte) response.Value[4];
            readNetworkKey[5] = (byte) response.Value[5];
            readNetworkKey[6] = (byte) response.Value[6];
            readNetworkKey[7] = (byte) response.Value[7];
            readNetworkKey[8] = (byte) response.Value[8];
            readNetworkKey[9] = (byte) response.Value[9];
            readNetworkKey[10] = (byte) response.Value[10];
            readNetworkKey[11] = (byte) response.Value[11];
            readNetworkKey[12] = (byte) response.Value[12];
            readNetworkKey[13] = (byte) response.Value[13];
            readNetworkKey[14] = (byte) response.Value[14];
            readNetworkKey[15] = (byte) response.Value[15];
            return readNetworkKey;
        } else {
            logger.error("Error reading zigbee network key: " + ResponseStatus.getStatus(response.Status));
            return null;
        }
    }

    public DriverStatus getDriverStatus() {
        return state;
    }

    private void createCustomDevicesOnDongle() {
        int[] input;
        int[] output;

        if (this.ep != null) {
            for (int i = 0; i < this.ep.length; i++) {
                // input
                int size = 0;
                for (int j = 0; j < this.inp[i].length; j++) {

                    if (this.inp[i][j] != 0 && this.inp[i][j] != -1) {
                        size++;
                    }
                }

                input = new int[size];
                for (int j = 0; j < this.inp[i].length; j++) {

                    if (this.inp[i][j] != 0 && this.inp[i][j] != -1) {
                        input[j] = this.inp[i][j];
                    }
                }

                // output
                size = 0;
                for (int j = 0; j < this.out[i].length; j++) {

                    if (this.out[i][j] != 0 && this.out[i][j] != -1) {
                        size++;
                    }
                }

                output = new int[size];
                for (int j = 0; j < this.out[i].length; j++) {

                    if (this.out[i][j] != 0 && this.out[i][j] != -1) {
                        output[j] = this.out[i][j];
                    }
                }

                if (newDevice(new AF_REGISTER(Byte.valueOf(this.ep[i] + ""), this.prof[i],
                        Short.valueOf(this.dev[i] + ""), Byte.valueOf(this.ver[i] + ""), input, output))) {
                    logger.debug("Custom device {} registered at endpoint {}", this.dev[i], this.ep[i]);
                } else {
                    logger.debug("Custom device {} registration failed at endpoint {}", this.dev[i], this.ep[i]);
                }
            }
        }
    }

    private boolean newDevice(AF_REGISTER request) {
        try {
            AF_REGISTER_SRSP response = (AF_REGISTER_SRSP) sendSynchronous(request);
            if (response != null && response.Status == 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error("Error in device register.", e);
        }

        return false;
    }

    private class NetworkStateListener implements AsynchronousCommandListener {
        @Override
        public void receivedAsynchronousCommand(ZToolPacket packet) {
            if (packet.isError()) {
                return;
            }
            if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_STATE_CHANGE_IND) {
                ZDO_STATE_CHANGE_IND p = ((ZDO_STATE_CHANGE_IND) packet);
                switch (p.getStatus()) {
                    case DEV_COORD_STARTED:
                        logger.debug("Started as ZigBee Coordinator");
                        setState(DriverStatus.NETWORK_READY);
                        break;
                    default:
                        break;
                }
            }
        }

        @Override
        public void receivedUnclaimedSynchronousCommandResponse(ZToolPacket packet) {
            // Processing not required
        }
    }

    private static class AFMessageListenerFilter implements AsynchronousCommandListener {
        /**
         * The logger.
         */
        private final Logger logger = LoggerFactory.getLogger(AFMessageListenerFilter.class);

        private final Collection<ApplicationFrameworkMessageListener> listeners;

        private AFMessageListenerFilter(Collection<ApplicationFrameworkMessageListener> list) {
            listeners = list;
        }

        /**
         * An asynchronous command is received from the network. If it is an AF_INCOMING_MSG then
         * pass the message on to any listeners.
         */
        @Override
        public void receivedAsynchronousCommand(ZToolPacket packet) {
            if (packet.isError()) {
                return;
            }
            if (packet.getCMD().get16BitValue() == ZToolCMD.AF_INCOMING_MSG) {
                AF_INCOMING_MSG msg = (AF_INCOMING_MSG) packet;
                if (listeners.isEmpty()) {
                    logger.warn(
                            "Received AF_INCOMING_MSG but no listeners. "
                                    + "Message was from {} and cluster {} to end point {}. Data: {}",
                            msg.getSrcAddr(), msg.getClusterId(), msg.getDstEndpoint(), msg);
                } else {
                    logger.trace("Received AF_INCOMING_MSG from {} and cluster {} to end point {}. Data: {}",
                            msg.getSrcAddr(), msg.getClusterId(), msg.getDstEndpoint(), msg);
                }
                ArrayList<ApplicationFrameworkMessageListener> localCopy;
                synchronized (listeners) {
                    localCopy = new ArrayList<ApplicationFrameworkMessageListener>(listeners);
                }
                for (ApplicationFrameworkMessageListener listener : localCopy) {
                    try {
                        listener.notify(msg);
                    } catch (final Exception e) {
                        logger.error("Error AF message listener notify.", e);
                    }
                }
            }
        }

        @Override
        public void receivedUnclaimedSynchronousCommandResponse(ZToolPacket packet) {
            // No need to handle unclaimed responses here
        }
    }
}
