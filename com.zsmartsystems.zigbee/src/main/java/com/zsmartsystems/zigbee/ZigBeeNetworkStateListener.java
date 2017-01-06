package com.zsmartsystems.zigbee;

import com.zsmartsystems.zigbee.ZigBeeNetwork.TransportState;

/**
 * ZigBee network listener. Provides notifications on updates to the network state
 * 
 * @author Tommi S.E. Laukkanen
 */
public interface ZigBeeNetworkStateListener {

    /**
     * Network state has been update.
     *
     * @param state
     *            the updated {@link TransportState}
     */
    void networkStateUpdated(final TransportState state);
}
