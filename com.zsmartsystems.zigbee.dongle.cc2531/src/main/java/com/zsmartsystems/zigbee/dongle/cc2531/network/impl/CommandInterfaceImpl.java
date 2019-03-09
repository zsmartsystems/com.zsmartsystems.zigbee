/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
   Copyright 2008-2013 CNR-ISTI, http://isti.cnr.it
   Institute of Information Science and Technologies
   of the Italian National Research Council


   See the NOTICE file distributed with this work for additional
   information regarding copyright ownership

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.zsmartsystems.zigbee.dongle.cc2531.network.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.cc2531.network.AsynchronousCommandListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.CommandInterface;
import com.zsmartsystems.zigbee.dongle.cc2531.network.SynchronousCommandListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacketHandler;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacketParser;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ByteUtils;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 * ZigBeeSerialInterface is used to startup connection to ZigBee network.
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:tommi.s.e.laukkanen@gmail.com">Tommi S.E. Laukkanen</a>
 * @author <a href="mailto:christopherhattonuk@gmail.com">Chris Hatton</a>
 * @author Chris Jackson
 */
public class CommandInterfaceImpl implements ZToolPacketHandler, CommandInterface {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(CommandInterfaceImpl.class);
    /**
     * The port interface.
     */
    private final ZigBeePort port;
    /**
     * The packet parser.
     */
    private ZToolPacketParser parser;
    /**
     * Support parallel processing of different command types.
     * Only one command per command ID can be in process at a time.
     */
    private final boolean supportMultipleSynchrounsCommand = false;
    /**
     * Synchronous command listeners.
     */
    private final Hashtable<Short, SynchronousCommandListener> synchronousCommandListeners = new Hashtable<Short, SynchronousCommandListener>();
    /**
     * Asynchronous command listeners.
     */
    private final HashSet<AsynchronousCommandListener> asynchrounsCommandListeners = new HashSet<AsynchronousCommandListener>();
    /**
     * Timeout times for synchronous command listeners.
     */
    private final HashMap<SynchronousCommandListener, Long> synchronousCommandListenerTimeouts = new HashMap<SynchronousCommandListener, Long>();

    /**
     * Constructor for configuring the ZigBee Network connection parameters.
     *
     * @param port the ZigBee transport implementation.
     */
    public CommandInterfaceImpl(ZigBeePort port) {
        if (port == null) {
            throw new IllegalArgumentException("Port may not be null");
        }
        this.port = port;
    }

    /**
     * Opens connection to ZigBee Network.
     *
     * @return true if connection startup was success.
     */
    @Override
    public boolean open() {
        if (!port.open()) {
            return false;
        }
        parser = new ZToolPacketParser(port, this);
        return true;
    }

    /**
     * Closes connection to ZigBee Network.
     */
    @Override
    public void close() {
        synchronized (port) {
            if (parser != null) {
                parser.setClosing();
            }
            port.close();
            if (parser != null) {
                parser.close();
            }
        }
    }

    /* ZToolPacketHandler */

    /**
     * Exception in packet parsing.
     *
     * @param th the exception
     */
    @Override
    public void error(final Throwable th) {
        if (th instanceof IOException) {
            logger.error("IO exception in packet parsing: ", th);
        } else {
            logger.error("Unexpected exception in packet parsing: ", th);
        }
    }

    /**
     * Handle parsed packet.
     *
     * @param packet the packet
     */
    @Override
    public void handlePacket(final ZToolPacket packet) {
        final DoubleByte cmdId = packet.getCMD();
        switch (cmdId.getMsb() & 0xE0) {
            // Received incoming message which can be either message from dongle or remote device.
            case 0x40:
                logger.debug("<-- {} ({})", packet.getClass().getSimpleName(), ByteUtils.toBase16(packet.getPacket()));
                notifyAsynchronousCommand(packet);
                break;

            // Received synchronous command response.
            case 0x60:
                logger.debug("<-  {} ({})", packet.getClass().getSimpleName(), ByteUtils.toBase16(packet.getPacket()));
                notifySynchronousCommand(packet);
                break;

            default:
                logger.error("Received unknown packet. {}", packet.getClass().getSimpleName());
                break;
        }
    }

