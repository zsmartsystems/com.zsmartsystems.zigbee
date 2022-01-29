/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
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
 * Originating Device value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum OriginatingDeviceEnum {

    /**
     * Energy Service Interface, 0, 0x0000
     */
    ENERGY_SERVICE_INTERFACE(0x0000),

    /**
     * Meter, 1, 0x0001
     */
    METER(0x0001),

    /**
     * In Home Display Device, 2, 0x0002
     */
    IN_HOME_DISPLAY_DEVICE(0x0002);

    /**
     * A mapping between the integer code and its corresponding OriginatingDeviceEnum type to facilitate lookup by value.
     */
    private static Map<Integer, OriginatingDeviceEnum> idMap;

    static {
        idMap = new HashMap<Integer, OriginatingDeviceEnum>();
        for (OriginatingDeviceEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private OriginatingDeviceEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static OriginatingDeviceEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
