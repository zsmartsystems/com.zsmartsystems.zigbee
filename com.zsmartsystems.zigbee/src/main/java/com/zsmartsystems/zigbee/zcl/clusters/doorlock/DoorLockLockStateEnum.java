/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
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
 * Door Lock Lock State value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-17T10:34:05Z")
public enum DoorLockLockStateEnum {

    /**
     * Not Fully Locked, 0, 0x0000
     */
    NOT_FULLY_LOCKED(0x0000),

    /**
     * Locked, 1, 0x0001
     */
    LOCKED(0x0001),

    /**
     * Unlocked, 2, 0x0002
     */
    UNLOCKED(0x0002),

    /**
     * Undefined, 255, 0x00FF
     */
    UNDEFINED(0x00FF);

    /**
     * A mapping between the integer code and its corresponding DoorLockLockStateEnum type to facilitate lookup by value.
     */
    private static Map<Integer, DoorLockLockStateEnum> idMap;

    static {
        idMap = new HashMap<Integer, DoorLockLockStateEnum>();
        for (DoorLockLockStateEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private DoorLockLockStateEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static DoorLockLockStateEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
