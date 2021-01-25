/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
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
 * Debt Recovery Frequency value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum DebtRecoveryFrequencyEnum {

    /**
     * Per Hour, 0, 0x0000
     */
    PER_HOUR(0x0000),

    /**
     * Per Day, 1, 0x0001
     */
    PER_DAY(0x0001),

    /**
     * Per Week, 2, 0x0002
     */
    PER_WEEK(0x0002),

    /**
     * Per Month, 3, 0x0003
     */
    PER_MONTH(0x0003),

    /**
     * Per Quarter, 4, 0x0004
     */
    PER_QUARTER(0x0004);

    /**
     * A mapping between the integer code and its corresponding DebtRecoveryFrequencyEnum type to facilitate lookup by value.
     */
    private static Map<Integer, DebtRecoveryFrequencyEnum> idMap;

    static {
        idMap = new HashMap<Integer, DebtRecoveryFrequencyEnum>();
        for (DebtRecoveryFrequencyEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private DebtRecoveryFrequencyEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static DebtRecoveryFrequencyEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
