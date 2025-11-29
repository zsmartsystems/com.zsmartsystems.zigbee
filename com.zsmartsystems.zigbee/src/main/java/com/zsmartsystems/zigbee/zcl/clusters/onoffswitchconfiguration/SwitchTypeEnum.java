/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.onoffswitchconfiguration;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Switch Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum SwitchTypeEnum {

    /**
     * Toggle, 0, 0x0000
     */
    TOGGLE(0x0000),

    /**
     * Momentary, 1, 0x0001
     */
    MOMENTARY(0x0001),

    /**
     * Multifunction, 2, 0x0002
     */
    MULTIFUNCTION(0x0002);

    /**
     * A mapping between the integer code and its corresponding SwitchTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, SwitchTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, SwitchTypeEnum>();
        for (SwitchTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private SwitchTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static SwitchTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
