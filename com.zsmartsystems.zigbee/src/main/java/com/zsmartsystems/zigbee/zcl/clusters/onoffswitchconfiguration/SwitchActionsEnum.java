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
 * Enumeration of On/off Switch Configuration attribute SwitchActions options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
public enum SwitchActionsEnum {
    ON(0x0000),
    OFF(0x0001),
    TOGGLE(0x0002);

    /**
     * A mapping between the integer code and its corresponding SwitchActionsEnum type to facilitate lookup by value.
     */
    private static Map<Integer, SwitchActionsEnum> idMap;

    private final int key;

    SwitchActionsEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static SwitchActionsEnum getByValue(final int value) {
        if (idMap == null) {
            idMap = new HashMap<Integer, SwitchActionsEnum>();
            for (SwitchActionsEnum enumValue : values()) {
                idMap.put(enumValue.key, enumValue);
            }
        }
        return idMap.get(value);
    }
}
