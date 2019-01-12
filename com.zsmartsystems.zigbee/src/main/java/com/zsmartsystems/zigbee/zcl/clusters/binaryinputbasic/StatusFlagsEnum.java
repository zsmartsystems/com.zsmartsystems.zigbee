/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.binaryinputbasic;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Enumeration of Binary Input (Basic) attribute StatusFlags options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-05-26T18:19:06Z")
public enum StatusFlagsEnum {
    IN_ALARM(0x0001),
    FAULT(0x0002),
    OVERRIDDEN(0x0004),
    OUT_OF_SERVICE(0x0008);

    /**
     * A mapping between the integer code and its corresponding StatusFlagsEnum type to facilitate lookup by value.
     */
    private static Map<Integer, StatusFlagsEnum> idMap;

    static {
        idMap = new HashMap<Integer, StatusFlagsEnum>();
        for (StatusFlagsEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    StatusFlagsEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static StatusFlagsEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
