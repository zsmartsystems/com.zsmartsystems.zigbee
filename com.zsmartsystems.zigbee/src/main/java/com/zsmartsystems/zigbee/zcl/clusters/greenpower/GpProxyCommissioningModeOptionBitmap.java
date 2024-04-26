/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Gp Proxy Commissioning Mode Option value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum GpProxyCommissioningModeOptionBitmap {

    /**
     * Action, 1, 0x0001
     */
    ACTION(0x0001),

    /**
     * Exit Mode, 14, 0x000E
     */
    EXIT_MODE(0x000E),

    /**
     * Channel Present, 16, 0x0010
     */
    CHANNEL_PRESENT(0x0010),

    /**
     * Unicast Communication, 32, 0x0020
     */
    UNICAST_COMMUNICATION(0x0020);

    /**
     * A mapping between the integer code and its corresponding GpProxyCommissioningModeOptionBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, GpProxyCommissioningModeOptionBitmap> idMap;

    static {
        idMap = new HashMap<Integer, GpProxyCommissioningModeOptionBitmap>();
        for (GpProxyCommissioningModeOptionBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GpProxyCommissioningModeOptionBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GpProxyCommissioningModeOptionBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
