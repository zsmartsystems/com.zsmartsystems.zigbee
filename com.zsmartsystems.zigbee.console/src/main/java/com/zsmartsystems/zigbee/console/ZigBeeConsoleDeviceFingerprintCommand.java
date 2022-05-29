/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceType;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFrameType;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementBindRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementRoutingRequest;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor;

/**
 * Gets device information from the device
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleDeviceFingerprintCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "fingerprint";
    }

    @Override
    public String getDescription() {
        return "Get detailed information about a device";
    }

    @Override
    public String getSyntax() {
        return "ADDRESS";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        // Use a separate stream so we can print all output at once
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final String utf8 = StandardCharsets.UTF_8.name();
        PrintStream writer;
        try {
            writer = new PrintStream(baos, true, utf8);
        } catch (UnsupportedEncodingException e) {
            out.println("Error creating print writer");
            return;
        }

        ZigBeeNode node = getNode(networkManager, args[1]);
        NodeDescriptor nodeDescriptor = node.getNodeDescriptor();
        PowerDescriptor powerDescriptor = node.getPowerDescriptor();
        writer.println("|>| Node Descriptor");
        if (nodeDescriptor == null) {
            writer.println("| |> Not Set");
        } else {
            writer.println("| |> Logical Type               " + nodeDescriptor.getLogicalType());
            writer.println("| |> MAC Capabilities           " + nodeDescriptor.getMacCapabilities());
            writer.println("| |> Stack Compliance           " + nodeDescriptor.getStackCompliance());
            writer.println("| |> Server Capabilities        " + nodeDescriptor.getServerCapabilities());
            writer.println("| |> Buffer Size                " + nodeDescriptor.getBufferSize());
            writer.println("| |> Incoming Transfer Size     " + nodeDescriptor.getIncomingTransferSize());
            writer.println("| |> Outgoing Transfer Size     " + nodeDescriptor.getOutGoingTransferSize());
        }

        writer.println("|");
        writer.println("|>| Power Descriptor");
        if (powerDescriptor == null) {
            writer.println("| |> Not Set");
        } else {
            writer.println("| |> Available Power Sources    " + powerDescriptor.getAvailablePowerSources());
            writer.println("| |> Current Power Source       " + powerDescriptor.getCurrentPowerSource());
            writer.println("| |> Current Power Mode         " + powerDescriptor.getCurrentPowerMode());
            writer.println("| |> Power Level                " + powerDescriptor.getPowerLevel());
        }

        writer.println("|");
        writer.println("|>| ZDO");
        writer.println("| |> ManagementBindRequest      "
                + checkZdoSupported(out, networkManager, node.getNetworkAddress(), new ManagementBindRequest(0)));
        writer.println("| |> IeeeAddressRequest         "
                + checkZdoSupported(out, networkManager, node.getNetworkAddress(),
                        new IeeeAddressRequest(node.getNetworkAddress(), 1, 0)));
        writer.println("| |> ManagementLqiRequest       "
                + checkZdoSupported(out, networkManager, node.getNetworkAddress(),
                        new ManagementLqiRequest(0)));
        writer.println("| |> ManagementRoutingRequest   "
                + checkZdoSupported(out, networkManager, node.getNetworkAddress(),
                        new ManagementRoutingRequest(0)));

        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            ZclCluster cluster = endpoint.getInputCluster(ZclBasicCluster.CLUSTER_ID);
            if (cluster != null) {
                writer.println("|");
                writer.println("|>| Basic Information");
                getBasicInformation(writer, cluster);
                break;
            }
        }

        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            getEndpointInfo(writer, networkManager, endpoint);
        }

        out.println(baos.toString());
    }

    private void getBasicInformation(PrintStream out, ZclCluster cluster) {
        getBasicAttribute(out, cluster, ZclBasicCluster.ATTR_GENERICDEVICECLASS);
        getBasicAttribute(out, cluster, ZclBasicCluster.ATTR_GENERICDEVICETYPE);
        getBasicAttribute(out, cluster, ZclBasicCluster.ATTR_MANUFACTURERNAME);
        getBasicAttribute(out, cluster, ZclBasicCluster.ATTR_MODELIDENTIFIER);
        getBasicAttribute(out, cluster, ZclBasicCluster.ATTR_PRODUCTCODE);
        getBasicAttribute(out, cluster, ZclBasicCluster.ATTR_PRODUCTURL);
        getBasicAttribute(out, cluster, ZclBasicCluster.ATTR_DATECODE);
        getBasicAttribute(out, cluster, ZclBasicCluster.ATTR_APPLICATIONVERSION);
        getBasicAttribute(out, cluster, ZclBasicCluster.ATTR_SWBUILDID);
        getBasicAttribute(out, cluster, ZclBasicCluster.ATTR_HWVERSION);
        getBasicAttribute(out, cluster, ZclBasicCluster.ATTR_ZCLVERSION);
        getBasicAttribute(out, cluster, ZclBasicCluster.ATTR_STACKVERSION);
    }

    private void getEndpointInfo(PrintStream out, ZigBeeNetworkManager networkManager, ZigBeeEndpoint endpoint) {
        out.println("| |");
        out.println("| |>| Endpoint " + endpoint.getEndpointId());

        out.print("| | |> Profile                  " + String.format("%04X", endpoint.getProfileId()));
        if (ZigBeeProfileType.getByValue(endpoint.getProfileId()) != null) {
            out.print("  " + ZigBeeProfileType.getByValue(endpoint.getProfileId()).toString());
        }
        out.println();
        out.print("| | |> Device Type              " + String.format("%04X", endpoint.getDeviceId()));
        if (ZigBeeDeviceType.getByValue(endpoint.getDeviceId()) != null) {
            out.print("  " + ZigBeeDeviceType
                    .getByValue(ZigBeeProfileType.getByValue(endpoint.getProfileId()), endpoint.getDeviceId())
                    .toString());
        }
        out.println();

        out.println("| | |> Device Version           " + endpoint.getDeviceVersion());

        if (endpoint.getEndpointId() >= 242) {
            return;
        }

        Collection<Integer> clusterIds;

        out.println("| | |");
        out.println("| | |>| Input Clusters");
        clusterIds = endpoint.getInputClusterIds();
        for (int clusterId : clusterIds) {
            getClusterInfo(out, networkManager, endpoint.getInputCluster(clusterId));
        }

        out.println("| | |");
        out.println("| | |>| Output Clusters");
        clusterIds = endpoint.getOutputClusterIds();
        for (int clusterId : clusterIds) {
            getClusterInfo(out, networkManager, endpoint.getOutputCluster(clusterId));
        }
    }

    private void getClusterInfo(PrintStream out, ZigBeeNetworkManager networkManager, ZclCluster cluster) {
        out.println("| | | |");
        out.println(
                "| | | |>| Cluster " + String.format("%04X", cluster.getClusterId()) + " " + cluster.getClusterName());
        out.println("| | | | |> Type                 " + (cluster.isClient() ? "Client [Output]" : "Server [Input]"));
        out.println("| | | | |> Manufacturer Spec.   " + (cluster.isManufacturerSpecific() ? "Yes" : "No"));

        if ((cluster.isClient() && !networkManager.isClientClusterSupported(cluster.getClusterId())) ||
                (cluster.isServer() && !networkManager.isServerClusterSupported(cluster.getClusterId()))) {
            out.println("| | | | |> Unsupported locally");
            return;
        }
        ZigBeeStatus success;
        try {
            success = cluster.discoverCommandsGenerated(true).get() ? ZigBeeStatus.SUCCESS : ZigBeeStatus.FAILURE;
        } catch (InterruptedException | ExecutionException e) {
            success = ZigBeeStatus.FATAL_ERROR;
        }
        out.println("| | | | |");
        out.println("| | | | |>| Commands Generated");
        if (success == ZigBeeStatus.SUCCESS) {
            for (int commandId : cluster.getSupportedCommandsGenerated()) {
                out.println(
                        "| | | | | |> " + String.format("%04X", commandId) + " " + getCommandName(cluster, commandId));
            }
        } else {
            out.println("| | | | | |> " + success);
        }

        try {
            success = cluster.discoverCommandsReceived(true).get() ? ZigBeeStatus.SUCCESS : ZigBeeStatus.FAILURE;
        } catch (InterruptedException | ExecutionException e) {
            success = ZigBeeStatus.FATAL_ERROR;
        }
        out.println("| | | | |");
        out.println("| | | | |>| Commands Received");
        if (success == ZigBeeStatus.SUCCESS) {
            for (int commandId : cluster.getSupportedCommandsReceived()) {
                out.println(
                        "| | | | | |> " + String.format("%04X", commandId) + " " + getCommandName(cluster, commandId));
            }
        } else {
            out.println("| | | | |> " + success);
        }

        try {
            success = cluster.discoverAttributes(true).get() ? ZigBeeStatus.SUCCESS : ZigBeeStatus.FAILURE;
        } catch (InterruptedException | ExecutionException e) {
            success = ZigBeeStatus.FATAL_ERROR;
        }
        out.println("| | | | |");
        out.println("| | | | |>| Attributes Supported");
        if (success == ZigBeeStatus.SUCCESS) {
            getAttributes(out, cluster, cluster.getSupportedAttributes());
        } else {
            out.println("| | | | | |> " + success);

            out.println("| | | | |");
            out.println("| | | | |>| Attribute Values");
            if (success == ZigBeeStatus.SUCCESS) {
                getAttributes(out, cluster.getAttributes());
            } else {
                out.println("| | | | | |> " + success);
            }
        }
    }

    private String getCommandName(ZclCluster cluster, int commandId) {
        ZclCommand command = null;

        if (cluster.isClient()) {
            command = cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, commandId);
        } else {
            command = cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, commandId);
        }

        if (command == null) {
            return "Unknown";
        }

        return command.getClass().getSimpleName();
    }

    private void getAttributes(PrintStream out, ZclCluster cluster, Collection<Integer> attributeIds) {
        for (int attributeId : attributeIds) {
            ZclAttribute attribute = cluster.getAttribute(attributeId);
            Object value = null;
            if (attribute != null && attribute.isReadable()) {
                value = attribute.readValue(60L);
            } else {
                value = "";
            }

            out.println(String.format("| | | | | |> %04X  %-40s  %s", attributeId, (attribute == null ? "Unknown"
                    : attribute.getName()), (value == null ? "" : (">> " + value.toString()))));
        }
    }

    private void getAttributes(PrintStream out, Collection<ZclAttribute> attributes) {
        for (ZclAttribute attribute : attributes) {
            Object value = null;
            if (!attribute.isReadable()) {
                continue;
            }

            value = attribute.readValue(60L);
            if (value == null) {
                continue;
            }
            out.println(String.format("| | | | | |> %04X  %-40s  >> %s", attribute.getId(), attribute.getName(),
                    value.toString()));
        }
    }

    private void getBasicAttribute(PrintStream out, ZclCluster cluster, int attributeId) {
        ZclAttribute attribute = cluster.getAttribute(attributeId);
        String name;
        Object value = attribute.readValue(0);
        switch (attribute.getId()) {
            case ZclBasicCluster.ATTR_APPLICATIONVERSION:
                name = "Application Version        ";
                break;
            case ZclBasicCluster.ATTR_DATECODE:
                name = "Date Code                  ";
                break;
            case ZclBasicCluster.ATTR_GENERICDEVICECLASS:
                name = "Generic Device Class       ";
                break;
            case ZclBasicCluster.ATTR_GENERICDEVICETYPE:
                name = "Generic Device Type        ";
                break;
            case ZclBasicCluster.ATTR_HWVERSION:
                name = "Hardware Version           ";
                break;
            case ZclBasicCluster.ATTR_MANUFACTURERNAME:
                name = "Manufacturer Name          ";
                break;
            case ZclBasicCluster.ATTR_MODELIDENTIFIER:
                name = "Model Indentifier          ";
                break;
            case ZclBasicCluster.ATTR_PRODUCTCODE:
                name = "Product Code               ";
                break;
            case ZclBasicCluster.ATTR_PRODUCTURL:
                name = "Product URL                ";
                break;
            case ZclBasicCluster.ATTR_STACKVERSION:
                name = "Stack Version              ";
                break;
            case ZclBasicCluster.ATTR_SWBUILDID:
                name = "Software Build ID          ";
                break;
            case ZclBasicCluster.ATTR_ZCLVERSION:
                name = "Zcl Version                ";
                break;
            default:
                return;
        }
        out.println("| |> " + name + (value == null ? "" : value.toString()));
    }

    private ZdoStatus checkZdoSupported(PrintStream out, ZigBeeNetworkManager networkManager, int address,
            ZdoCommand command) {
        command.setDestinationAddress(new ZigBeeEndpointAddress(address));

        CommandResult result;
        try {
            result = networkManager.sendTransaction(command, (ZigBeeTransactionMatcher) command).get();
        } catch (InterruptedException | ExecutionException e) {
            return ZdoStatus.TIMEOUT;
        }
        if (result.isTimeout()) {
            return ZdoStatus.TIMEOUT;
        }
        if (result.isSuccess()) {
            return ZdoStatus.SUCCESS;
        }

        if (ZdoStatus.getStatus(result.getStatusCode()) == null) {
            return ZdoStatus.UNKNOWN;
        }

        return ZdoStatus.getStatus(result.getStatusCode());
    }

}
