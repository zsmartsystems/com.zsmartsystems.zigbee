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
package com.zsmartsystems.zigbee.dongle.cc2531.network.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeException;
import com.zsmartsystems.zigbee.ZigBeePort;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ApplicationFrameworkMessageListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.AsynchronousCommandListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.CommandInterface;
import com.zsmartsystems.zigbee.dongle.cc2531.network.SynchronousCommandListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.cc2531.network.model.DriverStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.model.NetworkMode;
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
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_ACTIVE_EP_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_ACTIVE_EP_REQ_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_ACTIVE_EP_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_BIND_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_BIND_REQ_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_BIND_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_IEEE_ADDR_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_IEEE_ADDR_REQ_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_IEEE_ADDR_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_LEAVE_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_LEAVE_REQ_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_LEAVE_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_LQI_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_LQI_REQ_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_LQI_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_PERMIT_JOIN_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_PERMIT_JOIN_REQ_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_PERMIT_JOIN_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MSG_CB_REGISTER;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MSG_CB_REGISTER_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_NODE_DESC_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_NODE_DESC_REQ_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_NODE_DESC_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_POWER_DESC_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_POWER_DESC_REQ_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_POWER_DESC_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_SIMPLE_DESC_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_SIMPLE_DESC_REQ_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_SIMPLE_DESC_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_STARTUP_FROM_APP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_STARTUP_FROM_APP_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_STATE_CHANGE_IND;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_UNBIND_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_UNBIND_REQ_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_UNBIND_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.Integers;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress64;

/**
 * The zigbee network manager port implementation.
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:michele.girolami@isti.cnr.it">Michele Girolami</a>
 * @author <a href="mailto:manlio.bacco@isti.cnr.it">Manlio Bacco</a>
 * @author <a href="mailto:tommi.s.e.laukkanen@gmail.com">Tommi S.E. Laukkanen</a>
 * @author <a href="mailto:christopherhattonuk@gmail.com">Chris Hatton</a>
 * @author <a href="mailto:chris@cd-jackson.com">Chris Jackson</a>
 */
public class ZigBeeNetworkManagerImpl implements ZigBeeNetworkManager {

    private final static Logger logger = LoggerFactory.getLogger(ZigBeeNetworkManagerImpl.class);

    private static final int DEFAULT_TIMEOUT = 8000;
    private static final String TIMEOUT_KEY = "zigbee.driver.cc2531.timeout";

    private static final int RESET_TIMEOUT_DEFAULT = 5000;
    private static final String RESET_TIMEOUT_KEY = "zigbee.driver.cc2531.reset.timeout";

    private static final int STARTUP_TIMEOUT_DEFAULT = 10000;
    private static final String STARTUP_TIMEOUT_KEY = "zigbee.driver.cc2531.startup.timeout";

    private static final int RESEND_TIMEOUT_DEFAULT = 1000;
    private static final String RESEND_TIMEOUT_KEY = "zigbee.driver.cc2531.resend.timeout";

    private static final int RESEND_MAX_RESEND_DEFAULT = 3;
    private static final String RESEND_MAX_RESEND_KEY = "zigbee.driver.cc2531.resend.max";

    private static final boolean RESEND_ONLY_EXCEPTION_DEFAULT = true;
    private static final String RESEND_ONLY_EXCEPTION_KEY = "zigbee.driver.cc2531.resend.exceptionally";

    private static final int BOOTLOADER_MAGIC_BYTE_DEFAULT = 0xef;
    private static final String BOOTLOADER_MAGIC_BYTE_KEY = "zigbee.driver.cc2531.bl.magic.byte";

    private final int TIMEOUT;
    private final int RESET_TIMEOUT;
    private final int STARTUP_TIMEOUT;
    private final int RESEND_TIMEOUT;
    private final int RESEND_MAX_RETRY;
    private final boolean RESEND_ONLY_EXCEPTION;
    private final int BOOTLOADER_MAGIC_BYTE;

    // Dongle startup options
    private final int STARTOPT_CLEAR_CONFIG = 0x00000001;
    private final int STARTOPT_CLEAR_STATE = 0x00000002;

    // The dongle will automatically pickup a random, not conflicting PAN ID
    private final short AUTO_PANID = (short) 0xffff;

    private CommandInterface commandInterface;
    private ZigBeePort port;
    private DriverStatus state;
    private NetworkMode mode;
    private short pan = AUTO_PANID;
    private byte channel;
    private long extendedPanId; // do not initialize to use dongle defaults (the IEEE address)
    private byte[] networkKey; // 16 byte network key
    private boolean distributeNetworkKey = true; // distribute network key in clear (be careful)
    private int securityMode = 1; // int for future extensibility

    private int[] ep, prof, dev, ver;
    private short[][] inp, out;

    // private final Set<AnnounceListener> announceListeners = new HashSet<AnnounceListener>();
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

