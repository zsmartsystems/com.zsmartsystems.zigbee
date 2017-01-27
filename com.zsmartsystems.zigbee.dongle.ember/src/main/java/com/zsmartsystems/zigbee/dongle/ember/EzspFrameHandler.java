package com.zsmartsystems.zigbee.dongle.ember;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;

/**
 * Interface to exchange asynchronous packets and link state changes from the
 * ASH handler to the EZSP layer.
 *
 * @author Chris Jackson
 *
 */
public interface EzspFrameHandler {
    /**
     * Passes received asynchronous frames from the ASH handler to the EZSP
     * layer
     *
     * @param response
     *            incoming {@link EzspFrame} response frame
     */
    public void handlePacket(EzspFrame response);

    /**
     * Called when the ASH link state changes
     *
     * @param state
     *            true if the link is UP, false if the link is DOWN
     */
    public void handleLinkStateChange(boolean state);
}
