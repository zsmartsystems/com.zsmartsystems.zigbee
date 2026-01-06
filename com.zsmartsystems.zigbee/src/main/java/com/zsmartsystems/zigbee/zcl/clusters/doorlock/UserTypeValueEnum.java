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
 * User Type Value value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-17T10:52:31Z")
public enum UserTypeValueEnum {

    /**
     * Unrestricted User, 0, 0x0000
     */
    UNRESTRICTED_USER(0x0000),

    /**
     * Year Day Schedule User, 1, 0x0001
     */
    YEAR_DAY_SCHEDULE_USER(0x0001),

    /**
     * Week Day Schedule User, 2, 0x0002
     */
    WEEK_DAY_SCHEDULE_USER(0x0002),

    /**
     * Master User, 3, 0x0003
     */
    MASTER_USER(0x0003),

    /**
     * Non Access User, 4, 0x0004
     */
    NON_ACCESS_USER(0x0004),

    /**
     * Not Supported, 255, 0x00FF
     */
    NOT_SUPPORTED(0x00FF);

    /**
     * A mapping between the integer code and its corresponding UserTypeValueEnum type to facilitate lookup by value.
     */
    private static Map<Integer, UserTypeValueEnum> idMap;

    static {
        idMap = new HashMap<Integer, UserTypeValueEnum>();
        for (UserTypeValueEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private UserTypeValueEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static UserTypeValueEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
