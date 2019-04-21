/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.multistateoutputbasic;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Reliability value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum MultistateOutputReliabilityEnum {

    /**
     * No - Fault - Detected
     */
    NO_FAULT_DETECTED(0x0000),

    /**
     * Over - Range
     */
    OVER_RANGE(0x0002),

    /**
     * Under - Range
     */
    UNDER_RANGE(0x0003),

    /**
     * Open - Loop
     */
    OPEN_LOOP(0x0004),

    /**
     * Shorted - Loop
     */
    SHORTED_LOOP(0x0005),

    /**
     * Unreliable - Other
     */
    UNRELIABLE_OTHER(0x0007),

    /**
     * Process - Error
     */
    PROCESS_ERROR(0x0008),

    /**
     * Multi - State - Fault
     */
    MULTI_STATE_FAULT(0x0009),

    /**
     * Configuration - Error
     */
    CONFIGURATION_ERROR(0x000A);

    /**
     * A mapping between the integer code and its corresponding MultistateOutputReliabilityEnum type to facilitate lookup by value.
     */
    private static Map<Integer, MultistateOutputReliabilityEnum> idMap;

    static {
        idMap = new HashMap<Integer, MultistateOutputReliabilityEnum>();
        for (MultistateOutputReliabilityEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private MultistateOutputReliabilityEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static MultistateOutputReliabilityEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
