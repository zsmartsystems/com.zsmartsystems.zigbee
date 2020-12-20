/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.thermostat;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * AC Louver Position value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-20T07:40:15Z")
public enum ACLouverPositionEnum {

    /**
     * Fully Closed
     */
    FULLY_CLOSED(0x0001),

    /**
     * Fully Open
     */
    FULLY_OPEN(0x0002),

    /**
     * Quarter Open
     */
    QUARTER_OPEN(0x0003),

    /**
     * Half Open
     */
    HALF_OPEN(0x0004),

    /**
     * Three Quarters Open
     */
    THREE_QUARTERS_OPEN(0x0005);

    /**
     * A mapping between the integer code and its corresponding ACLouverPositionEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ACLouverPositionEnum> idMap;

    static {
        idMap = new HashMap<Integer, ACLouverPositionEnum>();
        for (ACLouverPositionEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private ACLouverPositionEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ACLouverPositionEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
