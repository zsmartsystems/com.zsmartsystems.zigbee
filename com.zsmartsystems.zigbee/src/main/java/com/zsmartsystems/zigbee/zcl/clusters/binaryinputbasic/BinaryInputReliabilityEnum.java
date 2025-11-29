/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.binaryinputbasic;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Reliability value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum BinaryInputReliabilityEnum {

    /**
     * No - Fault - Detected, 0, 0x0000
     */
    NO_FAULT_DETECTED(0x0000),

    /**
     * Over - Range, 2, 0x0002
     */
    OVER_RANGE(0x0002),

    /**
     * Under - Range, 3, 0x0003
     */
    UNDER_RANGE(0x0003),

    /**
     * Open - Loop, 4, 0x0004
     */
    OPEN_LOOP(0x0004),

    /**
     * Shorted - Loop, 5, 0x0005
     */
    SHORTED_LOOP(0x0005),

    /**
     * Unreliable - Other, 7, 0x0007
     */
    UNRELIABLE_OTHER(0x0007),

    /**
     * Process - Error, 8, 0x0008
     */
    PROCESS_ERROR(0x0008),

    /**
     * Multi - State - Fault, 9, 0x0009
     */
    MULTI_STATE_FAULT(0x0009),

    /**
     * Configuration - Error, 10, 0x000A
     */
    CONFIGURATION_ERROR(0x000A);

    /**
     * A mapping between the integer code and its corresponding BinaryInputReliabilityEnum type to facilitate lookup by value.
     */
    private static Map<Integer, BinaryInputReliabilityEnum> idMap;

    static {
        idMap = new HashMap<Integer, BinaryInputReliabilityEnum>();
        for (BinaryInputReliabilityEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private BinaryInputReliabilityEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static BinaryInputReliabilityEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
