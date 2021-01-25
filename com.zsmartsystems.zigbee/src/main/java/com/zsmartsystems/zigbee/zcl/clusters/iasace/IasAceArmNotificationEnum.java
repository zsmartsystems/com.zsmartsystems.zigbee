/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
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
 * IAS ACE Arm Notification value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum IasAceArmNotificationEnum {

    /**
     * All Zones Disarmed, 0, 0x0000
     */
    ALL_ZONES_DISARMED(0x0000),

    /**
     * Only Day Home Zones Armed, 1, 0x0001
     */
    ONLY_DAY_HOME_ZONES_ARMED(0x0001),

    /**
     * Only Night Sleep Zones Armed, 2, 0x0002
     */
    ONLY_NIGHT_SLEEP_ZONES_ARMED(0x0002),

    /**
     * All Zones Armed, 3, 0x0003
     */
    ALL_ZONES_ARMED(0x0003),

    /**
     * Invalid Arm Disarm Code, 4, 0x0004
     */
    INVALID_ARM_DISARM_CODE(0x0004),

    /**
     * Not Ready To Arm, 5, 0x0005
     */
    NOT_READY_TO_ARM(0x0005),

    /**
     * Already Disarmed, 6, 0x0006
     */
    ALREADY_DISARMED(0x0006);

    /**
     * A mapping between the integer code and its corresponding IasAceArmNotificationEnum type to facilitate lookup by value.
     */
    private static Map<Integer, IasAceArmNotificationEnum> idMap;

    static {
        idMap = new HashMap<Integer, IasAceArmNotificationEnum>();
        for (IasAceArmNotificationEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private IasAceArmNotificationEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static IasAceArmNotificationEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
