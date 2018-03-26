/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Get Zone Status Command value object class.
 * <p>
 * This command is used by ACE clients to request an update of the status of the IAS Zone devices managed by the ACE server
 * (i.e., the IAS CIE). In particular, this command is useful for battery-powered ACE clients with polling rates longer than
 * the ZigBee standard check-in rate. The command is similar to the Get At-tributes Supportedcommand in that it specifies a
 * starting Zone ID and a number of Zone IDs for which information is requested.Depending on the number of IAS Zone devices
 * managed by the IAS ACE server, sending the Zone Status of all zones MAYnot fit into a single Get ZoneStatus Response command.
 * IAS ACE clients MAY need to send multiple Get Zone Status commands in order to get the information they seek.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * The IAS ACE cluster defines an interface to the functionality of any Ancillary
 * Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE
 * device can access a IAS CIE device and manipulate the IAS system, on behalf of a
 * level-2 user.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-26T17:34:00Z")
public class GetZoneStatusCommand extends ZclCommand {
    /**
     * Starting Zone ID command message field.
     */
    private Integer startingZoneId;

    /**
     * Max Zone IDs command message field.
     */
    private Integer maxZoneIDs;

    /**
     * Zone Status Mask Flag command message field.
     */
    private Boolean zoneStatusMaskFlag;

    /**
     * Zone Status Mask command message field.
     */
    private Integer zoneStatusMask;

    /**
     * Default constructor.
     */
    public GetZoneStatusCommand() {
        genericCommand = false;
        clusterId = 1281;
        commandId = 9;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Starting Zone ID.
     *
     * @return the Starting Zone ID
     */
    public Integer getStartingZoneId() {
        return startingZoneId;
    }

    /**
     * Sets Starting Zone ID.
     *
     * @param startingZoneId the Starting Zone ID
     */
    public void setStartingZoneId(final Integer startingZoneId) {
        this.startingZoneId = startingZoneId;
    }

    /**
     * Gets Max Zone IDs.
     *
     * @return the Max Zone IDs
     */
    public Integer getMaxZoneIDs() {
        return maxZoneIDs;
    }

    /**
     * Sets Max Zone IDs.
     *
     * @param maxZoneIDs the Max Zone IDs
     */
    public void setMaxZoneIDs(final Integer maxZoneIDs) {
        this.maxZoneIDs = maxZoneIDs;
    }

    /**
     * Gets Zone Status Mask Flag.
     *
     * @return the Zone Status Mask Flag
     */
    public Boolean getZoneStatusMaskFlag() {
        return zoneStatusMaskFlag;
    }

    /**
     * Sets Zone Status Mask Flag.
     *
     * @param zoneStatusMaskFlag the Zone Status Mask Flag
     */
    public void setZoneStatusMaskFlag(final Boolean zoneStatusMaskFlag) {
        this.zoneStatusMaskFlag = zoneStatusMaskFlag;
    }

    /**
     * Gets Zone Status Mask.
     *
     * @return the Zone Status Mask
     */
    public Integer getZoneStatusMask() {
        return zoneStatusMask;
    }

    /**
     * Sets Zone Status Mask.
     *
     * @param zoneStatusMask the Zone Status Mask
     */
    public void setZoneStatusMask(final Integer zoneStatusMask) {
        this.zoneStatusMask = zoneStatusMask;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(startingZoneId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(maxZoneIDs, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(zoneStatusMaskFlag, ZclDataType.BOOLEAN);
        serializer.serialize(zoneStatusMask, ZclDataType.BITMAP_16_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        startingZoneId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        maxZoneIDs = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        zoneStatusMaskFlag = (Boolean) deserializer.deserialize(ZclDataType.BOOLEAN);
        zoneStatusMask = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(159);
        builder.append("GetZoneStatusCommand [");
        builder.append(super.toString());
        builder.append(", startingZoneId=");
        builder.append(startingZoneId);
        builder.append(", maxZoneIDs=");
        builder.append(maxZoneIDs);
        builder.append(", zoneStatusMaskFlag=");
        builder.append(zoneStatusMaskFlag);
        builder.append(", zoneStatusMask=");
        builder.append(zoneStatusMask);
        builder.append(']');
        return builder.toString();
    }

}
