/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-07-04T21:54:11Z")
public enum GpPairingConfigurationOptionBitmap {

    /**
     * Application ID
     */
    APPLICATION_ID(0x0007),

    /**
     * Communication Mode
     */
    COMMUNICATION_MODE(0x0018),

    /**
     * Sequence Number Capabilities
     */
    SEQUENCE_NUMBER_CAPABILITIES(0x0020),

    /**
     * Rx On Capability
     */
    RX_ON_CAPABILITY(0x0040),

    /**
     * Fixed Location
     */
    FIXED_LOCATION(0x0080),

    /**
     * Assigned Alias
     */
    ASSIGNED_ALIAS(0x0100),

    /**
     * Security Use
     */
    SECURITY_USE(0x0200),

    /**
     * Application Information Present
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
