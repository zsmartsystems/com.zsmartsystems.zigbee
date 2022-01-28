/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.security;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;

/**
 * A 48 byte key used in ZigBee Smart Energy Crypto Suite 1
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeCryptoSuite1Certificate extends ZigBeeCertificate {
    public static final int CERTIFICATE_LENGTH = 48;

    private final ByteArray keyData;

    /**
     * Construct a key. Key data is assumed to contain the following format -:
     * PublicReconstrKey || Subject || Issuer || ProfileAttributeData
     * <ul>
     * <li>PublicReconstrKey: the 22-byte representation of the public-key reconstruction data BEU as specified in the
     * implicit certificate generation protocol, which is an elliptic-curve point.
     * <li>Subject: the 8-byte identifier of the entity U that is bound to the public-key reconstruction data BEU during
     * execution of the implicit certificate generation protocol (i.e., the extended, 64-bit IEEE 802.15.4 address of
     * the device that purportedly owns the private key corresponding to the public key that can be reconstructed with
     * PublicReconstrKey).
     * <li>Issuer: the 8-byte identifier of the CA that creates the implicit certificate during the execution of the
     * implicit certificate generation protocol (the so-called Certificate Authority).
     * <li>ProfileAttributeData: the 10-byte sequence of octets that can be used by a ZigBee profile for any purpose.
     * The first two bytes of this sequence is reserved as a profile identifier, which must be defined by another ZigBee
     * standard.
     * </ul>
     *
     * @param keyData
     */
    public ZigBeeCryptoSuite1Certificate(ByteArray keyData) throws IllegalArgumentException {
        if (keyData.size() != CERTIFICATE_LENGTH) {
            throw new IllegalArgumentException(
                    "Crypto Suite 1 certificate length must be 48 bytes - passed " + keyData.size() + " bytes");
        }

        cryptoSuite = ZigBeeCryptoSuites.ECC_163K1;
        this.keyData = keyData;
    }

    /**
     * @return the public key
     */
    public int[] getPublicKey() {
        return keyData.getAsIntArray(0, 22);
    }

    @Override
    public IeeeAddress getIssuer() {
        return new IeeeAddress(reverseCopy(keyData.getAsIntArray(30, 38)));
    }

    @Override
    public IeeeAddress getSubject() {
        return new IeeeAddress(reverseCopy(keyData.getAsIntArray(22, 30)));
    }

    public int[] getAttributeData() {
        return keyData.getAsIntArray(38, 48);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(185);

        builder.append("CryptoSuite1Certificate [issuer=");
        builder.append(getIssuer());
        builder.append(", subject=");
        builder.append(getSubject());
        builder.append(", publicKey=");
        appendArray(builder, getPublicKey());
        builder.append(", attributeData=");
        appendArray(builder, getAttributeData());
        builder.append(']');

        return builder.toString();
    }

    @Override
    public ByteArray getByteArray() {
        return keyData;
    }
}
