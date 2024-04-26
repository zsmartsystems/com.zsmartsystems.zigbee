/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Backup Source Bind Request value object class.
 * <p>
 * <p>
 * The Backup_Source_Bind_req is generated from a local primary binding table cache and sent
 * to a remote backup binding table cache device to request backup storage of its entire source
 * table. The destination addressing mode for this request is unicast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class BackupSourceBindRequest extends ZdoRequest {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x0029;

    /**
     * Source Table Entries command message field.
     */
    private Integer sourceTableEntries;

    /**
     * Start Index command message field.
     */
    private Integer startIndex;

    /**
     * Source Table List Count command message field.
     */
    private Integer sourceTableListCount;

    /**
     * Source Table List command message field.
     */
    private List<Long> sourceTableList;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public BackupSourceBindRequest() {
        clusterId = CLUSTER_ID;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param sourceTableEntries {@link Integer} Source Table Entries
     * @param startIndex {@link Integer} Start Index
     * @param sourceTableListCount {@link Integer} Source Table List Count
     * @param sourceTableList {@link List<Long>} Source Table List
     */
    public BackupSourceBindRequest(
            Integer sourceTableEntries,
            Integer startIndex,
            Integer sourceTableListCount,
            List<Long> sourceTableList) {

        clusterId = CLUSTER_ID;

        this.sourceTableEntries = sourceTableEntries;
        this.startIndex = startIndex;
        this.sourceTableListCount = sourceTableListCount;
        this.sourceTableList = sourceTableList;
    }

    /**
     * Gets Source Table Entries.
     *
     * @return the Source Table Entries
     */
    public Integer getSourceTableEntries() {
        return sourceTableEntries;
    }

    /**
     * Sets Source Table Entries.
     *
     * @param sourceTableEntries the Source Table Entries
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSourceTableEntries(final Integer sourceTableEntries) {
        this.sourceTableEntries = sourceTableEntries;
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
     * Gets Source Table List Count.
     *
     * @return the Source Table List Count
     */
    public Integer getSourceTableListCount() {
        return sourceTableListCount;
    }

    /**
     * Sets Source Table List Count.
     *
     * @param sourceTableListCount the Source Table List Count
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSourceTableListCount(final Integer sourceTableListCount) {
        this.sourceTableListCount = sourceTableListCount;
    }

    /**
     * Gets Source Table List.
     *
     * @return the Source Table List
     */
    public List<Long> getSourceTableList() {
        return sourceTableList;
    }

    /**
     * Sets Source Table List.
     *
     * @param sourceTableList the Source Table List
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSourceTableList(final List<Long> sourceTableList) {
        this.sourceTableList = sourceTableList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(sourceTableEntries, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(startIndex, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(sourceTableListCount, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(sourceTableList, ZclDataType.N_X_IEEE_ADDRESS);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        sourceTableEntries = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        startIndex = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sourceTableListCount = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sourceTableList = deserializer.deserialize(ZclDataType.N_X_IEEE_ADDRESS);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(169);
        builder.append("BackupSourceBindRequest [");
        builder.append(super.toString());
        builder.append(", sourceTableEntries=");
        builder.append(sourceTableEntries);
        builder.append(", startIndex=");
        builder.append(startIndex);
        builder.append(", sourceTableListCount=");
        builder.append(sourceTableListCount);
        builder.append(", sourceTableList=");
        builder.append(sourceTableList);
        builder.append(']');
        return builder.toString();
    }

}
