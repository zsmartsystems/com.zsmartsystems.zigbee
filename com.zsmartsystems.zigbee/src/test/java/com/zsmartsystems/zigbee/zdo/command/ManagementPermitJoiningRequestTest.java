package com.zsmartsystems.zigbee.zdo.command;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;

/**
 *
 * @author Chris Jackson
 *
 */
public class ManagementPermitJoiningRequestTest extends CommandTest {

    @Test
    public void testReceive() {
        // Short response - ie not extended
        int[] packet = getPacketData("00 FF 01");

        ManagementPermitJoiningRequest request = new ManagementPermitJoiningRequest();
        request.setDestinationAddress(new ZigBeeDeviceAddress(0));
        request.setTcSignificance(true);
        request.setPermitDuration(255);

        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);

        request.serialize(fieldSerializer);
        assertTrue(Arrays.equals(packet, serializer.getPayload()));
    }
}
