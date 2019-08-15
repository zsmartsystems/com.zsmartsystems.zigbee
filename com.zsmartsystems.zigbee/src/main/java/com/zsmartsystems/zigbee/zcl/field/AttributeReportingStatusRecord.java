/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.field;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.ZclListItemField;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadReportingConfigurationResponse;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Attribute Reporting Configuration Record field. This is reported in the {@link ReadReportingConfigurationResponse}
 * command.
 * <p>
 * <b>minInterval</b>:
 * The minimum reporting interval field is 16 bits in length and shall contain the
 * minimum interval, in seconds, between issuing reports of the specified attribute.
 * If minInterval is set to 0x0000, then there is no minimum limit, unless one is
 * imposed by the specification of the cluster using this reporting mechanism or by
 * the applicable profile.
 * <p>
 * <b>maxInterval</b>:
 * The maximum reporting interval field is 16 bits in length and shall contain the
 * maximum interval, in seconds, between issuing reports of the specified attribute.
 * If maxInterval is set to 0xffff, then the device shall not issue reports for the specified
 * attribute, and the configuration information for that attribute need not be
 * maintained.
 * <p>
 * <b>reportableChange</b>:
 * The reportable change field shall contain the minimum change to the attribute that
 * will result in a report being issued. This field is of variable length. For attributes
 * with 'analog' data type the field has the same data type as the attribute. The sign (if any) of the reportable
 * change field is ignored.
 * <p>
 * <b>timeout</b>:
 * The timeout period field is 16 bits in length and shall contain the maximum
 * expected time, in seconds, between received reports for the attribute specified in
 * the attribute identifier field. If more time than this elapses between reports, this
 * may be an indication that there is a problem with reporting.
 * If timeout is set to 0x0000, reports of the attribute are not subject to timeout.
 * Note that, for a server/client connection to work properly using automatic
 * reporting, the timeout value set for attribute reports to be received by the client (or
 * server) cluster must be set somewhat higher than the maximum reporting interval
 * set for the attribute on the server (or client) cluster.
 *
 * @author Chris Jackson
 */
public class AttributeReportingStatusRecord implements ZclListItemField {
    /**
     * The status
     * <p>
     * If the attribute is not implemented on the sender or receiver of the command, whichever is relevant (depending on
     * direction), this field SHALL be set to UNSUPPORTED_ATTRIBUTE. If the attribute is supported, but is not capable
     * of being reported, this field SHALL be set to UNREPORTABLE_ATTRIBUTE. If the attribute is supported and
     * reportable, but there is no report configuration, this field SHALL be set to NOT_FOUND. Otherwise, this field
     * SHALL be set to SUCCESS.
     * <p>
     * If the status field is not set to SUCCESS, all fields except the direction and attribute identifier fields SHALL
     * be omitted.
     */
    private ZclStatus status;
    /**
     * The direction.
     * <p>
     * The direction field specifies whether values of the attribute are be reported, or
     * whether reports of the attribute are to be received.
     * <p>
     * If this value is set to 0x00, then the attribute data type field, the minimum
     * reporting interval field, the maximum reporting interval field and the reportable
     * change field are included in the payload, and the timeout period field is omitted.
     * The record is sent to a cluster server (or client) to configure how it sends reports to
     * a client (or server) of the same cluster.
     * <p>
     * If this value is set to 0x01, then the timeout period field is included in the payload,
     * and the attribute data type field, the minimum reporting interval field, the
     * maximum reporting interval field and the reportable change field are omitted. The
     * record is sent to a cluster client (or server) to configure how it should expect
     * reports from a server (or client) of the same cluster.
     */
    private int direction;
    /**
     * The attribute identifier.
     */
    private int attributeIdentifier;
    /**
     * The attribute data type.
     */
    private ZclDataType attributeDataType;
    /**
     * The minimum reporting interval.
     * <p>
     * The minimum reporting interval field is 16 bits in length and shall contain the
     * minimum interval, in seconds, between issuing reports of the specified attribute.
     * If minInterval is set to 0x0000, then there is no minimum limit, unless one is
     * imposed by the specification of the cluster using this reporting mechanism or by
     * the applicable profile.
     */
    private int minimumReportingInterval;
    /**
     * The maximum reporting interval.
     * <p>
     * The maximum reporting interval field is 16 bits in length and shall contain the
     * maximum interval, in seconds, between issuing reports of the specified attribute.
     * If maxInterval is set to 0xffff, then the device shall not issue reports for the specified
     * attribute, and the configuration information for that attribute need not be
     * maintained.
     */
    private int maximumReportingInterval;
    /**
     * The reportable change.
     * <p>
     * The reportable change field shall contain the minimum change to the attribute that
     * will result in a report being issued. This field is of variable length. For attributes
     * with 'analog' data type the field has the same data type as the attribute. The sign (if any) of the reportable
     * change field is ignored.
     */
    private Object reportableChange;
    /**
     * The maximum reporting interval.
     * <p>
     * The timeout period field is 16 bits in length and shall contain the maximum
     * expected time, in seconds, between received reports for the attribute specified in
     * the attribute identifier field. If more time than this elapses between reports, this
     * may be an indication that there is a problem with reporting.
     * If timeout is set to 0x0000, reports of the attribute are not subject to timeout.
     * Note that, for a server/client connection to work properly using automatic
     * reporting, the timeout value set for attribute reports to be received by the client (or
     * server) cluster must be set somewhat higher than the maximum reporting interval
     * set for the attribute on the server (or client) cluster.
     */
    private int timeoutPeriod;

