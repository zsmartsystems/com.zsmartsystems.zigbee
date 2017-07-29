package com.zsmartsystems.zigbee.dongle.conbee.frame;

import java.util.Arrays;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeReadReceivedDataResponse extends ConBeeFrameResponse {
    private ConBeeNetworkState state;
    private int destinationAddressMode;
    private int destinationNetworkAddress;
    private int[] destinationIeeeAddress;
    private int destinationEndpoint;
    private int sourceAddressMode;
    private int sourceNetworkAddress;
    private int[] sourceIeeeAddress;
    private int sourceEndpoint;
    private int profileId;
    private int clusterId;
    private int adsuLength;
    private int[] adsuData;
    private int lqi;
    private int rssi;

    ConBeeReadReceivedDataResponse(final int[] response) {
        super(response);

        if (deserializeUInt8() != APS_DATA_INDICATION) {
            throw new IllegalArgumentException();
        }
        sequence = deserializeUInt8();
        status = deserializeStatus();
        deserializeUInt16();
        int size = deserializeUInt16() - 1;

        int tmp = deserializeUInt8();
        state = ConBeeNetworkState.values()[tmp & 0x03];

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

        sourceAddressMode = deserializeUInt8();
        switch (sourceAddressMode) {
            case 0x01:
            case 0x02:
                sourceNetworkAddress = deserializeUInt16();
                break;
            case 0x03:
                sourceIeeeAddress = Arrays.copyOfRange(buffer, length, length + 8);
                break;
        }
        sourceEndpoint = deserializeUInt8();

        profileId = deserializeUInt16();
        clusterId = deserializeUInt16();

        adsuLength = deserializeUInt16();

        adsuData = Arrays.copyOfRange(buffer, length, length + 8);

        deserializeUInt8(); // Reserved
        deserializeUInt8(); // Reserved

        lqi = deserializeUInt8();

        deserializeUInt8(); // Reserved
        deserializeUInt8(); // Reserved
        deserializeUInt8(); // Reserved
        deserializeUInt8(); // Reserved

        rssi = deserializeInt8();
    }

    /**
     * @return the state
     */
    public ConBeeNetworkState getState() {
        return state;
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

    /**
     * @return the sourceAddressMode
     */
    public int getSourceAddressMode() {
        return sourceAddressMode;
    }

    /**
     * @return the sourceNetworkAddress
     */
    public int getSourceNetworkAddress() {
        return sourceNetworkAddress;
    }

    /**
     * @return the sourceIeeeAddress
     */
    public int[] getSourceIeeeAddress() {
        return sourceIeeeAddress;
    }

    /**
     * @return the sourceEndpoint
     */
    public int getSourceEndpoint() {
        return sourceEndpoint;
    }

    /**
     * @return the profileId
     */
    public int getProfileId() {
        return profileId;
    }

    /**
     * @return the clusterId
     */
    public int getClusterId() {
        return clusterId;
    }

    /**
     * @return the adsuLength
     */
    public int getAdsuLength() {
        return adsuLength;
    }

    /**
     * @return the adsuData
     */
    public int[] getAdsuData() {
        return adsuData;
    }

    /**
     * @return the lqi
     */
    public int getLqi() {
        return lqi;
    }

    /**
     * @return the rssi
     */
    public int getRssi() {
        return rssi;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ReadReceivedDataResponse [sequence=");
        builder.append(sequence);
        builder.append(", parameter=");
        builder.append("");
        builder.append(", value=");
        boolean first = true;
        for (int val : adsuData) {
            if (first == false) {
                builder.append(' ');
            }
            first = false;
            builder.append(String.format("%02X", val));
        }
        builder.append(']');
        return builder.toString();
    }

}
