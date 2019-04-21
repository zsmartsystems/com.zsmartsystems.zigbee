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
 * Originating Device value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum OriginatingDeviceEnum {

    /**
     * Energy Service Interface
     */
    ENERGY_SERVICE_INTERFACE(0x0000),

    /**
     * Meter
     */
    METER(0x0001),

    /**
     * In Home Display Device
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
