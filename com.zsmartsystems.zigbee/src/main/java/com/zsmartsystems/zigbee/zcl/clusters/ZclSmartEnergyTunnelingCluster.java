/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.AckTransferDataClientToServer;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.AckTransferDataServerToClient;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.CloseTunnel;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.GetSupportedTunnelProtocols;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.Protocol;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.ReadyDataClientToServer;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.ReadyDataServerToClient;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.RequestTunnel;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.RequestTunnelResponse;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.SupportedTunnelProtocolsResponse;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.TransferDataClientToServer;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.TransferDataErrorClientToServer;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.TransferDataErrorServerToClient;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.TransferDataServerToClient;
import com.zsmartsystems.zigbee.zcl.clusters.smartenergytunneling.TunnelClosureNotification;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Smart Energy Tunneling</b> cluster implementation (<i>Cluster ID 0x0704</i>).
 * <p>
 * The tunneling cluster provides an interface for tunneling protocols. It is comprised of
 * commands and attributes required to transport any existing metering communication
 * protocol within the payload of standard ZigBee frames (including the handling of issues
 * such as addressing, fragmentation and flow control). Examples for such protocols are
 * DLMS/COSEM, IEC61107, ANSI C12, M-Bus, ClimateTalk etc.
 * <p>
 * The tunneling foresees the roles of a server and a CLIENT taking part in the data exchange.
 * Their roles are defined as follows:
 * <p>
 * CLIENT: Requests a tunnel from the server and closes the tunnel if it is no longer needed.
 * <p>
 * Server: Provides and manages tunnels to the clients.
 * <p>
 * The data exchange through the tunnel is symmetric. This means both CLIENT and server provide
 * the commands to transfer data (TransferData). And both must make sure that only the partner
 * to which the tunnel has been built up is granted read/write access to it (e.g. tunnel
 * identifier protection through checking the MAC address).
 * <p>
 * Sleepy devices either close the tunnel immediately after they have pushed their data
 * through it, or leave it open in which case an attribute in the server (CloseTunnelTimeout)
 * decides whether the tunnel is closed from the server side during the sleeping phase or not. It
 * is recommended that battery-powered (sleepy) devices fulfil the role of the Tunneling
 * cluster CLIENT (and therefore have control over when they request a tunnel from the server).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ZclSmartEnergyTunnelingCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0704;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Smart Energy Tunneling";

    // Attribute constants
    /**
     * CloseTunnelTimeout defines the minimum number of seconds that the server waits on an
     * inactive tunnel before closing it on its own and freeing its resources (without waiting
     * for the CloseTunnel command from the client). Inactive means here that the timer is
     * re-started with each new reception of a command.0x0000 is an invalid value.
     */
    public static final int ATTR_CLOSETUNNELTIMEOUT = 0x0000;

    // Attribute initialisation
    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(1);

        attributeMap.put(ATTR_CLOSETUNNELTIMEOUT, new ZclAttribute(ZclClusterType.SMART_ENERGY_TUNNELING, ATTR_CLOSETUNNELTIMEOUT, "Close Tunnel Timeout", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Smart Energy Tunneling cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclSmartEnergyTunnelingCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Close Tunnel Timeout</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * CloseTunnelTimeout defines the minimum number of seconds that the server waits on an
     * inactive tunnel before closing it on its own and freeing its resources (without waiting
     * for the CloseTunnel command from the client). Inactive means here that the timer is
     * re-started with each new reception of a command.0x0000 is an invalid value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCloseTunnelTimeoutAsync() {
        return read(attributes.get(ATTR_CLOSETUNNELTIMEOUT));
    }

    /**
     * Synchronously get the <i>Close Tunnel Timeout</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * CloseTunnelTimeout defines the minimum number of seconds that the server waits on an
     * inactive tunnel before closing it on its own and freeing its resources (without waiting
     * for the CloseTunnel command from the client). Inactive means here that the timer is
     * re-started with each new reception of a command.0x0000 is an invalid value.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getCloseTunnelTimeout(final long refreshPeriod) {
        if (attributes.get(ATTR_CLOSETUNNELTIMEOUT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CLOSETUNNELTIMEOUT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CLOSETUNNELTIMEOUT));
    }

    /**
     * Set reporting for the <i>Close Tunnel Timeout</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * CloseTunnelTimeout defines the minimum number of seconds that the server waits on an
     * inactive tunnel before closing it on its own and freeing its resources (without waiting
     * for the CloseTunnel command from the client). Inactive means here that the timer is
     * re-started with each new reception of a command.0x0000 is an invalid value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCloseTunnelTimeoutReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CLOSETUNNELTIMEOUT), minInterval, maxInterval, reportableChange);
    }

    /**
     * The Request Tunnel
     * <p>
     * RequestTunnel is the client command used to setup a tunnel association with the server.
     * The request payload specifies the protocol identifier for the requested tunnel, a
     * manufacturer code in case of proprietary protocols and the use of flow control for
     * streaming protocols.
     *
     * @param protocolId {@link Integer} Protocol ID
     * @param manufacturerCode {@link Integer} Manufacturer Code
     * @param flowControlSupport {@link Boolean} Flow Control Support
     * @param maximumIncomingTransferSize {@link Integer} Maximum Incoming Transfer Size
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> requestTunnel(Integer protocolId, Integer manufacturerCode, Boolean flowControlSupport, Integer maximumIncomingTransferSize) {
        RequestTunnel command = new RequestTunnel();

        // Set the fields
        command.setProtocolId(protocolId);
        command.setManufacturerCode(manufacturerCode);
        command.setFlowControlSupport(flowControlSupport);
        command.setMaximumIncomingTransferSize(maximumIncomingTransferSize);

        return send(command);
    }

    /**
     * The Close Tunnel
     * <p>
     * Client command used to close the tunnel with the server. The parameter in the payload
     * specifies the tunnel identifier of the tunnel that has to be closed. The server leaves
     * the tunnel open and the assigned resources allocated until the client sends the
     * CloseTunnel command or the CloseTunnelTimeout fires.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> closeTunnel(Integer tunnelId) {
        CloseTunnel command = new CloseTunnel();

        // Set the fields
        command.setTunnelId(tunnelId);

        return send(command);
    }

    /**
     * The Transfer Data Client To Server
     * <p>
     * Command that indicates (if received) that the client has sent data to the server. The
     * data itself is contained within the payload.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     * @param data {@link Integer} Data
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> transferDataClientToServer(Integer tunnelId, Integer data) {
        TransferDataClientToServer command = new TransferDataClientToServer();

        // Set the fields
        command.setTunnelId(tunnelId);
        command.setData(data);

        return send(command);
    }

    /**
     * The Transfer Data Error Client To Server
     * <p>
     * This command is generated by the receiver of a TransferData command if the tunnel status
     * indicates that something is wrong. There are two three cases in which
     * TransferDataError is sent:
     * <p>
     * The TransferData received contains a TunnelID that does not match to any of the active
     * tunnels of the receiving device. This could happen if a (sleeping) device sends a
     * TransferData command to a tunnel that has been closed by the server after the
     * CloseTunnelTimeout.
     * <p>
     * The TransferData received contains a proper TunnelID of an active tunnel, but the
     * device sending the data does not match to it.
     * <p>
     * The TransferData received contains more data than indicated by the
     * MaximumIncomingTransferSize of the receiving device.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     * @param transferDataStatus {@link Integer} Transfer Data Status
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> transferDataErrorClientToServer(Integer tunnelId, Integer transferDataStatus) {
        TransferDataErrorClientToServer command = new TransferDataErrorClientToServer();

        // Set the fields
        command.setTunnelId(tunnelId);
        command.setTransferDataStatus(transferDataStatus);

        return send(command);
    }

    /**
     * The Ack Transfer Data Client To Server
     * <p>
     * Command sent in response to each TransferData command in case - and only in case - flow
     * control has been requested by the client in the TunnelRequest command and is supported
     * by both tunnel endpoints. The response payload indicates the number of octets that may
     * still be received by the receiver.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     * @param numberOfBytesLeft {@link Integer} Number Of Bytes Left
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> ackTransferDataClientToServer(Integer tunnelId, Integer numberOfBytesLeft) {
        AckTransferDataClientToServer command = new AckTransferDataClientToServer();

        // Set the fields
        command.setTunnelId(tunnelId);
        command.setNumberOfBytesLeft(numberOfBytesLeft);

        return send(command);
    }

    /**
     * The Ready Data Client To Server
     * <p>
     * The ReadyData command is generated - after a receiver had to stop the dataflow using the
     * AckTransferData(0) command - to indicate that the device is now ready to continue
     * receiving data. The parameter NumberOfOctetsLeft gives a hint on how much space is left
     * for the next data transfer. The ReadyData command is only issued if flow control is
     * enabled.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     * @param numberOfOctetsLeft {@link Integer} Number Of Octets Left
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> readyDataClientToServer(Integer tunnelId, Integer numberOfOctetsLeft) {
        ReadyDataClientToServer command = new ReadyDataClientToServer();

        // Set the fields
        command.setTunnelId(tunnelId);
        command.setNumberOfOctetsLeft(numberOfOctetsLeft);

        return send(command);
    }

    /**
     * The Get Supported Tunnel Protocols
     * <p>
     * Get Supported Tunnel Protocols is the client command used to determine the Tunnel
     * protocols supported on another device.
     *
     * @param protocolOffset {@link Integer} Protocol Offset
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getSupportedTunnelProtocols(Integer protocolOffset) {
        GetSupportedTunnelProtocols command = new GetSupportedTunnelProtocols();

        // Set the fields
        command.setProtocolOffset(protocolOffset);

        return send(command);
    }

    /**
     * The Request Tunnel Response
     * <p>
     * RequestTunnelResponse is sent by the server in response to a RequestTunnel command
     * previously received from the client. The response contains the status of the
     * RequestTunnel command and a tunnel identifier corresponding to the tunnel that has
     * been set-up in the server in case of success.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     * @param tunnelStatus {@link Integer} Tunnel Status
     * @param maximumIncomingTransferSize {@link Integer} Maximum Incoming Transfer Size
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> requestTunnelResponse(Integer tunnelId, Integer tunnelStatus, Integer maximumIncomingTransferSize) {
        RequestTunnelResponse command = new RequestTunnelResponse();

        // Set the fields
        command.setTunnelId(tunnelId);
        command.setTunnelStatus(tunnelStatus);
        command.setMaximumIncomingTransferSize(maximumIncomingTransferSize);

        return send(command);
    }

    /**
     * The Transfer Data Server To Client
     * <p>
     * Command that transfers data from server to the client. The data itself has to be placed
     * within the payload.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     * @param data {@link ByteArray} Data
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> transferDataServerToClient(Integer tunnelId, ByteArray data) {
        TransferDataServerToClient command = new TransferDataServerToClient();

        // Set the fields
        command.setTunnelId(tunnelId);
        command.setData(data);

        return send(command);
    }

    /**
     * The Transfer Data Error Server To Client
     * <p>
     * This command is generated by the receiver of a TransferData command if the tunnel status
     * indicates that something is wrong. There are two three cases in which
     * TransferDataError is sent:
     * <p>
     * The TransferData received contains a TunnelID that does not match to any of the active
     * tunnels of the receiving device. This could happen if a (sleeping) device sends a
     * TransferData command to a tunnel that has been closed by the server after the
     * CloseTunnelTimeout.
     * <p>
     * The TransferData received contains a proper TunnelID of an active tunnel, but the
     * device sending the data does not match to it.
     * <p>
     * The TransferData received contains more data than indicated by the
     * MaximumIncomingTransferSize of the receiving device.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     * @param transferDataStatus {@link Integer} Transfer Data Status
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> transferDataErrorServerToClient(Integer tunnelId, Integer transferDataStatus) {
        TransferDataErrorServerToClient command = new TransferDataErrorServerToClient();

        // Set the fields
        command.setTunnelId(tunnelId);
        command.setTransferDataStatus(transferDataStatus);

        return send(command);
    }

    /**
     * The Ack Transfer Data Server To Client
     * <p>
     * Command sent in response to each TransferData command in case - and only in case - flow
     * control has been requested by the client in the TunnelRequest command and is supported
     * by both tunnel endpoints. The response payload indicates the number of octets that may
     * still be received by the receiver.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     * @param numberOfBytesLeft {@link Integer} Number Of Bytes Left
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> ackTransferDataServerToClient(Integer tunnelId, Integer numberOfBytesLeft) {
        AckTransferDataServerToClient command = new AckTransferDataServerToClient();

        // Set the fields
        command.setTunnelId(tunnelId);
        command.setNumberOfBytesLeft(numberOfBytesLeft);

        return send(command);
    }

    /**
     * The Ready Data Server To Client
     * <p>
     * The ReadyData command is generated - after a receiver had to stop the dataflow using the
     * AckTransferData(0) command - to indicate that the device is now ready to continue
     * receiving data. The parameter NumberOfOctetsLeft gives a hint on how much space is left
     * for the next data transfer. The ReadyData command is only issued if flow control is
     * enabled.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     * @param numberOfOctetsLeft {@link Integer} Number Of Octets Left
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> readyDataServerToClient(Integer tunnelId, Integer numberOfOctetsLeft) {
        ReadyDataServerToClient command = new ReadyDataServerToClient();

        // Set the fields
        command.setTunnelId(tunnelId);
        command.setNumberOfOctetsLeft(numberOfOctetsLeft);

        return send(command);
    }

    /**
     * The Supported Tunnel Protocols Response
     * <p>
     * Supported Tunnel Protocol Response is sent in response to a Get Supported Tunnel
     * Protocols command previously received. The response contains a list of Tunnel
     * protocols supported by the device; the payload of the response should be capable of
     * holding up to 16 protocols.
     *
     * @param protocolListComplete {@link Boolean} Protocol List Complete
     * @param protocolCount {@link Integer} Protocol Count
     * @param protocolList {@link Protocol} Protocol List
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> supportedTunnelProtocolsResponse(Boolean protocolListComplete, Integer protocolCount, Protocol protocolList) {
        SupportedTunnelProtocolsResponse command = new SupportedTunnelProtocolsResponse();

        // Set the fields
        command.setProtocolListComplete(protocolListComplete);
        command.setProtocolCount(protocolCount);
        command.setProtocolList(protocolList);

        return send(command);
    }

    /**
     * The Tunnel Closure Notification
     * <p>
     * TunnelClosureNotification is sent by the server to indicate that a tunnel has been
     * closed due to expiration of a CloseTunnelTimeout.
     * <p>
     * The command is sent by a server when a tunnel is closed due to expiration of
     * CloseTunnelTimeout. It is sent unicast to the client that had originally requested
     * that tunnel.
     *
     * @param tunnelId {@link Integer} Tunnel ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> tunnelClosureNotification(Integer tunnelId) {
        TunnelClosureNotification command = new TunnelClosureNotification();

        // Set the fields
        command.setTunnelId(tunnelId);

        return send(command);
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0x00: // REQUEST_TUNNEL
                return new RequestTunnel();
            case 0x01: // CLOSE_TUNNEL
                return new CloseTunnel();
            case 0x02: // TRANSFER_DATA_CLIENT_TO_SERVER
                return new TransferDataClientToServer();
            case 0x03: // TRANSFER_DATA_ERROR_CLIENT_TO_SERVER
                return new TransferDataErrorClientToServer();
            case 0x04: // ACK_TRANSFER_DATA_CLIENT_TO_SERVER
                return new AckTransferDataClientToServer();
            case 0x05: // READY_DATA_CLIENT_TO_SERVER
                return new ReadyDataClientToServer();
            case 0x06: // GET_SUPPORTED_TUNNEL_PROTOCOLS
                return new GetSupportedTunnelProtocols();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 0x00: // REQUEST_TUNNEL_RESPONSE
                return new RequestTunnelResponse();
            case 0x01: // TRANSFER_DATA_SERVER_TO_CLIENT
                return new TransferDataServerToClient();
            case 0x02: // TRANSFER_DATA_ERROR_SERVER_TO_CLIENT
                return new TransferDataErrorServerToClient();
            case 0x03: // ACK_TRANSFER_DATA_SERVER_TO_CLIENT
                return new AckTransferDataServerToClient();
            case 0x04: // READY_DATA_SERVER_TO_CLIENT
                return new ReadyDataServerToClient();
            case 0x05: // SUPPORTED_TUNNEL_PROTOCOLS_RESPONSE
                return new SupportedTunnelProtocolsResponse();
            case 0x06: // TUNNEL_CLOSURE_NOTIFICATION
                return new TunnelClosureNotification();
            default:
                return null;
        }
    }
}
