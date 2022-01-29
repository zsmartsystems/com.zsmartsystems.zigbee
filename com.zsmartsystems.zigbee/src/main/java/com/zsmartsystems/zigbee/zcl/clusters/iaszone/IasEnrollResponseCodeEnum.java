/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
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
 * IAS Enroll Response Code value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum IasEnrollResponseCodeEnum {

    /**
     * Success, 0, 0x0000
     */
    SUCCESS(0x0000),

    /**
     * Not Supported, 1, 0x0001
     */
    NOT_SUPPORTED(0x0001),

    /**
     * No Enroll Permit, 2, 0x0002
     */
    NO_ENROLL_PERMIT(0x0002),

    /**
     * Too Many Zones, 3, 0x0003
     */
    TOO_MANY_ZONES(0x0003);

    /**
     * A mapping between the integer code and its corresponding IasEnrollResponseCodeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, IasEnrollResponseCodeEnum> idMap;

    static {
        idMap = new HashMap<Integer, IasEnrollResponseCodeEnum>();
        for (IasEnrollResponseCodeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private IasEnrollResponseCodeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static IasEnrollResponseCodeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
