/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.general;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.field.AttributeInformation;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class DiscoverAttributesResponseTest extends CommandTest {
    @Test
    public void testReceive() {
        int[] packet = getPacketData("00 02 00 21 03 00 21 04 00 21 07 00 21 08 00 30");

        DiscoverAttributesResponse response = new DiscoverAttributesResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        response.deserialize(fieldDeserializer);

        System.out.println(response);

        List<AttributeInformation> records = response.getAttributeInformation();
        assertEquals(5, records.size());

        AttributeInformation record = records.get(0);
        assertEquals(ZclDataType.UNSIGNED_16_BIT_INTEGER, record.getDataType());
        assertEquals(2, record.getIdentifier());
    }

}
