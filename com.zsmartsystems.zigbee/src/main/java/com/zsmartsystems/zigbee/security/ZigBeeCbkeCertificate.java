/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.security;

/**
 * Provides a base implementation of the {@link ZigBeeCbkeCertificate}
 *
 * @author Chris Jackson
 *
 */
public abstract class ZigBeeCbkeCertificate {
    protected ZigBeeCryptoSuites cryptoSuite;

    protected int[] certificate;
    protected int[] privateKey;
    protected int[] caPublicKey;

    /**
     * Gets the {@link ZigBeeCryptoSuites} that is applicable for this certificate
     *
     * @return the {@link ZigBeeCryptoSuites}
     */
    public ZigBeeCryptoSuites getCryptoSuite() {
        return cryptoSuite;
    }

    /**
     * Gets the device certificate
     *
     * @return an integer array with the devices certificate
     */
    public int[] getCertificate() {
        return certificate;
    }

    /**
     * Gets the devices private key
     *
     * @return an integer array with the devices private
     */
    public int[] getPrivateKey() {
        return privateKey;
    }

    /**
     * Gets the certificate authorities public key used to sign the certificate
     *
     * @return an integer array with the certificate authorities public key
     */
    public int[] getCaPublicKey() {
        return caPublicKey;
    }

    protected int[] hexStringToIntArray(String hexString) {
        if (hexString.length() % 2 != 0) {
            throw new IllegalArgumentException(
                    "Certificate string '" + hexString + "' must be even number of bytes long.");
        }

        int[] output = new int[hexString.length() / 2];
        char enc[] = hexString.toCharArray();
        for (int i = 0; i < enc.length - 1; i += 2) {
            StringBuilder curr = new StringBuilder(2);
            curr.append(enc[i]).append(enc[i + 1]);
            output[i / 2] = Integer.parseInt(curr.toString(), 16);
        }

        return output;
    }

}
