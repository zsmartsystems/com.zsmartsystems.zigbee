/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.NeighborInformation;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Report RSSI Measurements Command value object class.
 * <p>
 * Cluster: <b>RSSI Location</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T19:19:25Z")
public class ReportRssiMeasurementsCommand extends ZclCommand {
    /**
     * Reporting Address command message field.
     */
    private IeeeAddress reportingAddress;

    /**
     * Number Of Neighbors command message field.
     */
    private Integer numberOfNeighbors;

    /**
     * Neighbors Information command message field.
     */
    private List<NeighborInformation> neighborsInformation;

    /**
     * Default constructor.
     */
    public ReportRssiMeasurementsCommand() {
        genericCommand = false;
        clusterId = 0x000B;
        commandId = 6;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Reporting Address.
     *
     * @return the Reporting Address
     */
    public IeeeAddress getReportingAddress() {
        return reportingAddress;
    }

    /**
     * Sets Reporting Address.
     *
     * @param reportingAddress the Reporting Address
     */
    public void setReportingAddress(final IeeeAddress reportingAddress) {
        this.reportingAddress = reportingAddress;
    }

    /**
     * Gets Number Of Neighbors.
     *
     * @return the Number Of Neighbors
     */
    public Integer getNumberOfNeighbors() {
        return numberOfNeighbors;
    }

    /**
     * Sets Number Of Neighbors.
     *
     * @param numberOfNeighbors the Number Of Neighbors
     */
    public void setNumberOfNeighbors(final Integer numberOfNeighbors) {
        this.numberOfNeighbors = numberOfNeighbors;
    }

    /**
     * Gets Neighbors Information.
     *
     * @return the Neighbors Information
     */
    public List<NeighborInformation> getNeighborsInformation() {
        return neighborsInformation;
    }

    /**
     * Sets Neighbors Information.
     *
     * @param neighborsInformation the Neighbors Information
     */
    public void setNeighborsInformation(final List<NeighborInformation> neighborsInformation) {
        this.neighborsInformation = neighborsInformation;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(reportingAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(numberOfNeighbors, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(neighborsInformation, ZclDataType.N_X_NEIGHBORS_INFORMATION);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        reportingAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        numberOfNeighbors = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        neighborsInformation = (List<NeighborInformation>) deserializer.deserialize(ZclDataType.N_X_NEIGHBORS_INFORMATION);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(145);
        builder.append("ReportRssiMeasurementsCommand [");
        builder.append(super.toString());
        builder.append(", reportingAddress=");
        builder.append(reportingAddress);
        builder.append(", numberOfNeighbors=");
        builder.append(numberOfNeighbors);
        builder.append(", neighborsInformation=");
        builder.append(neighborsInformation);
        builder.append(']');
        return builder.toString();
    }

}
