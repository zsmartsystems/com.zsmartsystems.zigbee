/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Bypass Response value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * Provides the response of the security panel to the request from the IAS ACE client to bypass
 * zones via a Bypass command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T19:19:25Z")
public class BypassResponse extends ZclCommand {
    /**
     * Bypass Result command message field.
     * <p>
     * An array of Zone IDs for each zone requested to be bypassed via the Bypass command where X
     * is equal to the value of the Number of Zones field. The order of results for Zone IDs shall
     * be the same as the order of Zone IDs sent in the Bypass command by the IAS ACE client.
     */
    private List<Integer> bypassResult;

    /**
     * Default constructor.
     */
    public BypassResponse() {
        genericCommand = false;
        clusterId = 0x0501;
        commandId = 7;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Bypass Result.
     * <p>
     * An array of Zone IDs for each zone requested to be bypassed via the Bypass command where X
     * is equal to the value of the Number of Zones field. The order of results for Zone IDs shall
     * be the same as the order of Zone IDs sent in the Bypass command by the IAS ACE client.
     *
     * @return the Bypass Result
     */
    public List<Integer> getBypassResult() {
        return bypassResult;
    }

    /**
     * Sets Bypass Result.
     * <p>
     * An array of Zone IDs for each zone requested to be bypassed via the Bypass command where X
     * is equal to the value of the Number of Zones field. The order of results for Zone IDs shall
     * be the same as the order of Zone IDs sent in the Bypass command by the IAS ACE client.
     *
     * @param bypassResult the Bypass Result
     */
    public void setBypassResult(final List<Integer> bypassResult) {
        this.bypassResult = bypassResult;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(bypassResult, ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        bypassResult = (List<Integer>) deserializer.deserialize(ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(49);
        builder.append("BypassResponse [");
        builder.append(super.toString());
        builder.append(", bypassResult=");
        builder.append(bypassResult);
        builder.append(']');
        return builder.toString();
    }

}
