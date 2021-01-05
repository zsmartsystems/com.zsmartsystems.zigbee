/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.colorcontrol;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Step Mode value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum StepModeEnum {

    /**
     * Up, 1, 0x0001
     */
    UP(0x0001),

    /**
     * Down, 3, 0x0003
     */
    DOWN(0x0003);

    /**
     * A mapping between the integer code and its corresponding StepModeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, StepModeEnum> idMap;

    static {
        idMap = new HashMap<Integer, StepModeEnum>();
        for (StepModeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private StepModeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static StepModeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
