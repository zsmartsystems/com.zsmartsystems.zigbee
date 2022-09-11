/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * Class to implement the Ember EZSP command <b>sendMulticast</b>.
 * <p>
 * Sends a multicast message to all endpoints that share a specific multicast ID and are within a
 * specified number of hops of the sender.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspSendMulticastResponse extends EzspFrameResponse {
    public static final int FRAME_ID = 0x38;

    /**
     * An EmberStatus value. For any result other than EMBER_SUCCESS, the message will not be sent.
     * 	EMBER_SUCCESS - The message has been submitted for transmission.
     * EMBER_INVALID_BINDING_INDEX - The bindingTableIndex refers to a non-multicast binding.
     * EMBER_NETWORK_DOWN - The node is not part of a network. EMBER_MESSAGE_TOO_LONG - The
     * message is too large to fit in a MAC layer frame. EMBER_NO_BUFFERS - The free packet buffer
     * pool is empty. EMBER_NETWORK_BUSY - Insufficient resources available in Network or MAC
     * layers to send message.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     */
    private EmberStatus status;

    /**
     * The sequence number that will be used when this message is transmitted.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int sequence;

    /**
     * Response and Handler constructor
     */
    public EzspSendMulticastResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        status = deserializer.deserializeEmberStatus();
        sequence = deserializer.deserializeUInt8();
    }

    /**
     * An EmberStatus value. For any result other than EMBER_SUCCESS, the message will not be sent.
     * 	EMBER_SUCCESS - The message has been submitted for transmission.
     * EMBER_INVALID_BINDING_INDEX - The bindingTableIndex refers to a non-multicast binding.
     * EMBER_NETWORK_DOWN - The node is not part of a network. EMBER_MESSAGE_TOO_LONG - The
     * message is too large to fit in a MAC layer frame. EMBER_NO_BUFFERS - The free packet buffer
     * pool is empty. EMBER_NETWORK_BUSY - Insufficient resources available in Network or MAC
     * layers to send message.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     *
     * @return the current status as {@link EmberStatus}
     */
    public EmberStatus getStatus() {
        return status;
    }

    /**
     * An EmberStatus value. For any result other than EMBER_SUCCESS, the message will not be sent.
     * 	EMBER_SUCCESS - The message has been submitted for transmission.
     * EMBER_INVALID_BINDING_INDEX - The bindingTableIndex refers to a non-multicast binding.
     * EMBER_NETWORK_DOWN - The node is not part of a network. EMBER_MESSAGE_TOO_LONG - The
     * message is too large to fit in a MAC layer frame. EMBER_NO_BUFFERS - The free packet buffer
     * pool is empty. EMBER_NETWORK_BUSY - Insufficient resources available in Network or MAC
     * layers to send message.
     *
     * @param status the status to set as {@link EmberStatus}
     */
    public void setStatus(EmberStatus status) {
        this.status = status;
    }

    /**
     * The sequence number that will be used when this message is transmitted.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current sequence as {@link int}
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * The sequence number that will be used when this message is transmitted.
     *
     * @param sequence the sequence to set as {@link int}
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(103);
        builder.append("EzspSendMulticastResponse [networkId=");
        builder.append(networkId);
        builder.append(", status=");
        builder.append(status);
        builder.append(", sequence=");
        builder.append(String.format("%02X", sequence));
        builder.append(']');
        return builder.toString();
    }
}
