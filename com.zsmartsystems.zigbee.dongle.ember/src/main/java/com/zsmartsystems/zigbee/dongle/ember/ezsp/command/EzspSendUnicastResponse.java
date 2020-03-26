/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * Class to implement the Ember EZSP command <b>sendUnicast</b>.
 * <p>
 * Sends a unicast message as per the ZigBee specification. The message will arrive at its
 * destination only if there is a known route to the destination node. Setting the
 * ENABLE_ROUTE_DISCOVERY option will cause a route to be discovered if none is known. Setting
 * the FORCE_ROUTE_DISCOVERY option will force route discovery. Routes to end-device
 * children of the local node are always known. Setting the APS_RETRY option will cause the
 * message to be retransmitted until either a matching acknowledgement is received or three
 * transmissions have been made.
 * <p>
 * <b>Note:</b> Using the FORCE_ROUTE_DISCOVERY option will cause the first
 * transmission to be consumed by a route request as part of discovery, so the application
 * payload of this packet will not reach its destination on the first attempt. If you want the
 * packet to reach its destination, the APS_RETRY option must be set so that another attempt is
 * made to transmit the message with its application payload after the route has been
 * constructed.
 * <p>
 * <b>Note:</b> When sending fragmented messages, the stack will only assign a new APS
 * sequence number for the first fragment of the message (i.e., EMBER_APS_OPTION_FRAGMENT is
 * set and the low-order byte of the groupId field in the APS frame is zero). For all subsequent
 * fragments of the same message, the application must set the sequence number field in the APS
 * frame to the sequence number assigned by the stack to the first fragment.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspSendUnicastResponse extends EzspFrameResponse {
    public static final int FRAME_ID = 0x34;

    /**
     * An EmberStatus value indicating success or the reason for failure.
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
    public EzspSendUnicastResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        status = deserializer.deserializeEmberStatus();
        sequence = deserializer.deserializeUInt8();
    }

    /**
     * An EmberStatus value indicating success or the reason for failure.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     *
     * @return the current status as {@link EmberStatus}
     */
    public EmberStatus getStatus() {
        return status;
    }

    /**
     * An EmberStatus value indicating success or the reason for failure.
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
        final StringBuilder builder = new StringBuilder(101);
        builder.append("EzspSendUnicastResponse [networkId=");
        builder.append(networkId);
        builder.append(", status=");
        builder.append(status);
        builder.append(", sequence=");
        builder.append(String.format("%02X", sequence));
        builder.append(']');
        return builder.toString();
    }
}
