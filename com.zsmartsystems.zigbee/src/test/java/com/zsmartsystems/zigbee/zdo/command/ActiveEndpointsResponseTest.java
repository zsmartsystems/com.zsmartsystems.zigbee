package com.zsmartsystems.zigbee.zdo.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;

/**
 *
 * @author Chris Jackson
 *
 */
public class ActiveEndpointsResponseTest extends CommandTest {

    @Test
    public void testReceive() {
        // Short response - ie not extended
        int[] packet = getPacketData("00 00 00 00 01 01");

        ActiveEndpointsResponse endpointsResponse = new ActiveEndpointsResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        endpointsResponse.deserialize(fieldDeserializer);

        System.out.println(endpointsResponse);

        assertEquals(1, endpointsResponse.getActiveEpList().size());
        assertEquals(0x8005, (int) endpointsResponse.getClusterId());
        assertEquals(0, (int) endpointsResponse.getStatus());
        assertEquals(0, (int) endpointsResponse.getNwkAddrOfInterest());
        assertEquals(1, (int) endpointsResponse.getActiveEpList().get(0));
    }
}
