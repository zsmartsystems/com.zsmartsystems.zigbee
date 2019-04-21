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
 * IAS ACE Panel Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum IasAcePanelStatusEnum {

    /**
     * Panel Disarmed
     */
    PANEL_DISARMED(0x0000),

    /**
     * Armed Stay
     */
    ARMED_STAY(0x0001),

    /**
     * Armed Night
     */
    ARMED_NIGHT(0x0002),

    /**
     * Armed Away
     */
    ARMED_AWAY(0x0003),

    /**
     * Exit Delay
     */
    EXIT_DELAY(0x0004),

    /**
     * Entry Delay
     */
    ENTRY_DELAY(0x0005),

    /**
     * Not Ready To Arm
     */
    NOT_READY_TO_ARM(0x0006),

    /**
     * In Alarm
     */
    IN_ALARM(0x0007),

    /**
     * Arming Stay
     */
    ARMING_STAY(0x0008),

    /**
     * Arming Night
     */
    ARMING_NIGHT(0x0009),

    /**
     * Arming Away
     */
    ARMING_AWAY(0x000A);

    /**
     * A mapping between the integer code and its corresponding IasAcePanelStatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, IasAcePanelStatusEnum> idMap;

    static {
        idMap = new HashMap<Integer, IasAcePanelStatusEnum>();
        for (IasAcePanelStatusEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private IasAcePanelStatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static IasAcePanelStatusEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
