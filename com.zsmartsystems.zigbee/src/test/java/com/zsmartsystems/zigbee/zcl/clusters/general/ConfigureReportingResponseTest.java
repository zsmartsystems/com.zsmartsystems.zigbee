/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.general;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclHeader;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.field.AttributeStatusRecord;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConfigureReportingResponseTest extends CommandTest {
    @Test
    public void test() {
        int[] packet = getPacketData("18 1B 07 00 00 00 00");

        ConfigureReportingResponse response = new ConfigureReportingResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        ZclHeader zclHeader = new ZclHeader(fieldDeserializer);
        System.out.println(zclHeader);
        response.deserialize(fieldDeserializer);
        System.out.println(response);

        assertNull(response.getStatus());

        assertEquals(1, response.getRecords().size());
        AttributeStatusRecord record = response.getRecords().get(0);

        assertEquals(0, record.getAttributeIdentifier());
        assertEquals(ZclStatus.SUCCESS, record.getStatus());
    }

    @Test
    public void testStatusOnly() {
        int[] packet = getPacketData("18 11 07 00");

        ConfigureReportingResponse response = new ConfigureReportingResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        ZclHeader zclHeader = new ZclHeader(fieldDeserializer);
        System.out.println(zclHeader);

        response.deserialize(fieldDeserializer);
        System.out.println(response);

        assertEquals(ZclStatus.SUCCESS, response.getStatus());
        assertNull(response.getRecords());
    }

    @Test
    public void testErrorInvalidDataType() {
        int[] packet = getPacketData("08 6C 07 8D 00 00 00");

        ConfigureReportingResponse response = new ConfigureReportingResponse();

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        ZclHeader zclHeader = new ZclHeader(fieldDeserializer);
        System.out.println(zclHeader);

        response.deserialize(fieldDeserializer);
        System.out.println(response);

        assertNull(response.getStatus());

        assertEquals(1, response.getRecords().size());
        AttributeStatusRecord record = response.getRecords().get(0);

        assertEquals(0, record.getAttributeIdentifier());
        assertEquals(ZclStatus.INVALID_DATA_TYPE, record.getStatus());
    }
}