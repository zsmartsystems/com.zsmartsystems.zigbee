/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.iasclient;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeAnnounceListener;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeNodeStatus;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeNetworkExtension;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIasZoneCluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IAS extension. This provides the top level functionality for the IAS application.
 * <p>
 * It listens for new nodes that are discovered on the network and registers the {@link ZclIasZoneCluster} if the
 * device supports IAS Zone control.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeIasCieExtension implements ZigBeeNetworkExtension, ZigBeeNetworkNodeListener, ZigBeeAnnounceListener {

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeIasCieExtension.class);

    // private final Map<Integer, IeeeAddress> zoneMap = new ConcurrentHashMap<>();

    private ZigBeeNetworkManager networkManager;

    @Override
    public ZigBeeStatus extensionInitialize(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;

        networkManager.addSupportedClientCluster(ZclIasZoneCluster.CLUSTER_ID);
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
        logger.debug("IAS CIE Extension: {}: node added", node.getIeeeAddress());
        registerApplication(node);
    }

    @Override
    public void nodeUpdated(ZigBeeNode node) {
        logger.debug("IAS CIE Extension: {}: node updated", node.getIeeeAddress());
        registerApplication(node);
    }

    @Override
    public void deviceStatusUpdate(ZigBeeNodeStatus deviceStatus, Integer networkAddress, IeeeAddress ieeeAddress, Integer parentNetworkAddress) {
        switch (deviceStatus) {
            case UNSECURED_JOIN:
                ZigBeeNode node = networkManager.getNode(ieeeAddress);
                if(node != null) {
                    for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
                        if (endpoint.getInputCluster(ZclIasZoneCluster.CLUSTER_ID) != null) {
                            logger.debug("IAS CIE Extension: {}: removing application [endpoint = {}]", node.getIeeeAddress(), endpoint.getEndpointId());
                            endpoint.removeApplication(ZclIasZoneCluster.CLUSTER_ID);
                            break;
                        }
                    }
                }
                break;
        }
    }

    private void registerApplication(ZigBeeNode node) {
        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            if (endpoint.getInputCluster(ZclIasZoneCluster.CLUSTER_ID) != null) {
                logger.debug("IAS CIE Extension: {}: found IAS cluster [endpoint = {}]", node.getIeeeAddress(), endpoint.getEndpointId());
                ZclIasZoneClient client = new ZclIasZoneClient(networkManager, networkManager.getLocalIeeeAddress(), 0);
                client.setAutoEnrollDelay(10000);
                endpoint.addApplication(client);
                break;
            }
        }
    }
}
