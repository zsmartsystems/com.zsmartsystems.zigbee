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
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Request Tunnel value object class.
 * <p>
 * Cluster: <b>Smart Energy Tunneling</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Smart Energy Tunneling cluster.
 * <p>
 * RequestTunnel is the client command used to setup a tunnel association with the server. The
 * request payload specifies the protocol identifier for the requested tunnel, a
 * manufacturer code in case of proprietary protocols and the use of flow control for streaming
 * protocols.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class RequestTunnel extends ZclCommand {
    /**
     * Protocol ID command message field.
     * <p>
     * An enumeration representing the identifier of the metering communication protocol
     * for which the tunnel is requested.
     * <p>
     * The values above 199 may be used for manufacturer specific protocols.
     */
    private Integer protocolId;

    /**
     * Manufacturer Code command message field.
     * <p>
     * A code that is allocated by the ZigBee Alliance, relating the manufacturer to a device
     * and – for the tunneling - a manufacturer specific protocol. The parameter is ignored
     * when the ProtocolID value is less than 200. This allows for 55 manufacturer-defined
     * protocols for each manufacturer to be defined. A value of 0xFFFF indicates that the
     * Manufacturer Code is not used.
     */
    private Integer manufacturerCode;

    /**
     * Flow Control Support command message field.
     * <p>
     * A boolean type parameter that indicates whether flow control support is requested from
     * the tunnel (TRUE) or not (FALSE). The default value is FALSE (no flow control).
     */
    private Boolean flowControlSupport;

    /**
     * Maximum Incoming Transfer Size command message field.
     * <p>
     * A value that defines the size, in octets, of the maximum data packet that can be
     * transferred to the client in the payload of a single TransferData command.
     */
    private Integer maximumIncomingTransferSize;

    /**
     * Default constructor.
     */
    public RequestTunnel() {
        genericCommand = false;
        clusterId = 0x0704;
        commandId = 0;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Protocol ID.
     * <p>
     * An enumeration representing the identifier of the metering communication protocol
     * for which the tunnel is requested.
     * <p>
     * The values above 199 may be used for manufacturer specific protocols.
     *
     * @return the Protocol ID
     */
    public Integer getProtocolId() {
        return protocolId;
    }

    /**
     * Sets Protocol ID.
     * <p>
     * An enumeration representing the identifier of the metering communication protocol
     * for which the tunnel is requested.
     * <p>
     * The values above 199 may be used for manufacturer specific protocols.
     *
     * @param protocolId the Protocol ID
     */
    public void setProtocolId(final Integer protocolId) {
        this.protocolId = protocolId;
    }

    /**
     * Gets Manufacturer Code.
     * <p>
     * A code that is allocated by the ZigBee Alliance, relating the manufacturer to a device
     * and – for the tunneling - a manufacturer specific protocol. The parameter is ignored
     * when the ProtocolID value is less than 200. This allows for 55 manufacturer-defined
     * protocols for each manufacturer to be defined. A value of 0xFFFF indicates that the
     * Manufacturer Code is not used.
     *
     * @return the Manufacturer Code
     */
    public Integer getManufacturerCode() {
        return manufacturerCode;
    }

    /**
     * Sets Manufacturer Code.
     * <p>
     * A code that is allocated by the ZigBee Alliance, relating the manufacturer to a device
     * and – for the tunneling - a manufacturer specific protocol. The parameter is ignored
     * when the ProtocolID value is less than 200. This allows for 55 manufacturer-defined
     * protocols for each manufacturer to be defined. A value of 0xFFFF indicates that the
     * Manufacturer Code is not used.
     *
     * @param manufacturerCode the Manufacturer Code
     */
    public void setManufacturerCode(final Integer manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    /**
     * Gets Flow Control Support.
     * <p>
     * A boolean type parameter that indicates whether flow control support is requested from
     * the tunnel (TRUE) or not (FALSE). The default value is FALSE (no flow control).
     *
     * @return the Flow Control Support
     */
    public Boolean getFlowControlSupport() {
        return flowControlSupport;
    }

    /**
     * Sets Flow Control Support.
     * <p>
     * A boolean type parameter that indicates whether flow control support is requested from
     * the tunnel (TRUE) or not (FALSE). The default value is FALSE (no flow control).
     *
     * @param flowControlSupport the Flow Control Support
     */
    public void setFlowControlSupport(final Boolean flowControlSupport) {
        this.flowControlSupport = flowControlSupport;
    }

    /**
     * Gets Maximum Incoming Transfer Size.
     * <p>
     * A value that defines the size, in octets, of the maximum data packet that can be
     * transferred to the client in the payload of a single TransferData command.
     *
     * @return the Maximum Incoming Transfer Size
     */
    public Integer getMaximumIncomingTransferSize() {
        return maximumIncomingTransferSize;
    }

    /**
     * Sets Maximum Incoming Transfer Size.
     * <p>
     * A value that defines the size, in octets, of the maximum data packet that can be
     * transferred to the client in the payload of a single TransferData command.
     *
     * @param maximumIncomingTransferSize the Maximum Incoming Transfer Size
     */
    public void setMaximumIncomingTransferSize(final Integer maximumIncomingTransferSize) {
        this.maximumIncomingTransferSize = maximumIncomingTransferSize;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(protocolId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(flowControlSupport, ZclDataType.BOOLEAN);
        serializer.serialize(maximumIncomingTransferSize, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        protocolId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        manufacturerCode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        flowControlSupport = (Boolean) deserializer.deserialize(ZclDataType.BOOLEAN);
        maximumIncomingTransferSize = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(167);
        builder.append("RequestTunnel [");
        builder.append(super.toString());
        builder.append(", protocolId=");
        builder.append(protocolId);
        builder.append(", manufacturerCode=");
        builder.append(manufacturerCode);
        builder.append(", flowControlSupport=");
        builder.append(flowControlSupport);
        builder.append(", maximumIncomingTransferSize=");
        builder.append(maximumIncomingTransferSize);
        builder.append(']');
        return builder.toString();
    }

}
