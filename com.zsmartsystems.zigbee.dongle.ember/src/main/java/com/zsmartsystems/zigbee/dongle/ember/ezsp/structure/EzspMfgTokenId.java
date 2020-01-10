/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the Ember Enumeration <b>EzspMfgTokenId</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public enum EzspMfgTokenId {
    /**
     * Default unknown value
     */
    UNKNOWN(-1),

    /**
     * Custom version (2 bytes)
     */
    EZSP_MFG_CUSTOM_VERSION(0x0000),

    /**
     * Manufacturing string (16 bytes)
     */
    EZSP_MFG_STRING(0x0001),

    /**
     * Board name (16 bytes)
     */
    EZSP_MFG_BOARD_NAME(0x0002),

    /**
     * Manufacturing ID (2 bytes)
     */
    EZSP_MFG_MANUF_ID(0x0003),

    /**
     * Radio configuration (2 bytes)
     */
    EZSP_MFG_PHY_CONFIG(0x0004),

    /**
     * Bootload AES key (16 bytes)
     */
    EZSP_MFG_BOOTLOAD_AES_KEY(0x0005),

    /**
     * ASH configuration (40 bytes)
     */
    EZSP_MFG_ASH_CONFIG(0x0006),

    /**
     * EZSP storage (8 bytes)
     */
    EZSP_MFG_EZSP_STORAGE(0x0007),

    /**
     * Radio calibration data (64 bytes). 4 bytes are stored for each of the 16 channels. This token
     * is not stored in the Flash Information Area. It is updated by the stack each time a calibration
     * is performed.
     */
    EZSP_STACK_CAL_DATA(0x0008),

    /**
     * Certificate Based Key Exchange (CBKE) data (92 bytes)
     */
    EZSP_MFG_CBKE_DATA(0x0009),

    /**
     * Installation code (20 bytes)
     */
    EZSP_MFG_INSTALLATION_CODE(0x000A),

    /**
     * Radio channel filter calibration data (1 byte). This token is not stored in the Flash
     * Information Area. It is updated by the stack each time a calibration is performed.
     */
    EZSP_STACK_CAL_FILTER(0x000B),

    /**
     * Custom EUI64 MAC address (8 bytes)
     */
    EZSP_MFG_CUSTOM_EUI_64(0x000C),

    /**
     * CTUNE value (2 byte)
     */
    EZSP_MFG_CTUNE(0x000D);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EzspMfgTokenId> codeMapping;

    private int key;

    static {
        codeMapping = new HashMap<Integer, EzspMfgTokenId>();
        for (EzspMfgTokenId s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    private EzspMfgTokenId(int key) {
        this.key = key;
    }

    /**
     * Lookup function based on the EmberStatus type code. Returns null if the
     * code does not exist.
     *
     * @param code the code to lookup
     * @return enumeration value of the alarm type.
     */
    public static EzspMfgTokenId getEzspMfgTokenId(int code) {
        if (codeMapping.get(code) == null) {
            return UNKNOWN;
        }

        return codeMapping.get(code);
    }

    /**
     * Returns the EZSP protocol defined value for this enumeration.
     *
     * @return the EZSP protocol key
     */
    public int getKey() {
        return key;
    }
}
