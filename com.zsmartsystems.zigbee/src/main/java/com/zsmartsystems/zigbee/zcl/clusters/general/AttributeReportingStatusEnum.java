/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.general;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Attribute Reporting Status Enum value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2022-05-28T20:55:53Z")
public enum AttributeReportingStatusEnum {

    /**
     * Pending, 0, 0x0000
     */
    PENDING(0x0000),

    /**
     * Attribute Reporting Complete, 1, 0x0001
     */
    ATTRIBUTE_REPORTING_COMPLETE(0x0001);

    /**
     * A mapping between the integer code and its corresponding AttributeReportingStatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, AttributeReportingStatusEnum> idMap;

    static {
        idMap = new HashMap<Integer, AttributeReportingStatusEnum>();
        for (AttributeReportingStatusEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private AttributeReportingStatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static AttributeReportingStatusEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
