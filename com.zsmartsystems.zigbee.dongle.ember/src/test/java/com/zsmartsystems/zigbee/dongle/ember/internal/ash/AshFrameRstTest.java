/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ash;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class AshFrameRstTest {
    @Test
    public void testAshFrameRst() {
        AshFrameRst frame;
        frame = new AshFrameRst();
        System.out.println(frame);
        assertTrue(Arrays.equals(new int[] { 26, 192, 56, 188, 126 }, frame.getOutputBuffer()));

        AshFrame inFrame = AshFrame.createFromInput(new int[] { 192, 56, 188 });
        assertTrue(inFrame instanceof AshFrameRst);
    }

}
