/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Management Network Discovery value object class.
 * <p>
 * <p>
 * The Mgmt_NWK_Disc_req is generated from a Local Device requesting that the Remote Device
 * execute a Scan to report back networks in the vicinity of the Local Device. The destination
 * addressing on this command shall be unicast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class ManagementNetworkDiscovery extends ZdoRequest {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x0030;

    /**
     * Scan Channels command message field.
     */
    private Integer scanChannels;

    /**
     * Scan Duration command message field.
     */
    private Integer scanDuration;

    /**
     * Start Index command message field.
     */
    private Integer startIndex;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ManagementNetworkDiscovery() {
        clusterId = CLUSTER_ID;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param scanChannels {@link Integer} Scan Channels
     * @param scanDuration {@link Integer} Scan Duration
     * @param startIndex {@link Integer} Start Index
     */
    public ManagementNetworkDiscovery(
            Integer scanChannels,
            Integer scanDuration,
            Integer startIndex) {

        clusterId = CLUSTER_ID;

        this.scanChannels = scanChannels;
        this.scanDuration = scanDuration;
        this.startIndex = startIndex;
    }

    /**
     * Gets Scan Channels.
     *
     * @return the Scan Channels
     */
    public Integer getScanChannels() {
        return scanChannels;
    }

    /**
     * Sets Scan Channels.
     *
     * @param scanChannels the Scan Channels
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setScanChannels(final Integer scanChannels) {
        this.scanChannels = scanChannels;
    }

    /**
     * Gets Scan Duration.
     *
     * @return the Scan Duration
     */
    public Integer getScanDuration() {
        return scanDuration;
    }

    /**
     * Sets Scan Duration.
     *
     * @param scanDuration the Scan Duration
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setScanDuration(final Integer scanDuration) {
        this.scanDuration = scanDuration;
    }

    /**
     * Gets Start Index.
     *
     * @return the Start Index
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * Sets Start Index.
     *
     * @param startIndex the Start Index
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStartIndex(final Integer startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(scanChannels, ZclDataType.BITMAP_32_BIT);
        serializer.serialize(scanDuration, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(startIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        scanChannels = deserializer.deserialize(ZclDataType.BITMAP_32_BIT);
        scanDuration = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        startIndex = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(123);
        builder.append("ManagementNetworkDiscovery [");
        builder.append(super.toString());
        builder.append(", scanChannels=");
        builder.append(String.format("%08X", scanChannels));
        builder.append(", scanDuration=");
        builder.append(scanDuration);
        builder.append(", startIndex=");
        builder.append(startIndex);
        builder.append(']');
        return builder.toString();
    }

}
