/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZstackFrameRequestTest {

    @Test
    public void deserializeUInt16() {
        Request request = new Request();
        request.serializeUInt16(0x1234);

        assertTrue(Arrays.equals(new int[] { 0xFE, 0x04, 0x00, 0x00, 0x34, 0x12, 0x22 }, request.getPayload()));
    }

    @Test
    public void serializeUInt32() {
        Request request = new Request();
        request.serializeUInt32(0x12345678);

        assertTrue(Arrays.equals(new int[] { 0xFE, 0x06, 0x00, 0x00, 0x78, 0x56, 0x34, 0x12, 0x0E },
                request.getPayload()));
    }

    @Test
    public void serializeIeeeAddress() {
        Request request = new Request();
        IeeeAddress address = new IeeeAddress("1234567890ABCDEF");
        request.serializeIeeeAddress(address);

        assertTrue(Arrays.equals(
                new int[] { 0xFE, 0x0A, 0x00, 0x00, 0xEF, 0xCD, 0xAB, 0x90, 0x78, 0x56, 0x34, 0x12, 0x1B },
                request.getPayload()));
    }

    @Test
    public void serializeBoolean() {
        Request request = new Request();
        request.serializeBoolean(false);
        request.serializeBoolean(true);

        assertTrue(Arrays.equals(new int[] { 0xFE, 0x04, 0x00, 0x00, 0x00, 0x01, 0x05 }, request.getPayload()));
    }

    class Request extends ZstackFrameRequest {
        @Override
        public int[] serialize() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
