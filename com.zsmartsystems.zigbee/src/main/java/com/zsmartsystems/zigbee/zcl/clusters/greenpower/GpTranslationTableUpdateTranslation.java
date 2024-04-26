/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;
import javax.annotation.Generated;

import com.zsmartsystems.zigbee.serialization.ZigBeeSerializable;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Gp Translation Table Update Translation structure implementation.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:02:05Z")
public class GpTranslationTableUpdateTranslation implements ZigBeeSerializable {
    /**
     * Index structure field.
     */
    private Integer index;

    /**
     * Gpd Command ID structure field.
     */
    private Integer gpdCommandId;

    /**
     * Endpoint structure field.
     */
    private Integer endpoint;

    /**
     * Profile structure field.
     */
    private Integer profile;

    /**
     * Cluster structure field.
     */
    private Integer cluster;

    /**
     * Zigbee Command ID structure field.
     */
    private Integer zigbeeCommandId;

    /**
     * Zigbee Command Payload structure field.
     */
    private ByteArray zigbeeCommandPayload;


    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public GpTranslationTableUpdateTranslation() {
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param index {@link Integer} Index
     * @param gpdCommandId {@link Integer} Gpd Command ID
     * @param endpoint {@link Integer} Endpoint
     * @param profile {@link Integer} Profile
     * @param cluster {@link Integer} Cluster
     * @param zigbeeCommandId {@link Integer} Zigbee Command ID
     * @param zigbeeCommandPayload {@link ByteArray} Zigbee Command Payload
     */
    public GpTranslationTableUpdateTranslation(
            Integer index,
            Integer gpdCommandId,
            Integer endpoint,
            Integer profile,
            Integer cluster,
            Integer zigbeeCommandId,
            ByteArray zigbeeCommandPayload) {

        this.index = index;
        this.gpdCommandId = gpdCommandId;
        this.endpoint = endpoint;
        this.profile = profile;
        this.cluster = cluster;
        this.zigbeeCommandId = zigbeeCommandId;
        this.zigbeeCommandPayload = zigbeeCommandPayload;
    }

    /**
     * Gets Index.
     *
     * @return the Index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Sets Index.
     *
     * @param index the Index
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setIndex(final Integer index) {
        this.index = index;
    }

    /**
     * Gets Gpd Command ID.
     *
     * @return the Gpd Command ID
     */
    public Integer getGpdCommandId() {
        return gpdCommandId;
    }

    /**
     * Sets Gpd Command ID.
     *
     * @param gpdCommandId the Gpd Command ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGpdCommandId(final Integer gpdCommandId) {
        this.gpdCommandId = gpdCommandId;
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
     * Gets Profile.
     *
     * @return the Profile
     */
    public Integer getProfile() {
        return profile;
    }

    /**
     * Sets Profile.
     *
     * @param profile the Profile
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setProfile(final Integer profile) {
        this.profile = profile;
    }

    /**
     * Gets Cluster.
     *
     * @return the Cluster
     */
    public Integer getCluster() {
        return cluster;
    }

    /**
     * Sets Cluster.
     *
     * @param cluster the Cluster
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCluster(final Integer cluster) {
        this.cluster = cluster;
    }

    /**
     * Gets Zigbee Command ID.
     *
     * @return the Zigbee Command ID
     */
    public Integer getZigbeeCommandId() {
        return zigbeeCommandId;
    }

    /**
     * Sets Zigbee Command ID.
     *
     * @param zigbeeCommandId the Zigbee Command ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZigbeeCommandId(final Integer zigbeeCommandId) {
        this.zigbeeCommandId = zigbeeCommandId;
    }

    /**
     * Gets Zigbee Command Payload.
     *
     * @return the Zigbee Command Payload
     */
    public ByteArray getZigbeeCommandPayload() {
        return zigbeeCommandPayload;
    }

    /**
     * Sets Zigbee Command Payload.
     *
     * @param zigbeeCommandPayload the Zigbee Command Payload
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZigbeeCommandPayload(final ByteArray zigbeeCommandPayload) {
        this.zigbeeCommandPayload = zigbeeCommandPayload;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(index, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(gpdCommandId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(endpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(profile, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(cluster, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(zigbeeCommandId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(zigbeeCommandPayload, ZclDataType.OCTET_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        index = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        gpdCommandId = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        endpoint = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        profile = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        cluster = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        zigbeeCommandId = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        zigbeeCommandPayload = deserializer.deserialize(ZclDataType.OCTET_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(252);
        builder.append("GpTranslationTableUpdateTranslation [");
        builder.append(super.toString());
        builder.append(", index=");
        builder.append(index);
        builder.append(", gpdCommandId=");
        builder.append(gpdCommandId);
        builder.append(", endpoint=");
        builder.append(endpoint);
        builder.append(", profile=");
        builder.append(profile);
        builder.append(", cluster=");
        builder.append(cluster);
        builder.append(", zigbeeCommandId=");
        builder.append(zigbeeCommandId);
        builder.append(", zigbeeCommandPayload=");
        builder.append(zigbeeCommandPayload);
        builder.append(']');
        return builder.toString();
    }
}
