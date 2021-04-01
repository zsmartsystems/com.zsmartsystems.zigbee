/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.electricalmeasurement;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Measurement Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum MeasurementTypeEnum {

    /**
     * AC Active Measurement, 0, 0x0000
     */
    AC_ACTIVE_MEASUREMENT(0x0000),

    /**
     * AC Reactive Measurement, 1, 0x0001
     */
    AC_REACTIVE_MEASUREMENT(0x0001),

    /**
     * AC Apparent Measurement, 2, 0x0002
     */
    AC_APPARENT_MEASUREMENT(0x0002),

    /**
     * Phase A Measurement, 4, 0x0004
     */
    PHASE_A_MEASUREMENT(0x0004),

    /**
     * Phase B Measurement, 8, 0x0008
     */
    PHASE_B_MEASUREMENT(0x0008),

    /**
     * Phase C Measurement, 16, 0x0010
     */
    PHASE_C_MEASUREMENT(0x0010),

    /**
     * DC Measurement, 32, 0x0020
     */
    DC_MEASUREMENT(0x0020),

    /**
     * Harmonics Measurement, 64, 0x0040
     */
    HARMONICS_MEASUREMENT(0x0040),

    /**
     * Power Quality Measurement, 128, 0x0080
     */
    POWER_QUALITY_MEASUREMENT(0x0080);

    /**
     * A mapping between the integer code and its corresponding MeasurementTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, MeasurementTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, MeasurementTypeEnum>();
        for (MeasurementTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private MeasurementTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static MeasurementTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
