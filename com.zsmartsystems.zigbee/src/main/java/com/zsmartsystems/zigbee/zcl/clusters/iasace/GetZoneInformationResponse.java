/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Zone Information Response value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetZoneInformationResponse extends ZclCommand {
    /**
     * Zone ID command message field.
     */
    private Integer zoneId;

    /**
     * Zone Type command message field.
     */
    private Integer zoneType;

    /**
     * IEEE Address command message field.
     */
    private IeeeAddress ieeeAddress;

    /**
     * Zone Label command message field.
     * <p>
     * Provides the ZoneLabel stored in the IAS CIE. If none is programmed, the IAS ACE server
     * shall transmit a string with a length of zero.There is no minimum or maximum length to the
     * Zone Label field; however, the Zone Label should be between 16 to 24 alphanumeric
     * characters in length.
     * <p>
     * The string encoding shall be UTF-8.
     */
    private String zoneLabel;

    /**
     * Default constructor.
     */
    public GetZoneInformationResponse() {
        genericCommand = false;
        clusterId = 0x0501;
        commandId = 2;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Zone ID.
     *
     * @return the Zone ID
     */
    public Integer getZoneId() {
        return zoneId;
    }

    /**
     * Sets Zone ID.
     *
     * @param zoneId the Zone ID
     */
    public void setZoneId(final Integer zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * Gets Zone Type.
     *
     * @return the Zone Type
     */
    public Integer getZoneType() {
        return zoneType;
    }

    /**
     * Sets Zone Type.
     *
     * @param zoneType the Zone Type
     */
    public void setZoneType(final Integer zoneType) {
        this.zoneType = zoneType;
    }

    /**
     * Gets IEEE Address.
     *
     * @return the IEEE Address
     */
    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    /**
     * Sets IEEE Address.
     *
     * @param ieeeAddress the IEEE Address
     */
    public void setIeeeAddress(final IeeeAddress ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    /**
     * Gets Zone Label.
     * <p>
     * Provides the ZoneLabel stored in the IAS CIE. If none is programmed, the IAS ACE server
     * shall transmit a string with a length of zero.There is no minimum or maximum length to the
     * Zone Label field; however, the Zone Label should be between 16 to 24 alphanumeric
     * characters in length.
     * <p>
     * The string encoding shall be UTF-8.
     *
     * @return the Zone Label
     */
    public String getZoneLabel() {
        return zoneLabel;
    }

    /**
     * Sets Zone Label.
     * <p>
     * Provides the ZoneLabel stored in the IAS CIE. If none is programmed, the IAS ACE server
     * shall transmit a string with a length of zero.There is no minimum or maximum length to the
     * Zone Label field; however, the Zone Label should be between 16 to 24 alphanumeric
     * characters in length.
     * <p>
     * The string encoding shall be UTF-8.
     *
     * @param zoneLabel the Zone Label
     */
    public void setZoneLabel(final String zoneLabel) {
        this.zoneLabel = zoneLabel;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(zoneId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(zoneType, ZclDataType.ENUMERATION_16_BIT);
        serializer.serialize(ieeeAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(zoneLabel, ZclDataType.CHARACTER_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        zoneId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        zoneType = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_16_BIT);
        ieeeAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        zoneLabel = (String) deserializer.deserialize(ZclDataType.CHARACTER_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(143);
        builder.append("GetZoneInformationResponse [");
        builder.append(super.toString());
        builder.append(", zoneId=");
        builder.append(zoneId);
        builder.append(", zoneType=");
        builder.append(zoneType);
        builder.append(", ieeeAddress=");
        builder.append(ieeeAddress);
        builder.append(", zoneLabel=");
        builder.append(zoneLabel);
        builder.append(']');
        return builder.toString();
    }

}
