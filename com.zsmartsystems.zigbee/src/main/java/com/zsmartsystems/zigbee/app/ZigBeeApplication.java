/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zcl.ZclCluster;

/**
 * Defines the interface for a ZigBee Application
 * <p>
 * Applications provide specific functionality in the framework and can be instantiated and registered with an endpoint.
 * An application is registered with the {@link ZigBeeEndpoint}, and the endpoint will take care of starting and
 * stopping the application, and passing any received commands to the application.
 * <p>
 * Normally, this will be managed through a {@link ZigBeeNetworkExtension} which will manage the addition of the
 * application to the endpoint when the node joins the network, along with responding to the service discovery requests.
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeeApplication {

    /**
     * Starts an application. The application should perform any initialisation. This gets called when
     * the application is registered.
     *
     * @param cluster The {@link ZclCluster} which is the client we are using
     * @return true if the application started successfully
     */
    public boolean appStartup(final ZclCluster cluster);

    /**
     * Shuts down an application. The application should perform any shutdown and cleanup as required.
     */
    public void appShutdown();

    /**
     * Gets the applicable cluster ID for this application
     *
     * @return the cluster ID
     */
    public int getClusterId();

    /**
     * Called when a command has been received. This is called by the {@link ZigBeeNode} when a command for this node is
     * received.
     *
     * @param command the received {@link ZigBeeCommand}
     */
    void commandReceived(final ZigBeeCommand command);
}