/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.meteridentification;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Meter Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum MeterTypeEnum {

    /**
     * Utility Primary Meter, 0, 0x0000
     */
    UTILITY_PRIMARY_METER(0x0000),

    /**
     * Utility Production Meter, 1, 0x0001
     */
    UTILITY_PRODUCTION_METER(0x0001),

    /**
     * Utility Secondary Meter, 2, 0x0002
     */
    UTILITY_SECONDARY_METER(0x0002),

    /**
     * Private Primary Meter, 256, 0x0100
     */
    PRIVATE_PRIMARY_METER(0x0100),

    /**
     * Private Production Meter, 257, 0x0101
     */
    PRIVATE_PRODUCTION_METER(0x0101),

    /**
     * Private Secondary Meters, 258, 0x0102
     */
    PRIVATE_SECONDARY_METERS(0x0102),

    /**
     * Generic Meter, 272, 0x0110
     */
    GENERIC_METER(0x0110);

    /**
     * A mapping between the integer code and its corresponding MeterTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, MeterTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, MeterTypeEnum>();
        for (MeterTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private MeterTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static MeterTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
