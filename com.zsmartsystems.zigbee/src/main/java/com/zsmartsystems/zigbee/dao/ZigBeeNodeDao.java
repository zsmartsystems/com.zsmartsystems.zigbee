/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dao;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNodeDao {
    /**
     * The extended {@link IeeeAddress} for the node
     */
    private String ieeeAddress;

    /**
     * The 16 bit network address for the node
     */
    private Integer networkAddress;

    /**
     * The {@link NodeDescriptor} for the node
     */
    private NodeDescriptor nodeDescriptor;

    /**
     * The {@link PowerDescriptor} for the node
     */
    private PowerDescriptor powerDescriptor;

    public String getIeeeAddress() {
        return ieeeAddress;
    }

    public void setIeeeAddress(String ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    public Integer getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(Integer networkAddress) {
        this.networkAddress = networkAddress;
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

    public static ZigBeeNodeDao createFromZigBeeNode(ZigBeeNode node) {
        ZigBeeNodeDao nodeDao = new ZigBeeNodeDao();
        nodeDao.setIeeeAddress(node.getIeeeAddress().toString());
        nodeDao.setNetworkAddress(node.getNetworkAddress());
        nodeDao.setNodeDescriptor(node.getNodeDescriptor());
        nodeDao.setPowerDescriptor(node.getPowerDescriptor());

        return nodeDao;
    }

    public static ZigBeeNode createFromZigBeeDao(ZigBeeNetworkManager networkManager, ZigBeeNodeDao nodeDao) {
        ZigBeeNode node = new ZigBeeNode(networkManager);
        node.setIeeeAddress(new IeeeAddress(nodeDao.getIeeeAddress()));
        node.setNetworkAddress(nodeDao.getNetworkAddress());
        node.setNodeDescriptor(nodeDao.getNodeDescriptor());
        node.setPowerDescriptor(nodeDao.getPowerDescriptor());

        return node;
    }
}
