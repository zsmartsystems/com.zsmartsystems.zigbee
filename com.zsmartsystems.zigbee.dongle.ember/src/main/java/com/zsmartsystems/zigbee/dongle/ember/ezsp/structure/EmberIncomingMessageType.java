package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Chris Jackson
 *
 */
public enum EmberIncomingMessageType {
    EMBER_INCOMING_UNKNOWN(0xFFFFF),
    /**
     * Unicast.
     */
    EMBER_INCOMING_UNICAST(0x00),
    /**
     * Unicast reply.
     */
    EMBER_INCOMING_UNICAST_REPLY(0x01),
    /**
     * Multicast.
     */
    EMBER_INCOMING_MULTICAST(0x02),
    /**
     * Multicast sent by the local device.
     */
    EMBER_INCOMING_MULTICAST_LOOPBACK(0x03),
    /**
     * Broadcast message.
     */
    EMBER_INCOMING_BROADCAST(0x04),
    /**
     * Broadcast sent by the local device.
     */
    EMBER_INCOMING_BROADCAST_LOOPBACK(0x05),
    /**
     * Many to one route request.
     */
    EMBER_INCOMING_MANY_TO_ONE_ROUTE_REQUEST(0x06);

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
     * Lookup function based on the EzspNetworkScanType type code. Returns null
     * if the code does not exist.
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
            return EMBER_INCOMING_UNKNOWN;
        }

        return codeMapping.get(i);
    }

    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }
}
