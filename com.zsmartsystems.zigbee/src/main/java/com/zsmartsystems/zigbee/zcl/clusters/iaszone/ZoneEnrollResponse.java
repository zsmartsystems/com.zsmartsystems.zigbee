/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iaszone;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
public class ZoneEnrollResponse extends ZclCommand {
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
     */
    public ZoneEnrollResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     * @return the ZoneEnrollResponse command
     */
    public ZoneEnrollResponse setEnrollResponseCode(final Integer enrollResponseCode) {
        this.enrollResponseCode = enrollResponseCode;
        return this;
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
     * @return the ZoneEnrollResponse command
     */
    public ZoneEnrollResponse setZoneId(final Integer zoneId) {
        this.zoneId = zoneId;
        return this;
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