    public ZigBeeNetworkManagerImpl(ZigBeePort port, NetworkMode mode, int pan, int channel, byte[] networkKey,
            long timeout) {

        int aux = RESEND_TIMEOUT_DEFAULT;
        try {
            aux = Integer.parseInt(System.getProperty(RESEND_TIMEOUT_KEY));
            logger.trace("Using RESEND_TIMEOUT set from enviroment {}", aux);
        } catch (NumberFormatException ex) {
            logger.trace("Using RESEND_TIMEOUT set as DEFAULT {}", aux);
        }
        RESEND_TIMEOUT = aux;

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

        aux = RESEND_MAX_RESEND_DEFAULT;
        try {
            aux = Integer.parseInt(System.getProperty(RESEND_MAX_RESEND_KEY));
            logger.trace("Using RESEND_MAX_RETRY set from enviroment {}", aux);
        } catch (NumberFormatException ex) {
            logger.trace("Using RESEND_MAX_RETRY set as DEFAULT {}", aux);
        }
        RESEND_MAX_RETRY = aux;

        boolean b = RESEND_ONLY_EXCEPTION_DEFAULT;
        try {
            b = Boolean.parseBoolean(System.getProperty(RESEND_ONLY_EXCEPTION_KEY));
            logger.trace("Using RESEND_MAX_RETRY set from enviroment {}", aux);
        } catch (NumberFormatException ex) {
            logger.trace("Using RESEND_MAX_RETRY set as DEFAULT {}", aux);
        }
        RESEND_ONLY_EXCEPTION = b;

        aux = BOOTLOADER_MAGIC_BYTE_DEFAULT;
        try {
            aux = Integer.parseInt(System.getProperty(BOOTLOADER_MAGIC_BYTE_KEY));
            logger.trace("Using BOOTLOADER_MAGIC_BYTE set from enviroment {}", aux);
        } catch (NumberFormatException ex) {
            logger.trace("Using BOOTLOADER_MAGIC_BYTE set as DEFAULT {}", aux);
        }
        BOOTLOADER_MAGIC_BYTE = aux;

        state = DriverStatus.CLOSED;
        setSerialPort(port);
        setZigBeeNetwork((byte) channel, (short) pan);
        setZigBeeNodeMode(mode);
        setZigBeeNetworkKey(networkKey);
    }

    @Override
    public boolean startup() {
        if (state == DriverStatus.CLOSED) {
            state = DriverStatus.CREATED;
            logger.trace("Initializing hardware.");

            // Open the hardware port
            setState(DriverStatus.HARDWARE_INITIALIZING);
            if (!initializeHardware()) {
                shutdown();
                return false;
            }

            // Now reset the dongle
            setState(DriverStatus.HARDWARE_OPEN);
            if (!dongleReset()) {
                logger.warn("Dongle reset failed. Assuming bootloader is running and sending magic byte {}.",
                        String.format("0x%02x", BOOTLOADER_MAGIC_BYTE));
                if (!bootloaderGetOut(BOOTLOADER_MAGIC_BYTE)) {
                    logger.warn("Attempt to get out from bootloader failed.");
                    shutdown();
                    return false;
                }
            }

            setState(DriverStatus.HARDWARE_READY);

            return true;
        } else {
            throw new IllegalStateException("Driver already opened, current status is:" + state);
        }
    }

    @Override
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
        commandInterface = new CommandInterfaceImpl(port);
        if (!commandInterface.open()) {
            logger.error("Failed to initialize the dongle on port {}.", port);
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
        if (checkZigBeeNetworkConfiguration()) {
            logger.error("Dongle configuration does not match the specified configuration.");
            shutdown();
            return false;
        }
        return true;
    }

