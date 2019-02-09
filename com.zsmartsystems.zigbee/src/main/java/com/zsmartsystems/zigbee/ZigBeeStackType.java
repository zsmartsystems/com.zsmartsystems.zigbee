/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 * ZigBee Stack Type value enumeration.
 * <p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum ZigBeeStackType {

    /**
     * ZigBee 2006
     */
    ZIGBEE_2006(0x0000),

    /**
     * ZigBee 2007
     */
    ZIGBEE_2007(0x0001),

    /**
     * ZigBee Pro
     */
    ZIGBEE_PRO(0x0002),

    /**
     * ZigBee IP
     */
    ZIGBEE_IP(0x0003);

    /**
     * A mapping between the integer code and its corresponding ZigBeeStackType type to facilitate lookup by value.
     */
    private static Map<Integer, ZigBeeStackType> idMap;

    static {
        idMap = new HashMap<Integer, ZigBeeStackType>();
        for (ZigBeeStackType enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private ZigBeeStackType(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ZigBeeStackType getByValue(final int value) {
        return idMap.get(value);
    }
}
