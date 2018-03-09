/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the ZigBee stack types
 *
 * @author Chris Jackson
 */
public enum ZigBeeStackType {
    UNKNOWN(0xFF),

    /**
     * ZigBee 2006
     */
    ZIGBEE_2006(0x00),
    /**
     * ZigBee 2007
     */
    ZIGBEE_2007(0x01),
    /**
     * ZigBee Pro
     */
    ZIGBEE_PRO(0x02),
    /**
     * ZigBee IP
     */
    ZIGBEE_IP(0x03);

    /**
     * The stack type value
     */
    private final int stackId;

    /**
     * Map containing the link of stack type value to the enum
     */
    private static Map<Integer, ZigBeeStackType> map = null;

    private ZigBeeStackType(int stackId) {
        this.stackId = stackId;
    }

    /**
     * Get a {@link ZigBeeStackType} from an integer
     *
     * @param stackTypeValue integer value defining the stack type
     * @return {@link ZigBeeStackType} or {@link #UNKNOWN} if the value could not be converted
     */
    public static ZigBeeStackType getStackType(int stackTypeValue) {
        if (map == null) {
            map = new HashMap<Integer, ZigBeeStackType>();
            for (ZigBeeStackType stackType : values()) {
                map.put(stackType.stackId, stackType);
            }

        }
        if (map.get(stackTypeValue) == null) {
            return UNKNOWN;
        }
        return map.get(stackTypeValue);
    }

    /**
     * Gets the integer value for the stack type
     *
     * @return int defining the stack type
     */
    public int getId() {
        return stackId;
    }
}