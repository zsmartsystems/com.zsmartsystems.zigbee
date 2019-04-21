/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Tunnel Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum TunnelStatusEnum {

    /**
     * Success
     */
    SUCCESS(0x0000),

    /**
     * Busy
     */
    BUSY(0x0001),

    /**
     * No More Tunnel Ids
     */
    NO_MORE_TUNNEL_IDS(0x0002),

    /**
     * Protocol Not Supported
     */
    PROTOCOL_NOT_SUPPORTED(0x0003),

    /**
     * Flow Control Not Supported
     */
    FLOW_CONTROL_NOT_SUPPORTED(0x0004);

    /**
     * A mapping between the integer code and its corresponding TunnelStatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, TunnelStatusEnum> idMap;

    static {
        idMap = new HashMap<Integer, TunnelStatusEnum>();
        for (TunnelStatusEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private TunnelStatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static TunnelStatusEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
