package com.zsmartsystems.zigbee;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zdo.ZdoCommand;

/**
 * <p>
 * Defines the interface for data passed from the transport layer (ie dongle) to the ZigBee stack framework.
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
 * The ZDO interface exchanges only command classes. This is different to the ZCL interface since different sticks tend
 * to implement ZDO functionality as individual commands rather than allowing a binary ZDO packet to be sent and
 * received.
 * </p>
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeeTransportReceive {
    /**
     * <p>
     * A callback called by the {@link ZigBeeTransportTransmit} when a ZigBee Cluster Library command is received.
     * </p>
     * <p>
     * The method allows the transport layer to specify the NWK (Network) header, the APS (Application Support Sublayer)
     * and the payload. The headers are provided separately to allow the framework to specify the configuration in some
     * detail, while allowing the transport implementation (eg dongle) to format the data as per its needs. The payload
     * is deserialised by the framework using the {@link ZigBeeDeserializer} interface, thus allowing the format to be
     * set for different hardware implementations.
     * </p>
     *
     * @param nwkHeader the {@link ZigBeeNwkHeader} for this command
     * @param apsHeader the {@link ZigBeeApsHeader} for this command
     * @param payload the serialised payload to be sent as {@link int[]}
     */
    void receiveZclCommand(final ZigBeeNwkHeader nwkHeader, final ZigBeeApsHeader apsHeader, final int[] payload);

    /**
     * <p>
     * The ZDO interface exchanges only command classes. This is different to the ZCL interface since different sticks
     * tend to implement ZDO functionality as individual commands rather than allowing a binary ZDO packet to be sent
     * and received.
     * </p>
     *
     * @param command the received {@link ZdoCommand}
     */
    void receiveZdoCommand(final ZdoCommand command);

    /**
     * <p>
     * Set the network state.
     * </p>
     * <p>
     * This is a callback from the {@link ZigBeeTransportTransmit} when the state of the transport changes
     * </p>
     *
     * @param state
     */
    void setNetworkState(ZigBeeTransportState state);

    /**
     * Adds a ZigBee network state listener.
     * This will receive network state updates (e.g. when the network is initialised) from the
     * {@link ZigBeeTransportTransmit}.
     *
     * @param stateListener the {@link ZigBeeNetworkStateListener} state listener
     */
    void addNetworkStateListener(ZigBeeNetworkStateListener stateListener);
}
