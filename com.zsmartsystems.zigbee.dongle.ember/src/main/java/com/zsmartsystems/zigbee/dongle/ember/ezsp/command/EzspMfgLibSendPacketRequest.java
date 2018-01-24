/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;

/**
 * Class to implement the Ember EZSP command <b>mfglibStart</b>.
 * <p>
 * Sends a single packet consisting of the following bytes: packetLength, packetContents[0], ... , packetContents[packetLength - 3], 
 * CRC[0], CRC[1]. The total number of bytes sent is packetLength + 1. The radio replaces the last two bytes of
 * packetContents[] with the 16-bit CRC for the packet.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 */
public class EzspMfgLibSendPacketRequest extends EzspFrameRequest {
    public static int FRAME_ID = 0x89;

    /**
     * The packet to send. Two bytes will be added for the 16-bit CRC.
     * The length of the packetContents parameter in bytes. Must be greater than 3 and less than 123.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     */
    private int[] messageContents;
    
    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspMfgLibSendPacketRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * The packet to send.
     * The length of the packetContents parameter in bytes. Must be greater than 3 and less than 123.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     *
     * @return the current messageContents as {@link int[]}
     */
    public int[] getMessageContents() {
        return messageContents;
    }

    /**
     * The packet to send.
     * The length of the packetContents parameter in bytes. Must be greater than 3 and less than 123.
     *
     * @param messageContents the messageContents to set as {@link int[]}
     */
    public void setMessageContents(int[] messageContents) {
        this.messageContents = messageContents;
    }    

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeUInt8(messageContents.length+2);
        serializer.serializeUInt8Array(messageContents);
        serializer.serializeUInt8(0); // CRC[0]
        serializer.serializeUInt8(0); // CRC[1]
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(85);
        builder.append("EzspMfgLibSendPacketRequest [messageContents=");
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
