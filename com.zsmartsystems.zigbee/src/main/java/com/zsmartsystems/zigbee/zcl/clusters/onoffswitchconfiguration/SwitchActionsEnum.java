/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
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
 * Switch Actions value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum SwitchActionsEnum {

    /**
     * On, 0, 0x0000
     */
    ON(0x0000),

    /**
     * Off, 1, 0x0001
     */
    OFF(0x0001),

    /**
     * Toggle, 2, 0x0002
     */
    TOGGLE(0x0002);

    /**
     * A mapping between the integer code and its corresponding SwitchActionsEnum type to facilitate lookup by value.
     */
    private static Map<Integer, SwitchActionsEnum> idMap;

    static {
        idMap = new HashMap<Integer, SwitchActionsEnum>();
        for (SwitchActionsEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private SwitchActionsEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static SwitchActionsEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
