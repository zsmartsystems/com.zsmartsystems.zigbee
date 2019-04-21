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
 * IAS ACE Arm Notification value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum IasAceArmNotificationEnum {

    /**
     * All Zones Disarmed
     */
    ALL_ZONES_DISARMED(0x0000),

    /**
     * Only Day Home Zones Armed
     */
    ONLY_DAY_HOME_ZONES_ARMED(0x0001),

    /**
     * Only Night Sleep Zones Armed
     */
    ONLY_NIGHT_SLEEP_ZONES_ARMED(0x0002),

    /**
     * All Zones Armed
     */
    ALL_ZONES_ARMED(0x0003),

    /**
     * Invalid Arm Disarm Code
     */
    INVALID_ARM_DISARM_CODE(0x0004),

    /**
     * Not Ready To Arm
     */
    NOT_READY_TO_ARM(0x0005),

    /**
     * Already Disarmed
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
