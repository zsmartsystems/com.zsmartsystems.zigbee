/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisEnergyScanCommand.ScanResult;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisEnergyScanCommandTest extends TelegesisFrameBaseTest {

    @Test
    public void test() {
        TelegesisEnergyScanCommand command = new TelegesisEnergyScanCommand();
        System.out.println(command);
        assertEquals("AT+ESCAN\r\n", intArrayToString(command.serialize()));

        command.deserialize(stringToIntArray("AT+ESCAN:\r"));
        System.out.println(command);
        assertEquals(0, command.getScanResults().size());

        command.deserialize(stringToIntArray("11:BE\r"));
        command.deserialize(stringToIntArray("12:A0\r"));
        command.deserialize(stringToIntArray("14:A0\r"));
        command.deserialize(stringToIntArray("15:A2\r"));
        command.deserialize(stringToIntArray("16:AB\r"));
        command.deserialize(stringToIntArray("17:D9\r"));
        command.deserialize(stringToIntArray("18:C7\r"));
        command.deserialize(stringToIntArray("19:EC\r"));
        command.deserialize(stringToIntArray("20:C9\r"));
        command.deserialize(stringToIntArray("21:A6\r"));
        System.out.println(command);

        command.deserialize(stringToIntArray("OK\r"));
        System.out.println(command);

        assertEquals(10, command.getScanResults().size());
        assertEquals(TelegesisStatusCode.SUCCESS, command.getStatus());

        ScanResult result;
        Iterator<ScanResult> resultIterator = command.getScanResults().iterator();

        result = resultIterator.next();
        assertEquals(Integer.valueOf(11), result.getChannel());
        assertEquals(Integer.valueOf(0xbe), result.getRssi());

        result = resultIterator.next();

    }
}
