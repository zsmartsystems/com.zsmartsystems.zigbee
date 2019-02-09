/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.keyestablishment;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Initiate Key Establishment Request Command value object class.
 * <p>
 * Cluster: <b>Key Establishment</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Key Establishment cluster.
 * <p>
 * The Initiate Key Establishment Request command allows a device to initiate key
 * establishment with another device. The sender will transmit its identity information and
 * key establishment protocol information to the receiving device.
 * <p>
 * If the device does not currently have the resources to respond to a key establishment request
 * it shall send a Terminate Key Establishment command with the result value set to
 * NO_RESOURCES and the Wait Time field shall be set to an approximation of the time that must
 * pass before the device will have the resources to process a new Key Establishment Request.
 * <p>
 * If the device can process this request, it shall check the Issuer field of the device's
 * implicit certificate. If the Issuer field does not contain a value that corresponds to a
 * known Certificate Authority, the device shall send a Terminate Key Establishment command
 * with the result set to UNKNOWN_ISSUER.
 * <p>
 * If the device accepts the request it shall send an Initiate Key Establishment Response
 * command containing its own identity information. The device should verify the certificate
 * belongs to the address that the device is communicating with. The binding between the
 * identity of the communicating device and its address is verifiable using out-of-band
 * method.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class InitiateKeyEstablishmentRequestCommand extends ZclCommand {
    /**
     * Key Establishment Suite command message field.
     * <p>
     * This will be the type of Key Establishment that the initiator is requesting for the Key
     * Establishment Cluster. For CBKE-ECMQV this will be 0x0001.
     */
    private Integer keyEstablishmentSuite;

    /**
     * Ephemeral Data Generate Time command message field.
     * <p>
     * This value indicates approximately how long the initiator device will take in seconds
     * to generate the Ephemeral Data Request command. The valid range is 0x00 to 0xFE.
     */
    private Integer ephemeralDataGenerateTime;

    /**
     * Confirm Key Generate Time command message field.
     * <p>
     * This value indicates approximately how long the initiator device will take in seconds
     * to generate the Confirm Key Request command. The valid range is 0x00 to 0xFE.
     */
    private Integer confirmKeyGenerateTime;

    /**
     * Identity command message field.
     * <p>
     * For KeyEstablishmentSuite = 0x0001 (CBKE), the identity field shall be the block of
     * octets containing the implicit certificate CERTU.
     */
    private ByteArray identity;

    /**
     * Default constructor.
     */
    public InitiateKeyEstablishmentRequestCommand() {
        genericCommand = false;
        clusterId = 0x0800;
        commandId = 0;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Key Establishment Suite.
     * <p>
     * This will be the type of Key Establishment that the initiator is requesting for the Key
     * Establishment Cluster. For CBKE-ECMQV this will be 0x0001.
     *
     * @return the Key Establishment Suite
     */
    public Integer getKeyEstablishmentSuite() {
        return keyEstablishmentSuite;
    }

    /**
     * Sets Key Establishment Suite.
     * <p>
     * This will be the type of Key Establishment that the initiator is requesting for the Key
     * Establishment Cluster. For CBKE-ECMQV this will be 0x0001.
     *
     * @param keyEstablishmentSuite the Key Establishment Suite
     */
    public void setKeyEstablishmentSuite(final Integer keyEstablishmentSuite) {
        this.keyEstablishmentSuite = keyEstablishmentSuite;
    }

    /**
     * Gets Ephemeral Data Generate Time.
     * <p>
     * This value indicates approximately how long the initiator device will take in seconds
     * to generate the Ephemeral Data Request command. The valid range is 0x00 to 0xFE.
     *
     * @return the Ephemeral Data Generate Time
     */
    public Integer getEphemeralDataGenerateTime() {
        return ephemeralDataGenerateTime;
    }

    /**
     * Sets Ephemeral Data Generate Time.
     * <p>
     * This value indicates approximately how long the initiator device will take in seconds
     * to generate the Ephemeral Data Request command. The valid range is 0x00 to 0xFE.
     *
     * @param ephemeralDataGenerateTime the Ephemeral Data Generate Time
     */
    public void setEphemeralDataGenerateTime(final Integer ephemeralDataGenerateTime) {
        this.ephemeralDataGenerateTime = ephemeralDataGenerateTime;
    }

    /**
     * Gets Confirm Key Generate Time.
     * <p>
     * This value indicates approximately how long the initiator device will take in seconds
     * to generate the Confirm Key Request command. The valid range is 0x00 to 0xFE.
     *
     * @return the Confirm Key Generate Time
     */
    public Integer getConfirmKeyGenerateTime() {
        return confirmKeyGenerateTime;
    }

    /**
     * Sets Confirm Key Generate Time.
     * <p>
     * This value indicates approximately how long the initiator device will take in seconds
     * to generate the Confirm Key Request command. The valid range is 0x00 to 0xFE.
     *
     * @param confirmKeyGenerateTime the Confirm Key Generate Time
     */
    public void setConfirmKeyGenerateTime(final Integer confirmKeyGenerateTime) {
        this.confirmKeyGenerateTime = confirmKeyGenerateTime;
    }

    /**
     * Gets Identity.
     * <p>
     * For KeyEstablishmentSuite = 0x0001 (CBKE), the identity field shall be the block of
     * octets containing the implicit certificate CERTU.
     *
     * @return the Identity
     */
    public ByteArray getIdentity() {
        return identity;
    }

    /**
     * Sets Identity.
     * <p>
     * For KeyEstablishmentSuite = 0x0001 (CBKE), the identity field shall be the block of
     * octets containing the implicit certificate CERTU.
     *
     * @param identity the Identity
     */
    public void setIdentity(final ByteArray identity) {
        this.identity = identity;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(keyEstablishmentSuite, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(ephemeralDataGenerateTime, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(confirmKeyGenerateTime, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(identity, ZclDataType.RAW_OCTET);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        keyEstablishmentSuite = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        ephemeralDataGenerateTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        confirmKeyGenerateTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        identity = (ByteArray) deserializer.deserialize(ZclDataType.RAW_OCTET);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(197);
        builder.append("InitiateKeyEstablishmentRequestCommand [");
        builder.append(super.toString());
        builder.append(", keyEstablishmentSuite=");
        builder.append(keyEstablishmentSuite);
        builder.append(", ephemeralDataGenerateTime=");
        builder.append(ephemeralDataGenerateTime);
        builder.append(", confirmKeyGenerateTime=");
        builder.append(confirmKeyGenerateTime);
        builder.append(", identity=");
        builder.append(identity);
        builder.append(']');
        return builder.toString();
    }

}
