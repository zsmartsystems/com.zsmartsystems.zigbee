/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.commissioning;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Restore Startup Parameters Command value object class.
 * <p>
 * Cluster: <b>Commissioning</b>. Command ID 0x02 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Commissioning cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-10T12:07:00Z")
public class RestoreStartupParametersCommand extends ZclCommissioningCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0015;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Option command message field.
     */
    private Integer option;

    /**
     * Index command message field.
     */
    private Integer index;

    /**
     * Default constructor.
     */
    public RestoreStartupParametersCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Option.
     *
     * @return the Option
     */
    public Integer getOption() {
        return option;
    }

    /**
     * Sets Option.
     *
     * @param option the Option
     */
    public void setOption(final Integer option) {
        this.option = option;
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
     */
    public void setIndex(final Integer index) {
        this.index = index;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(option, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(index, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        option = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        index = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(85);
        builder.append("RestoreStartupParametersCommand [");
        builder.append(super.toString());
        builder.append(", option=");
        builder.append(option);
        builder.append(", index=");
        builder.append(index);
        builder.append(']');
        return builder.toString();
    }

}