    /**
     * Gets the status.
     * <p>
     * If the attribute is not implemented on the sender or receiver of the command, whichever is relevant (depending on
     * direction), this field SHALL be set to UNSUPPORTED_ATTRIBUTE. If the attribute is supported, but is not capable
     * of being reported, this field SHALL be set to UNREPORTABLE_ATTRIBUTE. If the attribute is supported and
     * reportable, but there is no report configuration, this field SHALL be set to NOT_FOUND. Otherwise, this field
     * SHALL be set to SUCCESS.
     *
     * @return the status of the record
     */
    public ZclStatus getStatus() {
        return status;
    }

    /**
     * Sets the status.
     * <p>
     * If the attribute is not implemented on the sender or receiver of the command, whichever is relevant (depending on
     * direction), this field SHALL be set to UNSUPPORTED_ATTRIBUTE. If the attribute is supported, but is not capable
     * of being reported, this field SHALL be set to UNREPORTABLE_ATTRIBUTE. If the attribute is supported and
     * reportable, but there is no report configuration, this field SHALL be set to NOT_FOUND. Otherwise, this field
     * SHALL be set to SUCCESS.
     *
     * @param status the {@link ZclStatus} of the record
     */
    public void setStatus(ZclStatus status) {
        this.status = status;
    }

    /**
     * Gets the direction
     * <p>
     * The direction field specifies whether values of the attribute are be reported, or
     * whether reports of the attribute are to be received.
     * <p>
     * If this value is set to 0x00, then the attribute data type field, the minimum
     * reporting interval field, the maximum reporting interval field and the reportable
     * change field are included in the payload, and the timeout period field is omitted.
     * The record is sent to a cluster server (or client) to configure how it sends reports to
     * a client (or server) of the same cluster.
     * <p>
     * If this value is set to 0x01, then the timeout period field is included in the payload,
     * and the attribute data type field, the minimum reporting interval field, the
     * maximum reporting interval field and the reportable change field are omitted. The
     * record is sent to a cluster client (or server) to configure how it should expect
     * reports from a server (or client) of the same cluster.
     *
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Sets the direction.
     * <p>
     * The direction field specifies whether values of the attribute are be reported, or
     * whether reports of the attribute are to be received.
     * <p>
     * If this value is set to 0x00, then the attribute data type field, the minimum
     * reporting interval field, the maximum reporting interval field and the reportable
     * change field are included in the payload, and the timeout period field is omitted.
     * The record is sent to a cluster server (or client) to configure how it sends reports to
     * a client (or server) of the same cluster.
     * <p>
     * If this value is set to 0x01, then the timeout period field is included in the payload,
     * and the attribute data type field, the minimum reporting interval field, the
     * maximum reporting interval field and the reportable change field are omitted. The
     * record is sent to a cluster client (or server) to configure how it should expect
     * reports from a server (or client) of the same cluster.
     *
     * @param direction the direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Gets attribute data type.
     *
     * @return the attribute data type
     */
    public ZclDataType getAttributeDataType() {
        return attributeDataType;
    }

    /**
     * Sets attribute data type.
     *
     * @param attributeDataType the attribute data type
     */
    public void setAttributeDataType(ZclDataType attributeDataType) {
        this.attributeDataType = attributeDataType;
    }

