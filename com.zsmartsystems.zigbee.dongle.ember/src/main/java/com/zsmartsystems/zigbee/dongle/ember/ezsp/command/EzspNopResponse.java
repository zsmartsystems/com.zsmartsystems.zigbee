/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;

/**
 * Class to implement the Ember EZSP command <b>nop</b>.
 * <p>
 * A command which does nothing. The Host can use this to set the sleep mode or check the status of
 * the NCP.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspNopResponse extends EzspFrameResponse {
    public static final int FRAME_ID = 0x05;

    /**
     * Response and Handler constructor
     */
    public EzspNopResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(43);
        builder.append("EzspNopResponse [networkId=");
        builder.append(networkId);
        builder.append(']');
        return builder.toString();
    }
}
