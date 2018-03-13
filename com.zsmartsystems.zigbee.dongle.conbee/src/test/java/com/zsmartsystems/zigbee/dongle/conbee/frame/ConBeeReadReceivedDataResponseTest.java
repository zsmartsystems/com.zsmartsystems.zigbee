/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeReadReceivedDataResponseTest {
    @Test
    public void readResponse() {
        ConBeeReadReceivedDataResponse readResponse = new ConBeeReadReceivedDataResponse(new int[] { 0x17, 0x0C, 0x00,
                0x32, 0x00, 0x2B, 0x00, 0x26, 0x02, 0x00, 0x00, 0x00, 0x03, 0x8C, 0x0A, 0x01, 0xFF, 0xFF, 0x2E, 0x21,
                0x00, 0x00, 0x00, 0x00, 0x01, 0x80, 0x0E, 0x00, 0x00, 0x00, 0x8C, 0x0A, 0x01, 0xFF, 0xFF, 0x2E, 0x21,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xAF, 0x00, 0x00, 0x00, 0x02, 0x02, 0x00, 0x4B, 0xF8, 0xC0 });
        System.out.println(readResponse);
        assertEquals(12, readResponse.getSequence());
        assertEquals(ConBeeStatus.SUCCESS, readResponse.getStatus());
        assertEquals(0x8001, readResponse.getClusterId());
        assertEquals(14, readResponse.getAdsuData().length);
        assertEquals(new IeeeAddress("00212EFFFF010A8C"), readResponse.getSourceIeeeAddress());
    }

    @Test
    public void readErrorResponse() {
        ConBeeReadReceivedDataResponse readResponse = new ConBeeReadReceivedDataResponse(
                new int[] { 0x17, 0x15, 0x05, 0x08, 0x00, 0x01, 0x00, 0x26, 0xA0, 0xFF, 0xC0 });
        System.out.println(readResponse);
        assertEquals(21, readResponse.getSequence());
        assertEquals(ConBeeStatus.ERROR, readResponse.getStatus());
    }
}
