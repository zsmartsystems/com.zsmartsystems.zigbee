/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.messaging;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Messaging Control Mask value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum MessagingControlMaskBitmap {

    /**
     * Trans Mechanism
     */
    TRANS_MECHANISM(0x0003),

    /**
     * Message Urgency
     */
    MESSAGE_URGENCY(0x000C),

    /**
     * Enhanced Confirmation Request
     */
    ENHANCED_CONFIRMATION_REQUEST(0x0020),

    /**
     * Message Confirmation
     */
    MESSAGE_CONFIRMATION(0x0080);

    /**
     * A mapping between the integer code and its corresponding MessagingControlMaskBitmap type to facilitate lookup by value.
     */
    private static Map<Integer, MessagingControlMaskBitmap> idMap;

    static {
        idMap = new HashMap<Integer, MessagingControlMaskBitmap>();
        for (MessagingControlMaskBitmap enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private MessagingControlMaskBitmap(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static MessagingControlMaskBitmap getByValue(final int value) {
        return idMap.get(value);
    }
}
