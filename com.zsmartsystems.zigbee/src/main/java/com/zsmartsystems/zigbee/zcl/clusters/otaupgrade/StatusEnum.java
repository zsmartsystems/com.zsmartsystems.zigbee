/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:28:08Z")
public enum StatusEnum {

    /**
     * Success
     */
    SUCCESS(0x0000),

    /**
     * Failure
     */
    FAILURE(0x0001),

    /**
     * Request_Denied
     */
    REQUEST_DENIED(0x0070),

    /**
     * Multiple_Request_Not_Allowed
     */
    MULTIPLE_REQUEST_NOT_ALLOWED(0x0071),

    /**
     * Indication_Redirection_To_Ap
     */
    INDICATION_REDIRECTION_TO_AP(0x0072),

    /**
     * Preference_Denied
     */
    PREFERENCE_DENIED(0x0073),

    /**
     * Preference_Ignored
     */
    PREFERENCE_IGNORED(0x0074),

    /**
     * Not_Authorized
     */
    NOT_AUTHORIZED(0x007E),

    /**
     * Reserved_Field_Not_Zero
     */
    RESERVED_FIELD_NOT_ZERO(0x007F),

    /**
     * Malformed_Command
     */
    MALFORMED_COMMAND(0x0080),

    /**
     * Unsup_Cluster_Command
     */
    UNSUP_CLUSTER_COMMAND(0x0081),

    /**
     * Unsup_General_Command
     */
    UNSUP_GENERAL_COMMAND(0x0082),

    /**
     * Unsup_Manuf_Cluster_Command
     */
    UNSUP_MANUF_CLUSTER_COMMAND(0x0083),

    /**
     * Unsup_Manuf_General_Command
     */
    UNSUP_MANUF_GENERAL_COMMAND(0x0084),

    /**
     * Invalid_Field
     */
    INVALID_FIELD(0x0085),

    /**
     * Unsupported_Attribute
     */
    UNSUPPORTED_ATTRIBUTE(0x0086),

    /**
     * Invalid_Value
     */
    INVALID_VALUE(0x0087),

    /**
     * Read_Only
     */
    READ_ONLY(0x0088),

    /**
     * Insufficient_Space
     */
    INSUFFICIENT_SPACE(0x0089),

    /**
     * Duplicate_Exists
     */
    DUPLICATE_EXISTS(0x008A),

    /**
     * Not_Found
     */
    NOT_FOUND(0x008B),

    /**
     * Unreportable_Attribute
     */
    UNREPORTABLE_ATTRIBUTE(0x008C),

    /**
     * Invalid_Data_Type
     */
    INVALID_DATA_TYPE(0x008D),

    /**
     * Invalid_Selector
     */
    INVALID_SELECTOR(0x008E),

    /**
     * Write_Only
     */
    WRITE_ONLY(0x008F),

    /**
     * Inconsistent_Startup_State
     */
    INCONSISTENT_STARTUP_STATE(0x0090),

    /**
     * Defined_Out_Of_Band
     */
    DEFINED_OUT_OF_BAND(0x0091),

    /**
     * Inconsistent
     */
    INCONSISTENT(0x0092),

    /**
     * Action_Denied
     */
    ACTION_DENIED(0x0093),

    /**
     * Timeout
     */
    TIMEOUT(0x0094),

    /**
     * Abort
     */
    ABORT(0x0095),

    /**
     * Invalid_Image
     */
    INVALID_IMAGE(0x0096),

    /**
     * Wait_For_Data
     */
    WAIT_FOR_DATA(0x0097),

    /**
     * No_Image_Available
     */
    NO_IMAGE_AVAILABLE(0x0098),

    /**
     * Require_More_Image
     */
    REQUIRE_MORE_IMAGE(0x0099),

    /**
     * Hardware_Failure
     */
    HARDWARE_FAILURE(0x00C0),

    /**
     * Software_Failure
     */
    SOFTWARE_FAILURE(0x00C1),

    /**
     * Calibration_Error
     */
    CALIBRATION_ERROR(0x00C2),

    /**
     * Unsupported_Cluster
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
