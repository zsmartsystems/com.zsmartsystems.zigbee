/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
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
 * Metering Device Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum MeteringDeviceTypeEnum {

    /**
     * Electric Metering, 0, 0x0000
     */
    ELECTRIC_METERING(0x0000),

    /**
     * Gas Metering, 1, 0x0001
     */
    GAS_METERING(0x0001),

    /**
     * Water Metering, 2, 0x0002
     */
    WATER_METERING(0x0002),

    /**
     * Thermal Metering, 3, 0x0003
     */
    THERMAL_METERING(0x0003),

    /**
     * Pressure Metering, 4, 0x0004
     */
    PRESSURE_METERING(0x0004),

    /**
     * Heat Metering, 5, 0x0005
     */
    HEAT_METERING(0x0005),

    /**
     * Cooling Metering, 6, 0x0006
     */
    COOLING_METERING(0x0006),

    /**
     * Mirrored Gas Metering, 128, 0x0080
     */
    MIRRORED_GAS_METERING(0x0080),

    /**
     * Mirrored Water Metering, 129, 0x0081
     */
    MIRRORED_WATER_METERING(0x0081),

    /**
     * Mirrored Thermal Metering, 130, 0x0082
     */
    MIRRORED_THERMAL_METERING(0x0082),

    /**
     * Mirrored Pressure Metering, 131, 0x0083
     */
    MIRRORED_PRESSURE_METERING(0x0083),

    /**
     * Mirrored Heat Metering, 132, 0x0084
     */
    MIRRORED_HEAT_METERING(0x0084),

    /**
     * Mirrored Cooling Metering, 133, 0x0085
     */
    MIRRORED_COOLING_METERING(0x0085);

    /**
     * A mapping between the integer code and its corresponding MeteringDeviceTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, MeteringDeviceTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, MeteringDeviceTypeEnum>();
        for (MeteringDeviceTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private MeteringDeviceTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static MeteringDeviceTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
