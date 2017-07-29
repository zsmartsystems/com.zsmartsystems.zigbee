package com.zsmartsystems.zigbee.dongle.conbee.frame;

import java.util.Arrays;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeGroupAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeQuerySendDataResponse extends ConBeeFrameResponse {
    private ConBeeDeviceState state;
    private int requestId;
    private ConBeeAddressMode destinationAddressMode;
    private ZigBeeAddress destinationAddress;
    private IeeeAddress destinationIeeeAddress;
    private int destinationEndpoint;

    ConBeeQuerySendDataResponse(final int[] response) {
        super(response);

        if (deserializeUInt8() != APS_DATA_CONFIRM) {
            throw new IllegalArgumentException();
        }

        sequence = deserializeUInt8();
        status = deserializeStatus();
        deserializeUInt16();
        state = deserializeDeviceState();

        requestId = deserializeUInt8();

        destinationAddressMode = deserializeAddressMode();
        switch (destinationAddressMode) {
            case GROUP:
                destinationAddress = new ZigBeeGroupAddress(deserializeUInt16());
                break;
            case NWK:
                destinationAddress = new ZigBeeDeviceAddress(deserializeUInt16());
                break;
            case IEEE:
                destinationIeeeAddress = new IeeeAddress(Arrays.copyOfRange(buffer, length, length + 8));
                break;
            default:
                break;
        }
        destinationEndpoint = deserializeUInt8();
        if (destinationAddressMode == ConBeeAddressMode.NWK) {
            ((ZigBeeDeviceAddress) destinationAddress).setEndpoint(destinationEndpoint);
        }

    }

    /**
     * @return the state
     */
    public ConBeeDeviceState getState() {
        return state;
    }

    /**
     * @return the requestId
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * @return the destinationAddressMode
     */
    public ConBeeAddressMode getDestinationAddressMode() {
        return destinationAddressMode;
    }

    /**
     * @return the destinationNetworkAddress
     */
    public ZigBeeAddress getDestinationNetworkAddress() {
        return destinationAddress;
    }

    /**
     * @return the destinationIeeeAddress
     */
    public IeeeAddress getDestinationIeeeAddress() {
        return destinationIeeeAddress;
    }

    /**
     * @return the destinationEndpoint
     */
    public int getDestinationEndpoint() {
        return destinationEndpoint;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EnqueueSendDataResponse [sequence=");
        builder.append(sequence);
        builder.append(", status=");
        builder.append(status);
        builder.append(", networkState=");
        builder.append(state);

        builder.append(", destinationAddress=(");
        builder.append(destinationAddressMode);
        builder.append("=");
        if (destinationAddressMode == ConBeeAddressMode.IEEE) {
            builder.append(destinationIeeeAddress);
        } else {
            builder.append(destinationAddress);
        }
        builder.append(", requestId=");
        builder.append(requestId);
        builder.append(']');
        return builder.toString();
    }

}
