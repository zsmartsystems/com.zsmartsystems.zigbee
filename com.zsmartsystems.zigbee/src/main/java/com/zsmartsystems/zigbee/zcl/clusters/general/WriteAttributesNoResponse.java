package com.zsmartsystems.zigbee.zcl.clusters.general;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

import java.util.List;
import com.zsmartsystems.zigbee.zcl.field.WriteAttributeRecord;

/**
 * Write Attributes No Response value object class.
 * <p>
 * The write attributes no response command is generated when a device wishes to
 * change the value of one or more attributes located on another device but does not
 * require a response. Each write attribute record shall contain the identifier and the
 * actual value of the attribute to be written.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class WriteAttributesNoResponse extends ZclCommand {
    /**
     * Records command message field.
     */
    private List<WriteAttributeRecord> records;

    /**
     * Default constructor.
     */
    public WriteAttributesNoResponse() {
        genericCommand = true;
        commandId = 5;
        commandDirection = true;
    }

    /**
     * Sets the cluster ID for <i>generic</i> commands. {@link WriteAttributesNoResponse} is a <i>generic</i> command.
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
     *
     * @return the Records
     */
    public List<WriteAttributeRecord> getRecords() {
        return records;
    }

    /**
     * Sets Records.
     *
     * @param records the Records
     */
    public void setRecords(final List<WriteAttributeRecord> records) {
        this.records = records;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(records, ZclDataType.N_X_WRITE_ATTRIBUTE_RECORD);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        records = (List<WriteAttributeRecord>) deserializer.deserialize(ZclDataType.N_X_WRITE_ATTRIBUTE_RECORD);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("WriteAttributesNoResponse [");
        builder.append(super.toString());
        builder.append(", records=");
        builder.append(records);
        builder.append("]");
        return builder.toString();
    }

}
