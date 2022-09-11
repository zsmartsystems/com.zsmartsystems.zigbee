/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
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
 * Gp Pairing Search Option value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum GpPairingSearchOptionBitmap {

    /**
     * Application ID, 7, 0x0007
     */
    APPLICATION_ID(0x0007),

    /**
     * Request Unicast Sinks, 8, 0x0008
     */
    REQUEST_UNICAST_SINKS(0x0008),

    /**
     * Request Derived Groupcast Sinks, 16, 0x0010
     */
    REQUEST_DERIVED_GROUPCAST_SINKS(0x0010),

    /**
     * Request Commissioned Groupcast Sinks, 32, 0x0020
     */
    REQUEST_COMMISSIONED_GROUPCAST_SINKS(0x0020),

    /**
     * Request Gpd Security Frame Counter, 64, 0x0040
     */
    REQUEST_GPD_SECURITY_FRAME_COUNTER(0x0040),

    /**
     * Request Gpd Security Key, 128, 0x0080
     */
    REQUEST_GPD_SECURITY_KEY(0x0080);

    /**
     * A mapping between the integer code and its corresponding GpPairingSearchOptionBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, GpPairingSearchOptionBitmap> idMap;

    static {
        idMap = new HashMap<Integer, GpPairingSearchOptionBitmap>();
        for (GpPairingSearchOptionBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GpPairingSearchOptionBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GpPairingSearchOptionBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
