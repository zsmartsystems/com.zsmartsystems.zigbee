/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.doorlock;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Toggle Response value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x02 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * This command is sent in response to a Toggle command with one status byte payload. The Status
 * field shall be set to SUCCESS or FAILURE.
 * <p>
 * The status byte only indicates if the message has received successfully. To determine the
 * lock and/or door status, the client should query to [Lock State attribute] and [Door State
 * attribute].
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
public class ToggleResponse extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Status command message field.
     */
    private Integer status;

    /**
     * Default constructor.
     */
    public ToggleResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
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
     * @return the ToggleResponse command
     */
    public ToggleResponse setStatus(final Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(43);
        builder.append("ToggleResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(']');
        return builder.toString();
    }

}
