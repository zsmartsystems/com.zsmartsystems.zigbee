/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.security;

import java.util.Arrays;
import java.util.List;

/**
 * Provides an implementation of {@link ZigBeeCbkeCertificate} that parses the Certicom certificate format
 *
 * @author Chris Jackson
 *
 */
public class CerticomCbkeCertificate extends ZigBeeCbkeCertificate {
    private final static String DELIM_CA_PUBLIC_KEY = "CAPUBKEY:";
    private final static String DELIM_DEV_IMPLICIT_CERT = "DEVICEIMPLICITCERT:";
    private final static String DELIM_DEV_PRIVATE_KEY = "PRIVATEKEYRECONSTRUCTIONDATA:";
    private final static String DELIM_DEV_PUBLIC_KEY = "DEVICEPUBLICKEY:";

    private final static int LENGTH_CA_PUBLIC_KEY_1 = 22;
    private final static int LENGTH_DEV_IMPLICIT_CERT_1 = 48;
    private final static int LENGTH_DEV_PRIVATE_KEY_1 = 21;
    private final static int LENGTH_DEV_PUBLIC_KEY_1 = 22;

    private final static int LENGTH_CA_PUBLIC_KEY_2 = 37;
    private final static int LENGTH_DEV_IMPLICIT_CERT_2 = 74;
    private final static int LENGTH_DEV_PRIVATE_KEY_2 = 36;
    private final static int LENGTH_DEV_PUBLIC_KEY_2 = 37;

    private final List<String> delimiterList = Arrays.asList(
            new String[] { DELIM_CA_PUBLIC_KEY, DELIM_DEV_IMPLICIT_CERT, DELIM_DEV_PRIVATE_KEY, DELIM_DEV_PUBLIC_KEY });

    /**
     * Creates a {@link ZigBeeCbkeCertificate} from a Certicom compatible format.
     * <p>
     * This constructor tries to be tolerant to different formatting and will ignore line feeds and other white space.
     * It will automatically set the certificate type depending on the length of the keys. If these are not consistent
     * amongst all keys, and the required length for the key type, {@link IllegalArgumentException} will be thrown.
     * <p>
     * It should be possible to cut and paste directly from the Certicom website.
     * <p>
     * https://www.certicom.com/content/certicom/en/products-and-services/managed-certificate-service/smart-energy-device-certificate-service/zigbee-registration.html
     *
     * @param stringCertificate the certificate input
     * @throws IllegalArgumentException
     */
    public CerticomCbkeCertificate(String stringCertificate) throws IllegalArgumentException {
        String localString = stringCertificate.replaceAll("\\s", "").toUpperCase();

        String caPublicKeyString = getString(localString, DELIM_CA_PUBLIC_KEY);
        String devImplicitCertString = getString(localString, DELIM_DEV_IMPLICIT_CERT);
        String devPrivateKeyString = getString(localString, DELIM_DEV_PRIVATE_KEY);

        certificate = hexStringToIntArray(devImplicitCertString);
        privateKey = hexStringToIntArray(devPrivateKeyString);
        caPublicKey = hexStringToIntArray(caPublicKeyString);

        // Consistency check the key lengths to decide the certificate type
        if (certificate.length == LENGTH_DEV_IMPLICIT_CERT_1 && privateKey.length == LENGTH_DEV_PRIVATE_KEY_1
                && caPublicKey.length == LENGTH_CA_PUBLIC_KEY_1) {
            cryptoSuite = ZigBeeCryptoSuites.ECC_163K1;
        } else if (certificate.length == LENGTH_DEV_IMPLICIT_CERT_2 && privateKey.length == LENGTH_DEV_PRIVATE_KEY_2
                && caPublicKey.length == LENGTH_CA_PUBLIC_KEY_2) {
            cryptoSuite = ZigBeeCryptoSuites.ECC_283K1;
        } else {
            throw new IllegalArgumentException("Key lengths are not consistent with Certicom key format");
        }
    }

    private String getString(String certificate, String component) {
        int offset = certificate.indexOf(component) + component.length();
        if (offset == -1) {
            return "";
        }

        int nextOffset = certificate.length();
        for (String key : delimiterList) {
            int keyOffset = certificate.indexOf(key, offset);
            if (keyOffset != -1 && keyOffset < nextOffset) {
                nextOffset = keyOffset;
            }
        }

        return certificate.substring(offset, nextOffset);
    }
}
