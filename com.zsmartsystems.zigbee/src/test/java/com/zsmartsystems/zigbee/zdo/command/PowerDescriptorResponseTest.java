package com.zsmartsystems.zigbee.zdo.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor.CurrentPowerModeType;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor.PowerLevelType;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor.PowerSourceType;

/**
 *
 * @author Chris Jackson
 *
 */
public class PowerDescriptorResponseTest extends CommandTest {

    @Test
    public void testReceive() {
        // int[] packet = getPacketData("00 00 00 00 10 C1");
        int[] packet = getPacketData("00 00 00 10 C1");

        PowerDescriptorResponse descriptorResponse = new PowerDescriptorResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        descriptorResponse.deserialize(fieldDeserializer);

        System.out.println(descriptorResponse);

        PowerDescriptor powerDescriptor = descriptorResponse.getPowerDescriptor();

        assertEquals(PowerLevelType.FULL, powerDescriptor.getPowerLevel());
        assertEquals(CurrentPowerModeType.RECEIVER_ON_IDLE, powerDescriptor.getCurrentPowerMode());
        assertEquals(PowerSourceType.MAINS, powerDescriptor.getCurrentPowerSource());
    }
}
