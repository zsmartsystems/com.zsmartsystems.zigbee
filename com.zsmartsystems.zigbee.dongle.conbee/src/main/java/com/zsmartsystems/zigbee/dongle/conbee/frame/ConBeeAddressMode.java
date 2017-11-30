/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Chris Jackson
 *
 */
public enum ConBeeAddressMode {
    UNKNOWN(0),
    GROUP(1),
    NWK(2),
    IEEE(3);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, ConBeeAddressMode> codeMapping;

    private int key;

    private ConBeeAddressMode(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, ConBeeAddressMode>();
        for (ConBeeAddressMode s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    /**
     * Lookup function based on the type code. Returns null if the
     * code does not exist.
     *
     * @param i
     *            the code to lookup
     * @return enumeration value of the alarm type.
     */
    public static ConBeeAddressMode getMode(int mode) {
        if (codeMapping == null) {
            initMapping();
        }

        if (codeMapping.get(mode) == null) {
            return UNKNOWN;
        }

        return codeMapping.get(mode);
    }

    /**
     * Returns the value for this enum
     *
     * @return the key
     */
    public int getKey() {
        return key;
    }
}
