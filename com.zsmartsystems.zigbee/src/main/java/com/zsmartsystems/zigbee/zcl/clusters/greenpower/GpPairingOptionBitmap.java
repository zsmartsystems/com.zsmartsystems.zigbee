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
 * Gp Pairing Option value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-07-04T21:54:11Z")
public enum GpPairingOptionBitmap {

    /**
     * Application ID
     */
    APPLICATION_ID(0x0007),

    /**
     * Add Sink
     */
    ADD_SINK(0x0008),

    /**
     * Remove Gpd
     */
    REMOVE_GPD(0x0010),

    /**
     * Communication Mode
     */
    COMMUNICATION_MODE(0x0060),

    /**
     * Gpd Fixed
     */
    GPD_FIXED(0x0080),

    /**
     * Gpd MAC Sequence Number Capabilities
     */
    GPD_MAC_SEQUENCE_NUMBER_CAPABILITIES(0x0100),

    /**
     * Security Level
     */
    SECURITY_LEVEL(0x0600),

    /**
     * Security Key Type
     */
    SECURITY_KEY_TYPE(0x3800),

    /**
     * Gpd Security Frame Counter Present
     */
    GPD_SECURITY_FRAME_COUNTER_PRESENT(0x4000),

    /**
     * Gpd Security Key Present
     */
    GPD_SECURITY_KEY_PRESENT(0x8000),

    /**
     * Assigned Alias Present
     */
    ASSIGNED_ALIAS_PRESENT(0x10000),

    /**
     * Forwarding Radius Present
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
