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
package com.zsmartsystems.zigbee.dongle.cc2531.network.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.cc2531.network.AsynchronousCommandListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.CommandInterface;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;

/**
 * Blocking receiver for asynchronous commands.
 */
public class BlockingCommandReceiver implements AsynchronousCommandListener {
    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BlockingCommandReceiver.class);

    /**
     * The command interface.
     */
    private final CommandInterface commandInterface;
    /**
     * The command ID to wait for.
     */
    private final int commandId;
    /**
     * The command packet.
     */
    private ZToolPacket commandPacket = null;

    /**
     * The constructor for setting expected command ID and command interface.
     * Sets self as listener for command in command interface.
     *
     * @param commandId the command ID
     * @param commandInterface the command interface
     */
    public BlockingCommandReceiver(int commandId, CommandInterface commandInterface) {
        this.commandId = commandId;
        this.commandInterface = commandInterface;
        LOGGER.trace("Waiting for asynchronous response message {}.", commandId);
        commandInterface.addAsynchronousCommandListener(this);
    }

    /**
     * Gets command packet and blocks until the command packet is available or timeoutMillis occurs.
     * 
     * @param timeoutMillis the timeout in milliseconds
     * @return the command packet or null if time out occurs.
     */
    public ZToolPacket getCommand(final long timeoutMillis) {
        synchronized (this) {
            final long wakeUpTime = System.currentTimeMillis() + timeoutMillis;
            while (commandPacket == null && wakeUpTime > System.currentTimeMillis()) {
                try {
                    this.wait(wakeUpTime - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    LOGGER.trace("Blocking command receive timed out.", e);
                }
            }
        }
        if (commandPacket == null) {
            LOGGER.trace("Timeout {} expired and no packet with {} received", timeoutMillis, commandId);
        }
        cleanup();
        return commandPacket;
    }

    /**
     * Clean up asynchronous command listener from command interface.
     */
    public void cleanup() {
        synchronized (this) {
            commandInterface.removeAsynchronousCommandListener(this);
            this.notify();
        }
    }

    @Override
    public void receivedAsynchronousCommand(ZToolPacket packet) {
        LOGGER.trace("Received a packet {} and waiting for {}", packet.getCMD().get16BitValue(), commandId);
        LOGGER.trace("received {} {}", packet.getClass(), packet.toString());
        if (packet.isError()) {
            return;
        }
        if (packet.getCMD().get16BitValue() != commandId) {
            LOGGER.trace("Received unexpected packet: " + packet.getClass().getSimpleName());
            return;
        }
        synchronized (this) {
            commandPacket = packet;
            LOGGER.trace("Received expected response: {}", packet.getClass().getSimpleName());
            cleanup();
        }
    }

    @Override
    public void receivedUnclaimedSynchronousCommandResponse(ZToolPacket packet) {
        // Response handler not required
    }

}
