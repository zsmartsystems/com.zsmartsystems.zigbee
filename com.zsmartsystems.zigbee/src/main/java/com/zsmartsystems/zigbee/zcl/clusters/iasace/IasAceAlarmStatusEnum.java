/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 * IAS ACE Alarm Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum IasAceAlarmStatusEnum {

    /**
     * No Alarm
     */
    NO_ALARM(0x0000),

    /**
     * Burglar
     */
    BURGLAR(0x0001),

    /**
     * Fire
     */
    FIRE(0x0002),

    /**
     * Emergency
     */
    EMERGENCY(0x0003),

    /**
     * Police Panic
     */
    POLICE_PANIC(0x0004),

    /**
     * Fire Panic
     */
    FIRE_PANIC(0x0005),

    /**
     * Emergency Panic
     */
    EMERGENCY_PANIC(0x0006);

    /**
     * A mapping between the integer code and its corresponding IasAceAlarmStatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, IasAceAlarmStatusEnum> idMap;

    static {
        idMap = new HashMap<Integer, IasAceAlarmStatusEnum>();
        for (IasAceAlarmStatusEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private IasAceAlarmStatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static IasAceAlarmStatusEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
