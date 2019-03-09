/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.sys;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZstackSysResetReqAcmdTest {
    @Test
    public void test() {
        ZstackSysResetReqAcmd req = new ZstackSysResetReqAcmd();
        req.setType(ZstackResetType.TARGET_DEVICE);

        System.out.println(req);
        int[] data = req.serialize();
        assertTrue(Arrays.equals(data, new int[] { 0xFE, 0x01, 0x41, 0x00, 0x00, 0x40 }));

        req = new ZstackSysResetReqAcmd();
        req.setType(ZstackResetType.SERIAL_BOOTLOADER);

        System.out.println(req);
        data = req.serialize();
        assertTrue(Arrays.equals(data, new int[] { 0xFE, 0x01, 0x41, 0x00, 0x01, 0x41 }));
    }
}
