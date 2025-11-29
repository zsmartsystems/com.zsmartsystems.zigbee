/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mockito.Mockito;

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

    /**
     * Tests that after {@link ZigBeeTransportTransmit#initialize()} has been called,
     * {@link ZigBeeTransportTransmit#getIeeeAddress()} does not return null.
     */
    @Test
    public void getIeeeAddress() {
        transport.setZigBeeTransportReceive(Mockito.mock(ZigBeeTransportReceive.class));
        assertEquals(ZigBeeStatus.SUCCESS, transport.initialize());
        assertNotNull(transport.getIeeeAddress());
    }

    /**
     * Tests that after {@link ZigBeeTransportTransmit#startup()} has been called,
     * {@link ZigBeeTransportTransmit#getNwkAddress()} does not return null.
     */
    @Test
    public void getNwkAddress() {
        transport.setZigBeeTransportReceive(Mockito.mock(ZigBeeTransportReceive.class));
        assertEquals(ZigBeeStatus.SUCCESS, transport.initialize());
        assertEquals(ZigBeeStatus.SUCCESS, transport.startup(false));
        assertNotNull(transport.getNwkAddress());
    }
}
