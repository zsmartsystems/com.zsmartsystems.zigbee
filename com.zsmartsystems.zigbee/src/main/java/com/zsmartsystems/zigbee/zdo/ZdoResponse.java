package com.zsmartsystems.zigbee.zdo;

/**
 * Common interface for response commands.
 * 
 * @author Tommi S.E. Laukkanen
 */
public interface ZdoResponse {

    /**
     * Gets the source address
     *
     * @return the source address
     */
    int getSourceAddress();

    /**
     * Gets the response status
     *
     * @return the response status
     */
    int getStatus();

}
