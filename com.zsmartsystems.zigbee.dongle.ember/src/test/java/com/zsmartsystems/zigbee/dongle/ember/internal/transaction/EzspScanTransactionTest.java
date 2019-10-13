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
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
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
public class EzspScanTransactionTest extends EzspFrameTest {
    @Test
    public void testScanTransaction() {
        EzspStartScanRequest energyScan = new EzspStartScanRequest();
        energyScan.setChannelMask(ZigBeeChannelMask.CHANNEL_MASK_2GHZ);
        energyScan.setDuration(1);
        energyScan.setSequenceNumber(3);
        energyScan.setScanType(EzspNetworkScanType.EZSP_ENERGY_SCAN);

        Set<Class<?>> relatedResponses = new HashSet<Class<?>>(
                Arrays.asList(EzspStartScanResponse.class, EzspNetworkFoundHandler.class,
                        EzspEnergyScanResultHandler.class));
        EzspMultiResponseTransaction scanTransaction = new EzspMultiResponseTransaction(energyScan,
                EzspScanCompleteHandler.class, relatedResponses);

        EzspFrameResponse response;

        // Start Scan Response
        response = new EzspStartScanResponse(getPacketData("03 80 1A 00"));
        assertTrue(scanTransaction.handleResponse(response));
        assertFalse(scanTransaction.isComplete());

        response = new EzspEnergyScanResultHandler(getPacketData("03 90 48 0B 9D"));
        assertEquals(11, ((EzspEnergyScanResultHandler) response).getChannel());
        assertEquals(-99, ((EzspEnergyScanResultHandler) response).getMaxRssiValue());
        assertTrue(scanTransaction.handleResponse(response));
        assertFalse(scanTransaction.isComplete());

        response = new EzspEnergyScanResultHandler(getPacketData("03 90 48 0C 9D"));
        assertTrue(scanTransaction.handleResponse(response));
        assertFalse(scanTransaction.isComplete());

        response = new EzspEnergyScanResultHandler(getPacketData("03 90 48 0D AB"));
        assertEquals(13, ((EzspEnergyScanResultHandler) response).getChannel());
        assertEquals(-85, ((EzspEnergyScanResultHandler) response).getMaxRssiValue());
        assertTrue(scanTransaction.handleResponse(response));
        assertFalse(scanTransaction.isComplete());

        response = new EzspEnergyScanResultHandler(getPacketData("03 90 48 0E 9D"));
        assertTrue(scanTransaction.handleResponse(response));
        assertFalse(scanTransaction.isComplete());

        response = new EzspScanCompleteHandler(getPacketData("03 90 1C 0B 00"));
        assertEquals(EmberStatus.EMBER_SUCCESS, ((EzspScanCompleteHandler) response).getStatus());
        assertTrue(scanTransaction.handleResponse(response));
        assertTrue(scanTransaction.isComplete());

        assertEquals(6, scanTransaction.getResponses().size());
        assertTrue(scanTransaction.getResponses().get(0) instanceof EzspStartScanResponse);
        assertTrue(scanTransaction.getResponses().get(1) instanceof EzspEnergyScanResultHandler);
        assertTrue(scanTransaction.getResponse() instanceof EzspScanCompleteHandler);
    }
}