    private boolean createZigBeeNetwork() {
        createCustomDevicesOnDongle();
        logger.debug("Creating network as {}", mode.toString());

        final int ALL_CLUSTERS = 0xFFFF;

        logger.trace("Reset seq: Trying MSG_CB_REGISTER");
        ZDO_MSG_CB_REGISTER_SRSP responseCb = (ZDO_MSG_CB_REGISTER_SRSP) sendSynchrouns(commandInterface,
                new ZDO_MSG_CB_REGISTER(new DoubleByte(ALL_CLUSTERS)));
        if (responseCb == null) {
            return false;
        }

        final int instantStartup = 0;

        ZDO_STARTUP_FROM_APP_SRSP response = (ZDO_STARTUP_FROM_APP_SRSP) sendSynchrouns(commandInterface,
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

    private boolean checkZigBeeNetworkConfiguration() {
        int value;
        long longValue;
        boolean mismatch = false;
        if ((value = getCurrentChannel()) != channel) {
            logger.warn("The channel configuration differ from the channel configuration in use: "
                    + "in use {}, while the configured is {}.\n"
                    + "The ZigBee network should be reconfigured or configuration corrected.", value, channel);
            mismatch = true;
        }
        // Do not check the current PAN ID if a random one is generated
        // by the dongle.
        if (pan != AUTO_PANID && (value = getCurrentPanId()) != pan) {
            logger.warn("The PanId configuration differ from the PanId configuration in use: "
                    + "in use {}, while the configured is {}.\n"
                    + "The ZigBee network should be reconfigured or configuration corrected.", value, pan);
            mismatch = true;
        }
        if (extendedPanId != 0 && (longValue = getExtendedPanId()) != extendedPanId) {
            logger.warn(
                    "The ExtendedPanId configuration differ from the ExtendedPanId configuration in use: "
                            + "in use {}, while the configured is {}.\n"
                            + "The ZigBee network should be reconfigured or configuration corrected.",
                    longValue, extendedPanId);
            mismatch = true;
        }
        if ((value = getZigBeeNodeMode()) != mode.ordinal()) {
            logger.warn(
                    "The NetworkMode configuration differ from the NetworkMode configuration in use: "
                            + "in use {}, while the configured is {}.\n"
                            + "The ZigBee network should be reconfigured or configuration corrected.",
                    value, mode.ordinal());
            mismatch = true;
        }

        if (networkKey != null) {
            final byte[] readNetworkKey = getZigBeeNetworkKey();
            if (readNetworkKey == null) {
                logger.warn("Could not read preconfigured network key.");
                mismatch = true;
            } else {
                boolean networkKeyMismatch = false;
                for (int i = 0; i < 16; i++) {
                    if (networkKey[i] != readNetworkKey[i]) {
                        networkKeyMismatch = true;
                        break;
                    }
                }
                if (networkKeyMismatch) {
                    logger.warn("The NetworkKey configuration differ from the NetworkKey configuration in use.",
                            Hex.toHexString(networkKey), Hex.toHexString(readNetworkKey));
                    mismatch = true;
                }
            }
        }

        return mismatch;
    }

    private boolean configureZigBeeNetwork() {
        logger.debug("Resetting network stack.");

        // Make sure we start clearing configuration and state
        logger.info("Setting clean state.");
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
        logger.info("Resetting dongle.");
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
        if (extendedPanId != 0) {
            logger.debug("Setting Extended PAN ID to {}.", String.format("%08X", extendedPanId));
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
        logger.trace("{} -> {}", this.state, value);
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

    @Override
    public void setZigBeeNodeMode(NetworkMode networkMode) {
        if (state != DriverStatus.CLOSED) {
            throw new IllegalStateException(
                    "Network mode can be changed only " + "if driver is CLOSED while it is:" + state);
        }
        mode = networkMode;
    }

    public void setZigBeeNetworkKey(byte[] networkKey) {
        if (state != DriverStatus.CLOSED) {
            throw new IllegalStateException(
                    "Network key can be changed only " + "if driver is CLOSED while it is:" + state);
        }
        this.networkKey = networkKey;
    }

    @Override
    public void setZigBeeNetwork(byte ch, short panId) {
        if (state != DriverStatus.CLOSED) {
            throw new IllegalStateException(
                    "Network mode can be changed only " + "if driver is CLOSED while it is:" + state);
        }
        channel = ch;
        pan = panId;
    }

    @Override
    public void setSerialPort(ZigBeePort port) {
        if (state != DriverStatus.CLOSED) {
            throw new IllegalStateException(
                    "Serial port can be changed only " + "if driver is CLOSED while it is:" + state);
        }
        this.port = port;
    }

    @Override
    public void addAsynchronousCommandListener(final AsynchronousCommandListener asynchronousCommandListener) {
        commandInterface.addAsynchronousCommandListener(asynchronousCommandListener);
    }

    public <REQUEST extends ZToolPacket, RESPONSE extends ZToolPacket> RESPONSE sendLocalRequest(REQUEST request) {
        if (!waitForNetwork()) {
            return null;
        }
        RESPONSE result = (RESPONSE) sendSynchrouns(commandInterface, request);
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
        ZToolPacket response = sendSynchrouns(commandInterface, request);
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

    @Override
    public ZDO_MGMT_LQI_RSP sendLQIRequest(ZDO_MGMT_LQI_REQ request) {

        if (!waitForNetwork()) {
            return null;
        }
        ZDO_MGMT_LQI_RSP result = null;

        waitAndLock3WayConversation(request);
        final BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.ZDO_MGMT_LQI_RSP, commandInterface);

        logger.trace("Sending ZDO_MGMT_LQI_REQ {}", request);
        ZDO_MGMT_LQI_REQ_SRSP response = (ZDO_MGMT_LQI_REQ_SRSP) sendSynchrouns(commandInterface, request);
        if (response == null || response.Status != 0) {
            logger.trace("ZDO_MGMT_LQI_REQ failed, received {}", response);
            waiter.cleanup();
        } else {
            result = (ZDO_MGMT_LQI_RSP) waiter.getCommand(TIMEOUT);
        }
        unLock3WayConversation(request);
        return result;
    }

    @Override
    public ZDO_IEEE_ADDR_RSP sendZDOIEEEAddressRequest(ZDO_IEEE_ADDR_REQ request) {
        if (!waitForNetwork()) {
            return null;
        }
        ZDO_IEEE_ADDR_RSP result = null;

        waitAndLock3WayConversation(request);
        final BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.ZDO_IEEE_ADDR_RSP,
                commandInterface);

        logger.trace("Sending ZDO_IEEE_ADDR_REQ {}", request);
        ZDO_IEEE_ADDR_REQ_SRSP response = (ZDO_IEEE_ADDR_REQ_SRSP) sendSynchrouns(commandInterface, request);
        if (response == null || response.Status != 0) {
            logger.warn("ZDO_IEEE_ADDR_REQ failed, received {}", response);
            waiter.cleanup();
        } else {
            result = (ZDO_IEEE_ADDR_RSP) waiter.getCommand(TIMEOUT);
        }
        unLock3WayConversation(request);
        return result;
    }

    @Override
    public ZDO_NODE_DESC_RSP sendZDONodeDescriptionRequest(ZDO_NODE_DESC_REQ request) {
        if (!waitForNetwork()) {
            return null;
        }
        ZDO_NODE_DESC_RSP result = null;

        waitAndLock3WayConversation(request);
        final BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.ZDO_NODE_DESC_RSP,
                commandInterface);

        ZDO_NODE_DESC_REQ_SRSP response = (ZDO_NODE_DESC_REQ_SRSP) sendSynchrouns(commandInterface, request);
        if (response == null || response.Status != 0) {
            waiter.cleanup();
        } else {
            result = (ZDO_NODE_DESC_RSP) waiter.getCommand(TIMEOUT);
        }

        unLock3WayConversation(request);
        return result;
    }

    @Override
    public ZDO_POWER_DESC_RSP sendZDOPowerDescriptionRequest(ZDO_POWER_DESC_REQ request) {
        if (!waitForNetwork()) {
            return null;
        }
        ZDO_POWER_DESC_RSP result = null;

        waitAndLock3WayConversation(request);
        final BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.ZDO_POWER_DESC_RSP,
                commandInterface);

        ZDO_POWER_DESC_REQ_SRSP response = (ZDO_POWER_DESC_REQ_SRSP) sendSynchrouns(commandInterface, request);
        if (response == null || response.Status != 0) {
            waiter.cleanup();
        } else {
            result = (ZDO_POWER_DESC_RSP) waiter.getCommand(TIMEOUT);
        }

        unLock3WayConversation(request);
        return result;
    }

    @Override
    public ZDO_ACTIVE_EP_RSP sendZDOActiveEndPointRequest(ZDO_ACTIVE_EP_REQ request) {
        if (!waitForNetwork()) {
            return null;
        }
        ZDO_ACTIVE_EP_RSP result = null;

        waitAndLock3WayConversation(request);
        final BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.ZDO_ACTIVE_EP_RSP,
                commandInterface);

        logger.trace("Sending ZDO_ACTIVE_EP_REQ {}", request);
        ZDO_ACTIVE_EP_REQ_SRSP response = (ZDO_ACTIVE_EP_REQ_SRSP) sendSynchrouns(commandInterface, request);
        if (response == null || response.Status != 0) {
            logger.trace("ZDO_ACTIVE_EP_REQ failed, received {}", response);
            waiter.cleanup();
        } else {
            result = (ZDO_ACTIVE_EP_RSP) waiter.getCommand(TIMEOUT);
        }
        unLock3WayConversation(request);
        return result;
    }

