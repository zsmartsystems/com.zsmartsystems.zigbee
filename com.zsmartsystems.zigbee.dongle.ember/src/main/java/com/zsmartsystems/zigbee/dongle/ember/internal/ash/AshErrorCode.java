/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ash;

import java.util.HashMap;
import java.util.Map;

/**
 * Lists the codes returned by the NCP in the Reset Code byte of a RSTACK frame or in the Error Code byte of an ERROR
 * frame.
 *
 * @author Chris Jackson
 *
 */
public enum AshErrorCode {
    UNKNOWN(-1, "Unknown"),
    RESET_UNKNOWN(0x00, "Reset: Unknown reason"),
    RESET_EXTERNAL(0x01, "Reset: External"),
    RESET_POWER_ON(0x02, "Reset: Power-on"),
    RESET_WATCHDOG(0x03, "Reset: Watchdog"),
    RESET_ASSERT(0x06, "Reset: Assert"),
    RESET_BOOTLOADER(0x09, "Reset: Boot loader"),
    RESET_SOFTWARE(0x0B, "Reset: Software"),
    RESET_TIMEOUT(0x51, "Error: Exceeded maximum ACK timeout count");

    private static Map<Integer, AshErrorCode> codeHashMap;
    private final int errorCode;
    private final String description;

    AshErrorCode(int errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    /**
     * Gets an AshErrorCode enum given the ASH error code
     *
     * @param errorCode
     * @return
     */
    public static AshErrorCode getAshErrorCode(int errorCode) {
        if (codeHashMap == null) {
            codeHashMap = new HashMap<Integer, AshErrorCode>();
            for (AshErrorCode value : values()) {
                codeHashMap.put(value.getErrorCode(), value);
            }
        }

        AshErrorCode error = codeHashMap.get(errorCode);
        if (error == null) {
            return UNKNOWN;
        }

        return error;
    }

    /**
     * Returns the ASH error code value
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Returns the UG101 documented description of the error code
     *
     * @return
     */
    public String getDescription() {
        return description;
    }
}
