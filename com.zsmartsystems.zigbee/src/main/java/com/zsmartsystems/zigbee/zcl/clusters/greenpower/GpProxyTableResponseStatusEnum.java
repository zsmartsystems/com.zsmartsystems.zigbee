/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
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
 * Gp Proxy Table Response Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum GpProxyTableResponseStatusEnum {

    /**
     * Success, 0, 0x0000
     */
    SUCCESS(0x0000),

    /**
     * Not_Found, 139, 0x008B
     */
    NOT_FOUND(0x008B);

    /**
     * A mapping between the integer code and its corresponding GpProxyTableResponseStatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, GpProxyTableResponseStatusEnum> idMap;

    static {
        idMap = new HashMap<Integer, GpProxyTableResponseStatusEnum>();
        for (GpProxyTableResponseStatusEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GpProxyTableResponseStatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GpProxyTableResponseStatusEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
