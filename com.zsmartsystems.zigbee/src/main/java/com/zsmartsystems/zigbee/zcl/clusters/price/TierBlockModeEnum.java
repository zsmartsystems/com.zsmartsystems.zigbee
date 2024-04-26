/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.price;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Tier Block Mode value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum TierBlockModeEnum {

    /**
     * Active Block, 0, 0x0000
     */
    ACTIVE_BLOCK(0x0000),

    /**
     * Active Block Price Tier, 1, 0x0001
     */
    ACTIVE_BLOCK_PRICE_TIER(0x0001),

    /**
     * Active Block Price Tier Threshold, 2, 0x0002
     */
    ACTIVE_BLOCK_PRICE_TIER_THRESHOLD(0x0002),

    /**
     * Not Used, 255, 0x00FF
     */
    NOT_USED(0x00FF);

    /**
     * A mapping between the integer code and its corresponding TierBlockModeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, TierBlockModeEnum> idMap;

    static {
        idMap = new HashMap<Integer, TierBlockModeEnum>();
        for (TierBlockModeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private TierBlockModeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static TierBlockModeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
