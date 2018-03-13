/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.field.SimpleDescriptor;

/**
 *
 * @author Chris Jackson
 *
 */
public class SimpleDescriptorResponseTest extends CommandTest {

    @Test
    public void testReceiveError() {
        // int[] packet = getPacketData("00 00 00 00 10 C1");
        int[] packet = getPacketData("00 81 00 01");

        SimpleDescriptorResponse descriptorResponse = new SimpleDescriptorResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        descriptorResponse.deserialize(fieldDeserializer);

        System.out.println(descriptorResponse);

        SimpleDescriptor simpleDescriptor = descriptorResponse.getSimpleDescriptor();

        assertNull(simpleDescriptor);
        assertEquals(ZdoStatus.DEVICE_NOT_FOUND, descriptorResponse.getStatus());
    }

    @Test
    public void testReceive() {
        int[] packet = getPacketData("00 00 00 00 14 01 04 01 00 00 00 03 00 00 01 00 06 00 03 00 00 01 00 06 00");

        SimpleDescriptorResponse descriptorResponse = new SimpleDescriptorResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        descriptorResponse.deserialize(fieldDeserializer);

        System.out.println(descriptorResponse);

        SimpleDescriptor simpleDescriptor = descriptorResponse.getSimpleDescriptor();

        assertEquals(ZdoStatus.SUCCESS, descriptorResponse.getStatus());
        assertEquals(0, (int) descriptorResponse.getNwkAddrOfInterest());

        assertEquals(1, simpleDescriptor.getEndpoint());
        assertEquals(0x104, simpleDescriptor.getProfileId());
        assertEquals(3, simpleDescriptor.getInputClusterList().size());
        assertTrue(simpleDescriptor.getInputClusterList().contains(0));
        assertTrue(simpleDescriptor.getInputClusterList().contains(1));
        assertTrue(simpleDescriptor.getInputClusterList().contains(6));
        assertEquals(3, simpleDescriptor.getOutputClusterList().size());
    }
}
