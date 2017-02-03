package com.zsmartsystems.zigbee;

/**
 * ZigBee network listener. Provides notifications on updates to the network state
 *
 * @author Chris Jackson
 */
public interface ZigBeeNetworkStateListener {

    /**
     * Network state has been updated.
     *
     * @param state
     *            the updated {@link TransportState}
     */
    void networkStateUpdated(final ZigBeeTransportState state);
}
