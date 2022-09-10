/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.appcnf;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the Z-Stack Enumeration <b>ZstackAuthenticationOption</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public enum ZstackAuthenticationOption {

    /**
     * The device has not been authenticated
     */
    NOT_AUTHENTICATED(0x0000),

    /**
     * The device has been authenticated using CBKE
     */
    AUTHENTICATED_CBCK(0x0001),

    /**
     * The device has been authenticated using EA
     */
    AUTHENTICATED_EA(0x0002);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, ZstackAuthenticationOption> codeMapping;

    private int key;

    static {
        codeMapping = new HashMap<Integer, ZstackAuthenticationOption>();
        for (ZstackAuthenticationOption s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    private ZstackAuthenticationOption(int key) {
        this.key = key;
    }

    /**
     * Lookup function based on the type code. Returns null if the code does not exist.
     *
     * @param code the code to lookup
     * @return enumeration value of the alarm type.
     */
    public static ZstackAuthenticationOption valueOf(int code) {

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
