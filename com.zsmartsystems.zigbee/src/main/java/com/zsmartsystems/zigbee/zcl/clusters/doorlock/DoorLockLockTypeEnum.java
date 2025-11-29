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
 * Door Lock Lock Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-17T10:34:05Z")
public enum DoorLockLockTypeEnum {

    /**
     * Dead Bolt, 0, 0x0000
     */
    DEAD_BOLT(0x0000),

    /**
     * Magnetic, 1, 0x0001
     */
    MAGNETIC(0x0001),

    /**
     * Other, 2, 0x0002
     */
    OTHER(0x0002),

    /**
     * Mortise, 3, 0x0003
     */
    MORTISE(0x0003),

    /**
     * Rim, 4, 0x0004
     */
    RIM(0x0004),

    /**
     * Latch Bolt, 5, 0x0005
     */
    LATCH_BOLT(0x0005),

    /**
     * Cylindrical Lock, 6, 0x0006
     */
    CYLINDRICAL_LOCK(0x0006),

    /**
     * Tubular Lock, 7, 0x0007
     */
    TUBULAR_LOCK(0x0007),

    /**
     * Interconnected Lock, 8, 0x0008
     */
    INTERCONNECTED_LOCK(0x0008),

    /**
     * Dead Latch, 9, 0x0009
     */
    DEAD_LATCH(0x0009),

    /**
     * Door Furniture, 10, 0x000A
     */
    DOOR_FURNITURE(0x000A),

    /**
     * Undefined, 255, 0x00FF
     */
    UNDEFINED(0x00FF);

    /**
     * A mapping between the integer code and its corresponding DoorLockLockTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, DoorLockLockTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, DoorLockLockTypeEnum>();
        for (DoorLockLockTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private DoorLockLockTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static DoorLockLockTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
