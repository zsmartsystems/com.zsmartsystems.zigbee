/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * ZigBee network listener. Provides notifications on devices and nodes - eg node added
 * to the network, removed from the network, or updated.
 *
 * @author Chris Jackson
 */
public interface ZigBeeNetworkNodeListener {

    /**
     * Node was added
     *
     * @param node the new {@link ZigBeeNode}
     */
    default void nodeAdded(final ZigBeeNode node) {
        // Default implementation does nothing
    }

    /**
     * Node was updated
     *
     * @param node the updated {@link ZigBeeNode}
     */
    default void nodeUpdated(final ZigBeeNode node) {
        // Default implementation does nothing
    }

    /**
     * Node was removed
     *
     * @param node the removed {@link ZigBeeNode}
     */
    default void nodeRemoved(final ZigBeeNode node) {
        // Default implementation does nothing
    }
}
