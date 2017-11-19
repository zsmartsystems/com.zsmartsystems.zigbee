/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ash;

/**
 * ASH Reset ACK Frame.
 * Informs the Host that the NCP has reset and the reason for the reset.
 *
 * @author Chris Jackson
 *
 */
public class AshFrameRstAck extends AshFrame {
    private int version;
    private int resetCode;
    private AshErrorCode errorCode;

    public AshFrameRstAck() {
        this.frameType = FrameType.RSTACK;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setResetCode(int resetCode) {
        this.resetCode = resetCode;
        errorCode = AshErrorCode.getAshErrorCode(resetCode);
    }

    public int getResetCode() {
        return resetCode;
    }

    public AshErrorCode getResetType() {
        return errorCode;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("AshFrameRstAck [version=");
        builder.append(version);
        builder.append(", resetCode=");
        builder.append(resetCode);
        AshErrorCode ashError = AshErrorCode.getAshErrorCode(resetCode);
        builder.append(": ");
        builder.append(ashError.getDescription());
        builder.append(']');
        return builder.toString();
    }
}
