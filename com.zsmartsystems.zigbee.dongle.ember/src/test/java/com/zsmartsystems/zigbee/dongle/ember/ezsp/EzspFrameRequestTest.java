/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspFrameRequestTest {

    @Test
    public void testRequestV4() {
        EzspFrameRequest request = Mockito.mock(EzspFrameRequest.class, Mockito.CALLS_REAL_METHODS);

        EzspSerializer serializer = new EzspSerializer();
        request.serializeHeader(4, serializer);
        assertTrue(Arrays.equals(new int[] { 0x00, 0x00, 0x00 }, serializer.getPayload()));
    }

    @Test
    public void testRequestV5() {
        EzspFrameRequest request = Mockito.mock(EzspFrameRequest.class, Mockito.CALLS_REAL_METHODS);

        EzspSerializer serializer = new EzspSerializer();
        request.serializeHeader(5, serializer);
        assertTrue(Arrays.equals(new int[] { 0x00, 0x00, 0xFF, 0x00, 0x00 }, serializer.getPayload()));
    }

    @Test
    public void testRequestV8() {
        EzspFrameRequest request = Mockito.mock(EzspFrameRequest.class, Mockito.CALLS_REAL_METHODS);

        EzspSerializer serializer = new EzspSerializer();
        request.serializeHeader(8, serializer);
        assertTrue(Arrays.equals(new int[] { 0x00, 0x00, 0x01, 0x00, 0x00 }, serializer.getPayload()));
    }

    @Test
    public void testRequestV8NetworkId() {
        EzspFrameRequest request = Mockito.mock(EzspFrameRequest.class, Mockito.CALLS_REAL_METHODS);

        request.setNetworkId(1);
        EzspSerializer serializer = new EzspSerializer();
        request.serializeHeader(8, serializer);
        assertTrue(Arrays.equals(new int[] { 0x00, 0x20, 0x01, 0x00, 0x00 }, serializer.getPayload()));

        request.setNetworkId(3);
        serializer = new EzspSerializer();
        request.serializeHeader(8, serializer);
        assertTrue(Arrays.equals(new int[] { 0x00, 0x60, 0x01, 0x00, 0x00 }, serializer.getPayload()));
    }

}