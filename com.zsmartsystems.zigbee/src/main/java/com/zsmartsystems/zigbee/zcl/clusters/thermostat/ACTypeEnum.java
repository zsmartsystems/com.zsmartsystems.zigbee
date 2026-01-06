/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
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
 * AC Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum ACTypeEnum {

    /**
     * Reserved, 0, 0x0000
     */
    RESERVED(0x0000),

    /**
     * Cooling and Fixed Speed, 1, 0x0001
     */
    COOLING_AND_FIXED_SPEED(0x0001),

    /**
     * Heat Pump and Fixed Speed, 2, 0x0002
     */
    HEAT_PUMP_AND_FIXED_SPEED(0x0002),

    /**
     * Cooling and Inverter, 3, 0x0003
     */
    COOLING_AND_INVERTER(0x0003),

    /**
     * Heat Pump and Inverter, 4, 0x0004
     */
    HEAT_PUMP_AND_INVERTER(0x0004);

    /**
     * A mapping between the integer code and its corresponding ACTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ACTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, ACTypeEnum>();
        for (ACTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private ACTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ACTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
