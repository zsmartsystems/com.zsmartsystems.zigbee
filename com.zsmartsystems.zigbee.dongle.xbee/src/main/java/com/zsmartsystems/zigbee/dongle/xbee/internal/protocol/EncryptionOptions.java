/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the XBee Enumeration <b>EncryptionOptions</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public enum EncryptionOptions {
    /**
     * Default unknown value
     */
    UNKNOWN(-1),

    /**
     * [1]
     */
    SEND_NWK_KEY_IN_CLEAR(0x0001),

    /**
     * [2]
     */
    ENABLE_TRUST_CENTRE(0x0002),

    /**
     * [8]
     */
    AUTHENTICATE_JOINING(0x0008);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EncryptionOptions> codeMapping;

    private int key;

    private EncryptionOptions(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, EncryptionOptions>();
        for (EncryptionOptions s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    /**
     * Lookup function based on the type code. Returns null if the code does not exist.
     *
     * @param encryptionOptions
     *            the code to lookup
     * @return enumeration value.
     */
    public static EncryptionOptions getEncryptionOptions(int encryptionOptions) {
        if (codeMapping == null) {
            initMapping();
        }

        if (codeMapping.get(encryptionOptions) == null) {
            return UNKNOWN;
        }

        return codeMapping.get(encryptionOptions);
    }

    /**
     * Returns the XBee protocol defined value for this enum
     *
     * @return the XBee enumeration key
     */
    public int getKey() {
        return key;
    }
}
