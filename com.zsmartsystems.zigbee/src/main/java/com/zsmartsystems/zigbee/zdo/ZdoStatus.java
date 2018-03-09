/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the ZDO status values and textual descriptions
 * <p>
 * From ZigBee Specification 2.4.5 ZDP Enumeration Description
 *
 * @author Chris Jackson
 */
public enum ZdoStatus {
    UNKNOWN(0xFF, "Unknown Response"),

    /**
     * Completed successfully
     */
    SUCCESS(0x00, "Success"),

    /**
     * The supplied request type was invalid.
     */
    INV_REQUESTTYPE(0x80, "The supplied request type was invalid."),
    /**
     * The requested device did not exist on a device following a child descriptor request to a parent.
     */
    DEVICE_NOT_FOUND(0x81,
            "The requested device did not exist on a device following a child descriptor request to a parent."),
    /**
     * The supplied endpoint was equal to 0x00 or between 0xf1 and 0xff.
     */
    INVALID_EP(0x82, " The supplied endpoint was equal to 0x00 or between 0xf1 and 0xff."),
    /**
     * The requested endpoint is not described by a simple descriptor.
     */
    NOT_ACTIVE(0x83, "The requested endpoint is not described by a simple descriptor."),
    /**
     * The requested optional feature is not supported on the target device.
     */
    NOT_SUPPORTED(0x84, "The requested optional feature is not supported on the target device."),
    /**
     * A timeout has occurred with the requested operation.
     */
    TIMEOUT(0x85, "A timeout has occurred with the requested operation."),
    /**
     * The end device bind request was unsuccessful due to a failure to match any suitable clusters.
     */
    NO_MATCH(0x86, "The end device bind request was unsuccessful due to a failure to match any suitable clusters."),
    /**
     * The unbind request was unsuccessful due to the coordinator or source device not having an entry in its binding
     * table to unbind.
     */
    NO_ENTRY(0x88,
            "The unbind request was unsuccessful due to the coordinator or source device not having an entry in its binding table to unbind."),
    /**
     * A child descriptor was not available following a discovery request to a parent.
     */
    NO_DESCRIPTOR(0x89, "A child descriptor was not available following a discovery request to a parent."),
    /**
     * The device does not have storage space to support the requested operation.
     */
    INSUFFICIENT_SPACE(0x8A, "The device does not have storage space to support the requested operation."),
    /**
     * The device is not in the proper state to support the requested operation.
     */
    NOT_PERMITTED(0x8B, " The device is not in the proper state to support the requested operation."),
    /**
     * The device does not have table space to support the operation.
     */
    TABLE_FULL(0x8C, "The device does not have table space to support the operation."),
    /**
     * The permissions configuration table on the target indicates that the request is not authorized from this device.
     */
    NOT_AUTHORIZED(0x8D,
            "The permissions configuration table on the target indicates that the request is not authorized from this device.");

    private final int id;
    private final String description;
    private static Map<Integer, ZdoStatus> map = null;

    private ZdoStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static ZdoStatus getStatus(int b) {
        if (map == null) {
            map = new HashMap<Integer, ZdoStatus>();
            for (ZdoStatus s : values()) {
                map.put(s.id, s);
            }

        }
        if (map.get(b) == null) {
            return UNKNOWN;
        }
        return map.get(b);
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
