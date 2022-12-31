/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
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
 * Transfer Data Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum TransferDataStatusEnum {

    /**
     * No Such Tunnel, 0, 0x0000
     */
    NO_SUCH_TUNNEL(0x0000),

    /**
     * Wrong Device, 1, 0x0001
     */
    WRONG_DEVICE(0x0001),

    /**
     * Data Overflow, 2, 0x0002
     */
    DATA_OVERFLOW(0x0002);

    /**
     * A mapping between the integer code and its corresponding TransferDataStatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, TransferDataStatusEnum> idMap;

    static {
        idMap = new HashMap<Integer, TransferDataStatusEnum>();
        for (TransferDataStatusEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private TransferDataStatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static TransferDataStatusEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
