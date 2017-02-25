package com.zsmartsystems.zigbee;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the broadcast destination addresses defined in the ZigBee protocol
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeBroadcastDestination {
    /**
     * All devices in PAN
     */
    BROADCAST_ALL_DEVICES(0xFFFF),

    /**
     * macRxOnWhenIdle = TRUE
     */
    BROADCAST_RX_ON(0xFFFD),

    /**
     * All routers and coordinator
     */
    BROADCAST_ROUTERS_AND_COORD(0xFFFC),

    /**
     * Low power routers only
     */
    BROADCAST_LOW_POWER_ROUTERS(0xFFFB),

    // Reserved values
    BROADCAST_RESERVED_FFFE(0xFFFE),
    BROADCAST_RESERVED_FFFA(0xFFFA),
    BROADCAST_RESERVED_FFF9(0xFFF9),
    BROADCAST_RESERVED_FFF8(0xFFF8);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, ZigBeeBroadcastDestination> codeMapping;

    private int key;

    private ZigBeeBroadcastDestination(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, ZigBeeBroadcastDestination>();
        for (ZigBeeBroadcastDestination s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    /**
     * Lookup function based on the EmberApsOption type code. Returns null
     * if the code does not exist.
     *
     * @param i
     *            the code to lookup
     * @return enumeration value of the alarm type.
     */
    public static ZigBeeBroadcastDestination getBroadcastDestination(int i) {
        if (codeMapping == null) {
            initMapping();
        }

        if (codeMapping.get(i) == null) {
            return BROADCAST_ALL_DEVICES;
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