    /**
     * Gets attribute identifier.
     *
     * @return the attribute identifier
     */
    public int getAttributeIdentifier() {
        return attributeIdentifier;
    }

    /**
     * Sets attribute identifier.
     *
     * @param attributeIdentifier the attribute identifier
     */
    public void setAttributeIdentifier(int attributeIdentifier) {
        this.attributeIdentifier = attributeIdentifier;
    }

    /**
     * Gets maximum reporting interval.
     * <p>
     * The maximum reporting interval field is 16 bits in length and shall contain the
     * maximum interval, in seconds, between issuing reports of the specified attribute.
     * If maxInterval is set to 0xffff, then the device shall not issue reports for the specified
     * attribute, and the configuration information for that attribute need not be
     * maintained.
     *
     * @return the maximum reporting interval
     */
    public int getMaximumReportingInterval() {
        return maximumReportingInterval;
    }

    /**
     * Sets maximum reporting interval.
     * <p>
     * The maximum reporting interval field is 16 bits in length and shall contain the
     * maximum interval, in seconds, between issuing reports of the specified attribute.
     * If maxInterval is set to 0xffff, then the device shall not issue reports for the specified
     * attribute, and the configuration information for that attribute need not be
     * maintained.
     *
     * @param maximumReportingInterval the maximum reporting interval
     */
    public void setMaximumReportingInterval(int maximumReportingInterval) {
        this.maximumReportingInterval = maximumReportingInterval;
    }

    /**
     * Gets minimum reporting interval.
     * <p>
     * The minimum reporting interval field is 16 bits in length and shall contain the
     * minimum interval, in seconds, between issuing reports of the specified attribute.
     * If minInterval is set to 0x0000, then there is no minimum limit, unless one is
     * imposed by the specification of the cluster using this reporting mechanism or by
     * the applicable profile.
     *
     * @return the minimum reporting interval
     */
    public int getMinimumReportingInterval() {
        return minimumReportingInterval;
    }

    /**
     * Sets minimum reporting interval.
     * <p>
     * The minimum reporting interval field is 16 bits in length and shall contain the
     * minimum interval, in seconds, between issuing reports of the specified attribute.
     * If minInterval is set to 0x0000, then there is no minimum limit, unless one is
     * imposed by the specification of the cluster using this reporting mechanism or by
     * the applicable profile.
     *
     * @param minimumReportingInterval the minimum reporting interval
     */
    public void setMinimumReportingInterval(int minimumReportingInterval) {
        this.minimumReportingInterval = minimumReportingInterval;
    }

    /**
     * Gets reportable change.
     * <p>
     * The reportable change field shall contain the minimum change to the attribute that
     * will result in a report being issued. This field is of variable length. For attributes
     * with 'analog' data type the field has the same data type as the attribute. The sign (if any) of the reportable
     * change field is ignored.
     *
     * @return the reportable change
     */
    public Object getReportableChange() {
        return reportableChange;
    }

    /**
     * Sets reportable change.
     * <p>
     * The reportable change field shall contain the minimum change to the attribute that
     * will result in a report being issued. This field is of variable length. For attributes
     * with 'analog' data type the field has the same data type as the attribute. The sign (if any) of the reportable
     * change field is ignored.
     *
     * @param reportableChange the reportable change
     */
    public void setReportableChange(Object reportableChange) {
        this.reportableChange = reportableChange;
    }

    /**
     * Gets timeout period.
     * <p>
     * The timeout period field is 16 bits in length and shall contain the maximum
     * expected time, in seconds, between received reports for the attribute specified in
     * the attribute identifier field. If more time than this elapses between reports, this
     * may be an indication that there is a problem with reporting.
     * If timeout is set to 0x0000, reports of the attribute are not subject to timeout.
     * Note that, for a server/client connection to work properly using automatic
     * reporting, the timeout value set for attribute reports to be received by the client (or
     * server) cluster must be set somewhat higher than the maximum reporting interval
     * set for the attribute on the server (or client) cluster.
     *
     * @return the timeout period
     */
    public int getTimeoutPeriod() {
        return timeoutPeriod;
    }

