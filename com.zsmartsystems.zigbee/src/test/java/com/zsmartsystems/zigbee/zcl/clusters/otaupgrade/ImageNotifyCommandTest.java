/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.otaupgrade;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;

/**
 *
 * @author Chris Jackson
 *
 */
public class ImageNotifyCommandTest extends CommandTest {

    @Test
    public void testSend() {
        ImageNotifyCommand command = getTestImageNotifyCommandWithPayloadType(0);
        System.out.println(command);

        ZigBeeSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);
        command.serialize(fieldSerializer);
        assertTrue(Arrays.equals(getPacketData("00 48"), serializer.getPayload()));

        serializer = new DefaultSerializer();
        fieldSerializer = new ZclFieldSerializer(serializer);
        command = getTestImageNotifyCommandWithPayloadType(1);
        command.serialize(fieldSerializer);
        assertTrue(Arrays.equals(getPacketData("01 48 0C 11"), serializer.getPayload()));

        serializer = new DefaultSerializer();
        fieldSerializer = new ZclFieldSerializer(serializer);
        command.serialize(fieldSerializer);
        assertTrue(Arrays.equals(getPacketData("01 48 0C 11"), serializer.getPayload()));

        serializer = new DefaultSerializer();
        fieldSerializer = new ZclFieldSerializer(serializer);
        command = getTestImageNotifyCommandWithPayloadType(2);
        command.serialize(fieldSerializer);
        assertTrue(Arrays.equals(getPacketData("02 48 0C 11 06 00"), serializer.getPayload()));

        serializer = new DefaultSerializer();
        fieldSerializer = new ZclFieldSerializer(serializer);
        command = getTestImageNotifyCommandWithPayloadType(3);
        command.serialize(fieldSerializer);
        assertTrue(Arrays.equals(getPacketData("03 48 0C 11 06 00 07 03 02 01"), serializer.getPayload()));
    }

    private ImageNotifyCommand getTestImageNotifyCommandWithPayloadType(int payloadType) {
        ImageNotifyCommand command = new ImageNotifyCommand(payloadType, 72, 4364, 6, 16909063);
        command.setSourceAddress(new ZigBeeEndpointAddress(0, 1));
        command.setDestinationAddress(new ZigBeeEndpointAddress(57337, 3));
        return command;
    }

}
