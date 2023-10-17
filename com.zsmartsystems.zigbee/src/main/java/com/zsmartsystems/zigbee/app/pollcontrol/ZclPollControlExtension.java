package com.zsmartsystems.zigbee.app.pollcontrol;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeAnnounceListener;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeNodeStatus;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeNetworkExtension;
import com.zsmartsystems.zigbee.zcl.clusters.ZclPollControlCluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZclPollControlExtension implements ZigBeeNetworkExtension, ZigBeeNetworkNodeListener, ZigBeeAnnounceListener {
    private final Logger logger = LoggerFactory.getLogger(ZclPollControlExtension.class);

    private ZigBeeNetworkManager networkManager;

    private int timeout = 0;

    public int getTimeout() {
        return timeout;
    }

    public ZclPollControlExtension setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    @Override
    public ZigBeeStatus extensionInitialize(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;

        networkManager.addSupportedClientCluster(ZclPollControlCluster.CLUSTER_ID);
        networkManager.addNetworkNodeListener(this);
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
        logger.debug("ZclPollControlExtension: {}: node added", node.getIeeeAddress());
        registerApplication(node);
    }

    @Override
    public void nodeUpdated(ZigBeeNode node) {
        logger.debug("ZclPollControlExtension: {}: node updated", node.getIeeeAddress());
        registerApplication(node);
    }

    @Override
    public void deviceStatusUpdate(ZigBeeNodeStatus deviceStatus, Integer networkAddress, IeeeAddress ieeeAddress) {
        switch (deviceStatus) {
            case UNSECURED_JOIN:
                ZigBeeNode node = networkManager.getNode(ieeeAddress);
                if(node != null) {
                    for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
                        if (endpoint.getOutputCluster(ZclPollControlCluster.CLUSTER_ID) != null) {
                            logger.debug("ZclPollControlExtension: {}: found Poll Control cluster [endpoint = {}]", node.getIeeeAddress(), endpoint.getEndpointId());
                            endpoint.removeApplication(ZclPollControlCluster.CLUSTER_ID);
                            break;
                        }
                    }
                }
                break;
        }
    }

    private void registerApplication(ZigBeeNode node) {
        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            if (endpoint.getApplication(ZclPollControlCluster.CLUSTER_ID) != null) {
                return;
            }
        }

        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            if (endpoint.getInputCluster(ZclPollControlCluster.CLUSTER_ID) != null) {
                logger.debug("ZclPollControlExtension: {}: found Poll Control cluster [endpoint = {}]", node.getIeeeAddress(), endpoint.getEndpointId());
                endpoint.addApplication(new ZclPollControlClient(this));
                break;
            }
        }
    }
}
