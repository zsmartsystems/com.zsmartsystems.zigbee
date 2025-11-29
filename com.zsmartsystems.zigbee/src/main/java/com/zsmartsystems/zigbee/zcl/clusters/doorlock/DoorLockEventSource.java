/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
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
 * Door Lock Event Source value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:31:31Z")
public enum DoorLockEventSource {

    /**
     * Keypad, 0, 0x0000
     */
    KEYPAD(0x0000),

    /**
     * RF, 1, 0x0001
     */
    RF(0x0001),

    /**
     * Manual, 2, 0x0002
     */
    MANUAL(0x0002),

    /**
     * RFID, 3, 0x0003
     */
    RFID(0x0003),

    /**
     * Indeterminate, 255, 0x00FF
     */
    INDETERMINATE(0x00FF);

    /**
     * A mapping between the integer code and its corresponding DoorLockEventSource type to facilitate lookup by value.
     */
    private static Map<Integer, DoorLockEventSource> idMap;

    static {
        idMap = new HashMap<Integer, DoorLockEventSource>();
        for (DoorLockEventSource enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private DoorLockEventSource(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static DoorLockEventSource getByValue(final int value) {
        return idMap.get(value);
    }
}
