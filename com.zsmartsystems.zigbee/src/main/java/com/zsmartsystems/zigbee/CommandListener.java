package com.zsmartsystems.zigbee;

/**
 * Command listener.
 *
 * @author Tommi S.E. Laukkanen
 */
public interface CommandListener {

    /**
     * Called when a command has been received.
     *
     * @param command the received {@link Command}
     */
    void commandReceived(final Command command);
}
