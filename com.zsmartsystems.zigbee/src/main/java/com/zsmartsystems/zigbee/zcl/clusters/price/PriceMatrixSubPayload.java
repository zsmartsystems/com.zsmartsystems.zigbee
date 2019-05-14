/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.price;
import javax.annotation.Generated;

import com.zsmartsystems.zigbee.serialization.ZigBeeSerializable;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Price Matrix Sub Payload structure implementation.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class PriceMatrixSubPayload implements ZigBeeSerializable {
    /**
     * Tier Block ID structure field.
     */
    private Integer tierBlockId;

    /**
     * Price structure field.
     */
    private Integer price;



    /**
     * Gets Tier Block ID.
     *
     * @return the Tier Block ID
     */
    public Integer getTierBlockId() {
        return tierBlockId;
    }

    /**
     * Sets Tier Block ID.
     *
     * @param tierBlockId the Tier Block ID
     */
    public void setTierBlockId(final Integer tierBlockId) {
        this.tierBlockId = tierBlockId;
    }

    /**
     * Gets Price.
     *
     * @return the Price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Sets Price.
     *
     * @param price the Price
     */
    public void setPrice(final Integer price) {
        this.price = price;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(tierBlockId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(price, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        tierBlockId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        price = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(80);
        builder.append("PriceMatrixSubPayload [");
        builder.append(super.toString());
        builder.append(", tierBlockId=");
        builder.append(tierBlockId);
        builder.append(", price=");
        builder.append(price);
        builder.append(']');
        return builder.toString();
    }
}
