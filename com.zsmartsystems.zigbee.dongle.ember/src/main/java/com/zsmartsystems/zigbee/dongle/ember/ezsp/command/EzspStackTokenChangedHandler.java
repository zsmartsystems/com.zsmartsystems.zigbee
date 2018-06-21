/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;

/**
 * Class to implement the Ember EZSP command <b>stackTokenChangedHandler</b>.
 * <p>
 * A callback invoked to inform the application that a stack token has changed.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspStackTokenChangedHandler extends EzspFrameResponse {
    public static final int FRAME_ID = 0x0D;

    /**
     * The address of the stack token that has changed.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     */
    private int tokenAddress;

    /**
     * Response and Handler constructor
     */
    public EzspStackTokenChangedHandler(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        tokenAddress = deserializer.deserializeUInt16();
    }

    /**
     * The address of the stack token that has changed.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     *
     * @return the current tokenAddress as {@link int}
     */
    public int getTokenAddress() {
        return tokenAddress;
    }

    /**
     * The address of the stack token that has changed.
     *
     * @param tokenAddress the tokenAddress to set as {@link int}
     */
    public void setTokenAddress(int tokenAddress) {
        this.tokenAddress = tokenAddress;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(56);
        builder.append("EzspStackTokenChangedHandler [tokenAddress=");
        builder.append(tokenAddress);
        builder.append(']');
        return builder.toString();
    }
}
