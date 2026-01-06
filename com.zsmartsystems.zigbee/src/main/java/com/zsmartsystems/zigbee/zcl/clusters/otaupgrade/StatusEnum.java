/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.otaupgrade;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Status value enumeration.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-15T11:25:03Z")
public enum StatusEnum {

    /**
     * Success, 0, 0x0000
     */
    SUCCESS(0x0000),

    /**
     * Failure, 1, 0x0001
     */
    FAILURE(0x0001),

    /**
     * Request_Denied, 112, 0x0070
     */
    REQUEST_DENIED(0x0070),

    /**
     * Multiple_Request_Not_Allowed, 113, 0x0071
     */
    MULTIPLE_REQUEST_NOT_ALLOWED(0x0071),

    /**
     * Indication_Redirection_To_Ap, 114, 0x0072
     */
    INDICATION_REDIRECTION_TO_AP(0x0072),

    /**
     * Preference_Denied, 115, 0x0073
     */
    PREFERENCE_DENIED(0x0073),

    /**
     * Preference_Ignored, 116, 0x0074
     */
    PREFERENCE_IGNORED(0x0074),

    /**
     * Not_Authorized, 126, 0x007E
     */
    NOT_AUTHORIZED(0x007E),

    /**
     * Reserved_Field_Not_Zero, 127, 0x007F
     */
    RESERVED_FIELD_NOT_ZERO(0x007F),

    /**
     * Malformed_Command, 128, 0x0080
     */
    MALFORMED_COMMAND(0x0080),

    /**
     * Unsup_Cluster_Command, 129, 0x0081
     */
    UNSUP_CLUSTER_COMMAND(0x0081),

    /**
     * Unsup_General_Command, 130, 0x0082
     */
    UNSUP_GENERAL_COMMAND(0x0082),

    /**
     * Unsup_Manuf_Cluster_Command, 131, 0x0083
     */
    UNSUP_MANUF_CLUSTER_COMMAND(0x0083),

    /**
     * Unsup_Manuf_General_Command, 132, 0x0084
     */
    UNSUP_MANUF_GENERAL_COMMAND(0x0084),

    /**
     * Invalid_Field, 133, 0x0085
     */
    INVALID_FIELD(0x0085),

    /**
     * Unsupported_Attribute, 134, 0x0086
     */
    UNSUPPORTED_ATTRIBUTE(0x0086),

    /**
     * Invalid_Value, 135, 0x0087
     */
    INVALID_VALUE(0x0087),

    /**
     * Read_Only, 136, 0x0088
     */
    READ_ONLY(0x0088),

    /**
     * Insufficient_Space, 137, 0x0089
     */
    INSUFFICIENT_SPACE(0x0089),

    /**
     * Duplicate_Exists, 138, 0x008A
     */
    DUPLICATE_EXISTS(0x008A),

    /**
     * Not_Found, 139, 0x008B
     */
    NOT_FOUND(0x008B),

    /**
     * Unreportable_Attribute, 140, 0x008C
     */
    UNREPORTABLE_ATTRIBUTE(0x008C),

    /**
     * Invalid_Data_Type, 141, 0x008D
     */
    INVALID_DATA_TYPE(0x008D),

    /**
     * Invalid_Selector, 142, 0x008E
     */
    INVALID_SELECTOR(0x008E),

    /**
     * Write_Only, 143, 0x008F
     */
    WRITE_ONLY(0x008F),

    /**
     * Inconsistent_Startup_State, 144, 0x0090
     */
    INCONSISTENT_STARTUP_STATE(0x0090),

    /**
     * Defined_Out_Of_Band, 145, 0x0091
     */
    DEFINED_OUT_OF_BAND(0x0091),

    /**
     * Inconsistent, 146, 0x0092
     */
    INCONSISTENT(0x0092),

    /**
     * Action_Denied, 147, 0x0093
     */
    ACTION_DENIED(0x0093),

    /**
     * Timeout, 148, 0x0094
     */
    TIMEOUT(0x0094),

    /**
     * Abort, 149, 0x0095
     */
    ABORT(0x0095),

    /**
     * Invalid_Image, 150, 0x0096
     */
    INVALID_IMAGE(0x0096),

    /**
     * Wait_For_Data, 151, 0x0097
     */
    WAIT_FOR_DATA(0x0097),

    /**
     * No_Image_Available, 152, 0x0098
     */
    NO_IMAGE_AVAILABLE(0x0098),

    /**
     * Require_More_Image, 153, 0x0099
     */
    REQUIRE_MORE_IMAGE(0x0099),

    /**
     * Hardware_Failure, 192, 0x00C0
     */
    HARDWARE_FAILURE(0x00C0),

    /**
     * Software_Failure, 193, 0x00C1
     */
    SOFTWARE_FAILURE(0x00C1),

    /**
     * Calibration_Error, 194, 0x00C2
     */
    CALIBRATION_ERROR(0x00C2),

    /**
     * Unsupported_Cluster, 195, 0x00C3
     */
    UNSUPPORTED_CLUSTER(0x00C3);

    /**
     * A mapping between the integer code and its corresponding StatusEnum type to facilitate lookup by value.
     */
    private static Map<Integer, StatusEnum> idMap;

    static {
        idMap = new HashMap<Integer, StatusEnum>();
        for (StatusEnum enumValue : values()) {
            idMap.put(enumValue.key, enumValue);
        }
    }

    private final int key;

    private StatusEnum(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static StatusEnum getByValue(final int value) {
        return idMap.get(value);
    }
}
