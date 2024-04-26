/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
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

/**
 *
 * @author Chris Jackson
 *
 */
public class AshFrameAckTest {
    @Test
    public void testAshFrameAck() {
        AshFrameAck frame;
        AshFrame inFrame;

        frame = new AshFrameAck(4);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 132, 48, 252, 126 }, frame.getOutputBuffer()));

        inFrame = AshFrame.createFromInput(new int[] { 132, 48, 252 });
        assertTrue(inFrame instanceof AshFrameAck);
        assertEquals(4, inFrame.getAckNum());

        frame = new AshFrameAck(7);
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 135, 0, 159, 126 }, frame.getOutputBuffer()));

        inFrame = AshFrame.createFromInput(new int[] { 135, 0, 159 });
        assertTrue(inFrame instanceof AshFrameAck);
        assertEquals(7, inFrame.getAckNum());
    }
}
