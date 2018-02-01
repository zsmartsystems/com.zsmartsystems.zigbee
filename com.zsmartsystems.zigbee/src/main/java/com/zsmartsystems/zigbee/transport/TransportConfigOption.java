/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

import java.util.Collection;

import com.zsmartsystems.zigbee.ZigBeeKey;

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
     * Value must be one of {@link TrustCentreLinkMode} enumeration.
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
    SUPPORTED_OUTPUT_CLUSTERS
}
