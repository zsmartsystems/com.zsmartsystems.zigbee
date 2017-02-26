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
 * Class to implement the Ember Enumeration <b>EmberIncomingMessageType</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public enum EmberIncomingMessageType {
    /**
     * Default unknown value
     */
    UNKNOWN(-1),

    /**
     * Unicast
     */
    EMBER_INCOMING_UNICAST(0x0000),

    /**
     * Unicast reply
     */
    EMBER_INCOMING_UNICAST_REPLY(0x0001),

    /**
     * Multicast
     */
    EMBER_INCOMING_MULTICAST(0x0002),

    /**
     * Multicast sent by the local device
     */
    EMBER_INCOMING_MULTICAST_LOOPBACK(0x0003),

    /**
     * Broadcast
     */
    EMBER_INCOMING_BROADCAST(0x0004),

    /**
     * Broadcast sent by the local device.
     */
    EMBER_INCOMING_BROADCAST_LOOPBACK(0x0005),

    /**
     * Many to one route request
     */
    EMBER_INCOMING_MANY_TO_ONE_ROUTE_REQUEST(0x0006);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EmberIncomingMessageType> codeMapping;

    private int key;

    private EmberIncomingMessageType(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, EmberIncomingMessageType>();
        for (EmberIncomingMessageType s : values()) {
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
    public static EmberIncomingMessageType getEmberIncomingMessageType(int i) {
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
