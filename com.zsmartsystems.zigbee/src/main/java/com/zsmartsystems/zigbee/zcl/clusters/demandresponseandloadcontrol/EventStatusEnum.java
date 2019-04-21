/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Event Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum EventStatusEnum {

    /**
     * Load Control Event Command Rx
     */
    LOAD_CONTROL_EVENT_COMMAND_RX(0x0001),

    /**
     * Event Started
     */
    EVENT_STARTED(0x0002),

    /**
     * Event Completed
     */
    EVENT_COMPLETED(0x0003),

    /**
     * User Has Choose To Opt Out
     */
    USER_HAS_CHOOSE_TO_OPT_OUT(0x0004),

    /**
     * User Has Choose To Opt In
     */
    USER_HAS_CHOOSE_TO_OPT_IN(0x0005),

    /**
     * The Event Has Been Canceled
     */
    THE_EVENT_HAS_BEEN_CANCELED(0x0006),

    /**
     * The Event Has Been Superseded
     */
    THE_EVENT_HAS_BEEN_SUPERSEDED(0x0007),

    /**
     * Event Partially Completed With User Opt Out
     */
    EVENT_PARTIALLY_COMPLETED_WITH_USER_OPT_OUT(0x0008),

    /**
     * Event Partially Completed Due To User Opt In
     */
    EVENT_PARTIALLY_COMPLETED_DUE_TO_USER_OPT_IN(0x0009),

    /**
     * Event Completed No User Participation Previous Opt Out
     */
    EVENT_COMPLETED_NO_USER_PARTICIPATION_PREVIOUS_OPT_OUT(0x000A),

    /**
     * Invalid Opt Out
     */
    INVALID_OPT_OUT(0x00F6),

    /**
     * Event Not Found
     */
    EVENT_NOT_FOUND(0x00F7),

    /**
     * Rejected Invalid Cancel Command
     */
    REJECTED_INVALID_CANCEL_COMMAND(0x00F8),

    /**
     * Rejected Invalid Cancel Command Invalid Effective Time
     */
    REJECTED_INVALID_CANCEL_COMMAND_INVALID_EFFECTIVE_TIME(0x00F9),

    /**
     * Rejected Event Expired
     */
    REJECTED_EVENT_EXPIRED(0x00FB),

    /**
     * Rejected Invalid Cancel Undefined Event
     */
    REJECTED_INVALID_CANCEL_UNDEFINED_EVENT(0x00FD),

    /**
     * Load Control Event Command Rejected
     */
    LOAD_CONTROL_EVENT_COMMAND_REJECTED(0x00FE);

    /**
     * A mapping between the integer code and its corresponding EventStatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, EventStatusEnum> idMap;

    static {
        idMap = new HashMap<Integer, EventStatusEnum>();
        for (EventStatusEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private EventStatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static EventStatusEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
