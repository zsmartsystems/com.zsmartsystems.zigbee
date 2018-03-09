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

import com.zsmartsystems.zigbee.ZigBeeKey;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisGetNetworkKeyCommandTest extends TelegesisFrameBaseTest {

    @Test
    public void testOk() {
        TelegesisGetNetworkKeyCommand command = new TelegesisGetNetworkKeyCommand();
        System.out.println(command);
        assertEquals("ATS08?\r\n", intArrayToString(command.serialize()));

        assertFalse(command.deserialize(stringToIntArray("11223344556677889900AABBCCDDEEFF\r")));
        System.out.println(command);
        assertTrue(command.deserialize(stringToIntArray("OK\r")));
        System.out.println(command);

        assertEquals(new ZigBeeKey("11223344556677889900AABBCCDDEEFF"), command.getNetworkKey());
        assertEquals(TelegesisStatusCode.SUCCESS, command.getStatus());
    }
}
