/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * ZigBee network listener. Provides notifications on devices - eg devices added
 * to the network, removed from the network, or updated.
 *
 * @author Chris Jackson
 */
public interface ZigBeeNetworkEndpointListener {

    /**
     * Device was added to network.
     *
     * @param device
     *            the {@link ZigBeeEndpoint}
     */
    void deviceAdded(final ZigBeeEndpoint device);

    /**
     * Device was updated.
     *
     * @param device
     *            the {@link ZigBeeEndpoint}
     */
    void deviceUpdated(final ZigBeeEndpoint device);

    /**
     * Device was removed from network.
     *
     * @param device
     *            the {@link ZigBeeEndpoint}
     */
    void deviceRemoved(final ZigBeeEndpoint device);
}
