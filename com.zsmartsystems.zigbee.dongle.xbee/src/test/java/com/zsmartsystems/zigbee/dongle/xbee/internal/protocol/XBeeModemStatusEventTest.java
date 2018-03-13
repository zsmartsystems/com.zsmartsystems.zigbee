/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class XBeeModemStatusEventTest extends XBeeFrameBaseTest {
    @Test
    public void test() {
        XBeeModemStatusEvent event = new XBeeModemStatusEvent();
        event.deserialize(getPacketData("00 02 8A 06 6F"));
        System.out.println(event);
        assertEquals(0x8A, event.getFrameType());
        assertEquals(ModemStatus.COORDINATOR_STARTED, event.getStatus());
    }
}
