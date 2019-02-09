/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 * Credit Payment Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum CreditPaymentStatusEnum {

    /**
     * Pending
     */
    PENDING(0x0000),

    /**
     * Received/Paid
     */
    RECEIVED_PAID(0x0001),

    /**
     * Overdue
     */
    OVERDUE(0x0002),

    /**
     * Two Payments Overdue
     */
    TWO_PAYMENTS_OVERDUE(0x0003),

    /**
     * Three Payments Overdue
     */
    THREE_PAYMENTS_OVERDUE(0x0004);

    /**
     * A mapping between the integer code and its corresponding CreditPaymentStatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, CreditPaymentStatusEnum> idMap;

    static {
        idMap = new HashMap<Integer, CreditPaymentStatusEnum>();
        for (CreditPaymentStatusEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private CreditPaymentStatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static CreditPaymentStatusEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
