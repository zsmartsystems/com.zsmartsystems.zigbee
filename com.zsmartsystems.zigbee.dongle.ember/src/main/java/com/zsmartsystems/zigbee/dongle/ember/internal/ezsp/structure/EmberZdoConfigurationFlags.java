/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the Ember Enumeration <b>EmberZdoConfigurationFlags</b>.
 * <p>
 * This is a bitmask that controls which incoming ZDO request messages are passed to the
 * application.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public enum EmberZdoConfigurationFlags {
    /**
     * Default unknown value
     */
    UNKNOWN(-1),

    /**
     * Set this flag in order to receive supported ZDO request messages via the
     * incomingMessageHandler callback. A supported ZDO request is one that is handled by the
     * EmberZNet stack. The stack will continue to handle the request and send the appropriate ZDO
     * response even if this configuration option is enabled.
     */
    EMBER_APP_RECEIVES_SUPPORTED_ZDO_REQUESTS(0x0001),

    /**
     * Set this flag in order to receive unsupported ZDO request messages via the
     * incomingMessageHandler callback. An unsupported ZDO request is one that is not handled by
     * the EmberZNet stack, other than to send a 'not supported' ZDO response. If this
     * configuration option is enabled, the stack will no longer send any ZDO response, and it is the
     * application's responsibility to do so.
     */
    EMBER_APP_HANDLES_UNSUPPORTED_ZDO_REQUESTS(0x0002),

    /**
     * Set this flag in order to receive the following ZDO request messages via the
     * incomingMessageHandler callback: SIMPLE_DESCRIPTOR_REQUEST,
     * MATCH_DESCRIPTORS_REQUEST, and ACTIVE_ENDPOINTS_REQUEST. If this configuration
     * option is enabled, the stack will no longer send any ZDO response for these requests, and it is
     * the application's responsibility to do so.
     */
    EMBER_APP_HANDLES_ZDO_ENDPOINT_REQUESTS(0x0004),

    /**
     * Set this flag in order to receive the following ZDO request messages via the
     * incomingMessageHandler callback: BINDING_TABLE_REQUEST, BIND_REQUEST, and
     * UNBIND_REQUEST. If this configuration option is enabled, the stack will no longer send any
     * ZDO response for these requests, and it is the application's responsibility to do so.
     */
    EMBER_APP_HANDLES_ZDO_BINDING_REQUESTS(0x0008);

    /**
     * A mapping between the integer code and its corresponding type to
     * facilitate lookup by code.
     */
    private static Map<Integer, EmberZdoConfigurationFlags> codeMapping;

    private int key;

    static {
        codeMapping = new HashMap<Integer, EmberZdoConfigurationFlags>();
        for (EmberZdoConfigurationFlags s : values()) {
            codeMapping.put(s.key, s);
        }
    }

    private EmberZdoConfigurationFlags(int key) {
        this.key = key;
    }

    /**
     * Lookup function based on the EmberStatus type code. Returns null if the
     * code does not exist.
     *
     * @param code the code to lookup
     * @return enumeration value of the alarm type.
     */
    public static EmberZdoConfigurationFlags getEmberZdoConfigurationFlags(int code) {
        if (codeMapping.get(code) == null) {
            return UNKNOWN;
        }

        return codeMapping.get(code);
    }

    /**
     * Returns the EZSP protocol defined value for this enumeration.
     *
     * @return the EZSP protocol key
     */
    public int getKey() {
        return key;
    }
}
