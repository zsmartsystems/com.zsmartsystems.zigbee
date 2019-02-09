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
 * Tariff Type Charging Scheme value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum TariffTypeChargingSchemeBitmap {

    /**
     * Tariff Type
     */
    TARIFF_TYPE(0x000F),

    /**
     * Tariff Charging Scheme
     */
    TARIFF_CHARGING_SCHEME(0x00F0);

    /**
     * A mapping between the integer code and its corresponding TariffTypeChargingSchemeBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, TariffTypeChargingSchemeBitmap> idMap;

    static {
        idMap = new HashMap<Integer, TariffTypeChargingSchemeBitmap>();
        for (TariffTypeChargingSchemeBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private TariffTypeChargingSchemeBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static TariffTypeChargingSchemeBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
