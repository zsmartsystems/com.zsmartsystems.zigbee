/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeDeviceStateChangedTest {
    @Test
    public void doRequest() {
        ConBeeDeviceStateChanged response = new ConBeeDeviceStateChanged(
                new int[] { 0x0E, 0x11, 0x00, 0x07, 0x00, 0xA6, 0x00, 0x34, 0xFF });
        System.out.print(response);

        assertEquals(17, response.getSequence());
        assertEquals(ConBeeStatus.SUCCESS, response.getStatus());
        assertEquals(ConBeeNetworkState.NET_CONNECTED, response.getDeviceState().getNetworkState());
        assertTrue(response.getDeviceState().isDataConfirm());
        assertFalse(response.getDeviceState().isDataIndication());
        assertTrue(response.getDeviceState().isDataRequest());
        assertFalse(response.getDeviceState().isConfigChanged());
    }
}
