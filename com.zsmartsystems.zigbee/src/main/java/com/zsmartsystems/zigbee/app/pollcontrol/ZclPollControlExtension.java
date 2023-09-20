package com.zsmartsystems.zigbee.app.pollcontrol;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeNetworkExtension;
import com.zsmartsystems.zigbee.zcl.clusters.ZclPollControlCluster;

public class ZclPollControlExtension implements ZigBeeNetworkExtension, ZigBeeNetworkNodeListener {
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
        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            if (endpoint.getApplication(ZclPollControlCluster.CLUSTER_ID) != null) {
                return;
            }
        }

        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            if (endpoint.getInputCluster(ZclPollControlCluster.CLUSTER_ID) != null) {
                endpoint.addApplication(new ZclPollControlClient(this));
                break;
            }
        }
    }
}