    public ZDO_MGMT_PERMIT_JOIN_RSP sendPermitJoinRequest(ZDO_MGMT_PERMIT_JOIN_REQ request, boolean waitForCommand) {
        if (!waitForNetwork()) {
            return null;
        }
        ZDO_MGMT_PERMIT_JOIN_RSP result = null;

        waitAndLock3WayConversation(request);
        final BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.ZDO_MGMT_PERMIT_JOIN_RSP,
                commandInterface);

        logger.trace("Sending ZDO_MGMT_PERMIT_JOIN_REQ {}", request);
        ZDO_MGMT_PERMIT_JOIN_REQ_SRSP response = (ZDO_MGMT_PERMIT_JOIN_REQ_SRSP) sendSynchrouns(commandInterface,
                request);
        if (response == null || response.Status != 0) {
            logger.trace("ZDO_MGMT_PERMIT_JOIN_REQ failed, received {}", response);
            waiter.cleanup();
        } else if (!waitForCommand) {
            result = (ZDO_MGMT_PERMIT_JOIN_RSP) waiter.getCommand(TIMEOUT);
        }
        unLock3WayConversation(request);
        return result;
    }

    public boolean sendZDOLeaveRequest(ZToolAddress16[] addresses) {
        // ---------ZDO GET IEEE ADDR
        logger.trace("Reset seq: Trying GETIEEEADDR");
        ZToolAddress64[] longAddresses = new ZToolAddress64[addresses.length];
        for (int k = 0; k < addresses.length; k++) {
            // ZDO_IEEE_ADDR_RSP responseA4 = sendZDOIEEEAddressRequest(new
            // ZDO_IEEE_ADDR_REQ(addresses[k],ZDO_IEEE_ADDR_REQ.REQ_TYPE.EXTENDED.getValue(),0));

            ZDO_IEEE_ADDR_RSP responseA4 = null;
            BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.ZDO_IEEE_ADDR_RSP, commandInterface);
            logger.trace("Sending ZDO_IEEE_ADDR_REQ");
            ZDO_IEEE_ADDR_REQ_SRSP response = (ZDO_IEEE_ADDR_REQ_SRSP) sendSynchrouns(commandInterface,
                    new ZDO_IEEE_ADDR_REQ(addresses[k], ZDO_IEEE_ADDR_REQ.REQ_TYPE.EXTENDED.getValue(), 0));
            if (response == null || response.Status != 0) {
                logger.trace("ZDO_IEEE_ADDR_REQ failed, received {}", response);
                waiter.cleanup();
            } else {
                responseA4 = (ZDO_IEEE_ADDR_RSP) waiter.getCommand(TIMEOUT);
            }

            if (responseA4 != null) {
                longAddresses[k] = responseA4.getIeeeAddress();
            } else {
                longAddresses[k] = null;
            }
        }
        // ---------ZDO MGMT LEAVE
        logger.debug("Reset seq: Trying LEAVE");
        long start = System.currentTimeMillis();
        for (int k = 0; k < longAddresses.length; k++) {
            if (longAddresses[k] != null) {
                BlockingCommandReceiver waiter3 = new BlockingCommandReceiver(ZToolCMD.ZDO_MGMT_LEAVE_RSP,
                        commandInterface);

                ZDO_MGMT_LEAVE_REQ_SRSP response = (ZDO_MGMT_LEAVE_REQ_SRSP) sendSynchrouns(commandInterface,
                        new ZDO_MGMT_LEAVE_REQ(addresses[k], longAddresses[k], 0));
                if (response == null) {
                    logger.error("Leave request time out.");
                    return false;
                }
                if (response.Status != 0) {
                    waiter3.cleanup();
                    logger.error("Leave SRSP error status: " + response.Status);
                    return false;
                }
                ZDO_MGMT_LEAVE_RSP responseA5 = (ZDO_MGMT_LEAVE_RSP) waiter3.getCommand(TIMEOUT);
                if (responseA5 == null) {
                    logger.error("Leave request got no RSP.");
                    return false;
                }
                if (responseA5.Status != 0) {
                    logger.error("Leave request RSP error status: " + responseA5.Status);
                    return false;
                }
            }
            if ((System.currentTimeMillis() - start) > TIMEOUT) {
                logger.error("Reset seq: Failed LEAVE");
                return false;
            }
        }

        return true;
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

    @Override
    public ZDO_SIMPLE_DESC_RSP sendZDOSimpleDescriptionRequest(ZDO_SIMPLE_DESC_REQ request) {
        if (!waitForNetwork()) {
            return null;
        }
        ZDO_SIMPLE_DESC_RSP result = null;
        waitAndLock3WayConversation(request);
        final BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.ZDO_SIMPLE_DESC_RSP,
                commandInterface);

        ZDO_SIMPLE_DESC_REQ_SRSP response = (ZDO_SIMPLE_DESC_REQ_SRSP) sendSynchrouns(commandInterface, request);
        if (response == null || response.Status != 0) {
            waiter.cleanup();
        } else {
            result = (ZDO_SIMPLE_DESC_RSP) waiter.getCommand(TIMEOUT);
        }

        unLock3WayConversation(request);
        return result;
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
        response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchrouns(commandInterface,
                new ZB_WRITE_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_STARTUP_OPTION, new int[] { mask }));

        if (response == null || response.Status != 0) {
            logger.warn("Couldn't set ZCD_NV_STARTUP_OPTION mask {}", String.format("%08X", mask));
            return false;
        } else {
            logger.trace("Set ZCD_NV_STARTUP_OPTION mask {}", String.format("%08X", mask));
        }

        return true;
    }

    private static final int[] buildChannelMask(int channel) {
        int channelMask = 1 << channel;
        int[] mask = new int[4];
        for (int i = 0; i < mask.length; i++) {
            mask[i] = Integers.getByteAsInteger(channelMask, i);
        }
        return mask;
    }

    private boolean dongleSetChannel(int[] channelMask) {
        logger.trace("Setting the channel to {}{}{}{}",
                new Object[] { Integer.toHexString(channelMask[0]), Integer.toHexString(channelMask[1]),
                        Integer.toHexString(channelMask[2]), Integer.toHexString(channelMask[3]) });

        ZB_WRITE_CONFIGURATION_RSP response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchrouns(commandInterface,
                new ZB_WRITE_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_CHANLIST, channelMask));

        return response != null && response.Status == 0;
    }

    private boolean dongleSetChannel(int ch) {
        int[] channelMask = buildChannelMask(ch);

        return dongleSetChannel(channelMask);
    }

    private boolean dongleSetChannel() {
        int[] channelMask = buildChannelMask(channel);

        return dongleSetChannel(channelMask);
    }

    private boolean dongleSetNetworkMode() {

        ZB_WRITE_CONFIGURATION_RSP response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchrouns(commandInterface,
                new ZB_WRITE_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_LOGICAL_TYPE,
                        new int[] { mode.ordinal() }));

        return response != null && response.Status == 0;
    }

    private boolean dongleSetPanId() {
        currentPanId = -1;

        ZB_WRITE_CONFIGURATION_RSP response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchrouns(commandInterface,
                new ZB_WRITE_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_PANID,
                        new int[] { Integers.getByteAsInteger(pan, 0), Integers.getByteAsInteger(pan, 1) }));

        return response != null && response.Status == 0;
    }

    private boolean dongleSetExtendedPanId() {
        ZB_WRITE_CONFIGURATION_RSP response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchrouns(commandInterface,
                new ZB_WRITE_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_EXTPANID, new int[] {
                        Integers.getByteAsInteger(extendedPanId, 0), Integers.getByteAsInteger(extendedPanId, 1),
                        Integers.getByteAsInteger(extendedPanId, 2), Integers.getByteAsInteger(extendedPanId, 3),
                        Integers.getByteAsInteger(extendedPanId, 4), Integers.getByteAsInteger(extendedPanId, 5),
                        Integers.getByteAsInteger(extendedPanId, 6), Integers.getByteAsInteger(extendedPanId, 7), }));

        return response != null && response.Status == 0;
    }

    private boolean dongleSetNetworkKey() {
        ZB_WRITE_CONFIGURATION_RSP response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchrouns(commandInterface,
                new ZB_WRITE_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_PRECFGKEY,
                        new int[] { networkKey[0], networkKey[1], networkKey[2], networkKey[3], networkKey[4],
                                networkKey[5], networkKey[6], networkKey[7], networkKey[8], networkKey[9],
                                networkKey[10], networkKey[11], networkKey[12], networkKey[13], networkKey[14],
                                networkKey[15] }));

        return response != null && response.Status == 0;
    }

    private boolean dongleSetDistributeNetworkKey() {
        ZB_WRITE_CONFIGURATION_RSP response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchrouns(commandInterface,
                new ZB_WRITE_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_PRECFGKEYS_ENABLE,
                        new int[] { distributeNetworkKey ? 0 : 1 }));

        return response != null && response.Status == 0;
    }

    private boolean dongleSetSecurityMode() {
        ZB_WRITE_CONFIGURATION_RSP response = (ZB_WRITE_CONFIGURATION_RSP) sendSynchrouns(commandInterface,
                new ZB_WRITE_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_SECURITY_MODE,
                        new int[] { securityMode }));

        return response != null && response.Status == 0;
    }

    public void sendCommand(final ZToolPacket request) throws ZigBeeException {
        try {
            commandInterface.sendPacket(request);
        } catch (IOException e) {
            throw new ZigBeeException(e);
        }
    }

    private ZToolPacket sendSynchrouns(final CommandInterface hwDriver, final ZToolPacket request) {
        // final int RESEND_TIMEOUT = 1000;
        return sendSynchrouns(hwDriver, request, RESEND_TIMEOUT);
    }

    private ZToolPacket sendSynchrouns(final CommandInterface hwDriver, final ZToolPacket request, int timeout) {
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
                    hwDriver.sendSynchronousCommand(request, listener, timeout);
                } catch (IOException e) {
                    logger.error("Synchronous command send failed due to IO exception. ", e);
                    break;
                } catch (Exception ex) {
                    logger.error("Synchronous command send failed due to unexpected exception.", ex);
                }
                logger.trace("{} sent (synchronous command, retry: {}).", request.getClass().getSimpleName(), sending);
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
                    logger.warn("{} executed and timed out while waiting for response.",
                            request.getClass().getSimpleName());
                }
                if (RESEND_ONLY_EXCEPTION) {
                    break;
                } else {
                    logger.info("Failed to send {} during the {}-th tentative", request.getClass().getName(), sending);
                    sending++;
                }
            } catch (Exception ignored) {
                logger.info("Failed to send {} during the {}-th tentative", request.getClass().getName(), sending);
                logger.trace("Sending operation failed due to ", ignored);
                sending++;
            }
        }

        return response[0];
    }

    @Override
    public AF_REGISTER_SRSP sendAFRegister(AF_REGISTER request) {
        if (!waitForNetwork()) {
            return null;
        }

        AF_REGISTER_SRSP response = (AF_REGISTER_SRSP) sendSynchrouns(commandInterface, request);
        return response;
    }

    @Override
    /**
     * @param request {@link AF_DATA_REQUEST}
     */
    public AF_DATA_CONFIRM sendAFDataRequest(AF_DATA_REQUEST request) {
        if (!waitForNetwork()) {
            return null;
        }
        AF_DATA_CONFIRM result = null;

        waitAndLock3WayConversation(request);
        final BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.AF_DATA_CONFIRM, commandInterface);

        AF_DATA_SRSP response = (AF_DATA_SRSP) sendSynchrouns(commandInterface, request);
        if (response == null || response.Status != 0) {
            waiter.cleanup();
        } else {
            result = (AF_DATA_CONFIRM) waiter.getCommand(TIMEOUT);
        }
        unLock3WayConversation(request);

        return result;
    }

    public AF_DATA_SRSP_EXT sendAFDataRequestExt(AF_DATA_REQUEST_EXT request) {
        if (!waitForNetwork()) {
            return null;
        }
        AF_DATA_SRSP_EXT response = (AF_DATA_SRSP_EXT) sendSynchrouns(commandInterface, request);
        return response;
    }

    @Override
    public ZDO_BIND_RSP sendZDOBind(ZDO_BIND_REQ request) {
        if (!waitForNetwork()) {
            return null;
        }
        ZDO_BIND_RSP result = null;

        waitAndLock3WayConversation(request);
        final BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.ZDO_BIND_RSP, commandInterface);

        ZDO_BIND_REQ_SRSP response = (ZDO_BIND_REQ_SRSP) sendSynchrouns(commandInterface, request);
        if (response == null || response.Status != 0) {
            waiter.cleanup();
        } else {
            result = (ZDO_BIND_RSP) waiter.getCommand(TIMEOUT);
        }
        unLock3WayConversation(request);
        return result;
    }

    @Override
    public ZDO_UNBIND_RSP sendZDOUnbind(ZDO_UNBIND_REQ request) {
        if (!waitForNetwork()) {
            return null;
        }
        ZDO_UNBIND_RSP result = null;

        waitAndLock3WayConversation(request);
        final BlockingCommandReceiver waiter = new BlockingCommandReceiver(ZToolCMD.ZDO_UNBIND_RSP, commandInterface);

        ZDO_UNBIND_REQ_SRSP response = (ZDO_UNBIND_REQ_SRSP) sendSynchrouns(commandInterface, request);
        if (response == null || response.Status != 0) {
            waiter.cleanup();
        } else {
            result = (ZDO_UNBIND_RSP) waiter.getCommand(TIMEOUT);
        }

        unLock3WayConversation(request);
        return result;
    }

    /**
     * Removes an Application Framework message listener that was previously added with the addAFMessageListener method
     *
     * @param listener a class that implements the {@link ApplicationFrameworkMessageListener} interface
     * @return true if the listener was added
     */
    @Override
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
            logger.trace("Removed ApplicationFrameworkMessageListener {}:{}", listener, listener.getClass().getName());
            return true;
        } else {
            logger.warn("Could not remove ApplicationFrameworkMessageListener {}:{}", listener,
                    listener.getClass().getName());
            return false;
        }
    }

    /**
     * Adds an Application Framework message listener
     *
     * @param listener a class that implements the {@link ApplicationFrameworkMessageListener} interface
     * @return true if the listener was added
     */
    @Override
    public boolean addAFMessageListener(ApplicationFrameworkMessageListener listener) {
        synchronized (messageListeners) {
            if (messageListeners.contains(listener)) {
                return true;
            }
        }
        if (messageListeners.isEmpty() && isHardwareReady()) {
            if (commandInterface.addAsynchronousCommandListener(afMessageListenerFilter)) {
                logger.trace("Added AsynchrounsCommandListener {} to ZigBeeSerialInterface",
                        afMessageListenerFilter.getClass().getName());
            } else {
                logger.trace("Could not add AsynchrounsCommandListener {} to ZigBeeSerialInterface",
                        afMessageListenerFilter.getClass().getName());
            }
        }
        boolean result;
        synchronized (messageListeners) {
            result = messageListeners.add(listener);
        }

        if (result) {
            logger.trace("Added ApplicationFrameworkMessageListener {}:{}", listener, listener.getClass().getName());
            return true;
        } else {
            logger.warn("Could not add ApplicationFrameworkMessageListener {}:{}", listener,
                    listener.getClass().getName());
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
     * @since 0.2.0
     */
    @Override
    public long getExtendedPanId() {
        if (!waitForNetwork()) {
            logger.info("Failed to reach the {} level: getExtendedPanId() failed", DriverStatus.NETWORK_READY);
            return -1;
        }

        int[] result = getDeviceInfo(ZB_GET_DEVICE_INFO.DEV_INFO_TYPE.EXT_PAN_ID);

        if (result == null) {
            // luckily -1 (aka 0xffffffffffffffffL) is not a valid extended PAN ID value
            return -1;
        } else {
            return Integers.longFromInts(result, 7, 0);
        }
    }

    /**
     * Gets the IEEE address of our node on the network
     *
     * @return the IEEE address as a long or -1 on failure
     * @since 0.2.0
     */
    @Override
    public long getIeeeAddress() {

        if (ieeeAddress != -1) {
            return ieeeAddress;
        }

        if (!waitForNetwork()) {
            logger.info("Failed to reach the {} level: getIeeeAddress() failed", DriverStatus.NETWORK_READY);
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
     * @since 0.2.0
     */
    @Override
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
     * @since 0.2.0
     */
    @Override
    public int getCurrentChannel() {
        if (!waitForNetwork()) {
            logger.info("Failed to reach the {} level: getCurrentChannel() failed", DriverStatus.NETWORK_READY);
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
        ZB_GET_DEVICE_INFO_RSP response = (ZB_GET_DEVICE_INFO_RSP) sendSynchrouns(commandInterface,
                new ZB_GET_DEVICE_INFO(type));

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

    @Override
    public int getZigBeeNodeMode() {
        ZB_READ_CONFIGURATION_RSP response = (ZB_READ_CONFIGURATION_RSP) sendSynchrouns(commandInterface,
                new ZB_READ_CONFIGURATION(ZB_WRITE_CONFIGURATION.CONFIG_ID.ZCD_NV_LOGICAL_TYPE));
        if (response != null && response.Status == 0) {
            return response.Value[0];
        } else {
            return -1;
        }
    }

    public byte[] getZigBeeNetworkKey() {
        ZB_READ_CONFIGURATION_RSP response = (ZB_READ_CONFIGURATION_RSP) sendSynchrouns(commandInterface,
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

    @Override
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
            AF_REGISTER_SRSP response = (AF_REGISTER_SRSP) sendSynchrouns(commandInterface, request);
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
                try {
                    ZDO_STATE_CHANGE_IND p = ((ZDO_STATE_CHANGE_IND) packet);
                    switch (p.State) {
                        case 0:
                            logger.trace("Initialized - not started automatically");
                            break;
                        case 1:
                            logger.trace("Initialized - not connected to anything");
                            break;
                        case 2:
                            logger.trace("Discovering PANs to join or waiting for permit join");
                            break;
                        case 3:
                            logger.trace("Joining a PAN");
                            break;
                        case 4:
                            logger.trace("Rejoining a PAN, only for end-devices");
                            break;
                        case 5:
                            logger.trace("Joined but not yet authenticated by trust center");
                            break;
                        case 6:
                            logger.trace("Started as device after authentication");
                            break;
                        case 7:
                            logger.trace("Device joined, authenticated and is a router");
                            break;
                        case 8:
                            logger.trace("Starting as ZigBee Coordinator");
                            break;
                        case 9:
                            logger.debug("Started as ZigBee Coordinator");
                            setState(DriverStatus.NETWORK_READY);
                            break;
                        case 10:
                            logger.debug("Device has lost information about its parent");
                            break;
                        default:
                            break;
                    }
                } catch (Exception ex) {
                    // ignored
                }
            }
        }

        @Override
        public void receivedUnclaimedSynchronousCommandResponse(ZToolPacket packet) {

        }

    }

    private static class AFMessageListenerFilter implements AsynchronousCommandListener {
        /**
         * The logger.
         */
        private static final Logger LOGGER = LoggerFactory.getLogger(AFMessageListenerFilter.class);

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
                for (ApplicationFrameworkMessageListener l : localCopy) {
                    try {
                        l.notify(msg);
                    } catch (final Exception e) {
                        LOGGER.error("Error AF message listener notify.", e);
                    }
                }

                /*
                 * ZCLFrame frame = new ZCLFrame(new ClusterMessageImpl(msg.getData(), msg.getClusterId()));
                 * logger.debug("Received: [ ZCL Header: " + ByteUtils.toBase16(frame.getHeader().toByte())
                 * + ", ZCL Payload: " + ByteUtils.toBase16(frame.getPayload())
                 * + "]");
                 */
            }
        }

        @Override
        public void receivedUnclaimedSynchronousCommandResponse(ZToolPacket packet) {
            // No need to handle unclaimed responses here
        }

    }
}
