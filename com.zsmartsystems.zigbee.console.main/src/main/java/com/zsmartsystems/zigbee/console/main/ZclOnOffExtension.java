package com.zsmartsystems.zigbee.console.main;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeNetworkExtension;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;

public class ZclOnOffExtension implements ZigBeeNetworkExtension, ZigBeeNetworkNodeListener {
    private ZigBeeNetworkManager networkManager;

    @Override
    public ZigBeeStatus extensionInitialize(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;

        networkManager.addSupportedServerCluster(ZclOnOffCluster.CLUSTER_ID);
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
            if (endpoint.getApplication(ZclOnOffCluster.CLUSTER_ID) != null) {
                return;
            }
        }

        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            if (endpoint.getOutputCluster(ZclOnOffCluster.CLUSTER_ID) != null) {
                endpoint.addApplication(new ZclOnOffServer());
                break;
            }
        }
    }
}
