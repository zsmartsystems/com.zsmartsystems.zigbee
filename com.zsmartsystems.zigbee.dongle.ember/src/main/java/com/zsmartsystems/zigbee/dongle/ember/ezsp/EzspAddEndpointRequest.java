package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import java.util.Arrays;

/**
 * Configures endpoint information on the NCP. The NCP does not remember these settings after a reset. Endpoints can be
 * added by the Host after the NCP has reset. Once the status of the stack changes to EMBER_NETWORK_UP, endpoints can no
 * longer be added and this command will respond with EZSP_ERROR_INVALID_CALL.
 *
 * @author Chris Jackson
 *
 */
public class EzspAddEndpointRequest extends EzspFrameRequest {
    /**
     * The application endpoint to be added.
     */
    private int endpoint;

    /**
     * The endpoint's application profile.
     */
    private int profileId;

    /**
     * The endpoint's device ID within the application profile.
     */
    private int deviceId;

    /**
     * The device version and flags indicating description availability.
     */
    private int appFlags;

    /**
     * Input cluster IDs the endpoint will accept.
     */
    private int[] inputClusterList;

    /**
     * Output cluster IDs the endpoint may send.
     */
    private int[] outputClusterList;

    /**
     * Creates an EZSP <i>addEndpoint</i> request frame
     * 
     */
    public EzspAddEndpointRequest() {
        super(FRAME_ID_ADD_ENDPOINT);
    }

    @Override
    public int[] getOutputBuffer() {
        initialiseOutputBuffer();
        outputUInt8(endpoint);
        outputUInt16(profileId);
        outputUInt16(deviceId);
        outputUInt8(appFlags);
        outputUInt8(inputClusterList.length);
        outputUInt8(outputClusterList.length);
        outputUInt16Array(inputClusterList);
        outputUInt16Array(outputClusterList);

        return Arrays.copyOfRange(buffer, 0, length);
    }

    @Override
    public boolean processResponse(EzspFrameResponse ezspResponse) {
        if (getSequenceNumber() != ezspResponse.getSequenceNumber()) {
            return false;
        }
        if (getFrameId() != ezspResponse.getFrameId()) {
            return false;
        }

        emberStatus = ezspResponse.getEmberStatus();

        return true;
    }

    /**
     * @return the endpoint
     */
    public int getEndpoint() {
        return endpoint;
    }

    /**
     * @param endpoint the endpoint to set
     */
    public void setEndpoint(int endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @return the profileId
     */
    public int getProfileId() {
        return profileId;
    }

    /**
     * @param profileId the profileId to set
     */
    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    /**
     * @return the deviceId
     */
    public int getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the appFlags
     */
    public int getAppFlags() {
        return appFlags;
    }

    /**
     * @param appFlags the appFlags to set
     */
    public void setAppFlags(int appFlags) {
        this.appFlags = appFlags;
    }

    /**
     * @return the inputClusterList
     */
    public int[] getInputClusterList() {
        return inputClusterList;
    }

    /**
     * @param inputClusterList the inputClusterList to set
     */
    public void setInputClusterList(int[] inputClusterList) {
        this.inputClusterList = inputClusterList;
    }

    /**
     * @return the outputClusterList
     */
    public int[] getOutputClusterList() {
        return outputClusterList;
    }

    /**
     * @param outputClusterList the outputClusterList to set
     */
    public void setOutputClusterList(int[] outputClusterList) {
        this.outputClusterList = outputClusterList;
    }

    @Override
    public String toString() {
        return "EzspAddEndpointRequest [" + emberStatus + ": endpoint=" + endpoint + ", profileId=" + profileId
                + ", deviceId=" + deviceId + ", appFlags=" + appFlags + ", inputClusterList="
                + Arrays.toString(inputClusterList) + ", outputClusterList=" + Arrays.toString(outputClusterList) + "]";
    }
}
