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
public class TelegesisSetPanIdCommandTest extends TelegesisFrameBaseTest {
    @Test
    public void test() {
        TelegesisSetPanIdCommand command = new TelegesisSetPanIdCommand();
        command.setPanId(0);
        System.out.println(command);
        assertEquals("ATS02=0000\r\n", intArrayToString(command.serialize()));

        command = new TelegesisSetPanIdCommand();
        command.setPanId(1);
        System.out.println(command);
        assertEquals("ATS02=0001\r\n", intArrayToString(command.serialize()));

        command = new TelegesisSetPanIdCommand();
        command.setPanId(65535);
        System.out.println(command);
        assertEquals("ATS02=FFFF\r\n", intArrayToString(command.serialize()));
    }
}
