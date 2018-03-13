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
public class TelegesisDisplayProductIdentificationCommandTest extends TelegesisFrameBaseTest {

    @Test
    public void testOk() {
        TelegesisDisplayProductIdentificationCommand command = new TelegesisDisplayProductIdentificationCommand();
        System.out.println(command);
        assertEquals("ATI\r\n", intArrayToString(command.serialize()));

        assertFalse(command.deserialize(stringToIntArray("Telegesis ETRX357-LRS\r")));
        System.out.println(command);
        assertFalse(command.deserialize(stringToIntArray("R309\r")));
        System.out.println(command);
        assertFalse(command.deserialize(stringToIntArray("1234567890ABCDEF\r")));
        System.out.println(command);
        assertTrue(command.deserialize(stringToIntArray("OK\r")));
        System.out.println(command);

        assertEquals("ETRX357-LRS", command.getDeviceName());
        assertEquals("309", command.getFirmwareRevision());
        assertEquals(new IeeeAddress("1234567890ABCDEF"), command.getIeeeAddress());
        assertEquals(TelegesisStatusCode.SUCCESS, command.getStatus());
    }
}
