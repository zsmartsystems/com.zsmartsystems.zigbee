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

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisGetIeeeAddressCommandTest extends TelegesisFrameBaseTest {

    @Test
    public void testOk() {
        TelegesisGetIeeeAddressCommand command = new TelegesisGetIeeeAddressCommand();
        System.out.println(command);
        assertEquals("ATS04?\r\n", intArrayToString(command.serialize()));

        assertFalse(command.deserialize(stringToIntArray("1122334455AABBCC\r")));
        System.out.println(command);
        assertTrue(command.deserialize(stringToIntArray("OK\r")));
        System.out.println(command);

        assertEquals(new IeeeAddress("1122334455AABBCC"), command.getIeeeAddress());
        assertEquals(TelegesisStatusCode.SUCCESS, command.getStatus());
    }
}
