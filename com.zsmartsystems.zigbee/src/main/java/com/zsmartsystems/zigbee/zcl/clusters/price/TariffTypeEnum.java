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
 * Tariff Type value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum TariffTypeEnum {

    /**
     * Delivered Tariff
     */
    DELIVERED_TARIFF(0x0000),

    /**
     * Received Tariff
     */
    RECEIVED_TARIFF(0x0001),

    /**
     * Delivered And Received Tariff
     */
    DELIVERED_AND_RECEIVED_TARIFF(0x0002);

    /**
     * A mapping between the integer code and its corresponding TariffTypeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, TariffTypeEnum> idMap;

    static {
        idMap = new HashMap<Integer, TariffTypeEnum>();
        for (TariffTypeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private TariffTypeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static TariffTypeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
