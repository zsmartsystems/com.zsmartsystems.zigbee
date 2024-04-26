/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * Device status listener. This provides a notification of devices joining or leaving the network to aid discovery
 * handlers.
 * <p>
 * Listeners are called when the device status on the network is updated. Normally this occurs when a device joins or
 * leaves. Unknown devices are also notified through this interface.
 *
 * @author Chris Jackson
 */
public interface ZigBeeAnnounceListener {

    /**
     * Called when a new device is heard on the network.
     *
     * @param deviceStatus the {@link ZigBeeNodeStatus} of the newly announced device
     * @param networkAddress the network address of the newly announced device
     * @param ieeeAddress the {@link IeeeAddress} of the newly announced device
     */
    default void deviceStatusUpdate(final ZigBeeNodeStatus deviceStatus, final Integer networkAddress,
            final IeeeAddress ieeeAddress, final Integer parentNetworkAddress) {
        // Default implementation
    }

    /**
     * Called when a device is heard that is unknown to the system. This will generally mean that the device is not
     * known to the Network Manager, however it is joined to the network and should be rediscovered.
     *
     * @param networkAddress the network address of the unknown device
     */
    default void announceUnknownDevice(final Integer networkAddress) {
        // Default implementation
    }
}
