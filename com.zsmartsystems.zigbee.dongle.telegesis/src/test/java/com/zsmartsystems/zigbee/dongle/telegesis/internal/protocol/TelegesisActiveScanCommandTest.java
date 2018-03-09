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

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisActiveScanCommand.ScanResult;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisActiveScanCommandTest extends TelegesisFrameBaseTest {

    @Test
    public void test() {
        TelegesisActiveScanCommand command = new TelegesisActiveScanCommand();
        command.setChannelMask(0xFFF0);
        System.out.println(command);
        assertEquals("AT+PANSCAN:FFF0\r\n", intArrayToString(command.serialize()));

        command.deserialize(stringToIntArray("+PANSCAN:11,2001,000000003ADE68B1,02,00\r"));
        System.out.println(command);
        command.deserialize(stringToIntArray("+PANSCAN:19,9D12,BD42FE090EEE14CA,02,00\r"));
        System.out.println(command);

        command.deserialize(stringToIntArray("OK\r"));
        System.out.println(command);

        assertEquals(2, command.getScanResults().size());
        assertEquals(TelegesisStatusCode.SUCCESS, command.getStatus());

        ScanResult result;
        Iterator<ScanResult> resultIterator = command.getScanResults().iterator();

        result = resultIterator.next();
        assertEquals(Integer.valueOf(11), result.getChannel());
        assertEquals(Integer.valueOf(0x2001), result.getPanId());
        assertEquals(new ExtendedPanId("000000003ADE68B1"), result.getEpanId());
        assertEquals(Integer.valueOf(2), result.getProfileId());
        assertEquals(false, result.getJoiningEnabled());

        result = resultIterator.next();
        assertEquals(Integer.valueOf(19), result.getChannel());
        assertEquals(Integer.valueOf(0x9D12), result.getPanId());
        assertEquals(new ExtendedPanId("BD42FE090EEE14CA"), result.getEpanId());
        assertEquals(Integer.valueOf(2), result.getProfileId());
        assertEquals(false, result.getJoiningEnabled());
    }
}
