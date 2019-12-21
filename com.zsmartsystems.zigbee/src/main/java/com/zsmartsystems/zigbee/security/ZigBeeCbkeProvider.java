/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 * Interface defining the Certificate Based Key Exchange security algorithm methods.
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeeCbkeProvider {
    /**
     * Set the provider to act as a client or server when generating the SMAC codes.
     *
     * @param client set to true to act as a client, and false to act as the server
     * @return the {@link ZigBeeStatus} providing success or failure
     */
    public ZigBeeStatus setClientServer(boolean client);

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
     * @return true if the certificate was added successfully
     */
    public boolean addCertificate(ZigBeeCbkeCertificate certificate);

    /**
     * Sets the partner certificate as part of the CBKE.
     *
     * @param suite the {@link ZigBeeCryptoSuites}
     * @param partnerCertificate a {@link ByteArray} containing the partner certificate
     * @return true if the data was stored
     */
    public boolean addPartnerCertificate(ZigBeeCryptoSuites suite, ByteArray partnerCertificate);

    /**
     * Sets the partner ephemeral data as part of the CBKE.
     *
     * @param suite the {@link ZigBeeCryptoSuites}
     * @param partnerEphemeralData a {@link ByteArray} containing the partner Ephemeral data
     * @return true if the data was stored
     */
    public boolean addPartnerEphemeralData(ZigBeeCryptoSuites suite, ByteArray partnerEphemeralData);

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
     * Gets the ephemeral data for specified certificate type
     *
     * @param suite the {@link ZigBeeCryptoSuites} to get
     * @return the ephemeral data as a {@link ByteArray}, or null if there was an error (eg no certificate available for
     *         the requested type).
     */
    public ByteArray getCbkeEphemeralData(ZigBeeCryptoSuites suite);

    /**
     * Gets the Secure Message Authentication Code for the initiator. This should be sent to the remote.
     *
     * @param suite the {@link ZigBeeCryptoSuites} to get
     * @return the initiator secure message authentication code as a {@link ByteArray}
     */
    public ByteArray getInitiatorMac(ZigBeeCryptoSuites suite);

    /**
     * Gets the Secure Message Authentication Code for the responder. This should be compared with the response from the
     * remote.
     *
     * @param suite the {@link ZigBeeCryptoSuites} to get
     * @return the responder secure message authentication code as a {@link ByteArray}
     */
    public ByteArray getResponderMac(ZigBeeCryptoSuites suite);

    /**
     * Tells the provider that the key exchange is complete. If success is true, then the provider shall store the key
     * in the dongle key table.
     *
     * @param suite the {@link ZigBeeCryptoSuites} being used
     * @param success true if the key exchange was successful and the provider should store the key
     * @return true if the key was stored
     */
    public boolean completeKeyExchange(ZigBeeCryptoSuites suite, boolean success);

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
