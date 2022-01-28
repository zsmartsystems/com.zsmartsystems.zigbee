/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.general;

import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Read Attributes Response value object class.
 * <p>
 * Cluster: <b>General</b>. Command ID 0x01 is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * The read attributes response command is generated in response to a read attributes or read
 * attributes structured command. The command frame shall contain a read attribute status
 * record for each attribute identifier specified in the original read attributes or read
 * attributes structured command. For each read attribute status record, the attribute
 * identifier field shall contain the identifier specified in the original read attributes or
 * read attributes structured command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class ReadAttributesResponse extends ZclGeneralCommand {
    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

    /**
     * Records command message field.
     */
    private List<ReadAttributeStatusRecord> records;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ReadAttributesResponse() {
        commandId = COMMAND_ID;
        genericCommand = true;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param records {@link List<ReadAttributeStatusRecord>} Records
     */
    public ReadAttributesResponse(
            List<ReadAttributeStatusRecord> records) {

        commandId = COMMAND_ID;
        genericCommand = true;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.records = records;
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
     *
     * @return the Records
     */
    public List<ReadAttributeStatusRecord> getRecords() {
        return records;
    }

    /**
     * Sets Records.
     *
     * @param records the Records
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
        final StringBuilder builder = new StringBuilder(52);
        builder.append("ReadAttributesResponse [");
        builder.append(super.toString());
        builder.append(", records=");
        builder.append(records);
        builder.append(']');
        return builder.toString();
    }

}
