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
 * Get Profile Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum GetProfileStatusEnum {

    /**
     * Success
     */
    SUCCESS(0x0000),

    /**
     * Undefined Interval Channel Requested
     */
    UNDEFINED_INTERVAL_CHANNEL_REQUESTED(0x0001),

    /**
     * Interval Channel Not Supported
     */
    INTERVAL_CHANNEL_NOT_SUPPORTED(0x0002),

    /**
     * Invalid End Time
     */
    INVALID_END_TIME(0x0003),

    /**
     * More Periods Requested Than Can Be Returned
     */
    MORE_PERIODS_REQUESTED_THAN_CAN_BE_RETURNED(0x0004),

    /**
     * No Intervals Available For The Requested Time
     */
    NO_INTERVALS_AVAILABLE_FOR_THE_REQUESTED_TIME(0x0005);

    /**
     * A mapping between the integer code and its corresponding GetProfileStatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, GetProfileStatusEnum> idMap;

    static {
        idMap = new HashMap<Integer, GetProfileStatusEnum>();
        for (GetProfileStatusEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GetProfileStatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GetProfileStatusEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
