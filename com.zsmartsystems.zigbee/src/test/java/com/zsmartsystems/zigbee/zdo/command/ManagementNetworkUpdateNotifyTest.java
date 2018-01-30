/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
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
public class ManagementNetworkUpdateNotifyTest extends CommandTest {

    @Test
    public void testReceive() {
        int[] packet = getPacketData(
                "00 00 00 F8 FF 07 1F 00 08 00 10 AE AF D6 AC B7 D5 B0 B2 B6 B6 A7 AF A9 A8 AA A5");

        ManagementNetworkUpdateNotify networkUpdate = new ManagementNetworkUpdateNotify();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        networkUpdate.deserialize(fieldDeserializer);

        System.out.println(networkUpdate);

        assertEquals(Integer.valueOf(31), networkUpdate.getTotalTransmissions());
        assertEquals(Integer.valueOf(8), networkUpdate.getTransmissionFailures());
        assertEquals(Integer.valueOf(0x7FFF800), networkUpdate.getScannedChannels());
    }

}
