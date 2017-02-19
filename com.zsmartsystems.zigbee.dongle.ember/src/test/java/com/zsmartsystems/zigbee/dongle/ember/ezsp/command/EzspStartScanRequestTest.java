package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspChannelMask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspNetworkScanType;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspStartScanRequestTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        EzspStartScanRequest request = new EzspStartScanRequest();
        request.setSequenceNumber(3);
        request.setScanType(EzspNetworkScanType.EZSP_ENERGY_SCAN);
        request.setChannelMask(EzspChannelMask.EZSP_CHANNEL_MASK_ALL.getKey());
        request.setDuration(1);

        assertTrue(Arrays.equals(getPacketData("03 00 1A 00 00 F8 FF 07 01"), request.serialize()));
    }
}
