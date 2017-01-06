package com.zsmartsystems.zigbee.dongle.ember.ezsp;

/**
 * A callback invoked when the status of the stack changes. If the status parameter equals EMBER_NETWORK_UP, then the
 * getNetworkParameters command can be called to obtain the new network parameters. If any of the parameters are being
 * stored in nonvolatile memory by the Host, the stored values should be updated.
 *
 * @author Chris Jackson
 *
 */
public class EzspStackStatusHandlerResponse extends EzspFrameResponse {
    /**
     * Creates an EZSP <i>stackStatusHandler</i> from a response frame
     * 
     * @param inputBuffer
     *            the received response data
     */
    public EzspStackStatusHandlerResponse(int[] inputBuffer) {
        super(inputBuffer);

        emberStatus = inputEmberStatus();
    }

    @Override
    public String toString() {
        return "EzspStackStatusHandler RESPONSE [" + emberStatus + "]";
    }
}
