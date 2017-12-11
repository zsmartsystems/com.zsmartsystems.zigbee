/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.powerconfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration of Power configuration attribute BatterySize options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
public enum BatterySizeEnum {
    NO_BATTERY(0x0000),
    BUILD_IN(0x0001),
    OTHER(0x0002),
    AA__CELL(0x0003),
    AAA_CELL(0x0004),
    C_CELL(0x0005),
    D_CELL(0x0006),
    CR2_CELL(0x0007),
    CR123A_CELL(0x0008),
    UNKNOWN(0x00FF);

    /**
     * A mapping between the integer code and its corresponding BatterySizeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, BatterySizeEnum> idMap;

    private final int key;

    BatterySizeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static BatterySizeEnum getByValue(final int value) {
        if (idMap == null) {
            idMap = new HashMap<Integer, BatterySizeEnum>();
            for (BatterySizeEnum enumValue : values()) {
                idMap.put(enumValue.key, enumValue);
            }
        }
        return idMap.get(value);
    }
}
