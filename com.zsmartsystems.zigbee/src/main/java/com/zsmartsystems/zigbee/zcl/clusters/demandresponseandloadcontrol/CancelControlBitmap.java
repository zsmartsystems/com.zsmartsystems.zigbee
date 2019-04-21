/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Cancel Control value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum CancelControlBitmap {

    /**
     * Terminate With Randomization
     */
    TERMINATE_WITH_RANDOMIZATION(0x0001);

    /**
     * A mapping between the integer code and its corresponding CancelControlBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, CancelControlBitmap> idMap;

    static {
        idMap = new HashMap<Integer, CancelControlBitmap>();
        for (CancelControlBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private CancelControlBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static CancelControlBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
