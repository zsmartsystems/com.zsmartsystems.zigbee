/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.field.AttributeReportingStatusRecord;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class ReadReportingConfigurationResponseTest extends CommandTest {

    @Test
    public void testReceive_SUCCESS() {
        int[] packet = getPacketData("00 00 00 00 10 01 00 2C 01");

        ReadReportingConfigurationResponse response = new ReadReportingConfigurationResponse(null);

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        response.deserialize(fieldDeserializer);

        System.out.println(response);

        List<AttributeReportingStatusRecord> records = response.getRecords();
        AttributeReportingStatusRecord record = records.get(0);
        assertEquals(ZclDataType.BOOLEAN, record.getAttributeDataType());
        assertEquals(0, record.getAttributeIdentifier());
        assertEquals(ZclStatus.SUCCESS, record.getStatus());
        assertEquals(1, record.getMinimumReportingInterval());
        assertEquals(300, record.getMaximumReportingInterval());
    }

    @Test
    public void testReceive_UNSUPPORTED_ATTRIBUTE() {
        int[] packet = getPacketData("86 00 00 00");

        ReadReportingConfigurationResponse response = new ReadReportingConfigurationResponse(null);

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        response.deserialize(fieldDeserializer);

        System.out.println(response);

        List<AttributeReportingStatusRecord> records = response.getRecords();
        AttributeReportingStatusRecord record = records.get(0);
        assertEquals(0, record.getAttributeIdentifier());
        assertEquals(ZclStatus.UNSUPPORTED_ATTRIBUTE, record.getStatus());
    }

}
