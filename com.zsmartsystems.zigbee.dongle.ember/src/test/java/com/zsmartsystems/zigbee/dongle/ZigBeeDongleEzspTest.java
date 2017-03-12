package com.zsmartsystems.zigbee.dongle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ZigBeeDongleEzsp;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleEzspTest {
    @Test
    public void setZigBeeExtendedPanId() {
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);

        dongle.setZigBeeExtendedPanId(0x123456789abcdefL);
        assertEquals(0x123456789abcdefL, dongle.getZigBeeExtendedPanId());
    }

    @Test
    public void setZigBeePanId() {
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);

        dongle.setZigBeePanId(0x1234);
        assertEquals(0x1234, dongle.getZigBeePanId());
    }
}
