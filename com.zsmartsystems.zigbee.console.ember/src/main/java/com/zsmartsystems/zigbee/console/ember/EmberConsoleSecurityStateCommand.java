/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberCurrentSecurityBitmask;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberCurrentSecurityState;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberKeyStruct;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberKeyStructBitmask;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberKeyType;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspPolicyId;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;

/**
 * Console command that displays the current security configuration for the Ember NCP.
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleSecurityStateCommand extends EmberConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "ncpsecuritystate";
    }

    @Override
    public String getDescription() {
        return "Gets the current NCP security state and configuration";
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
        if (networkManager.getNetworkState() != ZigBeeTransportState.ONLINE) {
            throw new IllegalStateException(
                    "Network state is " + networkManager.getNetworkState() + ". Unable to show security data.");
        }
        EmberNcp ncp = getEmberNcp(networkManager);

        Integer securityLevel = ncp.getConfiguration(EzspConfigId.EZSP_CONFIG_SECURITY_LEVEL);
        Integer rejoinTimeout = ncp
                .getConfiguration(EzspConfigId.EZSP_CONFIG_TC_REJOINS_USING_WELL_KNOWN_KEY_TIMEOUT_S);
        Integer trustCentreCacheSize = ncp.getConfiguration(EzspConfigId.EZSP_CONFIG_TRUST_CENTER_ADDRESS_CACHE_SIZE);
        Integer keyTableSize = ncp.getConfiguration(EzspConfigId.EZSP_CONFIG_KEY_TABLE_SIZE);
        Integer transientKeyTimeout = ncp.getConfiguration(EzspConfigId.EZSP_CONFIG_TRANSIENT_KEY_TIMEOUT_S);
        EmberCurrentSecurityState securityState = ncp.getCurrentSecurityState();

        EzspDecisionId trustCentrePolicy = ncp.getPolicy(EzspPolicyId.EZSP_TRUST_CENTER_POLICY);
        EzspDecisionId appKeyPolicy = ncp.getPolicy(EzspPolicyId.EZSP_APP_KEY_REQUEST_POLICY);
        EzspDecisionId trustCentreKeyPolicy = ncp.getPolicy(EzspPolicyId.EZSP_TC_KEY_REQUEST_POLICY);
        EzspDecisionId trustCentreRejoinPolicy = ncp
                .getPolicy(EzspPolicyId.EZSP_TC_REJOINS_USING_WELL_KNOWN_KEY_POLICY);

        EmberKeyStruct networkKey = ncp.getKey(EmberKeyType.EMBER_CURRENT_NETWORK_KEY);
        EmberKeyStruct linkKey = ncp.getKey(EmberKeyType.EMBER_TRUST_CENTER_LINK_KEY);

        List<EmberKeyStruct> keyTable = new ArrayList<>();
        for (int cnt = 0; cnt < keyTableSize; cnt++) {
            EmberKeyStruct key = ncp.getKeyTableEntry(cnt);
            if (key != null) {
                keyTable.add(key);
            }
        }

        out.println("Trust Centre Address       : " + securityState.getTrustCenterLongAddress());
        out.println("Security level             : " + securityLevel);
        out.println("Rejoin timeout             : " + rejoinTimeout);
        out.println("Key table size             : " + keyTableSize);
        out.println("Trust Centre cache size    : " + trustCentreCacheSize);
        out.println("Transient key timeout      : " + transientKeyTimeout);
        out.println("Application Key Policy     : " + appKeyPolicy);
        out.println("Trust Centre Policy        : " + trustCentrePolicy);
        out.println("Trust Centre Key Policy    : " + trustCentreKeyPolicy);
        out.println("Trust Centre Rejoin Policy : " + trustCentreRejoinPolicy);
        out.print("Security state flags       : ");
        boolean first = true;
        for (EmberCurrentSecurityBitmask state : securityState.getBitmask()) {
            if (!first) {
                out.print("                           : ");
            }
            first = false;
            out.println(state);
        }
        if (first) {
            out.println();
        }
        out.println();
        out.println(
                "Key Type                        IEEE Address      Key Data                          In Cnt    Out Cnt   Seq");

        out.println(printKey(linkKey));
        out.println(printKey(networkKey));

        for (EmberKeyStruct key : keyTable) {
            out.println(printKey(key));
        }
    }

    private String printKey(EmberKeyStruct key) {
        StringBuilder builder = new StringBuilder(110);

        builder.append(String.format("%-30s  ", key.getType()));

        if (key.getBitmask().contains(EmberKeyStructBitmask.EMBER_KEY_HAS_PARTNER_EUI64)) {
            builder.append(key.getPartnerEUI64());
        } else {
            builder.append("                ");
        }
        builder.append("  ");

        for (int value : key.getKey().getContents()) {
            builder.append(String.format("%02X", value));
        }

        if (key.getBitmask().contains(EmberKeyStructBitmask.EMBER_KEY_HAS_INCOMING_FRAME_COUNTER)) {
            builder.append(String.format("  %08X  ", key.getIncomingFrameCounter()));
        } else {
            builder.append("            ");
        }

        if (key.getBitmask().contains(EmberKeyStructBitmask.EMBER_KEY_HAS_OUTGOING_FRAME_COUNTER)) {
            builder.append(String.format("%08X  ", key.getOutgoingFrameCounter()));
        } else {
            builder.append("          ");
        }

        if (key.getBitmask().contains(EmberKeyStructBitmask.EMBER_KEY_HAS_SEQUENCE_NUMBER)) {
            builder.append(String.format("%3d", key.getSequenceNumber()));
        } else {
            builder.append("   ");
        }

        return builder.toString();
    }
}
