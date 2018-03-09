/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
    private static final Logger logger = LoggerFactory.getLogger(BlockingCommandReceiver.class);

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
        logger.trace("Waiting for asynchronous response message {}.", commandId);
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
                    logger.trace("Blocking command receive timed out.", e);
                }
            }
        }
        if (commandPacket == null) {
            logger.trace("Timeout {} expired and no packet with {} received", timeoutMillis, commandId);
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
        logger.trace("Received a packet {} and waiting for {}", packet.getCMD().get16BitValue(), commandId);
        logger.trace("received {} {}", packet.getClass(), packet.toString());
        if (packet.isError()) {
            return;
        }
        if (packet.getCMD().get16BitValue() != commandId) {
            logger.trace("Received unexpected packet: " + packet.getClass().getSimpleName());
            return;
        }
        synchronized (this) {
            commandPacket = packet;
            logger.trace("Received expected response: {}", packet.getClass().getSimpleName());
            cleanup();
        }
    }

    @Override
    public void receivedUnclaimedSynchronousCommandResponse(ZToolPacket packet) {
        // Response handler not required
    }

}
