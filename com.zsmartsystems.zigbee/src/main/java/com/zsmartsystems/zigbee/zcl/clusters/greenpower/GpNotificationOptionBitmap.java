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
 * Gp Notification Option value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-07-04T21:54:11Z")
public enum GpNotificationOptionBitmap {

    /**
     * Application ID
     */
    APPLICATION_ID(0x0007),

    /**
     * Also Unicast
     */
    ALSO_UNICAST(0x0008),

    /**
     * Also Derived Group
     */
    ALSO_DERIVED_GROUP(0x0010),

    /**
     * Also Commissioned Group
     */
    ALSO_COMMISSIONED_GROUP(0x0020),

    /**
     * Security Level
     */
    SECURITY_LEVEL(0x00C0),

    /**
     * Security Key Type
     */
    SECURITY_KEY_TYPE(0x0700),

    /**
     * Rx After Tx
     */
    RX_AFTER_TX(0x0800),

    /**
     * Gp Tx Queue Full
     */
    GP_TX_QUEUE_FULL(0x1000),

    /**
     * Bidirectional Capability
     */
    BIDIRECTIONAL_CAPABILITY(0x2000),

    /**
     * Proxy Info Present
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
