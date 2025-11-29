/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpPairingConfigurationGroupList;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Gp Pairing Configuration value object class.
 * <p>
 * Cluster: <b>Green Power</b>. Command ID 0x09 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Green Power cluster.
 * <p>
 * The command is generated to configure the Sink Table of a sink, to
 * create/update/replace/remove a pairing to a GPD and/or trigger the sending of GP Pairing
 * command.
 * <p>
 * In the current version of the specification, a device shall only send GP Pairing
 * Configuration command with the Number of paired endpoints field set to 0xfe, if the
 * CommunicationMode is equal to Pre-Commissioned Groupcast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GpPairingConfiguration extends ZclGreenPowerCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0021;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x09;

    /**
     * Actions command message field.
     */
    private Integer actions;

    /**
     * Options command message field.
     */
    private Integer options;

    /**
     * Gpd Src ID command message field.
     */
    private Integer gpdSrcId;

    /**
     * Gpd IEEE command message field.
     */
    private IeeeAddress gpdIeee;

    /**
     * Endpoint command message field.
     */
    private Integer endpoint;

    /**
     * Device ID command message field.
     */
    private Integer deviceId;

    /**
     * Group List Count command message field.
     */
    private Integer groupListCount;

    /**
     * Group List command message field.
     */
    private GpPairingConfigurationGroupList groupList;

    /**
     * Gpd Assigned Alias command message field.
     */
    private Integer gpdAssignedAlias;

    /**
     * Forwarding Radius command message field.
     */
    private Integer forwardingRadius;

    /**
     * Security Options command message field.
     */
    private Integer securityOptions;

    /**
     * Gpd Security Frame Counter command message field.
     */
    private Integer gpdSecurityFrameCounter;

    /**
     * Gpd Security Key command message field.
     */
    private ZigBeeKey gpdSecurityKey;

    /**
     * Number Of Paired Endpoints command message field.
     */
    private Integer numberOfPairedEndpoints;

    /**
     * Paired Endpoints command message field.
     */
    private Integer pairedEndpoints;

    /**
     * Application Information command message field.
     */
    private Integer applicationInformation;

    /**
     * Manufacturer ID command message field.
     */
    private Integer manufacturerId;

    /**
     * Mode ID command message field.
     */
    private Integer modeId;

    /**
     * Number Of Gpd Commands command message field.
     */
    private Integer numberOfGpdCommands;

    /**
     * Gpd Command ID List command message field.
     */
    private Integer gpdCommandIdList;

    /**
     * Cluster ID List Count command message field.
     */
    private Integer clusterIdListCount;

    /**
     * Cluster List Server command message field.
     */
    private Integer clusterListServer;

    /**
     * Cluster List Client command message field.
     */
    private Integer clusterListClient;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GpPairingConfiguration() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param actions {@link Integer} Actions
     * @param options {@link Integer} Options
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param endpoint {@link Integer} Endpoint
     * @param deviceId {@link Integer} Device ID
     * @param groupListCount {@link Integer} Group List Count
     * @param groupList {@link GpPairingConfigurationGroupList} Group List
     * @param gpdAssignedAlias {@link Integer} Gpd Assigned Alias
     * @param forwardingRadius {@link Integer} Forwarding Radius
     * @param securityOptions {@link Integer} Security Options
     * @param gpdSecurityFrameCounter {@link Integer} Gpd Security Frame Counter
     * @param gpdSecurityKey {@link ZigBeeKey} Gpd Security Key
     * @param numberOfPairedEndpoints {@link Integer} Number Of Paired Endpoints
     * @param pairedEndpoints {@link Integer} Paired Endpoints
     * @param applicationInformation {@link Integer} Application Information
     * @param manufacturerId {@link Integer} Manufacturer ID
     * @param modeId {@link Integer} Mode ID
     * @param numberOfGpdCommands {@link Integer} Number Of Gpd Commands
     * @param gpdCommandIdList {@link Integer} Gpd Command ID List
     * @param clusterIdListCount {@link Integer} Cluster ID List Count
     * @param clusterListServer {@link Integer} Cluster List Server
     * @param clusterListClient {@link Integer} Cluster List Client
     */
    public GpPairingConfiguration(
            Integer actions,
            Integer options,
            Integer gpdSrcId,
            IeeeAddress gpdIeee,
            Integer endpoint,
            Integer deviceId,
            Integer groupListCount,
            GpPairingConfigurationGroupList groupList,
            Integer gpdAssignedAlias,
            Integer forwardingRadius,
            Integer securityOptions,
            Integer gpdSecurityFrameCounter,
            ZigBeeKey gpdSecurityKey,
            Integer numberOfPairedEndpoints,
            Integer pairedEndpoints,
            Integer applicationInformation,
            Integer manufacturerId,
            Integer modeId,
            Integer numberOfGpdCommands,
            Integer gpdCommandIdList,
            Integer clusterIdListCount,
            Integer clusterListServer,
            Integer clusterListClient) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.actions = actions;
        this.options = options;
        this.gpdSrcId = gpdSrcId;
        this.gpdIeee = gpdIeee;
        this.endpoint = endpoint;
        this.deviceId = deviceId;
        this.groupListCount = groupListCount;
        this.groupList = groupList;
        this.gpdAssignedAlias = gpdAssignedAlias;
        this.forwardingRadius = forwardingRadius;
        this.securityOptions = securityOptions;
        this.gpdSecurityFrameCounter = gpdSecurityFrameCounter;
        this.gpdSecurityKey = gpdSecurityKey;
        this.numberOfPairedEndpoints = numberOfPairedEndpoints;
        this.pairedEndpoints = pairedEndpoints;
        this.applicationInformation = applicationInformation;
        this.manufacturerId = manufacturerId;
        this.modeId = modeId;
        this.numberOfGpdCommands = numberOfGpdCommands;
        this.gpdCommandIdList = gpdCommandIdList;
        this.clusterIdListCount = clusterIdListCount;
        this.clusterListServer = clusterListServer;
        this.clusterListClient = clusterListClient;
    }

    /**
     * Gets Actions.
     *
     * @return the Actions
     */
    public Integer getActions() {
        return actions;
    }

    /**
     * Sets Actions.
     *
     * @param actions the Actions
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setActions(final Integer actions) {
        this.actions = actions;
    }

    /**
     * Gets Options.
     *
     * @return the Options
     */
    public Integer getOptions() {
        return options;
    }

    /**
     * Sets Options.
     *
     * @param options the Options
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setOptions(final Integer options) {
        this.options = options;
    }

    /**
     * Gets Gpd Src ID.
     *
     * @return the Gpd Src ID
     */
    public Integer getGpdSrcId() {
        return gpdSrcId;
    }

    /**
     * Sets Gpd Src ID.
     *
     * @param gpdSrcId the Gpd Src ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGpdSrcId(final Integer gpdSrcId) {
        this.gpdSrcId = gpdSrcId;
    }

    /**
     * Gets Gpd IEEE.
     *
     * @return the Gpd IEEE
     */
    public IeeeAddress getGpdIeee() {
        return gpdIeee;
    }

    /**
     * Sets Gpd IEEE.
     *
     * @param gpdIeee the Gpd IEEE
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGpdIeee(final IeeeAddress gpdIeee) {
        this.gpdIeee = gpdIeee;
    }

    /**
     * Gets Endpoint.
     *
     * @return the Endpoint
     */
    public Integer getEndpoint() {
        return endpoint;
    }

    /**
     * Sets Endpoint.
     *
     * @param endpoint the Endpoint
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setEndpoint(final Integer endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Gets Device ID.
     *
     * @return the Device ID
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     * Sets Device ID.
     *
     * @param deviceId the Device ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setDeviceId(final Integer deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Gets Group List Count.
     *
     * @return the Group List Count
     */
    public Integer getGroupListCount() {
        return groupListCount;
    }

    /**
     * Sets Group List Count.
     *
     * @param groupListCount the Group List Count
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGroupListCount(final Integer groupListCount) {
        this.groupListCount = groupListCount;
    }

    /**
     * Gets Group List.
     *
     * @return the Group List
     */
    public GpPairingConfigurationGroupList getGroupList() {
        return groupList;
    }

    /**
     * Sets Group List.
     *
     * @param groupList the Group List
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGroupList(final GpPairingConfigurationGroupList groupList) {
        this.groupList = groupList;
    }

    /**
     * Gets Gpd Assigned Alias.
     *
     * @return the Gpd Assigned Alias
     */
    public Integer getGpdAssignedAlias() {
        return gpdAssignedAlias;
    }

    /**
     * Sets Gpd Assigned Alias.
     *
     * @param gpdAssignedAlias the Gpd Assigned Alias
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGpdAssignedAlias(final Integer gpdAssignedAlias) {
        this.gpdAssignedAlias = gpdAssignedAlias;
    }

    /**
     * Gets Forwarding Radius.
     *
     * @return the Forwarding Radius
     */
    public Integer getForwardingRadius() {
        return forwardingRadius;
    }

    /**
     * Sets Forwarding Radius.
     *
     * @param forwardingRadius the Forwarding Radius
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setForwardingRadius(final Integer forwardingRadius) {
        this.forwardingRadius = forwardingRadius;
    }

    /**
     * Gets Security Options.
     *
     * @return the Security Options
     */
    public Integer getSecurityOptions() {
        return securityOptions;
    }

    /**
     * Sets Security Options.
     *
     * @param securityOptions the Security Options
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSecurityOptions(final Integer securityOptions) {
        this.securityOptions = securityOptions;
    }

    /**
     * Gets Gpd Security Frame Counter.
     *
     * @return the Gpd Security Frame Counter
     */
    public Integer getGpdSecurityFrameCounter() {
        return gpdSecurityFrameCounter;
    }

    /**
     * Sets Gpd Security Frame Counter.
     *
     * @param gpdSecurityFrameCounter the Gpd Security Frame Counter
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGpdSecurityFrameCounter(final Integer gpdSecurityFrameCounter) {
        this.gpdSecurityFrameCounter = gpdSecurityFrameCounter;
    }

    /**
     * Gets Gpd Security Key.
     *
     * @return the Gpd Security Key
     */
    public ZigBeeKey getGpdSecurityKey() {
        return gpdSecurityKey;
    }

    /**
     * Sets Gpd Security Key.
     *
     * @param gpdSecurityKey the Gpd Security Key
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGpdSecurityKey(final ZigBeeKey gpdSecurityKey) {
        this.gpdSecurityKey = gpdSecurityKey;
    }

    /**
     * Gets Number Of Paired Endpoints.
     *
     * @return the Number Of Paired Endpoints
     */
    public Integer getNumberOfPairedEndpoints() {
        return numberOfPairedEndpoints;
    }

    /**
     * Sets Number Of Paired Endpoints.
     *
     * @param numberOfPairedEndpoints the Number Of Paired Endpoints
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNumberOfPairedEndpoints(final Integer numberOfPairedEndpoints) {
        this.numberOfPairedEndpoints = numberOfPairedEndpoints;
    }

    /**
     * Gets Paired Endpoints.
     *
     * @return the Paired Endpoints
     */
    public Integer getPairedEndpoints() {
        return pairedEndpoints;
    }

    /**
     * Sets Paired Endpoints.
     *
     * @param pairedEndpoints the Paired Endpoints
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setPairedEndpoints(final Integer pairedEndpoints) {
        this.pairedEndpoints = pairedEndpoints;
    }

    /**
     * Gets Application Information.
     *
     * @return the Application Information
     */
    public Integer getApplicationInformation() {
        return applicationInformation;
    }

    /**
     * Sets Application Information.
     *
     * @param applicationInformation the Application Information
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setApplicationInformation(final Integer applicationInformation) {
        this.applicationInformation = applicationInformation;
    }

    /**
     * Gets Manufacturer ID.
     *
     * @return the Manufacturer ID
     */
    public Integer getManufacturerId() {
        return manufacturerId;
    }

    /**
     * Sets Manufacturer ID.
     *
     * @param manufacturerId the Manufacturer ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setManufacturerId(final Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * Gets Mode ID.
     *
     * @return the Mode ID
     */
    public Integer getModeId() {
        return modeId;
    }

    /**
     * Sets Mode ID.
     *
     * @param modeId the Mode ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setModeId(final Integer modeId) {
        this.modeId = modeId;
    }

    /**
     * Gets Number Of Gpd Commands.
     *
     * @return the Number Of Gpd Commands
     */
    public Integer getNumberOfGpdCommands() {
        return numberOfGpdCommands;
    }

    /**
     * Sets Number Of Gpd Commands.
     *
     * @param numberOfGpdCommands the Number Of Gpd Commands
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNumberOfGpdCommands(final Integer numberOfGpdCommands) {
        this.numberOfGpdCommands = numberOfGpdCommands;
    }

    /**
     * Gets Gpd Command ID List.
     *
     * @return the Gpd Command ID List
     */
    public Integer getGpdCommandIdList() {
        return gpdCommandIdList;
    }

    /**
     * Sets Gpd Command ID List.
     *
     * @param gpdCommandIdList the Gpd Command ID List
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGpdCommandIdList(final Integer gpdCommandIdList) {
        this.gpdCommandIdList = gpdCommandIdList;
    }

    /**
     * Gets Cluster ID List Count.
     *
     * @return the Cluster ID List Count
     */
    public Integer getClusterIdListCount() {
        return clusterIdListCount;
    }

    /**
     * Sets Cluster ID List Count.
     *
     * @param clusterIdListCount the Cluster ID List Count
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setClusterIdListCount(final Integer clusterIdListCount) {
        this.clusterIdListCount = clusterIdListCount;
    }

    /**
     * Gets Cluster List Server.
     *
     * @return the Cluster List Server
     */
    public Integer getClusterListServer() {
        return clusterListServer;
    }

    /**
     * Sets Cluster List Server.
     *
     * @param clusterListServer the Cluster List Server
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setClusterListServer(final Integer clusterListServer) {
        this.clusterListServer = clusterListServer;
    }

    /**
     * Gets Cluster List Client.
     *
     * @return the Cluster List Client
     */
    public Integer getClusterListClient() {
        return clusterListClient;
    }

    /**
     * Sets Cluster List Client.
     *
     * @param clusterListClient the Cluster List Client
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setClusterListClient(final Integer clusterListClient) {
        this.clusterListClient = clusterListClient;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(actions, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(options, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(gpdSrcId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(gpdIeee, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(endpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(deviceId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(groupListCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        groupList.serialize(serializer);
        serializer.serialize(gpdAssignedAlias, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(forwardingRadius, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(securityOptions, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(gpdSecurityFrameCounter, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(gpdSecurityKey, ZclDataType.SECURITY_KEY);
        serializer.serialize(numberOfPairedEndpoints, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(pairedEndpoints, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(applicationInformation, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(manufacturerId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(modeId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(numberOfGpdCommands, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(gpdCommandIdList, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(clusterIdListCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(clusterListServer, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(clusterListClient, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        actions = deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        options = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        gpdSrcId = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        gpdIeee = deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        endpoint = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        deviceId = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        groupListCount = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        groupList = new GpPairingConfigurationGroupList();
        groupList.deserialize(deserializer);
        gpdAssignedAlias = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        forwardingRadius = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        securityOptions = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        gpdSecurityFrameCounter = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        gpdSecurityKey = deserializer.deserialize(ZclDataType.SECURITY_KEY);
        numberOfPairedEndpoints = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        pairedEndpoints = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        applicationInformation = deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        manufacturerId = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        modeId = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        numberOfGpdCommands = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        gpdCommandIdList = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        clusterIdListCount = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        clusterListServer = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        clusterListClient = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(804);
        builder.append("GpPairingConfiguration [");
        builder.append(super.toString());
        builder.append(", actions=");
        builder.append(actions);
        builder.append(", options=");
        builder.append(options);
        builder.append(", gpdSrcId=");
        builder.append(gpdSrcId);
        builder.append(", gpdIeee=");
        builder.append(gpdIeee);
        builder.append(", endpoint=");
        builder.append(endpoint);
        builder.append(", deviceId=");
        builder.append(deviceId);
        builder.append(", groupListCount=");
        builder.append(groupListCount);
        builder.append(", groupList=");
        builder.append(groupList);
        builder.append(", gpdAssignedAlias=");
        builder.append(gpdAssignedAlias);
        builder.append(", forwardingRadius=");
        builder.append(forwardingRadius);
        builder.append(", securityOptions=");
        builder.append(securityOptions);
        builder.append(", gpdSecurityFrameCounter=");
        builder.append(gpdSecurityFrameCounter);
        builder.append(", gpdSecurityKey=");
        builder.append(gpdSecurityKey);
        builder.append(", numberOfPairedEndpoints=");
        builder.append(numberOfPairedEndpoints);
        builder.append(", pairedEndpoints=");
        builder.append(pairedEndpoints);
        builder.append(", applicationInformation=");
        builder.append(applicationInformation);
        builder.append(", manufacturerId=");
        builder.append(manufacturerId);
        builder.append(", modeId=");
        builder.append(modeId);
        builder.append(", numberOfGpdCommands=");
        builder.append(numberOfGpdCommands);
        builder.append(", gpdCommandIdList=");
        builder.append(gpdCommandIdList);
        builder.append(", clusterIdListCount=");
        builder.append(clusterIdListCount);
        builder.append(", clusterListServer=");
        builder.append(clusterListServer);
        builder.append(", clusterListClient=");
        builder.append(clusterListClient);
        builder.append(']');
        return builder.toString();
    }

}
