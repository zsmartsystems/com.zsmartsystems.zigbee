/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.security;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests to check creation of CBKE certificates from a Certicom string (cut and paste from website).
 * 
 * @author Chris Jackson
 *
 */
public class CerticomCbkeCertificateTest {
    @Test
    public void testCerticomCertificateSuit1() {
        String certificateString = "CA Pub Key:\n" + "0200fde8a7f3d1084224962a4e7c54e69ac3f04da6b8\n"
                + "Device Implicit Cert:\n"
                + "02020882b1d7df05538f37cdd09a4f6b15368998642b002208000000000154455354534543410109108301234567890a\n"
                + "Private Key Reconstruction Data:\n" + "0392ba4afc2453a49eebe9bca8de73210f02479a4a\n"
                + "Device Public Key:\n" + "0207d1ca014786b5e552cfd5f04339a2e856f00eaaef\n" + "";
        ZigBeeCbkeCertificate certificate = new CerticomCbkeCertificate(certificateString);

        assertEquals(ZigBeeCryptoSuites.ECC_163K1, certificate.getCryptoSuite());
    }

    @Test
    public void testCerticomCertificateSuit2() {
        String certificateString = "CA Pub Key:\n"
                + "0207a445022d9f39f49bdc38380026a27a9e0a1799313ab28c5c1a1c6b605154db1dff6752\n"
                + "Device Implicit Cert:\n"
                + "000b3ad5a312ab75d60d081112131415161718005b7ed230ffffffff0022080000000001080205bb3d1d1576197ff9ab6563915019a130437adb0ee7915432dc4a09c97dc595c5856800\n"
                + "Private Key Reconstruction Data:\n"
                + "00997097b87684f62f555173c47be89935fbf6440ae292492b54c5829a0d541f4d26e254\n" + "Device Public Key:\n"
                + "02012f082ee7db1bd3f89f16eabbf5d6221a5dcd077fc0c9d37b329eee8e2cdaee03439de0" + "";
        ZigBeeCbkeCertificate certificate = new CerticomCbkeCertificate(certificateString);

        assertEquals(ZigBeeCryptoSuites.ECC_283K1, certificate.getCryptoSuite());
    }
}
