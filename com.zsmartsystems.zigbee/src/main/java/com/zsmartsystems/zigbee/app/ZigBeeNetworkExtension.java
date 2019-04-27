/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app;

import com.zsmartsystems.zigbee.ZigBeeAnnounceListener;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateListener;
import com.zsmartsystems.zigbee.ZigBeeStatus;

/**
 * Defines the interface for a ZigBee Extension.
 * <p>
 * Extensions provide specific functionality in the framework and can be instantiated and registered with the network
 * manager. An extension is registered with the {@link ZigBeeNetworkManager}, and the manager will take care of
 * starting and stopping the extension.
 * <p>
 * The extension lifecycle is as follows -:
 * <ul>
 * <li>{@link #extensionInitialize} is called when the extension is first registered. This can be used to initialise any
 * internal variables, or register clusters etc with the system. The extension may not communicate on the network until
 * after the {@link #extensionStartup} method is called.
 * <li>{@link #extensionStartup} is called when the network is online and the extension may run operationally.
 * <li>{@link #extensionShutdown} is called when the extension is closed. The framework will do this when it is shutting
 * down.
 * </ul>
 * Extensions should not attempt to communicate on the network until after their {@link #extensionStartup} method has
 * been called, and must not communicate again after their {@link #extensionShutdown} method is called.
 * <p>
 * Extensions should register with the standard {@link ZigBeeNetworkManager} listeners to receive network notifications
 * -:
 * <ul>
 * <li>{@link ZigBeeNetworkStateListener} for network state changes
 * <li>{@link ZigBeeNetworkNodeListener} for updates to nodes
 * <li>{@link ZigBeeCommandListener} to receive incoming commands
 * <li>{@link ZigBeeAnnounceListener} to receive announcement messages
 * </ul>
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeeNetworkExtension {

    /**
     * Initializes an extension. The extension should perform any initialisation. This gets called when
     * the extension is registered. The extension should not assume that the network is online, and should
     * not attempt to communicate on the network until after {@link #extensionStartup()} is called.
     *
     * @param networkManager The {@link ZigBeeNetworkManager} of the network
     * @return {@link ZigBeeStatus#SUCCESS} if the extension initialized successfully,
     *         {@link ZigBeeStatus#INVALID_STATE} if the extension was already started.
     */
    public ZigBeeStatus extensionInitialize(final ZigBeeNetworkManager networkManager);

    /**
     * Starts an extension. This gets called when the network is online.
     *
     * @return {@link ZigBeeStatus#SUCCESS} if the extension started successfully
     */
    public ZigBeeStatus extensionStartup();

    /**
     * Shuts down an extension. The extension should perform any shutdown and cleanup as required.
     */
    public void extensionShutdown();
}