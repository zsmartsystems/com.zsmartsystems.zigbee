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
 * Temperature Setpoint Hold value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum TemperatureSetpointHoldEnum {

    /**
     * Setpoint Hold Off, 0, 0x0000
     */
    SETPOINT_HOLD_OFF(0x0000),

    /**
     * Setpoint Hold On, 1, 0x0001
     */
    SETPOINT_HOLD_ON(0x0001);

    /**
     * A mapping between the integer code and its corresponding TemperatureSetpointHoldEnum type to facilitate lookup by value.
     */
    private static Map<Integer, TemperatureSetpointHoldEnum> idMap;

    static {
        idMap = new HashMap<Integer, TemperatureSetpointHoldEnum>();
        for (TemperatureSetpointHoldEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private TemperatureSetpointHoldEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static TemperatureSetpointHoldEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
