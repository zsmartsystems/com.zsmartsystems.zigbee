/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

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
     * Defines the concentrator type based on {@link ConcentratorType}.
     */
    CONCENTRATOR_TYPE,

    /**
     * Configures the trust centre join mode. Value must be a {@link TrustCentreLinkMode} enumeration.
     */
    TRUST_CENTRE_JOIN_MODE,

    /**
     * Sets the trust centre link key. Value must be a {@link ZigBeeKey}.
     */
    TRUST_CENTRE_LINK_KEY
}
