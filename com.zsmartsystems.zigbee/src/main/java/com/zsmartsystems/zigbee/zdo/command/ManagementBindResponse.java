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
import com.zsmartsystems.zigbee.zdo.field.BindingTable;

/**
 * Management Bind Response value object class.
 * <p>
 * The Mgmt_Bind_rsp is generated in response to a Mgmt_Bind_req. If this
 * management command is not supported, a status of NOT_SUPPORTED shall be
 * returned and all parameter fields after the Status field shall be omitted. Otherwise,
 * the Remote Device shall implement the following processing.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementBindResponse extends ZdoResponse {
    /**
     * BindingTableEntries command message field.
     */
    private Integer bindingTableEntries;

    /**
     * StartIndex command message field.
     */
    private Integer startIndex;

    /**
     * BindingTableListCount command message field.
     */
    private Integer bindingTableListCount;

    /**
     * BindingTableList command message field.
     */
    private List<BindingTable> bindingTableList;

    /**
     * Default constructor.
     */
    public ManagementBindResponse() {
        clusterId = 0x8033;
    }

    /**
     * Gets BindingTableEntries.
     *
     * @return the BindingTableEntries
     */
    public Integer getBindingTableEntries() {
        return bindingTableEntries;
    }

    /**
     * Sets BindingTableEntries.
     *
     * @param bindingTableEntries the BindingTableEntries
     */
    public void setBindingTableEntries(final Integer bindingTableEntries) {
        this.bindingTableEntries = bindingTableEntries;
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
     * Gets BindingTableListCount.
     *
     * @return the BindingTableListCount
     */
    public Integer getBindingTableListCount() {
        return bindingTableListCount;
    }

    /**
     * Sets BindingTableListCount.
     *
     * @param bindingTableListCount the BindingTableListCount
     */
    public void setBindingTableListCount(final Integer bindingTableListCount) {
        this.bindingTableListCount = bindingTableListCount;
    }

    /**
     * Gets BindingTableList.
     *
     * @return the BindingTableList
     */
    public List<BindingTable> getBindingTableList() {
        return bindingTableList;
    }

    /**
     * Sets BindingTableList.
     *
     * @param bindingTableList the BindingTableList
     */
    public void setBindingTableList(final List<BindingTable> bindingTableList) {
        this.bindingTableList = bindingTableList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(status, ZclDataType.ZDO_STATUS);
        serializer.serialize(bindingTableEntries, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(startIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(bindingTableListCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        for (int cnt = 0; cnt < bindingTableList.size(); cnt++) {
            serializer.serialize(bindingTableList.get(cnt), ZclDataType.BINDING_TABLE);
        }
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        // Create lists
        bindingTableList = new ArrayList<BindingTable>();

        status = (ZdoStatus) deserializer.deserialize(ZclDataType.ZDO_STATUS);
        if (status != ZdoStatus.SUCCESS) {
            // Don't read the full response if we have an error
            return;
        }
        bindingTableEntries = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        startIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        bindingTableListCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (bindingTableListCount != null) {
            for (int cnt = 0; cnt < bindingTableListCount; cnt++) {
                bindingTableList.add((BindingTable) deserializer.deserialize(ZclDataType.BINDING_TABLE));
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(197);
        builder.append("ManagementBindResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", bindingTableEntries=");
        builder.append(bindingTableEntries);
        builder.append(", startIndex=");
        builder.append(startIndex);
        builder.append(", bindingTableListCount=");
        builder.append(bindingTableListCount);
        builder.append(", bindingTableList=");
        builder.append(bindingTableList);
        builder.append(']');
        return builder.toString();
    }

}
