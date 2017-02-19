package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberZdoConfigurationFlags;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspSetConfigurationValueRequestTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        EzspSetConfigurationValueRequest request = new EzspSetConfigurationValueRequest();
        request.setSequenceNumber(2);
        request.setConfigId(EzspConfigId.EZSP_CONFIG_APPLICATION_ZDO_FLAGS.getKey());
        request.setValue(EmberZdoConfigurationFlags.EMBER_APP_RECEIVES_SUPPORTED_ZDO_REQUESTS.getKey());

        assertTrue(Arrays.equals(getPacketData("02 00 53 2A 01 00"), request.serialize()));
    }
}
