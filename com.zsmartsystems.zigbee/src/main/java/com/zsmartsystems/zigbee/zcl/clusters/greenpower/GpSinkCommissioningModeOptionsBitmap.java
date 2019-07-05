/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Gp Sink Commissioning Mode Options value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-07-04T21:54:11Z")
public enum GpSinkCommissioningModeOptionsBitmap {

    /**
     * Action
     */
    ACTION(0x0001),

    /**
     * Involve Gpm In Security
     */
    INVOLVE_GPM_IN_SECURITY(0x0002),

    /**
     * Involve Gpm In Pairing
     */
    INVOLVE_GPM_IN_PAIRING(0x0004),

    /**
     * Involve Proxies
     */
    INVOLVE_PROXIES(0x0008);

    /**
     * A mapping between the integer code and its corresponding GpSinkCommissioningModeOptionsBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, GpSinkCommissioningModeOptionsBitmap> idMap;

    static {
        idMap = new HashMap<Integer, GpSinkCommissioningModeOptionsBitmap>();
        for (GpSinkCommissioningModeOptionsBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GpSinkCommissioningModeOptionsBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GpSinkCommissioningModeOptionsBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