    private String frameToString(int[] inputBuffer) {
        if (inputBuffer == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int data : inputBuffer) {
            result.append(String.format("%02X ", data));
        }
        return result.toString();
    }

    /**
     * Send packet to dongle.
     *
     * @param packet the packet
     * @throws IOException if IO exception occurs while sending packet
     */
    @Override
    public void sendPacket(final ZToolPacket packet) throws IOException {
        logger.debug("->  {} ({}) ", packet.getClass().getSimpleName(), packet);
        final int[] pck = packet.getPacket();
        logger.debug("Sending frame {}", frameToString(pck));
        sendRaw(pck);
    }

    /**
     * Cleans expired synchronous command listeners.
     */
    private void cleanExpiredSynchronousCommandListeners() {
        final long now = System.currentTimeMillis();
        final ArrayList<Short> expired = new ArrayList<Short>();
        synchronized (synchronousCommandListeners) {
            final Iterator<Map.Entry<Short, SynchronousCommandListener>> i = synchronousCommandListeners.entrySet()
                    .iterator();
            while (i.hasNext()) {
                Map.Entry<Short, SynchronousCommandListener> entry = i.next();

                final long expiration = synchronousCommandListenerTimeouts.get(entry.getValue());
                if (expiration != -1L && expiration < now) {
                    expired.add(entry.getKey());
                }
            }

            for (Short key : expired) {
                synchronousCommandListeners.remove(key);
            }
            synchronousCommandListeners.notifyAll();
        }
    }

    /**
     * Sends synchronous command and adds listener.
     *
     * @param packet the command packet
     * @param listener the synchronous command response listener
     * @param timeoutMillis the timeout
     * @throws IOException if IO exception occurs in packet sending
     */
    @Override
    public void sendSynchronousCommand(final ZToolPacket packet, final SynchronousCommandListener listener,
            final long timeoutMillis) throws IOException {
        if (timeoutMillis == -1L) {
            synchronousCommandListenerTimeouts.put(listener, -1L);
        } else {
            final long expirationTime = System.currentTimeMillis() + timeoutMillis;
            synchronousCommandListenerTimeouts.put(listener, expirationTime);
        }

        final DoubleByte cmdId = packet.getCMD();
        final int value = (cmdId.getMsb() & 0xE0);
        if (value != 0x20) {
            throw new IllegalArgumentException(
                    "You are trying to send a non SREQ packet as synchronous command. " + "Evaluated " + value
                            + " instead of " + 0x20 + "\nPacket " + packet.getClass().getName() + "\n" + packet);
        }

        cleanExpiredSynchronousCommandListeners();

        if (supportMultipleSynchrounsCommand) {
            synchronized (synchronousCommandListeners) {
                final short id = (short) (cmdId.get16BitValue() & 0x1FFF);
                while (synchronousCommandListeners.get(cmdId) != null) {
                    try {
                        logger.trace("Waiting for other request {} to complete", id);
                        synchronousCommandListeners.wait(500);
                        cleanExpiredSynchronousCommandListeners();
                    } catch (InterruptedException ignored) {
                    }
                }
                synchronousCommandListeners.put(id, listener);
            }
        } else {
            synchronized (synchronousCommandListeners) {
                final short id = (short) (cmdId.get16BitValue() & 0x1FFF);
                while (!synchronousCommandListeners.isEmpty()) {
                    try {
                        logger.trace("Waiting for other request to complete");
                        synchronousCommandListeners.wait(500);
                        cleanExpiredSynchronousCommandListeners();
                    } catch (InterruptedException ignored) {
                    }
                }
                logger.trace("Put synchronousCommandListeners listener for {} command", id);
                synchronousCommandListeners.put(id, listener);
            }
        }
        logger.trace("Sending SynchronousCommand {} ", packet);
        sendPacket(packet);
    }

