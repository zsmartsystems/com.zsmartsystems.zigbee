/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
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
 * Generation Tier value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum GenerationTierEnum {

    /**
     * Current Tier 1 Summation Received Attribute, 1, 0x0001
     */
    CURRENT_TIER_1_SUMMATION_RECEIVED_ATTRIBUTE(0x0001),

    /**
     * Current Tier 2 Summation Received Attribute, 2, 0x0002
     */
    CURRENT_TIER_2_SUMMATION_RECEIVED_ATTRIBUTE(0x0002),

    /**
     * Current Tier 3 Summation Received Attribute, 3, 0x0003
     */
    CURRENT_TIER_3_SUMMATION_RECEIVED_ATTRIBUTE(0x0003),

    /**
     * Current Tier 4 Summation Received Attribute, 4, 0x0004
     */
    CURRENT_TIER_4_SUMMATION_RECEIVED_ATTRIBUTE(0x0004),

    /**
     * Current Tier 5 Summation Received Attribute, 5, 0x0005
     */
    CURRENT_TIER_5_SUMMATION_RECEIVED_ATTRIBUTE(0x0005),

    /**
     * Current Tier 6 Summation Received Attribute, 6, 0x0006
     */
    CURRENT_TIER_6_SUMMATION_RECEIVED_ATTRIBUTE(0x0006),

    /**
     * Current Tier 7 Summation Received Attribute, 7, 0x0007
     */
    CURRENT_TIER_7_SUMMATION_RECEIVED_ATTRIBUTE(0x0007),

    /**
     * Current Tier 8 Summation Received Attribute, 8, 0x0008
     */
    CURRENT_TIER_8_SUMMATION_RECEIVED_ATTRIBUTE(0x0008),

    /**
     * Current Tier 9 Summation Received Attribute, 9, 0x0009
     */
    CURRENT_TIER_9_SUMMATION_RECEIVED_ATTRIBUTE(0x0009),

    /**
     * Current Tier 10 Summation Received Attribute, 10, 0x000A
     */
    CURRENT_TIER_10_SUMMATION_RECEIVED_ATTRIBUTE(0x000A),

    /**
     * Current Tier 11 Summation Received Attribute, 11, 0x000B
     */
    CURRENT_TIER_11_SUMMATION_RECEIVED_ATTRIBUTE(0x000B),

    /**
     * Current Tier 12 Summation Received Attribute, 12, 0x000C
     */
    CURRENT_TIER_12_SUMMATION_RECEIVED_ATTRIBUTE(0x000C),

    /**
     * Current Tier 13 Summation Received Attribute, 13, 0x000D
     */
    CURRENT_TIER_13_SUMMATION_RECEIVED_ATTRIBUTE(0x000D),

    /**
     * Current Tier 14 Summation Received Attribute, 14, 0x000E
     */
    CURRENT_TIER_14_SUMMATION_RECEIVED_ATTRIBUTE(0x000E),

    /**
     * Current Tier 15 Summation Received Attribute, 15, 0x000F
     */
    CURRENT_TIER_15_SUMMATION_RECEIVED_ATTRIBUTE(0x000F),

    /**
     * Current Tier 16 Summation Received Attribute, 16, 0x0010
     */
    CURRENT_TIER_16_SUMMATION_RECEIVED_ATTRIBUTE(0x0010),

    /**
     * Current Tier 17 Summation Received Attribute, 17, 0x0011
     */
    CURRENT_TIER_17_SUMMATION_RECEIVED_ATTRIBUTE(0x0011),

    /**
     * Current Tier 18 Summation Received Attribute, 18, 0x0012
     */
    CURRENT_TIER_18_SUMMATION_RECEIVED_ATTRIBUTE(0x0012),

    /**
     * Current Tier 19 Summation Received Attribute, 19, 0x0013
     */
    CURRENT_TIER_19_SUMMATION_RECEIVED_ATTRIBUTE(0x0013),

    /**
     * Current Tier 20 Summation Received Attribute, 20, 0x0014
     */
    CURRENT_TIER_20_SUMMATION_RECEIVED_ATTRIBUTE(0x0014),

    /**
     * Current Tier 21 Summation Received Attribute, 21, 0x0015
     */
    CURRENT_TIER_21_SUMMATION_RECEIVED_ATTRIBUTE(0x0015),

    /**
     * Current Tier 22 Summation Received Attribute, 22, 0x0016
     */
    CURRENT_TIER_22_SUMMATION_RECEIVED_ATTRIBUTE(0x0016),

    /**
     * Current Tier 23 Summation Received Attribute, 23, 0x0017
     */
    CURRENT_TIER_23_SUMMATION_RECEIVED_ATTRIBUTE(0x0017),

    /**
     * Current Tier 24 Summation Received Attribute, 24, 0x0018
     */
    CURRENT_TIER_24_SUMMATION_RECEIVED_ATTRIBUTE(0x0018),

    /**
     * Current Tier 25 Summation Received Attribute, 25, 0x0019
     */
    CURRENT_TIER_25_SUMMATION_RECEIVED_ATTRIBUTE(0x0019),

    /**
     * Current Tier 26 Summation Received Attribute, 26, 0x001A
     */
    CURRENT_TIER_26_SUMMATION_RECEIVED_ATTRIBUTE(0x001A),

    /**
     * Current Tier 27 Summation Received Attribute, 27, 0x001B
     */
    CURRENT_TIER_27_SUMMATION_RECEIVED_ATTRIBUTE(0x001B),

    /**
     * Current Tier 28 Summation Received Attribute, 28, 0x001C
     */
    CURRENT_TIER_28_SUMMATION_RECEIVED_ATTRIBUTE(0x001C),

    /**
     * Current Tier 29 Summation Received Attribute, 29, 0x001D
     */
    CURRENT_TIER_29_SUMMATION_RECEIVED_ATTRIBUTE(0x001D),

    /**
     * Current Tier 30 Summation Received Attribute, 30, 0x001E
     */
    CURRENT_TIER_30_SUMMATION_RECEIVED_ATTRIBUTE(0x001E),

    /**
     * Current Tier 31 Summation Received Attribute, 31, 0x001F
     */
    CURRENT_TIER_31_SUMMATION_RECEIVED_ATTRIBUTE(0x001F),

    /**
     * Current Tier 32 Summation Received Attribute, 32, 0x0020
     */
    CURRENT_TIER_32_SUMMATION_RECEIVED_ATTRIBUTE(0x0020),

    /**
     * Current Tier 33 Summation Received Attribute, 33, 0x0021
     */
    CURRENT_TIER_33_SUMMATION_RECEIVED_ATTRIBUTE(0x0021),

    /**
     * Current Tier 34 Summation Received Attribute, 34, 0x0022
     */
    CURRENT_TIER_34_SUMMATION_RECEIVED_ATTRIBUTE(0x0022),

    /**
     * Current Tier 35 Summation Received Attribute, 35, 0x0023
     */
    CURRENT_TIER_35_SUMMATION_RECEIVED_ATTRIBUTE(0x0023),

    /**
     * Current Tier 36 Summation Received Attribute, 36, 0x0024
     */
    CURRENT_TIER_36_SUMMATION_RECEIVED_ATTRIBUTE(0x0024),

    /**
     * Current Tier 37 Summation Received Attribute, 37, 0x0025
     */
    CURRENT_TIER_37_SUMMATION_RECEIVED_ATTRIBUTE(0x0025),

    /**
     * Current Tier 38 Summation Received Attribute, 38, 0x0026
     */
    CURRENT_TIER_38_SUMMATION_RECEIVED_ATTRIBUTE(0x0026),

    /**
     * Current Tier 39 Summation Received Attribute, 39, 0x0027
     */
    CURRENT_TIER_39_SUMMATION_RECEIVED_ATTRIBUTE(0x0027),

    /**
     * Current Tier 40 Summation Received Attribute, 40, 0x0028
     */
    CURRENT_TIER_40_SUMMATION_RECEIVED_ATTRIBUTE(0x0028),

    /**
     * Current Tier 41 Summation Received Attribute, 41, 0x0029
     */
    CURRENT_TIER_41_SUMMATION_RECEIVED_ATTRIBUTE(0x0029),

    /**
     * Current Tier 42 Summation Received Attribute, 42, 0x002A
     */
    CURRENT_TIER_42_SUMMATION_RECEIVED_ATTRIBUTE(0x002A),

    /**
     * Current Tier 43 Summation Received Attribute, 43, 0x002B
     */
    CURRENT_TIER_43_SUMMATION_RECEIVED_ATTRIBUTE(0x002B),

    /**
     * Current Tier 44 Summation Received Attribute, 44, 0x002C
     */
    CURRENT_TIER_44_SUMMATION_RECEIVED_ATTRIBUTE(0x002C),

    /**
     * Current Tier 45 Summation Received Attribute, 45, 0x002D
     */
    CURRENT_TIER_45_SUMMATION_RECEIVED_ATTRIBUTE(0x002D),

    /**
     * Current Tier 46 Summation Received Attribute, 46, 0x002E
     */
    CURRENT_TIER_46_SUMMATION_RECEIVED_ATTRIBUTE(0x002E),

    /**
     * Current Tier 47 Summation Received Attribute, 47, 0x002F
     */
    CURRENT_TIER_47_SUMMATION_RECEIVED_ATTRIBUTE(0x002F),

    /**
     * Current Tier 48 Summation Received Attribute, 48, 0x0030
     */
    CURRENT_TIER_48_SUMMATION_RECEIVED_ATTRIBUTE(0x0030);

    /**
     * A mapping between the integer code and its corresponding GenerationTierEnum type to facilitate lookup by value.
     */
    private static Map<Integer, GenerationTierEnum> idMap;

    static {
        idMap = new HashMap<Integer, GenerationTierEnum>();
        for (GenerationTierEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GenerationTierEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GenerationTierEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
