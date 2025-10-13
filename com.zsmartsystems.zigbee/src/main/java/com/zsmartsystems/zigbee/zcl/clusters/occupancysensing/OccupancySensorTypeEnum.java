/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.occupancysensing;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Occupancy Sensor Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-10-12T13:08:25Z")
public enum OccupancySensorTypeEnum {

    /**
     * PIR, 0, 0x0000
     */
    PIR(0x0000),

    /**
     * Ultrasonic, 1, 0x0001
     */
    ULTRASONIC(0x0001),

    /**
     * PIR and Ultrasonic, 2, 0x0002
     */
    PIR_AND_ULTRASONIC(0x0002),

    /**
     * Physical Contact, 3, 0x0003
     */
    PHYSICAL_CONTACT(0x0003);

    /**
     * A mapping between the integer code and its corresponding OccupancySensorTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, OccupancySensorTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, OccupancySensorTypeEnum>();
        for (OccupancySensorTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private OccupancySensorTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static OccupancySensorTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
