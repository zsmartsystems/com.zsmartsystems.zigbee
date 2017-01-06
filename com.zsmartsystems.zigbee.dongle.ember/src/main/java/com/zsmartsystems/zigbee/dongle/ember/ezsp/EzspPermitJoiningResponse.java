package com.zsmartsystems.zigbee.dongle.ember.ezsp;

/**
 * The command allows the Host to specify the desired EZSP version and must be
 * sent before any other command. The response provides information about the
 * firmware running on the NCP.
 *
 * @author Chris Jackson
 *
 */
public class EzspPermitJoiningResponse extends EzspFrameResponse {
    /**
     * 
     * @param inputBuffer
     *            the received response data
     */
    public EzspPermitJoiningResponse(int[] inputBuffer) {
        super(inputBuffer);

        emberStatus = inputEmberStatus();
    }

    @Override
    public String toString() {
        return "EzspPermitJoining RESPONSE [" + emberStatus + "]";
    }
}
