/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.ZigBeeException;
import com.zsmartsystems.zigbee.ZigBeeKey;
import com.zsmartsystems.zigbee.ZigBeeNetwork;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager.ZigBeeInitializeResponse;
import com.zsmartsystems.zigbee.ZigBeeTcLinkMode;
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
     * <p>
     * During the initialize() method, the provider must initialize the ports and perform any configuration required to
     * get the stack ready for use. If the dongle has already joined a network, then this method will return true.
     *
     * @return {@link ZigBeeInitializeResponse}
     */
    ZigBeeInitializeResponse initialize();

    /**
     * Starts the transport interface.
     * <p>
     * This call will optionally reconfigure the interface if the reinitialize parameter is true.
     *
     * @param reinitialize true if the provider is to reinitialise the network with the parameters configured since the
     *            {@link #initialize} method was called.
     * @return true if startup was success.
     */
    boolean startup(boolean reinitialize);

    /**
     * Shuts down a transport interface.
     */
    void shutdown();

    /**
     * Get the transport layer version string
     */
    String getVersionString();

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
     * @return the EPAN ID
     */
    ExtendedPanId getZigBeeExtendedPanId();

    /**
     * Sets the ZigBee Extended PAN ID to the specified value
     *
     * @param panId the new {@link ExtendedPanId}
     * @return true if the EPAN Id was set correctly
     */
    boolean setZigBeeExtendedPanId(ExtendedPanId panId);

    /**
     * Sets the ZigBee network security key to the specified value
     *
     * @param key the new network key as {@link ZigBeeKey}
     * @return true if the key was set correctly
     */
    boolean setZigBeeNetworkKey(ZigBeeKey key);

    /**
     * Sets the Trust Center link security key to the specified value
     *
     * @param key the new link key as {@link ZigBeeKey}
     * @return true if the key was set correctly
     */
    boolean setTcLinkKey(ZigBeeKey key);

    /**
     * Sets the Trust Center link mode
     *
     * @param linkMode the {@link ZigBeeTcLinkMode} defining the Trust Center link mode
     * @return true if the trust center link mode was updated
     */
    boolean setTcLinkMode(ZigBeeTcLinkMode linkMode);
}
