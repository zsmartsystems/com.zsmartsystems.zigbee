package com.zsmartsystems.zigbee;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zdo.ZdoCommand;

/**
 * <p>
 * Defines the interface for data passed to the transport layer (ie dongle) from the ZigBee stack framework.
 * </p>
 * <p>
 * ZigBee transport interface implemented by different hardware drivers. This could support for example serial
 * interfaces for dongles, or IP connections to remote interfaces.
 * </p>
 * <p>
 * The ZCL interface allows the stack to specify the NWK (Network) header, the APS (Application Support Sublayer) and
 * the payload. The headers are provided separately to allow the framework to specify the configuration in some detail,
 * while allowing the transport implementation (eg dongle) to format the data as per its needs. The payload is
 * serialised by the framework using the {@link ZigBeeSerializer} and {@link ZigBeeDeserializer} interfaces, thus
 * allowing the format to be set for different hardware implementations.
 * </p>
 * <p>
 * The ZDO interface exchanges only command classes. This is different to the ZCL interface since different sticks
 * tend to implement ZDO functionality as individual commands rather than allowing a binary ZDO packet to be sent and
 * received.
 * </p>
 *
 * @author Chris Jackson
 */
public interface ZigBeeTransportTransmit {
    /**
     * Starts up a transport interface.
     *
     * @return true if startup was success.
     */
    boolean startup();

    /**
     * Shuts down a transport interface.
     */
    void shutdown();

    /**
     * <p>
     * Sends ZigBee Cluster Library command without waiting for response. Responses are provided to the framework
     * through the {@link ZigBeeNetwork#receiveZclCommand} callback.
     * </p>
     * <p>
     * The ZCL method allows the stack to specify the NWK (Network) header, the APS (Application Support Sublayer) and
     * the payload. The headers are provided separately to allow the framework to specify the configuration in some
     * detail, while allowing the transport implementation (eg dongle) to format the data as per its needs. The payload
     * is serialised by the framework using the {@link ZigBeeSerializer} interface, thus allowing the format to be set
     * for different hardware implementations.
     * </p>
     *
     * @param nwkHeader the {@link ZigBeeNwkHeader} for this command
     * @param apsHeader the {@link ZigBeeApsHeader} for this command
     * @param payload the serialised payload to be sent as {@link int[]}
     * @return transaction ID
     * @throws {@link ZigBeeException} if exception occurs in sending
     */
    void sendZclCommand(final ZigBeeNwkHeader nwkHeader, final ZigBeeApsHeader apsHeader, final int[] payload)
            throws ZigBeeException;

    /**
     * <p>
     * The ZDO interface exchanges only command classes. This is different to the ZCL interface since different sticks
     * tend to implement ZDO functionality as individual commands rather than allowing a binary ZDO packet to be sent
     * and received.
     * </p>
     *
     * @param command the {@link ZdoCommand} to send
     * @throws {@link ZigBeeException} if exception occurs in sending
     */
    void sendZdoCommand(final ZdoCommand command) throws ZigBeeException;

    /**
     * Sets the {@link ZigBeeTransportReceive}. Set by the network so that the {@link ZigBeeTransportTransmit} can send
     * callback messages to the network when a command is received or network state changes.
     *
     * @param zigbeeTransportReceive the {@link ZigBeeTransportReceive}
     */
    void setZigBeeTransportReceive(ZigBeeTransportReceive zigbeeTransportReceive);
}
