package com.zsmartsystems.zigbee.dongle.ember.ezsp;

/**
 * Resume network operation after a reboot. The node retains its original type.
 * This should be called on startup whether or not the node was previously part
 * of a network. EMBER_NOT_JOINED is returned if the node is not part of a
 * network.
 *
 * @author Chris Jackson
 *
 */
public class EzspNetworkInitResponse extends EzspFrameResponse {
    /**
     * Creates an EZSP <i>networkInit</i> from a response frame
     * 
     * @param inputBuffer
     *            the received response data
     */
    public EzspNetworkInitResponse(int[] inputBuffer) {
        super(inputBuffer);

        emberStatus = inputEmberStatus();
    }

    @Override
    public String toString() {
        return "EzspNetworkInit RESPONSE [" + emberStatus + "]";
    }
}
