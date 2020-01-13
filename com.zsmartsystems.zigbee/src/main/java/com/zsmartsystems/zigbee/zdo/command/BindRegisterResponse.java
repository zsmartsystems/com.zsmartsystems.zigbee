/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zdo.field.BindingTable;

/**
 * Bind Register Response value object class.
 * <p>
 * <p>
 * The Bind_Register_rsp is generated from a primary binding table cache device in response to
 * a Bind_Register_req and contains the status of the request. This command shall be unicast to
 * the requesting device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:33:13Z")
public class BindRegisterResponse extends ZdoResponse {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x8023;

    /**
     * Binding Table Entries command message field.
     */
    private Integer bindingTableEntries;

    /**
     * Binding Table List command message field.
     */
    private List<BindingTable> bindingTableList;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public BindRegisterResponse() {
        clusterId = CLUSTER_ID;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param status {@link ZdoStatus} Status
     * @param bindingTableEntries {@link Integer} Binding Table Entries
     * @param bindingTableList {@link List<BindingTable>} Binding Table List
     */
    public BindRegisterResponse(
            ZdoStatus status,
            Integer bindingTableEntries,
            List<BindingTable> bindingTableList) {

        clusterId = CLUSTER_ID;

        this.status = status;
        this.bindingTableEntries = bindingTableEntries;
        this.bindingTableList = bindingTableList;
    }

    /**
     * Gets Binding Table Entries.
     *
     * @return the Binding Table Entries
     */
    public Integer getBindingTableEntries() {
        return bindingTableEntries;
    }

    /**
     * Sets Binding Table Entries.
     *
     * @param bindingTableEntries the Binding Table Entries
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setBindingTableEntries(final Integer bindingTableEntries) {
        this.bindingTableEntries = bindingTableEntries;
    }

    /**
     * Gets Binding Table List.
     *
     * @return the Binding Table List
     */
    public List<BindingTable> getBindingTableList() {
        return bindingTableList;
    }

    /**
     * Sets Binding Table List.
     *
     * @param bindingTableList the Binding Table List
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setBindingTableList(final List<BindingTable> bindingTableList) {
        this.bindingTableList = bindingTableList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(status, ZclDataType.ZDO_STATUS);
        serializer.serialize(bindingTableEntries, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(bindingTableList.size(), ZclDataType.UNSIGNED_16_BIT_INTEGER);
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
        bindingTableEntries = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        Integer bindingTableListCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        if (bindingTableListCount != null) {
            for (int cnt = 0; cnt < bindingTableListCount; cnt++) {
                bindingTableList.add((BindingTable) deserializer.deserialize(ZclDataType.BINDING_TABLE));
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
        builder.append(", bindingTableList=");
        builder.append(bindingTableList);
        builder.append(']');
        return builder.toString();
    }

}
