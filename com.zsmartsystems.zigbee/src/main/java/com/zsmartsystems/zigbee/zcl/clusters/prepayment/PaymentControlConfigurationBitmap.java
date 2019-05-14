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
 * Payment Control Configuration value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum PaymentControlConfigurationBitmap {

    /**
     * Disconnection Enabled
     */
    DISCONNECTION_ENABLED(0x0001),

    /**
     * Prepayment Enabled
     */
    PREPAYMENT_ENABLED(0x0002),

    /**
     * Credit Management Enabled
     */
    CREDIT_MANAGEMENT_ENABLED(0x0004),

    /**
     * Credit Display Enabled
     */
    CREDIT_DISPLAY_ENABLED(0x0010),

    /**
     * Account Base
     */
    ACCOUNT_BASE(0x0040),

    /**
     * Contactor Fitted
     */
    CONTACTOR_FITTED(0x0080),

    /**
     * Standing Charge Configuration
     */
    STANDING_CHARGE_CONFIGURATION(0x0100),

    /**
     * Emergency Standing Charge Configuration
     */
    EMERGENCY_STANDING_CHARGE_CONFIGURATION(0x0200),

    /**
     * Debt Configuration
     */
    DEBT_CONFIGURATION(0x0400),

    /**
     * Emergency Debt Configuration
     */
    EMERGENCY_DEBT_CONFIGURATION(0x0800);

    /**
     * A mapping between the integer code and its corresponding PaymentControlConfigurationBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, PaymentControlConfigurationBitmap> idMap;

    static {
        idMap = new HashMap<Integer, PaymentControlConfigurationBitmap>();
        for (PaymentControlConfigurationBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private PaymentControlConfigurationBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static PaymentControlConfigurationBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
