/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.thermostat;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Setpoint Change Source value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum SetpointChangeSourceEnum {

    /**
     * Manual user-initiated, 0, 0x0000
     */
    MANUAL_USER_INITIATED(0x0000),

    /**
     * Schedule/internal programming-initiated, 1, 0x0001
     */
    SCHEDULE_INTERNAL_PROGRAMMING_INITIATED(0x0001),

    /**
     * Externally-initiated, 2, 0x0002
     */
    EXTERNALLY_INITIATED(0x0002);

    /**
     * A mapping between the integer code and its corresponding SetpointChangeSourceEnum type to facilitate lookup by value.
     */
    private static Map<Integer, SetpointChangeSourceEnum> idMap;

    static {
        idMap = new HashMap<Integer, SetpointChangeSourceEnum>();
        for (SetpointChangeSourceEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private SetpointChangeSourceEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static SetpointChangeSourceEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
