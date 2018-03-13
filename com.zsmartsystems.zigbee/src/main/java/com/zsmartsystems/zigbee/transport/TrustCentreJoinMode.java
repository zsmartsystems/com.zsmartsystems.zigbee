/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

/**
 * An enumeration with the Trust Center join mode.
 *
 * @author Chris Jackson
 *
 */
public enum TrustCentreJoinMode {
    /**
     * The TC should deny joins. Even if a router allows a device to join the network, the trust center will not support
     * the request, and will not deliver the network key to the device.
     */
    TC_JOIN_DENY,
    /**
     * The TC should allow joins using the link key, or a preconfigured link key / device specific install code.
     */
    TC_JOIN_INSECURE,
    /**
     * The TC should allow joins only with a preconfigured link key / device specific install code.
     */
    TC_JOIN_SECURE
}
