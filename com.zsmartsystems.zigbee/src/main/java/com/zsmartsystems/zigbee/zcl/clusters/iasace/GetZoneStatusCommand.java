/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Zone Status Command value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * This command is used by ACE clients to request an update of the status of the IAS Zone devices
 * managed by the ACE server (i.e., the IAS CIE). In particular, this command is useful for
 * battery-powered ACE clients with polling rates longer than the ZigBee standard check-in
 * rate. The command is similar to the Get Attributes Supported command in that it specifies a
 * starting Zone ID and a number of Zone IDs for which information is requested. Depending on the
 * number of IAS Zone devices managed by the IAS ACE server, sending the Zone Status of all zones
 * may not fit into a single Get ZoneStatus Response command. IAS ACE clients may need to send
 * multiple Get Zone Status commands in order to get the information they seek.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetZoneStatusCommand extends ZclCommand {
    /**
     * Starting Zone ID command message field.
     * <p>
     * Specifies the starting Zone ID at which the IAS Client would like to obtain zone status
     * information.
     */
    private Integer startingZoneId;

    /**
     * Max Zone I Ds command message field.
     * <p>
     * Specifies the maximum number of Zone IDs and corresponding Zone Statuses that are to be
     * returned by the IAS ACE server when it responds with a Get Zone Status Response command
     */
    private Integer maxZoneIDs;

    /**
     * Zone Status Mask Flag command message field.
     * <p>
     * Functions as a query operand with the Zone Status Mask field. If set to zero (i.e.,
     * FALSE), the IAS ACE server shall include all Zone IDs and their status, regardless of
     * their Zone Status when it responds with a Get Zone Status Response command. If set to one
     * (i.e., TRUE), the IAS ACE server shall include only those Zone IDs whose Zone Status
     * attribute is equal to one or more of the Zone Statuses requested in the Zone Status Mask
     * field of the Get Zone Status command.
     * <p>
     * Use of Zone Status Mask Flag and Zone Status Mask fields allow a client to obtain updated
     * information for the subset of Zone IDs they’re interested in, which is beneficial when
     * the number of IAS Zone devices in a system is large.
     */
    private Boolean zoneStatusMaskFlag;

    /**
     * Zone Status Mask command message field.
     * <p>
     * Coupled with the Zone Status Mask Flag field, functions as a mask to enable IAS ACE
     * clients to get information about the Zone IDs whose ZoneStatus attribute is equal to any
     * of the bits indicated by the IAS ACE client in the Zone Status Mask field. The format of
     * this field is the same as the ZoneStatus attribute in the IAS Zone cluster. Per the Zone
     * Status Mask Flag field, IAS ACE servers shall respond with only the Zone IDs whose
     * ZoneStatus attributes are equal to at least one of the Zone Status bits set in the Zone
     * Status Mask field requested by the IAS ACE client.For example, if the Zone Status Mask
     * field set to “0x0003” would match IAS Zones whose ZoneStatus attributes are 0x0001,
     * 0x0002, and 0x0003.
     * <p>
     * In other words, if a logical 'AND' between the Zone Status Mask field and the IAS Zone’s
     * ZoneStatus attribute yields a non-zero result, the IAS ACE server shall include that
     * IAS Zone in the Get Zone Status Response command.
     */
    private Integer zoneStatusMask;

    /**
     * Default constructor.
     */
    public GetZoneStatusCommand() {
        genericCommand = false;
        clusterId = 0x0501;
        commandId = 9;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Starting Zone ID.
     * <p>
     * Specifies the starting Zone ID at which the IAS Client would like to obtain zone status
     * information.
     *
     * @return the Starting Zone ID
     */
    public Integer getStartingZoneId() {
        return startingZoneId;
    }

    /**
     * Sets Starting Zone ID.
     * <p>
     * Specifies the starting Zone ID at which the IAS Client would like to obtain zone status
     * information.
     *
     * @param startingZoneId the Starting Zone ID
     */
    public void setStartingZoneId(final Integer startingZoneId) {
        this.startingZoneId = startingZoneId;
    }

    /**
     * Gets Max Zone I Ds.
     * <p>
     * Specifies the maximum number of Zone IDs and corresponding Zone Statuses that are to be
     * returned by the IAS ACE server when it responds with a Get Zone Status Response command
     *
     * @return the Max Zone I Ds
     */
    public Integer getMaxZoneIDs() {
        return maxZoneIDs;
    }

    /**
     * Sets Max Zone I Ds.
     * <p>
     * Specifies the maximum number of Zone IDs and corresponding Zone Statuses that are to be
     * returned by the IAS ACE server when it responds with a Get Zone Status Response command
     *
     * @param maxZoneIDs the Max Zone I Ds
     */
    public void setMaxZoneIDs(final Integer maxZoneIDs) {
        this.maxZoneIDs = maxZoneIDs;
    }

    /**
     * Gets Zone Status Mask Flag.
     * <p>
     * Functions as a query operand with the Zone Status Mask field. If set to zero (i.e.,
     * FALSE), the IAS ACE server shall include all Zone IDs and their status, regardless of
     * their Zone Status when it responds with a Get Zone Status Response command. If set to one
     * (i.e., TRUE), the IAS ACE server shall include only those Zone IDs whose Zone Status
     * attribute is equal to one or more of the Zone Statuses requested in the Zone Status Mask
     * field of the Get Zone Status command.
     * <p>
     * Use of Zone Status Mask Flag and Zone Status Mask fields allow a client to obtain updated
     * information for the subset of Zone IDs they’re interested in, which is beneficial when
     * the number of IAS Zone devices in a system is large.
     *
     * @return the Zone Status Mask Flag
     */
    public Boolean getZoneStatusMaskFlag() {
        return zoneStatusMaskFlag;
    }

    /**
     * Sets Zone Status Mask Flag.
     * <p>
     * Functions as a query operand with the Zone Status Mask field. If set to zero (i.e.,
     * FALSE), the IAS ACE server shall include all Zone IDs and their status, regardless of
     * their Zone Status when it responds with a Get Zone Status Response command. If set to one
     * (i.e., TRUE), the IAS ACE server shall include only those Zone IDs whose Zone Status
     * attribute is equal to one or more of the Zone Statuses requested in the Zone Status Mask
     * field of the Get Zone Status command.
     * <p>
     * Use of Zone Status Mask Flag and Zone Status Mask fields allow a client to obtain updated
     * information for the subset of Zone IDs they’re interested in, which is beneficial when
     * the number of IAS Zone devices in a system is large.
     *
     * @param zoneStatusMaskFlag the Zone Status Mask Flag
     */
    public void setZoneStatusMaskFlag(final Boolean zoneStatusMaskFlag) {
        this.zoneStatusMaskFlag = zoneStatusMaskFlag;
    }

    /**
     * Gets Zone Status Mask.
     * <p>
     * Coupled with the Zone Status Mask Flag field, functions as a mask to enable IAS ACE
     * clients to get information about the Zone IDs whose ZoneStatus attribute is equal to any
     * of the bits indicated by the IAS ACE client in the Zone Status Mask field. The format of
     * this field is the same as the ZoneStatus attribute in the IAS Zone cluster. Per the Zone
     * Status Mask Flag field, IAS ACE servers shall respond with only the Zone IDs whose
     * ZoneStatus attributes are equal to at least one of the Zone Status bits set in the Zone
     * Status Mask field requested by the IAS ACE client.For example, if the Zone Status Mask
     * field set to “0x0003” would match IAS Zones whose ZoneStatus attributes are 0x0001,
     * 0x0002, and 0x0003.
     * <p>
     * In other words, if a logical 'AND' between the Zone Status Mask field and the IAS Zone’s
     * ZoneStatus attribute yields a non-zero result, the IAS ACE server shall include that
     * IAS Zone in the Get Zone Status Response command.
     *
     * @return the Zone Status Mask
     */
    public Integer getZoneStatusMask() {
        return zoneStatusMask;
    }

    /**
     * Sets Zone Status Mask.
     * <p>
     * Coupled with the Zone Status Mask Flag field, functions as a mask to enable IAS ACE
     * clients to get information about the Zone IDs whose ZoneStatus attribute is equal to any
     * of the bits indicated by the IAS ACE client in the Zone Status Mask field. The format of
     * this field is the same as the ZoneStatus attribute in the IAS Zone cluster. Per the Zone
     * Status Mask Flag field, IAS ACE servers shall respond with only the Zone IDs whose
     * ZoneStatus attributes are equal to at least one of the Zone Status bits set in the Zone
     * Status Mask field requested by the IAS ACE client.For example, if the Zone Status Mask
     * field set to “0x0003” would match IAS Zones whose ZoneStatus attributes are 0x0001,
     * 0x0002, and 0x0003.
     * <p>
     * In other words, if a logical 'AND' between the Zone Status Mask field and the IAS Zone’s
     * ZoneStatus attribute yields a non-zero result, the IAS ACE server shall include that
     * IAS Zone in the Get Zone Status Response command.
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
