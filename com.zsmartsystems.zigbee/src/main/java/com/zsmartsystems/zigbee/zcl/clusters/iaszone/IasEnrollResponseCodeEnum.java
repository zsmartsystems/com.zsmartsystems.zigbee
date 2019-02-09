/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum IasEnrollResponseCodeEnum {

    /**
     * Success
     */
    SUCCESS(0x0000),

    /**
     * Not Supported
     */
    NOT_SUPPORTED(0x0001),

    /**
     * No Enroll Permit
     */
    NO_ENROLL_PERMIT(0x0002),

    /**
     * Too Many Zones
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
