/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class ActiveEndpointsResponseTest extends CommandTest {

    @Test
    public void testReceive() {
        int[] packet = getPacketData("00 00 00 00 01 01");

        ActiveEndpointsResponse endpointsResponse = new ActiveEndpointsResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        endpointsResponse.deserialize(fieldDeserializer);

        System.out.println(endpointsResponse);

        assertEquals(1, endpointsResponse.getActiveEpList().size());
        assertEquals(0x8005, (int) endpointsResponse.getClusterId());
        assertEquals(ZdoStatus.SUCCESS, endpointsResponse.getStatus());
        assertEquals(0, (int) endpointsResponse.getNwkAddrOfInterest());
        assertEquals(1, (int) endpointsResponse.getActiveEpList().get(0));
    }

    @Test
    public void testReceive2() {
        int[] packet = getPacketData("00 00 BD 97 06 01 02 03 04 C8 E8");

        ActiveEndpointsResponse endpointsResponse = new ActiveEndpointsResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        endpointsResponse.deserialize(fieldDeserializer);

        System.out.println(endpointsResponse);

        assertEquals(6, endpointsResponse.getActiveEpList().size());
        assertEquals(0x8005, (int) endpointsResponse.getClusterId());
        assertEquals(ZdoStatus.SUCCESS, endpointsResponse.getStatus());
        assertEquals(0x97bd, (int) endpointsResponse.getNwkAddrOfInterest());
        assertEquals(1, (int) endpointsResponse.getActiveEpList().get(0));
        assertEquals(2, (int) endpointsResponse.getActiveEpList().get(1));
        assertEquals(3, (int) endpointsResponse.getActiveEpList().get(2));
        assertEquals(4, (int) endpointsResponse.getActiveEpList().get(3));
        assertEquals(0xc8, (int) endpointsResponse.getActiveEpList().get(4));
        assertEquals(0xe8, (int) endpointsResponse.getActiveEpList().get(5));
    }

}
