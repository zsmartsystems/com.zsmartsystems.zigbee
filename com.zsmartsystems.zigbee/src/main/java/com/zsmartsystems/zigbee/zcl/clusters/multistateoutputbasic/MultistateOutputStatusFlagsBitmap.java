/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.multistateoutputbasic;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Status Flags value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum MultistateOutputStatusFlagsBitmap {

    /**
     * In_Alarm
     */
    IN_ALARM(0x0001),

    /**
     * Fault
     */
    FAULT(0x0002),

    /**
     * Overridden
     */
    OVERRIDDEN(0x0004),

    /**
     * Out Of Service
     */
    OUT_OF_SERVICE(0x0008);

    /**
     * A mapping between the integer code and its corresponding MultistateOutputStatusFlagsBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, MultistateOutputStatusFlagsBitmap> idMap;

    static {
        idMap = new HashMap<Integer, MultistateOutputStatusFlagsBitmap>();
        for (MultistateOutputStatusFlagsBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private MultistateOutputStatusFlagsBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static MultistateOutputStatusFlagsBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
