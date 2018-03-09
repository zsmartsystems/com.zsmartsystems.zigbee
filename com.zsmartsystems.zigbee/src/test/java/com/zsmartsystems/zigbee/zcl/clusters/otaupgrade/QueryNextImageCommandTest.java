/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.otaupgrade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;

/**
 *
 * @author Chris Jackson
 *
 */
public class QueryNextImageCommandTest extends CommandTest {

    @Test
    public void testReceive() {
        int[] packet = getPacketData("00 0C 11 62 00 03 05 02 01");

        QueryNextImageCommand command = new QueryNextImageCommand();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);
        command.deserialize(fieldDeserializer);
        System.out.println(command);

        assertEquals(Integer.valueOf(4364), command.getManufacturerCode());
        assertEquals(Integer.valueOf(98), command.getImageType());
        assertEquals(Integer.valueOf(0x1020503), command.getFileVersion());
        assertNull(command.getHardwareVersion());
    }

    @Test
    public void testReceiveHwVersion() {
        int[] packet = getPacketData("01 0C 11 62 00 03 05 02 01 33 44");

        QueryNextImageCommand command = new QueryNextImageCommand();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);
        command.deserialize(fieldDeserializer);
        System.out.println(command);

        assertEquals(Integer.valueOf(4364), command.getManufacturerCode());
        assertEquals(Integer.valueOf(98), command.getImageType());
        assertEquals(Integer.valueOf(0x1020503), command.getFileVersion());
        assertEquals(Integer.valueOf(0x4433), command.getHardwareVersion());
    }

}
