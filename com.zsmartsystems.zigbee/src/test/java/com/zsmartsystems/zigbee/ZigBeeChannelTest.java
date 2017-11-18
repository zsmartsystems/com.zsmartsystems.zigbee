/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeChannelTest {
    @Test
    public void testChannel() {
        assertEquals(1 << 1, ZigBeeChannel.create(1).getMask());
        assertEquals(ZigBeeChannel.CHANNEL_11, ZigBeeChannel.create(11));
        assertEquals(1 << 11, ZigBeeChannel.create(11).getMask());
    }
}
