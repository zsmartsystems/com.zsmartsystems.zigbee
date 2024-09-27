/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.af;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZstackAfRegisterSreqTest {
    @Test
    public void test() {
        ZstackAfRegisterSreq request = new ZstackAfRegisterSreq();

        request.setEndPoint(1);
        request.setAppDeviceId(2);
        request.setAppProfId(0x1122);
        request.setAppInClusterList(new int[] { 1, 2, 3 });
        request.setAppOutClusterList(new int[] { 0x1234 });
        request.setLatencyReq(0x21);
        request.setAppDevVer(0x12);

        System.out.println(request);

        int[] x = request.serialize();

        assertTrue(Arrays.equals(new int[] { 0xFE, 0x11, 0x24, 0x00, 0x01, 0x22, 0x11, 0x02, 0x00, 0x12, 0x21, 0x03,
                0x01, 0x00, 0x02, 0x00, 0x03, 0x00, 0x01, 0x34, 0x12, 0x12 }, x));
    }
}
