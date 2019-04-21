/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 * Mode For Sequence value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum ModeForSequenceBitmap {

    /**
     * Heat Setpoint Field Present
     */
    HEAT_SETPOINT_FIELD_PRESENT(0x0001),

    /**
     * Cool Setpoint Field Present
     */
    COOL_SETPOINT_FIELD_PRESENT(0x0002);

    /**
     * A mapping between the integer code and its corresponding ModeForSequenceBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, ModeForSequenceBitmap> idMap;

    static {
        idMap = new HashMap<Integer, ModeForSequenceBitmap>();
        for (ModeForSequenceBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private ModeForSequenceBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ModeForSequenceBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
