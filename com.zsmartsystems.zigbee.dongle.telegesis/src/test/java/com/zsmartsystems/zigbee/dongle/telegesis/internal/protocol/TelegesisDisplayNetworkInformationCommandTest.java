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

import com.zsmartsystems.zigbee.ExtendedPanId;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisDisplayNetworkInformationCommandTest extends TelegesisFrameBaseTest {
    @Test
    public void test() {
        TelegesisDisplayNetworkInformationCommand command = new TelegesisDisplayNetworkInformationCommand();
        System.out.println(command);
        assertEquals("AT+N?\r\n", intArrayToString(command.serialize()));

        command.deserialize(stringToIntArray("+N=FFD,18,5,1234,1234567890ABCDEF"));
        System.out.println(command);

        assertEquals(Integer.valueOf(18), command.getChannel());
        assertEquals(Integer.valueOf(5), command.getPower());
        assertEquals(Integer.valueOf(0x1234), command.getPanId());
        assertEquals(new ExtendedPanId("1234567890ABCDEF"), command.getEpanId());
    }

    @Test
    public void testNoPAN() {
        TelegesisDisplayNetworkInformationCommand command = new TelegesisDisplayNetworkInformationCommand();
        System.out.println(command);
        assertEquals("AT+N?\r\n", intArrayToString(command.serialize()));

        command.deserialize(stringToIntArray("+N=NOPAN"));
        System.out.println(command);
    }
}
