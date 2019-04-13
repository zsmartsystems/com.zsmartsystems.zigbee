/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.basic;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeNetworkExtension;

/**
 * Extension to provide responses to basic client requests from remote devices. This provides a basic wrapper around the
 * {@link ZclBasicServer} class by implementing the standard {@link ZigBeeNetworkExtension} interface.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeBasicServerExtension implements ZigBeeNetworkExtension {
    private ZclBasicServer basicServer;

    @Override
    public ZigBeeStatus extensionInitialize(ZigBeeNetworkManager networkManager) {
        basicServer = new ZclBasicServer(networkManager);
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeStatus extensionStartup() {
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public void extensionShutdown() {
        basicServer.shutdown();
    }

    /**
     * Sets an attribute value in the basic server.
     *
     * @param attributeId the attribute identifier to set
     * @param attributeValue the value related to the attribute ID
     * @return true if the attribute was set
     */
    public boolean setAttribute(Integer attributeId, Object attributeValue) {
        return basicServer.setAttribute(attributeId, attributeValue);
    }
}
