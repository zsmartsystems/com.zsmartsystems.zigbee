package com.zsmartsystems.zigbee;

/**
 * New Device Announcement listener.
 *
 * @author Chris Jackson
 */
public interface DeviceAnnounceListener {

    /**
     * Called when a new device is heard on the network.
     *
     * @param address the network address of the newly announced device
     */
    void deviceAnnounced(final Integer address);
}
