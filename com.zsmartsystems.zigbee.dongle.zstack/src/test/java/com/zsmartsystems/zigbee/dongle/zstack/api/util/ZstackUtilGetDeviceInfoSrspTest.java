/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackResponseCode;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZstackUtilGetDeviceInfoSrspTest {
    @Test
    public void test() {
        ZstackUtilGetDeviceInfoSrsp res = new ZstackUtilGetDeviceInfoSrsp(
                new int[] { 0x67, 0x00, 0x00, 0x14, 0xD4, 0xF1, 0x02, 0x00, 0x4B, 0x12, 0x00, 0x00, 0x00, 0x01, 0x00,
                        0x04, 0xFC, 0x35, 0x7E, 0xC4, 0xAF, 0x29, 0xE3, 0xF6 });

        System.out.println(res);

        assertEquals(ZstackResponseCode.SUCCESS, res.getStatus());
        assertEquals(1, res.getDeviceType());
        assertEquals(0, res.getShortAddr());
        assertEquals(new IeeeAddress("00124B0002F1D414"), res.getIeeeAddress());
        assertEquals(ZstackDeviceState.UNINITIALIZED, res.getDeviceState());
        assertEquals(4, res.getAssocDevicesList().length);
        assertEquals(0x35FC, res.getAssocDevicesList()[0]);
    }
}
