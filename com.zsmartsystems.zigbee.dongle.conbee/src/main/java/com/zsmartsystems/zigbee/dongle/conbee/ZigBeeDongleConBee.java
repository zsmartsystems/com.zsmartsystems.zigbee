/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeGroupAddress;
import com.zsmartsystems.zigbee.ZigBeeKey;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager.ZigBeeInitializeResponse;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeAddressMode;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeDeviceState;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeDeviceStateChanged;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeDeviceStateRequest;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeDeviceStateResponse;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeEnqueueSendDataRequest;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeFrame;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeNetworkParameter;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeNetworkState;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeReadParameterRequest;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeReadParameterResponse;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeReadReceivedDataResponse;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeStatus;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeVersionRequest;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeVersionResponse;
import com.zsmartsystems.zigbee.dongle.conbee.transaction.ConBeeSingleResponseTransaction;
import com.zsmartsystems.zigbee.transport.TransportConfig;
import com.zsmartsystems.zigbee.transport.TransportConfigOption;
import com.zsmartsystems.zigbee.transport.TransportConfigResult;
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
     * The serial port used to connect to the dongle
     */
    private ZigBeePort serialPort;

    /**
     * The handler that processes the low level communication with the ConBee
     */
    private ConBeeFrameHandler conbeeHandler;

    /**
     * The current network state of the ConBee stack
     */
    private ConBeeNetworkState currentNetworkState = null;

    /**
     * The firmware version in the ConBee
     */
    private String firmwareVersion;

    /**
     * The IeeeAddress of the ConBee
     */
    private IeeeAddress ieeeAddress;

    /**
     * Flag to remember that initialisation is completed to we can avoid signalling ONLINE state prematurely.
     */
    private boolean initialisationComplete = false;

    /**
     * Constructor to configure the port interface.
     *
     * @param serialPort the serial port
     */
    public ZigBeeDongleConBee(final ZigBeePort serialPort) {
        this.serialPort = serialPort;
    }

    @Override
    public ZigBeeInitializeResponse initialize() {
        logger.debug("ConBee transport initialize");

        zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);

        if (!serialPort.open()) {
            logger.error("Unable to open ConBee serial port");
            return ZigBeeInitializeResponse.FAILED;
        }

        conbeeHandler = new ConBeeFrameHandler(serialPort, this);

        ConBeeVersionRequest versionRequest = new ConBeeVersionRequest();
        ConBeeVersionResponse versionResponse = (ConBeeVersionResponse) conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(versionRequest, ConBeeVersionResponse.class))
                .getResponse();
        firmwareVersion = String.format("%08X", versionResponse.getVersion());

        ConBeeReadParameterRequest readParameter;
        ConBeeReadParameterResponse readResponse;

        readParameter = new ConBeeReadParameterRequest();
        readParameter.setParameter(ConBeeNetworkParameter.DEVICE_TYPE);
        conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(readParameter, ConBeeReadParameterResponse.class));

        readParameter = new ConBeeReadParameterRequest();
        readParameter.setParameter(ConBeeNetworkParameter.MAC_ADDRESS);
        readResponse = (ConBeeReadParameterResponse) conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(readParameter, ConBeeReadParameterResponse.class))
                .getResponse();
        ieeeAddress = (IeeeAddress) readResponse.getValue();

        readParameter = new ConBeeReadParameterRequest();
        readParameter.setParameter(ConBeeNetworkParameter.NWK_PANID);
        readResponse = (ConBeeReadParameterResponse) conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(readParameter, ConBeeReadParameterResponse.class))
                .getResponse();

        readParameter = new ConBeeReadParameterRequest();
        readParameter.setParameter(ConBeeNetworkParameter.APS_EXTENDED_PANID);
        conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(readParameter, ConBeeReadParameterResponse.class));

        readParameter = new ConBeeReadParameterRequest();
        readParameter.setParameter(ConBeeNetworkParameter.NWK_ADDRESS);
        conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(readParameter, ConBeeReadParameterResponse.class));

        readParameter = new ConBeeReadParameterRequest();
        readParameter.setParameter(ConBeeNetworkParameter.NWK_EXTENDED_PANID);
        conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(readParameter, ConBeeReadParameterResponse.class));

        readParameter = new ConBeeReadParameterRequest();
        readParameter.setParameter(ConBeeNetworkParameter.CURRENT_CHANNEL);
        conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(readParameter, ConBeeReadParameterResponse.class));

        ConBeeDeviceStateRequest stateRequest = new ConBeeDeviceStateRequest();
        conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(stateRequest, ConBeeDeviceStateResponse.class));

        initialisationComplete = true;

        return ZigBeeInitializeResponse.JOINED;
    }

    @Override
    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    @Override
    public int getZigBeeChannel() {
        ConBeeReadParameterRequest readParameter = new ConBeeReadParameterRequest();
        readParameter.setParameter(ConBeeNetworkParameter.CURRENT_CHANNEL);
        ConBeeReadParameterResponse response = (ConBeeReadParameterResponse) conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(readParameter, ConBeeReadParameterResponse.class))
                .getResponse();

        return (int) response.getValue();
    }

    @Override
    public boolean setZigBeeChannel(int channel) {
        return false;
    }

    @Override
    public int getZigBeePanId() {
        ConBeeReadParameterRequest readParameter = new ConBeeReadParameterRequest();
        readParameter.setParameter(ConBeeNetworkParameter.NWK_PANID);
        ConBeeReadParameterResponse response = (ConBeeReadParameterResponse) conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(readParameter, ConBeeReadParameterResponse.class))
                .getResponse();

        return (int) response.getValue();
    }

    @Override
    public boolean setZigBeePanId(int panId) {
        // Can not set the PAN ID with the ConBee
        return false;
    }

    @Override
    public ExtendedPanId getZigBeeExtendedPanId() {
        ConBeeReadParameterRequest readParameter = new ConBeeReadParameterRequest();
        readParameter.setParameter(ConBeeNetworkParameter.NWK_EXTENDED_PANID);
        ConBeeReadParameterResponse response = (ConBeeReadParameterResponse) conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(readParameter, ConBeeReadParameterResponse.class))
                .getResponse();

        return (ExtendedPanId) response.getValue();
    }

    @Override
    public boolean setZigBeeExtendedPanId(ExtendedPanId panId) {
        // Can not set the Extended PAN ID with the ConBee
        return false;
    }

    @Override
    public boolean setZigBeeNetworkKey(ZigBeeKey key) {
        return false;
    }

    @Override
    public void updateTransportConfig(TransportConfig configuration) {
        for (TransportConfigOption option : configuration.getOptions()) {
            try {
                switch (option) {
                    default:
                        configuration.setResult(option, TransportConfigResult.ERROR_UNSUPPORTED);
                        logger.debug("Unsupported configuration option \"{}\" in Telegesis dongle", option);
                        break;
                }
            } catch (ClassCastException e) {
                configuration.setResult(option, TransportConfigResult.ERROR_INVALID_VALUE);
            }
        }
    }

    @Override
    public boolean setTcLinkKey(ZigBeeKey key) {
        return false;
    }

    @Override
    public boolean startup(boolean reinitialize) {
        logger.debug("ConBee transport startup");

        ConBeeDeviceStateRequest stateRequest = new ConBeeDeviceStateRequest();
        ConBeeDeviceStateResponse stateResponse = (ConBeeDeviceStateResponse) conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(stateRequest, ConBeeDeviceStateResponse.class))
                .getResponse();

        stateResponse.getDeviceState();

        return true;
    }

    @Override
    public void shutdown() {
        if (conbeeHandler == null) {
            return;
        }
        conbeeHandler.setClosing();
        zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
        serialPort.close();
        conbeeHandler.close();
        logger.debug("ConBee dongle shutdown.");
    }

    @Override
    public String getVersionString() {
        return firmwareVersion;
    }

    @Override
    public void sendCommand(final ZigBeeApsFrame apsFrame) {
        ConBeeEnqueueSendDataRequest request = new ConBeeEnqueueSendDataRequest();

        request.setRequestId(apsFrame.getSequence());
        request.setClusterId(apsFrame.getCluster());
        switch (apsFrame.getAddressMode()) {
            case DEVICE:
                request.setDestinationAddress(
                        new ZigBeeEndpointAddress(apsFrame.getDestinationAddress(), apsFrame.getDestinationEndpoint()));
                request.setDestinationAddressMode(ConBeeAddressMode.NWK);
                if (apsFrame.getDestinationAddress() > 0xfff8) {
                    //
                    request.setTxOptions(0);
                }
                break;
            case GROUP:
                request.setDestinationAddress(new ZigBeeGroupAddress(apsFrame.getDestinationAddress()));
                request.setDestinationAddressMode(ConBeeAddressMode.GROUP);
                break;
            default:
                break;
        }
        request.setProfileId(apsFrame.getProfile());
        request.setRadius(apsFrame.getRadius());
        request.setSourceEndpoint(apsFrame.getSourceEndpoint());
        // request.setTxOptions(txOptions);

        request.setAdsuData(apsFrame.getPayload());

        conbeeHandler.queueFrame(request);
    }

    @Override
    public void setZigBeeTransportReceive(ZigBeeTransportReceive zigbeeTransportReceive) {
        this.zigbeeNetworkReceive = zigbeeTransportReceive;
    }

    /**
     *
     * @param frame the received {@link ConBeeFrame}
     */
    public void receiveIncomingFrame(ConBeeFrame frame) {
        if (frame instanceof ConBeeReadReceivedDataResponse) {
            ConBeeReadReceivedDataResponse receivedData = (ConBeeReadReceivedDataResponse) frame;

            if (receivedData.getStatus() != ConBeeStatus.SUCCESS) {
                return;
            }

            ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
            // apsFrame.setApsCounter(emberApsFrame.getSequence());
            apsFrame.setCluster(receivedData.getClusterId());
            apsFrame.setDestinationEndpoint(receivedData.getDestinationEndpoint());
            apsFrame.setProfile(receivedData.getProfileId());
            apsFrame.setSourceEndpoint(receivedData.getSourceEndpoint());

            // receivedData.getSourceAddressMode()
            // apsFrame.sets
            apsFrame.setSourceAddress(receivedData.getSourceNetworkAddress());
            apsFrame.setPayload(receivedData.getAdsuData());
            zigbeeNetworkReceive.receiveCommand(apsFrame);
            return;
        }

        if (frame instanceof ConBeeDeviceStateChanged || frame instanceof ConBeeDeviceStateResponse) {
            ConBeeDeviceState deviceState;
            if (frame instanceof ConBeeDeviceStateChanged) {
                deviceState = ((ConBeeDeviceStateChanged) frame).getDeviceState();
            } else {
                deviceState = ((ConBeeDeviceStateResponse) frame).getDeviceState();

            }

            if (!initialisationComplete || deviceState.getNetworkState() == currentNetworkState) {
                return;
            }
            currentNetworkState = deviceState.getNetworkState();
            switch (deviceState.getNetworkState()) {
                case NET_CONNECTED:
                    zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.ONLINE);
                    break;
                case NET_JOINING:
                case NET_LEAVING:
                case NET_OFFLINE:
                    zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
                    break;
                default:
                    break;
            }
        }
    }

}
