/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.field.WriteAttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Write Attributes Structured Response value object class.
 * <p>
 * Cluster: <b>General</b>. Command ID 0x10 is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * The write attributes structured response command is generated in response to a write
 * attributes structured command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class WriteAttributesStructuredResponse extends ZclGeneralCommand {
    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x10;

    /**
     * Status command message field.
     * <p>
     * Status is only provided if the command was successful, and the write attribute status
     * records are not included for successfully written attributes, in order to save
     * bandwidth.
     */
    private ZclStatus status;

    /**
     * Records command message field.
     * <p>
     * Note that write attribute status records are not included for successfully written
     * attributes, in order to save bandwidth. In the case of successful writing of all
     * attributes, only a single write attribute status record shall be included in the
     * command, with the status field set to SUCCESS and the attribute identifier field
     * omitted.
     */
    private List<WriteAttributeStatusRecord> records;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public WriteAttributesStructuredResponse() {
        commandId = COMMAND_ID;
        genericCommand = true;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param status {@link ZclStatus} Status
     * @param records {@link List<WriteAttributeStatusRecord>} Records
     */
    public WriteAttributesStructuredResponse(
            ZclStatus status,
            List<WriteAttributeStatusRecord> records) {

        commandId = COMMAND_ID;
        genericCommand = true;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.status = status;
        this.records = records;
    }

    /**
     * Sets the cluster ID for <i>generic</i> commands. {@link WriteAttributesStructuredResponse} is a <i>generic</i> command.
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
     * Gets Status.
     * <p>
     * Status is only provided if the command was successful, and the write attribute status
     * records are not included for successfully written attributes, in order to save
     * bandwidth.
     *
     * @return the Status
     */
    public ZclStatus getStatus() {
        return status;
    }

    /**
     * Sets Status.
     * <p>
     * Status is only provided if the command was successful, and the write attribute status
     * records are not included for successfully written attributes, in order to save
     * bandwidth.
     *
     * @param status the Status
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStatus(final ZclStatus status) {
        this.status = status;
    }

    /**
     * Gets Records.
     * <p>
     * Note that write attribute status records are not included for successfully written
     * attributes, in order to save bandwidth. In the case of successful writing of all
     * attributes, only a single write attribute status record shall be included in the
     * command, with the status field set to SUCCESS and the attribute identifier field
     * omitted.
     *
     * @return the Records
     */
    public List<WriteAttributeStatusRecord> getRecords() {
        return records;
    }

    /**
     * Sets Records.
     * <p>
     * Note that write attribute status records are not included for successfully written
     * attributes, in order to save bandwidth. In the case of successful writing of all
     * attributes, only a single write attribute status record shall be included in the
     * command, with the status field set to SUCCESS and the attribute identifier field
     * omitted.
     *
     * @param records the Records
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setRecords(final List<WriteAttributeStatusRecord> records) {
        this.records = records;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        if (status == ZclStatus.SUCCESS) {
            serializer.serialize(status, ZclDataType.ZCL_STATUS);
            return;
        }
        serializer.serialize(records, ZclDataType.N_X_WRITE_ATTRIBUTE_STATUS_RECORD);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        if (deserializer.getRemainingLength() == 1) {
            status = (ZclStatus) deserializer.deserialize(ZclDataType.ZCL_STATUS);
            return;
        }
        records = (List<WriteAttributeStatusRecord>) deserializer.deserialize(ZclDataType.N_X_WRITE_ATTRIBUTE_STATUS_RECORD);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(89);
        builder.append("WriteAttributesStructuredResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", records=");
        builder.append(records);
        builder.append(']');
        return builder.toString();
    }

}
