/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeCryptoSuite1CertificateTest {

    @Test
    public void test() {
        ByteArray data = new ByteArray(new int[] { 0x02, 0x07, 0x5F, 0xDC, 0x7B, 0x30, 0x19, 0xF8, 0xDB, 0x9D, 0x7D,
                0x55, 0x34, 0x23, 0x4D, 0x38, 0x11, 0xC4, 0xD8, 0xB4, 0x3E, 0xF1, 0x00, 0x0D, 0x6F, 0x00, 0x00, 0x61,
                0x4E, 0xDF, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x01, 0x09, 0x00, 0x01, 0x00, 0x00, 0x11,
                0x22, 0x33, 0x44 });

        ZigBeeCryptoSuite1Certificate certificate = new ZigBeeCryptoSuite1Certificate(data);
        System.out.println(certificate);

        assertEquals(ZigBeeCryptoSuites.ECC_163K1, certificate.getCryptoSuite());
        assertEquals(new IeeeAddress("0102030405060708"), certificate.getIssuer());
        assertEquals(new IeeeAddress("000D6F0000614EDF"), certificate.getSubject());
        assertTrue(Arrays.equals(new int[] { 0x02, 0x07, 0x5F, 0xDC, 0x7B, 0x30, 0x19, 0xF8, 0xDB, 0x9D, 0x7D, 0x55,
                0x34, 0x23, 0x4D, 0x38, 0x11, 0xC4, 0xD8, 0xB4, 0x3E, 0xF1 }, certificate.getPublicKey()));
        assertTrue(Arrays.equals(new int[] { 0x01, 0x09, 0x00, 0x01, 0x00, 0x00, 0x11, 0x22, 0x33, 0x44 },
                certificate.getAttributeData()));
    }
}