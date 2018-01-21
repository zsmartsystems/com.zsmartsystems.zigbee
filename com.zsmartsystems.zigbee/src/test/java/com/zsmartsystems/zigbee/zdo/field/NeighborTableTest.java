/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable.NeighborTableJoining;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable.NeighborTableRelationship;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable.NeighborTableRxState;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.LogicalType;

/**
 *
 * @author Chris Jackson
 *
 */
public class NeighborTableTest extends CommandTest {
    @Test
    public void testDeserialize() {
        int[] packet = getPacketData("B1 68 DE 3A 00 00 00 00 86 06 00 00 00 EE 1F 00 A9 44 25 02 0F E2");

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);

        NeighborTable neighbor = new NeighborTable();
        neighbor.deserialize(deserializer);

        assertEquals(LogicalType.ROUTER, neighbor.getDeviceType());
        assertEquals(new IeeeAddress("001FEE0000000686"), neighbor.getExtendedAddress());
        assertEquals(new ExtendedPanId("3ADE68B1"), neighbor.getExtendedPanId());
        assertEquals(NeighborTableRelationship.SIBLING, neighbor.getRelationship());
        assertEquals(Integer.valueOf(17577), neighbor.getNetworkAddress());
        assertEquals(NeighborTableJoining.UNKNOWN, neighbor.getPermitJoining());
        assertEquals(NeighborTableRxState.RX_ON, neighbor.getRxOnWhenIdle());
        assertEquals(Integer.valueOf(15), neighbor.getDepth());
        assertEquals(Integer.valueOf(226), neighbor.getLqi());
    }

    private NeighborTable getNeighborTable(Integer networkAddress, String ieeeAddressString, Integer lqi) {
        NeighborTable neighbor = new NeighborTable();
        try {
            IeeeAddress ieeeAddress = new IeeeAddress(ieeeAddressString);

            Field fieldNetworkAddress = NeighborTable.class.getDeclaredField("networkAddress");
            fieldNetworkAddress.setAccessible(true);
            fieldNetworkAddress.set(neighbor, networkAddress);

            Field fieldExtendedAddress = NeighborTable.class.getDeclaredField("extendedAddress");
            fieldExtendedAddress.setAccessible(true);
            fieldExtendedAddress.set(neighbor, ieeeAddress);

            Field fieldLqi = NeighborTable.class.getDeclaredField("lqi");
            fieldLqi.setAccessible(true);
            fieldLqi.set(neighbor, lqi);
        } catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return neighbor;
    }

    @Test
    public void testHashCodeAndEquals() {
        NeighborTable neighbor1 = getNeighborTable(12345, "123456789", 0);
        NeighborTable neighbor2 = getNeighborTable(12345, "123456789", 0);
        NeighborTable neighbor3 = getNeighborTable(54321, "987654321", 0);
        NeighborTable neighbor4 = getNeighborTable(54321, "987654321", 11);

        assertEquals(neighbor1.hashCode(), neighbor2.hashCode());
        assertNotEquals(neighbor1.hashCode(), neighbor3.hashCode());

        assertTrue(neighbor1.equals(neighbor2));
        assertFalse(neighbor1.equals(neighbor3));
        assertFalse(neighbor3.equals(neighbor4));
    }
}
