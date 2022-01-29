/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

/**
 * Defines the configuration options for {@link TransportConfigOption#DEVICE_TYPE}.
 *
 * @author Chris Jackson
 *
 */
public enum DeviceType {
    /**
     * ZigBee Coordinator (ZC)
     * <p>
     * Will relay messages and can act as a parent to other nodes.
     */
    COORDINATOR,

    /**
     * ZigBee Router (ZR)
     * <p>
     * Will relay messages and can act as a parent to other nodes.
     */
    ROUTER,

    /**
     * ZigBee End Device (ZED)
     * <p>
     * Communicates only with its parent and will not relay messages.
     */
    END_DEVICE,

    /**
     * ZigBee Sleepy End Device (ZSED)
     * <p>
     * An end device whose radio can be turned off to save power. The application must poll to receive messages.
     */
    SLEEPY_END_DEVICE;
}
