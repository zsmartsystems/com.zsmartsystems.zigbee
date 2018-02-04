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

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspVersionRequest;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspVersionRequestTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        EzspVersionRequest version = new EzspVersionRequest();
        version.setDesiredProtocolVersion(4);
        version.setSequenceNumber(0);

        assertTrue(Arrays.equals(getPacketData("00 00 00 04"), version.serialize()));
    }
}
