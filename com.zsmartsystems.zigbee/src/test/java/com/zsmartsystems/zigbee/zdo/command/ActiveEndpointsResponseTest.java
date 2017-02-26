package com.zsmartsystems.zigbee.zdo.command;

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

        // assertEquals(new IeeeAddress("0000002000F0F081"), addressResponse.getIeeeAddrRemoteDev());
        // assertEquals(0x8001, (int) addressResponse.getClusterId());
        // assertEquals(0, (int) addressResponse.getStatus());
    }
}
