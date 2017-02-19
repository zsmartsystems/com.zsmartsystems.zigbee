package com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspEnergyScanResultHandlerResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkFoundHandlerResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspScanCompleteHandlerResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStartScanRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStartScanResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspChannelMask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspNetworkScanType;

public class EzspScanTransactionTest {
    @Test
    void testScanTransaction() {
        EzspStartScanRequest energyScan = new EzspStartScanRequest();
        energyScan.setChannelMask(EzspChannelMask.EZSP_CHANNEL_MASK_ALL.getKey());
        energyScan.setDuration(1);
        energyScan.setScanType(EzspNetworkScanType.EZSP_ENERGY_SCAN);

        Set<Class<?>> relatedResponses = new HashSet<Class<?>>(Arrays.asList(EzspStartScanResponse.class,
                EzspNetworkFoundHandlerResponse.class, EzspEnergyScanResultHandlerResponse.class));
        EzspMultiResponseTransaction scanTransaction = new EzspMultiResponseTransaction(energyScan,
                EzspScanCompleteHandlerResponse.class, relatedResponses);

    }
}
