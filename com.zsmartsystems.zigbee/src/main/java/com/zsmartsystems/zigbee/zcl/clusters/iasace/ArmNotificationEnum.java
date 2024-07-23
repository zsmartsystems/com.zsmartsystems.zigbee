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
 * Enumeration of IAS ACE attribute Arm Notification options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-04-10T07:28:44Z")
public enum ArmNotificationEnum {
    ALL_ZONES_DISARMED(0x0000),
    DAY_ZONES_ARMED(0x0001),
    NIGHT_ZONES_ARMED(0x0002),
    ALL_ZONES_ARMED(0x0003),
    INVALID_ARM_CODE(0x0004),
    NOT_READY_TO_ARM(0x0005),
    ALREADY_DISARMED(0x0006);

    /**
     * A mapping between the integer code and its corresponding ArmNotificationEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ArmNotificationEnum> idMap;

    static {
        idMap = new HashMap<Integer, ArmNotificationEnum>();
        for (ArmNotificationEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    ArmNotificationEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ArmNotificationEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
