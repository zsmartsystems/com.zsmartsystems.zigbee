/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.cc2531.network;

import java.io.IOException;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;

/**
 * The command interface for sending and receiving ZNP serial interface commands.
 */
public interface CommandInterface {
    /**
     * Opens the command interface.
     * @return true if open was successful.
     */
    boolean open();
    /**
     * Closes command interface.
     */
    void close();

    /**
     * Sends packet.
     * @param packet the packet
     * @throws IOException if IO exception occurs in sending
     */
    void sendPacket(ZToolPacket packet)
            throws IOException;
    /**
     * Sends synchronous command packet.
     * @param packet the command packet
     * @param listener the synchronous command listener
     * @param timeoutMillis the timeout in milliseconds.
     * @throws IOException if IO exception occurs in sending
     */
    void sendSynchronousCommand(ZToolPacket packet, SynchronousCommandListener listener, long timeoutMillis)
            throws IOException;
    /**
     * Sends asynchronous command packet.
     * @param packet the command packet
     * @throws IOException if IO exception occurs in sending
     */
    void sendAsynchronousCommand(ZToolPacket packet) throws IOException;
    /**
     * Sends raw command packet
     * @param packet the raw packet
     * @throws IOException if IO exception occurs in sending
     */
    void sendRaw(int[] packet) throws IOException;
    /**
     * Adds asynchronous command listener
     * @param listener the asynchronous command listener
     * @return true if listener add was successful
     */
    boolean addAsynchronousCommandListener(AsynchronousCommandListener listener);
    /**
     * Removes asynchronous command listener
     * @param listener the asynchronous command listener
     * @return true if listener remove was successful
     */
    boolean removeAsynchronousCommandListener(AsynchronousCommandListener listener);
}
