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
 * Door Lock Operation Event Code value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:31:31Z")
public enum DoorLockOperationEventCode {

    /**
     * Unknown Or Mfg Specific, 0, 0x0000
     */
    UNKNOWN_OR_MFG_SPECIFIC(0x0000),

    /**
     * Lock, 1, 0x0001
     */
    LOCK(0x0001),

    /**
     * Unlock, 2, 0x0002
     */
    UNLOCK(0x0002),

    /**
     * Lock Failure Invalid PIN or ID, 3, 0x0003
     */
    LOCK_FAILURE_INVALID_PIN_OR_ID(0x0003),

    /**
     * Lock Failure Invalid Schedule, 4, 0x0004
     */
    LOCK_FAILURE_INVALID_SCHEDULE(0x0004),

    /**
     * Unlock Failure Invalid PIN or ID, 5, 0x0005
     */
    UNLOCK_FAILURE_INVALID_PIN_OR_ID(0x0005),

    /**
     * Unlock Failure Invalid Schedule, 6, 0x0006
     */
    UNLOCK_FAILURE_INVALID_SCHEDULE(0x0006),

    /**
     * One Touch Lock, 7, 0x0007
     */
    ONE_TOUCH_LOCK(0x0007),

    /**
     * Key Lock, 8, 0x0008
     */
    KEY_LOCK(0x0008),

    /**
     * Key Unlock, 9, 0x0009
     */
    KEY_UNLOCK(0x0009),

    /**
     * Auto Lock, 10, 0x000A
     */
    AUTO_LOCK(0x000A),

    /**
     * Schedule Lock, 11, 0x000B
     */
    SCHEDULE_LOCK(0x000B),

    /**
     * Schedule Unlock, 12, 0x000C
     */
    SCHEDULE_UNLOCK(0x000C),

    /**
     * Manual Lock, 13, 0x000D
     */
    MANUAL_LOCK(0x000D),

    /**
     * Manual Unlock, 14, 0x000E
     */
    MANUAL_UNLOCK(0x000E),

    /**
     * Non Access User Event, 15, 0x000F
     */
    NON_ACCESS_USER_EVENT(0x000F);

    /**
     * A mapping between the integer code and its corresponding DoorLockOperationEventCode type to facilitate lookup by value.
     */
    private static Map<Integer, DoorLockOperationEventCode> idMap;

    static {
        idMap = new HashMap<Integer, DoorLockOperationEventCode>();
        for (DoorLockOperationEventCode enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private DoorLockOperationEventCode(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static DoorLockOperationEventCode getByValue(final int value) {
        return idMap.get(value);
    }
}
