/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Gp Device ID value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum GpDeviceIdEnum {

    /**
     * Gp Simple Generice Two State Switch, 0, 0x0000
     */
    GP_SIMPLE_GENERICE_TWO_STATE_SWITCH(0x0000),

    /**
     * Gp On Off Switch, 8, 0x0008
     */
    GP_ON_OFF_SWITCH(0x0008),

    /**
     * Gp Level Control Switch, 16, 0x0010
     */
    GP_LEVEL_CONTROL_SWITCH(0x0010),

    /**
     * Gp Indoor Environment Snesor, 24, 0x0018
     */
    GP_INDOOR_ENVIRONMENT_SNESOR(0x0018);

    /**
     * A mapping between the integer code and its corresponding GpDeviceIdEnum type to facilitate lookup by value.
     */
    private static Map<Integer, GpDeviceIdEnum> idMap;

    static {
        idMap = new HashMap<Integer, GpDeviceIdEnum>();
        for (GpDeviceIdEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GpDeviceIdEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GpDeviceIdEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
