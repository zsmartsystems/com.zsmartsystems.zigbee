/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
 * Start Of Week value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum StartOfWeekEnum {

    /**
     * Sunday, 0, 0x0000
     */
    SUNDAY(0x0000),

    /**
     * Monday, 1, 0x0001
     */
    MONDAY(0x0001),

    /**
     * Tuesday, 2, 0x0002
     */
    TUESDAY(0x0002),

    /**
     * Wednesday, 3, 0x0003
     */
    WEDNESDAY(0x0003),

    /**
     * Thursday, 4, 0x0004
     */
    THURSDAY(0x0004),

    /**
     * Friday, 5, 0x0005
     */
    FRIDAY(0x0005),

    /**
     * Saturday, 6, 0x0006
     */
    SATURDAY(0x0006);

    /**
     * A mapping between the integer code and its corresponding StartOfWeekEnum type to facilitate lookup by value.
     */
    private static Map<Integer, StartOfWeekEnum> idMap;

    static {
        idMap = new HashMap<Integer, StartOfWeekEnum>();
        for (StartOfWeekEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private StartOfWeekEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static StartOfWeekEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
