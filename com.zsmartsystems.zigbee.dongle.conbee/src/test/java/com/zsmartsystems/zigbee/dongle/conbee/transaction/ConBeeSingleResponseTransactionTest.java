/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeDeviceStateRequest;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeDeviceStateResponse;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeEnqueueSendDataResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeSingleResponseTransactionTest {
    @Test
    public void testResponseMatches() {
        ConBeeDeviceStateRequest request = new ConBeeDeviceStateRequest();

        ConBeeTransaction transaction = new ConBeeSingleResponseTransaction(request, ConBeeDeviceStateResponse.class);

        ConBeeDeviceStateResponse response = new ConBeeDeviceStateResponse(
                new int[] { 0x07, 0x00, 0x00, 0x08, 0x00, 0xA2, 0x00, 0x00, 0x4F, 0xFF });

        assertTrue(transaction.isMatch(response));

        transaction.getRequest();
        assertEquals(1, transaction.getResponses().size());
        assertNotNull(transaction.getResponses());
        assertEquals(transaction.getResponses().get(0), transaction.getResponse());
    }

    @Test
    public void testResponseMatchFails() {
        ConBeeDeviceStateRequest request = new ConBeeDeviceStateRequest();

        ConBeeTransaction transaction = new ConBeeSingleResponseTransaction(request, ConBeeDeviceStateResponse.class);

        ConBeeEnqueueSendDataResponse response = new ConBeeEnqueueSendDataResponse(
                new int[] { 0x12, 0x0D, 0x00, 0x09, 0x00, 0x02, 0x00, 0x22, 0x00, 0xB4, 0xFF });

        assertFalse(transaction.isMatch(response));
        assertNull(transaction.getResponse());
    }
}
