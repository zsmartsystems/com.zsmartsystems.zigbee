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
 * Enumeration of IAS ACE attribute Arm Mode options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-04-10T07:28:44Z")
public enum ArmModeEnum {
    DISARM(0x0000),
    ARM_DAY(0x0001),
    ARM_NIGHT(0x0002),
    ARM_ALL_ZONES(0x0003);

    /**
     * A mapping between the integer code and its corresponding ArmModeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, ArmModeEnum> idMap;

    static {
        idMap = new HashMap<Integer, ArmModeEnum>();
        for (ArmModeEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    ArmModeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ArmModeEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
