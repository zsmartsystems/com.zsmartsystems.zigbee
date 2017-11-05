/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

/**
 * Interface for a generic port used for the ZigBee API. The stack will call the
 * interface to {@link #open} and {@link #close} the port, and to {@link read} and
 * {@link write} data.
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeePort {
    /**
     * Open the port.
     *
     * @return true if port was opened successfully.
     */
    boolean open();

    /**
     * Close the port. Closing the port should abort any read and write operations to allow a clean closure of the port.
     */
    void close();

    /**
     * Write a data byte (integer) data to serial port. This should be non-blocking.
     *
     * @param value the value to write.
     */
    void write(int value);

    /**
     * Read a value from the port. This should block until a byte is available.
     *
     * @return the data byte (integer) read from the port
     */
    int read();

    /**
     * Read a value from the port. This will block until a byte is available or the timeout period is reached.
     *
     * @param timeout the timeout in milliseconds to wait. If not data is received, -1 is returned.
     * @return the data byte (integer) read from the port
     */
    int read(int timeout);
}
