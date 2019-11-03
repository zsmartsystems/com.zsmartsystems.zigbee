/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 * Gp Proxy Table Response value object class.
 * <p>
 * Cluster: <b>Green Power</b>. Command ID 0x0B is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Green Power cluster.
 * <p>
 * To reply with read-out Proxy Table entries, by index or by GPD ID.
 * <p>
 * Upon reception of the GP Proxy Table Request command, the device shall check if it implements
 * a Proxy Table. If not, it shall generate a ZCL Default Response command, with the Status code
 * field carrying UNSUP_CLUSTER_COMMAND. If the device implements the Proxy Table, it shall
 * prepare a GP Proxy Table Response.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-11-03T12:48:45Z")
public class GpProxyTableResponse extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0021;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0B;

    /**
     * Status command message field.
     */
    private Integer status;

    /**
     * Total Number Of Non Empty Proxy Table Entries command message field.
     */
    private Integer totalNumberOfNonEmptyProxyTableEntries;

    /**
     * Start Index command message field.
     */
    private Integer startIndex;

    /**
     * Entries Count command message field.
     */
    private Integer entriesCount;

    /**
     * Proxy Table Entries command message field.
     */
    private Integer proxyTableEntries;

    /**
     * Default constructor.
     */
    public GpProxyTableResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     */
    public void setStatus(final Integer status) {
        this.status = status;
    }

    /**
     * Gets Total Number Of Non Empty Proxy Table Entries.
     *
     * @return the Total Number Of Non Empty Proxy Table Entries
     */
    public Integer getTotalNumberOfNonEmptyProxyTableEntries() {
        return totalNumberOfNonEmptyProxyTableEntries;
    }

    /**
     * Sets Total Number Of Non Empty Proxy Table Entries.
     *
     * @param totalNumberOfNonEmptyProxyTableEntries the Total Number Of Non Empty Proxy Table Entries
     */
    public void setTotalNumberOfNonEmptyProxyTableEntries(final Integer totalNumberOfNonEmptyProxyTableEntries) {
        this.totalNumberOfNonEmptyProxyTableEntries = totalNumberOfNonEmptyProxyTableEntries;
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
     */
    public void setStartIndex(final Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * Gets Entries Count.
     *
     * @return the Entries Count
     */
    public Integer getEntriesCount() {
        return entriesCount;
    }

    /**
     * Sets Entries Count.
     *
     * @param entriesCount the Entries Count
     */
    public void setEntriesCount(final Integer entriesCount) {
        this.entriesCount = entriesCount;
    }

    /**
     * Gets Proxy Table Entries.
     *
     * @return the Proxy Table Entries
     */
    public Integer getProxyTableEntries() {
        return proxyTableEntries;
    }

    /**
     * Sets Proxy Table Entries.
     *
     * @param proxyTableEntries the Proxy Table Entries
     */
    public void setProxyTableEntries(final Integer proxyTableEntries) {
        this.proxyTableEntries = proxyTableEntries;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(totalNumberOfNonEmptyProxyTableEntries, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(startIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(entriesCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(proxyTableEntries, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        totalNumberOfNonEmptyProxyTableEntries = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        startIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        entriesCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        proxyTableEntries = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(206);
        builder.append("GpProxyTableResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", totalNumberOfNonEmptyProxyTableEntries=");
        builder.append(totalNumberOfNonEmptyProxyTableEntries);
        builder.append(", startIndex=");
        builder.append(startIndex);
        builder.append(", entriesCount=");
        builder.append(entriesCount);
        builder.append(", proxyTableEntries=");
        builder.append(proxyTableEntries);
        builder.append(']');
        return builder.toString();
    }

}
