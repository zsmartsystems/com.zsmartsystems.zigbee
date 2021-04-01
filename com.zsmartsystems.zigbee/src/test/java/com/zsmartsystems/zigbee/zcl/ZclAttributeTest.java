/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.database.ZclAttributeDao;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclAttributeTest {
    @Test
    public void testConstructor() {
        ZclAttribute attribute = new ZclAttribute(new ZclOnOffCluster(null), 0, "Test Name",
                ZclDataType.UNSIGNED_8_BIT_INTEGER, false, false, false, false);

        assertEquals(ZclClusterType.ON_OFF, attribute.getCluster());
        assertEquals(0, attribute.getId());
        assertEquals("Test Name", attribute.getName());
        assertEquals(ZclDataType.UNSIGNED_8_BIT_INTEGER, attribute.getDataType());
        assertEquals(false, attribute.isMandatory());
        assertEquals(false, attribute.isWritable());
        assertEquals(false, attribute.isReadable());
        assertEquals(false, attribute.isReportable());
        assertEquals(false, attribute.isManufacturerSpecific());
        System.out.println(attribute.toString());

        attribute = new ZclAttribute(new ZclOnOffCluster(null), 0, "Test Name", ZclDataType.UNSIGNED_8_BIT_INTEGER,
                true, true, true, true, 0x1234);

        assertEquals(true, attribute.isMandatory());
        assertEquals(true, attribute.isWritable());
        assertEquals(true, attribute.isReadable());
        assertEquals(true, attribute.isReportable());
        assertEquals(true, attribute.isManufacturerSpecific());
        assertEquals(Integer.valueOf(0x1234), attribute.getManufacturerCode());
        System.out.println(attribute.toString());
    }

    @Test
    public void getLastReportTime() throws InterruptedException {
        ZclAttribute attribute = new ZclAttribute(new ZclOnOffCluster(null), 0, "Test Name",
                ZclDataType.UNSIGNED_8_BIT_INTEGER, false, false, false, false);

        // No value has been set, so should always be false
        assertFalse(attribute.isLastValueCurrent(Long.MAX_VALUE));

        Calendar start = Calendar.getInstance();
        attribute.updateValue(0);
        Calendar stop = Calendar.getInstance();

        assertEquals(0, attribute.getLastValue());
        assertTrue(attribute.getLastReportTime().compareTo(start) >= 0);
        assertTrue(attribute.getLastReportTime().compareTo(stop) <= 0);

        Thread.sleep(100);
        assertFalse(attribute.isLastValueCurrent(50));
        assertTrue(attribute.isLastValueCurrent(Long.MAX_VALUE));
    }

    @Test
    public void writeValue() {
        ZclCluster cluster = Mockito.mock(ZclCluster.class);
        ZclAttribute attribute = new ZclAttribute(cluster, 123, "Test Name", ZclDataType.UNSIGNED_8_BIT_INTEGER, false,
                false, false, false);

        Integer value = Integer.valueOf(888);
        attribute.writeValue(value);
        Mockito.verify(cluster, Mockito.times(1)).writeAttribute(123, ZclDataType.UNSIGNED_8_BIT_INTEGER, value);
    }

    @Test
    public void readValue() throws Exception {
        ZclCluster cluster = Mockito.mock(ZclCluster.class);
        ZclAttribute attribute = new ZclAttribute(cluster, 123, "Test Name", ZclDataType.UNSIGNED_8_BIT_INTEGER, false,
                false, false, false);

        attribute.readValue(Long.MAX_VALUE);
        Mockito.verify(cluster, Mockito.times(1)).readAttributeValue(123);

        TestUtilities.setField(ZclAttribute.class, attribute, "lastReportTime", Calendar.getInstance());
        TestUtilities.setField(ZclAttribute.class, attribute, "lastValue", Integer.valueOf(8888));
        assertEquals(Integer.valueOf(8888), attribute.readValue(Long.MAX_VALUE));
        Mockito.verify(cluster, Mockito.times(1)).readAttributeValue(123);
    }

    @Test
    public void reportValue() {
        ZclCluster cluster = Mockito.mock(ZclCluster.class);
        ZclAttribute attribute = new ZclAttribute(cluster, 123, "Test Name", ZclDataType.UNSIGNED_8_BIT_INTEGER, false,
                false, false, false);

        Integer value = Integer.valueOf(888);
        attribute.reportValue(value);
        Mockito.verify(cluster, Mockito.times(1)).reportAttribute(123, ZclDataType.UNSIGNED_8_BIT_INTEGER, value);
    }

    @Test
    public void setReporting() {
        ZclCluster cluster = Mockito.mock(ZclCluster.class);
        ZclAttribute attribute = new ZclAttribute(cluster, 123, "Test Name", ZclDataType.UNSIGNED_8_BIT_INTEGER, false,
                false, false, false);

        attribute.setReporting(1, 2);
        Mockito.verify(cluster, Mockito.times(1)).setReporting(123, 1, 2);

        attribute.setReporting(1, 2, 3);
        Mockito.verify(cluster, Mockito.times(1)).setReporting(123, 1, 2, 3);
    }

    @Test
    public void dao() {
        ZclCluster cluster = Mockito.mock(ZclCluster.class);
        ZclAttribute attribute = new ZclAttribute(cluster, 123, "Test Name", ZclDataType.UNSIGNED_8_BIT_INTEGER, true,
                false, true, false, 0x1234);

        attribute.updateValue(Integer.valueOf(12345));

        ZclAttributeDao dao = attribute.getDao();
        assertEquals(123, dao.getId());
        assertEquals("Test Name", dao.getName());
        assertEquals(ZclDataType.UNSIGNED_8_BIT_INTEGER, dao.getDataType());
        assertTrue(dao.isMandatory());
        assertFalse(dao.isImplemented());
        assertTrue(dao.isWritable());
        assertFalse(dao.isReadable());
        assertFalse(dao.isReportable());
        assertEquals(Integer.valueOf(0x1234), dao.getManufacturerCode());
        assertEquals(12345, dao.getLastValue());

        ZclAttribute daoAttribute = new ZclAttribute();
        daoAttribute.setDao(cluster, dao);

        assertEquals(123, daoAttribute.getId());
        assertEquals("Test Name", daoAttribute.getName());
        assertEquals(ZclDataType.UNSIGNED_8_BIT_INTEGER, daoAttribute.getDataType());
        assertTrue(daoAttribute.isMandatory());
        assertFalse(daoAttribute.isImplemented());
        assertTrue(daoAttribute.isWritable());
        assertFalse(daoAttribute.isReadable());
        assertFalse(daoAttribute.isReportable());
        assertEquals(Integer.valueOf(0x1234), daoAttribute.getManufacturerCode());
        assertEquals(12345, daoAttribute.getLastValue());
    }
}
