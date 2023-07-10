/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberAesMmoHashContext;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspAesMmoHashRequestTest extends EzspFrameTest {

    @Test
    public void test() throws Exception {
        EzspFrame.setEzspVersion(4);
        EmberAesMmoHashContext context = new EmberAesMmoHashContext();
        context.setResult(new int[16]);
        EzspAesMmoHashRequest request = new EzspAesMmoHashRequest();
        request.setContext(context);
        request.setData(new int[] { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77 });
        request.setFinalize(true);
        TestUtilities.setField(EzspFrame.class, request, "sequenceNumber", 170);
        System.out.println(request);

        assertTrue(Arrays.equals(getPacketData(
                "AA 00 6F 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 11 22 33 44 55 66 77"),
                request.serialize()));
    }
}
