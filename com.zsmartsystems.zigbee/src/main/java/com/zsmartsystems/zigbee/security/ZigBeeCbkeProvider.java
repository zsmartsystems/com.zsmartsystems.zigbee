/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.security;

import java.util.Set;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;

/**
 * Interface defining the Certificate Based Key Exchange security provider.
 * <p>
 * The CBKE provider provides methods to get the provider capabilities. To perform a CBKE, the user must call
 * {@link #getCbkeKeyExchange} to get a key exchange algorithm provider. The ZigBeeCbkeProvider may only be able to
 * support a limited number of simultaneous key exchanges, and therefore {@link #getCbkeKeyExchange} may fail with
 * {@link ZigBeeStatus#NO_RESOURCES} if too many exchanges are requested at once.
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeeCbkeProvider {
    /**
     * Checks if the key associated with the partner {@link IeeeAddress} has previously been authorised.
     *
     * @return true if the partner address has been authorised
     */
    public boolean isAuthorised(IeeeAddress partner);

    /**
     * Adds a certificate to the provider, along with the public key of the signing Certificate Authority and the
     * private key of our node. Multiple certificates may be added, however if more than one certificate of
     * each type is added, only the last will be used.
     *
     * @param certificate the {@link ZigBeeCryptoCertificate} to be added to the provider, signed by the CA
     * @return {@link ZigBeeStatus} defining success or failure if the certificate was added successfully
     */
    public ZigBeeStatus addCertificate(ZigBeeCbkeCertificate certificate);

    /**
     * Gets a list of supported crypto suites. This is the suites that may be used - ie the algorithms are supported. It
     * does not mean that certificates are available for each suite supported.
     *
     * @return set of supported {@link ZigBeeCryptoSuites}
     */
    public Set<ZigBeeCryptoSuites> getSupportedCryptoSuites();

    /**
     * Gets a list of available crypto suites that are provided. This should also ensure that returned suites have
     * certificates available.
     *
     * @return set of available {@link ZigBeeCryptoSuites}
     */
    public Set<ZigBeeCryptoSuites> getAvailableCryptoSuites();

    /**
     * Gets the crypto certificate of the specified type
     *
     * @return the requested {@link ByteArray} or null if no certificate was found
     */
    public ByteArray getCertificate(ZigBeeCryptoSuites suite);

    /**
     * Gets a {@link ZigBeeCbkeExchange} to perform the CBKE cryptographic algorithms as an initiator (Client). If the
     * provider does not support multiple key exchanges, then this method may return null and the user should wait an
     * appropriate amount of time before trying again.
     * <p>
     * Once the {@link ZigBeeCbkeExchange} has been returned here, it must be released by calling
     * {@link ZigBeeCbkeExchange#complete
     *
     * @return a {@link ZigBeeCbkeExchange}, or null if no resources are available.
     */
    public ZigBeeCbkeExchange getCbkeKeyExchangeInitiator();

    /**
     * Gets a {@link ZigBeeCbkeExchange} to perform the CBKE cryptographic algorithms as a responder (Server). If the
     * provider does not support multiple key exchanges, then this method may return null and the user should wait an
     * appropriate amount of time before trying again.
     * <p>
     * Once the {@link ZigBeeCbkeExchange} has been returned here, it must be released by calling
     * {@link ZigBeeCbkeExchange#complete
     *
     * @return a {@link ZigBeeCbkeExchange}, or null if no resources are available.
     */
    public ZigBeeCbkeExchange getCbkeKeyExchangeResponder();

    /**
     * Gets the time that the provider will take to generate the ephemeral data. This is passed to the partner to allow
     * it to know how long to wait before timing out.
     *
     * @return the Ephemeral Data Generate Time
     */
    public int getEphemeralDataGenerateTime();

    /**
     * Gets the time that the provider will take to generate the confirm key. This is passed to the partner to allow it
     * to know how long to wait before timing out.
     *
     * @return the Confirm Key Generate Time
     */
    public int getConfirmKeyGenerateTime();
}
