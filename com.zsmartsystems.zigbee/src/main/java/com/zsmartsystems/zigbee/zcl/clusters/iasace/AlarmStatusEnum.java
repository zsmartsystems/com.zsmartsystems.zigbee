/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
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
 * Enumeration of IAS ACE attribute Alarm Status options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-26T16:35:56Z")
public enum AlarmStatusEnum {
    NO_ALARM(0x0000),
    BURGLAR(0x0001),
    FIRE(0x0002),
    EMERGENCY(0x0003),
    POLICE_PANIC(0x0004),
    FIRE_PANIC(0x0005),
    EMERGENCY_PANIC(0x0006);

    /**
     * A mapping between the integer code and its corresponding AlarmStatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, AlarmStatusEnum> idMap;

    private final int key;

    AlarmStatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static AlarmStatusEnum getByValue(final int value) {
        if (idMap == null) {
            idMap = new HashMap<Integer, AlarmStatusEnum>();
            for (AlarmStatusEnum enumValue : values()) {
                idMap.put(enumValue.key, enumValue);
            }
        }
        return idMap.get(value);
    }
}
