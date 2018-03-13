/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeFrameTest {
    @Test
    public void testCreateDeviceStateResponse() {
        ConBeeFrame frame = ConBeeFrame
                .create(new int[] { 0x07, 0x00, 0x00, 0x08, 0x00, 0xA2, 0x00, 0x00, 0x4F, 0xFF });
        System.out.println(frame);

        assertNotNull(frame);
        assertTrue(frame instanceof ConBeeDeviceStateResponse);
        assertEquals(0, frame.getSequence());
    }

    @Test
    public void testCreateEnqueueSendDataResponse() {
        ConBeeFrame frame = ConBeeFrame
                .create(new int[] { 0x12, 0x0D, 0x00, 0x09, 0x00, 0x02, 0x00, 0x22, 0x00, 0xB4, 0xFF });
        System.out.println(frame);

        assertNotNull(frame);
        assertTrue(frame instanceof ConBeeEnqueueSendDataResponse);
        assertEquals(13, frame.getSequence());
    }

    @Test
    public void testCreateQuerySendDataStateResponse() {
        ConBeeFrame frame = ConBeeFrame.create(new int[] { 0x04, 0x00, 0x00, 0x13, 0x00, 0x0C, 0x00, 0x22, 0x21, 0x02,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x98, 0xFF });
        System.out.println(frame);

        assertNotNull(frame);
        assertTrue(frame instanceof ConBeeQuerySendDataResponse);
        ConBeeQuerySendDataResponse sendDataResponse = (ConBeeQuerySendDataResponse) frame;
        assertEquals(0, sendDataResponse.getSequence());
        assertEquals(33, sendDataResponse.getRequestId());
        assertEquals(ConBeeNetworkState.NET_CONNECTED, sendDataResponse.getDeviceState().getNetworkState());
    }

}
