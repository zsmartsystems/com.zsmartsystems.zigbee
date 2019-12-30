/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.security;

import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;

/**
 * Interface defining the Certificate Based Key Exchange security algorithm methods.
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeeCbkeExchange {
    /**
     * Sets the @link ZigBeeCryptoSuites} to be used for this exchange. This must be set before performing any crypto
     * functions.
     *
     * @param suite a {@link ZigBeeCryptoSuites} to be used for this exchange
     * @return the {@link ZigBeeStatus} providing success or failure
     */
    public ZigBeeStatus setCryptoSuite(ZigBeeCryptoSuites suite);

    /**
     * Gets the crypto suite being used for the exchange. This must be set with {@link #setCryptoSuite} or it will
     * return null;
     * 
     * @return the {@link ZigBeeCryptoSuites} being used for this exchange
     */
    public ZigBeeCryptoSuites getCryptoSuite();

    /**
     * Sets the partner certificate as part of the CBKE.
     *
     * @param partnerCertificate a {@link ByteArray} containing the partner certificate
     * @return the {@link ZigBeeStatus} providing success or failure
     */
    public ZigBeeStatus addPartnerCertificate(ByteArray partnerCertificate);

    /**
     * Sets the partner ephemeral data as part of the CBKE.
     *
     * @param partnerEphemeralData a {@link ByteArray} containing the partner Ephemeral data
     * @return the {@link ZigBeeStatus} providing success or failure
     */
    public ZigBeeStatus addPartnerEphemeralData(ByteArray partnerEphemeralData);

    /**
     * Gets the crypto certificate of the specified type
     *
     * @return a {@link ByteArray} containing the certificate data
     */
    public ByteArray getCertificate();

    /**
     * Gets the ephemeral data for specified certificate type
     *
     * @return the ephemeral data as a {@link ByteArray}, or null if there was an error (eg no certificate available for
     *         the requested type).
     */
    public ByteArray getCbkeEphemeralData();

    /**
     * Gets the Secure Message Authentication Code for the initiator. This should be sent to the remote.
     *
     * @return the initiator secure message authentication code as a {@link ByteArray}
     */
    public ByteArray getInitiatorMac();

    /**
     * Gets the Secure Message Authentication Code for the responder. This should be compared with the response from the
     * remote.
     *
     * @return the responder secure message authentication code as a {@link ByteArray}
     */
    public ByteArray getResponderMac();

    /**
     * Tells the provider that the key exchange is complete. If success is true, then the provider shall store the key
     * in the dongle key table.
     *
     * @param success true if the key exchange was successful and the provider should store the key
     * @return the {@link ZigBeeStatus} providing success or failure
     */
    public ZigBeeStatus completeKeyExchange(boolean success);
}
