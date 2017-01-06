package com.zsmartsystems.zigbee.dongle.cc2531.network;

/**
 * Provides an interface to notify upper layers of changes to {@link ZigBeeNode nodes}.
 * <p>
 * <ul>
 * <li><i>nodeAdded</i> is called when a node is added to the network, but before discovery is completed.</li>
 * <li><i>nodeDiscovered</i> is called after discovery is completed.</li>
 * <li><i>nodeUpdated</i> is called when a node changes address.</li>
 * <li><i>nodeRemoved</i> is called when a node is removed from the network.</li>
 * </ul>
 */
public interface NodeListener {
	/**
	 * Notification that a node has been added to the list of nodes maintained within the network.
	 * <p>
	 * This is called when a node is initially added, and before any discovery has occurred. The listener
	 * can not assume that all information contained within the {@link ZigBeeNode} is complete since
	 * discovery is still taking place.
	 * @param node {@link ZigBeeNode}
	 */
    void nodeAdded(final ZigBeeNode node);
	/**
	 * Notification that a new node has completed its endpoint and descriptor discovery. When this
	 * notification is called, the listener can assume that all information contained within the
	 * {@link ZigBeeNode} is complete.
	 * @param node {@link ZigBeeNode}
	 */
    void nodeDiscovered(final ZigBeeNode node);
	/**
	 * Notification that a node has been updated within the list of nodes maintained within the network.
	 * @param node {@link ZigBeeNode}
	 */
	void nodeUpdated(final ZigBeeNode node);
	/**
	 * Notification that a node has been removed from the list of nodes maintained within the network.
	 * @param node {@link ZigBeeNode}
	 */
    void nodeRemoved(final ZigBeeNode node);
}
