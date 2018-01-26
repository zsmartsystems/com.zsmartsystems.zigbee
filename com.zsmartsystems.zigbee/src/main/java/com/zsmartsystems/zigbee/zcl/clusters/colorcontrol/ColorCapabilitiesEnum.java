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
 * Enumeration of Color control attribute ColorCapabilities options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
public enum ColorCapabilitiesEnum {
    HUE_AND_SATURATION(0x0001),
    ENHANCED_HUE(0x0002),
    COLOR_LOOP(0x0004),
    XY_ATTRIBUTE(0x0008),
    COLOR_TEMPERATURE(0x0010);

    /**
     * A mapping between the integer code and its corresponding ColorCapabilitiesEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ColorCapabilitiesEnum> idMap;

    private final int key;

    ColorCapabilitiesEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ColorCapabilitiesEnum getByValue(final int value) {
        if (idMap == null) {
            idMap = new HashMap<Integer, ColorCapabilitiesEnum>();
            for (ColorCapabilitiesEnum enumValue : values()) {
                idMap.put(enumValue.key, enumValue);
            }
        }
        return idMap.get(value);
    }
}
