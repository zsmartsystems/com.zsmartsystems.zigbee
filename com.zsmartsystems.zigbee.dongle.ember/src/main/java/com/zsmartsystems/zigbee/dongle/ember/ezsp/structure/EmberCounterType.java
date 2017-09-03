/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the Ember Enumeration <b>EmberCounterType</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public enum EmberCounterType {
    /**
     * Default unknown value
     */
    UNKNOWN(-1),

    /**
     * The MAC received a broadcast
     */
    EMBER_COUNTER_MAC_RX_BROADCAST(0x0000),

    /**
     * The MAC transmitted a broadcast
     */
    EMBER_COUNTER_MAC_TX_BROADCAST(0x0001),

    /**
     * The MAC received a unicast
     */
    EMBER_COUNTER_MAC_RX_UNICAST(0x0002),

    /**
     * The MAC successfully transmitted a unicast.
     */
    EMBER_COUNTER_MAC_TX_UNICAST_SUCCESS(0x0003),

    /**
     * The MAC retried a unicast
     */
    EMBER_COUNTER_MAC_TX_UNICAST_RETRY(0x0004),

    /**
     * The MAC unsuccessfully transmitted a unicast.
     */
    EMBER_COUNTER_MAC_TX_UNICAST_FAILED(0x0005),

    /**
     * The APS layer received a data broadcast.
     */
    EMBER_COUNTER_APS_DATA_RX_BROADCAST(0x0006),

    /**
     * The APS layer transmitted a data broadcast.
     */
    EMBER_COUNTER_APS_DATA_TX_BROADCAST(0x0007),

    /**
     * The APS layer received a data unicast.
     */
    EMBER_COUNTER_APS_DATA_RX_UNICAST(0x0008),

    /**
     * The APS layer successfully transmitted a data unicast.
     */
    EMBER_COUNTER_APS_DATA_TX_UNICAST_SUCCESS(0x0009),

    /**
     * The APS layer retried a data unicast.
     */
    EMBER_COUNTER_APS_DATA_TX_UNICAST_RETRY(0x000A),

    /**
     * The APS layer unsuccessfully transmitted a data unicast.
     */
    EMBER_COUNTER_APS_DATA_TX_UNICAST_FAILED(0x000B),

    /**
     * The network layer successfully submitted a new route discovery to the MAC.
     */
    EMBER_COUNTER_ROUTE_DISCOVERY_INITIATED(0x000C),

    /**
     * An entry was added to the neighbor table.
     */
    EMBER_COUNTER_NEIGHBOR_ADDED(0x000D),

    /**
     * An entry was removed from the neighbor table.
     */
    EMBER_COUNTER_NEIGHBOR_REMOVED(0x000E),

    /**
     * A neighbor table entry became stale because it had not been heard from.
     */
    EMBER_COUNTER_NEIGHBOR_STALE(0x000F),

    /**
     * A node joined or rejoined to the network via this node.
     */
    EMBER_COUNTER_JOIN_INDICATION(0x0010),

    /**
     * An entry was removed from the child table.
     */
    EMBER_COUNTER_CHILD_REMOVED(0x0011),

    /**
     * EZSP-UART only. An overflow error occurred in the UART
     */
    EMBER_COUNTER_ASH_OVERFLOW_ERROR(0x0012),

    /**
     * EZSP-UART only. A framing error occurred in the UART
     */
    EMBER_COUNTER_ASH_FRAMING_ERROR(0x0013),

    /**
     * EZSP-UART only. An overrun error occurred in the UART
     */
    EMBER_COUNTER_ASH_OVERRUN_ERROR(0x0014),

    /**
     * A message was dropped at the network layer because the NWK frame counter was not higher than
     * the last message seen from that source.
     */
    EMBER_COUNTER_NWK_FRAME_COUNTER_FAILURE(0x0015),

    /**
     * A message was dropped at the APS layer because the APS frame counter was not higher than the
     * last message seen from that source.
     */
    EMBER_COUNTER_APS_FRAME_COUNTER_FAILURE(0x0016),

    /**
     * Utility counter for general debugging use.
     */
    EMBER_COUNTER_UTILITY(0x0017),

    /**
     * A message was dropped at the APS layer because it had APS encryption but the key associated
     * with the sender has not been authenticated, and thus the key is not authorized for use in APS
     * data messages.
     */
    EMBER_COUNTER_APS_LINK_KEY_NOT_AUTHORIZED(0x0018),

    /**
     * A NWK encrypted message was received but dropped because decryption failed.
     */
    EMBER_COUNTER_NWK_DECRYPTION_FAILURE(0x0019),

    /**
     * An APS encrypted message was received but dropped because decryption failed.
     */
    EMBER_COUNTER_APS_DECRYPTION_FAILURE(0x001A),

    /**
     * The number of times we failed to allocate a set of linked packet buffers. This doesn't
     * necessarily mean that the packet buffer count was 0 at the time, but that the number requested
     * was greater than the number free.
     */
    EMBER_COUNTER_ALLOCATE_PACKET_BUFFER_FAILURE(0x001B),

    /**
     * The number of relayed unicast packets.
     */
    EMBER_COUNTER_RELAYED_UNICAST(0x001C),

    /**
     * A placeholder giving the number of Ember counter types.
     */
    EMBER_COUNTER_TYPE_COUNT(0x001D);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EmberCounterType> codeMapping;

    private int key;

    private EmberCounterType(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, EmberCounterType>();
        for (EmberCounterType s : values()) {
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
    public static EmberCounterType getEmberCounterType(int i) {
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
