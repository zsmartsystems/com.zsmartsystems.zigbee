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
public class TelegesisGetExtendedFunctionCommandTest extends TelegesisFrameBaseTest {
    @Test
    public void test() {
        TelegesisGetExtendedFunctionCommand command = new TelegesisGetExtendedFunctionCommand();
        System.out.println(command);
        assertEquals("ATS10?\r\n", intArrayToString(command.serialize()));

        assertFalse(command.deserialize(stringToIntArray("FE12\n")));

        assertTrue(command.deserialize(stringToIntArray("OK\n")));
        System.out.println(command);
        assertEquals(Integer.valueOf(0xfe12), command.getConfiguration());
        assertEquals(TelegesisStatusCode.SUCCESS, command.getStatus());
    }
}
