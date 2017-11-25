/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspFrameTest {
    protected int[] getPacketData(String stringData) {
        String split[] = stringData.split(" ");

        int[] response = new int[split.length];
        int cnt = 0;
        for (String val : split) {
            response[cnt++] = Integer.parseInt(val, 16);
        }

        return response;
    }

    @Test
    public void testCreateHandlerV4() {
        AshFrameData data = new AshFrameData();
        data.setData(new int[] { 0x01, 0x80, 0x00, 0x04, 0x02, 0x00, 0x59 });
        EzspFrameResponse frame = EzspFrame.createHandler(data);
        System.out.println(frame);
        assertTrue(frame instanceof EzspVersionResponse);
    }

    @Test
    public void testCreateHandlerV5() {
        AshFrameData data = new AshFrameData();
        data.setData(new int[] { 0x01, 0x80, 0xFF, 0x00, 0x00, 0x05, 0x02, 0x10, 0x5A });
        EzspFrameResponse frame = EzspFrame.createHandler(data);
        System.out.println(frame);
        assertTrue(frame instanceof EzspVersionResponse);
    }
}
