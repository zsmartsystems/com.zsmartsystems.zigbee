/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.price;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Payment Discount Period value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum PaymentDiscountPeriodEnum {

    /**
     * Current Billing Period, 0, 0x0000
     */
    CURRENT_BILLING_PERIOD(0x0000),

    /**
     * Current Consolidated Bill, 1, 0x0001
     */
    CURRENT_CONSOLIDATED_BILL(0x0001),

    /**
     * One Month, 2, 0x0002
     */
    ONE_MONTH(0x0002),

    /**
     * One Quarter, 3, 0x0003
     */
    ONE_QUARTER(0x0003),

    /**
     * One Year, 4, 0x0004
     */
    ONE_YEAR(0x0004);

    /**
     * A mapping between the integer code and its corresponding PaymentDiscountPeriodEnum type to facilitate lookup by value.
     */
    private static Map<Integer, PaymentDiscountPeriodEnum> idMap;

    static {
        idMap = new HashMap<Integer, PaymentDiscountPeriodEnum>();
        for (PaymentDiscountPeriodEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private PaymentDiscountPeriodEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static PaymentDiscountPeriodEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
