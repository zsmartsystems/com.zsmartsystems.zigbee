/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

import java.util.Collection;

import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 * Enumeration defining all possible configuration options for the {@link ZigBeeTransportTransmit}. Configuration is
 * updated via the {@link ZigBeeTransportTransmit#updateTransportConfig(java.util.Dictionary)} method.
 *
 * @author Chris Jackson
 *
 */
public enum TransportConfigOption {
    /**
     * Defines the concentrator type.
     * <p>
     * Value must be one of {@link ConcentratorType}.
     *
     * @deprecated use CONCENTRATOR_CONFIG
     */
    CONCENTRATOR_TYPE,

    /**
     * Defines the concentrator type.
     * <p>
     * Value must be one of {@link ConcentratorConfigs}.
     */
    CONCENTRATOR_CONFIG,

    /**
     * Configures the trust centre join mode.
     * <p>
     * Value must be one of {@link TrustCentreJoinMode} enumeration.
     */
    TRUST_CENTRE_JOIN_MODE,

    /**
     * Sets the trust centre link key.
     * <p>
     * Value must be a {@link ZigBeeKey}.
     */
    TRUST_CENTRE_LINK_KEY,

    /**
     * Sets a list of supported input clusters. This is primarily intended to allow the application to configure
     * clusters that are matched in the MatchDescriptorRequest.
     * <p>
     * Value must be a {@link Collection} of Integer defining the input clusters
     */
    SUPPORTED_INPUT_CLUSTERS,

    /**
     * Sets a list of supported output clusters. This is primarily intended to allow the application to configure
     * clusters that are matched in the MatchDescriptorRequest.
     * <p>
     * Value must be a {@link Collection} of Integer defining the output clusters
     */
    SUPPORTED_OUTPUT_CLUSTERS,

    /**
     * Sets an installation key for the specified address. Using a blank key (ie all zeros) may be used to remove
     * the install key. This is dongle specific if the key can be removed or if it will time out.
     * <p>
     * Value must be a {@link ZigBeeNodeKey}
     */
    INSTALL_KEY,

    /**
     * Sets the device type used by the dongle. This allows to set the dongle as a coordinator or a router.
     * <p>
     * Value must be {@link DeviceType}
     */
    DEVICE_TYPE,

    /**
     * Sets the device radio power. Power level is defined in dBm.
     * <p>
     * Value must be {@link Integer}
     */
    RADIO_TX_POWER;
}
