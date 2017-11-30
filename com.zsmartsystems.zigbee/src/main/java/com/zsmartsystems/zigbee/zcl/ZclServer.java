/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeNode;

/**
 * Defines the interface for a ZigBee Server
 * <p>
 * Servers provide specific functionality in the framework and can be instantiated and registered with a node.
 * A server is registered with the {@link ZigBeeNode}, and the node will take care of starting and stopping the
 * server, and passing any received commands to the server.
 *
 * @author Chris Jackson
 *
 */
public interface ZclServer {

    /**
     * Starts a server. The server should perform any initialisation. This gets called when
     * the server is registered.
     *
     * @param cluster The {@link ZclCluster} which is the client we are serving
     * @return true if the server started successfully
     */
    public boolean serverStartup(final ZclCluster cluster);

    /**
     * Shuts down a server. The server should perform any shutdown and cleanup as required.
     */
    public void serverShutdown();

    /**
     * Gets the applicable cluster ID for this server
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