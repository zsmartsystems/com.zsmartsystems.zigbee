/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum UnitOfMeasureEnum {

    /**
     * Kilo Watt Hours
     */
    KILO_WATT_HOURS(0x0000),

    /**
     * Cubic Meter Per Hour
     */
    CUBIC_METER_PER_HOUR(0x0001),

    /**
     * Cubic Feet Per Hour
     */
    CUBIC_FEET_PER_HOUR(0x0002),

    /**
     * Centum Cubic Feet Per Hour
     */
    CENTUM_CUBIC_FEET_PER_HOUR(0x0003),

    /**
     * Us Gallons Per Hour
     */
    US_GALLONS_PER_HOUR(0x0004),

    /**
     * Imperial Gallons Per Hour
     */
    IMPERIAL_GALLONS_PER_HOUR(0x0005),

    /**
     * Bt Us Or Btu Per Hour
     */
    BT_US_OR_BTU_PER_HOUR(0x0006),

    /**
     * Liters Or Liters Per Hour
     */
    LITERS_OR_LITERS_PER_HOUR(0x0007),

    /**
     * Kpa Gauge
     */
    KPA_GAUGE(0x0008),

    /**
     * Kpa Absolute
     */
    KPA_ABSOLUTE(0x0009),

    /**
     * Mcf Or Mcf Per Second
     */
    MCF_OR_MCF_PER_SECOND(0x000A),

    /**
     * Unitless
     */
    UNITLESS(0x000B),

    /**
     * Mj Or Mj Per Second
     */
    MJ_OR_MJ_PER_SECOND(0x000C),

    /**
     * K Var Or K Var Hours
     */
    K_VAR_OR_K_VAR_HOURS(0x000D),

    /**
     * Kilo Watt Hours Bcd
     */
    KILO_WATT_HOURS_BCD(0x0080),

    /**
     * Cubic Meter Per Hour Bcd
     */
    CUBIC_METER_PER_HOUR_BCD(0x0081),

    /**
     * Cubic Feet Per Hour Bcd
     */
    CUBIC_FEET_PER_HOUR_BCD(0x0082),

    /**
     * Centum Cubic Feet Per Hour Bcd
     */
    CENTUM_CUBIC_FEET_PER_HOUR_BCD(0x0083),

    /**
     * Us Gallons Per Hour Bcd
     */
    US_GALLONS_PER_HOUR_BCD(0x0084),

    /**
     * Imperial Gallons Per Hour Bcd
     */
    IMPERIAL_GALLONS_PER_HOUR_BCD(0x0085),

    /**
     * Bt Us Or Btu Per Hour Bcd
     */
    BT_US_OR_BTU_PER_HOUR_BCD(0x0086),

    /**
     * Liters Or Liters Per Hour Bcd
     */
    LITERS_OR_LITERS_PER_HOUR_BCD(0x0087),

    /**
     * Kpa Guage Bcd
     */
    KPA_GUAGE_BCD(0x0088),

    /**
     * Kpa Absolute Bcd
     */
    KPA_ABSOLUTE_BCD(0x0089),

    /**
     * Mcf Or Mcf Per Second Bcd
     */
    MCF_OR_MCF_PER_SECOND_BCD(0x008A),

    /**
     * Unitless Bcd
     */
    UNITLESS_BCD(0x008B),

    /**
     * Mj Or Mj Per Second Bcd
     */
    MJ_OR_MJ_PER_SECOND_BCD(0x008C),

    /**
     * K Var Or K Var Hours Bcd
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
