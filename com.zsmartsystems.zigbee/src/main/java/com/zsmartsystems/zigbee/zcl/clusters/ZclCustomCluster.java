/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;

/**
 * Custom cluster used as a placeholder for unknown clusters
 *
 * @author Chris Jackson
 *
 */
public class ZclCustomCluster extends ZclCluster {

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        return new ConcurrentHashMap<>(0);
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        return new ConcurrentHashMap<>(0);
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        return new ConcurrentHashMap<>(0);
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        return new ConcurrentHashMap<>(0);
    }

    /**
     * Default constructor to create a custom cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     * @param clusterId the cluster ID
     * @param clusterName the cluster name
     */
    public ZclCustomCluster(final ZigBeeEndpoint zigbeeEndpoint, int clusterId, String clusterName) {
        super(zigbeeEndpoint, clusterId, clusterName);
    }
}
