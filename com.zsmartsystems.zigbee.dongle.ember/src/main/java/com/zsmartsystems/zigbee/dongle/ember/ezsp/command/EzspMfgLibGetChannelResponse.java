/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;

/**
 * Class to implement the Ember EZSP command <b>setConfigurationValue</b>.
 * <p>
 * Returns the current radio channel, as previously set via mfglibSetChannel().
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 */
public class EzspMfgLibGetChannelResponse extends EzspFrameResponse {
    public static int FRAME_ID = 0x8b;

    /**
     * The current channel.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EzspStatus}
     */
    private int channel;

    /**
     * Response and Handler constructor
     */
    public EzspMfgLibGetChannelResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        channel = deserializer.deserializeUInt8();
    }



    public int getChannel() {
		return channel;
	}



	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(61);
        builder.append("EzspMfgLibGetChannelResponse [channel=");
        builder.append(channel);
        builder.append(']');
        return builder.toString();
    }
}
