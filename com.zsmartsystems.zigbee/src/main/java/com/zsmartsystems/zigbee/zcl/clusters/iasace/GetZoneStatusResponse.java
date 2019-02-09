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
 * Get Zone Status Response value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * This command updates requesting IAS ACE clients in the system of changes to the IAS Zone
 * server statuses recorded by the ACE server (e.g., IAS CIE device).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetZoneStatusResponse extends ZclCommand {
    /**
     * Zone Status Complete command message field.
     * <p>
     * Indicates whether there are additional Zone IDs managed by the IAS ACE Server with Zone
     * Status information to be obtained. A value of zero (i.e. FALSE) indicates there are
     * additional Zone IDs for which Zone Status information is available and that the IAS ACE
     * client should send another Get Zone Status command.A value of one (i.e. TRUE) indicates
     * there are no more Zone IDs for the IAS ACE client to query and the IAS ACE client has
     * received all the Zone Status information for all IAS Zones managed by the IAS ACE server.
     * <p>
     * The IAS ACE client should NOT typically send another Get Zone Status command.
     */
    private Boolean zoneStatusComplete;

    /**
     * Number Of Zones command message field.
     */
    private Integer numberOfZones;

    /**
     * IAS ACE Zone Status command message field.
     */
    private Integer iasAceZoneStatus;

    /**
     * Zone ID command message field.
     */
    private Integer zoneId;

    /**
     * Zone Status command message field.
     */
    private Integer zoneStatus;

    /**
     * Default constructor.
     */
    public GetZoneStatusResponse() {
        genericCommand = false;
        clusterId = 0x0501;
        commandId = 8;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Zone Status Complete.
     * <p>
     * Indicates whether there are additional Zone IDs managed by the IAS ACE Server with Zone
     * Status information to be obtained. A value of zero (i.e. FALSE) indicates there are
     * additional Zone IDs for which Zone Status information is available and that the IAS ACE
     * client should send another Get Zone Status command.A value of one (i.e. TRUE) indicates
     * there are no more Zone IDs for the IAS ACE client to query and the IAS ACE client has
     * received all the Zone Status information for all IAS Zones managed by the IAS ACE server.
     * <p>
     * The IAS ACE client should NOT typically send another Get Zone Status command.
     *
     * @return the Zone Status Complete
     */
    public Boolean getZoneStatusComplete() {
        return zoneStatusComplete;
    }

    /**
     * Sets Zone Status Complete.
     * <p>
     * Indicates whether there are additional Zone IDs managed by the IAS ACE Server with Zone
     * Status information to be obtained. A value of zero (i.e. FALSE) indicates there are
     * additional Zone IDs for which Zone Status information is available and that the IAS ACE
     * client should send another Get Zone Status command.A value of one (i.e. TRUE) indicates
     * there are no more Zone IDs for the IAS ACE client to query and the IAS ACE client has
     * received all the Zone Status information for all IAS Zones managed by the IAS ACE server.
     * <p>
     * The IAS ACE client should NOT typically send another Get Zone Status command.
     *
     * @param zoneStatusComplete the Zone Status Complete
     */
    public void setZoneStatusComplete(final Boolean zoneStatusComplete) {
        this.zoneStatusComplete = zoneStatusComplete;
    }

    /**
     * Gets Number Of Zones.
     *
     * @return the Number Of Zones
     */
    public Integer getNumberOfZones() {
        return numberOfZones;
    }

    /**
     * Sets Number Of Zones.
     *
     * @param numberOfZones the Number Of Zones
     */
    public void setNumberOfZones(final Integer numberOfZones) {
        this.numberOfZones = numberOfZones;
    }

    /**
     * Gets IAS ACE Zone Status.
     *
     * @return the IAS ACE Zone Status
     */
    public Integer getIasAceZoneStatus() {
        return iasAceZoneStatus;
    }

    /**
     * Sets IAS ACE Zone Status.
     *
     * @param iasAceZoneStatus the IAS ACE Zone Status
     */
    public void setIasAceZoneStatus(final Integer iasAceZoneStatus) {
        this.iasAceZoneStatus = iasAceZoneStatus;
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
     * Gets Zone Status.
     *
     * @return the Zone Status
     */
    public Integer getZoneStatus() {
        return zoneStatus;
    }

    /**
     * Sets Zone Status.
     *
     * @param zoneStatus the Zone Status
     */
    public void setZoneStatus(final Integer zoneStatus) {
        this.zoneStatus = zoneStatus;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(zoneStatusComplete, ZclDataType.BOOLEAN);
        serializer.serialize(numberOfZones, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(iasAceZoneStatus, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(zoneId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(zoneStatus, ZclDataType.BITMAP_16_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        zoneStatusComplete = (Boolean) deserializer.deserialize(ZclDataType.BOOLEAN);
        numberOfZones = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        iasAceZoneStatus = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        zoneId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        zoneStatus = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(187);
        builder.append("GetZoneStatusResponse [");
        builder.append(super.toString());
        builder.append(", zoneStatusComplete=");
        builder.append(zoneStatusComplete);
        builder.append(", numberOfZones=");
        builder.append(numberOfZones);
        builder.append(", iasAceZoneStatus=");
        builder.append(iasAceZoneStatus);
        builder.append(", zoneId=");
        builder.append(zoneId);
        builder.append(", zoneStatus=");
        builder.append(zoneStatus);
        builder.append(']');
        return builder.toString();
    }

}
