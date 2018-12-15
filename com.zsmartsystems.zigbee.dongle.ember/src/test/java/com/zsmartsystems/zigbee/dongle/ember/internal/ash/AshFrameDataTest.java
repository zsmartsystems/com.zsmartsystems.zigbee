/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ash.AshFrame.FrameType;

/**
 *
 * @author Chris Jackson
 *
 */
public class AshFrameDataTest {

    @Test
    public void testAshFrameData() {
        AshFrameData frame;
        EzspVersionRequest request = new EzspVersionRequest();
        request.setSequenceNumber(1);
        request.setDesiredProtocolVersion(4);
        frame = new AshFrameData(request);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 0, 67, 33, 168, 80, 155, 152, 126 }, frame.getOutputBuffer()));

        request = new EzspVersionRequest();
        request.setSequenceNumber(2);
        request.setDesiredProtocolVersion(4);
        frame = new AshFrameData(request);
        frame.setAckNum(3);
        frame.setFrmNum(4);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 67, 64, 33, 168, 80, 255, 254, 126 }, frame.getOutputBuffer()));

        request = new EzspVersionRequest();
        request.setSequenceNumber(3);
        request.setDesiredProtocolVersion(4);
        frame = new AshFrameData(request);
        frame.setAckNum(6);
        frame.setFrmNum(2);
        frame.setReTx();
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 46, 65, 33, 168, 80, 177, 236, 126 }, frame.getOutputBuffer()));

        AshFrame inFrame = AshFrame.createFromInput(new int[] { 46, 65, 33, 168, 80, 177, 236 });
        assertTrue(inFrame instanceof AshFrameData);
        assertEquals(6, inFrame.getAckNum());
        assertEquals(2, inFrame.getFrmNum());
        assertEquals(FrameType.DATA, inFrame.getFrameType());
    }
}
