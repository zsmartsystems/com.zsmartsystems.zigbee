package com.zsmartsystems.zigbee;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface for a generic port used for the ZigBee API. The stack will call the
 * interface to open and close the port, and to get the {@link OutputStream} and
 * {@link InputStream} for reading and writing.
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
     * Close the port.
     */
    void close();

    /**
     * Gets output stream.
     * 
     * @return the output stream
     */
    OutputStream getOutputStream();

    /**
     * Gets input stream.
     * 
     * @return the input stream
     */
    InputStream getInputStream();
}
