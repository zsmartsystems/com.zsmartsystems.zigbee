/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * Defines an interface to serialize and deserialize the network state.
 * 
 * @author Chris Jackson
 *
 */
public interface ZigBeeNetworkStateSerializer {

    /**
     * Serializes the network state.
     *
     * @param networkState the network state
     */
    public void serialize(final ZigBeeNetworkManager networkState);

    /**
     * Deserializes the network state.
     *
     * @param networkState the network state
     */
    public void deserialize(final ZigBeeNetworkManager networkState);
}
