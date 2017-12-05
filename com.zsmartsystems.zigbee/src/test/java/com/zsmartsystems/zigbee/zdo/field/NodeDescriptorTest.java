/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.FrequencyBandType;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.LogicalType;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.MacCapabilitiesType;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.ServerCapabilitiesType;

/**
 *
 * @author Chris Jackson
 *
 */
public class NodeDescriptorTest extends CommandTest {

    @Test
    public void testNodeDescriptor() {
        NodeDescriptor descriptor = new NodeDescriptor(0, 3333, 74, true, 6666, 0, 6, 4444, true, 8);

        assertTrue(descriptor.isComplexDescriptorAvailable());
        assertTrue(descriptor.isUserDescriptorAvailable());
        assertEquals(4444, descriptor.getIncomingTransferSize());
        assertEquals(6666, descriptor.getManufacturerCode());
        assertEquals(3333, descriptor.getBufferSize());
        assertTrue(descriptor.getFrequencyBands().contains(FrequencyBandType.FREQ_2400_MHZ));
        assertEquals(LogicalType.COORDINATOR, descriptor.getLogicalType());
        assertEquals(0, descriptor.getApsFlags());
        assertTrue(descriptor.getServerCapabilities().contains(ServerCapabilitiesType.PRIMARY_BINDING_TABLE_CACHE));
        assertTrue(descriptor.getServerCapabilities().contains(ServerCapabilitiesType.BACKUP_TRUST_CENTER));
        assertTrue(descriptor.getMacCapabilities().contains(MacCapabilitiesType.SECURITY_CAPABLE));
        assertTrue(descriptor.getMacCapabilities().contains(MacCapabilitiesType.RECEIVER_ON_WHEN_IDLE));
        assertTrue(descriptor.getMacCapabilities().contains(MacCapabilitiesType.FULL_FUNCTION_DEVICE));
    }

    @Test
    public void testNodeDescriptorDeserialize() {
        int[] packet = getPacketData("00 00 00 00 00 40 8F CD AB 52 80 00 41 2A 80 00 00");

        NodeDescriptor descriptor = new NodeDescriptor();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        descriptor.deserialize(deserializer);
        System.out.println(descriptor);

        assertEquals(0, descriptor.getManufacturerCode());
        assertEquals(LogicalType.COORDINATOR, descriptor.getLogicalType());
    }

}
