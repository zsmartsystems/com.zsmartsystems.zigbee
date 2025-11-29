/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleAttributeAddCommand extends ZigBeeConsoleAbstractCommand {

    @Override
    public String getCommand() {
        return "attadd";
    }

    @Override
    public String getDescription() {
        return "Add an attribute.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT CLUSTER ATTRIBUTEID MANUFACTURER TYPE NAME";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length < 6) {
            throw new IllegalArgumentException("Too few arguments");
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        final ZclCluster cluster = getCluster(endpoint, args[2]);
        final Integer attributeId = parseAttribute(args[3]);
        final Integer manufacturerId = parseAttribute(args[4]);
        final ZclDataType dataType = ZclDataType.valueOf(args[5]);

        List<String> nameList = new ArrayList<>();
        for (int cnt = 6; cnt < args.length; cnt++) {
            nameList.add(args[cnt]);
        }
        String name = nameList.stream().collect(Collectors.joining(" "));

        final ZclAttribute attribute = new ZclAttribute(cluster, attributeId, name, dataType, false, true, true, true,
                manufacturerId);

        Set<ZclAttribute> attributes = new HashSet<>();
        attributes.add(attribute);

        cluster.addAttributes(attributes);
        out.println("Added attribute " + attribute);
    }
}