/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Mirror Report Attribute Response value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command ID 0x09 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * FIXME: This command is sent in response to the ReportAttribute command when the
 * MirrorReporting attribute is set.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class MirrorReportAttributeResponse extends ZclMeteringCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0702;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x09;

    /**
     * Notification Scheme command message field.
     * <p>
     * An unsigned 8-bit integer that allows for the pre-loading of the Notification Flags bit
     * mapping to ZCL or Smart Energy Standard commands.
     */
    private Integer notificationScheme;

    /**
     * Notification Flags command message field.
     */
    private Integer notificationFlags;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public MirrorReportAttributeResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param notificationScheme {@link Integer} Notification Scheme
     * @param notificationFlags {@link Integer} Notification Flags
     */
    public MirrorReportAttributeResponse(
            Integer notificationScheme,
            Integer notificationFlags) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.notificationScheme = notificationScheme;
        this.notificationFlags = notificationFlags;
    }

    /**
     * Gets Notification Scheme.
     * <p>
     * An unsigned 8-bit integer that allows for the pre-loading of the Notification Flags bit
     * mapping to ZCL or Smart Energy Standard commands.
     *
     * @return the Notification Scheme
     */
    public Integer getNotificationScheme() {
        return notificationScheme;
    }

    /**
     * Sets Notification Scheme.
     * <p>
     * An unsigned 8-bit integer that allows for the pre-loading of the Notification Flags bit
     * mapping to ZCL or Smart Energy Standard commands.
     *
     * @param notificationScheme the Notification Scheme
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNotificationScheme(final Integer notificationScheme) {
        this.notificationScheme = notificationScheme;
    }

    /**
     * Gets Notification Flags.
     *
     * @return the Notification Flags
     */
    public Integer getNotificationFlags() {
        return notificationFlags;
    }

    /**
     * Sets Notification Flags.
     *
     * @param notificationFlags the Notification Flags
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNotificationFlags(final Integer notificationFlags) {
        this.notificationFlags = notificationFlags;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(notificationScheme, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(notificationFlags, ZclDataType.BITMAP_32_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        notificationScheme = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        notificationFlags = (Integer) deserializer.deserialize(ZclDataType.BITMAP_32_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(107);
        builder.append("MirrorReportAttributeResponse [");
        builder.append(super.toString());
        builder.append(", notificationScheme=");
        builder.append(notificationScheme);
        builder.append(", notificationFlags=");
        builder.append(notificationFlags);
        builder.append(']');
        return builder.toString();
    }

}
