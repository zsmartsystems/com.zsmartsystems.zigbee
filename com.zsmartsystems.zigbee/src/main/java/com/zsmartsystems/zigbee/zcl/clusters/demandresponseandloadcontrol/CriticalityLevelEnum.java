/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Criticality Level value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum CriticalityLevelEnum {

    /**
     * Green
     */
    GREEN(0x0001),

    /**
     * Level 1
     */
    LEVEL_1(0x0002),

    /**
     * Level 2
     */
    LEVEL_2(0x0003),

    /**
     * Level 3
     */
    LEVEL_3(0x0004),

    /**
     * Level 4
     */
    LEVEL_4(0x0005),

    /**
     * Level 5
     */
    LEVEL_5(0x0006),

    /**
     * Emergency
     */
    EMERGENCY(0x0007),

    /**
     * Planned Outage
     */
    PLANNED_OUTAGE(0x0008),

    /**
     * Service Disconnect
     */
    SERVICE_DISCONNECT(0x0009),

    /**
     * Utility Defined 1
     */
    UTILITY_DEFINED_1(0x000A),

    /**
     * Utility Defined 2
     */
    UTILITY_DEFINED_2(0x000B),

    /**
     * Utility Defined 3
     */
    UTILITY_DEFINED_3(0x000C),

    /**
     * Utility Defined 4
     */
    UTILITY_DEFINED_4(0x000D),

    /**
     * Utility Defined 5
     */
    UTILITY_DEFINED_5(0x000E),

    /**
     * Utility Defined 6
     */
    UTILITY_DEFINED_6(0x000F);

    /**
     * A mapping between the integer code and its corresponding CriticalityLevelEnum type to facilitate lookup by value.
     */
    private static Map<Integer, CriticalityLevelEnum> idMap;

    static {
        idMap = new HashMap<Integer, CriticalityLevelEnum>();
        for (CriticalityLevelEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private CriticalityLevelEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static CriticalityLevelEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
