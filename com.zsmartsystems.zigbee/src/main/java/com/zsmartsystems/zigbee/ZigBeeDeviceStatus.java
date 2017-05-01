package com.zsmartsystems.zigbee;

/**
 * Enum defining the state of a device when joining or leaving
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeDeviceStatus {

    /**
     * A device has joined the network without security
     */
    UNSECURED_JOIN,

    /**
     * A device has securely rejoined the network
     */
    SECURED_REJOIN,

    /**
     * A device has unsecurely rejoined the network
     */
    UNSECURED_REJOIN,

    /**
     * A device has left the network
     */
    DEVICE_LEFT

    ;

}
