/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesResponse;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleAttributeWriteCommand extends ZigBeeConsoleAbstractCommand {

    private static final String ARRAY_START_MARKER = "{";
    private static final String ARRAY_END_MARKER = "}";

    @Override
    public String getCommand() {
        return "write";
    }

    @Override
    public String getDescription() {
        return "Write an attribute.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT CLUSTER ATTRIBUTEID (VALUE [TYPE] | {VALUE1 VALUE2 ... VALUEN} TYPE)";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length < 5) {
            throw new IllegalArgumentException("Too few arguments");
        }

        String endpointIdParam = args[1];
        String clusterIdParam = args[2];
        String attributeIdParam = args[3];
        String attributeValueParam = args[4];
        String attributeTypeParam = null;

        if (attributeValueParam.startsWith(ARRAY_START_MARKER)) {
            int cnt = 5;
            for (; cnt < args.length; cnt++) {
                attributeValueParam += " " + args[cnt];
                if (attributeValueParam.endsWith(ARRAY_END_MARKER)) {
                    break;
                }
            }
            if (!attributeValueParam.endsWith(ARRAY_END_MARKER)) {
                throw new IllegalArgumentException("Value defined as an array must be terminated with ']'");
            }
            if (args.length == cnt + 2) {
                attributeTypeParam = args[cnt + 1];
            } else {
                throw new IllegalArgumentException("Invalid number of arguments: Type missing");
            }
        } else if (args.length == 6) {
            attributeTypeParam = args[5];
        } else if (args.length > 6) {
            throw new IllegalArgumentException("Too many arguments");
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, endpointIdParam);
        final ZclCluster cluster = getCluster(endpoint, clusterIdParam);
        final Integer attributeId = parseAttribute(attributeIdParam);

        ZclDataType dataType = null;
        if (attributeTypeParam == null) {
            final ZclAttribute attribute = cluster.getAttribute(attributeId);
            if (attribute == null) {
                throw new IllegalArgumentException("Could not find attribute with ID " + attributeId);
            }
            dataType = attribute.getDataType();
        } else {
            dataType = ZclDataType.valueOf(attributeTypeParam);
            if (dataType == null) {
                dataType = ZclDataType.getType(parseInteger(attributeTypeParam));
            }
        }

        final Object value = parseValue(attributeValueParam, dataType);
        final CommandResult result = cluster.writeAttribute(attributeId, dataType, value)
                .get();
        if (result.isSuccess()) {
            final WriteAttributesResponse response = result.getResponse();
            final ZclStatus statusCode = response.getRecords().get(0).getStatus();
            if (statusCode == ZclStatus.SUCCESS) {
                out.println("Attribute value write success.");
            } else {
                out.println("Attribute value write error: " + statusCode);
            }
        } else {
            out.println("Error executing command: " + result);
        }
    }
}