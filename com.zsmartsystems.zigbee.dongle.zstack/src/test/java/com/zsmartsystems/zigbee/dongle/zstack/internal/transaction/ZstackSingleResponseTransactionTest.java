/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.internal.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilLedControlSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilLedControlSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilSetChannelsSrsp;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZstackSingleResponseTransactionTest {
    @Test
    public void testResponseMatches() {
        ZstackUtilLedControlSreq request = new ZstackUtilLedControlSreq();
        request.setLedId(1);
        request.setMode(false);
        System.out.println(request);

        ZstackTransaction transaction = new ZstackSingleResponseTransaction(request, ZstackUtilLedControlSrsp.class);

        ZstackUtilLedControlSrsp response = new ZstackUtilLedControlSrsp(new int[] { 0x00, 0x00, 0x34, 0x12 });
        System.out.println(response);

        assertTrue(transaction.isMatch(response));

        assertEquals(request, transaction.getRequest());
        assertNotNull(transaction.getResponse());
        assertEquals(response, transaction.getResponse());
    }

    @Test
    public void testResponseMatchFails() {
        ZstackUtilLedControlSreq request = new ZstackUtilLedControlSreq();
        request.setLedId(1);
        request.setMode(false);
        System.out.println(request);

        ZstackTransaction transaction = new ZstackSingleResponseTransaction(request, ZstackUtilLedControlSrsp.class);

        ZstackUtilSetChannelsSrsp response = new ZstackUtilSetChannelsSrsp(new int[] { 0x00, 0x00, 0x34, 0x12 });
        System.out.println(response);

        assertFalse(transaction.isMatch(response));

        assertEquals(request, transaction.getRequest());
        assertNull(transaction.getResponse());
    }
}
