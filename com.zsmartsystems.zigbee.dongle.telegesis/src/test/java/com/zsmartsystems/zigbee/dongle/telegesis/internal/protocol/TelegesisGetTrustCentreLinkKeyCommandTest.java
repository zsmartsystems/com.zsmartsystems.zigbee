/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisGetTrustCentreLinkKeyCommandTest extends TelegesisFrameBaseTest {

    @Test
    public void testOk() {
        TelegesisGetTrustCentreLinkKeyCommand command = new TelegesisGetTrustCentreLinkKeyCommand();
        System.out.println(command);
        assertEquals("ATS09?\r\n", intArrayToString(command.serialize()));

        assertFalse(command.deserialize(stringToIntArray("11223344556677889900AABBCCDDEEFF\r")));
        System.out.println(command);
        assertTrue(command.deserialize(stringToIntArray("OK\r")));
        System.out.println(command);

        assertEquals(new ZigBeeKey("11223344556677889900AABBCCDDEEFF"), command.getLinkKey());
        assertEquals(TelegesisStatusCode.SUCCESS, command.getStatus());
    }
}
