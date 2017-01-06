package com.zsmartsystems.zigbee;

/**
 * Command listener.
 * 
 * @author Tommi S.E. Laukkanen
 */
public interface CommandListener {

    /**
     * Invoked when command has been received.
     *
     * @param command the command
     */
    void commandReceived(final Command command);

}
