/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;

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
