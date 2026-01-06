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
 * Get Log Record value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x04 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * Request a log record. Log number is between 1 – [Number of Log Records Supported attribute].
 * If log number 0 is requested then the most recent log entry is returned.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-17T10:52:31Z")
public class GetLogRecord extends ZclDoorLockCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x04;

    /**
     * Log Index command message field.
     */
    private Integer logIndex;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetLogRecord() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param logIndex {@link Integer} Log Index
     */
    public GetLogRecord(
            Integer logIndex) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.logIndex = logIndex;
    }

    /**
     * Gets Log Index.
     *
     * @return the Log Index
     */
    public Integer getLogIndex() {
        return logIndex;
    }

    /**
     * Sets Log Index.
     *
     * @param logIndex the Log Index
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setLogIndex(final Integer logIndex) {
        this.logIndex = logIndex;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(logIndex, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        logIndex = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(43);
        builder.append("GetLogRecord [");
        builder.append(super.toString());
        builder.append(", logIndex=");
        builder.append(logIndex);
        builder.append(']');
        return builder.toString();
    }

}
