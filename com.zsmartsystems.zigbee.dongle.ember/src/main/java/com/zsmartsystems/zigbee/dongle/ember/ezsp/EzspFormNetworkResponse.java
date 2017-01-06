package com.zsmartsystems.zigbee.dongle.ember.ezsp;

/**
 * Forms a new network by becoming the coordinator.
 * 
 * @author Chris Jackson
 *
 */
public class EzspFormNetworkResponse extends EzspFrameResponse {
    /**
     * 
     * @param inputBuffer
     *            the received response data
     */
    public EzspFormNetworkResponse(int[] inputBuffer) {
        super(inputBuffer);

        emberStatus = inputEmberStatus();
    }

    @Override
    public String toString() {
        return "EzspFormNetwork RESPONSE [" + emberStatus + "]";
    }
}
