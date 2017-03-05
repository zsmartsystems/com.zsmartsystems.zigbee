package com.zsmartsystems.zigbee.zdo.descriptors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class NeighborTableTest {
    @Test
    public void testHashCodeAndEquals() {
        NeighborTable neighbor1 = new NeighborTable();
        neighbor1.setNetworkAddress(12345);
        neighbor1.setExtendedAddress(new IeeeAddress("123456789"));

        NeighborTable neighbor2 = new NeighborTable();
        neighbor2.setNetworkAddress(12345);
        neighbor2.setExtendedAddress(new IeeeAddress("123456789"));

        NeighborTable neighbor3 = new NeighborTable();
        neighbor3.setNetworkAddress(54321);
        neighbor3.setExtendedAddress(new IeeeAddress("987654321"));

        NeighborTable neighbor4 = new NeighborTable();
        neighbor4.setNetworkAddress(54321);
        neighbor4.setLqi(11);

        assertEquals(neighbor1.hashCode(), neighbor2.hashCode());
        assertNotEquals(neighbor1.hashCode(), neighbor3.hashCode());

        assertTrue(neighbor1.equals(neighbor2));
        assertFalse(neighbor1.equals(neighbor3));
        assertFalse(neighbor3.equals(neighbor4));
    }
}
