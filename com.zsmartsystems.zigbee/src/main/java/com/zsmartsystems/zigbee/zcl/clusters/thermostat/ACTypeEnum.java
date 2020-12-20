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
 * AC Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-20T07:40:15Z")
public enum ACTypeEnum {

    /**
     * Reserved
     */
    RESERVED(0x0000),

    /**
     * Cooling and Fixed Speed
     */
    COOLING_AND_FIXED_SPEED(0x0001),

    /**
     * Heat Pump and Fixed Speed
     */
    HEAT_PUMP_AND_FIXED_SPEED(0x0002),

    /**
     * Cooling and Inverter
     */
    COOLING_AND_INVERTER(0x0003),

    /**
     * Heat Pump and Inverter
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
