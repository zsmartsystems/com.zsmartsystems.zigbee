/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum ExtendedPriceTierEnum {

    /**
     * Refer To Price Tier Field, 0, 0x0000
     */
    REFER_TO_PRICE_TIER_FIELD(0x0000),

    /**
     * Tier 16 Price Label, 1, 0x0001
     */
    TIER_16_PRICE_LABEL(0x0001),

    /**
     * Tier 17 Price Label, 2, 0x0002
     */
    TIER_17_PRICE_LABEL(0x0002),

    /**
     * Tier 18 Price Label, 3, 0x0003
     */
    TIER_18_PRICE_LABEL(0x0003),

    /**
     * Tier 19 Price Label, 4, 0x0004
     */
    TIER_19_PRICE_LABEL(0x0004),

    /**
     * Tier 20 Price Label, 5, 0x0005
     */
    TIER_20_PRICE_LABEL(0x0005),

    /**
     * Tier 21 Price Label, 6, 0x0006
     */
    TIER_21_PRICE_LABEL(0x0006),

    /**
     * Tier 22 Price Label, 7, 0x0007
     */
    TIER_22_PRICE_LABEL(0x0007),

    /**
     * Tier 23 Price Label, 8, 0x0008
     */
    TIER_23_PRICE_LABEL(0x0008),

    /**
     * Tier 24 Price Label, 9, 0x0009
     */
    TIER_24_PRICE_LABEL(0x0009),

    /**
     * Tier 25 Price Label, 10, 0x000A
     */
    TIER_25_PRICE_LABEL(0x000A),

    /**
     * Tier 26 Price Label, 11, 0x000B
     */
    TIER_26_PRICE_LABEL(0x000B),

    /**
     * Tier 27 Price Label, 12, 0x000C
     */
    TIER_27_PRICE_LABEL(0x000C),

    /**
     * Tier 28 Price Label, 13, 0x000D
     */
    TIER_28_PRICE_LABEL(0x000D),

    /**
     * Tier 29 Price Label, 14, 0x000E
     */
    TIER_29_PRICE_LABEL(0x000E),

    /**
     * Tier 30 Price Label, 15, 0x000F
     */
    TIER_30_PRICE_LABEL(0x000F),

    /**
     * Tier 31 Price Label, 16, 0x0010
     */
    TIER_31_PRICE_LABEL(0x0010),

    /**
     * Tier 32 Price Label, 17, 0x0011
     */
    TIER_32_PRICE_LABEL(0x0011),

    /**
     * Tier 33 Price Label, 18, 0x0012
     */
    TIER_33_PRICE_LABEL(0x0012),

    /**
     * Tier 34 Price Label, 19, 0x0013
     */
    TIER_34_PRICE_LABEL(0x0013),

    /**
     * Tier 35 Price Label, 20, 0x0014
     */
    TIER_35_PRICE_LABEL(0x0014),

    /**
     * Tier 36 Price Label, 21, 0x0015
     */
    TIER_36_PRICE_LABEL(0x0015),

    /**
     * Tier 37 Price Label, 22, 0x0016
     */
    TIER_37_PRICE_LABEL(0x0016),

    /**
     * Tier 38 Price Label, 23, 0x0017
     */
    TIER_38_PRICE_LABEL(0x0017),

    /**
     * Tier 39 Price Label, 24, 0x0018
     */
    TIER_39_PRICE_LABEL(0x0018),

    /**
     * Tier 40 Price Label, 25, 0x0019
     */
    TIER_40_PRICE_LABEL(0x0019),

    /**
     * Tier 41 Price Label, 26, 0x001A
     */
    TIER_41_PRICE_LABEL(0x001A),

    /**
     * Tier 42 Price Label, 27, 0x001B
     */
    TIER_42_PRICE_LABEL(0x001B),

    /**
     * Tier 43 Price Label, 28, 0x001C
     */
    TIER_43_PRICE_LABEL(0x001C),

    /**
     * Tier 44 Price Label, 29, 0x001D
     */
    TIER_44_PRICE_LABEL(0x001D),

    /**
     * Tier 45 Price Label, 30, 0x001E
     */
    TIER_45_PRICE_LABEL(0x001E),

    /**
     * Tier 46 Price Label, 31, 0x001F
     */
    TIER_46_PRICE_LABEL(0x001F),

    /**
     * Tier 47 Price Label, 32, 0x0020
     */
    TIER_47_PRICE_LABEL(0x0020),

    /**
     * Tier 48 Price Label, 33, 0x0021
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
