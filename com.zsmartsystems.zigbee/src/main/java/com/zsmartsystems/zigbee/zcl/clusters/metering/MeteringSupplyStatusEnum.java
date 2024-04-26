/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Metering Supply Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum MeteringSupplyStatusEnum {

    /**
     * Supply Off, 0, 0x0000
     */
    SUPPLY_OFF(0x0000),

    /**
     * Supply Off Armed, 1, 0x0001
     */
    SUPPLY_OFF_ARMED(0x0001),

    /**
     * Supply On, 2, 0x0002
     */
    SUPPLY_ON(0x0002);

    /**
     * A mapping between the integer code and its corresponding MeteringSupplyStatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, MeteringSupplyStatusEnum> idMap;

    static {
        idMap = new HashMap<Integer, MeteringSupplyStatusEnum>();
        for (MeteringSupplyStatusEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private MeteringSupplyStatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static MeteringSupplyStatusEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
