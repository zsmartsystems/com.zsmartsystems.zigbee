/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ash.v2;

import com.zsmartsystems.zigbee.dongle.ember.ash.AshErrorCode;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameRstAck;

/**
 * ASH Reset ACK Frame.
 * Informs the Host that the NCP has reset and the reason for the reset.
 *
 * @author Chris Jackson
 *
 */
public class AshFrameRstAckV2 extends AshFrameRstAck {
    public AshFrameRstAckV2() {
        this.frameType = FrameType.RSTACK;
    }

    /**
     * Constructor to create an ASH frame from a byte buffer.
     *
     * @param buffer
     */
    public AshFrameRstAckV2(int[] frameBuffer) {
        this.frameType = FrameType.RSTACK;

        this.version = frameBuffer[1];
        this.resetCode = frameBuffer[2];
        this.errorCode = AshErrorCode.getAshErrorCode(this.resetCode);
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public int getResetCode() {
        return resetCode;
    }

    @Override
    public AshErrorCode getResetType() {
        return errorCode;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("AshFrameRstAck [version=");
        builder.append(version);
        builder.append(". resetCode=");
        builder.append(resetCode);
        AshErrorCode ashError = AshErrorCode.getAshErrorCode(resetCode);
        builder.append(ashError.getDescription());
        builder.append("]");
        return builder.toString();
    }
}
