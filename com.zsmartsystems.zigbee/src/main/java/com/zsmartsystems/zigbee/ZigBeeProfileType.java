/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * ZigBee Profile Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2022-05-08T20:48:02Z")
public enum ZigBeeProfileType {

    /**
     * ZigBee Device Profile, 0, 0x0000
     */
    ZIGBEE_DEVICE_PROFILE(0x0000),

    /**
     * Industrial Plant Monitoring, 257, 0x0101
     */
    INDUSTRIAL_PLANT_MONITORING(0x0101),

    /**
     * ZigBee Home Automation, 260, 0x0104
     */
    ZIGBEE_HOME_AUTOMATION(0x0104),

    /**
     * ZigBee Commercial Building Automation, 261, 0x0105
     */
    ZIGBEE_COMMERCIAL_BUILDING_AUTOMATION(0x0105),

    /**
     * ZigBee Wireless Sensor Networks, 262, 0x0106
     */
    ZIGBEE_WIRELESS_SENSOR_NETWORKS(0x0106),

    /**
     * ZigBee Smart Energy, 265, 0x0109
     */
    ZIGBEE_SMART_ENERGY(0x0109),

    /**
     * ZigBee Green Power, 41440, 0xA1E0
     */
    ZIGBEE_GREEN_POWER(0xA1E0),

    /**
     * ZigBee Light Link, 49246, 0xC05E
     */
    ZIGBEE_LIGHT_LINK(0xC05E),

    /**
     * Manufacturer Telegesis, 49241, 0xC059
     */
    MANUFACTURER_TELEGESIS(0xC059),

    /**
     * Manufacturer Digi, 49413, 0xC105
     */
    MANUFACTURER_DIGI(0xC105);

    /**
     * A mapping between the integer code and its corresponding ZigBeeProfileType type to facilitate lookup by value.
     */
    private static Map<Integer, ZigBeeProfileType> idMap;

    static {
        idMap = new HashMap<Integer, ZigBeeProfileType>();
        for (ZigBeeProfileType enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private ZigBeeProfileType(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ZigBeeProfileType getByValue(final int value) {
        return idMap.get(value);
    }
}
