/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
public class GpSinkTableResponse extends ZclCommand {
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
     */
    public GpSinkTableResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
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
     * @return the GpSinkTableResponse command
     */
    public GpSinkTableResponse setStatus(final Integer status) {
        this.status = status;
        return this;
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
     * @return the GpSinkTableResponse command
     */
    public GpSinkTableResponse setTotalNumberofNonEmptySinkTableEntries(final Integer totalNumberofNonEmptySinkTableEntries) {
        this.totalNumberofNonEmptySinkTableEntries = totalNumberofNonEmptySinkTableEntries;
        return this;
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
     * @return the GpSinkTableResponse command
     */
    public GpSinkTableResponse setStartIndex(final Integer startIndex) {
        this.startIndex = startIndex;
        return this;
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
     * @return the GpSinkTableResponse command
     */
    public GpSinkTableResponse setSinkTableEntriesCount(final Integer sinkTableEntriesCount) {
        this.sinkTableEntriesCount = sinkTableEntriesCount;
        return this;
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
     * @return the GpSinkTableResponse command
     */
    public GpSinkTableResponse setSinkTableEntries(final Integer sinkTableEntries) {
        this.sinkTableEntries = sinkTableEntries;
        return this;
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
        status = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        totalNumberofNonEmptySinkTableEntries = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        startIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        sinkTableEntriesCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        sinkTableEntries = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
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
