/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
 * Gp Pairing Option value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum GpPairingOptionBitmap {

    /**
     * Application ID, 7, 0x0007
     */
    APPLICATION_ID(0x0007),

    /**
     * Add Sink, 8, 0x0008
     */
    ADD_SINK(0x0008),

    /**
     * Remove Gpd, 16, 0x0010
     */
    REMOVE_GPD(0x0010),

    /**
     * Communication Mode, 96, 0x0060
     */
    COMMUNICATION_MODE(0x0060),

    /**
     * Gpd Fixed, 128, 0x0080
     */
    GPD_FIXED(0x0080),

    /**
     * Gpd MAC Sequence Number Capabilities, 256, 0x0100
     */
    GPD_MAC_SEQUENCE_NUMBER_CAPABILITIES(0x0100),

    /**
     * Security Level, 1536, 0x0600
     */
    SECURITY_LEVEL(0x0600),

    /**
     * Security Key Type, 14336, 0x3800
     */
    SECURITY_KEY_TYPE(0x3800),

    /**
     * Gpd Security Frame Counter Present, 16384, 0x4000
     */
    GPD_SECURITY_FRAME_COUNTER_PRESENT(0x4000),

    /**
     * Gpd Security Key Present, 32768, 0x8000
     */
    GPD_SECURITY_KEY_PRESENT(0x8000),

    /**
     * Assigned Alias Present, 65536, 0x10000
     */
    ASSIGNED_ALIAS_PRESENT(0x10000),

    /**
     * Forwarding Radius Present, 131072, 0x20000
     */
    FORWARDING_RADIUS_PRESENT(0x20000);

    /**
     * A mapping between the integer code and its corresponding GpPairingOptionBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, GpPairingOptionBitmap> idMap;

    static {
        idMap = new HashMap<Integer, GpPairingOptionBitmap>();
        for (GpPairingOptionBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private GpPairingOptionBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static GpPairingOptionBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
