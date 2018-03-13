/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iaswd;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Start Warning Command value object class.
 * <p>
 * This command starts the WD operation. The WD alerts the surrounding area by
 * audible (siren) and visual (strobe) signals.
 * <br>
 * A Start Warning command shall always terminate the effect of any previous
 * command that is still current.
 * <p>
 * Cluster: <b>IAS WD</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS WD cluster.
 * <p>
 * The IAS WD cluster provides an interface to the functionality of any Warning
 * Device equipment of the IAS system. Using this cluster, a ZigBee enabled CIE
 * device can access a ZigBee enabled IAS WD device and issue alarm warning
 * indications (siren, strobe lighting, etc.) when a system alarm condition is detected.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class StartWarningCommand extends ZclCommand {
    /**
     * Header command message field.
     */
    private Integer header;

    /**
     * Warning duration command message field.
     */
    private Integer warningDuration;

    /**
     * Default constructor.
     */
    public StartWarningCommand() {
        genericCommand = false;
        clusterId = 1282;
        commandId = 0;
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
     * Gets Warning duration.
     *
     * @return the Warning duration
     */
    public Integer getWarningDuration() {
        return warningDuration;
    }

    /**
     * Sets Warning duration.
     *
     * @param warningDuration the Warning duration
     */
    public void setWarningDuration(final Integer warningDuration) {
        this.warningDuration = warningDuration;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(header, ZclDataType.DATA_8_BIT);
        serializer.serialize(warningDuration, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        header = (Integer) deserializer.deserialize(ZclDataType.DATA_8_BIT);
        warningDuration = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(83);
        builder.append("StartWarningCommand [");
        builder.append(super.toString());
        builder.append(", header=");
        builder.append(header);
        builder.append(", warningDuration=");
        builder.append(warningDuration);
        builder.append(']');
        return builder.toString();
    }

}
