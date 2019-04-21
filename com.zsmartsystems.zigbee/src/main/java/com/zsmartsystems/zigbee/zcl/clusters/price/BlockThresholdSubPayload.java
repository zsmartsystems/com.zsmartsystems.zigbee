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
 * Block Threshold Sub Payload structure implementation.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class BlockThresholdSubPayload implements ZigBeeSerializable {
    /**
     * Tier Number Of Block Thresholds structure field.
     */
    private Integer tierNumberOfBlockThresholds;

    /**
     * Block Threshold structure field.
     */
    private Integer blockThreshold;



    /**
     * Gets Tier Number Of Block Thresholds.
     *
     * @return the Tier Number Of Block Thresholds
     */
    public Integer getTierNumberOfBlockThresholds() {
        return tierNumberOfBlockThresholds;
    }

    /**
     * Sets Tier Number Of Block Thresholds.
     *
     * @param tierNumberOfBlockThresholds the Tier Number Of Block Thresholds
     */
    public void setTierNumberOfBlockThresholds(final Integer tierNumberOfBlockThresholds) {
        this.tierNumberOfBlockThresholds = tierNumberOfBlockThresholds;
    }

    /**
     * Gets Block Threshold.
     *
     * @return the Block Threshold
     */
    public Integer getBlockThreshold() {
        return blockThreshold;
    }

    /**
     * Sets Block Threshold.
     *
     * @param blockThreshold the Block Threshold
     */
    public void setBlockThreshold(final Integer blockThreshold) {
        this.blockThreshold = blockThreshold;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(tierNumberOfBlockThresholds, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(blockThreshold, ZclDataType.UNSIGNED_48_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        tierNumberOfBlockThresholds = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        blockThreshold = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_48_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(108);
        builder.append("BlockThresholdSubPayload [");
        builder.append(super.toString());
        builder.append(", tierNumberOfBlockThresholds=");
        builder.append(tierNumberOfBlockThresholds);
        builder.append(", blockThreshold=");
        builder.append(blockThreshold);
        builder.append(']');
        return builder.toString();
    }
}
