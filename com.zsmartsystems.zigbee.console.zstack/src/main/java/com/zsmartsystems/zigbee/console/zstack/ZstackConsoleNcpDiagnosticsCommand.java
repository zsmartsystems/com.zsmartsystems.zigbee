/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.zstack;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.zstack.ZstackNcp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackDiagnosticAttribute;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZstackConsoleNcpDiagnosticsCommand extends ZstackConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "ncpdiags";
    }

    @Override
    public String getDescription() {
        return "Gets the NCP diagnostics counters.";
    }

    @Override
    public String getSyntax() {
        return "";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        ZstackNcp ncp = getZstackNcp(networkManager);
        Map<ZstackDiagnosticAttribute, Long> diagnostics = new HashMap<>();

        for (ZstackDiagnosticAttribute attribute : ZstackDiagnosticAttribute.values()) {
            if (attribute == ZstackDiagnosticAttribute.UNKNOWN) {
                continue;
            }

            Long value = ncp.getDiagnosticsAttribute(attribute);

            if (value == null) {
                continue;
            }
            diagnostics.put(attribute, value);
        }

        for (Entry<ZstackDiagnosticAttribute, Long> diagnosticsEntry : diagnostics.entrySet()) {
            out.println(String.format("%-40s  %d", diagnosticsEntry.getKey(), diagnosticsEntry.getValue()));
        }
    }

}
