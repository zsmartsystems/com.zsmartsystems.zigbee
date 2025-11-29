/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberAddressConstants;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;

/**
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpAddressTableCommand extends EmberConsoleAbstractCommand {
    private class AddressTableEntry {
        public int index;
        public IeeeAddress ieeeAddress;
        public int networkAddress;
        public boolean active;

        public String getNetworkAddress() {
            if (networkAddress == EmberAddressConstants.EMBER_UNKNOWN_NODE_ID.getKey()) {
                return "UNKNOWN";
            } else if (networkAddress == EmberAddressConstants.EMBER_DISCOVERY_ACTIVE_NODE_ID.getKey()) {
                return "DISCOVERING";
            } else {
                return String.format("%5d [%04X]", networkAddress, networkAddress);
            }
        }
    }

    @Override
    public String getCommand() {
        return "ncpaddrtable";
    }

    @Override
    public String getDescription() {
        return "Manages the NCP address table";
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
        EmberNcp ncp = getEmberNcp(networkManager);

        Integer addrTableSize = ncp.getConfiguration(EzspConfigId.EZSP_CONFIG_ADDRESS_TABLE_SIZE);
        if (addrTableSize == null) {
            out.println("Error getting NCP address table size!");
            return;
        }

        List<AddressTableEntry> addressTable = new ArrayList<>();

        int totalActiveEntries = 0;
        for (int index = 0; index < addrTableSize; index++) {
            int nodeId = ncp.getAddressTableRemoteNodeId(index);
            if (nodeId == EmberAddressConstants.EMBER_TABLE_ENTRY_UNUSED_NODE_ID.getKey()) {
                continue;
            }

            AddressTableEntry tableEntry = new AddressTableEntry();
            tableEntry.index = index;
            tableEntry.networkAddress = nodeId;
            tableEntry.ieeeAddress = ncp.getAddressTableRemoteEui64(index);
            tableEntry.active = ncp.addressTableEntryIsActive(index);

            addressTable.add(tableEntry);
        }

        out.println("Address Table Size : " + addrTableSize);
        out.println("Active Entries     : " + totalActiveEntries);

        out.println("IDX   State    NWK Address    IEEE Address");
        for (AddressTableEntry entry : addressTable) {
            out.println(String.format("%2d    %6s   %12s  %s", entry.index, (entry.active ? "ACTIVE" : ""),
                    entry.getNetworkAddress(),
                    entry.ieeeAddress));
        }
    }
}
