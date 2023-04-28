/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:02:05Z")
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
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public PriceMatrixSubPayload() {
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param tierBlockId {@link Integer} Tier Block ID
     * @param price {@link Integer} Price
     */
    public PriceMatrixSubPayload(
            Integer tierBlockId,
            Integer price) {

        this.tierBlockId = tierBlockId;
        this.price = price;
    }

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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
        tierBlockId = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        price = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
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
