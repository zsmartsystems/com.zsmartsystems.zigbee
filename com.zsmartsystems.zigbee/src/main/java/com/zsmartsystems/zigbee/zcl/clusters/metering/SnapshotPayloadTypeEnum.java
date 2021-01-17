/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
 * Snapshot Payload Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum SnapshotPayloadTypeEnum {

    /**
     * Tou Information Set Delivered Registers, 0, 0x0000
     */
    TOU_INFORMATION_SET_DELIVERED_REGISTERS(0x0000),

    /**
     * Tou Information Set Received Registers, 1, 0x0001
     */
    TOU_INFORMATION_SET_RECEIVED_REGISTERS(0x0001),

    /**
     * Block Tier Information Set Delivered, 2, 0x0002
     */
    BLOCK_TIER_INFORMATION_SET_DELIVERED(0x0002),

    /**
     * Block Tier Information Set Received, 3, 0x0003
     */
    BLOCK_TIER_INFORMATION_SET_RECEIVED(0x0003),

    /**
     * Tou Information Set Delivered Registers No Billing, 4, 0x0004
     */
    TOU_INFORMATION_SET_DELIVERED_REGISTERS_NO_BILLING(0x0004),

    /**
     * Tou Information Set Received Register No Billings, 5, 0x0005
     */
    TOU_INFORMATION_SET_RECEIVED_REGISTER_NO_BILLINGS(0x0005),

    /**
     * Block Tier Information Set Delivered No Billing, 6, 0x0006
     */
    BLOCK_TIER_INFORMATION_SET_DELIVERED_NO_BILLING(0x0006),

    /**
     * Block Tier Information Set Received No Billing, 7, 0x0007
     */
    BLOCK_TIER_INFORMATION_SET_RECEIVED_NO_BILLING(0x0007),

    /**
     * Data Unavailable, 128, 0x0080
     */
    DATA_UNAVAILABLE(0x0080);

    /**
     * A mapping between the integer code and its corresponding SnapshotPayloadTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, SnapshotPayloadTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, SnapshotPayloadTypeEnum>();
        for (SnapshotPayloadTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private SnapshotPayloadTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static SnapshotPayloadTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
