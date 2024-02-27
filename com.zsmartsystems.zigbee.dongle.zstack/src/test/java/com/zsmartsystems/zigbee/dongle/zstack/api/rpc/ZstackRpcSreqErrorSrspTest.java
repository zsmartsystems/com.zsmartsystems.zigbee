/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.rpc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZstackRpcSreqErrorSrspTest {
    @Test
    public void test() {
        ZstackRpcSreqErrorSrsp res = new ZstackRpcSreqErrorSrsp(new int[] { 0x60, 0x00, 0x02, 0x21, 0x19, 0x59 });

        System.out.println(res);

        assertEquals(0x21, res.getReqCmd0());
        assertEquals(0x19, res.getReqCmd1());
        assertEquals(ZstackSreqErrorCode.INVALID_COMMAND_ID, res.getErrorCode());
    }
}
