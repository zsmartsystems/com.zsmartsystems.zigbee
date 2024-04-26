/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.otaserver;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeAnnounceListener;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeNodeStatus;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeNetworkExtension;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOtaUpgradeCluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Over The Air Upgrade extension. This provides the top level functionality for the OTA client.
 * <p>
 * It listens for new nodes that are discovered on the network and registers the {@link ZclOtaUpgradeServer} if the
 * device supports OTA.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeOtaUpgradeExtension implements ZigBeeNetworkExtension, ZigBeeNetworkNodeListener, ZigBeeAnnounceListener {

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeOtaUpgradeExtension.class);

    private ZigBeeNetworkManager networkManager;

    @Override
    public ZigBeeStatus extensionInitialize(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;

        networkManager.addSupportedServerCluster(ZclOtaUpgradeCluster.CLUSTER_ID);
        networkManager.addNetworkNodeListener(this);
        networkManager.addAnnounceListener(this);
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeStatus extensionStartup() {
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public void extensionShutdown() {
        networkManager.removeNetworkNodeListener(this);
    }

    @Override
    public void nodeAdded(ZigBeeNode node) {
        logger.debug("OTA Upgrade Extension: {}: node added", node.getIeeeAddress());
        registerApplication(node);
    }

    @Override
    public void nodeUpdated(ZigBeeNode node) {
        logger.debug("OTA Upgrade Extension: {}: node updated", node.getIeeeAddress());
        registerApplication(node);
    }

    @Override
    public void deviceStatusUpdate(ZigBeeNodeStatus deviceStatus, Integer networkAddress, IeeeAddress ieeeAddress, Integer parentNetworkAddress) {
        switch (deviceStatus) {
            case UNSECURED_JOIN:
                ZigBeeNode node = networkManager.getNode(ieeeAddress);
                if(node != null) {
                    for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
                        if (endpoint.getOutputCluster(ZclOtaUpgradeCluster.CLUSTER_ID) != null) {
                            logger.debug("OTA Upgrade Extension: {}: found OTA upgrade cluster [endpoint = {}]", node.getIeeeAddress(), endpoint.getEndpointId());
                            endpoint.removeApplication(ZclOtaUpgradeCluster.CLUSTER_ID);
                            break;
                        }
                    }
                }
                break;
        }
    }

    private void registerApplication(ZigBeeNode node) {
        /*
         * This method needs to register the ZclOtaUpgradeServer to an endpoint on the node. Only one endpoint should be
         * registered with the ZclOtaUpgradeServer.
         * It should be noted that the nodeAdded method can be called more than once if the endpoint or cluster
         * information is updated. As such we need to take
         * care not to register multiple servers to the same or other endpoints.
         */
        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            if (endpoint.getApplication(ZclOtaUpgradeCluster.CLUSTER_ID) != null) {
                return;
            }
        }

        /*
         * We've confirmed there is no current application registered, so register the ZclOtaUpgradeServer to the first
         * endpoint supporting OTA upgrades
         */
        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            if (endpoint.getOutputCluster(ZclOtaUpgradeCluster.CLUSTER_ID) != null) {
                logger.debug("OTA Upgrade Extension: {}: found OTA upgrade cluster [endpoint = {}]", node.getIeeeAddress(), endpoint.getEndpointId());
                endpoint.addApplication(new ZclOtaUpgradeServer());
                break;
            }
        }
    }
}
