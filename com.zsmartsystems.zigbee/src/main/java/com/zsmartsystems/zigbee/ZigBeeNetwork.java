package com.zsmartsystems.zigbee;

import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;

/**
 * <p>
 * ZigBee network interface. It provides an interface for higher layers to receive information about the network and
 * also provides services for the {@link ZigBeeTransportTransmit} to provide network updates and incoming commands.
 * </p>
 *
 * @author Chris Jackson
 */
public interface ZigBeeNetwork {
    /**
     * <p>
     * Sends ZigBee library command without waiting for response.
     * </p>
     *
     * @param command the command
     * @return transactionId an {@link int} specifying the transaction ID for this transaction
     * @throws ZigBeeException if exception occurs in sending
     */
    int sendCommand(final Command command) throws ZigBeeException;

    /**
     * Adds ZigBee library command listener.
     *
     * @param commandListener the {@link CommandListener}
     */
    void addCommandListener(final CommandListener commandListener);

    /**
     * Removes ZigBee library command listener.
     *
     * @param commandListener the {@link CommandListener}
     */
    void removeCommandListener(final CommandListener commandListener);
}
