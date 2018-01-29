/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dao;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNodeDaoTest {
    @Test
    public void testSerialize() {
        ZigBeeTransportTransmit mockedTransport = Mockito.mock(ZigBeeTransportTransmit.class);
        ZigBeeNetworkManager networkManager = new ZigBeeNetworkManager(mockedTransport);
        ZigBeeNode node = new ZigBeeNode(networkManager, new IeeeAddress("1234567890ABCDEF"));
        node.setNetworkAddress(12345);

        ZigBeeEndpoint endpoint;
        endpoint = new ZigBeeEndpoint(networkManager, node, 1);
        endpoint.setProfileId(123);
        node.addEndpoint(endpoint);
        endpoint = new ZigBeeEndpoint(networkManager, node, 2);
        endpoint.setProfileId(321);
        node.addEndpoint(endpoint);
        /*
         * ZigBeeNodeDao nodeDao = ZigBeeNodeDao.createFromZigBeeNode(node);
         * assertEquals(new IeeeAddress("1234567890ABCDEF").toString(), nodeDao.getIeeeAddress());
         * assertEquals(Integer.valueOf(12345), nodeDao.getNetworkAddress());
         * 
         * node = ZigBeeNodeDao.createFromZigBeeDao(networkManager, nodeDao);
         * assertEquals(new IeeeAddress("1234567890ABCDEF"), node.getIeeeAddress());
         * assertEquals(Integer.valueOf(12345), node.getNetworkAddress());
         * assertEquals(2, node.getEndpoints().size());
         * 
         * endpoint = node.getEndpoint(1);
         * assertEquals(123, endpoint.getProfileId());
         */
    }
}
