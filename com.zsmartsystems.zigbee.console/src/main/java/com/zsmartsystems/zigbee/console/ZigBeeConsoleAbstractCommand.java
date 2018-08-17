/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Abstract class for ZigBee Console Commands
 *
 * @author Chris Jackson
 *
 */
public abstract class ZigBeeConsoleAbstractCommand implements ZigBeeConsoleCommand {
    @Override
    public abstract String getCommand();

    @Override
    public abstract String getDescription();

    @Override
    public abstract String getSyntax();

    @Override
    public abstract String getHelp();

    @Override
    public abstract void process(final ZigBeeNetworkManager networkManager, final String[] args, PrintStream out)
            throws IllegalArgumentException, IllegalStateException, ExecutionException, InterruptedException;

    /**
     * Gets a {@link ZigBeeNode}
     *
     * @param networkManager the {@link ZigBeeNetworkManager}
     * @param nodeId         a {@link String} with the node Id
     * @return the {@link ZigBeeNode}
     * @throws IllegalArgumentException
     */
    protected ZigBeeNode getNode(ZigBeeNetworkManager networkManager, final String nodeId)
            throws IllegalArgumentException {
        try {
            Integer nwkAddress = Integer.parseInt(nodeId);
            if (networkManager.getNode(nwkAddress) != null) {
                return networkManager.getNode(nwkAddress);
            }
        } catch (Exception e) {
        }

        try {
            IeeeAddress ieeeAddress = new IeeeAddress(nodeId);
            if (networkManager.getNode(ieeeAddress) != null) {
                return networkManager.getNode(ieeeAddress);
            }
        } catch (Exception e) {
        }

        throw new IllegalArgumentException("Node '" + nodeId + "' is not found.");
    }

    /**
     * Gets {@link ZigBeeEndpoint} by device identifier.
     *
     * @param networkManager the {@link ZigBeeNetworkManager}
     * @param endpointId     the device identifier
     * @return the {@link ZigBeeEndpoint}
     * @throws IllegalArgumentException
     */
    protected ZigBeeEndpoint getEndpoint(final ZigBeeNetworkManager networkManager, final String endpointId)
            throws IllegalArgumentException {
        for (final ZigBeeNode node : networkManager.getNodes()) {
            for (final ZigBeeEndpoint endpoint : node.getEndpoints()) {
                if (endpointId.equals(node.getNetworkAddress() + "/" + endpoint.getEndpointId())) {
                    return endpoint;
                }
            }
        }
        throw new IllegalArgumentException("Endpoint '" + endpointId + "' is not found");
    }

    /**
     * Parses a cluster ID as a string to an integer. The ID can be either a decimal or a hexadecimal literal (e..g, 11
     * or 0xB).
     *
     * @param clusterId a {@link String} name of the cluster
     * @return the cluster ID as an integer
     * @throws IllegalArgumentException
     */
    protected Integer parseClusterId(final String clusterId) throws IllegalArgumentException {
        try {
            return getInteger(clusterId);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Cluster ID '" + clusterId + "' uses an invalid number format.");
        }
    }

    /**
     * Gets the cluster for a given endpoint, where the cluster is specified by a cluster specifier.
     * <p>
     * The cluster specifier consists of the cluster ID (either in decimal, or in hex prefixed with 0x), optionally
     * prepended with any of the prefixes 'in', 'out', 'client', or 'server'. The prefix indicates whether an input or
     * an output cluster shall be returned. If no prefix is provided, then the method first tries to return an input
     * cluster with the given id, and, if none is found, to return an output cluster.
     * <p>
     * Examples for cluster specifiers:
     * <ul>
     * <li>0x0B
     * <li>11
     * <li>in:0xB
     * <li>server:11
     * </ul>
     *
     * @param endpoint         the ZigBee endpoint to get the cluster from (must be non-null)
     * @param clusterSpecified a cluster specified as described above (must be non-null)
     * @return the specified cluster provided by the endpoint or null if no such cluster is found
     * @throws IllegalArgumentException if the clusterSpecifier uses an invalid number format
     */
    protected ZclCluster getCluster(final ZigBeeEndpoint endpoint, final String clusterSpecifier)
            throws IllegalArgumentException {
        boolean isInput = clusterSpecifier.startsWith("in:") || clusterSpecifier.startsWith("server:");
        boolean isOutput = clusterSpecifier.startsWith("out:") || clusterSpecifier.startsWith("client:");

        Integer clusterId = (isInput || isOutput)
                ? parseClusterId(clusterSpecifier.substring(clusterSpecifier.indexOf(':') + 1))
                : parseClusterId(clusterSpecifier);

        if (isInput) {
            return endpoint.getInputCluster(clusterId);
        } else if (isOutput) {
            return endpoint.getOutputCluster(clusterId);
        } else {
            ZclCluster cluster = endpoint.getInputCluster(clusterId);
            return (cluster != null) ? cluster : endpoint.getOutputCluster(clusterId);
        }
    }

