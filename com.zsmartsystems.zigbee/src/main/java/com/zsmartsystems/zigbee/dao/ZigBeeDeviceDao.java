package com.zsmartsystems.zigbee.dao;

import java.util.ArrayList;
import java.util.List;

import com.zsmartsystems.zigbee.ZigBeeDevice;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
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

    public static ZigBeeDeviceDao createFromZigBeeDevice(ZigBeeDevice device) {
        ZigBeeDeviceDao deviceDao = new ZigBeeDeviceDao();
        deviceDao.setDeviceAddress(device.getDeviceAddress().toString());
        deviceDao.setLabel(device.getLabel());
        deviceDao.setProfileId(device.getProfileId());
        deviceDao.setInputClusterIds(device.getInputClusterIds());
        deviceDao.setOutputClusterIds(device.getOutputClusterIds());
        return deviceDao;
    }

    public static ZigBeeDevice createFromZigBeeDao(ZigBeeNetworkManager networkManager, ZigBeeDeviceDao deviceDao) {
        ZigBeeDevice device = new ZigBeeDevice(networkManager);
        device.setDeviceAddress(new ZigBeeDeviceAddress(deviceDao.getDeviceAddress()));
        device.setLabel(deviceDao.getLabel());
        device.setProfileId(deviceDao.getProfileId());
        device.setInputClusterIds(deviceDao.getInputClusterIds());
        device.setOutputClusterIds(deviceDao.getOutputClusterIds());
        return device;
    }

}
