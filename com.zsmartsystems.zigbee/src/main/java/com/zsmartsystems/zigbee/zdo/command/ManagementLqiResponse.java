/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable;

/**
 * Management LQI Response value object class.
 * <p>
 * <p>
 * The Mgmt_Lqi_rsp is generated in response to an Mgmt_Lqi_req. If this management command is
 * not supported, a status of NOT_SUPPORTED shall be returned and all parameter fields after
 * the Status field shall be omitted. Otherwise, the Remote Device shall implement the
 * following processing.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class ManagementLqiResponse extends ZdoResponse {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x8031;

    /**
     * Neighbor Table Entries command message field.
     */
    private Integer neighborTableEntries;

    /**
     * Start Index command message field.
     */
    private Integer startIndex;

    /**
     * Neighbor Table List command message field.
     */
    private List<NeighborTable> neighborTableList;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ManagementLqiResponse() {
        clusterId = CLUSTER_ID;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param status {@link ZdoStatus} Status
     * @param neighborTableEntries {@link Integer} Neighbor Table Entries
     * @param startIndex {@link Integer} Start Index
     * @param neighborTableList {@link List<NeighborTable>} Neighbor Table List
     */
    public ManagementLqiResponse(
            ZdoStatus status,
            Integer neighborTableEntries,
            Integer startIndex,
            List<NeighborTable> neighborTableList) {

        clusterId = CLUSTER_ID;

        this.status = status;
        this.neighborTableEntries = neighborTableEntries;
        this.startIndex = startIndex;
        this.neighborTableList = neighborTableList;
    }

    /**
     * Gets Neighbor Table Entries.
     *
     * @return the Neighbor Table Entries
     */
    public Integer getNeighborTableEntries() {
        return neighborTableEntries;
    }

    /**
     * Sets Neighbor Table Entries.
     *
     * @param neighborTableEntries the Neighbor Table Entries
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNeighborTableEntries(final Integer neighborTableEntries) {
        this.neighborTableEntries = neighborTableEntries;
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
     * Gets Neighbor Table List.
     *
     * @return the Neighbor Table List
     */
    public List<NeighborTable> getNeighborTableList() {
        return neighborTableList;
    }

    /**
     * Sets Neighbor Table List.
     *
     * @param neighborTableList the Neighbor Table List
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNeighborTableList(final List<NeighborTable> neighborTableList) {
        this.neighborTableList = neighborTableList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(status, ZclDataType.ZDO_STATUS);
        serializer.serialize(neighborTableEntries, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(startIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(neighborTableList.size(), ZclDataType.UNSIGNED_8_BIT_INTEGER);
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
        Integer neighborTableListCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
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
        builder.append(", neighborTableList=");
        builder.append(neighborTableList);
        builder.append(']');
        return builder.toString();
    }

}
