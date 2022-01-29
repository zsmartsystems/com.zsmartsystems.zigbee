/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
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
 * Day Of Week value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum DayOfWeekBitmap {

    /**
     * Sunday, 1, 0x0001
     */
    SUNDAY(0x0001),

    /**
     * Monday, 2, 0x0002
     */
    MONDAY(0x0002),

    /**
     * Tuesday, 4, 0x0004
     */
    TUESDAY(0x0004),

    /**
     * Wednesday, 8, 0x0008
     */
    WEDNESDAY(0x0008),

    /**
     * Thursday, 16, 0x0010
     */
    THURSDAY(0x0010),

    /**
     * Friday, 32, 0x0020
     */
    FRIDAY(0x0020),

    /**
     * Saturday, 64, 0x0040
     */
    SATURDAY(0x0040),

    /**
     * Away Or Vacation, 128, 0x0080
     */
    AWAY_OR_VACATION(0x0080);

    /**
     * A mapping between the integer code and its corresponding DayOfWeekBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, DayOfWeekBitmap> idMap;

    static {
        idMap = new HashMap<Integer, DayOfWeekBitmap>();
        for (DayOfWeekBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private DayOfWeekBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static DayOfWeekBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
