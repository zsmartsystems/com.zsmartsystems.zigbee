/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
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
        EzspFrame.setEzspVersion(4);

        EzspFrameRequest request = Mockito.mock(EzspFrameRequest.class, Mockito.CALLS_REAL_METHODS);

        EzspSerializer serializer = new EzspSerializer();
        request.serializeHeader(serializer);
        assertTrue(Arrays.equals(new int[] { 0x00, 0x00, 0x00 }, serializer.getPayload()));
        EzspFrame.setEzspVersion(4);
    }

    @Test
    public void testRequestV5() {
        EzspFrame.setEzspVersion(5);

        EzspFrameRequest request = Mockito.mock(EzspFrameRequest.class, Mockito.CALLS_REAL_METHODS);

        EzspSerializer serializer = new EzspSerializer();
        request.serializeHeader(serializer);
        assertTrue(Arrays.equals(new int[] { 0x00, 0x00, 0xFF, 0x00, 0x00 }, serializer.getPayload()));
        EzspFrame.setEzspVersion(4);
    }

    @Test
    public void testRequestV8() {
        EzspFrame.setEzspVersion(8);

        EzspFrameRequest request = Mockito.mock(EzspFrameRequest.class, Mockito.CALLS_REAL_METHODS);

        EzspSerializer serializer = new EzspSerializer();
        request.serializeHeader(serializer);
        assertTrue(Arrays.equals(new int[] { 0x00, 0x00, 0x01, 0x00, 0x00 }, serializer.getPayload()));
        EzspFrame.setEzspVersion(4);
    }

    @Test
    public void testRequestV8NetworkId() {
        EzspFrame.setEzspVersion(8);

        EzspFrameRequest request = Mockito.mock(EzspFrameRequest.class, Mockito.CALLS_REAL_METHODS);

        request.setNetworkId(1);
        EzspSerializer serializer = new EzspSerializer();
        request.serializeHeader(serializer);
        assertTrue(Arrays.equals(new int[] { 0x00, 0x20, 0x01, 0x00, 0x00 }, serializer.getPayload()));

        request.setNetworkId(3);
        serializer = new EzspSerializer();
        request.serializeHeader(serializer);
        assertTrue(Arrays.equals(new int[] { 0x00, 0x60, 0x01, 0x00, 0x00 }, serializer.getPayload()));

        EzspFrame.setEzspVersion(4);
    }

}