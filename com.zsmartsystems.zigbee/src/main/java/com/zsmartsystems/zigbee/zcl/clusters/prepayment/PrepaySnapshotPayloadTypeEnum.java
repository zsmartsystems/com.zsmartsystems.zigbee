/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.prepayment;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Prepay Snapshot Payload Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum PrepaySnapshotPayloadTypeEnum {

    /**
     * Debt Credit Status, 0, 0x0000
     */
    DEBT_CREDIT_STATUS(0x0000),

    /**
     * Not Used, 255, 0x00FF
     */
    NOT_USED(0x00FF);

    /**
     * A mapping between the integer code and its corresponding PrepaySnapshotPayloadTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, PrepaySnapshotPayloadTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, PrepaySnapshotPayloadTypeEnum>();
        for (PrepaySnapshotPayloadTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private PrepaySnapshotPayloadTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static PrepaySnapshotPayloadTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
