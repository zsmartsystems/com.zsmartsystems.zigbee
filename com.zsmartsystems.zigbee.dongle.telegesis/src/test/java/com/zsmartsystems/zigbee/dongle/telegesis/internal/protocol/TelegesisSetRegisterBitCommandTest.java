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
public class TelegesisSetRegisterBitCommandTest extends TelegesisFrameBaseTest {
    @Test
    public void testTrue() {
        TelegesisSetRegisterBitCommand command = new TelegesisSetRegisterBitCommand();
        command.setRegister(0x0A);
        command.setBit(5);
        command.setState(true);
        System.out.println(command);
        assertEquals("ATS0A5=1\r\n", intArrayToString(command.serialize()));
    }

    @Test
    public void testFalse() {
        TelegesisSetRegisterBitCommand command = new TelegesisSetRegisterBitCommand();
        command.setRegister(0x03);
        command.setBit(15);
        command.setState(false);
        System.out.println(command);
        assertEquals("ATS03F=0\r\n", intArrayToString(command.serialize()));
    }

    @Test
    public void testPassword() {
        TelegesisSetRegisterBitCommand command = new TelegesisSetRegisterBitCommand();
        command.setRegister(0x03);
        command.setBit(15);
        command.setState(false);
        command.setPassword("PassWord");
        System.out.println(command);
        assertEquals("ATS03F=0:PassWord\r\n", intArrayToString(command.serialize()));
    }

}
