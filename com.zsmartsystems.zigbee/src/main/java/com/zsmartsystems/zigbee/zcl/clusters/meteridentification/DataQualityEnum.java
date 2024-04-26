/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.meteridentification;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Data Quality value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum DataQualityEnum {

    /**
     * All Data Certified, 0, 0x0000
     */
    ALL_DATA_CERTIFIED(0x0000),

    /**
     * Only Instantaneous Power not Certified, 1, 0x0001
     */
    ONLY_INSTANTANEOUS_POWER_NOT_CERTIFIED(0x0001),

    /**
     * Only Cumulated Consumption not Certified, 2, 0x0002
     */
    ONLY_CUMULATED_CONSUMPTION_NOT_CERTIFIED(0x0002),

    /**
     * Not Certified data, 3, 0x0003
     */
    NOT_CERTIFIED_DATA(0x0003);

    /**
     * A mapping between the integer code and its corresponding DataQualityEnum type to facilitate lookup by value.
     */
    private static Map<Integer, DataQualityEnum> idMap;

    static {
        idMap = new HashMap<Integer, DataQualityEnum>();
        for (DataQualityEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private DataQualityEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static DataQualityEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
