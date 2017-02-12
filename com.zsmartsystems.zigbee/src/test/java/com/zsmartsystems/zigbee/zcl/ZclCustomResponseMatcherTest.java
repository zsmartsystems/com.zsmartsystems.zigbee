package com.zsmartsystems.zigbee.zcl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoinResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclCustomResponseMatcherTest {

    @Test
    public void testMatch() {
        ZclCustomResponseMatcher matcher = new ZclCustomResponseMatcher();

        ZclCommand zclCommand = new OnCommand();
        DefaultResponse zclResponse = new DefaultResponse();

        assertFalse(matcher.isMatch(zclCommand, zclResponse));

        zclCommand.setTransactionId(22);
        zclResponse.setTransactionId(22);
        zclResponse.setStatusCode(0);
        assertFalse(matcher.isMatch(zclCommand, zclResponse));

        zclResponse.setStatusCode(1);
        assertTrue(matcher.isMatch(zclCommand, zclResponse));

        zclResponse.setTransactionId(222);
        assertFalse(matcher.isMatch(zclCommand, zclResponse));

        ZdoCommand zdoResponse = new ManagementPermitJoinResponse();
        assertFalse(matcher.isMatch(zclCommand, zdoResponse));
    }
}
