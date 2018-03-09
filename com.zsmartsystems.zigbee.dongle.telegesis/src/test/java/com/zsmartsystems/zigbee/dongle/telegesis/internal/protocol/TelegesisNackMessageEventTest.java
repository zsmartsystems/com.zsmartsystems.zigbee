/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisNackMessageEventTest extends TelegesisFrameBaseTest {
    @Test
    public void testRemoteAddress() {
        TelegesisNackMessageEvent event = new TelegesisNackMessageEvent();
        event.deserialize(stringToIntArray("NACK:42"));
        System.out.println(event);
        assertEquals(Integer.valueOf(0x42), event.getMessageId());
    }
}
