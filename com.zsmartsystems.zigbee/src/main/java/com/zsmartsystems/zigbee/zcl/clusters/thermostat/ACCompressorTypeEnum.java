/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.thermostat;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * AC Compressor Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum ACCompressorTypeEnum {

    /**
     * Reserved, 0, 0x0000
     */
    RESERVED(0x0000),

    /**
     * T1, 1, 0x0001
     */
    T1(0x0001),

    /**
     * T2, 2, 0x0002
     */
    T2(0x0002),

    /**
     * T3, 3, 0x0003
     */
    T3(0x0003);

    /**
     * A mapping between the integer code and its corresponding ACCompressorTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ACCompressorTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, ACCompressorTypeEnum>();
        for (ACCompressorTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private ACCompressorTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ACCompressorTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
