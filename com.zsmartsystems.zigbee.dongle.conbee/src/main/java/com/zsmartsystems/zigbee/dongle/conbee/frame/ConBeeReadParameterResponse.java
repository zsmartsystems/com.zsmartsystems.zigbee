/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;

/**
 * If the response status is SUCCESS the parameter data is included in the response according to its definition in Table
 * 6-1: Parameters. If the status is UNSUPPORTED the ‘Length’ field is 0 and the fields ‘Parameter ID’ and ‘Parameter’
 * aren’t included in the response.
 *
 * @author Chris Jackson
 *
 */
public class ConBeeReadParameterResponse extends ConBeeFrameResponse {
    private ConBeeNetworkParameter parameter;
    private int[] value;

    ConBeeReadParameterResponse(final int[] response) {
        super(response);

        if (deserializeUInt8() != READ_PARAMETER) {
            throw new IllegalArgumentException();
        }
        sequence = deserializeUInt8();
        status = deserializeStatus();
        deserializeUInt16();
        int size = deserializeUInt16() - 1;
        parameter = ConBeeNetworkParameter.getParameterType(deserializeUInt8());
        value = deserializeUInt8Array(size);
    }

    public ConBeeNetworkParameter getParameter() {
        return parameter;
    }

    public Object getValue() {
        switch (parameter) {
            case APS_EXTENDED_PANID:
            case NWK_EXTENDED_PANID:
                return new ExtendedPanId(value);
            case CHANNEL_MASK:
                break;
            case CURRENT_CHANNEL:
                return value[0];
            case DEVICE_TYPE:
                break;
            case NETWORK_KEY:
                break;
            case NWK_ADDRESS:
                return value[0] + (value[1] << 8);
            case NWK_PANID:
                return value[0] + (value[1] << 8);
            case NWK_UPDATE_ID:
                break;
            case SECURITY_MODE:
                break;
            case MAC_ADDRESS:
            case TRUST_CENTRE_ADDRESS:
                return new IeeeAddress(value);
            default:
                break;
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(90);
        builder.append("ReadParameterResponse [sequence=");
        builder.append(sequence);
        builder.append(", status=");
        builder.append(status);
        builder.append(", parameter=");
        builder.append(parameter);
        builder.append(", value=");
        boolean first = true;
        for (int val : value) {
            if (!first) {
                builder.append(' ');
            }
            first = false;
            builder.append(String.format("%02X", val));
        }
        builder.append(']');
        return builder.toString();
    }

}
