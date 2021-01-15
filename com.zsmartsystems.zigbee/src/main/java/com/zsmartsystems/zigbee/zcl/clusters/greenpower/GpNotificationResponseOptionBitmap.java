/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
 * Gp Notification Response Option value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum GpNotificationResponseOptionBitmap {

    /**
     * Application ID, 7, 0x0007
     */
    APPLICATION_ID(0x0007),

    /**
     * First To Forward, 8, 0x0008
     */
    FIRST_TO_FORWARD(0x0008),

    /**
     * No Pairing, 16, 0x0010
     */
    NO_PAIRING(0x0010);

    /**
     * A mapping between the integer code and its corresponding GpNotificationResponseOptionBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, GpNotificationResponseOptionBitmap> idMap;

    static {
        idMap = new HashMap<Integer, GpNotificationResponseOptionBitmap>();
        for (GpNotificationResponseOptionBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GpNotificationResponseOptionBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GpNotificationResponseOptionBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
