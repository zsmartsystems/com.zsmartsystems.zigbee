/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.DeliveryStatus;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.DiscoveryStatus;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeResponse;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeTransmitStatusResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class XBeeResponseFactoryTest {
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
    public void testGetResponse() {
        int[] data = getPacketData("00 07 8B 8F F7 7B 00 00 40 33");
        XBeeResponse frame = XBeeResponseFactory.getXBeeFrame(data);
        assertTrue(frame instanceof XBeeTransmitStatusResponse);
        System.out.println(frame);

        XBeeTransmitStatusResponse event = (XBeeTransmitStatusResponse) frame;
        assertEquals(Integer.valueOf(143), event.getFrameId());
        assertEquals(Integer.valueOf(0), event.getTransmitRetryCount());
        assertEquals(DeliveryStatus.SUCCESS, event.getDeliveryStatus());
        assertEquals(DiscoveryStatus.EXTENDED_TIMEOUT_DISCOVERY, event.getDiscoveryStatus());
    }

}
