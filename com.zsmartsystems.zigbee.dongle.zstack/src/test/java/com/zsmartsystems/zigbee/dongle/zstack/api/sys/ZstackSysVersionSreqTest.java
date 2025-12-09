/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.sys;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZstackSysVersionSreqTest {
    @Test
    public void test() {
        ZstackSysVersionSreq req = new ZstackSysVersionSreq();

        System.out.println(req);
        int[] data = req.serialize();
        assertTrue(Arrays.equals(data, new int[] { 0xFE, 0x00, 0x21, 0x02, 0x23 }));

        ZstackRpcSreqErrorSrsp err = Mockito.mock(ZstackRpcSreqErrorSrsp.class);
        Mockito.when(err.getReqCmd0()).thenReturn(0x21);
        Mockito.when(err.getReqCmd1()).thenReturn(0x02);
        assertTrue(req.matchSreqError(err));

        Mockito.when(err.getReqCmd0()).thenReturn(0x21);
        Mockito.when(err.getReqCmd1()).thenReturn(0x03);
        assertFalse(req.matchSreqError(err));

        Mockito.when(err.getReqCmd0()).thenReturn(0x22);
        Mockito.when(err.getReqCmd1()).thenReturn(0x02);
        assertFalse(req.matchSreqError(err));

        Mockito.when(err.getReqCmd0()).thenReturn(0x41);
        Mockito.when(err.getReqCmd1()).thenReturn(0x02);
        assertTrue(req.matchSreqError(err));
    }
}
