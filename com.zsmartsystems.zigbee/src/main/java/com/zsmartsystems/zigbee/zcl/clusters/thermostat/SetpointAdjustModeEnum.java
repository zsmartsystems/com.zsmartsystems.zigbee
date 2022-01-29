/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
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
 * Setpoint Adjust Mode value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum SetpointAdjustModeEnum {

    /**
     * Heat Setpoint, 0, 0x0000
     */
    HEAT_SETPOINT(0x0000),

    /**
     * Cool Setpoint, 1, 0x0001
     */
    COOL_SETPOINT(0x0001),

    /**
     * Heat And Cool Setpoints, 2, 0x0002
     */
    HEAT_AND_COOL_SETPOINTS(0x0002);

    /**
     * A mapping between the integer code and its corresponding SetpointAdjustModeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, SetpointAdjustModeEnum> idMap;

    static {
        idMap = new HashMap<Integer, SetpointAdjustModeEnum>();
        for (SetpointAdjustModeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private SetpointAdjustModeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static SetpointAdjustModeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
