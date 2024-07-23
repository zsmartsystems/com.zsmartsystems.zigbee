/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Enumeration of IAS ACE attribute Panel Status options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-31T12:00:43Z")
public enum PanelStatusEnum {
    PANEL_DISARMED(0x0000),
    ARMED_STAY(0x0001),
    ARMED_NIGHT(0x0002),
    ARMED_AWAY(0x0003),
    EXIT_DELAY(0x0004),
    ENTRY_DELAY(0x0005),
    NOT_READY_TO_ARM(0x0006),
    IN_ALARM(0x0007),
    ARMING_STAY(0x0008),
    ARMING_NIGHT(0x0009),
    ARMING_AWAY(0x000A);

    /**
     * A mapping between the integer code and its corresponding PanelStatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, PanelStatusEnum> idMap;

    static {
        idMap = new HashMap<Integer, PanelStatusEnum>();
        for (PanelStatusEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    PanelStatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static PanelStatusEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
