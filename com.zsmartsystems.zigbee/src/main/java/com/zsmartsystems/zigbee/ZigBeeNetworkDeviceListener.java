package com.zsmartsystems.zigbee;

/**
 * ZigBee network listener. Provides notifications on devices - eg devices added
 * to the network, removed from the network, or updated.
 * 
 * @author Tommi S.E. Laukkanen
 */
public interface ZigBeeNetworkDeviceListener {

    /**
     * Device was added to network.
     *
     * @param device
     *            the {@link ZigBeeDevice}
     */
    void deviceAdded(final ZigBeeDevice device);

    /**
     * Device was updated.
     *
     * @param device
     *            the {@link ZigBeeDevice}
     */
    void deviceUpdated(final ZigBeeDevice device);

    /**
     * Device was removed from network.
     *
     * @param device
     *            the {@link ZigBeeDevice}
     */
    void deviceRemoved(final ZigBeeDevice device);
}
