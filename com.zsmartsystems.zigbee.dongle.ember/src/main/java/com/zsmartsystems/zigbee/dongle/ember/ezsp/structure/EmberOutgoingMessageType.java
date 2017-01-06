package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Chris Jackson
 *
 */
public enum EmberOutgoingMessageType {
    EMBER_OUTGOING_UNKNOWN(0xFFFFF),
    /**
     * Unicast sent directly to an EmberNodeId.
     */
    EMBER_OUTGOING_DIRECT(0x00),
    /**
     * Unicast sent using an entry in the address table.
     */
    EMBER_OUTGOING_VIA_ADDRESS_TABLE(0x01),
    /**
     * Unicast sent using an entry in the binding table.
     */
    EMBER_OUTGOING_VIA_BINDING(0x02),
    /**
     * Multicast message. This value is passed to emberMessageSentHandler() only. It may not be passed to
     * emberSendUnicast().
     */
    EMBER_OUTGOING_MULTICAST(0x03),
    /**
     * Broadcast message. This value is passed to emberMessageSentHandler() only. It may not be passed to
     * emberSendUnicast().
     */
    EMBER_OUTGOING_BROADCAST(0x04);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EmberOutgoingMessageType> codeMapping;

    private int key;

    private EmberOutgoingMessageType(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, EmberOutgoingMessageType>();
        for (EmberOutgoingMessageType s : values()) {
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
    public static EmberOutgoingMessageType getEmberOutgoingMessageType(int i) {
        if (codeMapping == null) {
            initMapping();
        }

        if (codeMapping.get(i) == null) {
            return EMBER_OUTGOING_UNKNOWN;
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
