package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import java.util.Arrays;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters;

/**
 * Forms a new network by becoming the coordinator.
 * 
 * @author Chris Jackson
 *
 */
public class EzspFormNetworkRequest extends EzspFrameRequest {
    /**
     * Identifies which configuration value to read.
     */
    private EmberNetworkParameters parameters;

    /**
     * 
     * @param configId
     *            Identifies which configuration value to read.
     */
    public EzspFormNetworkRequest() {
        super(FRAME_ID_FORM_NETWORK);
    }

    @Override
    public int[] getOutputBuffer() {
        initialiseOutputBuffer();
        outputStructure(parameters);
        return Arrays.copyOfRange(buffer, 0, length);
    }

    @Override
    public boolean processResponse(EzspFrameResponse response) {
        if (initialEzspResponse(response) == false) {
            return false;
        }

        if (getFrameId() != response.getFrameId()) {
            return false;
        }

        emberStatus = response.getEmberStatus();
        return true;
    }

    public void setParameters(EmberNetworkParameters parameters) {
        this.parameters = parameters;
    }

    public EmberNetworkParameters getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "EzspFormNetwork [" + emberStatus + ": parameters=" + parameters + "]";
    }
}
