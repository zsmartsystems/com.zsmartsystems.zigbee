/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.doorlock;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Door Lock Program Event Code value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:31:31Z")
public enum DoorLockProgramEventCode {

    /**
     * Unknown Or Mfg Specific, 0, 0x0000
     */
    UNKNOWN_OR_MFG_SPECIFIC(0x0000),

    /**
     * Master Code Changed, 1, 0x0001
     */
    MASTER_CODE_CHANGED(0x0001),

    /**
     * PINCodeAdded, 2, 0x0002
     */
    PINCODEADDED(0x0002),

    /**
     * PINCodeDeleted, 3, 0x0003
     */
    PINCODEDELETED(0x0003),

    /**
     * PINCodeChanged, 4, 0x0004
     */
    PINCODECHANGED(0x0004),

    /**
     * RFIDCodeAdded, 5, 0x0005
     */
    RFIDCODEADDED(0x0005),

    /**
     * RFIDCodeDeleted, 6, 0x0006
     */
    RFIDCODEDELETED(0x0006);

    /**
     * A mapping between the integer code and its corresponding DoorLockProgramEventCode type to facilitate lookup by value.
     */
    private static Map<Integer, DoorLockProgramEventCode> idMap;

    static {
        idMap = new HashMap<Integer, DoorLockProgramEventCode>();
        for (DoorLockProgramEventCode enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private DoorLockProgramEventCode(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static DoorLockProgramEventCode getByValue(final int value) {
        return idMap.get(value);
    }
}
