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
 * User Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:31:31Z")
public enum DoorLockUserStatusEnum {

    /**
     * Available, 0, 0x0000
     */
    AVAILABLE(0x0000),

    /**
     * Enabled, 1, 0x0001
     */
    ENABLED(0x0001),

    /**
     * Disabled, 2, 0x0002
     */
    DISABLED(0x0002),

    /**
     * Not Supported, 255, 0x00FF
     */
    NOT_SUPPORTED(0x00FF);

    /**
     * A mapping between the integer code and its corresponding DoorLockUserStatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, DoorLockUserStatusEnum> idMap;

    static {
        idMap = new HashMap<Integer, DoorLockUserStatusEnum>();
        for (DoorLockUserStatusEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private DoorLockUserStatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static DoorLockUserStatusEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
