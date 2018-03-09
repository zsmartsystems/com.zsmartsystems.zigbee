/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.ExtendedPanId;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleTelegesisTest {

    @Test
    public void setZigBeeExtendedPanId() {
        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);

        dongle.setZigBeeExtendedPanId(new ExtendedPanId("123456789abcdef"));
        assertEquals(new ExtendedPanId("123456789abcdef"), dongle.getZigBeeExtendedPanId());
    }

    @Test
    public void setZigBeePanId() {
        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);

        dongle.setZigBeePanId(0x1234);
        assertEquals(0x1234, dongle.getZigBeePanId());
    }

    @Test
    public void getVersionString() {
        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);

        assertEquals("Unknown", dongle.getVersionString());
    }

    @Test
    public void getFirmwareVersion() {
        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);

        assertEquals("", dongle.getFirmwareVersion());
    }
}
