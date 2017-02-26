/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the Ember Enumeration <b>EmberBindingType</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public enum EmberBindingType {
    /**
     * Default unknown value
     */
    UNKNOWN(-1),

    /**
     * A binding that is currently not in use.
     */
    EMBER_UNUSED_BINDING(0x0000),

    /**
     * A unicast binding whose 64-bit identifier is the destination EUI64.
     */
    EMBER_UNICAST_BINDING(0x0001),

    /**
     * A unicast binding whose 64-bit identifier is the aggregator EUI64.
     */
    EMBER_MANY_TO_ONE_BINDING(0x0002),

    /**
     * A multicast binding whose 64-bit identifier is the group address. A multicast binding can be
     * used to send messages to the group and to receive messages sent to the group.
     */
    EMBER_MULTICAST_BINDING(0x0003);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EmberBindingType> codeMapping;

    private int key;

    private EmberBindingType(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, EmberBindingType>();
        for (EmberBindingType s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    /**
     * Lookup function based on the EmberStatus type code. Returns null if the
     * code does not exist.
     *
     * @param i
     *            the code to lookup
     * @return enumeration value of the alarm type.
     */
    public static EmberBindingType getEmberBindingType(int i) {
        if (codeMapping == null) {
            initMapping();
        }

        if (codeMapping.get(i) == null) {
            return UNKNOWN;
        }

        return codeMapping.get(i);
    }

    /**
     * Returns the EZSP protocol defined value for this enum
     *
     * @return the EZSP protocol key
     */
    public int getKey() {
        return key;
    }
}
