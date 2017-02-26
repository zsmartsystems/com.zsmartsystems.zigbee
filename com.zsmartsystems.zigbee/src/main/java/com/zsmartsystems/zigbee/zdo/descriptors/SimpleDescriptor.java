package com.zsmartsystems.zigbee.zdo.descriptors;

import java.util.List;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * The simple descriptor contains information specific to each endpoint contained in
 * this node. The simple descriptor is mandatory for each endpoint present in the
 * node.
 *
 * @author Chris Jackson
 *
 */
public class SimpleDescriptor {

    private int endpoint;

    private int profileId;

    private int deviceId;

    private int deviceVersion;

    private List<Integer> inputClusterList;

    private List<Integer> outputClusterList;

    /**
     * Deserialise the contents of the structure.
     *
     * @param deserializer the {@link ZigBeeDeserializer} used to deserialize
     */
    public void deserialize(ZigBeeDeserializer deserializer) {
        // Deserialize the fields
        endpoint = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        profileId = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        deviceId = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        deviceVersion = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        inputClusterList = (List<Integer>) deserializer.readZigBeeType(ZclDataType.N_X_UNSIGNED_16_BIT_INTEGER);
        outputClusterList = (List<Integer>) deserializer.readZigBeeType(ZclDataType.N_X_UNSIGNED_16_BIT_INTEGER);
    }

    public int getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(int endpoint) {
        this.endpoint = endpoint;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(int deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public List<Integer> getInputClusterList() {
        return inputClusterList;
    }

    public void setInputClusterList(List<Integer> inputClusterList) {
        this.inputClusterList = inputClusterList;
    }

    public List<Integer> getOutputClusterList() {
        return outputClusterList;
    }

    public void setOutputClusterList(List<Integer> outputClusterList) {
        this.outputClusterList = outputClusterList;
    }

    @Override
    public String toString() {
        return "SimpleDescriptor [endpoint=" + endpoint + ", profileId=" + profileId + ", deviceId=" + deviceId
                + ", deviceVersion=" + deviceVersion + ", inputClusterList=" + inputClusterList + ", outputClusterList="
                + outputClusterList + "]";
    }

}
