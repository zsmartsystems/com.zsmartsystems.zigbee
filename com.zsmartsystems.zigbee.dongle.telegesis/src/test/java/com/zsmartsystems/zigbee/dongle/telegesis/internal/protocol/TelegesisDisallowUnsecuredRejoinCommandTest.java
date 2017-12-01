/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
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
public class TelegesisDisallowUnsecuredRejoinCommandTest extends TelegesisFrameBaseTest {
    @Test
    public void test() {
        TelegesisDisallowUnsecuredRejoinCommand command = new TelegesisDisallowUnsecuredRejoinCommand();
        command.setDisallowRejoin(false);
        command.setPassword("password");
        System.out.println(command);
        assertEquals("ATS0A3=0:password\r\n", intArrayToString(command.serialize()));

        command = new TelegesisDisallowUnsecuredRejoinCommand();
        command.setDisallowRejoin(true);
        command.setPassword("password");
        System.out.println(command);
        assertEquals("ATS0A3=1:password\r\n", intArrayToString(command.serialize()));
    }

}
