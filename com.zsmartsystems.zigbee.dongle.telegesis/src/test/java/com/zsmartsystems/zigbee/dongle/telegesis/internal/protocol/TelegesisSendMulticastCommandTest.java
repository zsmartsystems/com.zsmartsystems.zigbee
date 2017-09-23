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

import com.zsmartsystems.zigbee.ZigBeeGroupAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisSendMulticastCommandTest extends TelegesisFrameBaseTest {
    @Test
    public void test() {
        ZigBeeGroupAddress address = new ZigBeeGroupAddress(1234);

        TelegesisSendMulticastCommand command = new TelegesisSendMulticastCommand();
        command.setGroupId(address);
        command.setDestEp(0x38);
        command.setSourceEp(1);
        command.setProfileId(0x104);
        command.setClusterId(0x1204);
        command.setRadius(4);
        command.setMessageData(stringToIntArray("HELLO"));
        System.out.println(command);
        assertEquals("AT+SENDMCASTB:05,04,04D2,01,38,0104,1204,HELLO\r\n", intArrayToString(command.serialize()));
    }
}
