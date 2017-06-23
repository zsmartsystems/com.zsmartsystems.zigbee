package com.zsmartsystems.zigbee.dongle.conbee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.ZigBeeException;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager.ZigBeeInitializeResponse;
import com.zsmartsystems.zigbee.transport.ZigBeePort;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;

/**
 * ZigBee Dongle for the Dresden ConBee.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleConBee implements ZigBeeTransportTransmit {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeDongleConBee.class);

    /**
     * The reference to the network
     */
    private ZigBeeTransportReceive zigbeeNetworkReceive;

    /**
     * The Ember version used in this system. Set during initialisation and saved in case the client is interested.
     */
    private String versionString = "Unknown";

    /**
     * Constructor to configure the port interface.
     *
     * @param serialPort
     *            the serial port
     */
    public ZigBeeDongleConBee(final ZigBeePort serialPort) {
    }

    @Override
    public ZigBeeInitializeResponse initialize() {
        logger.debug("ConBee transport initialize");

        zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);

        return ZigBeeInitializeResponse.JOINED;
    }

    @Override
    public int getZigBeeChannel() {
        return 0;
    }

    @Override
    public boolean setZigBeeChannel(int channel) {
        return false;
    }

    @Override
    public int getZigBeePanId() {
        return 0;
    }

    @Override
    public boolean setZigBeePanId(int panId) {
        return false;
    }

    @Override
    public ExtendedPanId getZigBeeExtendedPanId() {
        return null;
    }

    @Override
    public boolean setZigBeeExtendedPanId(ExtendedPanId panId) {
        return false;
    }

    @Override
    public boolean setZigBeeSecurityKey(int[] keyData) {
        return false;
    }

    @Override
    public boolean startup(boolean reinitialize) {
        logger.debug("ConBee transport startup");

        return true;
    }

    @Override
    public void shutdown() {
    }

    @Override
    public String getVersionString() {
        return versionString;
    }

    @Override
    public void sendCommand(final ZigBeeApsFrame apsFrame) throws ZigBeeException {
    }

    @Override
    public void setZigBeeTransportReceive(ZigBeeTransportReceive zigbeeTransportReceive) {
        this.zigbeeNetworkReceive = zigbeeTransportReceive;
    }

}
