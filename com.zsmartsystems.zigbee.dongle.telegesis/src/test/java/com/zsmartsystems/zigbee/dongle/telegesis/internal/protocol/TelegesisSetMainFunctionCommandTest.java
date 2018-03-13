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
public class TelegesisSetMainFunctionCommandTest extends TelegesisFrameBaseTest {
    @Test
    public void test() {
        TelegesisSetMainFunctionCommand command = new TelegesisSetMainFunctionCommand();
        command.setConfiguration(0);
        command.setPassword("password");
        System.out.println(command);
        assertEquals("ATS0A=0000:password\r\n", intArrayToString(command.serialize()));

        command = new TelegesisSetMainFunctionCommand();
        command.setConfiguration(1);
        command.setPassword("password");
        System.out.println(command);
        assertEquals("ATS0A=0001:password\r\n", intArrayToString(command.serialize()));
        assertEquals(null, command.getStatus());

        command.deserialize(stringToIntArray("ERROR:20\r\n"));
        System.out.println(command);
        assertEquals(TelegesisStatusCode.INVALID_PASSWORD, command.getStatus());
    }
}
