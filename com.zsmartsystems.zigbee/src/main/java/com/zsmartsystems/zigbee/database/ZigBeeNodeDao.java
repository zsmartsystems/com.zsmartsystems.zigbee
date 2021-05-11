/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.database;

import java.util.List;
import java.util.Set;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zdo.field.BindingTable;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor;

/**
 * This class provides a clean class to hold a data object for serialisation of a {@link ZigBeeNode}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNodeDao {
    /**
     * The extended {@link IeeeAddress} for the node
     */
    private IeeeAddress ieeeAddress;

    /**
     * The 16 bit network address for the node
     */
    private Integer networkAddress;

    /**
     * The MAC capability flags field is eight bits in length and specifies the node capabilities, as required by the
     * IEEE 802.15.4-2003 MAC sub-layer.
     */
    private Set<NodeDescriptor.MacCapabilitiesType> macCapabilities;

    /**
     * The {@link NodeDescriptor} for the node
     */
    private NodeDescriptor nodeDescriptor;

    /**
     * The {@link PowerDescriptor} for the node
     */
    private PowerDescriptor powerDescriptor;

    /**
     * The list of endpoints for this node
     */
    private List<ZigBeeEndpointDao> endpoints;

    private Set<BindingTable> bindingTable;

    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    public void setIeeeAddress(IeeeAddress ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    public Integer getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(Integer networkAddress) {
        this.networkAddress = networkAddress;
    }

    public Set<NodeDescriptor.MacCapabilitiesType> getMacCapabilities() {
        return macCapabilities;
    }

    public void setMacCapabilities(Set<NodeDescriptor.MacCapabilitiesType> macCapabilities) {
        this.macCapabilities = macCapabilities;
    }

    public NodeDescriptor getNodeDescriptor() {
        return nodeDescriptor;
    }

    public void setNodeDescriptor(NodeDescriptor nodeDescriptor) {
        this.nodeDescriptor = nodeDescriptor;
    }

    public PowerDescriptor getPowerDescriptor() {
        return powerDescriptor;
    }

    public void setPowerDescriptor(PowerDescriptor powerDescriptor) {
        this.powerDescriptor = powerDescriptor;
    }

    public void setEndpoints(List<ZigBeeEndpointDao> endpointDaoList) {
        this.endpoints = endpointDaoList;
    }

    public List<ZigBeeEndpointDao> getEndpoints() {
        return endpoints;
    }

    public void setBindingTable(Set<BindingTable> bindingTable) {
        this.bindingTable = bindingTable;
    }

    public Set<BindingTable> getBindingTable() {
        return bindingTable;
    }
}
