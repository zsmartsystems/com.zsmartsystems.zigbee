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
 * Ephemeral Data Response value object class.
 * <p>
 * Cluster: <b>Key Establishment</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Key Establishment cluster.
 * <p>
 * The Ephemeral Data Response command allows a device to communicate its ephemeral data to
 * another device that previously requested it.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class EphemeralDataResponse extends ZclCommand {
    /**
     * Ephemeral Data command message field.
     */
    private ByteArray ephemeralData;

    /**
     * Default constructor.
     */
    public EphemeralDataResponse() {
        genericCommand = false;
        clusterId = 0x0800;
        commandId = 1;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Ephemeral Data.
     *
     * @return the Ephemeral Data
     */
    public ByteArray getEphemeralData() {
        return ephemeralData;
    }

    /**
     * Sets Ephemeral Data.
     *
     * @param ephemeralData the Ephemeral Data
     */
    public void setEphemeralData(final ByteArray ephemeralData) {
        this.ephemeralData = ephemeralData;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(ephemeralData, ZclDataType.RAW_OCTET);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        ephemeralData = (ByteArray) deserializer.deserialize(ZclDataType.RAW_OCTET);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(57);
        builder.append("EphemeralDataResponse [");
        builder.append(super.toString());
        builder.append(", ephemeralData=");
        builder.append(ephemeralData);
        builder.append(']');
        return builder.toString();
    }

}
