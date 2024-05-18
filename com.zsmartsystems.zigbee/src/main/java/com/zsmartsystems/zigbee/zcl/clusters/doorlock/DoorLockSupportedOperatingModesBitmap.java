/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.doorlock;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Door Lock Lock Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-17T10:34:05Z")
public enum DoorLockSupportedOperatingModesBitmap {

    /**
     * Normal, 0, 0x0000
     */
    NORMAL(0x0000),

    /**
     * Vacation, 1, 0x0001
     */
    VACATION(0x0001),

    /**
     * Privacy, 2, 0x0002
     */
    PRIVACY(0x0002),

    /**
     * No RF Lock/Unlock, 3, 0x0003
     */
    NO_RF_LOCK_UNLOCK(0x0003),

    /**
     * Passage, 4, 0x0004
     */
    PASSAGE(0x0004);

    /**
     * A mapping between the integer code and its corresponding DoorLockSupportedOperatingModesBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, DoorLockSupportedOperatingModesBitmap> idMap;

    static {
        idMap = new HashMap<Integer, DoorLockSupportedOperatingModesBitmap>();
        for (DoorLockSupportedOperatingModesBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private DoorLockSupportedOperatingModesBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static DoorLockSupportedOperatingModesBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
