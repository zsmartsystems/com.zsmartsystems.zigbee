/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-07-04T21:54:11Z")
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
     */
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
     */
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
     */
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
     */
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
     */
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
     */
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
     */
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
        index = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        gpdCommandId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        endpoint = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        profile = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        cluster = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        zigbeeCommandId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        zigbeeCommandPayload = (ByteArray) deserializer.deserialize(ZclDataType.OCTET_STRING);
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