    /**
     * Sends asynchronous command.
     *
     * @param packet the packet.
     * @throws IOException if IO exception occurs in packet sending.
     */
    @Override
    public void sendAsynchronousCommand(final ZToolPacket packet) throws IOException {
        int value = (packet.getCMD().getMsb() & 0xE0);
        if (value != 0x40) {
            throw new IllegalArgumentException("You are trying to send a non AREQ packet. " + "Evaluated " + value
                    + " instead of " + 0x40 + "\nPacket " + packet.getClass().getName() + "\n" + packet);
        }
        sendPacket(packet);
    }

    /**
     * Send raw bytes to output stream.
     *
     * @param packet the byte buffer
     * @throws IOException if IO exception occurs when writing or flushing bytes.
     */
    @Override
    public void sendRaw(int[] packet) throws IOException {
        synchronized (port) {
            for (int i = 0; i < packet.length; i++) {
                port.write(packet[i]);
            }
        }
    }

    /**
     * Notifies listeners about synchronous command response.
     *
     * @param packet the received packet
     */
    private void notifySynchronousCommand(final ZToolPacket packet) {
        final DoubleByte cmdId = packet.getCMD();
        synchronized (synchronousCommandListeners) {
            final short id = (short) (cmdId.get16BitValue() & 0x1FFF);
            final SynchronousCommandListener listener = synchronousCommandListeners.get(id);
            if (listener != null) {
                listener.receivedCommandResponse(packet);
                synchronousCommandListeners.remove(id);
                synchronousCommandListeners.notifyAll();
            } else {
                // Notify asynchronous command listeners of unclaimed asynchronous command responses.
                final AsynchronousCommandListener[] listeners;
                synchronized (asynchrounsCommandListeners) {
                    listeners = asynchrounsCommandListeners.toArray(new AsynchronousCommandListener[] {});
                }
                for (final AsynchronousCommandListener asynchronousCommandListener : listeners) {
                    try {
                        asynchronousCommandListener.receivedUnclaimedSynchronousCommandResponse(packet);
                    } catch (Throwable e) {
                        logger.error("Error in incoming asynchronous message processing: ", e);
                    }
                }
            }

        }
    }

    /**
     * Adds asynchronous command listener.
     *
     * @param listener the listener
     * @return true if listener did not already exist.
     */
    @Override
    public boolean addAsynchronousCommandListener(AsynchronousCommandListener listener) {
        boolean result;
        synchronized (asynchrounsCommandListeners) {
            result = asynchrounsCommandListeners.add(listener);
        }
        return result;
    }

    /**
     * Removes asynchronous command listener.
     *
     * @param listener the listener
     * @return true if listener did not already exist.
     */
    @Override
    public boolean removeAsynchronousCommandListener(AsynchronousCommandListener listener) {
        boolean result;
        synchronized (asynchrounsCommandListeners) {
            result = asynchrounsCommandListeners.remove(listener);
        }
        return result;
    }

    /**
     * Notifies listeners about asynchronous message.
     *
     * @param packet the packet containing the message
     */
    private void notifyAsynchronousCommand(final ZToolPacket packet) {
        final AsynchronousCommandListener[] listeners;

        synchronized (asynchrounsCommandListeners) {
            listeners = asynchrounsCommandListeners.toArray(new AsynchronousCommandListener[] {});
        }

        logger.debug("Received Async Cmd: {}", packet);

        for (final AsynchronousCommandListener listener : listeners) {
            try {
                listener.receivedAsynchronousCommand(packet);
            } catch (Throwable e) {
                logger.error("Error in incoming asynchronous message processing: ", e);
            }
        }
    }
}
