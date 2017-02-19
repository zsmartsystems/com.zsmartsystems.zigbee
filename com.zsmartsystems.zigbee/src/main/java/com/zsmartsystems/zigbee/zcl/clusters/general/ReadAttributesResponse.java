package com.zsmartsystems.zigbee.zcl.clusters.general;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

import java.util.List;
import com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord;

/**
 * Read Attributes Response value object class.
 * <p>
 * The read attributes response command is generated in response to a read attributes
 * or read attributes structured command. The command frame shall contain a read
 * attribute status record for each attribute identifier specified in the original read
 * attributes or read attributes structured command. For each read attribute status
 * record, the attribute identifier field shall contain the identifier specified in the
 * original read attributes or read attributes structured command.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ReadAttributesResponse extends ZclCommand {
    /**
     * Records command message field.
     */
    private List<ReadAttributeStatusRecord> records;

    /**
     * Default constructor.
     */
    public ReadAttributesResponse() {
        genericCommand = true;
        commandId = 1;
        commandDirection = true;
    }

    /**
     * Sets the cluster ID for <i>generic</i> commands. {@link ReadAttributesResponse} is a <i>generic</i> command.
     * <p>
     * For commands that are not <i>generic</i>, this method will do nothing as the cluster ID is fixed.
     * To test if a command is <i>generic</i>, use the {@link #isGenericCommand} method.
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
    public List<ReadAttributeStatusRecord> getRecords() {
        return records;
    }

    /**
     * Sets Records.
     * @param records the Records
     */
    public void setRecords(final List<ReadAttributeStatusRecord> records) {
        this.records = records;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(records, ZclDataType.N_X_READ_ATTRIBUTE_STATUS_RECORD);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        records = (List<ReadAttributeStatusRecord>) deserializer.deserialize(ZclDataType.N_X_READ_ATTRIBUTE_STATUS_RECORD);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", records=");
        builder.append(records);
        return builder.toString();
    }

}
