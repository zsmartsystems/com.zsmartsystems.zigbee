/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.keyestablishment;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Initiate Key Establishment Response value object class.
 * <p>
 * Cluster: <b>Key Establishment</b>. Command ID 0x00 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Key Establishment cluster.
 * <p>
 * The Initiate Key Establishment Response command allows a device to respond to a device
 * requesting the initiation of key establishment with it. The sender will transmit its
 * identity information and key establishment protocol information to the receiving device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class InitiateKeyEstablishmentResponse extends ZclKeyEstablishmentCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0800;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

    /**
     * Requested Key Establishment Suite command message field.
     * <p>
     * This will be the type of KeyEstablishmentSuite that the initiator has requested be used
     * for the key establishment exchange. The device shall set a single bit in the bitmask
     * indicating the requested suite, all other bits shall be set to zero.
     */
    private Integer requestedKeyEstablishmentSuite;

    /**
     * Ephemeral Data Generate Time command message field.
     * <p>
     * This value indicates approximately how long in seconds the responder device takes to
     * generate the Ephemeral Data Response message. The valid range is 0x00 to 0xFE.
     */
    private Integer ephemeralDataGenerateTime;

    /**
     * Confirm Key Generate Time command message field.
     * <p>
     * This value indicates approximately how long the responder device will take in seconds
     * to generate the Confirm Key Response message. The valid range is 0x00 to 0xFE.
     */
    private Integer confirmKeyGenerateTime;

    /**
     * Identity command message field.
     * <p>
     * For KeyEstablishmentSuite = 0x0001 (CBKE), the identity field shall be the block of
     * Octets containing the implicit certificate CERTU .
     */
    private ByteArray identity;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public InitiateKeyEstablishmentResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param requestedKeyEstablishmentSuite {@link Integer} Requested Key Establishment Suite
     * @param ephemeralDataGenerateTime {@link Integer} Ephemeral Data Generate Time
     * @param confirmKeyGenerateTime {@link Integer} Confirm Key Generate Time
     * @param identity {@link ByteArray} Identity
     */
    public InitiateKeyEstablishmentResponse(
            Integer requestedKeyEstablishmentSuite,
            Integer ephemeralDataGenerateTime,
            Integer confirmKeyGenerateTime,
            ByteArray identity) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.requestedKeyEstablishmentSuite = requestedKeyEstablishmentSuite;
        this.ephemeralDataGenerateTime = ephemeralDataGenerateTime;
        this.confirmKeyGenerateTime = confirmKeyGenerateTime;
        this.identity = identity;
    }

    /**
     * Gets Requested Key Establishment Suite.
     * <p>
     * This will be the type of KeyEstablishmentSuite that the initiator has requested be used
     * for the key establishment exchange. The device shall set a single bit in the bitmask
     * indicating the requested suite, all other bits shall be set to zero.
     *
     * @return the Requested Key Establishment Suite
     */
    public Integer getRequestedKeyEstablishmentSuite() {
        return requestedKeyEstablishmentSuite;
    }

    /**
     * Sets Requested Key Establishment Suite.
     * <p>
     * This will be the type of KeyEstablishmentSuite that the initiator has requested be used
     * for the key establishment exchange. The device shall set a single bit in the bitmask
     * indicating the requested suite, all other bits shall be set to zero.
     *
     * @param requestedKeyEstablishmentSuite the Requested Key Establishment Suite
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setRequestedKeyEstablishmentSuite(final Integer requestedKeyEstablishmentSuite) {
        this.requestedKeyEstablishmentSuite = requestedKeyEstablishmentSuite;
    }

    /**
     * Gets Ephemeral Data Generate Time.
     * <p>
     * This value indicates approximately how long in seconds the responder device takes to
     * generate the Ephemeral Data Response message. The valid range is 0x00 to 0xFE.
     *
     * @return the Ephemeral Data Generate Time
     */
    public Integer getEphemeralDataGenerateTime() {
        return ephemeralDataGenerateTime;
    }

    /**
     * Sets Ephemeral Data Generate Time.
     * <p>
     * This value indicates approximately how long in seconds the responder device takes to
     * generate the Ephemeral Data Response message. The valid range is 0x00 to 0xFE.
     *
     * @param ephemeralDataGenerateTime the Ephemeral Data Generate Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setEphemeralDataGenerateTime(final Integer ephemeralDataGenerateTime) {
        this.ephemeralDataGenerateTime = ephemeralDataGenerateTime;
    }

    /**
     * Gets Confirm Key Generate Time.
     * <p>
     * This value indicates approximately how long the responder device will take in seconds
     * to generate the Confirm Key Response message. The valid range is 0x00 to 0xFE.
     *
     * @return the Confirm Key Generate Time
     */
    public Integer getConfirmKeyGenerateTime() {
        return confirmKeyGenerateTime;
    }

    /**
     * Sets Confirm Key Generate Time.
     * <p>
     * This value indicates approximately how long the responder device will take in seconds
     * to generate the Confirm Key Response message. The valid range is 0x00 to 0xFE.
     *
     * @param confirmKeyGenerateTime the Confirm Key Generate Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setConfirmKeyGenerateTime(final Integer confirmKeyGenerateTime) {
        this.confirmKeyGenerateTime = confirmKeyGenerateTime;
    }

    /**
     * Gets Identity.
     * <p>
     * For KeyEstablishmentSuite = 0x0001 (CBKE), the identity field shall be the block of
     * Octets containing the implicit certificate CERTU .
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
     * Octets containing the implicit certificate CERTU .
     *
     * @param identity the Identity
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setIdentity(final ByteArray identity) {
        this.identity = identity;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(requestedKeyEstablishmentSuite, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(ephemeralDataGenerateTime, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(confirmKeyGenerateTime, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(identity, ZclDataType.RAW_OCTET);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        requestedKeyEstablishmentSuite = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        ephemeralDataGenerateTime = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        confirmKeyGenerateTime = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        identity = deserializer.deserialize(ZclDataType.RAW_OCTET);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("InitiateKeyEstablishmentResponse [");
        builder.append(super.toString());
        builder.append(", requestedKeyEstablishmentSuite=");
        builder.append(requestedKeyEstablishmentSuite);
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
