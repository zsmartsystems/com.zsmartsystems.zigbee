/**
 * Copyright 2013 Tommi S.E. Laukkanen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
