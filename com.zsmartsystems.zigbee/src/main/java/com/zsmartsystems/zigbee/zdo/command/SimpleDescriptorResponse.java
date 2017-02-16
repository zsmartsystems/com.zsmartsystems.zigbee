package com.zsmartsystems.zigbee.zdo.command;

import java.util.List;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * SimpleDescriptorResponse.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class SimpleDescriptorResponse extends ZdoResponse {

    /**
     * The profile ID.
     */
    public int profileId;
    /**
     * The device ID.
     */
    public int deviceId;
    /**
     * The device version.
     */
    public int deviceVersion;
    /**
     * The network address.
     */
    public int networkAddress;
    /**
     * The endpoint.
     */
    public int endpoint;
    /**
     * The input clusters.
     */
    private List<Integer> inputClusters;
    /**
     * The output clusters.
     */
    private List<Integer> outputClusters;

    public SimpleDescriptorResponse() {
    }

    public SimpleDescriptorResponse(int sourceAddress, int status, int profileId, int deviceId, int deviceVersion,
            int networkAddress, int endpoint, List<Integer> inputClusters, List<Integer> outputClusters) {
        this.sourceAddress = sourceAddress;
        this.status = status;
        this.profileId = profileId;
        this.deviceId = deviceId;
        this.deviceVersion = deviceVersion;
        this.networkAddress = networkAddress;
        this.endpoint = endpoint;
        this.inputClusters = inputClusters;
        this.outputClusters = outputClusters;
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

    public int getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(int endpoint) {
        this.endpoint = endpoint;
    }

    public List<Integer> getInputClusters() {
        return inputClusters;
    }

    public void setInputClusters(List<Integer> inputClusters) {
        this.inputClusters = inputClusters;
    }

    public int getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(int networkAddress) {
        this.networkAddress = networkAddress;
    }

    public List<Integer> getOutputClusters() {
        return outputClusters;
    }

    public void setOutputClusters(List<Integer> outputClusters) {
        this.outputClusters = outputClusters;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    @Override
    public String toString() {
        return "Simple Descriptor Response: deviceId=" + deviceId + ", sourceAddress=" + sourceAddress + ", status="
                + status + ", profileId=" + profileId + ", deviceVersion=" + deviceVersion + ", networkAddress="
                + networkAddress + ", endpoint=" + endpoint + ", inputClusters=" + inputClusters.toString()
                + ", outputClusters=" + outputClusters.toString();
    }
}
