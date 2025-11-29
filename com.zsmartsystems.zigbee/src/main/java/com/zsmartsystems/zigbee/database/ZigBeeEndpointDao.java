/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;

/**
 * This class provides a clean class to hold a data object for serialisation of a {@link ZigBeeEndpoint}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeEndpointDao {
    private int endpointId;

    private Integer profileId;

    private Integer deviceId;

    private Integer deviceVersion;

    /**
     * Input cluster IDs
     */
    private final List<Integer> inputClusterIds = new ArrayList<Integer>();
    /**
     * Output cluster IDs
     */
    private final List<Integer> outputClusterIds = new ArrayList<Integer>();

    private final List<ZclClusterDao> inputClusters = new ArrayList<ZclClusterDao>();

    private final List<ZclClusterDao> outputClusters = new ArrayList<ZclClusterDao>();

    public int getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(int endpointId) {
        this.endpointId = endpointId;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceVersion(int deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public Integer getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public List<Integer> getInputClusterIds() {
        return inputClusterIds;
    }

    public void setInputClusterIds(Collection<Integer> collection) {
        this.inputClusterIds.addAll(collection);
    }

    public List<Integer> getOutputClusterIds() {
        return outputClusterIds;
    }

    public void setOutputClusterIds(Collection<Integer> collection) {
        this.outputClusterIds.addAll(collection);
    }

    public void setOutputClusters(List<ZclClusterDao> clusters) {
        outputClusters.addAll(clusters);
    }

    public List<ZclClusterDao> getOutputClusters() {
        return outputClusters;
    }

    public void setInputClusters(List<ZclClusterDao> clusters) {
        inputClusters.addAll(clusters);
    }

    public List<ZclClusterDao> getInputClusters() {
        return inputClusters;
    }
}
