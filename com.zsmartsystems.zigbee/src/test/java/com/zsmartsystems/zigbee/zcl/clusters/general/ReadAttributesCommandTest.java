/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.general;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;

/**
 *
 * @author Chris Jackson
 *
 */
public class ReadAttributesCommandTest extends CommandTest {

    @Test
    public void testSingle() {
        int[] packet = getPacketData("04 00");

        ReadAttributesCommand command = new ReadAttributesCommand();
        command.setClusterId(0);
        command.setDestinationAddress(new ZigBeeEndpointAddress(57337, 3));
        command.setIdentifiers(Arrays.asList(4));
        command.setTransactionId(1);
        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);

        command.serialize(fieldSerializer);
        assertTrue(Arrays.equals(packet, serializer.getPayload()));
    }

}
