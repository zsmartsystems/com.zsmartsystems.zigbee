/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspEnergyScanResultHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkFoundHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspScanCompleteHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStartScanRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStartScanResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspNetworkScanType;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspMultiResponseTransactionTest extends EzspFrameTest {
    @Test
    public void testResponseMatches() {
        EzspStartScanRequest request = new EzspStartScanRequest();
        request.setChannelMask(ZigBeeChannelMask.CHANNEL_MASK_2GHZ);
        request.setDuration(1);
        request.setScanType(EzspNetworkScanType.EZSP_ENERGY_SCAN);

        Set<Class<?>> relatedResponses = new HashSet<Class<?>>(
                Arrays.asList(EzspStartScanResponse.class, EzspNetworkFoundHandler.class,
                        EzspEnergyScanResultHandler.class));
        EzspMultiResponseTransaction transaction = new EzspMultiResponseTransaction(request,
                EzspScanCompleteHandler.class, relatedResponses);

        EzspStartScanResponse scanResponse = new EzspStartScanResponse(getPacketData("39 80 1A 00"));
        System.out.println(scanResponse);
        assertTrue("message handled", transaction.handleResponse(scanResponse));
        assertFalse(transaction.isComplete());
        EzspEnergyScanResultHandler scanResult = new EzspEnergyScanResultHandler(getPacketData("39 8C 48 0B C1"));
        System.out.println(scanResult);
        assertTrue("message handled", transaction.handleResponse(scanResult));
        assertFalse(transaction.isComplete());
        scanResult = new EzspEnergyScanResultHandler(getPacketData("3A 8C 48 0E C6"));
        System.out.println(scanResult);
        assertTrue("message handled", transaction.handleResponse(scanResult));
        assertFalse(transaction.isComplete());
        scanResult = new EzspEnergyScanResultHandler(getPacketData("3B 8C 48 0F B4"));
        System.out.println(scanResult);
        assertTrue("message handled", transaction.handleResponse(scanResult));
        assertFalse(transaction.isComplete());
        scanResult = new EzspEnergyScanResultHandler(getPacketData("3B 8C 48 10 AA"));
        assertTrue("message handled", transaction.handleResponse(scanResult));
        assertFalse(transaction.isComplete());
        scanResult = new EzspEnergyScanResultHandler(getPacketData("3C 8C 48 12 B3"));
        assertTrue("message handled", transaction.handleResponse(scanResult));
        assertFalse(transaction.isComplete());
        EzspScanCompleteHandler scanComplete = new EzspScanCompleteHandler(getPacketData("3F 88 1C 02 00"));
        System.out.println(scanComplete);
        assertTrue(transaction.handleResponse(scanComplete));
        assertTrue(transaction.isComplete());

        EzspScanCompleteHandler response = (EzspScanCompleteHandler) transaction.getResponse();
        assertEquals(2, response.getChannel());
        assertEquals(EmberStatus.EMBER_SUCCESS, response.getStatus());

        assertEquals(7, transaction.getResponses().size());
    }
}
