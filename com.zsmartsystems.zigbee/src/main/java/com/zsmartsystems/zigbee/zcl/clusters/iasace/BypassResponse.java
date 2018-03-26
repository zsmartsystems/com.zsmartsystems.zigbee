/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

import java.util.List;

/**
 * Bypass Response value object class.
 * <p>
 * Provides the response of the security panel to the request from the IAS ACE client to bypass zones via a Bypass command.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * The IAS ACE cluster defines an interface to the functionality of any Ancillary
 * Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE
 * device can access a IAS CIE device and manipulate the IAS system, on behalf of a
 * level-2 user.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-26T17:34:00Z")
public class BypassResponse extends ZclCommand {
    /**
     * Bypass Result command message field.
     * <p>
     * An array of Zone IDs for each zone requested to be bypassed via the Bypass command whereX is equal to the value of
     * the Number of Zones field. The order of results for Zone IDs SHALL be the same as the order of Zone IDs sent in
     * the Bypass command by the IAS ACE client.
     * Indicates whether there are additional Zone IDs managed by the IAS ACE Server with Zone Status information to be obtained.
     * A value of zero (i.e. FALSE) indicates there are additional Zone IDs for which Zone Status information is available and
     * that the IAS ACE client SHOULD send another Get Zone Status com-mand.A value of one (i.e. TRUE) indicates there are no
     * more Zone IDs for the IAS ACE client to query and the IAS ACE client has received all the Zone Status information for all
     * IAS Zones managed by the IAS ACE server.
     * <p>
     * The IAS ACE client SHOULD NOT typically send another Get Zone Status command.
     */
    private List<Integer> bypassResult;

    /**
     * Default constructor.
     */
    public BypassResponse() {
        genericCommand = false;
        clusterId = 1281;
        commandId = 7;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Bypass Result.
     *
     * An array of Zone IDs for each zone requested to be bypassed via the Bypass command whereX is equal to the value of
     * the Number of Zones field. The order of results for Zone IDs SHALL be the same as the order of Zone IDs sent in
     * the Bypass command by the IAS ACE client.
     * Indicates whether there are additional Zone IDs managed by the IAS ACE Server with Zone Status information to be obtained.
     * A value of zero (i.e. FALSE) indicates there are additional Zone IDs for which Zone Status information is available and
     * that the IAS ACE client SHOULD send another Get Zone Status com-mand.A value of one (i.e. TRUE) indicates there are no
     * more Zone IDs for the IAS ACE client to query and the IAS ACE client has received all the Zone Status information for all
     * IAS Zones managed by the IAS ACE server.
     * <p>
     * The IAS ACE client SHOULD NOT typically send another Get Zone Status command.
     *
     * @return the Bypass Result
     */
    public List<Integer> getBypassResult() {
        return bypassResult;
    }

    /**
     * Sets Bypass Result.
     *
     * An array of Zone IDs for each zone requested to be bypassed via the Bypass command whereX is equal to the value of
     * the Number of Zones field. The order of results for Zone IDs SHALL be the same as the order of Zone IDs sent in
     * the Bypass command by the IAS ACE client.
     * Indicates whether there are additional Zone IDs managed by the IAS ACE Server with Zone Status information to be obtained.
     * A value of zero (i.e. FALSE) indicates there are additional Zone IDs for which Zone Status information is available and
     * that the IAS ACE client SHOULD send another Get Zone Status com-mand.A value of one (i.e. TRUE) indicates there are no
     * more Zone IDs for the IAS ACE client to query and the IAS ACE client has received all the Zone Status information for all
     * IAS Zones managed by the IAS ACE server.
     * <p>
     * The IAS ACE client SHOULD NOT typically send another Get Zone Status command.
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
