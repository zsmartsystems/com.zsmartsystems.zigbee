/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.multistateinputbasic;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Enumeration of Multistate Input (Basic) attribute Reliability options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public enum ReliabilityEnum {
    NO_FAULT_DETECTED(0x0000),
    OVER_RANGE(0x0002),
    UNDER_RANGE(0x0003),
    OPEN_LOOP(0x0004),
    SHORTED_LOOP(0x0005),
    UNRELIABLE_OTHER(0x0007),
    PROCESS_ERROR(0x0008),
    MULTI_STATE_FAULT(0x0009),
    CONFIGURATION_ERROR(0x000A);

    /**
     * A mapping between the integer code and its corresponding ReliabilityEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ReliabilityEnum> idMap;

    private final int key;

    ReliabilityEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ReliabilityEnum getByValue(final int value) {
        if (idMap == null) {
            idMap = new HashMap<Integer, ReliabilityEnum>();
            for (ReliabilityEnum enumValue : values()) {
                idMap.put(enumValue.key, enumValue);
            }
        }
        return idMap.get(value);
    }
}
