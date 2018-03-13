/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeReadParameterResponseTest {
    @Test
    public void readMacAddress() {
        ConBeeReadParameterResponse readParameter = new ConBeeReadParameterResponse(new int[] { 0x0A, 0x02, 0x00, 0x10,
                0x00, 0x09, 0x00, 0x01, 0x8C, 0x0A, 0x01, 0xFF, 0xFF, 0x2E, 0x21, 0x00, 0xF6, 0xFC });
        System.out.println(readParameter);
        assertEquals(2, readParameter.getSequence());
        assertEquals(ConBeeStatus.SUCCESS, readParameter.getStatus());
        assertEquals(ConBeeNetworkParameter.MAC_ADDRESS, readParameter.getParameter());
        assertEquals(new IeeeAddress("00212EFFFF010A8C"), readParameter.getValue());
    }

    @Test
    public void readPanId() {
        ConBeeReadParameterResponse readParameter = new ConBeeReadParameterResponse(
                new int[] { 0x0A, 0x04, 0x00, 0x0A, 0x00, 0x03, 0x00, 0x05, 0xD1, 0x06, 0x09, 0xFF });
        System.out.println(readParameter);
        assertEquals(4, readParameter.getSequence());
        assertEquals(ConBeeStatus.SUCCESS, readParameter.getStatus());
        assertEquals(ConBeeNetworkParameter.NWK_PANID, readParameter.getParameter());
        assertEquals(new Integer(0x06D1), readParameter.getValue());
        assertEquals(0x06D1, readParameter.getValue());
    }

    @Test
    public void readApsExtendedPanId() {
        ConBeeReadParameterResponse readParameter = new ConBeeReadParameterResponse(new int[] { 0x0A, 0x05, 0x00, 0x10,
                0x00, 0x09, 0x00, 0x0B, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xCD, 0xFF });
        System.out.println(readParameter);
        assertEquals(5, readParameter.getSequence());
        assertEquals(ConBeeStatus.SUCCESS, readParameter.getStatus());
        assertEquals(ConBeeNetworkParameter.APS_EXTENDED_PANID, readParameter.getParameter());
        assertEquals(new ExtendedPanId("0000000000000000"), readParameter.getValue());
    }

    @Test
    public void readNwkExtendedPanId() {
        ConBeeReadParameterResponse readParameter = new ConBeeReadParameterResponse(new int[] { 0x0A, 0x07, 0x00, 0x10,
                0x00, 0x09, 0x00, 0x08, 0x8C, 0x0A, 0x01, 0xFF, 0xFF, 0x2E, 0x21, 0x00, 0xEA, 0xFC });
        System.out.println(readParameter);
        assertEquals(7, readParameter.getSequence());
        assertEquals(ConBeeStatus.SUCCESS, readParameter.getStatus());
        assertEquals(ConBeeNetworkParameter.NWK_EXTENDED_PANID, readParameter.getParameter());
        assertEquals(new ExtendedPanId("00212EFFFF010A8C"), readParameter.getValue());
    }

    @Test
    public void readNwkAddress() {
        ConBeeReadParameterResponse readParameter = new ConBeeReadParameterResponse(
                new int[] { 0x0A, 0x06, 0x00, 0x0A, 0x00, 0x03, 0x00, 0x07, 0x00, 0x00, 0xDC, 0xFF });
        System.out.println(readParameter);
        assertEquals(6, readParameter.getSequence());
        assertEquals(ConBeeStatus.SUCCESS, readParameter.getStatus());
        assertEquals(ConBeeNetworkParameter.NWK_ADDRESS, readParameter.getParameter());
        assertEquals(Integer.valueOf(0), readParameter.getValue());
    }

    @Test
    public void readCurrentChannel() {
        ConBeeReadParameterResponse readParameter = new ConBeeReadParameterResponse(
                new int[] { 0x0A, 0x08, 0x00, 0x09, 0x00, 0x02, 0x00, 0x1C, 0x19, 0xAE, 0xFF });
        System.out.println(readParameter);
        assertEquals(8, readParameter.getSequence());
        assertEquals(ConBeeStatus.SUCCESS, readParameter.getStatus());
        assertEquals(ConBeeNetworkParameter.CURRENT_CHANNEL, readParameter.getParameter());
        assertEquals(new Integer(25), readParameter.getValue());
    }
}
