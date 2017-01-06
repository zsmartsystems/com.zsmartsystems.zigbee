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
public class EzspNetworkInitRequest extends EzspFrameRequest {
    /**
     * Creates an EZSP <i>networkInit</i> request frame
     *
     */
    public EzspNetworkInitRequest() {
        super(FRAME_ID_NETWORK_INIT);
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

    @Override
    public String toString() {
        return "EzspNetworkInit [" + emberStatus + "]";
    }
}
