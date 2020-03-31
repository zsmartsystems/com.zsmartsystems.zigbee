/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCertificate283k1Data;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCertificateData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCurrentSecurityBitmask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCurrentSecurityState;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyStruct;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyStructBitmask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberLibraryId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberLibraryStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberTransientKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspPolicyId;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;

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

        EmberNcp ncp = getEmberNcp(networkManager);

        EmberNetworkStatus networkState = ncp.getNetworkState();

        Integer securityLevel = ncp.getConfiguration(EzspConfigId.EZSP_CONFIG_SECURITY_LEVEL);
        Integer rejoinTimeout = ncp
                .getConfiguration(EzspConfigId.EZSP_CONFIG_TC_REJOINS_USING_WELL_KNOWN_KEY_TIMEOUT_S);
        Integer trustCentreCacheSize = ncp.getConfiguration(EzspConfigId.EZSP_CONFIG_TRUST_CENTER_ADDRESS_CACHE_SIZE);
        Integer keyTableSize = ncp.getConfiguration(EzspConfigId.EZSP_CONFIG_KEY_TABLE_SIZE);
        Integer transientKeyTimeout = ncp.getConfiguration(EzspConfigId.EZSP_CONFIG_TRANSIENT_KEY_TIMEOUT_S);
        EmberCurrentSecurityState securityState = ncp.getCurrentSecurityState();

        Integer trustCentrePolicy = ncp.getPolicy(EzspPolicyId.EZSP_TRUST_CENTER_POLICY);
        Integer appKeyPolicy = ncp.getPolicy(EzspPolicyId.EZSP_APP_KEY_REQUEST_POLICY);
        Integer trustCentreKeyPolicy = ncp.getPolicy(EzspPolicyId.EZSP_TC_KEY_REQUEST_POLICY);
        Integer trustCentreRejoinPolicy = ncp
                .getPolicy(EzspPolicyId.EZSP_TC_REJOINS_USING_WELL_KNOWN_KEY_POLICY);

        EmberKeyStruct networkKey = ncp.getKey(EmberKeyType.EMBER_CURRENT_NETWORK_KEY);
        EmberKeyStruct linkKey = ncp.getKey(EmberKeyType.EMBER_TRUST_CENTER_LINK_KEY);

        boolean libraryEcc = ncp
                .getLibraryStatus(EmberLibraryId.EMBER_ECC_LIBRARY) == EmberLibraryStatus.EMBER_LIBRARY_PRESENT;
        boolean libraryCbke = ncp
                .getLibraryStatus(EmberLibraryId.EMBER_CBKE_CORE_LIBRARY) == EmberLibraryStatus.EMBER_LIBRARY_PRESENT;
        boolean libraryCbke163k1 = ncp
                .getLibraryStatus(EmberLibraryId.EMBER_CBKE_LIBRARY) == EmberLibraryStatus.EMBER_LIBRARY_PRESENT;
        boolean libraryCbke283k1 = ncp
                .getLibraryStatus(EmberLibraryId.EMBER_CBKE_LIBRARY_283K1) == EmberLibraryStatus.EMBER_LIBRARY_PRESENT;

        EmberCertificateData certificate163k1 = ncp.getCertificateData();
        EmberCertificate283k1Data certificate283k1 = ncp.getCertificate283k1Data();

        ByteArray installCode = ncp.getMfgInstallationCode();

        List<EmberKeyStruct> keyTable = new ArrayList<>();
        for (int cnt = 0; cnt < keyTableSize; cnt++) {
            EmberKeyStruct key = ncp.getKeyTableEntry(cnt);
            if (key != null) {
                keyTable.add(key);
            }
        }

        List<EmberTransientKeyData> transientTable = new ArrayList<>();
        for (int cnt = 0; cnt < keyTableSize; cnt++) {
            EmberTransientKeyData key = ncp.getTransientLinkKeyIndex(cnt);
            if (key != null) {
                transientTable.add(key);
            }
        }

        out.println("Current Network State      : " + networkState);
        out.println("Trust Centre Address       : "
                + (securityState == null ? "" : securityState.getTrustCenterLongAddress()));
        out.println("Security level             : " + securityLevel);
        out.println("Rejoin timeout             : " + rejoinTimeout);
        out.println("Key table size             : " + keyTableSize);
        out.println("Trust Centre cache size    : " + trustCentreCacheSize);
        out.println("Transient key timeout      : " + transientKeyTimeout);
        out.println("Application Key Policy     : " + EzspDecisionId.getEzspDecisionId(appKeyPolicy));
        out.println("Trust Centre Policy        : " + String.format("%02X", trustCentrePolicy));
        out.println("Trust Centre Key Policy    : " + EzspDecisionId.getEzspDecisionId(trustCentreKeyPolicy));
        out.println("Trust Centre Rejoin Policy : " + EzspDecisionId.getEzspDecisionId(trustCentreRejoinPolicy));
        out.println(
                "Installation Code          : " + (installCode == null ? "" : printArray(installCode.getAsIntArray())));
        out.println("ECC Library Support        : " + libraryEcc);
        out.println("CBKE Library Support       : " + libraryCbke);
        out.println("CBKE 163k1 Library Support : " + libraryCbke163k1);
        out.println("CBKE 163k1 Certificate     : "
                + (certificate163k1 == null ? "No Certificate" : printArray(certificate163k1.getContents())));
        out.println("CBKE 283k1 Library Support : " + libraryCbke283k1);
        out.println("CBKE 283k1 Certificate     : "
                + (certificate283k1 == null ? "No Certificate" : printArray(certificate283k1.getContents())));
        out.print("Security state flags       : ");
        boolean first = true;
        if (securityState != null) {
            for (EmberCurrentSecurityBitmask state : securityState.getBitmask()) {
                if (!first) {
                    out.print("                           : ");
                }
                first = false;
                out.println(state);
            }
        }
        if (first) {
            out.println();
        }
        out.println();
        out.println(
                "Key Type                        IEEE Address      Key Data                          In Cnt    Out Cnt   Seq  Auth  Sleep  Timer");

        if (linkKey != null) {
            out.println(printKey(linkKey));
        }
        if (networkKey != null) {
            out.println(printKey(networkKey));
        }
        for (EmberKeyStruct key : keyTable) {
            out.println(printKey(key));
        }
        for (EmberTransientKeyData key : transientTable) {
            out.println(printTransientKey(key));
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
            builder.append(String.format("%3d  ", key.getSequenceNumber()));
        } else {
            builder.append("     ");
        }

        if (key.getBitmask().contains(EmberKeyStructBitmask.EMBER_KEY_IS_AUTHORIZED)) {
            builder.append("Yes   ");
        } else {
            builder.append("No    ");
        }

        if (key.getBitmask().contains(EmberKeyStructBitmask.EMBER_KEY_PARTNER_IS_SLEEPY)) {
            builder.append("Yes ");
        } else {
            builder.append("No  ");
        }

        return builder.toString();
    }

    private String printTransientKey(EmberTransientKeyData key) {
        StringBuilder builder = new StringBuilder(110);

        builder.append(String.format("%-30s  ", "EMBER_TRANSIENT_LINK_KEY"));

        builder.append(key.getEui64());
        builder.append("  ");

        for (int value : key.getKeyData().getContents()) {
            builder.append(String.format("%02X", value));
        }

        builder.append(String.format("  %08X  ", key.getIncomingFrameCounter()));
        builder.append("                            ");
        builder.append(String.format("% 5d", key.getCountdownTimerMs() / 100000));

        return builder.toString();
    }

    private String printArray(int[] array) {
        StringBuilder builder = new StringBuilder(110);

        for (int value : array) {
            builder.append(String.format("%02X", value));
        }

        return builder.toString();
    }
}
