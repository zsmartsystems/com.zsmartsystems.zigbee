/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclAttributeTest {
    @Test
    public void testConstructor() {
        ZclCluster cluster = Mockito.mock(ZclCluster.class);
        ZclAttribute attribute = new ZclAttribute(cluster, 0, "Test Name", ZclDataType.UNSIGNED_8_BIT_INTEGER, false,
                false, false, false);

        assertEquals(cluster, attribute.getCluster());
        assertEquals(0, attribute.getId());
        assertEquals("Test Name", attribute.getName());
        assertEquals(ZclDataType.UNSIGNED_8_BIT_INTEGER, attribute.getDataType());
        assertEquals(false, attribute.isMandatory());
        assertEquals(false, attribute.isWritable());
        assertEquals(false, attribute.isReadable());
        assertEquals(false, attribute.isReportable());
        assertEquals(false, attribute.isManufacturerSpecific());
        System.out.println(attribute.toString());

        attribute = new ZclAttribute(cluster, 0, "Test Name", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, true,
                true, 0x1234);

        assertEquals(true, attribute.isMandatory());
        assertEquals(true, attribute.isWritable());
        assertEquals(true, attribute.isReadable());
        assertEquals(true, attribute.isReportable());
        assertEquals(true, attribute.isManufacturerSpecific());
        assertEquals(Integer.valueOf(0x1234), attribute.getManufacturerCode());
        System.out.println(attribute.toString());
    }

    @Test
    public void getLastReportTime() {
        ZclCluster cluster = Mockito.mock(ZclCluster.class);
        ZclAttribute attribute = new ZclAttribute(cluster, 0, "Test Name", ZclDataType.UNSIGNED_8_BIT_INTEGER, false,
                false, false, false);

        // No value has been set, so should always be false
        assertFalse(attribute.isLastValueCurrent(Long.MAX_VALUE));

        Calendar start = Calendar.getInstance();
        attribute.updateValue(0);
        Calendar stop = Calendar.getInstance();

        assertEquals(0, attribute.getLastValue());
        assertTrue(attribute.getLastReportTime().compareTo(start) >= 0);
        assertTrue(attribute.getLastReportTime().compareTo(stop) <= 0);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertFalse(attribute.isLastValueCurrent(50));
        assertTrue(attribute.isLastValueCurrent(Long.MAX_VALUE));
    }
}
