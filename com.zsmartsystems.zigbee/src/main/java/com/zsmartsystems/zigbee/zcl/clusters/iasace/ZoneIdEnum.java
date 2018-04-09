/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Enumeration of IAS ACE attribute Zone ID options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-04-09T22:43:58Z")
public enum ZoneIdEnum {
    ZONE_BYPASSED(0x0000),
    ZONE_NOT_BYPASSED(0x0001),
    NOT_ALLOWED(0x0002),
    INVALID_ZONE_ID(0x0003),
    UNKNOWN_ZONE_ID(0x0004),
    INVALID_ARM_CODE(0x0005);

    /**
     * A mapping between the integer code and its corresponding ZoneIdEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ZoneIdEnum> idMap;

    static {
        idMap = new HashMap<Integer, ZoneIdEnum>();
        for (ZoneIdEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    ZoneIdEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ZoneIdEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
