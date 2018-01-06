/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zdo.field.BindingTable;

/**
 *
 * @author Chris Jackson
 *
 */
public class ManagementBindResponseTest extends CommandTest {

    @Test
    public void testReceive() {
        int[] packet = getPacketData("00 00 01 00 01 43 1D A5 00 AA 3E B0 7C 03 06 00 03 62 39 05 0D 00 6F 0D 00 01");

        ManagementBindResponse response = new ManagementBindResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        response.deserialize(fieldDeserializer);

        System.out.println(response);

        assertEquals(1, (int) response.getBindingTableEntries());
        assertEquals(0, (int) response.getStartIndex());

        List<BindingTable> table = response.getBindingTableList();
        assertEquals(1, table.size());
        BindingTable entry = table.get(0);
        assertEquals(6, entry.getClusterId());
        assertEquals(3, entry.getDstAddrMode());
        assertEquals(new IeeeAddress("7CB03EAA00A51D43"), entry.getSrcAddr());
        assertEquals(3, entry.getSrcEndpoint());
        assertEquals(new IeeeAddress("000D6F000D053962"), entry.getDstNodeAddr());
        assertEquals(1, entry.getDstNodeEndpoint());
    }
}
