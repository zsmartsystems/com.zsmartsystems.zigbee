package com.zsmartsystems.zigbee;

/**
 * Runtime exception class for ZigBee API.
 * 
 * @author Tommi S.E. Laukkanen
 */
public class ZigBeeApiException extends RuntimeException {
    /**
     * Constructor for setting message and cause.
     *
     * @param message the message
     * @param cause the cause
     */
    public ZigBeeApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
