/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iaszone;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Enumeration of IAS Zone attribute Enroll response code options.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public enum EnrollResponseCodeEnum {
    SUCCESS(0x0000),
    NOT_SUPPORTED(0x0001),
    NO_ENROLL_PERMIT(0x0002),
    TOO_MANY_ZONES(0x0003);

    /**
     * A mapping between the integer code and its corresponding EnrollResponseCodeEnum type to facilitate lookup by value.
     */
    private static Map<Integer, EnrollResponseCodeEnum> idMap;

    private final int key;

    EnrollResponseCodeEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static EnrollResponseCodeEnum getByValue(final int value) {
        if (idMap == null) {
            idMap = new HashMap<Integer, EnrollResponseCodeEnum>();
            for (EnrollResponseCodeEnum enumValue : values()) {
                idMap.put(enumValue.key, enumValue);
            }
        }
        return idMap.get(value);
    }
}
