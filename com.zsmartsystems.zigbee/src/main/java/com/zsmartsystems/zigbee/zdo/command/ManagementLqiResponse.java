/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;

import java.util.List;
import java.util.ArrayList;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable;

/**
 * Management LQI Response value object class.
 * <p>
 * The Mgmt_Lqi_rsp is generated in response to an Mgmt_Lqi_req. If this
 * management command is not supported, a status of NOT_SUPPORTED shall be
 * returned and all parameter fields after the Status field shall be omitted. Otherwise,
 * the Remote Device shall implement the following processing.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementLqiResponse extends ZdoResponse {
    /**
     * NeighborTableEntries command message field.
     */
    private Integer neighborTableEntries;

    /**
     * StartIndex command message field.
     */
    private Integer startIndex;

    /**
     * NeighborTableListCount command message field.
     */
    private Integer neighborTableListCount;

    /**
     * NeighborTableList command message field.
     */
    private List<NeighborTable> neighborTableList;

    /**
     * Default constructor.
     */
    public ManagementLqiResponse() {
        clusterId = 0x8031;
    }

    /**
     * Gets NeighborTableEntries.
     *
     * @return the NeighborTableEntries
     */
    public Integer getNeighborTableEntries() {
        return neighborTableEntries;
    }

    /**
     * Sets NeighborTableEntries.
     *
     * @param neighborTableEntries the NeighborTableEntries
     */
    public void setNeighborTableEntries(final Integer neighborTableEntries) {
        this.neighborTableEntries = neighborTableEntries;
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
     * Gets NeighborTableListCount.
     *
     * @return the NeighborTableListCount
     */
    public Integer getNeighborTableListCount() {
        return neighborTableListCount;
    }

    /**
     * Sets NeighborTableListCount.
     *
     * @param neighborTableListCount the NeighborTableListCount
     */
    public void setNeighborTableListCount(final Integer neighborTableListCount) {
        this.neighborTableListCount = neighborTableListCount;
    }

    /**
     * Gets NeighborTableList.
     *
     * @return the NeighborTableList
     */
    public List<NeighborTable> getNeighborTableList() {
        return neighborTableList;
    }

    /**
     * Sets NeighborTableList.
     *
     * @param neighborTableList the NeighborTableList
     */
    public void setNeighborTableList(final List<NeighborTable> neighborTableList) {
        this.neighborTableList = neighborTableList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(status, ZclDataType.ZDO_STATUS);
        serializer.serialize(neighborTableEntries, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(startIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(neighborTableListCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        for (int cnt = 0; cnt < neighborTableList.size(); cnt++) {
            serializer.serialize(neighborTableList.get(cnt), ZclDataType.NEIGHBOR_TABLE);
        }
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        // Create lists
        neighborTableList = new ArrayList<NeighborTable>();

        status = (ZdoStatus) deserializer.deserialize(ZclDataType.ZDO_STATUS);
        if (status != ZdoStatus.SUCCESS) {
            // Don't read the full response if we have an error
            return;
        }
        neighborTableEntries = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        startIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        neighborTableListCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (neighborTableListCount != null) {
            for (int cnt = 0; cnt < neighborTableListCount; cnt++) {
                neighborTableList.add((NeighborTable) deserializer.deserialize(ZclDataType.NEIGHBOR_TABLE));
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(199);
        builder.append("ManagementLqiResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", neighborTableEntries=");
        builder.append(neighborTableEntries);
        builder.append(", startIndex=");
        builder.append(startIndex);
        builder.append(", neighborTableListCount=");
        builder.append(neighborTableListCount);
        builder.append(", neighborTableList=");
        builder.append(neighborTableList);
        builder.append(']');
        return builder.toString();
    }

}
