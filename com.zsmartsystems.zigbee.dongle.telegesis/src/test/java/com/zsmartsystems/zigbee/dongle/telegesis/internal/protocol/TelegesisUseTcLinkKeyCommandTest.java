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
public class TelegesisUseTcLinkKeyCommandTest extends TelegesisFrameBaseTest {
    @Test
    public void test() {
        TelegesisUseTcLinkKeyCommand command = new TelegesisUseTcLinkKeyCommand();
        command.setUseKey(false);
        command.setPassword("password");
        System.out.println(command);
        assertEquals("ATS0A8=0:password\r\n", intArrayToString(command.serialize()));

        command = new TelegesisUseTcLinkKeyCommand();
        command.setUseKey(true);
        command.setPassword("password");
        System.out.println(command);
        assertEquals("ATS0A8=1:password\r\n", intArrayToString(command.serialize()));
    }

}
