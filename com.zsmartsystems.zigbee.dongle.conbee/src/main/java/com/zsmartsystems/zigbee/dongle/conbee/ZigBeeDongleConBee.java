package com.zsmartsystems.zigbee.dongle.conbee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeException;
import com.zsmartsystems.zigbee.ZigBeeGroupAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager.ZigBeeInitializeResponse;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeAddressMode;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeDeviceStateRequest;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeDeviceStateResponse;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeEnqueueSendDataRequest;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeNetworkParameter;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeReadParameterRequest;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeReadParameterResponse;
import com.zsmartsystems.zigbee.dongle.conbee.transaction.ConBeeSingleResponseTransaction;
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

    ConBeeFrameHandler conbeeHandler;

    /**
     * Constructor to configure the port interface.
     *
     * @param serialPort
     *            the serial port
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

        conbeeHandler = new ConBeeFrameHandler(serialPort.getInputStream(), serialPort.getOutputStream(), this);

        // State request seems to be necessary before we do anything else
        // ConBeeDeviceStateRequest stateRequest = new ConBeeDeviceStateRequest();
        // ConBeeDeviceStateResponse stateResponse = (ConBeeDeviceStateResponse) conbeeHandler
        // .sendTransaction(new ConBeeSingleResponseTransaction(stateRequest, ConBeeDeviceStateResponse.class))
        // .getResponse();

        ConBeeReadParameterRequest readParameter;
        readParameter = new ConBeeReadParameterRequest();
        readParameter.setParameter(ConBeeNetworkParameter.MAC_ADDRESS);
        ConBeeReadParameterResponse response = (ConBeeReadParameterResponse) conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(readParameter, ConBeeReadParameterResponse.class))
                .getResponse();

        readParameter = new ConBeeReadParameterRequest();
        readParameter.setParameter(ConBeeNetworkParameter.DEVICE_TYPE);
        conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(readParameter, ConBeeReadParameterResponse.class));

        readParameter = new ConBeeReadParameterRequest();
        readParameter.setParameter(ConBeeNetworkParameter.NWK_PANID);
        conbeeHandler
                .sendTransaction(new ConBeeSingleResponseTransaction(readParameter, ConBeeReadParameterResponse.class));

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

        return ZigBeeInitializeResponse.JOINED;
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
    public boolean setZigBeeSecurityKey(int[] keyData) {
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
    }

    @Override
    public String getVersionString() {
        // No version is available in ConBee
        return "";
    }

    @Override
    public void sendCommand(final ZigBeeApsFrame apsFrame) throws ZigBeeException {
        ConBeeEnqueueSendDataRequest request = new ConBeeEnqueueSendDataRequest();

        request.setRequestId(apsFrame.getSequence());
        request.setClusterId(apsFrame.getCluster());
        switch (apsFrame.getAddressMode()) {
            case DEVICE:
                request.setDestinationAddress(
                        new ZigBeeDeviceAddress(apsFrame.getDestinationAddress(), apsFrame.getDestinationEndpoint()));
                request.setDestinationAddressMode(ConBeeAddressMode.NWK);
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

}
