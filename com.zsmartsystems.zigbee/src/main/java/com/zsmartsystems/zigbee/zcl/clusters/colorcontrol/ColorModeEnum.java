/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 * Color Mode value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum ColorModeEnum {

    /**
     * Current Hue And Current Saturation
     */
    CURRENT_HUE_AND_CURRENT_SATURATION(0x0000),

    /**
     * Current X And Current Y
     */
    CURRENT_X_AND_CURRENT_Y(0x0001),

    /**
     * Color Temperature
     */
    COLOR_TEMPERATURE(0x0002);

    /**
     * A mapping between the integer code and its corresponding ColorModeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ColorModeEnum> idMap;

    static {
        idMap = new HashMap<Integer, ColorModeEnum>();
        for (ColorModeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private ColorModeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ColorModeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
