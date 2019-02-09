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
 * Snapshot Cause value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum SnapshotCauseBitmap {

    /**
     * General
     */
    GENERAL(0x0001),

    /**
     * End Of Billing Period
     */
    END_OF_BILLING_PERIOD(0x0002),

    /**
     * End Of Block Period
     */
    END_OF_BLOCK_PERIOD(0x0004),

    /**
     * Change Of Tariff Information
     */
    CHANGE_OF_TARIFF_INFORMATION(0x0008),

    /**
     * Change Of Price Matrix
     */
    CHANGE_OF_PRICE_MATRIX(0x0010),

    /**
     * Change Of Block Thresholds
     */
    CHANGE_OF_BLOCK_THRESHOLDS(0x0020),

    /**
     * Change Of Cv
     */
    CHANGE_OF_CV(0x0040),

    /**
     * Change Of Cf
     */
    CHANGE_OF_CF(0x0080),

    /**
     * Change Of Calendar
     */
    CHANGE_OF_CALENDAR(0x0100),

    /**
     * Critical Peak Pricing
     */
    CRITICAL_PEAK_PRICING(0x0200),

    /**
     * Manually Triggered From Client
     */
    MANUALLY_TRIGGERED_FROM_CLIENT(0x0400),

    /**
     * End Of Resolve Period
     */
    END_OF_RESOLVE_PERIOD(0x0800),

    /**
     * Change Of Tenancy
     */
    CHANGE_OF_TENANCY(0x1000),

    /**
     * Change Of Supplier
     */
    CHANGE_OF_SUPPLIER(0x2000),

    /**
     * Change Of Mode
     */
    CHANGE_OF_MODE(0x4000),

    /**
     * Debt Payment
     */
    DEBT_PAYMENT(0x8000),

    /**
     * Scheduled Snapshot
     */
    SCHEDULED_SNAPSHOT(0x10000),

    /**
     * Ota Firmware Download
     */
    OTA_FIRMWARE_DOWNLOAD(0x20000);

    /**
     * A mapping between the integer code and its corresponding SnapshotCauseBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, SnapshotCauseBitmap> idMap;

    static {
        idMap = new HashMap<Integer, SnapshotCauseBitmap>();
        for (SnapshotCauseBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private SnapshotCauseBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static SnapshotCauseBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
