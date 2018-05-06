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
     * Parses a cluster ID as a string to an integer
     *
     * @param cluster a {@link String} name of the cluster
     * @return the cluster ID as an integer
     * @throws IllegalArgumentException
     */
    protected Integer parseCluster(final String cluster) throws IllegalArgumentException {
        try {
            return getInteger(cluster);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Cluster '" + cluster + "' uses an invalid number format.");
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
            return Integer.parseInt(integer);
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
