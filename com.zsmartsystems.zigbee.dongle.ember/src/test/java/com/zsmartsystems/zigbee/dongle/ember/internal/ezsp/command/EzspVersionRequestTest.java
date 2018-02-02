/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameTest;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspVersionRequestTest extends EzspFrameTest {
    @Test
    public void testVersion4() {
        EzspFrame.setEzspVersion(4);
        EzspVersionRequest version = new EzspVersionRequest();
        version.setDesiredProtocolVersion(4);
        version.setSequenceNumber(0);

        assertTrue(Arrays.equals(getPacketData("00 00 00 04"), version.serialize()));
    }

    @Test
    public void testVersion5() {
        EzspFrame.setEzspVersion(5);
        EzspVersionRequest version = new EzspVersionRequest();
        version.setDesiredProtocolVersion(5);
        version.setSequenceNumber(0);

        assertTrue(Arrays.equals(getPacketData("00 00 FF 00 00 05"), version.serialize()));
    }

    @Test
    public void testVersion6() {
        EzspFrame.setEzspVersion(6);
        EzspVersionRequest version = new EzspVersionRequest();
        version.setDesiredProtocolVersion(6);
        version.setSequenceNumber(0);

        assertTrue(Arrays.equals(getPacketData("00 00 FF 00 00 06"), version.serialize()));
    }
}