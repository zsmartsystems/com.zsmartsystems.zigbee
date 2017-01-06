package com.zsmartsystems.zigbee.dongle.cc2531.network;

/**
 * Provides an interface to notify upper layers of changes to {@link ZigBeeEndpoint endpoints}.
 */
public interface EndpointListener {
    /**
     * Called when endpoint has been added.
     * @param endpoint the endpoint
     */
    void endpointAdded(final ZigBeeEndpoint endpoint);
    /**
     * Called when endpoint has been updated.
     * @param endpoint the endpoint
     */
    void endpointUpdated(final ZigBeeEndpoint endpoint);
    /**
     * Called when endpoint has been removed.
     * @param endpoint the endpoint
     */
    void endpointRemoved(final ZigBeeEndpoint endpoint);
}