    /**
     * Sets timeout period.
     * <p>
     * The timeout period field is 16 bits in length and shall contain the maximum
     * expected time, in seconds, between received reports for the attribute specified in
     * the attribute identifier field. If more time than this elapses between reports, this
     * may be an indication that there is a problem with reporting.
     * If timeout is set to 0x0000, reports of the attribute are not subject to timeout.
     * Note that, for a server/client connection to work properly using automatic
     * reporting, the timeout value set for attribute reports to be received by the client (or
     * server) cluster must be set somewhat higher than the maximum reporting interval
     * set for the attribute on the server (or client) cluster.
     *
     * @param timeoutPeriod the timeout period
     */
    public void setTimeoutPeriod(int timeoutPeriod) {
        this.timeoutPeriod = timeoutPeriod;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(status, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType(direction, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType(attributeIdentifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);

        if (direction == 1) {
            // If direction is set to 0x01, then the timeout period field is included in the payload,
            // and the attribute data type field, the minimum reporting interval field, the
            // maximum reporting interval field and the reportable change field are omitted. The
            // record is sent to a cluster client (or server) to configure how it should expect
            // reports from a server (or client) of the same cluster.
            serializer.appendZigBeeType(timeoutPeriod, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        } else {
            // If direction is set to 0x00, then the attribute data type field, the minimum
            // reporting interval field, the maximum reporting interval field and the reportable
            // change field are included in the payload, and the timeout period field is omitted.
            // The record is sent to a cluster server (or client) to configure how it sends reports to
            // a client (or server) of the same cluster.
            serializer.appendZigBeeType(attributeDataType.getId(), ZclDataType.UNSIGNED_8_BIT_INTEGER);
            serializer.appendZigBeeType(minimumReportingInterval, ZclDataType.UNSIGNED_16_BIT_INTEGER);
            serializer.appendZigBeeType(maximumReportingInterval, ZclDataType.UNSIGNED_16_BIT_INTEGER);

            // The reportable change field shall contain the minimum change to the attribute that
            // will result in a report being issued. This field is of variable length. For attributes
            // with 'analog' data typethe field has the same data type as the attribute. The sign (if any) of the
            // reportable change field is ignored. For attributes of 'discrete' data type this field is omitted.
            if (attributeDataType.isAnalog()) {
                serializer.appendZigBeeType(reportableChange, attributeDataType);
            }
        }
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        status = ZclStatus.getStatus((int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER));
        direction = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        attributeIdentifier = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        if (status != ZclStatus.SUCCESS) {
            return;
        }

        if (direction == 1) {
            // If direction is set to 0x01, then the timeout period field is included in the payload,
            // and the attribute data type field, the minimum reporting interval field, the
            // maximum reporting interval field and the reportable change field are omitted. The
            // record is sent to a cluster client (or server) to configure how it should expect
            // reports from a server (or client) of the same cluster.
            timeoutPeriod = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        } else {
            // If direction is set to 0x00, then the attribute data type field, the minimum
            // reporting interval field, the maximum reporting interval field and the reportable
            // change field are included in the payload, and the timeout period field is omitted.
            // The record is sent to a cluster server (or client) to configure how it sends reports to
            // a client (or server) of the same cluster.
            attributeDataType = ZclDataType
                    .getType((int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER));
            minimumReportingInterval = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
            maximumReportingInterval = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
            if (attributeDataType.isAnalog()) {
                // The reportable change field shall contain the minimum change to the attribute that
                // will result in a report being issued. This field is of variable length. For attributes
                // with 'analog' data typethe field has the same data type as the attribute. The sign (if any) of the
                // reportable change field is ignored. For attributes of 'discrete' data type this field is omitted.
                reportableChange = deserializer.readZigBeeType(attributeDataType);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(250);

        builder.append("AttributeReportingStatusRecord: [status=");
        builder.append(status);

        builder.append(", attributeIdentifier=");
        builder.append(attributeIdentifier);
        builder.append(", direction=");
        builder.append(direction);

        if (status == ZclStatus.SUCCESS) {
            builder.append(", attributeDataType=");
            builder.append(attributeDataType);

            if (direction == 0) {
                builder.append(", minimumReportingInterval=");
                builder.append(minimumReportingInterval);
                builder.append(", maximumReportingInterval=");
                builder.append(maximumReportingInterval);
                if (attributeDataType.isAnalog()) {
                    builder.append(", reportableChange=");
                    builder.append(reportableChange);
                }
            } else if (direction == 1) {
                builder.append(", timeoutPeriod=");
                builder.append(timeoutPeriod);
            }
        }
        builder.append(']');

        return builder.toString();
    }
}
