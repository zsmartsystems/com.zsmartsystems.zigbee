/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
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
 * Unit Of Measure value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum UnitOfMeasureEnum {

    /**
     * Kilo Watt Hours, 0, 0x0000
     */
    KILO_WATT_HOURS(0x0000),

    /**
     * Cubic Meter Per Hour, 1, 0x0001
     */
    CUBIC_METER_PER_HOUR(0x0001),

    /**
     * Cubic Feet Per Hour, 2, 0x0002
     */
    CUBIC_FEET_PER_HOUR(0x0002),

    /**
     * Centum Cubic Feet Per Hour, 3, 0x0003
     */
    CENTUM_CUBIC_FEET_PER_HOUR(0x0003),

    /**
     * Us Gallons Per Hour, 4, 0x0004
     */
    US_GALLONS_PER_HOUR(0x0004),

    /**
     * Imperial Gallons Per Hour, 5, 0x0005
     */
    IMPERIAL_GALLONS_PER_HOUR(0x0005),

    /**
     * Bt Us Or Btu Per Hour, 6, 0x0006
     */
    BT_US_OR_BTU_PER_HOUR(0x0006),

    /**
     * Liters Or Liters Per Hour, 7, 0x0007
     */
    LITERS_OR_LITERS_PER_HOUR(0x0007),

    /**
     * Kpa Gauge, 8, 0x0008
     */
    KPA_GAUGE(0x0008),

    /**
     * Kpa Absolute, 9, 0x0009
     */
    KPA_ABSOLUTE(0x0009),

    /**
     * Mcf Or Mcf Per Second, 10, 0x000A
     */
    MCF_OR_MCF_PER_SECOND(0x000A),

    /**
     * Unitless, 11, 0x000B
     */
    UNITLESS(0x000B),

    /**
     * Mj Or Mj Per Second, 12, 0x000C
     */
    MJ_OR_MJ_PER_SECOND(0x000C),

    /**
     * K Var Or K Var Hours, 13, 0x000D
     */
    K_VAR_OR_K_VAR_HOURS(0x000D),

    /**
     * Kilo Watt Hours Bcd, 128, 0x0080
     */
    KILO_WATT_HOURS_BCD(0x0080),

    /**
     * Cubic Meter Per Hour Bcd, 129, 0x0081
     */
    CUBIC_METER_PER_HOUR_BCD(0x0081),

    /**
     * Cubic Feet Per Hour Bcd, 130, 0x0082
     */
    CUBIC_FEET_PER_HOUR_BCD(0x0082),

    /**
     * Centum Cubic Feet Per Hour Bcd, 131, 0x0083
     */
    CENTUM_CUBIC_FEET_PER_HOUR_BCD(0x0083),

    /**
     * Us Gallons Per Hour Bcd, 132, 0x0084
     */
    US_GALLONS_PER_HOUR_BCD(0x0084),

    /**
     * Imperial Gallons Per Hour Bcd, 133, 0x0085
     */
    IMPERIAL_GALLONS_PER_HOUR_BCD(0x0085),

    /**
     * Bt Us Or Btu Per Hour Bcd, 134, 0x0086
     */
    BT_US_OR_BTU_PER_HOUR_BCD(0x0086),

    /**
     * Liters Or Liters Per Hour Bcd, 135, 0x0087
     */
    LITERS_OR_LITERS_PER_HOUR_BCD(0x0087),

    /**
     * Kpa Guage Bcd, 136, 0x0088
     */
    KPA_GUAGE_BCD(0x0088),

    /**
     * Kpa Absolute Bcd, 137, 0x0089
     */
    KPA_ABSOLUTE_BCD(0x0089),

    /**
     * Mcf Or Mcf Per Second Bcd, 138, 0x008A
     */
    MCF_OR_MCF_PER_SECOND_BCD(0x008A),

    /**
     * Unitless Bcd, 139, 0x008B
     */
    UNITLESS_BCD(0x008B),

    /**
     * Mj Or Mj Per Second Bcd, 140, 0x008C
     */
    MJ_OR_MJ_PER_SECOND_BCD(0x008C),

    /**
     * K Var Or K Var Hours Bcd, 141, 0x008D
     */
    K_VAR_OR_K_VAR_HOURS_BCD(0x008D);

    /**
     * A mapping between the integer code and its corresponding UnitOfMeasureEnum type to facilitate lookup by value.
     */
    private static Map<Integer, UnitOfMeasureEnum> idMap;

    static {
        idMap = new HashMap<Integer, UnitOfMeasureEnum>();
        for (UnitOfMeasureEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private UnitOfMeasureEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static UnitOfMeasureEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
