/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * RSSI Ping Command value object class.
 * <p>
 * Cluster: <b>RSSI Location</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class RssiPingCommand extends ZclCommand {
    /**
     * Location Type command message field.
     */
    private Integer locationType;

    /**
     * Default constructor.
     */
    public RssiPingCommand() {
        genericCommand = false;
        clusterId = 0x000B;
        commandId = 4;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Location Type.
     *
     * @return the Location Type
     */
    public Integer getLocationType() {
        return locationType;
    }

    /**
     * Sets Location Type.
     *
     * @param locationType the Location Type
     */
    public void setLocationType(final Integer locationType) {
        this.locationType = locationType;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(locationType, ZclDataType.DATA_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        locationType = (Integer) deserializer.deserialize(ZclDataType.DATA_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(50);
        builder.append("RssiPingCommand [");
        builder.append(super.toString());
        builder.append(", locationType=");
        builder.append(locationType);
        builder.append(']');
        return builder.toString();
    }

}
