/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.ZigBeeNodeStatus;
import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;

/**
 * Defines the interface for data passed from the transport layer (ie dongle) to the ZigBee stack framework.
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
 * The ZDO interface exchanges only command classes. This is different to the ZCL interface since different sticks tend
 * to implement ZDO functionality as individual commands rather than allowing a binary ZDO packet to be sent and
 * received.
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeeTransportReceive {
    /**
     * A callback called by the {@link ZigBeeTransportTransmit} when a ZigBee Cluster Library command is received.
     * <p>
     * The method allows the transport layer to specify the NWK (Network) header, the APS (Application Support Sublayer)
     * and the payload. The headers are provided separately to allow the framework to specify the configuration in some
     * detail, while allowing the transport implementation (eg dongle) to format the data as per its needs. The payload
     * is deserialised by the framework using the {@link ZigBeeDeserializer} interface, thus allowing the format to be
     * set for different hardware implementations.
     *
     * @param apsFrame the {@link ZigBeeApsFrame} for this command
     */
    void receiveCommand(final ZigBeeApsFrame apsFrame);

    /**
     * Set the network state. Note that this will only allow valid state transitions, and an attempt to transition
     * between states that are not allowed will result in the state remaining as it was.
     * <p>
     * This is a callback from the {@link ZigBeeTransportTransmit} when the state of the transport changes
     * </p>
     *
     * @param state the updated {@link ZigBeeTransportState}
     */
    void setNetworkState(final ZigBeeTransportState state);

    /**
     * Announce a node has joined or left the network.
     * <p>
     * It should be assumed that this interface provides a authoritative statement about a devices status.
     * This should come from higher level information provided by the coordinator/transport layer.
     *
     * @param deviceStatus the {@link ZigBeeNodeStatus} of the node
     * @param networkAddress the network address of the new node
     * @param ieeeAddress the {@link IeeeAddress} address of the new node
     */
    void nodeStatusUpdate(final ZigBeeNodeStatus deviceStatus, final Integer networkAddress,
            final IeeeAddress ieeeAddress);

    /**
     * A callback called by the {@link ZigBeeTransportTransmit} when a transaction sent using
     * {@link ZigBeeTransportTransmit#sendCommand(ZigBeeApsFrame)} is received.
     *
     * @param transactionId the transaction ID to which a response has been received
     * @param status the acknowledge status
     */
    void receiveCommandStatus(int transactionId, ZigBeeTransportProgressState status);

}
