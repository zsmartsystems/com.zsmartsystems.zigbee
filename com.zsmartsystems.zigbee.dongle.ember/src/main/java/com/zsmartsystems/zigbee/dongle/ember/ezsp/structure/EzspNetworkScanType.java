package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Chris Jackson
 *
 */
public enum EzspNetworkScanType {
    EZSP_UNKNOWN_SCAN(-1),
    EZSP_ENERGY_SCAN(0),
    EZSP_ACTIVE_SCAN(1);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EzspNetworkScanType> codeMapping;

    private int key;

    private EzspNetworkScanType(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, EzspNetworkScanType>();
        for (EzspNetworkScanType s : values()) {
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
    public static EzspNetworkScanType getEzspNetworkScanType(int i) {
        if (codeMapping == null) {
            initMapping();
        }

        if (codeMapping.get(i) == null) {
            return EZSP_UNKNOWN_SCAN;
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
