/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.internal.frame;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.conbee.internal.frame.ConBeeNetworkParameter;
import com.zsmartsystems.zigbee.dongle.conbee.internal.frame.ConBeeReadParameterRequest;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeReadParameterRequestTest {
    @Test
    public void test() {
        ConBeeReadParameterRequest request = new ConBeeReadParameterRequest();
        request.setParameter(ConBeeNetworkParameter.MAC_ADDRESS);
        request.setSequence(0x15);
        System.out.println(request);

        assertTrue(Arrays.equals(new int[] { 0x0A, 0x15, 0x00, 0x08, 0x00, 0x01, 0x00, 0x01, 0xD7, 0xff },
                request.getOutputBuffer()));
    }
}
