package com.zsmartsystems.zigbee;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;

/**
 * Defines the interface for data passed to the transport layer (ie dongle) from the ZigBee stack framework.
 * <p>
 * ZigBee transport interface implemented by different hardware drivers. This could support for example serial
 * interfaces for dongles, or IP connections to remote interfaces.
 * <p>
 * The ZCL interface allows the stack to specify the NWK (Network) header, the APS (Application Support Sublayer) and
 * the payload. The headers are provided separately to allow the framework to specify the configuration in some detail,
 * while allowing the transport implementation (eg dongle) to format the data as per its needs. The payload is
 * serialised by the framework using the {@link ZigBeeSerializer} and {@link ZigBeeDeserializer} interfaces, thus
 * allowing the format to be set for different hardware implementations.
 * <p>
 * The ZDO interface exchanges only command classes. This is different to the ZCL interface since different sticks
 * tend to implement ZDO functionality as individual commands rather than allowing a binary ZDO packet to be sent and
 * received.
 *
 * @author Chris Jackson
 */
public interface ZigBeeTransportTransmit {
    /**
     * Initialize the transport interface. Following the call to initialize the configuration methods may be used to
     * configure the transport layer.
     *
     * @return true if initialization was success.
     */
    boolean initialize();

    /**
     * Starts the transport interface.
     *
     * @return true if startup was success.
     */
    boolean startup();

    /**
     * Shuts down a transport interface.
     */
    void shutdown();

    /**
     * Sends ZigBee Cluster Library command without waiting for response. Responses are provided to the framework
     * through the {@link ZigBeeNetwork#receiveZclCommand} callback.
     * <p>
     * This method must return instantly - it should NOT wait for a response.
     * <p>
     * The ZCL method allows the stack to specify the NWK (Network) header, the APS (Application Support Sublayer) and
     * the payload. The headers are provided separately to allow the framework to specify the configuration in some
     * detail, while allowing the transport implementation (eg dongle) to format the data as per its needs. The payload
     * is serialised by the framework using the {@link ZigBeeSerializer} interface, thus allowing the format to be set
     * for different hardware implementations.
     *
     * @param apsFrame the {@link ZigBeeApsFrame} to be sent
     * @return transaction ID
     * @throws {@link ZigBeeException} if exception occurs in sending
     */
    void sendCommand(final ZigBeeApsFrame apsFrame) throws ZigBeeException;

    /**
     * The ZDO interface exchanges only command classes. This is different to the ZCL interface since different sticks
     * tend to implement ZDO functionality as individual commands rather than allowing a binary ZDO packet to be sent
     * and received.
     *
     * @param command the {@link ZdoCommand} to send
     * @throws {@link ZigBeeException} if exception occurs in sending
     */
    // void sendZdoCommand(final ZdoCommand command) throws ZigBeeException;

    /**
     * Sets the {@link ZigBeeTransportReceive}. Set by the network so that the {@link ZigBeeTransportTransmit} can send
     * callback messages to the network when a command is received or network state changes.
     *
     * @param zigbeeTransportReceive the {@link ZigBeeTransportReceive}
     */
    void setZigBeeTransportReceive(ZigBeeTransportReceive zigbeeTransportReceive);

    /**
     * Gets the current ZigBee RF channel
     *
     * @return the current channel or -1 on error
     * @return
     */
    int getZigBeeChannel();

    /**
     * Sets the ZigBee RF channel
     *
     * @param channel {@link int} defining the channel to use
     * @return true if the channel was set
     */
    boolean setZigBeeChannel(int channel);

    /**
     * Gets the ZigBee PAN ID currently in use by the transport
     *
     * @return the PAN ID
     */
    int getZigBeePanId();

    /**
     * Sets the ZigBee PAN ID to the specified value
     *
     * @param panId the new PAN ID
     * @return true if the PAN Id was set correctly
     */
    boolean setZigBeePanId(int panId);

    /**
     * Gets the ZigBee Extended PAN ID currently in use by the transport
     *
     * @return the PAN ID
     */
    long getZigBeeExtendedPanId();

    /**
     * Sets the ZigBee Extended PAN ID to the specified value
     *
     * @param panId the new PAN ID as {@link long}
     * @return true if the PAN Id was set correctly
     */
    boolean setZigBeeExtendedPanId(long panId);

    /**
     * Sets the ZigBee Extended PAN ID to the specified value
     *
     * @param key the new security key as {@link byte}[16]
     * @return true if the PAN Id was set correctly
     */
    boolean setZigBeeSecurityKey(byte[] key);
}
