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
 * Bind Register Response value object class.
 * <p>
 * The Bind_Register_rsp is generated from a primary binding table cache device in
 * response to a Bind_Register_req and contains the status of the request. This
 * command shall be unicast to the requesting device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class BindRegisterResponse extends ZdoResponse {
    /**
     * BindingTableEntries command message field.
     */
    private Integer bindingTableEntries;

    /**
     * BindingTableListCount command message field.
     */
    private Integer bindingTableListCount;

    /**
     * BindingTableList command message field.
     */
    private List<List<BindingTable>> bindingTableList;

    /**
     * Default constructor.
     */
    public BindRegisterResponse() {
        clusterId = 0x8023;
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
    public List<List<BindingTable>> getBindingTableList() {
        return bindingTableList;
    }

    /**
     * Sets BindingTableList.
     *
     * @param bindingTableList the BindingTableList
     */
    public void setBindingTableList(final List<List<BindingTable>> bindingTableList) {
        this.bindingTableList = bindingTableList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(status, ZclDataType.ZDO_STATUS);
        serializer.serialize(bindingTableEntries, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(bindingTableListCount, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        for (int cnt = 0; cnt < bindingTableList.size(); cnt++) {
            serializer.serialize(bindingTableList.get(cnt), ZclDataType.N_X_BINDING_TABLE);
        }
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        // Create lists
        bindingTableList = new ArrayList<List<BindingTable>>();

        status = (ZdoStatus) deserializer.deserialize(ZclDataType.ZDO_STATUS);
        if (status != ZdoStatus.SUCCESS) {
            // Don't read the full response if we have an error
            return;
        }
        bindingTableEntries = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        bindingTableListCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        if (bindingTableListCount != null) {
            for (int cnt = 0; cnt < bindingTableListCount; cnt++) {
                bindingTableList.add((List<BindingTable>) deserializer.deserialize(ZclDataType.N_X_BINDING_TABLE));
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(165);
        builder.append("BindRegisterResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", bindingTableEntries=");
        builder.append(bindingTableEntries);
        builder.append(", bindingTableListCount=");
        builder.append(bindingTableListCount);
        builder.append(", bindingTableList=");
        builder.append(bindingTableList);
        builder.append(']');
        return builder.toString();
    }

}
