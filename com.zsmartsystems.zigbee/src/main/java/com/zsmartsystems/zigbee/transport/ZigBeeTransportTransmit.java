/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

import java.util.Map;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.aps.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
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
 * while allowing the transport implementation (e.g. dongle) to format the data as per its needs. The payload is
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
     * get the stack ready for use. If the dongle has already joined a network, then this method will return
     * {@link ZigBeeStatus#SUCCESS}.
     * <p>
     * At the completion of the initialize method, the {@link #getIeeeAddress()} method must return the valid address
     * for the coordinator.
     *
     * @return {@link ZigBeeStatus} - {@link ZigBeeStatus#SUCCESS} if no error
     */
    ZigBeeStatus initialize();

    /**
     * Starts the transport interface and brings the transport state {@link ZigBeeTransportState#ONLINE}.
     * <p>
     * This call will optionally reconfigure the interface if the reinitialize parameter is true.
     * Startup would normally be called once after {@link #initialize()} on system start, and may be called again
     * subsequently if the dongle is taken offline.
     *
     * @param reinitialize true if the provider is to reinitialise the network with the parameters configured since the
     *            {@link #initialize} method was called.
     * @return {@link ZigBeeStatus} with the return status. If the dongle is online, {@link ZigBeeStatus#SUCCESS} will
     *         be returned and the network state will be set to {@link ZigBeeTransportState#ONLINE}.
     */
    ZigBeeStatus startup(boolean reinitialize);

    /**
     * Shuts down a transport interface. This method shall free all resources, and the interface may not be used again
     * until {@link #initialize()} is called.
     */
    void shutdown();

    /**
     * Get the transport layer version string
     */
    String getVersionString();

    /**
     * Returns the {@link IeeeAddress} of the local device. Implementations must ensure that this is known after
     * {@link #initialize()} is called.
     *
     * @return the {@link IeeeAddress} of the local device. May return null if the address is not known.
     */
    IeeeAddress getIeeeAddress();

    /**
     * Returns the network address of the local device
     *
     * @return the network address of the local device. May return null if the address is not known.
     */
    Integer getNwkAddress();

    /**
     * Sends ZigBee Cluster Library command without waiting for response. Responses are provided to the framework
     * through the {@link ZigBeeTransportReceive#receiveCommand(ZigBeeApsFrame)} callback.
     * <p>
     * This method must return instantly - it should NOT wait for a response. State updates are provided with the
     * {@link ZigBeeTransportReceive#receiveCommandState(int, ZigBeeTransportProgressState)} method.
     *
     * @param msgTag the message tag for this command
     * @param apsFrame the {@link ZigBeeApsFrame} to be sent
     */
    void sendCommand(final int msgTag, final ZigBeeApsFrame apsFrame);

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
     * @return the current {@link ZigBeeChannel} or {@link ZigBeeChannel.UNKNOWN} on error
     */
    ZigBeeChannel getZigBeeChannel();

    /**
     * Sets the ZigBee RF channel
     *
     * @param channel the {@link ZigBeeChannel} defining the channel to use
     * @return {@link ZigBeeStatus} with the status of function
     */
    ZigBeeStatus setZigBeeChannel(ZigBeeChannel channel);

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
     * @return {@link ZigBeeStatus} with the status of function
     */
    ZigBeeStatus setZigBeePanId(int panId);

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
     * @return {@link ZigBeeStatus} with the status of function
     */
    ZigBeeStatus setZigBeeExtendedPanId(ExtendedPanId panId);

    /**
     * Sets the ZigBee network security key to the specified value
     *
     * @param key the new network key as {@link ZigBeeKey}
     * @return {@link ZigBeeStatus} with the status of function
     */
    ZigBeeStatus setZigBeeNetworkKey(ZigBeeKey key);

    /**
     * Gets the current network key.
     *
     * @return the current network key as {@link ZigBeeKey} or null if not known.
     */
    ZigBeeKey getZigBeeNetworkKey();

    /**
     * Sets the Trust Center link security key to the specified value
     *
     * @param key the new link key as {@link ZigBeeKey}
     * @return {@link ZigBeeStatus} with the status of function
     */
    ZigBeeStatus setTcLinkKey(ZigBeeKey key);

    /**
     * Gets the current Trust Center link security key
     *
     * @return the link key as {@link ZigBeeKey} or null if unknown
     */
    ZigBeeKey getTcLinkKey();

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

    /**
     * Sets the default profile ID to use.
     * <p>
     * This should be set before calling the {@link #initialize} method.
     *
     * @param defaultProfileId the profile ID
     */
    default void setDefaultProfileId(int defaultProfileId) {
    }

    /**
     * Sets the default device ID.
     * <p>
     * This should be set before calling the {@link #initialize} method.
     *
     * @param defaultDeviceId the device ID.
     */
    default void setDefaultDeviceId(int defaultDeviceId) {
    }

    /**
     * Provides the transport with a {@link ZigBeeNetworkManager} instance.
     */
    default void setZigBeeNetworkManager(ZigBeeNetworkManager manager) {
    }
}
