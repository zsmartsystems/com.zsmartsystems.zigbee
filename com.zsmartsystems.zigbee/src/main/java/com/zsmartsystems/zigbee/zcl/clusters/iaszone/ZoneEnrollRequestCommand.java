/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iaszone;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Zone Enroll Request Command value object class.
 * <p>
 * Cluster: <b>IAS Zone</b>. Command ID 0x01 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS Zone cluster.
 * <p>
 * The Zone Enroll Request command is generated when a device embodying the Zone server cluster
 * wishes to be enrolled as an active alarm device. It must do this immediately it has joined the
 * network (during commissioning).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class ZoneEnrollRequestCommand extends ZclIasZoneCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0500;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

    /**
     * Zone Type command message field.
     */
    private Integer zoneType;

    /**
     * Manufacturer Code command message field.
     */
    private Integer manufacturerCode;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ZoneEnrollRequestCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param zoneType {@link Integer} Zone Type
     * @param manufacturerCode {@link Integer} Manufacturer Code
     */
    public ZoneEnrollRequestCommand(
            Integer zoneType,
            Integer manufacturerCode) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.zoneType = zoneType;
        this.manufacturerCode = manufacturerCode;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneType(final Integer zoneType) {
        this.zoneType = zoneType;
    }

    /**
     * Gets Manufacturer Code.
     *
     * @return the Manufacturer Code
     */
    public Integer getManufacturerCode() {
        return manufacturerCode;
    }

    /**
     * Sets Manufacturer Code.
     *
     * @param manufacturerCode the Manufacturer Code
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setManufacturerCode(final Integer manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(zoneType, ZclDataType.ENUMERATION_16_BIT);
        serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        zoneType = deserializer.deserialize(ZclDataType.ENUMERATION_16_BIT);
        manufacturerCode = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(91);
        builder.append("ZoneEnrollRequestCommand [");
        builder.append(super.toString());
        builder.append(", zoneType=");
        builder.append(zoneType);
        builder.append(", manufacturerCode=");
        builder.append(manufacturerCode);
        builder.append(']');
        return builder.toString();
    }

}
