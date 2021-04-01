/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iaszone;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * IAS Zone Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum IasZoneStatusBitmap {

    /**
     * Alarm 1, 1, 0x0001
     */
    ALARM_1(0x0001),

    /**
     * Alarm 2, 2, 0x0002
     */
    ALARM_2(0x0002),

    /**
     * Tamper, 4, 0x0004
     */
    TAMPER(0x0004),

    /**
     * Battery, 8, 0x0008
     */
    BATTERY(0x0008),

    /**
     * Supervision Reports, 16, 0x0010
     */
    SUPERVISION_REPORTS(0x0010),

    /**
     * Restore Reports, 32, 0x0020
     */
    RESTORE_REPORTS(0x0020),

    /**
     * Trouble, 64, 0x0040
     */
    TROUBLE(0x0040),

    /**
     * AC, 128, 0x0080
     */
    AC(0x0080),

    /**
     * Test, 256, 0x0100
     */
    TEST(0x0100),

    /**
     * Battery Defect, 512, 0x0200
     */
    BATTERY_DEFECT(0x0200);

    /**
     * A mapping between the integer code and its corresponding IasZoneStatusBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, IasZoneStatusBitmap> idMap;

    static {
        idMap = new HashMap<Integer, IasZoneStatusBitmap>();
        for (IasZoneStatusBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private IasZoneStatusBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static IasZoneStatusBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
