/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeEnqueueSendDataRequest extends ConBeeFrameRequest {
    private ConBeeAddressMode destinationAddressMode;
    private int radius;
    private int txOptions = 0x04;
    private int sourceEndpoint;
    private int profileId;
    private int clusterId;
    private IeeeAddress destinationIeeeAddress;
    private ZigBeeAddress destinationAddress;
    private int[] adsuData;
    private int requestId;

    @Override
    public int[] getOutputBuffer() {
        super.getOutputBuffer();

        int payloadLen = adsuData.length + 12; // Should this be 12?????

        // Account for the address size
        payloadLen += destinationAddressMode == ConBeeAddressMode.IEEE ? 8 : 2;

        // Account for the endpoint
        payloadLen += destinationAddressMode == ConBeeAddressMode.GROUP ? 0 : 1;

        serializeUInt8(APS_DATA_REQUEST);
        serializeUInt8(sequence);
        serializeUInt8(0);
        serializeUInt16(payloadLen + 7);
        serializeUInt16(payloadLen);
        serializeUInt8(requestId);
        serializeUInt8(0);
        serializeAddressMode(destinationAddressMode);
        switch (destinationAddressMode) {
            case GROUP:
                serializeUInt16(destinationAddress.getAddress());
                break;
            case IEEE:
                serializeUInt8Array(destinationIeeeAddress.getValue());
                serializeUInt8(((ZigBeeEndpointAddress) destinationAddress).getEndpoint());
                break;
            case NWK:
                serializeUInt16(destinationAddress.getAddress());
                serializeUInt8(((ZigBeeEndpointAddress) destinationAddress).getEndpoint());
                break;
            default:
                break;
        }

        serializeUInt16(profileId);
        serializeUInt16(clusterId);
        serializeUInt8(sourceEndpoint);

        serializeUInt16(adsuData.length);
        serializeUInt8Array(adsuData);

        serializeUInt8(txOptions);
        serializeUInt8(radius);

        return copyOutputBuffer();
    }

    /**
     * @param destinationAddressMode the destinationAddressMode to set
     */
    public void setDestinationAddressMode(ConBeeAddressMode destinationAddressMode) {
        this.destinationAddressMode = destinationAddressMode;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * @param txOptions the txOptions to set
     */
    public void setTxOptions(int txOptions) {
        this.txOptions = txOptions;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    /**
     * @param sourceEndpoint the sourceEndpoint to set
     */
    public void setSourceEndpoint(int sourceEndpoint) {
        this.sourceEndpoint = sourceEndpoint;
    }

    /**
     * @param profileId the profileId to set
     */
    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    /**
     * @param clusterId the clusterId to set
     */
    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    /**
     * @param destinationIeeeAddress the destinationIeeeAddress to set
     */
    public void setDestinationIeeeAddress(IeeeAddress destinationIeeeAddress) {
        this.destinationIeeeAddress = destinationIeeeAddress;
    }

    /**
     * @param destinationAddress the destinationAddress to set
     */
    public void setDestinationAddress(ZigBeeAddress destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    /**
     * @param adsuData the adsuData to set
     */
    public void setAdsuData(int[] adsuData) {
        this.adsuData = adsuData;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(210);
        builder.append("EnqueueSendDataRequest [sequence=");
        builder.append(sequence);

        builder.append(", sourceEndpoint=");
        builder.append(sourceEndpoint);

        builder.append(", destinationAddress=");
        builder.append(destinationAddressMode);
        builder.append('(');
        switch (destinationAddressMode) {
            case IEEE:
                builder.append(destinationIeeeAddress);
                break;
            case GROUP:
                builder.append(destinationAddress.getAddress());
                break;
            case NWK:
                builder.append(destinationAddress);
                break;
            default:
                builder.append("unknown");
                break;
        }

        builder.append("), txOptions=");
        builder.append(txOptions);
        builder.append(", radius=");
        builder.append(radius);
        builder.append(", clusterId=");
        builder.append(clusterId);
        builder.append(", requestId=");
        builder.append(requestId);
        builder.append(", profileId=");
        builder.append(profileId);

        builder.append(", data=");

        if (adsuData != null) {
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
