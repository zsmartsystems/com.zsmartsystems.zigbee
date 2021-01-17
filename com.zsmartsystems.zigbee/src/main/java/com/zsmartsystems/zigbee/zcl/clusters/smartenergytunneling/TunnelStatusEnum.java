/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum TunnelStatusEnum {

    /**
     * Success, 0, 0x0000
     */
    SUCCESS(0x0000),

    /**
     * Busy, 1, 0x0001
     */
    BUSY(0x0001),

    /**
     * No More Tunnel Ids, 2, 0x0002
     */
    NO_MORE_TUNNEL_IDS(0x0002),

    /**
     * Protocol Not Supported, 3, 0x0003
     */
    PROTOCOL_NOT_SUPPORTED(0x0003),

    /**
     * Flow Control Not Supported, 4, 0x0004
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
