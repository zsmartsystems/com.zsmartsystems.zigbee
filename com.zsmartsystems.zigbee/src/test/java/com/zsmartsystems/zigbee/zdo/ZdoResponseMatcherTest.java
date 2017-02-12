package com.zsmartsystems.zigbee.zdo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffCommand;
import com.zsmartsystems.zigbee.zdo.command.BindRequest;
import com.zsmartsystems.zigbee.zdo.command.BindResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZdoResponseMatcherTest {

    @Test
    public void testMatch() {
        ZdoResponseMatcher matcher = new ZdoResponseMatcher();

        ZdoRequest zdoCommand = new BindRequest();
        BindResponse zdoResponse = new BindResponse();

        zdoCommand.setDestinationAddress(1234);
        zdoResponse.setSourceAddress(1234);
        assertTrue(matcher.isMatch(zdoCommand, zdoResponse));

        zdoResponse.setSourceAddress(5678);
        assertFalse(matcher.isMatch(zdoCommand, zdoResponse));

        ZclCommand zclResponse = new OffCommand();
        assertFalse(matcher.isMatch(zdoCommand, zclResponse));
    }
}
