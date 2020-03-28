/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.general;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.field.AttributeReport;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class ReportAttributesCommandTest extends CommandTest {

    @Test
    public void testSingle() {
        int[] packet = getPacketData("05 FF 21 5C 03 55 00 39 FF BF 38 43");
        // 0x4338BFFF =
        ReportAttributesCommand response = new ReportAttributesCommand(null);

        DefaultDeserializer deserializer = new DefaultDeserializer(packet);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        response.deserialize(fieldDeserializer);

        System.out.println(response);

        List<AttributeReport> reports = response.getReports();
        AttributeReport record = reports.get(0);
        assertEquals(ZclDataType.UNSIGNED_16_BIT_INTEGER, record.getAttributeDataType());
        assertEquals(65285, record.getAttributeIdentifier());
        assertEquals(860, record.getAttributeValue());

        record = reports.get(1);
        assertEquals(ZclDataType.FLOAT_32_BIT, record.getAttributeDataType());
        assertEquals(85, record.getAttributeIdentifier());
        assertTrue(Math.abs((Double) record.getAttributeValue() - 184.74998) < 0.0001);
    }

}
