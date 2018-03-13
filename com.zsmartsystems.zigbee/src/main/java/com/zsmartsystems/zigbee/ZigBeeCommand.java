/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;

/**
 * Base class for all ZigBee commands.
 * <p>
 * The base class contains methods that are not part of the application layer (eg ZCL or ZDO).
 * These members may be part of other layers but are provide in the command for convenience.
 *
 * @author Chris Jackson
 */
public class ZigBeeCommand {
    /**
     * The source address.
     */
    private ZigBeeAddress sourceAddress;

    /**
     * The destination address.
     */
    private ZigBeeAddress destinationAddress;

    /**
     * The cluster ID
     */
    protected int clusterId;

    /**
     * The transaction ID.
     */
    private Integer transactionId;

    /**
     * Gets destination address.
     *
     * @return the destination address.
     */
    public ZigBeeAddress getDestinationAddress() {
        return destinationAddress;
    }

    /**
     * Sets destination address.
     *
     * @param destinationAddress the destination address.
     */
    public void setDestinationAddress(final ZigBeeAddress destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    /**
     * Gets source address.
     *
     * @return the source address
     */
    public ZigBeeAddress getSourceAddress() {
        return sourceAddress;
    }

    /**
     * Sets source address.
     *
     * @param sourceAddress the source address
     */
    public void setSourceAddress(final ZigBeeAddress sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    /**
     * Gets the cluster ID.
     *
     * @return the cluster ID.
     */
    public Integer getClusterId() {
        return clusterId;
    }

    /**
     * Gets the transaction ID.
     *
     * @return the transaction ID
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the transaction ID.
     *
     * @param transactionId the transaction ID
     */
    public void setTransactionId(final int transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Serialize the command class to the format required by the transport layer.
     *
     * @param serializer the {@link ZclFieldSerializer} to use
     */
    public void serialize(ZclFieldSerializer serializer) {
        // Default implementation does nothing - overridden by each class
    }

    /**
     * Deserialize the command from the format required by the transport layer and into the command class.
     *
     * @param serializer the {@link ZclFieldSerializer} to use
     */
    public void deserialize(final ZclFieldDeserializer deserializer) {
        // Default implementation does nothing - overridden by each class
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(sourceAddress);
        builder.append(" -> ");
        builder.append(destinationAddress);
        builder.append("");
        builder.append(String.format(", cluster=%04X, TID=%02X", clusterId, transactionId));
        return builder.toString();
    }
}
