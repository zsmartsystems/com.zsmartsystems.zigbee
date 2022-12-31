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
 * IAS ACE Panel Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum IasAcePanelStatusEnum {

    /**
     * Panel Disarmed, 0, 0x0000
     */
    PANEL_DISARMED(0x0000),

    /**
     * Armed Stay, 1, 0x0001
     */
    ARMED_STAY(0x0001),

    /**
     * Armed Night, 2, 0x0002
     */
    ARMED_NIGHT(0x0002),

    /**
     * Armed Away, 3, 0x0003
     */
    ARMED_AWAY(0x0003),

    /**
     * Exit Delay, 4, 0x0004
     */
    EXIT_DELAY(0x0004),

    /**
     * Entry Delay, 5, 0x0005
     */
    ENTRY_DELAY(0x0005),

    /**
     * Not Ready To Arm, 6, 0x0006
     */
    NOT_READY_TO_ARM(0x0006),

    /**
     * In Alarm, 7, 0x0007
     */
    IN_ALARM(0x0007),

    /**
     * Arming Stay, 8, 0x0008
     */
    ARMING_STAY(0x0008),

    /**
     * Arming Night, 9, 0x0009
     */
    ARMING_NIGHT(0x0009),

    /**
     * Arming Away, 10, 0x000A
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
