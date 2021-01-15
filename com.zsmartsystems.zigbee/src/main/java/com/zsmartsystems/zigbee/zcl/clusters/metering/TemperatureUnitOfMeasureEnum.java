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
 * Temperature Unit Of Measure value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum TemperatureUnitOfMeasureEnum {

    /**
     * Degrees Kelvin, 0, 0x0000
     */
    DEGREES_KELVIN(0x0000),

    /**
     * Degrees Celsius, 1, 0x0001
     */
    DEGREES_CELSIUS(0x0001),

    /**
     * Degrees Fahrenheit, 2, 0x0002
     */
    DEGREES_FAHRENHEIT(0x0002),

    /**
     * Degrees Kelvin BCD, 128, 0x0080
     */
    DEGREES_KELVIN_BCD(0x0080),

    /**
     * Degrees Celsius BCD, 129, 0x0081
     */
    DEGREES_CELSIUS_BCD(0x0081),

    /**
     * Degrees Fahrenheit BCD, 130, 0x0082
     */
    DEGREES_FAHRENHEIT_BCD(0x0082);

    /**
     * A mapping between the integer code and its corresponding TemperatureUnitOfMeasureEnum type to facilitate lookup by value.
     */
    private static Map<Integer, TemperatureUnitOfMeasureEnum> idMap;

    static {
        idMap = new HashMap<Integer, TemperatureUnitOfMeasureEnum>();
        for (TemperatureUnitOfMeasureEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private TemperatureUnitOfMeasureEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static TemperatureUnitOfMeasureEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
