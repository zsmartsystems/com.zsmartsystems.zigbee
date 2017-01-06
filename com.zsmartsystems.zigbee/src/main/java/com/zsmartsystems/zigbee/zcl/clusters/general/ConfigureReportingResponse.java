package com.zsmartsystems.zigbee.zcl.clusters.general;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.zsmartsystems.zigbee.zcl.field.AttributeStatusRecord;

/**
 * <p>
 * Configure Reporting Response value object class.
 * </p>
 * <p>
 * The Configure Reporting Response command is generated in response to a
 * Configure Reporting command.
 * </p>
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class ConfigureReportingResponse extends ZclCommand {
    /**
     * Records command message field.
     */
    private List<AttributeStatusRecord> records;

    /**
     * Default constructor setting the command type field.
     */
    public ConfigureReportingResponse() {
        genericCommand = true;
        commandId = 7;
        commandDirection = true;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public ConfigureReportingResponse(final Map<Integer, Object> fields) {
        this();
        records = (List<AttributeStatusRecord>) fields.get(0);
    }

    /**
     * <p>
     * Sets the cluster ID for <i>generic</i> commands. {@link ConfigureReportingResponse} is a <i>generic</i> command.
     * </p>
     * <p>
     * For commands that are not <i>generic</i>, this method will do nothing as the cluster ID is fixed.
     * To test if a command is <i>generic</i>, use the {@link #isGenericCommand} method.
     * </p>
     *
     * @param clusterId the cluster ID used for <i>generic</i> commands as an {@link Integer}
     */
    @Override
    public void setClusterId(Integer clusterId) {
        this.clusterId = clusterId;
    }

    /**
     * Gets Records.
     * @return the Records
     */
    public List<AttributeStatusRecord> getRecords() {
        return records;
    }

    /**
     * Sets Records.
     * @param records the Records
     */
    public void setRecords(final List<AttributeStatusRecord> records) {
        this.records = records;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(records, ZclDataType.N_X_ATTRIBUTE_STATUS_RECORD);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        records = (List<AttributeStatusRecord>) deserializer.deserialize(ZclDataType.N_X_ATTRIBUTE_STATUS_RECORD);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("records = ");
        builder.append(records);
        return builder.toString();
    }

}
