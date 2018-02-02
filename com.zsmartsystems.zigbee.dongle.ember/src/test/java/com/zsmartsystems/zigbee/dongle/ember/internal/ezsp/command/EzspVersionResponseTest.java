/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameTest;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspVersionResponseTest extends EzspFrameTest {
    @Test
    public void testVersion58() {
        EzspFrame.setEzspVersion(4);
        EzspVersionResponse version = new EzspVersionResponse(getPacketData("03 80 00 04 02 00 58"));

        // Version 5.8.0 - EZSP 4
        assertEquals(3, version.getSequenceNumber());
        assertEquals(true, version.isResponse());
        assertEquals(0, version.getFrameId());
        assertEquals(4, version.getProtocolVersion());
        assertEquals(2, version.getStackType());
        assertEquals(0x5800, version.getStackVersion());
    }

    @Test
    public void testVersion581() {
        EzspFrame.setEzspVersion(4);
        EzspVersionResponse version = new EzspVersionResponse(getPacketData("01 80 00 04 02 10 58"));

        // Version 5.8.1 - EZSP 4
        assertEquals(1, version.getSequenceNumber());
        assertEquals(true, version.isResponse());
        assertEquals(0, version.getFrameId());
        assertEquals(4, version.getProtocolVersion());
        assertEquals(2, version.getStackType());
        assertEquals(0x5810, version.getStackVersion());
    }

    @Test
    public void testVersion5A1() {
        EzspFrame.setEzspVersion(4);
        EzspVersionResponse version = new EzspVersionResponse(getPacketData("01 80 FF 00 00 05 02 10 5A"));

        // Version 5.10.1 - EZSP 5
        assertEquals(1, version.getSequenceNumber());
        assertEquals(true, version.isResponse());
        assertEquals(0, version.getFrameId());
        assertEquals(5, version.getProtocolVersion());
        assertEquals(2, version.getStackType());
        assertEquals(0x5A10, version.getStackVersion());
    }

    @Test
    public void testVersion600() {
        EzspFrame.setEzspVersion(4);
        EzspVersionResponse version = new EzspVersionResponse(getPacketData("01 80 FF 00 00 06 02 00 60"));

        // Version 6.0.0 - EZSP 6
        assertEquals(1, version.getSequenceNumber());
        assertEquals(true, version.isResponse());
        assertEquals(0, version.getFrameId());
        assertEquals(6, version.getProtocolVersion());
        assertEquals(2, version.getStackType());
        assertEquals(0x6000, version.getStackVersion());
    }
}
