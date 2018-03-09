/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

import java.util.Map;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.ZigBeeKey;
import com.zsmartsystems.zigbee.ZigBeeNetwork;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager.ZigBeeInitializeResponse;
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
     * <p>
     * At the completion of the initialize method, the {@link #getIeeeAddress()} method must return the valid address
     * for the coordinator.
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
     * Returns the {@link IeeeAddress} of the local device
     *
     * @return the {@link IeeeAddress} of the local device. May return null if the address is not known.
     */
    IeeeAddress getIeeeAddress();

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
     */
    void sendCommand(final ZigBeeApsFrame apsFrame);

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
     * @deprecated use {@link updateTransportConfiguration}
     * @param key the new link key as {@link ZigBeeKey}
     * @return true if the key was set correctly
     */
    @Deprecated
    boolean setTcLinkKey(ZigBeeKey key);

    /**
     * Sets the transport configuration.
     * <p>
     * This method passes a {@link Map} of {@link TransportConfigOption}s to the transport layer. Each option
     * must be defined as a {link Object} as defined by the option (see the documentation for
     * {@link TransportConfigOption}. The transport layer should update its configuration as appropriate - if this will
     * take any appreciable time to complete, the implementation should perform error checking and then return
     * {@link TransportConfigResult#SUCCESS}.
     * <p>
     * This method returns the result of each configuration in the calling {@link TransportConfig}.
     * If configuration options are invalid, {@link TransportConfigResult#ERROR_INVALID_VALUE} is returned.
     * If the transport is not in a mode where it can accept a specific configuration change
     * {@link TransportConfigResult#ERROR_INVALID_VALUE} is returned in the value status
     *
     * @param configuration {@link TransportConfig} containing the configuration items
     * @return {@link Map} of {@link TransportConfigOption} and {@link TransportConfigResult} values with the result
     */
    void updateTransportConfig(TransportConfig configuration);
}
