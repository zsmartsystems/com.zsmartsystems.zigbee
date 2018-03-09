/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the ConBee Enumeration for <b>Network Parameters</b>.
 *
 * @author Chris Jackson
 */
public enum ConBeeNetworkParameter {

    UNKNOWN(-1),
    MAC_ADDRESS(1),
    NWK_PANID(5),
    NWK_ADDRESS(7),
    NWK_EXTENDED_PANID(8),
    DEVICE_TYPE(9),
    CHANNEL_MASK(10),
    APS_EXTENDED_PANID(11),
    TRUST_CENTRE_ADDRESS(14),
    SECURITY_MODE(16),
    NETWORK_KEY(24),
    CURRENT_CHANNEL(28),
    NWK_UPDATE_ID(36);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, ConBeeNetworkParameter> codeMapping;

    private int key;

    private ConBeeNetworkParameter(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, ConBeeNetworkParameter>();
        for (ConBeeNetworkParameter s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    /**
     * Lookup function based on the type code. Returns null if the
     * code does not exist.
     *
     * @param i
     *            the code to lookup
     * @return enumeration value of the alarm type.
     */
    public static ConBeeNetworkParameter getParameterType(int param) {
        if (codeMapping == null) {
            initMapping();
        }

        if (codeMapping.get(param) == null) {
            return UNKNOWN;
        }

        return codeMapping.get(param);
    }

    /**
     * Returns the value for this enum
     *
     * @return the key
     */
    public int getKey() {
        return key;
    }
}
