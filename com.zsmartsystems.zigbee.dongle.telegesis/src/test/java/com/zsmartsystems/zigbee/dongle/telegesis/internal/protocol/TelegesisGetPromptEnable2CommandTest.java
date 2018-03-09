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
public class TelegesisGetPromptEnable2CommandTest extends TelegesisFrameBaseTest {

    @Test
    public void test() {
        TelegesisGetPromptEnable2Command command = new TelegesisGetPromptEnable2Command();
        System.out.println(command);
        assertEquals("ATS0F?\r\n", intArrayToString(command.serialize()));

        assertFalse(command.deserialize(stringToIntArray("ABCD\r")));
        System.out.println(command);
        assertTrue(command.deserialize(stringToIntArray("OK\r")));
        System.out.println(command);

        assertEquals(Integer.valueOf(0xabcd), command.getConfiguration());
        assertEquals(TelegesisStatusCode.SUCCESS, command.getStatus());
    }
}
