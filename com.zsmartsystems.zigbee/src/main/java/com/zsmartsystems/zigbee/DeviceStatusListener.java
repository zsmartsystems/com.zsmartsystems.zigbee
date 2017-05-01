package com.zsmartsystems.zigbee;

/**
 * Device status listener.
 * <p>
 * Listeners are called when the device status on the network is updated. Normally this occurs when a device joins or
 * leaves.
 *
 * @author Chris Jackson
 */
public interface DeviceStatusListener {

    /**
     * Called when a new device is heard on the network.
     *
     * @param address the network address of the newly announced device
     */
    void deviceStatusUpdate(final ZigBeeDeviceStatus deviceStatus, final Integer networkAddress,
            final IeeeAddress ieeeAddress);
}
