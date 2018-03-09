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
import com.zsmartsystems.zigbee.zcl.field.AttributeReportingConfigurationRecord;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConfigureReportingCommandTest extends CommandTest {

    @Test
    public void testSingle() {
        int[] packet = getPacketData("00 00 00 10 01 00 58 02");

        AttributeReportingConfigurationRecord record = new AttributeReportingConfigurationRecord();
        record.setAttributeIdentifier(0);
        record.setAttributeDataType(ZclDataType.BOOLEAN);
        record.setDirection(0);
        record.setTimeoutPeriod(0);
        record.setMinimumReportingInterval(1);
        record.setMaximumReportingInterval(600);
        ConfigureReportingCommand command = new ConfigureReportingCommand();
        command.setClusterId(6);
        command.setDestinationAddress(new ZigBeeEndpointAddress(31084, 18));
        command.setRecords(Arrays.asList(record));
        command.setTransactionId(23);

        System.out.println(command);

        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);
        command.serialize(fieldSerializer);

        assertTrue(Arrays.equals(packet, serializer.getPayload()));
    }

}
