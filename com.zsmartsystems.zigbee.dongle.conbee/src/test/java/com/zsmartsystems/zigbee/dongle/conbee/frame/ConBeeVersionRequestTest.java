/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeVersionRequestTest {
    @Test
    public void test() {
        ConBeeVersionRequest request = new ConBeeVersionRequest();
        request.setSequence(0x02);
        System.out.println(request);

        assertTrue(Arrays.equals(new int[] { 0x0D, 0x02, 0x00, 0x09, 0x00, 0x00, 0x00, 0x00, 0x00, 0xE8, 0xFF },
                request.getOutputBuffer()));
    }
}
