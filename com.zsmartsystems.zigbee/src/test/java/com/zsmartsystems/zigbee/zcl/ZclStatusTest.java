package com.zsmartsystems.zigbee.zcl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclStatusTest {
    @Test
    public void getId() {
        assertEquals(0x00, ZclStatus.SUCCESS.getId());
        assertEquals(0x01, ZclStatus.FAILURE.getId());
        assertEquals(0x7E, ZclStatus.NOT_AUTHORIZED.getId());
        assertEquals(0x88, ZclStatus.READ_ONLY.getId());
    }

    @Test
    public void getDescription() {
        assertEquals("Operation was successful.", ZclStatus.SUCCESS.getDescription());
    }

    @Test
    public void getStatus() {
        assertEquals(ZclStatus.SUCCESS, ZclStatus.getStatus(0));
        assertEquals(ZclStatus.RESERVED, ZclStatus.getStatus(999));
    }
}
