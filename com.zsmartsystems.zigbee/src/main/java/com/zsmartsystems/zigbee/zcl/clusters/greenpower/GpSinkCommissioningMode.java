/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Gp Sink Commissioning Mode value object class.
 * <p>
 * Cluster: <b>Green Power</b>. Command ID 0x05 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Green Power cluster.
 * <p>
 * The GP Sink Commissioning Mode command is generated by a remote device, e.g. a Commissioning
 * Tool, to request a sink to perform a commissioning action in a particular way.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-11-03T12:48:45Z")
public class GpSinkCommissioningMode extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0021;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x05;

    /**
     * Options command message field.
     */
    private Integer options;

    /**
     * Gpm Addr For Security command message field.
     */
    private Integer gpmAddrForSecurity;

    /**
     * Gpm Addr For Pairing command message field.
     */
    private Integer gpmAddrForPairing;

    /**
     * Sink Endpoint command message field.
     */
    private Integer sinkEndpoint;

    /**
     * Default constructor.
     */
    public GpSinkCommissioningMode() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Options.
     *
     * @return the Options
     */
    public Integer getOptions() {
        return options;
    }

    /**
     * Sets Options.
     *
     * @param options the Options
     */
    public void setOptions(final Integer options) {
        this.options = options;
    }

    /**
     * Gets Gpm Addr For Security.
     *
     * @return the Gpm Addr For Security
     */
    public Integer getGpmAddrForSecurity() {
        return gpmAddrForSecurity;
    }

    /**
     * Sets Gpm Addr For Security.
     *
     * @param gpmAddrForSecurity the Gpm Addr For Security
     */
    public void setGpmAddrForSecurity(final Integer gpmAddrForSecurity) {
        this.gpmAddrForSecurity = gpmAddrForSecurity;
    }

    /**
     * Gets Gpm Addr For Pairing.
     *
     * @return the Gpm Addr For Pairing
     */
    public Integer getGpmAddrForPairing() {
        return gpmAddrForPairing;
    }

    /**
     * Sets Gpm Addr For Pairing.
     *
     * @param gpmAddrForPairing the Gpm Addr For Pairing
     */
    public void setGpmAddrForPairing(final Integer gpmAddrForPairing) {
        this.gpmAddrForPairing = gpmAddrForPairing;
    }

    /**
     * Gets Sink Endpoint.
     *
     * @return the Sink Endpoint
     */
    public Integer getSinkEndpoint() {
        return sinkEndpoint;
    }

    /**
     * Sets Sink Endpoint.
     *
     * @param sinkEndpoint the Sink Endpoint
     */
    public void setSinkEndpoint(final Integer sinkEndpoint) {
        this.sinkEndpoint = sinkEndpoint;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(options, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(gpmAddrForSecurity, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(gpmAddrForPairing, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(sinkEndpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        options = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        gpmAddrForSecurity = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        gpmAddrForPairing = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sinkEndpoint = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(160);
        builder.append("GpSinkCommissioningMode [");
        builder.append(super.toString());
        builder.append(", options=");
        builder.append(options);
        builder.append(", gpmAddrForSecurity=");
        builder.append(gpmAddrForSecurity);
        builder.append(", gpmAddrForPairing=");
        builder.append(gpmAddrForPairing);
        builder.append(", sinkEndpoint=");
        builder.append(sinkEndpoint);
        builder.append(']');
        return builder.toString();
    }

}
