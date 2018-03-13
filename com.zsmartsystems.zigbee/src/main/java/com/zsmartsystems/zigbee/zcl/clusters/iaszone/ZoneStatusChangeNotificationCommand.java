/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iaszone;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Zone Status Change Notification Command value object class.
 * <p>
 * Cluster: <b>IAS Zone</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS Zone cluster.
 * <p>
 * The IAS Zone cluster defines an interface to the functionality of an IAS security
 * zone device. IAS Zone supports up to two alarm types per zone, low battery
 * reports and supervision of the IAS network.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class ZoneStatusChangeNotificationCommand extends ZclCommand {
    /**
     * Zone Status command message field.
     */
    private Integer zoneStatus;

    /**
     * Extended Status command message field.
     */
    private Integer extendedStatus;

    /**
     * Default constructor.
     */
    public ZoneStatusChangeNotificationCommand() {
        genericCommand = false;
        clusterId = 1280;
        commandId = 0;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
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
     */
    public void setZoneStatus(final Integer zoneStatus) {
        this.zoneStatus = zoneStatus;
    }

    /**
     * Gets Extended Status.
     *
     * @return the Extended Status
     */
    public Integer getExtendedStatus() {
        return extendedStatus;
    }

    /**
     * Sets Extended Status.
     *
     * @param extendedStatus the Extended Status
     */
    public void setExtendedStatus(final Integer extendedStatus) {
        this.extendedStatus = extendedStatus;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(zoneStatus, ZclDataType.ENUMERATION_16_BIT);
        serializer.serialize(extendedStatus, ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        zoneStatus = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_16_BIT);
        extendedStatus = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(102);
        builder.append("ZoneStatusChangeNotificationCommand [");
        builder.append(super.toString());
        builder.append(", zoneStatus=");
        builder.append(zoneStatus);
        builder.append(", extendedStatus=");
        builder.append(extendedStatus);
        builder.append(']');
        return builder.toString();
    }

}
