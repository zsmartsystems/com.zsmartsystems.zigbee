/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.prepayment;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Top Up Log value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command ID 0x08 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is sent to the Metering Device to retrieve the log of Top Up codes received
 * by the meter.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GetTopUpLog extends ZclPrepaymentCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0705;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x08;

    /**
     * Latest End Time command message field.
     */
    private Calendar latestEndTime;

    /**
     * Number Of Records command message field.
     */
    private Integer numberOfRecords;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetTopUpLog() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param latestEndTime {@link Calendar} Latest End Time
     * @param numberOfRecords {@link Integer} Number Of Records
     */
    public GetTopUpLog(
            Calendar latestEndTime,
            Integer numberOfRecords) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.latestEndTime = latestEndTime;
        this.numberOfRecords = numberOfRecords;
    }

    /**
     * Gets Latest End Time.
     *
     * @return the Latest End Time
     */
    public Calendar getLatestEndTime() {
        return latestEndTime;
    }

    /**
     * Sets Latest End Time.
     *
     * @param latestEndTime the Latest End Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setLatestEndTime(final Calendar latestEndTime) {
        this.latestEndTime = latestEndTime;
    }

    /**
     * Gets Number Of Records.
     *
     * @return the Number Of Records
     */
    public Integer getNumberOfRecords() {
        return numberOfRecords;
    }

    /**
     * Sets Number Of Records.
     *
     * @param numberOfRecords the Number Of Records
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNumberOfRecords(final Integer numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(latestEndTime, ZclDataType.UTCTIME);
        serializer.serialize(numberOfRecords, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        latestEndTime = deserializer.deserialize(ZclDataType.UTCTIME);
        numberOfRecords = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(82);
        builder.append("GetTopUpLog [");
        builder.append(super.toString());
        builder.append(", latestEndTime=");
        builder.append(latestEndTime);
        builder.append(", numberOfRecords=");
        builder.append(numberOfRecords);
        builder.append(']');
        return builder.toString();
    }

}
