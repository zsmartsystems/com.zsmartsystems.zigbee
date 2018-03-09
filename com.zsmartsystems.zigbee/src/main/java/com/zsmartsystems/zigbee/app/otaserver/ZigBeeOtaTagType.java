/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.otaserver;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the ZigBee OTA Image File Tag types
 *
 * @author Chris Jackson
 */
public enum ZigBeeOtaTagType {
    UNKNOWN(0xFFFF),

    /**
     * Upgrade Image
     */
    UPGRADE_IMAGE(0x0000),
    /**
     * ECDSA Signature
     */
    ECSDA_SIGNATURE(0x0001),
    /**
     * ECDSA Signing Certificate
     */
    ECSDA_CERTIFICATE(0x0002),
    /**
     * Image Integrity Code
     */
    INTEGRITY_CODE(0x0003);

    private final int tagId;
    private static Map<Integer, ZigBeeOtaTagType> map = null;

    private ZigBeeOtaTagType(int tagId) {
        this.tagId = tagId;
    }

    /**
     * Gets the {@link ZigBeeOtaTagType} given the integer value
     *
     * @param tagTypeValue the integer defining the tag type
     * @return the {@link ZigBeeOtaTagType} or {@link #UNKNOWN}
     */
    public static ZigBeeOtaTagType getTagType(int tagTypeValue) {
        if (map == null) {
            map = new HashMap<Integer, ZigBeeOtaTagType>();
            for (ZigBeeOtaTagType tagType : values()) {
                map.put(tagType.tagId, tagType);
            }

        }
        if (map.get(tagTypeValue) == null) {
            return UNKNOWN;
        }
        return map.get(tagTypeValue);
    }

    /**
     * Gets the integer ID for this tag type
     *
     * @return the integer ID
     */
    public int getId() {
        return tagId;
    }
}