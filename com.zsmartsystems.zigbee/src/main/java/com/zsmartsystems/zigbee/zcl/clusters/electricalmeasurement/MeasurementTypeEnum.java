/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum MeasurementTypeEnum {

    /**
     * AC Active Measurement
     */
    AC_ACTIVE_MEASUREMENT(0x0000),

    /**
     * AC Reactive Measurement
     */
    AC_REACTIVE_MEASUREMENT(0x0001),

    /**
     * AC Apparent Measurement
     */
    AC_APPARENT_MEASUREMENT(0x0002),

    /**
     * Phase A Measurement
     */
    PHASE_A_MEASUREMENT(0x0004),

    /**
     * Phase B Measurement
     */
    PHASE_B_MEASUREMENT(0x0008),

    /**
     * Phase C Measurement
     */
    PHASE_C_MEASUREMENT(0x0010),

    /**
     * DC Measurement
     */
    DC_MEASUREMENT(0x0020),

    /**
     * Harmonics Measurement
     */
    HARMONICS_MEASUREMENT(0x0040),

    /**
     * Power Quality Measurement
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
