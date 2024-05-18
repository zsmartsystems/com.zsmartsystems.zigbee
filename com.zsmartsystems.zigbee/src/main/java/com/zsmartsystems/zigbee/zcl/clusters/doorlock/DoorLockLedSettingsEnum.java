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
 * Door Lock LED Settings value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-17T10:34:05Z")
public enum DoorLockLedSettingsEnum {

    /**
     * Never, 0, 0x0000
     */
    NEVER(0x0000),

    /**
     * All Except Allowed Access, 1, 0x0001
     */
    ALL_EXCEPT_ALLOWED_ACCESS(0x0001),

    /**
     * All, 2, 0x0002
     */
    ALL(0x0002);

    /**
     * A mapping between the integer code and its corresponding DoorLockLedSettingsEnum type to facilitate lookup by value.
     */
    private static Map<Integer, DoorLockLedSettingsEnum> idMap;

    static {
        idMap = new HashMap<Integer, DoorLockLedSettingsEnum>();
        for (DoorLockLedSettingsEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private DoorLockLedSettingsEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static DoorLockLedSettingsEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
