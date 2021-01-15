/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
 * Gp Sink Table Request Options value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum GpSinkTableRequestOptionsBitmap {

    /**
     * Application ID, 7, 0x0007
     */
    APPLICATION_ID(0x0007),

    /**
     * Request Type, 24, 0x0018
     */
    REQUEST_TYPE(0x0018);

    /**
     * A mapping between the integer code and its corresponding GpSinkTableRequestOptionsBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, GpSinkTableRequestOptionsBitmap> idMap;

    static {
        idMap = new HashMap<Integer, GpSinkTableRequestOptionsBitmap>();
        for (GpSinkTableRequestOptionsBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GpSinkTableRequestOptionsBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GpSinkTableRequestOptionsBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
