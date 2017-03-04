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
 * Class to implement the Ember Enumeration <b>EzspConfigId</b>.
 * <p>
 * Identifies which configuration value to read or change with getConfigurationValue and
 * setConfigurationValue.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public enum EzspConfigId {
    /**
     * Default unknown value
     */
    UNKNOWN(-1),

    /**
     * The number of packet buffers available to the stack. When set to the special value 0xFF, the
     * NCP will allocate all remaining configuration RAM towards packet buffers, such that the
     * resulting count will be the largest whole number of packet buffers that can fit into the
     * available memory.
     */
    EZSP_CONFIG_PACKET_BUFFER_COUNT(0x0001),

    /**
     * Specifies the stack profile.
     */
    EZSP_CONFIG_STACK_PROFILE(0x000C),

    /**
     * The security level used for security at the MAC and network layers. The supported values are 0
     * (no security) and 5 (payload is encrypted and a four-byte MIC is used for authentication).
     */
    EZSP_CONFIG_SECURITY_LEVEL(0x000D),

    /**
     * The maximum number of end device children that a router will support.
     */
    EZSP_CONFIG_MAX_END_DEVICE_CHILDREN(0x0011),

    /**
     * Enables boost power mode and/or the alternate transmitter output.
     */
    EZSP_CONFIG_TX_POWER_MODE(0x0017),

    /**
     * The size of the Key Table used for storing individual link keys (if the device is a Trust
     * Center) or Application Link Keys (if the device is a normal node).
     */
    EZSP_CONFIG_KEY_TABLE_SIZE(0x001E),

    /**
     * This is a bitmask that controls which incoming ZDO request messages are passed to the
     * application. The bits are defined in the EmberZdoConfigurationFlags enumeration. To see
     * if the application is required to send a ZDO response in reply to an incoming message, the
     * application must check the APS options bitfield within the incomingMessageHandler
     * callback to see if the EMBER_APS_OPTION_ZDO_RESPONSE_REQUIRED flag is set.
     */
    EZSP_CONFIG_APPLICATION_ZDO_FLAGS(0x002A),

    /**
     * The number of supported networks.
     */
    EZSP_CONFIG_SUPPORTED_NETWORKS(0x002D);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EzspConfigId> codeMapping;

    private int key;

    private EzspConfigId(int key) {
        this.key = key;
    }

    private static void initMapping() {
        codeMapping = new HashMap<Integer, EzspConfigId>();
        for (EzspConfigId s : values()) {
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
    public static EzspConfigId getEzspConfigId(int i) {
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
