/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeChannelMaskTest {

    @Test
    public void testMask() {
        ZigBeeChannelMask mask;

        mask = new ZigBeeChannelMask();
        assertEquals(0, mask.getChannels().size());
        assertEquals(0, mask.getChannelMask());

        mask.addChannel(ZigBeeChannel.CHANNEL_10);
        assertEquals(1, mask.getChannels().size());
        mask.addChannel(ZigBeeChannel.CHANNEL_10);
        assertEquals(1, mask.getChannels().size());
        mask.addChannel(ZigBeeChannel.CHANNEL_11);
        assertEquals(2, mask.getChannels().size());
        assertEquals(0xc00, mask.getChannelMask());

        mask = new ZigBeeChannelMask(ZigBeeChannelMask.CHANNEL_MASK_2GHZ);
        assertEquals(16, mask.getChannels().size());
        assertFalse(mask.containsChannel(10));
        assertFalse(mask.containsChannel(ZigBeeChannel.CHANNEL_10));
        assertFalse(mask.getChannels().contains(ZigBeeChannel.CHANNEL_10));
        assertTrue(mask.getChannels().contains(ZigBeeChannel.CHANNEL_11));
        assertTrue(mask.getChannels().contains(ZigBeeChannel.CHANNEL_26));
        assertTrue(mask.containsChannel(11));
        assertTrue(mask.containsChannel(ZigBeeChannel.CHANNEL_11));

        assertFalse(mask.containsChannel(8));
        mask.addChannel(8);
        assertTrue(mask.containsChannel(8));
        assertEquals(17, mask.getChannels().size());
        mask.addChannel(-1);
        mask.addChannel(33);
        assertEquals(17, mask.getChannels().size());
    }
}
