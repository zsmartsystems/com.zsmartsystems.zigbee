/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.onoff;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclHeader;

/**
 *
 * @author Chris Jackson
 *
 */
public class OffWithEffectCommandTest extends CommandTest {
    @Test
    public void deserialize() {
        int[] packet = getPacketData("01 12 40 00 00");
        OffWithEffectCommand command = new OffWithEffectCommand();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        ZclHeader zclHeader = new ZclHeader(fieldDeserializer);
        System.out.println(zclHeader);
        command.deserialize(fieldDeserializer);
        System.out.println(command);

        assertEquals(Integer.valueOf(0), command.getEffectIdentifier());
        assertEquals(Integer.valueOf(0), command.getEffectVariant());
    }
}
