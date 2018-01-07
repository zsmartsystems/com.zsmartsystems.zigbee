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
import com.zsmartsystems.zigbee.zdo.field.NeighborTable;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable.NeighborTableJoining;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable.NeighborTableRelationship;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable.NeighborTableRxState;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.LogicalType;

/**
 *
 * @author Chris Jackson
 *
 */
public class ManagementLqiResponseTest extends CommandTest {

    @Test
    public void testReceive() {
        // Short response - ie not extended
        int[] packet = getPacketData(
                "00 00 02 00 02 14 D4 F1 02 00 4B 12 00 0B 88 DC 00 01 88 17 00 8F 22 15 02 01 3B 14 D4 F1 02 00 4B 12 00 EC A1 A5 01 00 8D 15 00 35 38 15 02 01 58");

        ManagementLqiResponse lqiResponse = new ManagementLqiResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        lqiResponse.deserialize(fieldDeserializer);

        System.out.println(lqiResponse);

        assertEquals(2, (int) lqiResponse.getNeighborTableEntries());
        assertEquals(0, (int) lqiResponse.getStartIndex());

        List<NeighborTable> neighbors = lqiResponse.getNeighborTableList();

        assertEquals(2, neighbors.size());
        assertEquals(59, (int) neighbors.get(0).getLqi());
        assertEquals(NeighborTableJoining.UNKNOWN, neighbors.get(0).getPermitJoining());
        assertEquals(1, (int) neighbors.get(0).getDepth());
        assertEquals(NeighborTableRelationship.CHILD, neighbors.get(0).getRelationship());
        assertEquals(LogicalType.ROUTER, neighbors.get(0).getDeviceType());
        assertEquals(NeighborTableRxState.RX_ON, neighbors.get(0).getRxOnWhenIdle());
        assertEquals(new IeeeAddress("0017880100DC880B"), neighbors.get(0).getExtendedAddress());
    }
}
