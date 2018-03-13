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

/**
 *
 * @author Chris Jackson
 *
 */
public class MatchDescriptorRequestTest extends CommandTest {

    @Test
    public void testReceive() {
        int[] packet = getPacketData("2E FD FF 04 01 00 01 00 05");

        MatchDescriptorRequest request = new MatchDescriptorRequest();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        request.deserialize(fieldDeserializer);

        System.out.println(request);

        assertEquals(Integer.valueOf(65533), request.getNwkAddrOfInterest());
        assertEquals(Integer.valueOf(260), request.getProfileId());
        assertEquals(0, request.getInClusterList().size());
        assertEquals(1, request.getOutClusterList().size());
        assertEquals(Integer.valueOf(1280), request.getOutClusterList().get(0));
    }
}
