package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspChannelMaskTest {
    @Test
    public void testMask() {
        assertEquals(EzspChannelMask.EZSP_CHANNEL_MASK_CHAN18, EzspChannelMask.getEzspChannelMask(1 << 18));
    }
}
