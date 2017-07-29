package com.zsmartsystems.zigbee.dongle.conbee.frame;

import java.util.Arrays;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeQuerySendDataResponse extends ConBeeFrameResponse {
    private ConBeeNetworkState state;
    private int requestId;
    private int destinationAddressMode;
    private int destinationNetworkAddress;
    private int[] destinationIeeeAddress;
    private int destinationEndpoint;

    ConBeeQuerySendDataResponse(final int[] response) {
        super(response);

        if (deserializeUInt8() != APS_DATA_CONFIRM) {
            throw new IllegalArgumentException();
        }

        sequence = deserializeUInt8();
        status = deserializeStatus();
        deserializeUInt16();

        int tmp = deserializeUInt8();
        state = ConBeeNetworkState.values()[tmp & 0x03];

        requestId = deserializeUInt8();

        destinationAddressMode = deserializeUInt8();
        switch (destinationAddressMode) {
            case 0x01:
            case 0x02:
                destinationNetworkAddress = deserializeUInt16();
                break;
            case 0x03:
                destinationIeeeAddress = Arrays.copyOfRange(buffer, length, length + 8);
                break;
        }
        destinationEndpoint = deserializeUInt8();

    }

    /**
     * @return the state
     */
    public ConBeeNetworkState getState() {
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
    public int getDestinationAddressMode() {
        return destinationAddressMode;
    }

    /**
     * @return the destinationNetworkAddress
     */
    public int getDestinationNetworkAddress() {
        return destinationNetworkAddress;
    }

    /**
     * @return the destinationIeeeAddress
     */
    public int[] getDestinationIeeeAddress() {
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
        builder.append(", state=");
        builder.append(state);
        builder.append(']');
        return builder.toString();
    }

}
