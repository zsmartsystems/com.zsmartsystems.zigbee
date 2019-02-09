/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Location Data Command value object class.
 * <p>
 * Cluster: <b>RSSI Location</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetLocationDataCommand extends ZclCommand {
    /**
     * Header command message field.
     */
    private Integer header;

    /**
     * Number Responses command message field.
     */
    private Integer numberResponses;

    /**
     * Target Address command message field.
     */
    private IeeeAddress targetAddress;

    /**
     * Default constructor.
     */
    public GetLocationDataCommand() {
        genericCommand = false;
        clusterId = 0x000B;
        commandId = 3;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Header.
     *
     * @return the Header
     */
    public Integer getHeader() {
        return header;
    }

    /**
     * Sets Header.
     *
     * @param header the Header
     */
    public void setHeader(final Integer header) {
        this.header = header;
    }

    /**
     * Gets Number Responses.
     *
     * @return the Number Responses
     */
    public Integer getNumberResponses() {
        return numberResponses;
    }

    /**
     * Sets Number Responses.
     *
     * @param numberResponses the Number Responses
     */
    public void setNumberResponses(final Integer numberResponses) {
        this.numberResponses = numberResponses;
    }

    /**
     * Gets Target Address.
     *
     * @return the Target Address
     */
    public IeeeAddress getTargetAddress() {
        return targetAddress;
    }

    /**
     * Sets Target Address.
     *
     * @param targetAddress the Target Address
     */
    public void setTargetAddress(final IeeeAddress targetAddress) {
        this.targetAddress = targetAddress;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(header, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(numberResponses, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(targetAddress, ZclDataType.IEEE_ADDRESS);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        header = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        numberResponses = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        targetAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(119);
        builder.append("GetLocationDataCommand [");
        builder.append(super.toString());
        builder.append(", header=");
        builder.append(header);
        builder.append(", numberResponses=");
        builder.append(numberResponses);
        builder.append(", targetAddress=");
        builder.append(targetAddress);
        builder.append(']');
        return builder.toString();
    }

}
