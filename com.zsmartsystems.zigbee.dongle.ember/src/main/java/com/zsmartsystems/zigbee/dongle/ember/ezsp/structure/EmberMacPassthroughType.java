/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the Ember Enumeration <b>EmberMacPassthroughType</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public enum EmberMacPassthroughType {
    /**
     * Default unknown value
     */
    UNKNOWN(-1),

    /**
     * No MAC passthrough messages.
     */
    EMBER_MAC_PASSTHROUGH_NONE(0x0000),

    /**
     * SE InterPAN messages.
     */
    EMBER_MAC_PASSTHROUGH_SE_INTERPAN(0x0001),

    /**
     * Legacy EmberNet messages.
     */
    EMBER_MAC_PASSTHROUGH_EMBERNET(0x0002),

    /**
     * Legacy EmberNet messages filtered by their source address.
     */
    EMBER_MAC_PASSTHROUGH_EMBERNET_SOURCE(0x0004);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EmberMacPassthroughType> codeMapping;

    private int key;

    static {
        codeMapping = new HashMap<Integer, EmberMacPassthroughType>();
        for (EmberMacPassthroughType s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    private EmberMacPassthroughType(int key) {
        this.key = key;
    }

    /**
     * Lookup function based on the EmberStatus type code. Returns null if the
     * code does not exist.
     *
     * @param code the code to lookup
     * @return enumeration value of the alarm type.
     */
    public static EmberMacPassthroughType getEmberMacPassthroughType(int code) {
        if (codeMapping.get(code) == null) {
            return UNKNOWN;
        }

        return codeMapping.get(code);
    }

    /**
     * Returns the EZSP protocol defined value for this enumeration.
     *
     * @return the EZSP protocol key
     */
    public int getKey() {
        return key;
    }
}
