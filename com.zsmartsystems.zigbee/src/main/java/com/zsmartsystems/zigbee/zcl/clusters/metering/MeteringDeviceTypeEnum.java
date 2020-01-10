/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-10-26T17:06:24Z")
public enum MeteringDeviceTypeEnum {

    /**
     * Electric Metering
     */
    ELECTRIC_METERING(0x0000),

    /**
     * Gas Metering
     */
    GAS_METERING(0x0001),

    /**
     * Water Metering
     */
    WATER_METERING(0x0002),

    /**
     * Thermal Metering
     */
    THERMAL_METERING(0x0003),

    /**
     * Pressure Metering
     */
    PRESSURE_METERING(0x0004),

    /**
     * Heat Metering
     */
    HEAT_METERING(0x0005),

    /**
     * Cooling Metering
     */
    COOLING_METERING(0x0006),

    /**
     * Mirrored Gas Metering
     */
    MIRRORED_GAS_METERING(0x0080),

    /**
     * Mirrored Water Metering
     */
    MIRRORED_WATER_METERING(0x0081),

    /**
     * Mirrored Thermal Metering
     */
    MIRRORED_THERMAL_METERING(0x0082),

    /**
     * Mirrored Pressure Metering
     */
    MIRRORED_PRESSURE_METERING(0x0083),

    /**
     * Mirrored Heat Metering
     */
    MIRRORED_HEAT_METERING(0x0084),

    /**
     * Mirrored Cooling Metering
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
