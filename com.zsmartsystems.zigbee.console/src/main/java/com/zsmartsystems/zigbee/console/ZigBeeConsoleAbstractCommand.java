/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.field.ZclArrayList;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Abstract class for ZigBee Console Commands
 *
 * @author Chris Jackson
 *
 */
public abstract class ZigBeeConsoleAbstractCommand implements ZigBeeConsoleCommand {
    protected ZigBeeConsoleArgument arguments = initializeArguments();

    protected abstract ZigBeeConsoleArgument initializeArguments();

    @Override
    public ZigBeeConsoleArgument getArguments() {
        return arguments;
    }

    /**
     * Gets a human readable syntax for this command
     *
     * @return a {@link String} defining the possible options
     */
    @Override
    public String getSyntax() {
        if (arguments == null) {
            return "";
        }
        return getSyntax("", arguments);
    }

    private String getSyntax(String indent, ZigBeeConsoleArgument argument) {
        StringBuilder builder = new StringBuilder();
        do {
            if (argument.hasSuboptions()) {
                int len = 0;
                for (ZigBeeConsoleArgument suboption : argument.getSuboptions()) {
                    if (suboption.getConstant().length() > len) {
                        len = suboption.getConstant().length();
                    }
                }
                for (ZigBeeConsoleArgument suboption : argument.getSuboptions()) {
                    builder.append(getSyntax(indent, suboption));
                }
            } else {
                if (argument.isOptional()) {
                    builder.append('[');
                }
                if (argument.getConstant() != null) {
                    builder.append(argument.getConstant());
                } else {
                    builder.append(argument.getType());
                }

                if (argument.isOptional()) {
                    builder.append(']');
                }
            }
            argument = argument.getNext();
            if (argument != null) {
                builder.append(' ');
            }
        } while (argument != null);
        builder.append('\n');
        return builder.toString();
    }

    /**
     * Gets a {@link ZigBeeNode}
     *
     * @param networkManager the {@link ZigBeeNetworkManager}
     * @param nodeId a {@link String} with the node Id
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
     * @param endpointId the device identifier
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
     * @param endpoint the ZigBee endpoint to get the cluster from (must be non-null)
     * @param clusterSpecifier a cluster specified as described above (must be non-null)
     * @return the specified cluster provided by the endpoint or null if no such cluster is found
     * @throws IllegalArgumentException if the clusterSpecifier uses an invalid number format, or if no cluster is found
     */
    protected ZclCluster getCluster(final ZigBeeEndpoint endpoint, final String clusterSpecifier)
            throws IllegalArgumentException {
        boolean isInput;
        boolean isOutput;
        String clusterIdString;

        if (clusterSpecifier.contains(":")) {
            String prefix = clusterSpecifier.substring(0, clusterSpecifier.indexOf(':'));
            isInput = prefix.equalsIgnoreCase("in") || prefix.equalsIgnoreCase("server");
            isOutput = prefix.equalsIgnoreCase("out") || prefix.equalsIgnoreCase("client");

            if (!(isInput || isOutput)) {
                throw new IllegalArgumentException(
                        "The prefix of the cluster specifier must be 'in', 'out', 'server', or 'client', but it was: "
                                + prefix);
            }

            clusterIdString = clusterSpecifier.substring(clusterSpecifier.indexOf(':') + 1);
        } else {
            isInput = false;
            isOutput = false;
            clusterIdString = clusterSpecifier;
        }

        Integer clusterId = parseClusterId(clusterIdString);

        ZclCluster result;

        if (isInput) {
            result = endpoint.getInputCluster(clusterId);
        } else if (isOutput) {
            result = endpoint.getOutputCluster(clusterId);
        } else {
            ZclCluster cluster = endpoint.getInputCluster(clusterId);
            result = (cluster != null) ? cluster : endpoint.getOutputCluster(clusterId);
        }

        if (result != null) {
            return result;
        } else {
            throw new IllegalArgumentException("A cluster specified by " + clusterSpecifier
                    + " is not found for endpoint " + endpoint.getEndpointId());
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
     * @param out the output
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
        if (stringValue.startsWith("[") && stringValue.endsWith("]")) {
            switch (zclDataType) {
                case OCTET_STRING:
                case DATA_8_BIT:
                    break;
                default:
                    throw new IllegalArgumentException("Array data type " + zclDataType + " is not supported.");
            }

            String[] stringValueArray = stringValue.substring(1, stringValue.length() - 1).split(" ");

            List<Object> values = new ArrayList<>(stringValueArray.length);
            for (String value : stringValueArray) {
                values.add(parseValueElement(value, zclDataType));
            }

            return new ZclArrayList(zclDataType, values);
        }
        return parseValueElement(stringValue, zclDataType);
    }

    private Object parseValueElement(String stringValue, ZclDataType zclDataType) {
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
                if (stringValue.length() % 2 != 0) {
                    throw new IllegalArgumentException("OCTET_STRING must have an even number of hex bytes.");
                }

                byte[] val = new byte[stringValue.length() / 2];
                for (int strCnt = 0; strCnt < stringValue.length() / 2; strCnt++) {
                    int index = strCnt * 2;
                    val[strCnt] = (byte) Integer.parseInt(stringValue.substring(index, index + 2), 16);
                }
                value = new ByteArray(val);
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

    /**
     * @param cluster a ZCL cluster
     * @return a String containing information about the cluster, example 'OnOff server cluster 0x00A4'
     */
    protected String printCluster(ZclCluster cluster) {
        String typePrefix = cluster.isServer() ? "server" : "client";
        return String.format("%s cluster %s (%s)", typePrefix, cluster.getClusterName(),
                printClusterId(cluster.getClusterId()));
    }

    private Integer getInteger(String string) {
        if (string.startsWith("0x")) {
            return Integer.parseInt(string.substring(2), 16);
        } else {
            return Integer.parseInt(string);
        }
    }
}
