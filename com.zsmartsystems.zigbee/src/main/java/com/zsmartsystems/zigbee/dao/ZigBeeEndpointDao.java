/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dao;

import java.util.ArrayList;
import java.util.List;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;

/**
 * This class provides a clean class to hold a data object for serialisation of a {@link ZigBeeEndpoint}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeEndpointDao {
    private String label;

    private Integer profileId;

    private int endpointId;

    /**
     * Input cluster IDs
     */
    private final List<Integer> inputClusterIds = new ArrayList<Integer>();
    /**
     * Output cluster IDs
     */
    private final List<Integer> outputClusterIds = new ArrayList<Integer>();

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public int getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(int endpointId) {
        this.endpointId = endpointId;
    }

    public List<Integer> getInputClusterIds() {
        return inputClusterIds;
    }

    public void setInputClusterIds(List<Integer> inputClusterIds) {
        this.inputClusterIds.addAll(inputClusterIds);
    }

    public List<Integer> getOutputClusterIds() {
        return outputClusterIds;
    }

    public void setOutputClusterIds(List<Integer> outputClusterIds) {
        this.outputClusterIds.addAll(outputClusterIds);
    }

    public static ZigBeeEndpointDao createFromZigBeeDevice(ZigBeeEndpoint endpoint) {
        ZigBeeEndpointDao deviceDao = new ZigBeeEndpointDao();
        deviceDao.setEndpointId(endpoint.getEndpointId());
        deviceDao.setProfileId(endpoint.getProfileId());
        deviceDao.setInputClusterIds(endpoint.getInputClusterIds());
        deviceDao.setOutputClusterIds(endpoint.getOutputClusterIds());
        return deviceDao;
    }

    public static ZigBeeEndpoint createFromZigBeeDao(ZigBeeNetworkManager networkManager, ZigBeeNode node,
            ZigBeeEndpointDao deviceDao) {
        ZigBeeEndpoint endpoint = new ZigBeeEndpoint(networkManager, node, deviceDao.endpointId);
        endpoint.setProfileId(deviceDao.getProfileId());
        endpoint.setInputClusterIds(deviceDao.getInputClusterIds());
        endpoint.setOutputClusterIds(deviceDao.getOutputClusterIds());
        return endpoint;
    }

}
