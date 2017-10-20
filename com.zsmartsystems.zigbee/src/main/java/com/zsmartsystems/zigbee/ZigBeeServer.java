/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

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
public interface ZigBeeServer {

    /**
     * Starts a server. The server should perform any initialisation. This gets called by the {@link ZigBeeNode} when
     * the server is registered with the node.
     *
     * @param networkManager The {@link ZigBeeNetworkManager} to which this node belongs
     */
    public void serverStartup(final ZigBeeNetworkManager networkManager);

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
     * @param command the received {@link Command}
     */
    void commandReceived(final Command command);
}
