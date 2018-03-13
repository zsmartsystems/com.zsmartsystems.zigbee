/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor.CurrentPowerModeType;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor.PowerLevelType;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor.PowerSourceType;

/**
 *
 * @author Chris Jackson
 *
 */
public class PowerDescriptorResponseTest extends CommandTest {

    @Test
    public void testReceive() {
        int[] packet = getPacketData("00 00 00 00 10 C1");

        PowerDescriptorResponse descriptorResponse = new PowerDescriptorResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        descriptorResponse.deserialize(fieldDeserializer);

        System.out.println(descriptorResponse);

        PowerDescriptor powerDescriptor = descriptorResponse.getPowerDescriptor();
        assertEquals(ZdoStatus.SUCCESS, descriptorResponse.getStatus());

        assertEquals(PowerLevelType.FULL, powerDescriptor.getPowerLevel());
        assertEquals(CurrentPowerModeType.RECEIVER_ON_IDLE, powerDescriptor.getCurrentPowerMode());
        assertEquals(PowerSourceType.MAINS, powerDescriptor.getCurrentPowerSource());
    }

    @Test
    public void testReceiveNotSupported() {
        int[] packet = getPacketData("84 84");

        PowerDescriptorResponse descriptorResponse = new PowerDescriptorResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        descriptorResponse.deserialize(fieldDeserializer);

        System.out.println(descriptorResponse);
        assertEquals(ZdoStatus.NOT_SUPPORTED, descriptorResponse.getStatus());

        PowerDescriptor powerDescriptor = descriptorResponse.getPowerDescriptor();
        assertEquals(null, powerDescriptor);
    }
}
