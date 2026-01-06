/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.prepayment;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Result Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum ResultTypeEnum {

    /**
     * Accepted, 0, 0x0000
     */
    ACCEPTED(0x0000),

    /**
     * Rejected Invalid Top Up, 1, 0x0001
     */
    REJECTED_INVALID_TOP_UP(0x0001),

    /**
     * Rejected Duplicate Top Up, 2, 0x0002
     */
    REJECTED_DUPLICATE_TOP_UP(0x0002),

    /**
     * Rejected Error, 3, 0x0003
     */
    REJECTED_ERROR(0x0003),

    /**
     * Rejected Max Credit Reached, 4, 0x0004
     */
    REJECTED_MAX_CREDIT_REACHED(0x0004),

    /**
     * Rejected Keypad Lock, 5, 0x0005
     */
    REJECTED_KEYPAD_LOCK(0x0005),

    /**
     * Rejected Top Up Value Too Large, 6, 0x0006
     */
    REJECTED_TOP_UP_VALUE_TOO_LARGE(0x0006),

    /**
     * Accepted Supply Enabled, 16, 0x0010
     */
    ACCEPTED_SUPPLY_ENABLED(0x0010),

    /**
     * Accepted Supply Disabled, 17, 0x0011
     */
    ACCEPTED_SUPPLY_DISABLED(0x0011),

    /**
     * Accepted Supply Armed, 18, 0x0012
     */
    ACCEPTED_SUPPLY_ARMED(0x0012);

    /**
     * A mapping between the integer code and its corresponding ResultTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ResultTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, ResultTypeEnum>();
        for (ResultTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private ResultTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ResultTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
