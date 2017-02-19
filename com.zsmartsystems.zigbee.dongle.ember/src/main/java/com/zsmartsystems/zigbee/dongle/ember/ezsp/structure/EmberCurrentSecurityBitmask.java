/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the Ember Enumeration <b>EmberCurrentSecurityBitmask</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public enum EmberCurrentSecurityBitmask {
    /**
     * Default unknown value
     */
    UNKNOWN(-1),

    /**
     * This denotes that the device is running in a network with ZigBee Standard Security.
     */
    EMBER_STANDARD_SECURITY_MODE(0),

    /**
     * This denotes that the device is running in a network with ZigBee High Security.
     */
    EMBER_HIGH_SECURITY_MODE(1),

    /**
     * This denotes that the device is running in a network without a centralized Trust Center.
     */
    EMBER_DISTRIBUTED_TRUST_CENTER_MODE(2),

    /**
     * This denotes that the device has a Global Link Key. The Trust Center Link Key is the same across
     * multiple nodes.
     */
    EMBER_GLOBAL_LINK_KEY(4),

    /**
     * This denotes that the node has a Trust Center Link Key.
     */
    EMBER_HAVE_TRUST_CENTER_LINK_KEY(16),

    /**
     * This denotes that the Trust Center is using a Hashed Link Key.
     */
    EMBER_TRUST_CENTER_USES_HASHED_LINK_KEY(132);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EmberCurrentSecurityBitmask> codeMapping;

    private int key;

    private EmberCurrentSecurityBitmask(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, EmberCurrentSecurityBitmask>();
        for (EmberCurrentSecurityBitmask s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    /**
     * Lookup function based on the EmberStatus type code. Returns null if the
     * code does not exist.
     *
     * @param i
     *            the code to lookup
     * @return enumeration value of the alarm type.
     */
    public static EmberCurrentSecurityBitmask getEmberCurrentSecurityBitmask(int i) {
        if (codeMapping == null) {
            initMapping();
        }

        if (codeMapping.get(i) == null) {
            return UNKNOWN;
        }

        return codeMapping.get(i);
    }

    /**
     * Returns the EZSP protocol defined value for this enum
     *
     * @return the EZSP protocol key
     */
    public int getKey() {
        return key;
    }
}
