/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.cc2531.network.packet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Response statuses.
 *
 * @author <a href="mailto:tommi.s.e.laukkanen@gmail.com">Tommi S.E. Laukkanen</a>
 */
public enum ResponseStatus {
    SUCCESS(0x00),
    FAILURE(0x01),
    INVALIDPARAMETER(0x02),
    INVALID_TASK(0x03),
    MSG_BUFFER_NOT_AVAIL(0x04),
    INVALID_MSG_POINTER(0x05),
    INVALID_EVENT_ID(0x06),
    INVALID_INTERRUPT_ID(0x07),
    NO_TIMER_AVAIL(0x08),
    NV_ITEM_UNINIT(0x09),
    NV_OPER_FAILED(0x0A),
    INVALID_MEM_SIZE(0x0B),
    NV_BAD_ITEM_LEN(0x0C),
    Z_MEM_ERROR(0x10),
    Z_BUFFER_FULL(0x11),
    Z_UNSUPPORTED_MODE(0x12),
    Z_MAC_MEM_ERROR(0x13),
    Z_SAPI_IN_PROGRESS(0x20),
    Z_SAPI_TIMEOUT(0x21),
    Z_SAPI_INIT(0x22),
    ZDP_INVALID_REQTYPE(0x80),
    ZDP_DEVICE_NOT_FOUND(0x81),
    ZDP_INVALID_EP(0x82),
    ZDP_NOT_ACTIVE(0x83),
    ZDP_NOT_SUPPORTED(0x84),
    ZDP_TIMEOUT(0x85),
    ZDP_NO_MATCH(0x86),
    ZDP_NO_ENTRY(0x88),
    ZDP_NO_DESCRIPTOR(0x89),
    ZDP_INSUFFICIENT_SPACE(0x8a),
    ZDP_NOT_PERMITTED(0x8b),
    ZDP_TABLE_FULL(0x8c),
    ZDP_NOT_AUTHORIZED(0x8d),
    Z_APS_FAIL(0xb1),
    Z_APS_TABLE_FULL(0xb2),
    Z_APS_ILLEGAL_REQUEST(0xb3),
    Z_APS_INVALID_BINDING(0xb4),
    Z_APS_UNSUPPORTED_ATTRIB(0xb5),
    Z_APS_NOT_SUPPORTED(0xb6),
    Z_APS_NO_ACK(0xb7),
    Z_APS_DUPLICATE_ENTRY(0xb8),
    Z_APS_NO_BOUND_DEVICE(0xb9),
    Z_APS_NOT_ALLOWED(0xba),
    Z_APS_NOT_AUTHENTICATED(0xbb),
    Z_SEC_NO_KEY(0xa1),
    Z_SEC_OLD_FRM_COUNT(0xa2),
    Z_SEC_MAX_FRM_COUNT(0xa3),
    Z_SEC_CCM_FAIL(0xa4),
    Z_NWK_INVALID_PARAM(0xc1),
    Z_NWK_INVALID_REQUEST(0xc2),
    Z_NWK_NOT_PERMITTED(0xc3),
    Z_NWK_STARTUP_FAILURE(0xc4),
    Z_NWK_ALREADY_PRESENT(0xc5),
    Z_NWK_SYNC_FAILURE(0xc6),
    Z_NWK_TABLE_FULL(0xc7),
    Z_NWK_UNKNOWN_DEVICE(0xc8),
    Z_NWK_UNSUPPORTED_ATTRIBUTE(0xc9),
    Z_NWK_NO_NETWORKS(0xca),
    Z_NWK_LEAVE_UNCONFIRMED(0xcb),
    Z_NWK_NO_ACK(0xcc),
    Z_NWK_NO_ROUTE(0xcd),
    Z_MAC_BEACON_LOSS(0xe0),
    Z_MAC_CHANNEL_ACCESS_FAILURE(0xe1),
    Z_MAC_DENIED(0xe2),
    Z_MAC_DISABLE_TRX_FAILURE(0xe3),
    Z_MAC_FAILED_SECURITY_CHECK(0xe4),
    Z_MAC_FRAME_TOO_LONG(0xe5),
    Z_MAC_INVALID_GTS(0xe6),
    Z_MAC_INVALID_HANDLE(0xe7),
    Z_MAC_INVALID_PARAMETER(0xe8),
    Z_MAC_NO_ACK(0xe9),
    Z_MAC_NO_BEACON(0xea),
    Z_MAC_NO_DATA(0xeb),
    Z_MAC_NO_SHORT_ADDR(0xec),
    Z_MAC_OUT_OF_CAP(0xed),
    Z_MAC_PANID_CONFLICT(0xee),
    Z_MAC_REALIGNMENT(0xef),
    Z_MAC_TRANSACTION_EXPIRED(0xf0),
    Z_MAC_TRANSACTION_OVER_FLOW(0xf1),
    Z_MAC_TX_ACTIVE(0xf2),
    Z_MAC_UN_AVAILABLE_KEY(0xf3),
    Z_MAC_UNSUPPORTED_ATTRIBUTE(0xf4),
    Z_MAC_UNSUPPORTED(0xf5),
    Z_MAC_SRC_MATCH_INVALID_INDEX(0xff),
    UNKNOWN(-1);

    /**
     * The logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(ResponseStatus.class);

    private static Map<Integer, ResponseStatus> mapping = new HashMap<Integer, ResponseStatus>();
    private int value;

    private ResponseStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ResponseStatus getStatus(int value) {
        if (!mapping.containsKey(value)) {
            logger.warn("Unknown status value: " + value);
            return mapping.get(-1);
        }
        return mapping.get(value);
    }

    static {
        for (ResponseStatus status : ResponseStatus.values()) {
            mapping.put(status.value, status);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "(" + value + ")";
    }
}
