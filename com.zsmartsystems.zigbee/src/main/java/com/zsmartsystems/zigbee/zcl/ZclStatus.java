/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
   Copyright 2008-2013 CNR-ISTI, http://isti.cnr.it
   Institute of Information Science and Technologies
   of the Italian National Research Council


   See the NOTICE file distributed with this work for additional
   information regarding copyright ownership

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.zsmartsystems.zigbee.zcl;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the ZigBee Cluster Library status values and descriptions
 *
 * @author Chris Jackson
 */
public enum ZclStatus {

    UNKNOWN(0xFF, "Unknown"),
    SUCCESS(0x00, "Operation was successful."),
    FAILURE(0x01, "Operation was not successful."),
    NOT_AUTHORIZED(0x7e, "The sender of the command does not have authorization to carry out this command."),
    RESERVED_FIELD_NOT_ZERO(0x7f, " A reserved field/subfield/bit contains a non-zero value"),
    MALFORMED_COMMAND(0x80,
            "The command appears to contain the wrong fields, as detected either by the presence of one or more invalid field entries or by there "
                    + "being missing fields. Command not carried out. Implementer has discretion as to whether to return this error or INVALID_FIELD."),
    UNSUP_CLUSTER_COMMAND(0x81,
            "The specified cluster command is not supported on the device. Command not carried out."),
    UNSUP_GENERAL_COMMAND(0x82, "The specified general ZCL command is not supported on the device."),
    UNSUP_MANUF_CLUSTER_COMMAND(0x83,
            "A manufacturer specific unicast, cluster specific command was received with an "
                    + "unknown manufacturer code, or the manufacturer code was recognized but the "
                    + "command is not supported."),
    UNSUP_MANUF_GENERAL_COMMAND(0x84,
            "A manufacturer specific unicast, ZCL specific command was "
                    + "received with an unknown manufacturer code, or the manufacturer code "
                    + "was recognized but the command is not supported."),
    INVALID_FIELD(0x85,
            "At least one field of the command contains an incorrect value, "
                    + "according to the specification the device is implemented to."),
    UNSUPPORTED_ATTRIBUTE(0x86, "The specified attribute does not exist on the device."),
    INVALID_VALUE(0x87,
            "Out of range error, or set to a reserved value. Attribute keeps its old value. "
                    + "Note that an attribute value may be out of range if an attribute is related to another, "
                    + "e.g. with minimum and maximum attributes. See the individual attribute descriptions for "
                    + "specific details."),
    READ_ONLY(0x88, "Attempt to write a read only attribute."),
    INSUFFICIENT_SPACE(0x89,
            "An operation (e.g. an attempt to create an entry in a table) failed due to an insufficient "
                    + "amount of free space available."),
    DUPLICATE_EXISTS(0x8a,
            "An attempt to create an entry in a table failed due to a duplicate entry already being present "
                    + "in the table."),
    NOT_FOUND(0x8b, "The requested information (e.g. table entry) could not be found."),
    UNREPORTABLE_ATTRIBUTE(0x8c, "Periodic reports cannot be issued for this attribute."),
    INVALID_DATA_TYPE(0x8d, "The data type given for an attribute is incorrect. Command not carried out."),
    INVALID_SELECTOR(0x8e, "The selector for an attribute is incorrect."),
    WRITE_ONLY(0x8f,
            "A request has been made to read an attribute that the requestor is not authorized to read. No action taken"),
    INCONSISTENT_STARTUP_STATE(0x90,
            "Setting the requested values would put the device in an inconsistent state on startup. No action taken."),
    DEFINED_OUT_OF_BAND(0x91,
            "An attempt has been made to write an attribute that is present but is defined using an out-of-band method and not over the air."),
    INCONSISTENT(0x92, "The supplied values (e.g., contents of table cells) are inconsistent."),
    ACTION_DENIED(0x93,
            "The credentials presented by the device sending the command are not sufficient to perform this action."),
    TIMEOUT(0x94, "The exchange was aborted due to excessive response time."),
    ABORT(0x95, "Failed case when a client or a server decides to abort the upgrade process."),
    INVALID_IMAGE(0x96, "Invalid OTA upgrade image."),
    WAIT_FOR_DATA(0x97, "Server does not have data block available yet"),
    NO_IMAGE_AVAILABLE(0x98, "No OTA upgrade image available for a particular client"),
    REQUIRE_MORE_IMAGE(0x99, "The client requires more OTA upgrade image files in order to successfully upgrade."),
    NOTIFICATION_PENDING(0x9A, "The command has been received and is being processed."),
    ASDU_TOO_LONG(0xA0, "A transmit request failed since the ASDU is too large and fragmentation is not supported."),
    DEFRAG_DEFERRED(0xA1, "A received fragmented frame could not be defragmented at the current time."),
    DEFRAG_UNSUPPORTED(0xA2,
            "A received fragmented frame could not be defragmented since the device does not support fragmentation."),
    ILLEGAL_REQUEST(0xA3, "A parameter value was out of range."),
    INVALID_BINDING(0xA4,
            "An APSME-UNBIND.request failed due to the requested binding link not existing in the binding table."),
    INVALID_GROUP(0xA5,
            "An APSME-REMOVE-GROUP.request has been issued with a group identifier that does not appear in the group table."),
    INVALID_PARAMETER(0xA6, "A parameter value was invalid or out of range."),
    NO_ACK(0xA7,
            "An APSDE-DATA.request requesting acknowledged transmission failed due to no acknowledgement being received."),
    NO_BOUND_DEVICE(0xA8,
            "An APSDE-DATA.request with a destination addressing mode set to 0x00 failed due to there being no devices bound to this device."),
    NO_SHORT_ADDRESS(0xA9,
            "An APSDE-DATA.request with a destination addressing mode set to 0x03 failed due to no corresponding short address found in the address map table."),
    NOT_SUPPORTED(0xAA,
            "An APSDE-DATA.request with a destination addressing mode set to 0x00 failed due to a binding table not being supported on the device."),
    SECURED_LINK_KEY(0xAB, "An ASDU was received that was secured using a link key."),
    SECURED_NWK_KEY(0xAC, "An ASDU was received that was secured using a network key."),
    SECURITY_FAIL(0xAD,
            "An APSDE-DATA.request requesting security has resulted in an error during the corresponding security processing."),
    TABLE_FULL(0xAE,
            "An APSME-BIND.request or APSME.ADDGROUP.request issued when the binding or group tables, respectively, were full."),
    UNSECURED(0xAF, "An ASDU was received without any security."),
    HARDWARE_FAILURE(0xc0, "An operation was unsuccessful due to a hardware failure."),
    SOFTWARE_FAILURE(0xc1, "An operation was unsuccessful due to a software failure."),
    CALIBRATION_ERROR(0xc2, "An error occurred during calibration."),
    UNSUPPORTED_CLUSTER(0xc3, "The cluster is not supported");

    private final int statusId;
    private final String description;
    private static Map<Integer, ZclStatus> map = null;

    static {
        map = new HashMap<Integer, ZclStatus>();
        for (ZclStatus status : values()) {
            map.put(status.statusId, status);
        }
    }

    private ZclStatus(int statusId, String description) {
        this.statusId = statusId;
        this.description = description;
    }

    /**
     * Get a {@link ZclStatus} given an integer value
     *
     * @param statusValue the integer status value
     * @return {@link ZclStatus} or {@link #UNKNOWN}
     */
    public static ZclStatus getStatus(int statusValue) {
        if (map.get(statusValue) == null) {
            return UNKNOWN;
        }
        return map.get(statusValue);
    }

    /**
     * Gets the integer status ID for this code
     *
     * @return integer Id for the status code as per the ZCL standard
     */
    public int getId() {
        return statusId;
    }

    /**
     * Get a human readable description of the status value
     *
     * @return {@link String} containing the human readable description
     */
    public String getDescription() {
        return description;
    }
}
