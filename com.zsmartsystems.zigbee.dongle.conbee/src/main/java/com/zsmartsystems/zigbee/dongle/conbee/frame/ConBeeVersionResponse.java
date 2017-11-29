/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
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
public class ConBeeVersionResponse extends ConBeeFrameResponse {
    private int version;

    ConBeeVersionResponse(final int[] response) {
        super(response);

        if (deserializeUInt8() != VERSION) {
            throw new IllegalArgumentException();
        }

        sequence = deserializeUInt8();
        deserializeUInt8();
        deserializeUInt16();
        version = deserializeUInt32();
    }

    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(55);
        builder.append("QueryVersionResponse [sequence=");
        builder.append(sequence);
        builder.append(", version=");
        builder.append(version);

        builder.append(']');
        return builder.toString();
    }

}
