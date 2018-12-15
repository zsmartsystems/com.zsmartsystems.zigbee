/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeStatus;

/**
 * Abstract set of tests that should be extended for each implementation of {@link ZigBeeTransportTransmit} to ensure
 * that certain system expectations are respected.
 *
 * @author Chris Jackson
 *
 */
public abstract class ZigBeeTransportTransmitAbstractTest {
    protected ZigBeeTransportTransmit transport;

    protected void setField(Class clazz, Object object, String fieldName, Object newValue) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(object, newValue);
    }

    /**
     * Tests that after {@link ZigBeeTransportTransmit#initialize()} has been called,
     * {@link ZigBeeTransportTransmit#getIeeeAddress()} does not return null.
     */
    @Test
    public void getIeeeAddress() {
        assertEquals(ZigBeeStatus.SUCCESS, transport.initialize());
        assertNotNull(transport.getIeeeAddress());
    }

    /**
     * Tests that after {@link ZigBeeTransportTransmit#startup()} has been called,
     * {@link ZigBeeTransportTransmit#getNwkAddress()} does not return null.
     */
    @Test
    public void getNwkAddress() {
        assertEquals(ZigBeeStatus.SUCCESS, transport.initialize());
        assertEquals(ZigBeeStatus.SUCCESS, transport.startup(false));
        assertNotNull(transport.getNwkAddress());
    }
}
