/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.field;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class ReadAttributeStatusRecordTest {
    @Test
    public void testSuccess() {
        ReadAttributeStatusRecord record = new ReadAttributeStatusRecord();

        ZigBeeSerializer serializer = Mockito.mock(ZigBeeSerializer.class);
        ArgumentCaptor<Object> valueCaptor = ArgumentCaptor.forClass(Object.class);
        ArgumentCaptor<ZclDataType> typeCaptor = ArgumentCaptor.forClass(ZclDataType.class);
        record.setAttributeDataType(ZclDataType.ATTRIBUTEID);
        record.setAttributeIdentifier(1);
        record.setAttributeValue(Integer.valueOf(0));
        record.setStatus(ZclStatus.SUCCESS);
        record.serialize(serializer);
        System.out.println(record);
        Mockito.verify(serializer, Mockito.times(4)).appendZigBeeType(valueCaptor.capture(), typeCaptor.capture());

        assertEquals(4, valueCaptor.getAllValues().size());
        assertEquals(4, typeCaptor.getAllValues().size());
    }

    @Test
    public void testFailure() {
        ReadAttributeStatusRecord record = new ReadAttributeStatusRecord();

        ZigBeeSerializer serializer = Mockito.mock(ZigBeeSerializer.class);
        ArgumentCaptor<Object> valueCaptor = ArgumentCaptor.forClass(Object.class);
        ArgumentCaptor<ZclDataType> typeCaptor = ArgumentCaptor.forClass(ZclDataType.class);
        record.setAttributeIdentifier(1);
        record.setStatus(ZclStatus.FAILURE);
        record.serialize(serializer);
        System.out.println(record);
        Mockito.verify(serializer, Mockito.times(2)).appendZigBeeType(valueCaptor.capture(), typeCaptor.capture());

        assertEquals(2, valueCaptor.getAllValues().size());
        assertEquals(2, typeCaptor.getAllValues().size());
    }
}
