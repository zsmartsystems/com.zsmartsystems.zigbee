/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;

import java.util.List;
import java.util.ArrayList;
import javax.annotation.Generated;

/**
 * Backup Source Bind Request value object class.
 * <p>
 * The Backup_Source_Bind_req is generated from a local primary binding table
 * cache and sent to a remote backup binding table cache device to request backup
 * storage of its entire source table. The destination addressing mode for this request
 * is unicast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */

@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class BackupSourceBindRequest extends ZdoRequest {
    /**
     * SourceTableEntries command message field.
     */
    private Integer sourceTableEntries;

    /**
     * StartIndex command message field.
     */
    private Integer startIndex;

    /**
     * SourceTableListCount command message field.
     */
    private Integer sourceTableListCount;

    /**
     * SourceTableList command message field.
     */
    private List<Long> sourceTableList;

    /**
     * Default constructor.
     */
    public BackupSourceBindRequest() {
        clusterId = 0x0029;
    }

    /**
     * Gets SourceTableEntries.
     *
     * @return the SourceTableEntries
     */
    public Integer getSourceTableEntries() {
        return sourceTableEntries;
    }

    /**
     * Sets SourceTableEntries.
     *
     * @param sourceTableEntries the SourceTableEntries
     */
    public void setSourceTableEntries(final Integer sourceTableEntries) {
        this.sourceTableEntries = sourceTableEntries;
    }

    /**
     * Gets StartIndex.
     *
     * @return the StartIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * Sets StartIndex.
     *
     * @param startIndex the StartIndex
     */
    public void setStartIndex(final Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * Gets SourceTableListCount.
     *
     * @return the SourceTableListCount
     */
    public Integer getSourceTableListCount() {
        return sourceTableListCount;
    }

    /**
     * Sets SourceTableListCount.
     *
     * @param sourceTableListCount the SourceTableListCount
     */
    public void setSourceTableListCount(final Integer sourceTableListCount) {
        this.sourceTableListCount = sourceTableListCount;
    }

    /**
     * Gets SourceTableList.
     *
     * @return the SourceTableList
     */
    public List<Long> getSourceTableList() {
        return sourceTableList;
    }

    /**
     * Sets SourceTableList.
     *
     * @param sourceTableList the SourceTableList
     */
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

        sourceTableEntries = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        startIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sourceTableListCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sourceTableList = (List<Long>) deserializer.deserialize(ZclDataType.N_X_IEEE_ADDRESS);
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
