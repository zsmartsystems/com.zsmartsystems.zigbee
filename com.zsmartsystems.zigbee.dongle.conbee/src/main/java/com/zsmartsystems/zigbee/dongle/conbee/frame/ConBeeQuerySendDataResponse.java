/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

import java.util.Arrays;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeGroupAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeQuerySendDataResponse extends ConBeeFrameResponse {
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
        deserializeUInt8();
        deserializeUInt16();
        deserializeUInt16();
        state = deserializeDeviceState();

        requestId = deserializeUInt8();

        destinationAddressMode = deserializeAddressMode();
        switch (destinationAddressMode) {
            case GROUP:
                destinationAddress = new ZigBeeGroupAddress(deserializeUInt16());
                break;
            case NWK:
                destinationAddress = new ZigBeeEndpointAddress(deserializeUInt16());
                break;
            case IEEE:
                destinationIeeeAddress = new IeeeAddress(Arrays.copyOfRange(buffer, length, length + 8));
                break;
            default:
                break;
        }
        destinationEndpoint = deserializeUInt8();
        if (destinationAddressMode == ConBeeAddressMode.NWK) {
            ((ZigBeeEndpointAddress) destinationAddress).setEndpoint(destinationEndpoint);
        }

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
        StringBuilder builder = new StringBuilder(130);
        builder.append("QuerySendDataResponse [sequence=");
        builder.append(sequence);
        builder.append(", networkState=");
        builder.append(state);

        builder.append(", destinationAddress=(");
        builder.append(destinationAddressMode);
        builder.append('=');
        if (destinationAddressMode == ConBeeAddressMode.IEEE) {
            builder.append(destinationIeeeAddress);
        } else {
            builder.append(destinationAddress);
        }
        builder.append("), requestId=");
        builder.append(requestId);
        builder.append(']');
        return builder.toString();
    }

}
