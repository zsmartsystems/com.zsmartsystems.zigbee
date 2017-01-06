package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Chris Jackson
 *
 */
public enum EmberJoinMethod {
    EMBER_USE_UNKNOWN(-1),
    /**
     * Normally devices use MAC Association to join a network, which
     * respects the "permit joining" flag in the MAC Beacon. For mobile
     * nodes this value causes the device to use an Ember Mobile Node
     * Join, which is functionally equivalent to a MAC association. This
     * value should be used by default.
     */
    EMBER_USE_MAC_ASSOCIATION(0),
    /**
     * For those networks where the "permit joining" flag is never turned
     * on, they will need to use a ZigBee NWK Rejoin. This value
     * causes the rejoin to be sent without NWK security and the Trust
     * Center will be asked to send the NWK key to the device. The
     * NWK key sent to the device can be encrypted with the device's
     * corresponding Trust Center link key. That is determined by the
     * ::EmberJoinDecision on the Trust Center returned by the
     * ::emberTrustCenterJoinHandler(). For a mobile node this value
     * will cause it to use an Ember Mobile node rejoin, which is
     * functionally equivalent.
     */
    EMBER_USE_NWK_REJOIN(1),
    /**
     * For those networks where the "permit joining" flag is never turned
     * on, they will need to use a NWK Rejoin. If those devices have
     * been preconfigured with the NWK key (including sequence
     * number) they can use a secured rejoin. This is only necessary for
     * end devices since they need a parent. Routers can simply use
     * the ::EMBER_USE_NWK_COMMISSIONING join method below.
     */
    EMBER_USE_NWK_REJOIN_HAVE_NWK_KEY(2),
    /**
     * For those networks where all network and security information is
     * known ahead of time, a router device may be commissioned such
     * that it does not need to send any messages to begin
     * communicating on the network.
     */
    EMBER_USE_NWK_COMMISSIONING(3);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EmberJoinMethod> codeMapping;

    private int key;

    private EmberJoinMethod(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, EmberJoinMethod>();
        for (EmberJoinMethod s : values()) {
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
    public static EmberJoinMethod getEmberJoinMethod(int i) {
        if (codeMapping == null) {
            initMapping();
        }

        if (codeMapping.get(i) == null) {
            return EMBER_USE_UNKNOWN;
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
