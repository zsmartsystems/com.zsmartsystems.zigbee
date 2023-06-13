/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
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
 * Confirm Key Data Request Command value object class.
 * <p>
 * Cluster: <b>Key Establishment</b>. Command ID 0x02 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Key Establishment cluster.
 * <p>
 * The Confirm Key Request command allows the initiator sending device to confirm the key
 * established with the responder receiving device based on performing a cryptographic hash
 * using part of the generated keying material and the identities and ephemeral data of both
 * parties.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class ConfirmKeyDataRequestCommand extends ZclKeyEstablishmentCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0800;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Secure Message Authentication Code command message field.
     */
    private ByteArray secureMessageAuthenticationCode;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ConfirmKeyDataRequestCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param secureMessageAuthenticationCode {@link ByteArray} Secure Message Authentication Code
     */
    public ConfirmKeyDataRequestCommand(
            ByteArray secureMessageAuthenticationCode) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.secureMessageAuthenticationCode = secureMessageAuthenticationCode;
    }

    /**
     * Gets Secure Message Authentication Code.
     *
     * @return the Secure Message Authentication Code
     */
    public ByteArray getSecureMessageAuthenticationCode() {
        return secureMessageAuthenticationCode;
    }

    /**
     * Sets Secure Message Authentication Code.
     *
     * @param secureMessageAuthenticationCode the Secure Message Authentication Code
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSecureMessageAuthenticationCode(final ByteArray secureMessageAuthenticationCode) {
        this.secureMessageAuthenticationCode = secureMessageAuthenticationCode;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(secureMessageAuthenticationCode, ZclDataType.RAW_OCTET);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        secureMessageAuthenticationCode = deserializer.deserialize(ZclDataType.RAW_OCTET);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(82);
        builder.append("ConfirmKeyDataRequestCommand [");
        builder.append(super.toString());
        builder.append(", secureMessageAuthenticationCode=");
        builder.append(secureMessageAuthenticationCode);
        builder.append(']');
        return builder.toString();
    }

}
