/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.af;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the Z-Stack Enumeration <b>AfDataOptions</b>.
 * <p>
 * Options applied when sending frames
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public enum AfDataOptions {

    /**
     * Set this bit to request APS acknowledgement for this packet
     */
    AF_ACK_REQUEST(0x0010),

    /**
     * Set this bit to force route discovery if a routing table entry doesn�t exist
     */
    AF_DISCV_ROUTE(0x0020),

    /**
     * Set this bit to enable APS security for this packet.
     */
    AF_EN_SECURITY(0x0040),

    /**
     * Skip routing.
     */
    AF_NO_ROUTING(0x0080);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, AfDataOptions> codeMapping;

    private int key;

    static {
        codeMapping = new HashMap<Integer, AfDataOptions>();
        for (AfDataOptions s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    private AfDataOptions(int key) {
        this.key = key;
    }

    /**
     * Lookup function based on the type code. Returns null if the code does not exist.
     *
     * @param code the code to lookup
     * @return enumeration value of the alarm type.
     */
    public static AfDataOptions valueOf(int code) {

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
