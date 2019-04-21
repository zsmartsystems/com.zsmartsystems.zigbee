/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.basic;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Physical Environment value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum PhysicalEnvironmentEnum {

    /**
     * Unknown
     */
    UNKNOWN(0x0000),

    /**
     * Atrium
     */
    ATRIUM(0x0001),

    /**
     * Bar
     */
    BAR(0x0002),

    /**
     * Courtyard
     */
    COURTYARD(0x0003),

    /**
     * Bathroom
     */
    BATHROOM(0x0004),

    /**
     * Bedroom
     */
    BEDROOM(0x0005);

    /**
     * A mapping between the integer code and its corresponding PhysicalEnvironmentEnum type to facilitate lookup by value.
     */
    private static Map<Integer, PhysicalEnvironmentEnum> idMap;

    static {
        idMap = new HashMap<Integer, PhysicalEnvironmentEnum>();
        for (PhysicalEnvironmentEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private PhysicalEnvironmentEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static PhysicalEnvironmentEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
