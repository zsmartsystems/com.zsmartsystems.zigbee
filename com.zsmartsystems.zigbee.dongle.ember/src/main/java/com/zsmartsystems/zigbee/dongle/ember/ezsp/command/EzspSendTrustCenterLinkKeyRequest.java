/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>sendTrustCenterLinkKey</b>.
 * <p>
 * This function sends an APS TransportKey command containing the current trust center link
 * key. The node to which the command is sent is specified via the short and long address
 * arguments.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspSendTrustCenterLinkKeyRequest extends EzspFrameRequest {
    public static final int FRAME_ID = 0x67;

    /**
     * The short address of the node to which this command will be sent
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     */
    private int destinationNodeId;

    /**
     * The long address of the node to which this command will be sent
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     */
    private IeeeAddress destinationEui64;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspSendTrustCenterLinkKeyRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * The short address of the node to which this command will be sent
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     *
     * @return the current destinationNodeId as {@link int}
     */
    public int getDestinationNodeId() {
        return destinationNodeId;
    }

    /**
     * The short address of the node to which this command will be sent
     *
     * @param destinationNodeId the destinationNodeId to set as {@link int}
     */
    public void setDestinationNodeId(int destinationNodeId) {
        this.destinationNodeId = destinationNodeId;
    }

    /**
     * The long address of the node to which this command will be sent
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     *
     * @return the current destinationEui64 as {@link IeeeAddress}
     */
    public IeeeAddress getDestinationEui64() {
        return destinationEui64;
    }

    /**
     * The long address of the node to which this command will be sent
     *
     * @param destinationEui64 the destinationEui64 to set as {@link IeeeAddress}
     */
    public void setDestinationEui64(IeeeAddress destinationEui64) {
        this.destinationEui64 = destinationEui64;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeUInt16(destinationNodeId);
        serializer.serializeEmberEui64(destinationEui64);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(111);
        builder.append("EzspSendTrustCenterLinkKeyRequest [networkId=");
        builder.append(networkId);
        builder.append(", destinationNodeId=");
        builder.append(String.format("%04X", destinationNodeId));
        builder.append(", destinationEui64=");
        builder.append(destinationEui64);
        builder.append(']');
        return builder.toString();
    }
}
