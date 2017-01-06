package com.zsmartsystems.zigbee;

/**
 * ZigBee network listener. Provides notifications on devices and nodes - eg devices added
 * to the network, removed from the network, or updated.
 * 
 * @author Tommi S.E. Laukkanen
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
