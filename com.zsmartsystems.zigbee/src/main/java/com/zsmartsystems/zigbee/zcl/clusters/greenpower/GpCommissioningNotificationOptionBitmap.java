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
 * Gp Commissioning Notification Option value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-07-04T21:54:11Z")
public enum GpCommissioningNotificationOptionBitmap {

    /**
     * Application ID
     */
    APPLICATION_ID(0x0007),

    /**
     * Rx After Tx
     */
    RX_AFTER_TX(0x0008),

    /**
     * Security Level
     */
    SECURITY_LEVEL(0x0030),

    /**
     * Security Key Type
     */
    SECURITY_KEY_TYPE(0x01C0),

    /**
     * Security Processing Failed
     */
    SECURITY_PROCESSING_FAILED(0x0200),

    /**
     * Bidirectional Capability
     */
    BIDIRECTIONAL_CAPABILITY(0x0400),

    /**
     * Proxy Info Present
     */
    PROXY_INFO_PRESENT(0x0800);

    /**
     * A mapping between the integer code and its corresponding GpCommissioningNotificationOptionBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, GpCommissioningNotificationOptionBitmap> idMap;

    static {
        idMap = new HashMap<Integer, GpCommissioningNotificationOptionBitmap>();
        for (GpCommissioningNotificationOptionBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GpCommissioningNotificationOptionBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GpCommissioningNotificationOptionBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
