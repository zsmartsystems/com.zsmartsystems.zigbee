/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Zone Status Response value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command ID 0x08 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * This command updates requesting IAS ACE clients in the system of changes to the IAS Zone
 * server statuses recorded by the ACE server (e.g., IAS CIE device).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:33:13Z")
public class GetZoneStatusResponse extends ZclIasAceCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0501;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x08;

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
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public GetZoneStatusResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param zoneStatusComplete {@link Boolean} Zone Status Complete
     * @param numberOfZones {@link Integer} Number Of Zones
     * @param iasAceZoneStatus {@link Integer} IAS ACE Zone Status
     * @param zoneId {@link Integer} Zone ID
     * @param zoneStatus {@link Integer} Zone Status
     */
    public GetZoneStatusResponse(
            Boolean zoneStatusComplete,
            Integer numberOfZones,
            Integer iasAceZoneStatus,
            Integer zoneId,
            Integer zoneStatus) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.zoneStatusComplete = zoneStatusComplete;
        this.numberOfZones = numberOfZones;
        this.iasAceZoneStatus = iasAceZoneStatus;
        this.zoneId = zoneId;
        this.zoneStatus = zoneStatus;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
