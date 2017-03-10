package com.zsmartsystems.zigbee.zdo.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;

/**
 *
 * @author Chris Jackson
 *
 */
public class IeeeAddressResponseTest extends CommandTest {

    @Test
    public void testReceiveShort() {
        // Short response - ie not extended. This is from the Ember response!
        int[] packet = getPacketData("00 00 BF 32 17 00 00 A3 22 00 00 00 00");

        IeeeAddressResponse addressResponse = new IeeeAddressResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        addressResponse.deserialize(fieldDeserializer);

        System.out.println(addressResponse);

        assertEquals(new IeeeAddress("0022A300001732BF"), addressResponse.getIeeeAddrRemoteDev());
        assertEquals(0x8001, (int) addressResponse.getClusterId());
        assertEquals(0, (int) addressResponse.getStatus());
    }

    @Test
    public void testReceive() {
        int[] packet = getPacketData("00 00 EC A1 A5 01 00 8D 15 00 35 38 00 01 2A 2F");

        IeeeAddressResponse addressResponse = new IeeeAddressResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        addressResponse.deserialize(fieldDeserializer);

        System.out.println(addressResponse);

        assertEquals(new IeeeAddress("00158D0001A5A1EC"), addressResponse.getIeeeAddrRemoteDev());
        assertEquals(0x8001, (int) addressResponse.getClusterId());
        assertEquals(0, (int) addressResponse.getStatus());
    }
}
