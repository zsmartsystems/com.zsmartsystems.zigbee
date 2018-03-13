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
public class TelegesisChangeChannelCommandTest extends TelegesisFrameBaseTest {
    @Test(expected = IllegalArgumentException.class)
    public void testChannelLow() {
        TelegesisChangeChannelCommand command = new TelegesisChangeChannelCommand();
        command.setChannel(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChannelHigh() {
        TelegesisChangeChannelCommand command = new TelegesisChangeChannelCommand();
        command.setChannel(27);
    }

    @Test
    public void testChannelSpecified() {
        TelegesisChangeChannelCommand command = new TelegesisChangeChannelCommand();
        command.setChannel(12);
        System.out.println(command);
        assertEquals("AT+CCHANGE:12\r\n", intArrayToString(command.serialize()));

        command.deserialize(stringToIntArray("OK\r"));
        System.out.println(command);

        assertEquals(TelegesisStatusCode.SUCCESS, command.getStatus());
    }

    @Test
    public void testNoChannelSpecified() {
        TelegesisChangeChannelCommand command = new TelegesisChangeChannelCommand();
        System.out.println(command);
        assertEquals("AT+CCHANGE\r\n", intArrayToString(command.serialize()));

        command.deserialize(stringToIntArray("OK\r"));
        System.out.println(command);

        assertEquals(TelegesisStatusCode.SUCCESS, command.getStatus());
    }
}
