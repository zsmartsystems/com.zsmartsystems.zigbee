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
 * Extended Register Tier value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum ExtendedRegisterTierEnum {

    /**
     * Refer To Register Tier Field
     */
    REFER_TO_REGISTER_TIER_FIELD(0x0000),

    /**
     * Current Tier 16 Summation Delivered Attribute
     */
    CURRENT_TIER_16_SUMMATION_DELIVERED_ATTRIBUTE(0x0001),

    /**
     * Current Tier 17 Summation Delivered Attribute
     */
    CURRENT_TIER_17_SUMMATION_DELIVERED_ATTRIBUTE(0x0002),

    /**
     * Current Tier 18 Summation Delivered Attribute
     */
    CURRENT_TIER_18_SUMMATION_DELIVERED_ATTRIBUTE(0x0003),

    /**
     * Current Tier 19 Summation Delivered Attribute
     */
    CURRENT_TIER_19_SUMMATION_DELIVERED_ATTRIBUTE(0x0004),

    /**
     * Current Tier 20 Summation Delivered Attribute
     */
    CURRENT_TIER_20_SUMMATION_DELIVERED_ATTRIBUTE(0x0005),

    /**
     * Current Tier 21 Summation Delivered Attribute
     */
    CURRENT_TIER_21_SUMMATION_DELIVERED_ATTRIBUTE(0x0006),

    /**
     * Current Tier 22 Summation Delivered Attribute
     */
    CURRENT_TIER_22_SUMMATION_DELIVERED_ATTRIBUTE(0x0007),

    /**
     * Current Tier 23 Summation Delivered Attribute
     */
    CURRENT_TIER_23_SUMMATION_DELIVERED_ATTRIBUTE(0x0008),

    /**
     * Current Tier 24 Summation Delivered Attribute
     */
    CURRENT_TIER_24_SUMMATION_DELIVERED_ATTRIBUTE(0x0009),

    /**
     * Current Tier 25 Summation Delivered Attribute
     */
    CURRENT_TIER_25_SUMMATION_DELIVERED_ATTRIBUTE(0x000A),

    /**
     * Current Tier 26 Summation Delivered Attribute
     */
    CURRENT_TIER_26_SUMMATION_DELIVERED_ATTRIBUTE(0x000B),

    /**
     * Current Tier 27 Summation Delivered Attribute
     */
    CURRENT_TIER_27_SUMMATION_DELIVERED_ATTRIBUTE(0x000C),

    /**
     * Current Tier 28 Summation Delivered Attribute
     */
    CURRENT_TIER_28_SUMMATION_DELIVERED_ATTRIBUTE(0x000D),

    /**
     * Current Tier 29 Summation Delivered Attribute
     */
    CURRENT_TIER_29_SUMMATION_DELIVERED_ATTRIBUTE(0x000E),

    /**
     * Current Tier 30 Summation Delivered Attribute
     */
    CURRENT_TIER_30_SUMMATION_DELIVERED_ATTRIBUTE(0x000F),

    /**
     * Current Tier 31 Summation Delivered Attribute
     */
    CURRENT_TIER_31_SUMMATION_DELIVERED_ATTRIBUTE(0x0010),

    /**
     * Current Tier 32 Summation Delivered Attribute
     */
    CURRENT_TIER_32_SUMMATION_DELIVERED_ATTRIBUTE(0x0011),

    /**
     * Current Tier 33 Summation Delivered Attribute
     */
    CURRENT_TIER_33_SUMMATION_DELIVERED_ATTRIBUTE(0x0012),

    /**
     * Current Tier 34 Summation Delivered Attribute
     */
    CURRENT_TIER_34_SUMMATION_DELIVERED_ATTRIBUTE(0x0013),

    /**
     * Current Tier 35 Summation Delivered Attribute
     */
    CURRENT_TIER_35_SUMMATION_DELIVERED_ATTRIBUTE(0x0014),

    /**
     * Current Tier 36 Summation Delivered Attribute
     */
    CURRENT_TIER_36_SUMMATION_DELIVERED_ATTRIBUTE(0x0015),

    /**
     * Current Tier 37 Summation Delivered Attribute
     */
    CURRENT_TIER_37_SUMMATION_DELIVERED_ATTRIBUTE(0x0016),

    /**
     * Current Tier 38 Summation Delivered Attribute
     */
    CURRENT_TIER_38_SUMMATION_DELIVERED_ATTRIBUTE(0x0017),

    /**
     * Current Tier 39 Summation Delivered Attribute
     */
    CURRENT_TIER_39_SUMMATION_DELIVERED_ATTRIBUTE(0x0018),

    /**
     * Current Tier 40 Summation Delivered Attribute
     */
    CURRENT_TIER_40_SUMMATION_DELIVERED_ATTRIBUTE(0x0019),

    /**
     * Current Tier 41 Summation Delivered Attribute
     */
    CURRENT_TIER_41_SUMMATION_DELIVERED_ATTRIBUTE(0x001A),

    /**
     * Current Tier 42 Summation Delivered Attribute
     */
    CURRENT_TIER_42_SUMMATION_DELIVERED_ATTRIBUTE(0x001B),

    /**
     * Current Tier 43 Summation Delivered Attribute
     */
    CURRENT_TIER_43_SUMMATION_DELIVERED_ATTRIBUTE(0x001C),

    /**
     * Current Tier 44 Summation Delivered Attribute
     */
    CURRENT_TIER_44_SUMMATION_DELIVERED_ATTRIBUTE(0x001D),

    /**
     * Current Tier 45 Summation Delivered Attribute
     */
    CURRENT_TIER_45_SUMMATION_DELIVERED_ATTRIBUTE(0x001E),

    /**
     * Current Tier 46 Summation Delivered Attribute
     */
    CURRENT_TIER_46_SUMMATION_DELIVERED_ATTRIBUTE(0x001F),

    /**
     * Current Tier 47 Summation Delivered Attribute
     */
    CURRENT_TIER_47_SUMMATION_DELIVERED_ATTRIBUTE(0x0020),

    /**
     * Current Tier 48 Summation Delivered Attribute
     */
    CURRENT_TIER_48_SUMMATION_DELIVERED_ATTRIBUTE(0x0021);

    /**
     * A mapping between the integer code and its corresponding ExtendedRegisterTierEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ExtendedRegisterTierEnum> idMap;

    static {
        idMap = new HashMap<Integer, ExtendedRegisterTierEnum>();
        for (ExtendedRegisterTierEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private ExtendedRegisterTierEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ExtendedRegisterTierEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
