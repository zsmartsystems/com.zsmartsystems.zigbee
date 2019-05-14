/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * ZigBee network listener. Provides notifications on updates to the network state
 * <p>
 * This is used to provide status updates to higher layer listeners registered through the
 * {@link ZigBeeNetworkStateListener} interface.
 * <p>
 * Valid state transitions are -:
 * <ul>
 * <li>UNITIALISED to INITIALISING or OFFLINE
 * <li>INITIALISING to ONLINE or OFFLINE
 * <li>ONLINE to OFFLINE
 * <li>OFFLINE to ONLINE
 * </ul
 *
 * @author Chris Jackson
 */
public interface ZigBeeNetworkStateListener {

    /**
     * Network state has been updated.
     *
     * @param state the updated {@link ZigBeeNetworkState}
     */
    void networkStateUpdated(final ZigBeeNetworkState state);
}
