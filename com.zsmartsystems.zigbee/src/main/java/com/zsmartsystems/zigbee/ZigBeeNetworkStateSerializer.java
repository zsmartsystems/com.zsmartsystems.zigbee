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
