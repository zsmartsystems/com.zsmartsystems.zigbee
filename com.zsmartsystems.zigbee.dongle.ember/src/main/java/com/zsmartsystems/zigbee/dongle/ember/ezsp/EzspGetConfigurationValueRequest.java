package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import java.util.Arrays;

/**
 * Reads a configuration value from the NCP.
 * 
 * @author Chris Jackson
 *
 */
public class EzspGetConfigurationValueRequest extends EzspFrameRequest {
    /**
     * Identifies which configuration value to read.
     */
    private int configId;

    /**
     * 
     * @param configId
     *            Identifies which configuration value to read.
     */
    public EzspGetConfigurationValueRequest() {
        super(FRAME_ID_GET_CONFIGURATION_VALUE);
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public int getConfigId() {
        return configId;
    }

    @Override
    public int[] getOutputBuffer() {
        initialiseOutputBuffer();
        outputUInt8(configId);
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

        response = ezspResponse;
        emberStatus = ezspResponse.getEmberStatus();

        return true;
    }

    @Override
    public String toString() {
        return "EzspGetConfigurationValue [" + emberStatus + ": configId=" + configId
                + ((response != null) ? (", value=" + ((EzspGetConfigurationValueResponse) response).getValue()) : "")
                + "]";
    }
}
