/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
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
     * @param node
     *            the new {@link ZigBeeNode}
     */
    void nodeAdded(final ZigBeeNode node);

    /**
     * Node was updated
     *
     * @param node
     *            the updated {@link ZigBeeNode}
     */
    void nodeUpdated(final ZigBeeNode node);

    /**
     * Node was removed
     *
     * @param node
     *            the removed {@link ZigBeeNode}
     */
    void nodeRemoved(final ZigBeeNode node);
}
