/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.otaserver;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.app.ZigBeeNetworkExtension;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOtaUpgradeCluster;

/**
 * Over The Air Upgrade extension. This provides the top level functionality for the OTA client.
 * <p>
 * It listens for new nodes that are discovered on the network and registers the {@link ZclOtaUpgradeServer} if the
 * device supports OTA.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeOtaUpgradeExtension implements ZigBeeNetworkExtension, ZigBeeNetworkNodeListener {
    private ZigBeeNetworkManager networkManager;

    @Override
    public boolean extensionStartup(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;

        networkManager.addSupportedCluster(ZclOtaUpgradeCluster.CLUSTER_ID);
        networkManager.addNetworkNodeListener(this);
        return false;
    }

    @Override
    public void extensionShutdown() {
        networkManager.removeNetworkNodeListener(this);
    }

    @Override
    public void nodeAdded(ZigBeeNode node) {
        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            if (endpoint.getOutputCluster(ZclOtaUpgradeCluster.CLUSTER_ID) != null) {
                endpoint.addApplication(new ZclOtaUpgradeServer());
                break;
            }
        }
    }

    @Override
    public void nodeUpdated(ZigBeeNode node) {
        // Not used
    }

    @Override
    public void nodeRemoved(ZigBeeNode node) {
        // Not used
    }

}
