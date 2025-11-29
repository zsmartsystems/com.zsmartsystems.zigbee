/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
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
 * Gp Notification Option value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum GpNotificationOptionBitmap {

    /**
     * Application ID, 7, 0x0007
     */
    APPLICATION_ID(0x0007),

    /**
     * Also Unicast, 8, 0x0008
     */
    ALSO_UNICAST(0x0008),

    /**
     * Also Derived Group, 16, 0x0010
     */
    ALSO_DERIVED_GROUP(0x0010),

    /**
     * Also Commissioned Group, 32, 0x0020
     */
    ALSO_COMMISSIONED_GROUP(0x0020),

    /**
     * Security Level, 192, 0x00C0
     */
    SECURITY_LEVEL(0x00C0),

    /**
     * Security Key Type, 1792, 0x0700
     */
    SECURITY_KEY_TYPE(0x0700),

    /**
     * Rx After Tx, 2048, 0x0800
     */
    RX_AFTER_TX(0x0800),

    /**
     * Gp Tx Queue Full, 4096, 0x1000
     */
    GP_TX_QUEUE_FULL(0x1000),

    /**
     * Bidirectional Capability, 8192, 0x2000
     */
    BIDIRECTIONAL_CAPABILITY(0x2000),

    /**
     * Proxy Info Present, 16384, 0x4000
     */
    PROXY_INFO_PRESENT(0x4000);

    /**
     * A mapping between the integer code and its corresponding GpNotificationOptionBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, GpNotificationOptionBitmap> idMap;

    static {
        idMap = new HashMap<Integer, GpNotificationOptionBitmap>();
        for (GpNotificationOptionBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GpNotificationOptionBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GpNotificationOptionBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
