/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclHeaderTest extends CommandTest {
    @Test
    public void testDeserialize() {
        int[] packet = getPacketData("11 04 01");

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);
        ZclHeader zclHeader = new ZclHeader(fieldDeserializer);
        System.out.println(zclHeader);

        assertEquals(1, zclHeader.getCommandId());
        assertEquals(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, zclHeader.getFrameType());
        assertEquals(false, zclHeader.isManufacturerSpecific());
        assertEquals(true, zclHeader.isDisableDefaultResponse());
        assertEquals(4, zclHeader.getSequenceNumber());

        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);
        assertTrue(Arrays.equals(packet, zclHeader.serialize(fieldSerializer, new int[] {})));
    }

    @Test
    public void testDeserialize2() {
        int[] packet = getPacketData("08 99 88");

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);
        ZclHeader zclHeader = new ZclHeader(fieldDeserializer);
        System.out.println(zclHeader);

        assertEquals(0x88, zclHeader.getCommandId());
        assertEquals(ZclFrameType.ENTIRE_PROFILE_COMMAND, zclHeader.getFrameType());
        assertEquals(false, zclHeader.isManufacturerSpecific());
        assertEquals(false, zclHeader.isDisableDefaultResponse());
        assertEquals(0x99, zclHeader.getSequenceNumber());

        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);
        assertTrue(Arrays.equals(packet, zclHeader.serialize(fieldSerializer, new int[] {})));
    }
}
