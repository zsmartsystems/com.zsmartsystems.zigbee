/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeWriteParameterResponse extends ConBeeFrameResponse {
    private ConBeeNetworkParameter parameter;

    ConBeeWriteParameterResponse(final int[] response) {
        super(response);

        if (deserializeUInt8() != WRITE_PARAMETER) {
            throw new IllegalArgumentException();
        }
        sequence = deserializeUInt8();
        status = deserializeStatus();
        deserializeUInt16();
        deserializeUInt16();
        parameter = ConBeeNetworkParameter.getParameterType(deserializeUInt8());
    }

    /**
     * @return the parameter
     */
    public ConBeeNetworkParameter getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(40);
        builder.append("WriteParameterResponse [parameter=");
        builder.append(parameter);
        builder.append(']');
        return builder.toString();
    }

}
