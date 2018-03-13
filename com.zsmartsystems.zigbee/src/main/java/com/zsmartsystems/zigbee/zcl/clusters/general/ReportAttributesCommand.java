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
import com.zsmartsystems.zigbee.zcl.field.AttributeReport;

/**
 * Report Attributes Command value object class.
 * <p>
 * The report attributes command is used by a device to report the values of one or
 * more of its attributes to another device, bound a priori. Individual clusters, defined
 * elsewhere in the ZCL, define which attributes are to be reported and at what
 * interval.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class ReportAttributesCommand extends ZclCommand {
    /**
     * Reports command message field.
     */
    private List<AttributeReport> reports;

    /**
     * Default constructor.
     */
    public ReportAttributesCommand() {
        genericCommand = true;
        commandId = 10;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Sets the cluster ID for <i>generic</i> commands. {@link ReportAttributesCommand} is a <i>generic</i> command.
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
     * Gets Reports.
     *
     * @return the Reports
     */
    public List<AttributeReport> getReports() {
        return reports;
    }

    /**
     * Sets Reports.
     *
     * @param reports the Reports
     */
    public void setReports(final List<AttributeReport> reports) {
        this.reports = reports;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(reports, ZclDataType.N_X_ATTRIBUTE_REPORT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        reports = (List<AttributeReport>) deserializer.deserialize(ZclDataType.N_X_ATTRIBUTE_REPORT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(53);
        builder.append("ReportAttributesCommand [");
        builder.append(super.toString());
        builder.append(", reports=");
        builder.append(reports);
        builder.append(']');
        return builder.toString();
    }

}
