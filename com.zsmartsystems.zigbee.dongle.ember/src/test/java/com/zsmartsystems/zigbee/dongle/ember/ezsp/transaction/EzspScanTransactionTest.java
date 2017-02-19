package com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspEnergyScanResultHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkFoundHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspScanCompleteHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStartScanRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStartScanResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspChannelMask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspNetworkScanType;

public class EzspScanTransactionTest extends EzspFrameTest {
    @Test
    public void testScanTransaction() {
        EzspStartScanRequest energyScan = new EzspStartScanRequest();
        energyScan.setChannelMask(EzspChannelMask.EZSP_CHANNEL_MASK_ALL.getKey());
        energyScan.setDuration(1);
        energyScan.setSequenceNumber(3);
        energyScan.setScanType(EzspNetworkScanType.EZSP_ENERGY_SCAN);

        Set<Class<?>> relatedResponses = new HashSet<Class<?>>(Arrays.asList(EzspStartScanResponse.class,
                EzspNetworkFoundHandler.class, EzspEnergyScanResultHandler.class));
        EzspMultiResponseTransaction scanTransaction = new EzspMultiResponseTransaction(energyScan,
                EzspScanCompleteHandler.class, relatedResponses);

        EzspFrameResponse response;

        // Start Scan Response
        response = new EzspStartScanResponse(getPacketData("03 80 1A 00"));
        assertFalse(scanTransaction.isMatch(response));

        response = new EzspEnergyScanResultHandler(getPacketData("03 90 48 0B 9D"));
        assertEquals(11, ((EzspEnergyScanResultHandler) response).getChannel());
        assertEquals(-99, ((EzspEnergyScanResultHandler) response).getMaxRssiValue());
        assertFalse(scanTransaction.isMatch(response));

        response = new EzspEnergyScanResultHandler(getPacketData("03 90 48 0C 9D"));
        assertFalse(scanTransaction.isMatch(response));

        response = new EzspEnergyScanResultHandler(getPacketData("03 90 48 0D AB"));
        assertEquals(13, ((EzspEnergyScanResultHandler) response).getChannel());
        assertEquals(-85, ((EzspEnergyScanResultHandler) response).getMaxRssiValue());
        assertFalse(scanTransaction.isMatch(response));

        response = new EzspEnergyScanResultHandler(getPacketData("03 90 48 0E 9D"));
        assertFalse(scanTransaction.isMatch(response));

        response = new EzspScanCompleteHandler(getPacketData("03 90 1C 0B 00"));
        assertEquals(EmberStatus.EMBER_SUCCESS, ((EzspScanCompleteHandler) response).getStatus());
        assertTrue(scanTransaction.isMatch(response));

        assertEquals(6, scanTransaction.getResponses().size());
        assertTrue(scanTransaction.getResponses().get(0) instanceof EzspStartScanResponse);
        assertTrue(scanTransaction.getResponses().get(1) instanceof EzspEnergyScanResultHandler);
        assertTrue(scanTransaction.getResponse() instanceof EzspScanCompleteHandler);
    }
}
