/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.electricalmeasurement;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration of Electrical Measurement attribute MeasurementType options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
public enum MeasurementTypeEnum {
    AC_ACTIVE_MEASUREMENT(0x0000),
    AC_REACTIVE_MEASUREMENT(0x0001),
    AC_APPARENT_MEASUREMENT(0x0002),
    PHASE_A_MEASUREMENT(0x0004),
    PHASE_B_MEASUREMENT(0x0008),
    PHASE_C_MEASUREMENT(0x0010),
    DC_MEASUREMENT(0x0020),
    HARMONICS_MEASUREMENT(0x0040),
    POWER_QUALITY_MEASUREMENT(0x0080);

    /**
     * A mapping between the integer code and its corresponding MeasurementTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, MeasurementTypeEnum> idMap;

    private final int key;

    MeasurementTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static MeasurementTypeEnum getByValue(final int value) {
        if (idMap == null) {
            idMap = new HashMap<Integer, MeasurementTypeEnum>();
            for (MeasurementTypeEnum enumValue : values()) {
                idMap.put(enumValue.key, enumValue);
            }
        }
        return idMap.get(value);
    }
}
