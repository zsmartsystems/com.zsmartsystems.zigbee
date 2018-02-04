/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleEzspTest {
    @Test
    public void setZigBeeExtendedPanId() {
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);

        dongle.setZigBeeExtendedPanId(new ExtendedPanId("123456789abcdef"));
        assertEquals(new ExtendedPanId("123456789abcdef"), dongle.getZigBeeExtendedPanId());
    }

    @Test
    public void setZigBeePanId() {
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);

        dongle.setZigBeePanId(0x1234);
        assertEquals(0x1234, dongle.getZigBeePanId());
    }

    @Test
    public void testEzspVersions() {
        EzspFrame.setEzspVersion(4);
        assertEquals(4, EzspFrame.getEzspVersion());
        assertFalse(EzspFrame.setEzspVersion(3));
        assertEquals(4, EzspFrame.getEzspVersion());
        assertTrue(EzspFrame.setEzspVersion(4));
        assertEquals(4, EzspFrame.getEzspVersion());
        assertTrue(EzspFrame.setEzspVersion(5));
        assertEquals(5, EzspFrame.getEzspVersion());
        assertTrue(EzspFrame.setEzspVersion(6));
        assertEquals(6, EzspFrame.getEzspVersion());
        assertFalse(EzspFrame.setEzspVersion(7));
        assertEquals(6, EzspFrame.getEzspVersion());
        EzspFrame.setEzspVersion(4);
    }
}
