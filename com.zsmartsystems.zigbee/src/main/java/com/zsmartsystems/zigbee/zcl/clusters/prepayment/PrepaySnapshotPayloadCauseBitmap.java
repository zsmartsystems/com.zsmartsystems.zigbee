/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 * Prepay Snapshot Payload Cause value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum PrepaySnapshotPayloadCauseBitmap {

    /**
     * General
     */
    GENERAL(0x0001),

    /**
     * Change Of Tariff Information
     */
    CHANGE_OF_TARIFF_INFORMATION(0x0008),

    /**
     * Change Of Price Matrix
     */
    CHANGE_OF_PRICE_MATRIX(0x0010),

    /**
     * Manually Triggered From Client
     */
    MANUALLY_TRIGGERED_FROM_CLIENT(0x0400),

    /**
     * Change Of Tenancy
     */
    CHANGE_OF_TENANCY(0x1000),

    /**
     * Change Of Supplier
     */
    CHANGE_OF_SUPPLIER(0x2000),

    /**
     * Change Of Meter Mode
     */
    CHANGE_OF_METER_MODE(0x4000),

    /**
     * Top Up Addition
     */
    TOP_UP_ADDITION(0x40000),

    /**
     * Debt Credit Addition
     */
    DEBT_CREDIT_ADDITION(0x80000);

    /**
     * A mapping between the integer code and its corresponding PrepaySnapshotPayloadCauseBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, PrepaySnapshotPayloadCauseBitmap> idMap;

    static {
        idMap = new HashMap<Integer, PrepaySnapshotPayloadCauseBitmap>();
        for (PrepaySnapshotPayloadCauseBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private PrepaySnapshotPayloadCauseBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static PrepaySnapshotPayloadCauseBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
