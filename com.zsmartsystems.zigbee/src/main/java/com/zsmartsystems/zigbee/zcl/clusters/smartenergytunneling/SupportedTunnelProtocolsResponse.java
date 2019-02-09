/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.Protocol;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Supported Tunnel Protocols Response value object class.
 * <p>
 * Cluster: <b>Smart Energy Tunneling</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Smart Energy Tunneling cluster.
 * <p>
 * Supported Tunnel Protocol Response is sent in response to a Get Supported Tunnel Protocols
 * command previously received. The response contains a list of Tunnel protocols supported by
 * the device; the payload of the response should be capable of holding up to 16 protocols.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class SupportedTunnelProtocolsResponse extends ZclCommand {
    /**
     * Protocol List Complete command message field.
     * <p>
     * The Protocol List Complete field is a Boolean; a value of 0 indicates that there are more
     * supported protocols available (if more than 16 protocols are supported). A value of 1
     * indicates that the list of supported protocols is complete.
     */
    private Boolean protocolListComplete;

    /**
     * Protocol Count command message field.
     * <p>
     * The number of Protocol fields contained in the response.
     */
    private Integer protocolCount;

    /**
     * Protocol List command message field.
     */
    private Protocol protocolList;

    /**
     * Default constructor.
     */
    public SupportedTunnelProtocolsResponse() {
        genericCommand = false;
        clusterId = 0x0704;
        commandId = 5;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Protocol List Complete.
     * <p>
     * The Protocol List Complete field is a Boolean; a value of 0 indicates that there are more
     * supported protocols available (if more than 16 protocols are supported). A value of 1
     * indicates that the list of supported protocols is complete.
     *
     * @return the Protocol List Complete
     */
    public Boolean getProtocolListComplete() {
        return protocolListComplete;
    }

    /**
     * Sets Protocol List Complete.
     * <p>
     * The Protocol List Complete field is a Boolean; a value of 0 indicates that there are more
     * supported protocols available (if more than 16 protocols are supported). A value of 1
     * indicates that the list of supported protocols is complete.
     *
     * @param protocolListComplete the Protocol List Complete
     */
    public void setProtocolListComplete(final Boolean protocolListComplete) {
        this.protocolListComplete = protocolListComplete;
    }

    /**
     * Gets Protocol Count.
     * <p>
     * The number of Protocol fields contained in the response.
     *
     * @return the Protocol Count
     */
    public Integer getProtocolCount() {
        return protocolCount;
    }

    /**
     * Sets Protocol Count.
     * <p>
     * The number of Protocol fields contained in the response.
     *
     * @param protocolCount the Protocol Count
     */
    public void setProtocolCount(final Integer protocolCount) {
        this.protocolCount = protocolCount;
    }

    /**
     * Gets Protocol List.
     *
     * @return the Protocol List
     */
    public Protocol getProtocolList() {
        return protocolList;
    }

    /**
     * Sets Protocol List.
     *
     * @param protocolList the Protocol List
     */
    public void setProtocolList(final Protocol protocolList) {
        this.protocolList = protocolList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(protocolListComplete, ZclDataType.BOOLEAN);
        serializer.serialize(protocolCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        protocolList.serialize(serializer);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        protocolListComplete = (Boolean) deserializer.deserialize(ZclDataType.BOOLEAN);
        protocolCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        protocolList = new Protocol();
        protocolList.deserialize(deserializer);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(140);
        builder.append("SupportedTunnelProtocolsResponse [");
        builder.append(super.toString());
        builder.append(", protocolListComplete=");
        builder.append(protocolListComplete);
        builder.append(", protocolCount=");
        builder.append(protocolCount);
        builder.append(", protocolList=");
        builder.append(protocolList);
        builder.append(']');
        return builder.toString();
    }

}
