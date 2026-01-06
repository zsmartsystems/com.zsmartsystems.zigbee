/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.doorlock;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Unlock With Timeout Response value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x03 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * This command is sent in response to an Unlock with Timeout command with one status byte
 * payload. The Status field shall be set to SUCCESS or FAILURE.
 * <p>
 * The status byte only indicates if the message has received successfully. To determine
 * status, the client should query to [Lock State attribute] and [Door State attribute].
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class UnlockWithTimeoutResponse extends ZclDoorLockCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x03;

    /**
     * Status command message field.
     */
    private Integer status;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public UnlockWithTimeoutResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param status {@link Integer} Status
     */
    public UnlockWithTimeoutResponse(
            Integer status) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.status = status;
    }

    /**
     * Gets Status.
     *
     * @return the Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets Status.
     *
     * @param status the Status
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStatus(final Integer status) {
        this.status = status;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(54);
        builder.append("UnlockWithTimeoutResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(']');
        return builder.toString();
    }

}
