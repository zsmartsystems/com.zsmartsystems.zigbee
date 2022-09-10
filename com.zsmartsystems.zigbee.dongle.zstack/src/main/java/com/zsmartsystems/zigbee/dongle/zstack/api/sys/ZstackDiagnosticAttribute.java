/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.sys;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the Z-Stack Enumeration <b>ZstackDiagnosticAttribute</b>.
 * <p>
 * Diagnostics attribute IDs.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public enum ZstackDiagnosticAttribute {
    /**
     * Default unknown value
     */
    UNKNOWN(-1),

    /**
     * System Clock when stats were saved/cleared
     */
    ZDIAGS_SYSTEM_CLOCK(0x0000),

    /**
     * Increments every time the system resets
     */
    ZDIAGS_NUMBER_OF_RESETS(0x0001),

    /**
     * MAC diagnostic CRC success counter
     */
    ZDIAGS_MAC_RX_CRC_PASS(0x0064),

    /**
     * MAC diagnostic CRC failure counter
     */
    ZDIAGS_MAC_RX_CRC_FAIL(0x0065),

    /**
     * MAC layer retries a unicast
     */
    ZDIAGS_MAC_TX_UCAST_RETRY(0x006A),

    /**
     * Mac layer fails to send a unicast
     */
    ZDIAGS_MAC_TX_UCAST_FAIL(0x006B),

    /**
     * NWK packet decryption failed
     */
    ZDIAGS_NWK_DECRYPT_FAILURES(0x00CF),

    /**
     * NWK packet drop because of validation error
     */
    ZDIAGS_PACKET_VALIDATE_DROP_COUNT(0x00D3),

    /**
     * APS layer transmits broadcast
     */
    ZDIAGS_APS_TX_BCAST(0x012D),

    /**
     * APS layer successfully transmits a unicast
     */
    ZDIAGS_APS_TX_UCAST_SUCCESS(0x012F),

    /**
     * APS layer retries the sending of a unicast
     */
    ZDIAGS_APS_TX_UCAST_RETRY(0x0130),

    /**
     * APS layer fails to send a unicast
     */
    ZDIAGS_APS_TX_UCAST_FAIL(0x0131),

    /**
     * APS packet decryption failed
     */
    ZDIAGS_APS_DECRYPT_FAILURES(0x0134),

    /**
     * APS invalid packet dropped
     */
    ZDIAGS_APS_INVALID_PACKETS(0x0135),

    /**
     * Number of MAC retries per APS message
     */
    ZDIAGS_MAC_RETRIES_PER_APS_TX_SUCCESS(0x0136);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, ZstackDiagnosticAttribute> codeMapping;

    private int key;

    static {
        codeMapping = new HashMap<Integer, ZstackDiagnosticAttribute>();
        for (ZstackDiagnosticAttribute s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    private ZstackDiagnosticAttribute(int key) {
        this.key = key;
    }

    /**
     * Lookup function based on the type code. Returns null if the code does not exist.
     *
     * @param code the code to lookup
     * @return enumeration value of the alarm type.
     */
    public static ZstackDiagnosticAttribute valueOf(int code) {
        if (codeMapping.get(code) == null) {
            return UNKNOWN;
        }

        return codeMapping.get(code);
    }

    /**
     * Returns the Z-Stack protocol defined value for this enumeration.
     *
     * @return the Z-Stack protocol key
     */
    public int getKey() {
        return key;
    }
}
