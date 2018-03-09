/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
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
 * Enumeration of IAS Zone attribute ZoneType options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public enum ZoneTypeEnum {
    STANDARD_CIE(0x0000),
    MOTION_SENSOR(0x000D),
    CONTACT_SWITCH(0x0015),
    WATER_SENSOR(0x0028),
    CO_SENSOR(0x002B),
    PERSONAL_EMERGENCY_DEVICE(0x002C),
    VIBRATION_MOVEMENT_SENSOR(0x002D),
    REMOTE_CONTROL(0x010F),
    KEY_FOB(0x0115),
    KEY_PAD(0x021D),
    STANDARD_WARNING_DEVICE(0x0225),
    GLASS_BREAK_SENSOR(0x0226),
    SECURITY_REPEATER(0x0229);

    /**
     * A mapping between the integer code and its corresponding ZoneTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ZoneTypeEnum> idMap;

    private final int key;

    ZoneTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ZoneTypeEnum getByValue(final int value) {
        if (idMap == null) {
            idMap = new HashMap<Integer, ZoneTypeEnum>();
            for (ZoneTypeEnum enumValue : values()) {
                idMap.put(enumValue.key, enumValue);
            }
        }
        return idMap.get(value);
    }
}
