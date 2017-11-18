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
public abstract class AshFrameRstAck extends AshFrame {
    protected int version;
    protected int resetCode;
    protected AshErrorCode errorCode;

    /**
     * Constructor to create an ASH Reset frame.
     *
     */
    public AshFrameRstAck() {
        this.frameType = FrameType.RSTACK;
    }

    /**
     * Constructor to create an ASH frame from a byte buffer.
     *
     * @param buffer
     */
    public AshFrameRstAck(int[] frameBuffer) {
        this.frameType = FrameType.RSTACK;

        this.version = frameBuffer[1];
        this.resetCode = frameBuffer[2];
        this.errorCode = AshErrorCode.getAshErrorCode(this.resetCode);
    }

    public abstract int getVersion();

    public abstract int getResetCode();

    public abstract AshErrorCode getResetType();

}
