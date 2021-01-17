/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum ZigBeeProfileType {

    /**
     * ZigBee Home Automation, 260, 0x0104
     */
    ZIGBEE_HOME_AUTOMATION(0x0104),

    /**
     * ZigBee Smart Energy, 265, 0x0109
     */
    ZIGBEE_SMART_ENERGY(0x0109),

    /**
     * ZigBee Green Power, 41230, 0xA10E
     */
    ZIGBEE_GREEN_POWER(0xA10E),

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
