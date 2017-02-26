package com.zsmartsystems.zigbee.zdo.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor;

/**
 *
 * @author Chris Jackson
 *
 */
public class NodeDescriptorResponseTest extends CommandTest {

    @Test
    public void testReceive() {
        int[] packet = getPacketData("00 00 00 00 40 8F CD AB 52 80 00 41 2A 80 00 00");

        NodeDescriptorResponse descriptorResponse = new NodeDescriptorResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        descriptorResponse.deserialize(fieldDeserializer);

        System.out.println(descriptorResponse);

        NodeDescriptor nodeDescriptor = descriptorResponse.getNodeDescriptor();

        assertEquals(82, nodeDescriptor.getBufferSize());
        assertEquals(128, nodeDescriptor.getTransferSize());
        assertEquals(43981, nodeDescriptor.getManufacturerCode());
    }
}
