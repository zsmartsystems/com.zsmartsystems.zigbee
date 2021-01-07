/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.onoff;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-07T07:33:30Z")
public enum StartUpOnOffEnum {

    /**
     * Off
     */
    OFF(0x0000),

    /**
     * On
     */
    ON(0x0001),

    /**
     * Toggle
     */
    TOGGLE(0x0002),

    /**
     * Previous
     */
    PREVIOUS(0x00FF);

    /**
     * A mapping between the integer code and its corresponding StartUpOnOffEnum type to facilitate lookup by value.
     */
    private static Map<Integer, StartUpOnOffEnum> idMap;

    static {
        idMap = new HashMap<Integer, StartUpOnOffEnum>();
        for (StartUpOnOffEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private StartUpOnOffEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static StartUpOnOffEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
