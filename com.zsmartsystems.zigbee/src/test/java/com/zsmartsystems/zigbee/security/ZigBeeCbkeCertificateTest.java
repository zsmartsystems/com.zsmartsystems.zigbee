/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.security;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeCbkeCertificateTest {

    @Test
    public void hexStringToIntArray() {
        ZigBeeCbkeCertificate certificate = Mockito.mock(ZigBeeCbkeCertificate.class, Mockito.CALLS_REAL_METHODS);

        assertTrue(Arrays.equals(new int[] { 0x12, 0x34, 0x56, 0x78 }, certificate.hexStringToIntArray("12345678")));
    }
}
