/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
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
 * AC Refrigerant Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum ACRefrigerantTypeEnum {

    /**
     * Reserved, 0, 0x0000
     */
    RESERVED(0x0000),

    /**
     * R22, 1, 0x0001
     */
    R22(0x0001),

    /**
     * R410a, 2, 0x0002
     */
    R410A(0x0002),

    /**
     * R407c, 3, 0x0003
     */
    R407C(0x0003);

    /**
     * A mapping between the integer code and its corresponding ACRefrigerantTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ACRefrigerantTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, ACRefrigerantTypeEnum>();
        for (ACRefrigerantTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private ACRefrigerantTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ACRefrigerantTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
