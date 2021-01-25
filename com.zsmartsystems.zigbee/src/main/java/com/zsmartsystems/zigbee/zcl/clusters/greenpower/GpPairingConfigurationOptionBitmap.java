/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Gp Pairing Configuration Option value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum GpPairingConfigurationOptionBitmap {

    /**
     * Application ID, 7, 0x0007
     */
    APPLICATION_ID(0x0007),

    /**
     * Communication Mode, 24, 0x0018
     */
    COMMUNICATION_MODE(0x0018),

    /**
     * Sequence Number Capabilities, 32, 0x0020
     */
    SEQUENCE_NUMBER_CAPABILITIES(0x0020),

    /**
     * Rx On Capability, 64, 0x0040
     */
    RX_ON_CAPABILITY(0x0040),

    /**
     * Fixed Location, 128, 0x0080
     */
    FIXED_LOCATION(0x0080),

    /**
     * Assigned Alias, 256, 0x0100
     */
    ASSIGNED_ALIAS(0x0100),

    /**
     * Security Use, 512, 0x0200
     */
    SECURITY_USE(0x0200),

    /**
     * Application Information Present, 1024, 0x0400
     */
    APPLICATION_INFORMATION_PRESENT(0x0400);

    /**
     * A mapping between the integer code and its corresponding GpPairingConfigurationOptionBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, GpPairingConfigurationOptionBitmap> idMap;

    static {
        idMap = new HashMap<Integer, GpPairingConfigurationOptionBitmap>();
        for (GpPairingConfigurationOptionBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GpPairingConfigurationOptionBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GpPairingConfigurationOptionBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
