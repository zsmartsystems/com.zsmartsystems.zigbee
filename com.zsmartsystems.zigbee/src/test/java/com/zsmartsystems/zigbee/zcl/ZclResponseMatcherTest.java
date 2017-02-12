package com.zsmartsystems.zigbee.zcl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclResponseMatcherTest {

    @Test
    public void testMatch() {
        ZclResponseMatcher matcher = new ZclResponseMatcher();

        ZclCommand zclCommand = new OnCommand();
        ZclCommand zclResponse = new DefaultResponse();

        assertFalse(matcher.isMatch(zclCommand, zclResponse));

        zclCommand.setTransactionId(22);
        zclResponse.setTransactionId(22);
        assertTrue(matcher.isMatch(zclCommand, zclResponse));

        zclResponse.setTransactionId(222);
        assertFalse(matcher.isMatch(zclCommand, zclResponse));

        ZdoCommand zdoResponse = new DeviceAnnounce();
        assertFalse(matcher.isMatch(zclCommand, zdoResponse));
    }
}
