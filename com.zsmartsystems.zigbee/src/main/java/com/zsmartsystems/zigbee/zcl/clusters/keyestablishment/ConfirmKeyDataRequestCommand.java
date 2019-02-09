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
 * Confirm Key Data Request Command value object class.
 * <p>
 * Cluster: <b>Key Establishment</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Key Establishment cluster.
 * <p>
 * The Confirm Key Request command allows the initiator sending device to confirm the key
 * established with the responder receiving device based on performing a cryptographic hash
 * using part of the generated keying material and the identities and ephemeral data of both
 * parties.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ConfirmKeyDataRequestCommand extends ZclCommand {
    /**
     * Secure Message Authentication Code command message field.
     */
    private ByteArray secureMessageAuthenticationCode;

    /**
     * Default constructor.
     */
    public ConfirmKeyDataRequestCommand() {
        genericCommand = false;
        clusterId = 0x0800;
        commandId = 2;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     */
    public void setSecureMessageAuthenticationCode(final ByteArray secureMessageAuthenticationCode) {
        this.secureMessageAuthenticationCode = secureMessageAuthenticationCode;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(secureMessageAuthenticationCode, ZclDataType.RAW_OCTET);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        secureMessageAuthenticationCode = (ByteArray) deserializer.deserialize(ZclDataType.RAW_OCTET);
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