    /**
     * Parses a attribute ID as a string to an integer
     *
     * @param attribute a {@link String} name of the attribute
     * @return the attribute ID as an integer
     * @throws IllegalArgumentException
     */
    protected Integer parseAttribute(final String attribute) throws IllegalArgumentException {
        try {
            return getInteger(attribute);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Attribute '" + attribute + "' uses an invalid number format.");
        }
    }

    /**
     * Parses an integer as a string to an integer
     *
     * @param integer a {@link String} of the integer
     * @return the integer
     * @throws IllegalArgumentException
     */
    protected Integer parseInteger(final String integer) throws IllegalArgumentException {
        try {
            return getInteger(integer);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Integer '" + integer + "' uses an invalid number format.");
        }
    }

    /**
     * Default processing for command result.
     *
     * @param result the command result
     * @param out    the output
     * @return true if result is success
     */
    protected boolean processDefaultResponse(CommandResult result, PrintStream out) {
        if (result.isSuccess()) {
            out.println("Success response received.");
            return true;
        } else {
            out.println("Error executing command: " + result);
            return true;
        }
    }

    protected Object parseValue(String stringValue, ZclDataType zclDataType) {
        Object value = null;
        switch (zclDataType) {
            case BITMAP_16_BIT:
                value = Integer.parseInt(stringValue);
                break;
            case BITMAP_8_BIT:
                value = Integer.parseInt(stringValue);
                break;
            case BOOLEAN:
                value = Boolean.parseBoolean(stringValue);
                break;
            case CHARACTER_STRING:
                value = stringValue;
                break;
            case DATA_8_BIT:
                value = Integer.parseInt(stringValue);
                break;
            case ENUMERATION_16_BIT:
                value = Integer.parseInt(stringValue);
                break;
            case ENUMERATION_8_BIT:
                value = Integer.parseInt(stringValue);
                break;
            case IEEE_ADDRESS:
                value = new IeeeAddress(stringValue);
                break;
            case OCTET_STRING:
                value = stringValue;
                break;
            case SIGNED_8_BIT_INTEGER:
                value = Integer.parseInt(stringValue);
                break;
            case SIGNED_16_BIT_INTEGER:
                value = Integer.parseInt(stringValue);
                break;
            case SIGNED_32_BIT_INTEGER:
                value = Integer.parseInt(stringValue);
                break;
            case UNSIGNED_8_BIT_INTEGER:
                value = Integer.parseInt(stringValue);
                break;
            case UNSIGNED_16_BIT_INTEGER:
                value = Integer.parseInt(stringValue);
                break;
            case UNSIGNED_32_BIT_INTEGER:
                value = Integer.parseInt(stringValue);
                break;
            default:
                throw new IllegalArgumentException("Data type " + zclDataType + " is not supported.");
        }
        return value;
    }

    protected String printClusterId(int cluster) {
        return String.format("%04X", cluster);
    }

    protected String printAttributeId(int attribute) {
        return String.format("%5d", attribute);
    }

    protected String printZclDataType(ZclDataType dataType) {
        return String.format("%-25s", dataType);
    }

    private Integer getInteger(String string) {
        if (string.startsWith("0x")) {
            return Integer.parseInt(string.substring(2), 16);
        } else {
            return Integer.parseInt(string);
        }
    }
}
