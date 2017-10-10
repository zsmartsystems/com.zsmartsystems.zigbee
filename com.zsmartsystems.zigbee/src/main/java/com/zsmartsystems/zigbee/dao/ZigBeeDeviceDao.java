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

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDeviceDao {
    private String label;

    private Integer profileId;

    private String deviceAddress;

    private String ieeeAddress;

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

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public String getIeeeAddress() {
        return ieeeAddress;
    }

    public void setIeeeAddress(String ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
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

    public static ZigBeeDeviceDao createFromZigBeeDevice(ZigBeeEndpoint device) {
        ZigBeeDeviceDao deviceDao = new ZigBeeDeviceDao();
        deviceDao.setDeviceAddress(device.getDeviceAddress().toString());
        // deviceDao.setIeeeAddress(device.getIeeeAddress().toString());
        deviceDao.setProfileId(device.getProfileId());
        deviceDao.setInputClusterIds(device.getInputClusterIds());
        deviceDao.setOutputClusterIds(device.getOutputClusterIds());
        return deviceDao;
    }

    public static ZigBeeEndpoint createFromZigBeeDao(ZigBeeNetworkManager networkManager, ZigBeeDeviceDao deviceDao) {
        /// ZigBeeNode node = networkManager.getNode(ieeeAddress)
        // ZigBeeDevice device = new ZigBeeDevice(networkManager);
        // device.setDeviceAddress(new ZigBeeDeviceAddress(deviceDao.getDeviceAddress()));
        // device.setIeeeAddress(new IeeeAddress(deviceDao.getIeeeAddress()));
        // device.setLabel(deviceDao.getLabel());
        // device.setProfileId(deviceDao.getProfileId());
        // device.setInputClusterIds(deviceDao.getInputClusterIds());
        // device.setOutputClusterIds(deviceDao.getOutputClusterIds());
        return null;// device;
    }

}
