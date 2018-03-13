/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeVersionResponseTest {
    @Test
    public void readMacAddress() {
        ConBeeVersionResponse readParameter = new ConBeeVersionResponse(
                new int[] { 0x0D, 0x02, 0x00, 0x09, 0x00, 0x00, 0x05, 0x1B, 0x26, 0xA2, 0xFF });
        System.out.println(readParameter);
        assertEquals(2, readParameter.getSequence());
        assertEquals(0x261B0500, readParameter.getVersion());
    }
}
