/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.basic;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Enumeration of Basic attribute PhysicalEnvironment options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public enum PhysicalEnvironmentEnum {
    UNKNOWN(0x0000),
    ATRIUM(0x0001),
    BAR(0x0002),
    COURTYARD(0x0003),
    BATHROOM(0x0004),
    EDROOM(0x0005);

    /**
     * A mapping between the integer code and its corresponding PhysicalEnvironmentEnum type to facilitate lookup by value.
     */
    private static Map<Integer, PhysicalEnvironmentEnum> idMap;

    private final int key;

    PhysicalEnvironmentEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static PhysicalEnvironmentEnum getByValue(final int value) {
        if (idMap == null) {
            idMap = new HashMap<Integer, PhysicalEnvironmentEnum>();
            for (PhysicalEnvironmentEnum enumValue : values()) {
                idMap.put(enumValue.key, enumValue);
            }
        }
        return idMap.get(value);
    }
}
