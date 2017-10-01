/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
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
public class ConBeeDeviceStateRequestTest {
    @Test
    public void doRequest() {
        ConBeeDeviceStateRequest request = new ConBeeDeviceStateRequest();
        request.setSequence(0);
        System.out.println(request);

        assertTrue(Arrays.equals(new int[] { 0x07, 0x00, 0x00, 0x08, 0x00, 0x00, 0x00, 0x00, 0xF1, 0xFF },
                request.getOutputBuffer()));
    }
}
