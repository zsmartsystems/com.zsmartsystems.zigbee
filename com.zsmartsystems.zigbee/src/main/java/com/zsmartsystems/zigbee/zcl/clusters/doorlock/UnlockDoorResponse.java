package com.zsmartsystems.zigbee.zcl.clusters.doorlock;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Unlock Door Response value object class.
 * </p>
 * <p>
 * Cluster: <b>Door Lock</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class UnlockDoorResponse extends ZclCommand {
    /**
     * Status command message field.
     */
    private Integer status;

    /**
     * Default constructor setting the command type field.
     */
    public UnlockDoorResponse() {
        genericCommand = false;
        clusterId = 257;
        commandId = 1;
        commandDirection = false;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public UnlockDoorResponse(final Map<Integer, Object> fields) {
        this();
        status = (Integer) fields.get(0);
    }

    /**
     * Gets Status.
     * @return the Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets Status.
     * @param status the Status
     */
    public void setStatus(final Integer status) {
        this.status = status;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("status = ");
        builder.append(status);
        return builder.toString();
    }

}
