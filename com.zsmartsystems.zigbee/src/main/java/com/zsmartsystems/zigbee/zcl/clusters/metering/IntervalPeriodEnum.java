/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Interval Period value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum IntervalPeriodEnum {

    /**
     * Daily
     */
    DAILY(0x0000),

    /**
     * Minutes 60
     */
    MINUTES_60(0x0001),

    /**
     * Minutes 30
     */
    MINUTES_30(0x0002),

    /**
     * Minutes 15
     */
    MINUTES_15(0x0003),

    /**
     * Minutes 10
     */
    MINUTES_10(0x0004),

    /**
     * Minutes 7p 5
     */
    MINUTES_7P_5(0x0005),

    /**
     * Minutes 5
     */
    MINUTES_5(0x0006),

    /**
     * Minutes 2p 5
     */
    MINUTES_2P_5(0x0007);

    /**
     * A mapping between the integer code and its corresponding IntervalPeriodEnum type to facilitate lookup by value.
     */
    private static Map<Integer, IntervalPeriodEnum> idMap;

    static {
        idMap = new HashMap<Integer, IntervalPeriodEnum>();
        for (IntervalPeriodEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private IntervalPeriodEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static IntervalPeriodEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
