package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ExtendedPanIdTest {

    @Test
    public void testConstructorArray() {
        ExtendedPanId address = new ExtendedPanId(new int[] { 0x0b, 0x88, 0xdc, 0x00, 0x01, 0x88, 0x17, 0x00 });
        assertEquals("0017880100DC880B", address.toString());
    }

    @Test
    public void testConstructorString() {
        ExtendedPanId address = new ExtendedPanId("17880100dc880b");
        assertEquals("0017880100DC880B", address.toString());

        address = new ExtendedPanId("8418260000D9959B");
        assertEquals("8418260000D9959B", address.toString());
    }

    @Test
    public void testHash() {
        ExtendedPanId address1 = new ExtendedPanId("17880100dc880b");
        ExtendedPanId address2 = new ExtendedPanId("17880100dc880b");
        assertEquals(address1.hashCode(), address2.hashCode());
    }

    @Test
    public void testEquals() {
        ExtendedPanId address1 = new ExtendedPanId("17880100dc880b");
        ExtendedPanId address2 = new ExtendedPanId("17880100dc880b");
        assertTrue(address1.equals(address2));
    }

    @Test
    public void testToString() {
        ExtendedPanId address = new ExtendedPanId("17880100dc880b");
        assertEquals("0017880100DC880B", address.toString());
    }
}
