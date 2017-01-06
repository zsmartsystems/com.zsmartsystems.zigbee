package com.zsmartsystems.zigbee.zcl.clusters.general;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.zsmartsystems.zigbee.zcl.field.AttributeReport;

/**
 * <p>
 * Report Attributes Command value object class.
 * </p>
 * <p>
 * The report attributes command is used by a device to report the values of one or
 * more of its attributes to another device, bound a priori. Individual clusters, defined
 * elsewhere in the ZCL, define which attributes are to be reported and at what
 * interval.
 * </p>
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class ReportAttributesCommand extends ZclCommand {
    /**
     * Reports command message field.
     */
    private List<AttributeReport> reports;

    /**
     * Default constructor setting the command type field.
     */
    public ReportAttributesCommand() {
        genericCommand = true;
        commandId = 10;
        commandDirection = true;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public ReportAttributesCommand(final Map<Integer, Object> fields) {
        this();
        reports = (List<AttributeReport>) fields.get(0);
    }

    /**
     * <p>
     * Sets the cluster ID for <i>generic</i> commands. {@link ReportAttributesCommand} is a <i>generic</i> command.
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
     * Gets Reports.
     * @return the Reports
     */
    public List<AttributeReport> getReports() {
        return reports;
    }

    /**
     * Sets Reports.
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
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("reports = ");
        builder.append(reports);
        return builder.toString();
    }

}
