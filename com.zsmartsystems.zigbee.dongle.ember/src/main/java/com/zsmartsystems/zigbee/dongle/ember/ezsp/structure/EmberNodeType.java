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
 * Class to implement the Ember Enumeration <b>EmberNodeType</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public enum EmberNodeType {
    /**
     * Default unknown value
     */
    UNKNOWN(-1),

    /**
     * Device is not joined
     */
    EMBER_UNKNOWN_DEVICE(0x0000),

    /**
     * Will relay messages and can act as a parent to other nodes.
     */
    EMBER_COORDINATOR(0x0001),

    /**
     * Will relay messages and can act as a parent to other nodes.
     */
    EMBER_ROUTER(0x0002),

    /**
     * Communicates only with its parent and will not relay messages.
     */
    EMBER_END_DEVICE(0x0003),

    /**
     * An end device whose radio can be turned off to save power. The application must poll to receive
     * messages.
     */
    EMBER_SLEEPY_END_DEVICE(0x0004),

    /**
     * A sleepy end device that can move through the network.
     */
    EMBER_MOBILE_END_DEVICE(0x0005),

    /**
     * RF4CE target node.
     */
    EMBER_RF4CE_TARGET(0x0006),

    /**
     * RF4CE controller node.
     */
    EMBER_RF4CE_CONTROLLER(0x0007);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EmberNodeType> codeMapping;

    private int key;

    static {
        codeMapping = new HashMap<Integer, EmberNodeType>();
        for (EmberNodeType s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    private EmberNodeType(int key) {
        this.key = key;
    }

    /**
     * Lookup function based on the EmberStatus type code. Returns null if the
     * code does not exist.
     *
     * @param code the code to lookup
     * @return enumeration value of the alarm type.
     */
    public static EmberNodeType getEmberNodeType(int code) {
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
