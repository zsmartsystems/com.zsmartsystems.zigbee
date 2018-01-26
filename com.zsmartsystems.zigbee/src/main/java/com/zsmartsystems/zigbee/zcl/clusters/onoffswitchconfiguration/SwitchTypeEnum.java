/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.onoffswitchconfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration of On/off Switch Configuration attribute SwitchType options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
public enum SwitchTypeEnum {
    TOGGLE(0x0000),
    MOMENTARY(0x0001),
    MULTIFUNCTION(0x0002);

    /**
     * A mapping between the integer code and its corresponding SwitchTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, SwitchTypeEnum> idMap;

    private final int key;

    SwitchTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static SwitchTypeEnum getByValue(final int value) {
        if (idMap == null) {
            idMap = new HashMap<Integer, SwitchTypeEnum>();
            for (SwitchTypeEnum enumValue : values()) {
                idMap.put(enumValue.key, enumValue);
            }
        }
        return idMap.get(value);
    }
}
