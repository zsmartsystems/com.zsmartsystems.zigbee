/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.keyestablishment;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Key Establishment Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum KeyEstablishmentStatusEnum {

    /**
     * Unknown Issuer
     */
    UNKNOWN_ISSUER(0x0001),

    /**
     * Bad Key Confirm
     */
    BAD_KEY_CONFIRM(0x0002),

    /**
     * Bad Message
     */
    BAD_MESSAGE(0x0003),

    /**
     * No Resources
     */
    NO_RESOURCES(0x0004),

    /**
     * Unsupported Suite
     */
    UNSUPPORTED_SUITE(0x0005),

    /**
     * Invalid Certificate
     */
    INVALID_CERTIFICATE(0x0006);

    /**
     * A mapping between the integer code and its corresponding KeyEstablishmentStatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, KeyEstablishmentStatusEnum> idMap;

    static {
        idMap = new HashMap<Integer, KeyEstablishmentStatusEnum>();
        for (KeyEstablishmentStatusEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private KeyEstablishmentStatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static KeyEstablishmentStatusEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
