/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
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
 * Gp Application Information value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum GpApplicationInformationBitmap {

    /**
     * Manufacture ID Present, 1, 0x0001
     */
    MANUFACTURE_ID_PRESENT(0x0001),

    /**
     * Model ID Present, 2, 0x0002
     */
    MODEL_ID_PRESENT(0x0002),

    /**
     * Gpd Commands Present, 4, 0x0004
     */
    GPD_COMMANDS_PRESENT(0x0004),

    /**
     * Cluster List Present, 8, 0x0008
     */
    CLUSTER_LIST_PRESENT(0x0008);

    /**
     * A mapping between the integer code and its corresponding GpApplicationInformationBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, GpApplicationInformationBitmap> idMap;

    static {
        idMap = new HashMap<Integer, GpApplicationInformationBitmap>();
        for (GpApplicationInformationBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GpApplicationInformationBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GpApplicationInformationBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
