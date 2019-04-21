/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 * Extended Price Tier value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum ExtendedPriceTierEnum {

    /**
     * Refer To Price Tier Field
     */
    REFER_TO_PRICE_TIER_FIELD(0x0000),

    /**
     * Tier 16 Price Label
     */
    TIER_16_PRICE_LABEL(0x0001),

    /**
     * Tier 17 Price Label
     */
    TIER_17_PRICE_LABEL(0x0002),

    /**
     * Tier 18 Price Label
     */
    TIER_18_PRICE_LABEL(0x0003),

    /**
     * Tier 19 Price Label
     */
    TIER_19_PRICE_LABEL(0x0004),

    /**
     * Tier 20 Price Label
     */
    TIER_20_PRICE_LABEL(0x0005),

    /**
     * Tier 21 Price Label
     */
    TIER_21_PRICE_LABEL(0x0006),

    /**
     * Tier 22 Price Label
     */
    TIER_22_PRICE_LABEL(0x0007),

    /**
     * Tier 23 Price Label
     */
    TIER_23_PRICE_LABEL(0x0008),

    /**
     * Tier 24 Price Label
     */
    TIER_24_PRICE_LABEL(0x0009),

    /**
     * Tier 25 Price Label
     */
    TIER_25_PRICE_LABEL(0x000A),

    /**
     * Tier 26 Price Label
     */
    TIER_26_PRICE_LABEL(0x000B),

    /**
     * Tier 27 Price Label
     */
    TIER_27_PRICE_LABEL(0x000C),

    /**
     * Tier 28 Price Label
     */
    TIER_28_PRICE_LABEL(0x000D),

    /**
     * Tier 29 Price Label
     */
    TIER_29_PRICE_LABEL(0x000E),

    /**
     * Tier 30 Price Label
     */
    TIER_30_PRICE_LABEL(0x000F),

    /**
     * Tier 31 Price Label
     */
    TIER_31_PRICE_LABEL(0x0010),

    /**
     * Tier 32 Price Label
     */
    TIER_32_PRICE_LABEL(0x0011),

    /**
     * Tier 33 Price Label
     */
    TIER_33_PRICE_LABEL(0x0012),

    /**
     * Tier 34 Price Label
     */
    TIER_34_PRICE_LABEL(0x0013),

    /**
     * Tier 35 Price Label
     */
    TIER_35_PRICE_LABEL(0x0014),

    /**
     * Tier 36 Price Label
     */
    TIER_36_PRICE_LABEL(0x0015),

    /**
     * Tier 37 Price Label
     */
    TIER_37_PRICE_LABEL(0x0016),

    /**
     * Tier 38 Price Label
     */
    TIER_38_PRICE_LABEL(0x0017),

    /**
     * Tier 39 Price Label
     */
    TIER_39_PRICE_LABEL(0x0018),

    /**
     * Tier 40 Price Label
     */
    TIER_40_PRICE_LABEL(0x0019),

    /**
     * Tier 41 Price Label
     */
    TIER_41_PRICE_LABEL(0x001A),

    /**
     * Tier 42 Price Label
     */
    TIER_42_PRICE_LABEL(0x001B),

    /**
     * Tier 43 Price Label
     */
    TIER_43_PRICE_LABEL(0x001C),

    /**
     * Tier 44 Price Label
     */
    TIER_44_PRICE_LABEL(0x001D),

    /**
     * Tier 45 Price Label
     */
    TIER_45_PRICE_LABEL(0x001E),

    /**
     * Tier 46 Price Label
     */
    TIER_46_PRICE_LABEL(0x001F),

    /**
     * Tier 47 Price Label
     */
    TIER_47_PRICE_LABEL(0x0020),

    /**
     * Tier 48 Price Label
     */
    TIER_48_PRICE_LABEL(0x0021);

    /**
     * A mapping between the integer code and its corresponding ExtendedPriceTierEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ExtendedPriceTierEnum> idMap;

    static {
        idMap = new HashMap<Integer, ExtendedPriceTierEnum>();
        for (ExtendedPriceTierEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private ExtendedPriceTierEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ExtendedPriceTierEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
