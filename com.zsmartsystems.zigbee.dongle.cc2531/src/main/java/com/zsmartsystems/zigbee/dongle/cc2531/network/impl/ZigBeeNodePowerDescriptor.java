package com.zsmartsystems.zigbee.dongle.cc2531.network.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_POWER_DESC_RSP;

/**
 * This implements the Node Power Descriptor.
 * <p>
 * The node power descriptor gives a dynamic indication of the power status of
 * the node and is mandatory for each node.
 * <p>
 * <ul>
 * <li>powerMode</li>
 * <li>powerSourcesAvailable</li>
 * <li>powerSource</li>
 * <li>powerLevel</li>
 * </ul>
 * <p>
 * The power descriptor may be requested during service discovery to gain information
 * about the device.
 * <p>
 * There shall be only one node power descriptor in a node.
 *
 * @author <a href="mailto:chris@cd-jackson.com">Chris Jackson</a>
 */
public class ZigBeeNodePowerDescriptor {
    private POWER_MODE powerMode;
    private List<POWER_SOURCE> powerSourcesAvailable;
    private POWER_SOURCE powerSource;
    private POWER_LEVEL powerLevel;

    /**
     * The current power mode field of the node power descriptor is four bits in
     * length and specifies the current sleep/power-saving mode of the node.
     *
     */
    enum POWER_MODE {
        RECEIVER_ON(0),
        RECEIVER_PERIODICAL(1),
        RECEIVER_USER(2),
        UNKNOWN(-1);

        private static Map<Integer, POWER_MODE> mapping;
        private int key;

        private POWER_MODE(int key) {
            this.key = key;
        }

        private static void initMapping() {
            mapping = new HashMap<Integer, POWER_MODE>();
            for (POWER_MODE s : values()) {
                mapping.put(s.key, s);
            }
        }

        /**
         * Lookup function based on the code.
         * Returns null if the code does not exist.
         * 
         * @param i the code to lookup
         * @return enumeration value of the mode type.
         */
        public static POWER_MODE getType(int i) {
            if (mapping == null) {
                initMapping();
            }
            return mapping.get(i);
        }

        /**
         * Gets the key for this enum
         * 
         * @return the key
         */
        public int getKey() {
            return key;
        }
    }

    /**
     * The current power source level field of the node power descriptor is four
     * bits in length and specifies the level of charge of the power source.
     *
     */
    enum POWER_LEVEL {
        CRITICAL(0),
        LOW(4),
        GOOD(8),
        FULL(12),
        UNKNOWN(-1);

        private static Map<Integer, POWER_LEVEL> mapping;
        private int key;

        private POWER_LEVEL(int key) {
            this.key = key;
        }

        private static void initMapping() {
            mapping = new HashMap<Integer, POWER_LEVEL>();
            for (POWER_LEVEL s : values()) {
                mapping.put(s.key, s);
            }
        }

        /**
         * Lookup function based on the code.
         * Returns null if the code does not exist.
         * 
         * @param i the code to lookup
         * @return enumeration value of the mode type.
         */
        public static POWER_LEVEL getType(int i) {
            if (mapping == null) {
                initMapping();
            }
            return mapping.get(i);
        }

        /**
         * Gets the key for this enum
         * 
         * @return the key
         */
        public int getKey() {
            return key;
        }
    }

    /**
     * The power sources fields of the node power descriptor are four bits in
     * length and specifies the power sources available on this node.
     *
     */
    enum POWER_SOURCE {
        MAINS(1),
        BATTERY_RECHARCHABLE(2),
        BATTERY_DISPOSABLE(4),
        UNKNOWN(8);

        private static Map<Integer, POWER_SOURCE> mapping;
        private int key;

        private POWER_SOURCE(int key) {
            this.key = key;
        }

        private static void initMapping() {
            mapping = new HashMap<Integer, POWER_SOURCE>();
            for (POWER_SOURCE s : values()) {
                mapping.put(s.key, s);
            }
        }

        /**
         * Lookup function based on the code.
         * Returns null if the code does not exist.
         * 
         * @param i the code to lookup
         * @return enumeration value of the mode type.
         */
        public static POWER_SOURCE getType(int i) {
            if (mapping == null) {
                initMapping();
            }
            return mapping.get(i);
        }

        /**
         * Gets the key for this enum
         * 
         * @return the key
         */
        public int getKey() {
            return key;
        }
    }

    @SuppressWarnings("unused")
    private ZigBeeNodePowerDescriptor() {
        // required for Jackson deserialization
    }

    public ZigBeeNodePowerDescriptor(ZDO_POWER_DESC_RSP descriptorResponse) {
        powerMode = POWER_MODE.getType(descriptorResponse.getCurrentMode());
        if (powerMode == null) {
            powerMode = POWER_MODE.UNKNOWN;
        }

        powerSource = POWER_SOURCE.getType(descriptorResponse.getCurrentSource());
        if (powerSource == null) {
            powerSource = POWER_SOURCE.UNKNOWN;
        }

        powerSourcesAvailable = new ArrayList<POWER_SOURCE>();
        for (POWER_SOURCE entry : POWER_SOURCE.values()) {
            if ((entry.getKey() & descriptorResponse.getAvailableSources()) != 0) {
                powerSourcesAvailable.add(entry);
            }
        }

        powerLevel = POWER_LEVEL.getType(descriptorResponse.getCurrentLevel());
        if (powerLevel == null) {
            powerLevel = POWER_LEVEL.UNKNOWN;
        }
    }

    /**
     * The current power mode field of the node power descriptor is four bits in
     * length and specifies the current sleep/power-saving mode of the node.
     * 
     * @return {@link POWER_MODE}
     */
    public POWER_MODE getPowerMode() {
        return powerMode;
    }

    /**
     * The available power sources field of the node power descriptor is four
     * bits in length and specifies the power sources available on this node.
     * For each power source supported on this node, the corresponding bit of
     * the available power sources field, shall be set to 1. All other bits
     * shall be set to 0.
     * 
     * @return {@link POWER_LEVEL}
     */
    public List<POWER_SOURCE> getPowerSourcesAvailable() {
        return powerSourcesAvailable;
    }

    /**
     * The current power source field of the node power descriptor is four bits
     * in length and specifies the current power source being utilized by the
     * node. For the current power source selected, the corresponding bit of the
     * current power source field, shall be set to 1. All other bits shall be
     * set to 0.
     * 
     * @return {@link POWER_SOURCE}
     */
    public POWER_SOURCE getPowerSource() {
        return powerSource;
    }

    /**
     * The current power source level field of the node power descriptor is four
     * bits in length and specifies the level of charge of the power source. The
     * current power source level field shall be set to one of the non-reserved
     * values.
     * 
     * @return {@link POWER_LEVEL}
     */
    public POWER_LEVEL getPowerLevel() {
        return powerLevel;
    }
}
