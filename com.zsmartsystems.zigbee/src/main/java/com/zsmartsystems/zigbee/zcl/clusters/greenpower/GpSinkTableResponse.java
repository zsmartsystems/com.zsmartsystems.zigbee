/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Gp Sink Table Response value object class.
 * <p>
 * Cluster: <b>Green Power</b>. Command ID 0x0A is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Green Power cluster.
 * <p>
 * To selected Proxy Table entries, by index or by GPD ID.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GpSinkTableResponse extends ZclGreenPowerCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0021;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0A;

    /**
     * Status command message field.
     */
    private Integer status;

    /**
     * Total Numberof Non Empty Sink Table Entries command message field.
     */
    private Integer totalNumberofNonEmptySinkTableEntries;

    /**
     * Start Index command message field.
     */
    private Integer startIndex;

    /**
     * Sink Table Entries Count command message field.
     */
    private Integer sinkTableEntriesCount;

    /**
     * Sink Table Entries command message field.
     */
    private Integer sinkTableEntries;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GpSinkTableResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param status {@link Integer} Status
     * @param totalNumberofNonEmptySinkTableEntries {@link Integer} Total Numberof Non Empty Sink Table Entries
     * @param startIndex {@link Integer} Start Index
     * @param sinkTableEntriesCount {@link Integer} Sink Table Entries Count
     * @param sinkTableEntries {@link Integer} Sink Table Entries
     */
    public GpSinkTableResponse(
            Integer status,
            Integer totalNumberofNonEmptySinkTableEntries,
            Integer startIndex,
            Integer sinkTableEntriesCount,
            Integer sinkTableEntries) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.status = status;
        this.totalNumberofNonEmptySinkTableEntries = totalNumberofNonEmptySinkTableEntries;
        this.startIndex = startIndex;
        this.sinkTableEntriesCount = sinkTableEntriesCount;
        this.sinkTableEntries = sinkTableEntries;
    }

    /**
     * Gets Status.
     *
     * @return the Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets Status.
     *
     * @param status the Status
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStatus(final Integer status) {
        this.status = status;
    }

    /**
     * Gets Total Numberof Non Empty Sink Table Entries.
     *
     * @return the Total Numberof Non Empty Sink Table Entries
     */
    public Integer getTotalNumberofNonEmptySinkTableEntries() {
        return totalNumberofNonEmptySinkTableEntries;
    }

    /**
     * Sets Total Numberof Non Empty Sink Table Entries.
     *
     * @param totalNumberofNonEmptySinkTableEntries the Total Numberof Non Empty Sink Table Entries
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTotalNumberofNonEmptySinkTableEntries(final Integer totalNumberofNonEmptySinkTableEntries) {
        this.totalNumberofNonEmptySinkTableEntries = totalNumberofNonEmptySinkTableEntries;
    }

    /**
     * Gets Start Index.
     *
     * @return the Start Index
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * Sets Start Index.
     *
     * @param startIndex the Start Index
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStartIndex(final Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * Gets Sink Table Entries Count.
     *
     * @return the Sink Table Entries Count
     */
    public Integer getSinkTableEntriesCount() {
        return sinkTableEntriesCount;
    }

    /**
     * Sets Sink Table Entries Count.
     *
     * @param sinkTableEntriesCount the Sink Table Entries Count
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSinkTableEntriesCount(final Integer sinkTableEntriesCount) {
        this.sinkTableEntriesCount = sinkTableEntriesCount;
    }

    /**
     * Gets Sink Table Entries.
     *
     * @return the Sink Table Entries
     */
    public Integer getSinkTableEntries() {
        return sinkTableEntries;
    }

    /**
     * Sets Sink Table Entries.
     *
     * @param sinkTableEntries the Sink Table Entries
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSinkTableEntries(final Integer sinkTableEntries) {
        this.sinkTableEntries = sinkTableEntries;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(totalNumberofNonEmptySinkTableEntries, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(startIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(sinkTableEntriesCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(sinkTableEntries, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        totalNumberofNonEmptySinkTableEntries = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        startIndex = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        sinkTableEntriesCount = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        sinkTableEntries = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(212);
        builder.append("GpSinkTableResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", totalNumberofNonEmptySinkTableEntries=");
        builder.append(totalNumberofNonEmptySinkTableEntries);
        builder.append(", startIndex=");
        builder.append(startIndex);
        builder.append(", sinkTableEntriesCount=");
        builder.append(sinkTableEntriesCount);
        builder.append(", sinkTableEntries=");
        builder.append(sinkTableEntries);
        builder.append(']');
        return builder.toString();
    }

}
