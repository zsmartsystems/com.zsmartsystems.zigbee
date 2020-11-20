/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-11-19T09:19:31Z")
public enum MeterTypeEnum {

    /**
     * Utility Primary Meter
     */
    UTILITY_PRIMARY_METER(0x0000),

    /**
     * Utility Production Meter
     */
    UTILITY_PRODUCTION_METER(0x0001),

    /**
     * Utility Secondary Meter
     */
    UTILITY_SECONDARY_METER(0x0002),

    /**
     * Private Primary Meter
     */
    PRIVATE_PRIMARY_METER(0x0100),

    /**
     * Private Production Meter
     */
    PRIVATE_PRODUCTION_METER(0x0101),

    /**
     * Private Secondary Meters
     */
    PRIVATE_SECONDARY_METERS(0x0102),

    /**
     * Generic Meter
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
