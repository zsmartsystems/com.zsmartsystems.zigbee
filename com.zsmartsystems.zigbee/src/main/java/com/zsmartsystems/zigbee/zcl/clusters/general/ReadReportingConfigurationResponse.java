/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.general;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

import java.util.List;
import com.zsmartsystems.zigbee.zcl.field.AttributeReportingConfigurationRecord;

/**
 * Read Reporting Configuration Response value object class.
 * <p>
 * The Read Reporting Configuration Response command is used to respond to a
 * Read Reporting Configuration command.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class ReadReportingConfigurationResponse extends ZclCommand {
    /**
     * Records command message field.
     */
    private List<AttributeReportingConfigurationRecord> records;

    /**
     * Default constructor.
     */
    public ReadReportingConfigurationResponse() {
        genericCommand = true;
        commandId = 9;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Sets the cluster ID for <i>generic</i> commands. {@link ReadReportingConfigurationResponse} is a <i>generic</i> command.
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
    public List<AttributeReportingConfigurationRecord> getRecords() {
        return records;
    }

    /**
     * Sets Records.
     *
     * @param records the Records
     */
    public void setRecords(final List<AttributeReportingConfigurationRecord> records) {
        this.records = records;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(records, ZclDataType.N_X_ATTRIBUTE_REPORTING_CONFIGURATION_RECORD);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        records = (List<AttributeReportingConfigurationRecord>) deserializer.deserialize(ZclDataType.N_X_ATTRIBUTE_REPORTING_CONFIGURATION_RECORD);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(64);
        builder.append("ReadReportingConfigurationResponse [");
        builder.append(super.toString());
        builder.append(", records=");
        builder.append(records);
        builder.append(']');
        return builder.toString();
    }

}
