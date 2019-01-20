/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.database.ZigBeeEndpointDao;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zcl.clusters.ZclAlarmsCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclColorControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclDoorLockCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclLevelControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclScenesCluster;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeEndpointTest {

    @Test
    public void testOutputClusterIds() {
        ZigBeeEndpoint endpoint = getEndpoint();

        List<Integer> clusterIdList = new ArrayList<Integer>();
        clusterIdList.add(ZclAlarmsCluster.CLUSTER_ID);
        clusterIdList.add(ZclBasicCluster.CLUSTER_ID);
        clusterIdList.add(ZclColorControlCluster.CLUSTER_ID);
        clusterIdList.add(ZclDoorLockCluster.CLUSTER_ID);
        clusterIdList.add(ZclLevelControlCluster.CLUSTER_ID);
        endpoint.setOutputClusterIds(clusterIdList);

        assertEquals(5, endpoint.getOutputClusterIds().size());

        assertNotNull(endpoint.getOutputCluster(ZclAlarmsCluster.CLUSTER_ID));
        assertTrue(endpoint.getOutputCluster(ZclAlarmsCluster.CLUSTER_ID).isClient());
        assertFalse(endpoint.getOutputCluster(ZclAlarmsCluster.CLUSTER_ID).isServer());

        assertNotNull(endpoint.getOutputCluster(ZclLevelControlCluster.CLUSTER_ID));
        assertTrue(endpoint.getOutputCluster(ZclLevelControlCluster.CLUSTER_ID).isClient());
        assertFalse(endpoint.getOutputCluster(ZclLevelControlCluster.CLUSTER_ID).isServer());

        clusterIdList = new ArrayList<Integer>();
        clusterIdList.add(ZclAlarmsCluster.CLUSTER_ID);
        clusterIdList.add(ZclBasicCluster.CLUSTER_ID);
        assertTrue(endpoint.getOutputCluster(ZclAlarmsCluster.CLUSTER_ID).isClient());
        assertFalse(endpoint.getOutputCluster(ZclLevelControlCluster.CLUSTER_ID).isServer());

        assertTrue(endpoint.addOutputCluster(new ZclScenesCluster(endpoint)));
        assertFalse(endpoint.addOutputCluster(new ZclScenesCluster(endpoint)));
        assertTrue(endpoint.getOutputClusterIds().contains(ZclScenesCluster.CLUSTER_ID));

        assertTrue(endpoint.getInputClusterIds().isEmpty());

        System.out.println(endpoint.toString());
    }

    @Test
    public void testInputClusterIds() {
        ZigBeeEndpoint endpoint = getEndpoint();

        List<Integer> clusterIdList = new ArrayList<Integer>();
        clusterIdList.add(ZclAlarmsCluster.CLUSTER_ID);
        clusterIdList.add(ZclBasicCluster.CLUSTER_ID);
        clusterIdList.add(ZclColorControlCluster.CLUSTER_ID);
        clusterIdList.add(ZclDoorLockCluster.CLUSTER_ID);
        clusterIdList.add(ZclLevelControlCluster.CLUSTER_ID);
        endpoint.setInputClusterIds(clusterIdList);

        assertEquals(5, endpoint.getInputClusterIds().size());

        assertNotNull(endpoint.getInputCluster(ZclAlarmsCluster.CLUSTER_ID));
        assertFalse(endpoint.getInputCluster(ZclAlarmsCluster.CLUSTER_ID).isClient());
        assertTrue(endpoint.getInputCluster(ZclAlarmsCluster.CLUSTER_ID).isServer());

        assertNotNull(endpoint.getInputCluster(ZclLevelControlCluster.CLUSTER_ID));
        assertFalse(endpoint.getInputCluster(ZclLevelControlCluster.CLUSTER_ID).isClient());
        assertTrue(endpoint.getInputCluster(ZclLevelControlCluster.CLUSTER_ID).isServer());

        assertTrue(endpoint.addInputCluster(new ZclScenesCluster(endpoint)));
        assertFalse(endpoint.addInputCluster(new ZclScenesCluster(endpoint)));
        assertTrue(endpoint.getInputClusterIds().contains(ZclScenesCluster.CLUSTER_ID));

        assertTrue(endpoint.getOutputClusterIds().isEmpty());
    }

    @Test
    public void testProfileId() {
        ZigBeeEndpoint endpoint = getEndpoint();

        endpoint.setProfileId(0x104);
        assertEquals(0x104, endpoint.getProfileId());
    }

    @Test
    public void testDeviceVersion() {
        ZigBeeEndpoint endpoint = getEndpoint();

        endpoint.setDeviceVersion(123);
        assertEquals(123, endpoint.getDeviceVersion());
    }

    @Test
    public void testGetDeviceId() {
        ZigBeeEndpoint endpoint = getEndpoint();

        endpoint.setDeviceId(9999);
        assertEquals(9999, endpoint.getDeviceId());
    }

    @Test
    public void setDao() {
        ZigBeeEndpoint endpoint = getEndpoint();

        ZigBeeEndpointDao dao = new ZigBeeEndpointDao();
        endpoint.setDao(dao);
    }

    private ZigBeeEndpoint getEndpoint() {
        ZigBeeTransportTransmit mockedTransport = Mockito.mock(ZigBeeTransportTransmit.class);
        ZigBeeNetworkManager networkManager = new ZigBeeNetworkManager(mockedTransport);
        ZigBeeNode node = new ZigBeeNode(networkManager, new IeeeAddress());
        node.setNetworkAddress(1234);
        return new ZigBeeEndpoint(node, 5);
    }
}
