/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.colorcontrol;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration of Color control attribute EnhancedColorMode options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
public enum EnhancedColorModeEnum {
    CURRENTHUE_AND_CURRENTSATURATION(0x0000),
    CURRENTX_AND_CURRENTY(0x0001),
    ENHANCEDCURRENTHUE_AND_CURRENTSATURATION(0x0002);

    /**
     * A mapping between the integer code and its corresponding EnhancedColorModeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, EnhancedColorModeEnum> idMap;

    private final int key;

    EnhancedColorModeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static EnhancedColorModeEnum getByValue(final int value) {
        if (idMap == null) {
            idMap = new HashMap<Integer, EnhancedColorModeEnum>();
            for (EnhancedColorModeEnum enumValue : values()) {
                idMap.put(enumValue.key, enumValue);
            }
        }
        return idMap.get(value);
    }
}
