/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ManagementNetworkDiscovery extends ZdoRequest {
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
     */
    public ManagementNetworkDiscovery() {
        clusterId = 0x0030;
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
     */
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
     */
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
     */
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

        scanChannels = (Integer) deserializer.deserialize(ZclDataType.BITMAP_32_BIT);
        scanDuration = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        startIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(123);
        builder.append("ManagementNetworkDiscovery [");
        builder.append(super.toString());
        builder.append(", scanChannels=");
        builder.append(scanChannels);
        builder.append(", scanDuration=");
        builder.append(scanDuration);
        builder.append(", startIndex=");
        builder.append(startIndex);
        builder.append(']');
        return builder.toString();
    }

}
