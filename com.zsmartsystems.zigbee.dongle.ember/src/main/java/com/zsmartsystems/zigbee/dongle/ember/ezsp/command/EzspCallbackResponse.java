/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;

/**
 * Class to implement the Ember EZSP command <b>callback</b>.
 * <p>
 * Allows the NCP to respond with a pending callback.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspCallbackResponse extends EzspFrameResponse {
    public static final int FRAME_ID = 0x06;

    /**
     * Response and Handler constructor
     */
    public EzspCallbackResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(48);
        builder.append("EzspCallbackResponse [networkId=");
        builder.append(networkId);
        builder.append(']');
        return builder.toString();
    }
}
