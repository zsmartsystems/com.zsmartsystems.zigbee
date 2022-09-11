/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.multistatevaluebasic;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Status Flags value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum MultistateValueStatusFlagsBitmap {

    /**
     * In_Alarm, 1, 0x0001
     */
    IN_ALARM(0x0001),

    /**
     * Fault, 2, 0x0002
     */
    FAULT(0x0002),

    /**
     * Overridden, 4, 0x0004
     */
    OVERRIDDEN(0x0004),

    /**
     * Out Of Service, 8, 0x0008
     */
    OUT_OF_SERVICE(0x0008);

    /**
     * A mapping between the integer code and its corresponding MultistateValueStatusFlagsBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, MultistateValueStatusFlagsBitmap> idMap;

    static {
        idMap = new HashMap<Integer, MultistateValueStatusFlagsBitmap>();
        for (MultistateValueStatusFlagsBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private MultistateValueStatusFlagsBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static MultistateValueStatusFlagsBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
