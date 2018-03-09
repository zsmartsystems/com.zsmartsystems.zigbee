/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisGetPanIdCommandTest extends TelegesisFrameBaseTest {
    @Test
    public void testError01() {
        TelegesisGetPanIdCommand command = new TelegesisGetPanIdCommand();
        System.out.println(command);
        assertEquals("ATS02?\r\n", intArrayToString(command.serialize()));

        assertTrue(command.deserialize(stringToIntArray("ERROR:01\n")));
        System.out.println(command);
        assertEquals(TelegesisStatusCode.TIMEOUT, command.getStatus());
    }

    @Test
    public void testErrorAE() {
        TelegesisGetPanIdCommand command = new TelegesisGetPanIdCommand();
        System.out.println(command);
        assertEquals("ATS02?\r\n", intArrayToString(command.serialize()));

        assertTrue(command.deserialize(stringToIntArray("ERROR:AE\r")));
        System.out.println(command);
        assertEquals(TelegesisStatusCode.NO_LINK_KEY_RECEIVED, command.getStatus());
    }

    @Test
    public void testOk() {
        TelegesisGetPanIdCommand command = new TelegesisGetPanIdCommand();
        System.out.println(command);
        assertEquals("ATS02?\r\n", intArrayToString(command.serialize()));

        assertFalse(command.deserialize(stringToIntArray("1234\r")));
        System.out.println(command);
        assertTrue(command.deserialize(stringToIntArray("OK\r")));
        System.out.println(command);

        assertEquals(Integer.valueOf(0x1234), command.getPanId());
        assertEquals(TelegesisStatusCode.SUCCESS, command.getStatus());
    }
}
