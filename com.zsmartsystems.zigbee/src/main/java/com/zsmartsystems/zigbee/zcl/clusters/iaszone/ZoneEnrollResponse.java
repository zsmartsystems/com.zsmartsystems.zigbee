/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
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
 * Zone Enroll Response value object class.
 * <p>
 * Cluster: <b>IAS Zone</b>. Command ID 0x00 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS Zone cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class ZoneEnrollResponse extends ZclIasZoneCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0500;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

    /**
     * Enroll Response Code command message field.
     */
    private Integer enrollResponseCode;

    /**
     * Zone ID command message field.
     */
    private Integer zoneId;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ZoneEnrollResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param enrollResponseCode {@link Integer} Enroll Response Code
     * @param zoneId {@link Integer} Zone ID
     */
    public ZoneEnrollResponse(
            Integer enrollResponseCode,
            Integer zoneId) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.enrollResponseCode = enrollResponseCode;
        this.zoneId = zoneId;
    }

    /**
     * Gets Enroll Response Code.
     *
     * @return the Enroll Response Code
     */
    public Integer getEnrollResponseCode() {
        return enrollResponseCode;
    }

    /**
     * Sets Enroll Response Code.
     *
     * @param enrollResponseCode the Enroll Response Code
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setEnrollResponseCode(final Integer enrollResponseCode) {
        this.enrollResponseCode = enrollResponseCode;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneId(final Integer zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(enrollResponseCode, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(zoneId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        enrollResponseCode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        zoneId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(85);
        builder.append("ZoneEnrollResponse [");
        builder.append(super.toString());
        builder.append(", enrollResponseCode=");
        builder.append(enrollResponseCode);
        builder.append(", zoneId=");
        builder.append(zoneId);
        builder.append(']');
        return builder.toString();
    }

}
