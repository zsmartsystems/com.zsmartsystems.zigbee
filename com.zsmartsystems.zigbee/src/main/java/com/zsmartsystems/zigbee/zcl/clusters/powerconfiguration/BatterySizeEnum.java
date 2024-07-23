/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.powerconfiguration;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Battery Size value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum BatterySizeEnum {

    /**
     * No Battery, 0, 0x0000
     */
    NO_BATTERY(0x0000),

    /**
     * Build In, 1, 0x0001
     */
    BUILD_IN(0x0001),

    /**
     * Other, 2, 0x0002
     */
    OTHER(0x0002),

    /**
     * AA Cell, 3, 0x0003
     */
    AA_CELL(0x0003),

    /**
     * AAA Cell, 4, 0x0004
     */
    AAA_CELL(0x0004),

    /**
     * C Cell, 5, 0x0005
     */
    C_CELL(0x0005),

    /**
     * D Cell, 6, 0x0006
     */
    D_CELL(0x0006),

    /**
     * CR 2 Cell, 7, 0x0007
     */
    CR_2_CELL(0x0007),

    /**
     * CR 123 A Cell, 8, 0x0008
     */
    CR_123_A_CELL(0x0008),

    /**
     * Unknown, 255, 0x00FF
     */
    UNKNOWN(0x00FF);

    /**
     * A mapping between the integer code and its corresponding BatterySizeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, BatterySizeEnum> idMap;

    static {
        idMap = new HashMap<Integer, BatterySizeEnum>();
        for (BatterySizeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private BatterySizeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static BatterySizeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
