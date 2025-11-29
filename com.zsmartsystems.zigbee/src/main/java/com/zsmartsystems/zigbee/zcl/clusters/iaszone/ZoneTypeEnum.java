/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iaszone;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Zone Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum ZoneTypeEnum {

    /**
     * Standard CIE, 0, 0x0000
     */
    STANDARD_CIE(0x0000),

    /**
     * Motion Sensor, 13, 0x000D
     */
    MOTION_SENSOR(0x000D),

    /**
     * Contact Switch, 21, 0x0015
     */
    CONTACT_SWITCH(0x0015),

    /**
     * Fire Sensor, 40, 0x0028
     */
    FIRE_SENSOR(0x0028),

    /**
     * Water Sensor, 42, 0x002A
     */
    WATER_SENSOR(0x002A),

    /**
     * CO Sensor, 43, 0x002B
     */
    CO_SENSOR(0x002B),

    /**
     * Personal Emergency Device, 44, 0x002C
     */
    PERSONAL_EMERGENCY_DEVICE(0x002C),

    /**
     * Vibration Movement Sensor, 45, 0x002D
     */
    VIBRATION_MOVEMENT_SENSOR(0x002D),

    /**
     * Remote Control, 271, 0x010F
     */
    REMOTE_CONTROL(0x010F),

    /**
     * Key Fob, 277, 0x0115
     */
    KEY_FOB(0x0115),

    /**
     * Key Pad, 541, 0x021D
     */
    KEY_PAD(0x021D),

    /**
     * Standard Warning Device, 549, 0x0225
     */
    STANDARD_WARNING_DEVICE(0x0225),

    /**
     * Glass Break Sensor, 550, 0x0226
     */
    GLASS_BREAK_SENSOR(0x0226),

    /**
     * Security Repeater, 553, 0x0229
     */
    SECURITY_REPEATER(0x0229);

    /**
     * A mapping between the integer code and its corresponding ZoneTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ZoneTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, ZoneTypeEnum>();
        for (ZoneTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private ZoneTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ZoneTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
