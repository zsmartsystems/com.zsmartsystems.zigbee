package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the Ember Node Types
 * 
 * @author Chris Jackson
 *
 */
public enum EmberNodeType {
    EMBER_UNKNOWN_DEVICE(0),
    EMBER_COORDINATOR(1),
    EMBER_ROUTER(2),
    EMBER_END_DEVICE(3),
    EMBER_SLEEPY_END_DEVICE(4),
    EMBER_MOBILE_END_DEVICE(5);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EmberNodeType> codeMapping;

    private int key;

    private EmberNodeType(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, EmberNodeType>();
        for (EmberNodeType s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    /**
     * Lookup function based on the EmberNodeType type code. Returns null if the
     * code does not exist.
     *
     * @param i
     *            the code to lookup
     * @return enumeration value of the alarm type.
     */
    public static EmberNodeType getEmberNodeType(int i) {
        if (codeMapping == null) {
            initMapping();
        }

        if (codeMapping.get(i) == null) {
            return EMBER_UNKNOWN_DEVICE;
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
