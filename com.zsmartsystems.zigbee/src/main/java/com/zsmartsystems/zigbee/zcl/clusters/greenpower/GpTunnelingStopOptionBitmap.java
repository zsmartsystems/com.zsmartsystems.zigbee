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
 * Gp Tunneling Stop Option value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-07-04T21:54:11Z")
public enum GpTunnelingStopOptionBitmap {

    /**
     * Application ID
     */
    APPLICATION_ID(0x0007),

    /**
     * Also Derived Group
     */
    ALSO_DERIVED_GROUP(0x0008),

    /**
     * Also Commissioned Group
     */
    ALSO_COMMISSIONED_GROUP(0x0010);

    /**
     * A mapping between the integer code and its corresponding GpTunnelingStopOptionBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, GpTunnelingStopOptionBitmap> idMap;

    static {
        idMap = new HashMap<Integer, GpTunnelingStopOptionBitmap>();
        for (GpTunnelingStopOptionBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GpTunnelingStopOptionBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GpTunnelingStopOptionBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
