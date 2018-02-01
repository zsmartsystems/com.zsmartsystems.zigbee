/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;

/**
 * Class to implement the Ember EZSP command <b>incomingMessageHandler</b>.
 * <p>
 * A callback indicating a packet with a valid CRC has been received.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 */
public class EzspMfgLibRxHandler extends EzspFrameResponse {
    public static int FRAME_ID = 0x8e;

    /**
     * The link quality observed during the reception
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int linkQuality;

    /**
     * The energy level (in units of dBm) observed during the reception.
     * <p>
     * EZSP type is <i>int8s</i> - Java type is {@link int}
     */
    private int lastHopRssi;

    /**
     * The received packet. The last two bytes are the 16-bit CRC.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     */
    private int[] messageContents;

    /**
     * Response and Handler constructor
     */
    public EzspMfgLibRxHandler(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        linkQuality = deserializer.deserializeUInt8();
        lastHopRssi = deserializer.deserializeInt8S();
        int messageLength = deserializer.deserializeUInt8();
        messageContents= deserializer.deserializeUInt8Array(messageLength);
    }



    public static int getFRAME_ID() {
		return FRAME_ID;
	}



	public int getLinkQuality() {
		return linkQuality;
	}



	public int getLastHopRssi() {
		return lastHopRssi;
	}



	public int[] getMessageContents() {
		return messageContents;
	}



	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(254);
        builder.append("EzspMfgLibRxHandler [linkQuality=");
        builder.append(linkQuality);
        builder.append(", lastHopRssi=");
        builder.append(lastHopRssi);
        builder.append(", messageContents=");
        for (int c = 0; c < messageContents.length; c++) {
            if (c > 0) {
                builder.append(" ");
            }
            builder.append(String.format("%02X", messageContents[c]));
        }
        builder.append(']');
        return builder.toString();
    }
}
