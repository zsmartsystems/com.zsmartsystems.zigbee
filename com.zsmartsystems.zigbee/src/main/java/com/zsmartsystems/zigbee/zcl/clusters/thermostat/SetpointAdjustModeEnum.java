/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum SetpointAdjustModeEnum {

    /**
     * Heat Setpoint
     */
    HEAT_SETPOINT(0x0000),

    /**
     * Cool Setpoint
     */
    COOL_SETPOINT(0x0001),

    /**
     * Heat And Cool Setpoints
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
