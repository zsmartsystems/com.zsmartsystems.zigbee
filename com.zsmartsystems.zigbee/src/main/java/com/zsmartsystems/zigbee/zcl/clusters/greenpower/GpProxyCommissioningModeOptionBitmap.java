/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-07-04T21:54:11Z")
public enum GpProxyCommissioningModeOptionBitmap {

    /**
     * Action
     */
    ACTION(0x0001),

    /**
     * Exit Mode
     */
    EXIT_MODE(0x000E),

    /**
     * Channel Present
     */
    CHANNEL_PRESENT(0x0010),

    /**
     * Unicast Communication
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
