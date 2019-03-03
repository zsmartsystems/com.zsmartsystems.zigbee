/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.database;

import java.util.Set;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 * Interface to be implemented by users providing a node database for persisting device data outside of the framework.
 * <p>
 * ZigBee coordinators do not store any information about devices that have joined the network. In order to provide a
 * continuous service, information about nodes that have joined needs to be persisted between restarts of the framework.
 * <p>
 * The underlying data store implementation must be able to store and retrieve node data with the
 * {@link #writeNode(ZigBeeNodeDao)} and {@link #readNode(IeeeAddress)} methods, and also provide a list of all nodes
 * currently in the store with the {@link #readNetworkNodes()} method.
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeeNetworkDataStore {
    /**
     * Reads the list of nodes that are currently included in network. The underlying data store should return the list
     * of all nodes that have been stored with {@link #writeNode(ZigBeeNodeDao)}. If {@link #removeNode(IeeeAddress)}
     * has subsequently been called, the node shall not be returned in this Set.
     *
     * @return nodes the {@link Set} of {@link IeeeAddress}s of all nodes currently included in the network. Must not
     *         return null - if no nodes are currently in the network, return an empty Set.
     */
    Set<IeeeAddress> readNetworkNodes();

    /**
     * Called when the library wants to restore the saved information about a node. This is normally only done on system
     * startup.
     *
     * @param address the {@link IeeeAddress} of the node to retrieve
     * @return the {@link ZigBeeNodeDao} containing the node data. May return null if the node is not found in the
     *         database.
     */
    ZigBeeNodeDao readNode(IeeeAddress address);

    /**
     * Called when information about a node has been updated, and the node must persist the node data to non-volatile
     * storage.
     *
     * @param node the {@link ZigBeeNodeDao} to be persisted
     */
    void writeNode(ZigBeeNodeDao node);

    /**
     * Called when a node has been removed from the network. It is expected that the database implementation will remove
     * this data from the storage.
     *
     * @param address the {@link IeeeAddress} of the node to remove
     */
    void removeNode(IeeeAddress address);
}
