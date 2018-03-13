/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * Device status listener.
 * <p>
 * Listeners are called when the device status on the network is updated. Normally this occurs when a device joins or
 * leaves.
 *
 * @author Chris Jackson
 */
public interface ZigBeeAnnounceListener {

    /**
     * Called when a new device is heard on the network.
     *
     * @param address the network address of the newly announced device
     */
    void deviceStatusUpdate(final ZigBeeNodeStatus deviceStatus, final Integer networkAddress,
            final IeeeAddress ieeeAddress);
}
