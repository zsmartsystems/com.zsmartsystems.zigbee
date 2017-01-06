package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the Ember Node Types
 * 
 * @author Chris Jackson
 *
 */
public enum EmberStatus {
    EMBED_UNKNOWN_STATUS(-3),
    EMBER_UNINTIALISED(-2),
    EMBER_IN_PROGRESS(-1),
    EMBER_SUCCESS(0x00),
    EMBER_ERR_FATAL(0x01),
    EMBER_BAD_ARGUMENT(0x02),
    EMBER_EEPROM_MFG_STACK_VERSION_MISMATCH(0x04),
    EMBER_INCOMPATIBLE_STATIC_MEMORY_DEFINITIONS(0x05),
    EMBER_EEPROM_MFG_VERSION_MISMATCH(0x06),
    EMBER_EEPROM_STACK_VERSION_MISMATCH(0x07),
    EMBER_NO_BUFFERS(0x18),
    EMBER_SERIAL_INVALID_BAUD_RATE(0x20),
    EMBER_SERIAL_INVALID_PORT(0x21),
    EMBER_SERIAL_TX_OVERFLOW(0x22),
    EMBER_SERIAL_RX_OVERFLOW(0x23),
    EMBER_SERIAL_RX_FRAME_ERROR(0x24),
    EMBER_SERIAL_RX_PARITY_ERROR(0x25),
    EMBER_SERIAL_RX_EMPTY(0x26),
    EMBER_SERIAL_RX_OVERRUN_ERROR(0x27),
    EMBER_MAC_NO_DATA(0x31),
    EMBER_MAC_JOINED_NETWORK(0x32),
    EMBER_MAC_BAD_SCAN_DURATION(0x33),
    EMBER_MAC_INCORRECT_SCAN_TYPE(0x34),
    EMBER_MAC_INVALID_CHANNEL_MASK(0x35),
    EMBER_MAC_COMMAND_TRANSMIT_FAILURE(0x36),
    EMBER_MAC_TRANSMIT_QUEUE_FULL(0x39),
    EMBER_MAC_UNKNOWN_HEADER_TYPE(0x3A),
    EMBER_MAC_SCANNING(0x3D),
    EMBER_MAC_NO_ACK_RECEIVED(0x40),
    EMBER_MAC_INDIRECT_TIMEOUT(0x42),
    EMBER_SIM_EEPROM_ERASE_PAGE_GREEN(0x43),
    EMBER_SIM_EEPROM_ERASE_PAGE_RED(0x44),

    EMBER_PHY_TX_INCOMPLETE(0x89),
    EMBER_PHY_INVALID_CHANNEL(0x8A),
    EMBER_PHY_INVALID_POWER(0x8B),
    EMBER_PHY_TX_BUSY(0x8C),
    EMBER_PHY_TX_CCA_FAIL(0x8D),
    EMBER_PHY_OSCILLATOR_CHECK_FAILED(0x8E),
    EMBER_PHY_ACK_RECEIVED(0x8F),

    EMBER_NETWORK_UP(0x90),
    EMBER_NETWORK_DOWN(0x91),
    EMBER_NOT_JOINED(0x93),
    EMBER_JOIN_FAILED(0x94),
    EMBER_INVALID_SECURITY_LEVEL(0x95),
    EMBER_MOVE_FAILED(0x96),
    EMBER_CANNOT_JOIN_AS_ROUTER(0x98),
    EMBER_NODE_ID_CHANGED(0x99),
    EMBER_PAN_ID_CHANGED(0x9A),

    EMBER_INSUFFICIENT_RANDOM_DATA(0xA5),
    EMBER_APS_ENCRYPTION_ERROR(0xA6),
    EMBER_TRUST_CENTER_MASTER_KEY_NOT_SET(0xA7),
    EMBER_SECURITY_STATE_NOT_SET(0xA8),
    EMBER_NO_BEACONS(0xAB),

    EMBER_KEY_TABLE_INVALID_ADDRESS(0xB3),
    EMBER_SECURITY_CONFIGURATION_INVALID(0xB7),
    EMBER_TOO_SOON_FOR_SWITCH_KEY(0xB8),
    EMBER_KEY_NOT_AUTHORIZED(0xBB),
    EMBER_SECURITY_DATA_INVALID(0xBD);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EmberStatus> codeMapping;

    private int key;

    private EmberStatus(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, EmberStatus>();
        for (EmberStatus s : values()) {
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
    public static EmberStatus getEmberStatus(int i) {
        if (codeMapping == null) {
            initMapping();
        }

        if (codeMapping.get(i) == null) {
            return EMBED_UNKNOWN_STATUS;
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
