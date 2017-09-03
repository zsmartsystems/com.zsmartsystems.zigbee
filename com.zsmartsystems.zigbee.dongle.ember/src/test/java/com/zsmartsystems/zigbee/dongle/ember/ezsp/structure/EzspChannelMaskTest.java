/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
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
