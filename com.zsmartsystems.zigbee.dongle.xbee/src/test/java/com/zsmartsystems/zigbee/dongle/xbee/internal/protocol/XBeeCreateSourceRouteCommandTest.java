/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class XBeeCreateSourceRouteCommandTest {
    @Test
    public void test() {
        XBeeCreateSourceRouteCommand command = new XBeeCreateSourceRouteCommand();

        command.setFrameId(0);
        command.setIeeeAddress(new IeeeAddress("0013A20040401122"));
        command.setNetworkAddress(0x3344);
        command.setAddressList(new int[] { 0xEEFF, 0xCCDD, 0xAABB });
        System.out.println(command);
        assertTrue(Arrays.equals(new int[] { 0x00, 0x14, 0x21, 0x00, 0x00, 0x13, 0xA2, 0x00, 0x40, 0x40, 0x11, 0x22,
                0x33, 0x44, 0x00, 0x03, 0xEE, 0xFF, 0xCC, 0xDD, 0xAA, 0xBB, 0x01 }, command.serialize()));
    }
}
