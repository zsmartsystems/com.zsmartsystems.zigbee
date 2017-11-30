/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

import java.util.Arrays;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeReadReceivedDataResponse extends ConBeeFrameResponse {
    private ConBeeAddressMode destinationAddressMode;
    private int destinationNetworkAddress;
    private IeeeAddress destinationIeeeAddress;
    private int destinationEndpoint;
    private ConBeeAddressMode sourceAddressMode;
    private int sourceNetworkAddress;
    private IeeeAddress sourceIeeeAddress;
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
        deserializeUInt16(); // Frame length
        int plLength = deserializeUInt16(); // Payload length
        state = deserializeDeviceState();

        if (plLength == 1) {
            return;
        }

        destinationAddressMode = deserializeAddressMode();
        switch (destinationAddressMode) {
            case GROUP:
                destinationNetworkAddress = deserializeUInt16();
                break;
            case NWK:
                destinationNetworkAddress = deserializeUInt16();
                break;
            case IEEE:
                destinationIeeeAddress = new IeeeAddress(Arrays.copyOfRange(buffer, length, length + 8));
                break;
            default:
                break;
        }
        destinationEndpoint = deserializeUInt8();
        // if (destinationAddressMode == ConBeeAddressMode.NWK) {
        // ((ZigBeeDeviceAddress) destinationAddress).setEndpoint(destinationEndpoint);
        // }

        sourceAddressMode = deserializeAddressMode();
        switch (sourceAddressMode) {
            case GROUP:
                sourceNetworkAddress = deserializeUInt16();
                break;
            case NWK:
                sourceNetworkAddress = deserializeUInt16();
                break;
            case IEEE:
                sourceIeeeAddress = deserializeIeeeAddress();
                break;
            default:
                break;
        }
        sourceEndpoint = deserializeUInt8();
        // if (sourceAddressMode == ConBeeAddressMode.NWK) {
        // ((ZigBeeDeviceAddress) sourceAddress).setEndpoint(sourceEndpoint);
        // }

        profileId = deserializeUInt16();
        clusterId = deserializeUInt16();

        adsuLength = deserializeUInt16();

        adsuData = Arrays.copyOfRange(buffer, length, length + adsuLength);
        length += adsuLength;

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
     * @return the destinationAddressMode
     */
    public ConBeeAddressMode getDestinationAddressMode() {
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
    public IeeeAddress getDestinationIeeeAddress() {
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
    public ConBeeAddressMode getSourceAddressMode() {
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
    public IeeeAddress getSourceIeeeAddress() {
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
        StringBuilder builder = new StringBuilder(210);
        builder.append("ReadReceivedDataResponse [sequence=");
        builder.append(sequence);
        builder.append(", status=");
        builder.append(status);
        builder.append(", networkState=");
        builder.append(state);
        builder.append(", sourceAddress=");
        builder.append(sourceAddressMode);
        builder.append('(');
        if (sourceAddressMode == ConBeeAddressMode.IEEE) {
            builder.append(sourceIeeeAddress);
        } else {
            builder.append(sourceNetworkAddress);
        }
        builder.append("), destinationAddress=");
        builder.append(destinationAddressMode);
        builder.append('(');
        if (destinationAddressMode == ConBeeAddressMode.IEEE) {
            builder.append(destinationIeeeAddress);
        } else {
            builder.append(destinationNetworkAddress);
        }

        builder.append("), profileId=");
        builder.append(String.format("%04X", profileId));
        builder.append(", clusterId=");
        builder.append(String.format("%04X", clusterId));
        builder.append(", lqi=");
        builder.append(lqi);
        builder.append(", rssi=");
        builder.append(rssi);

        builder.append(", data=");
        if (adsuData == null) {
            builder.append("null");
        } else {
            boolean first = true;
            for (int val : adsuData) {
                if (!first) {
                    builder.append(' ');
                }
                first = false;
                builder.append(String.format("%02X", val));
            }
        }
        builder.append(']');
        return builder.toString();
    }

}
