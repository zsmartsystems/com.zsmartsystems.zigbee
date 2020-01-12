/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspPolicyId;

/**
 * Reads or writes an NCP {@link EzspPolicyId}
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpPolicyCommand extends EmberConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "ncppolicy";
    }

    @Override
    public String getDescription() {
        return "Read or write an NCP policy decision";
    }

    @Override
    public String getSyntax() {
        return "[POLICYID] [DECISIONID]";
    }

    @Override
    public String getHelp() {
        return "POLICYID is the Ember NCP policy enumeration\n" + "DECISIONID is the value to write\n"
                + "If DECISIONID is not defined, then the policy will be read.\n"
                + "If no arguments are supplied then all policies will be displayed.";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length > 3) {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }

        EmberNcp ncp = getEmberNcp(networkManager);

        if (args.length == 1) {
            Map<EzspPolicyId, EzspDecisionId> policies = new TreeMap<>();
            for (EzspPolicyId policyId : EzspPolicyId.values()) {
                if (policyId == EzspPolicyId.UNKNOWN) {
                    continue;
                }

                policies.put(policyId, ncp.getPolicy(policyId));
            }

            for (Entry<EzspPolicyId, EzspDecisionId> policy : policies.entrySet()) {
                out.print(String.format("%-55s", policy.getKey()));
                if (policy.getValue() != null) {
                    out.print(policy.getValue());
                }
                out.println();
            }

            return;
        }

        EzspPolicyId policyId = EzspPolicyId.valueOf(args[1].toUpperCase());

        if (args.length == 2) {
            EzspDecisionId decisionId = ncp.getPolicy(policyId);
            if (decisionId == null) {
                out.println("Error reading Ember NCP policy " + policyId.toString());
            } else {
                out.println("Ember NCP policy " + policyId.toString() + " is " + decisionId);
            }
        } else {
            EzspDecisionId decisionId = EzspDecisionId.valueOf(args[2].toUpperCase());
            String result;
            switch (ncp.setPolicy(policyId, decisionId)) {
                case EZSP_SUCCESS:
                    result = "successful.";
                    break;
                case EZSP_ERROR_OUT_OF_MEMORY:
                    result = "unsuccessful. NCP is out of memory.";
                    break;
                case EZSP_ERROR_INVALID_VALUE:
                    result = "unsuccessful. Specified decision ID was invalid.";
                    break;
                case EZSP_ERROR_INVALID_ID:
                    result = "unsuccessful. Policy ID is unknown.";
                    break;
                case EZSP_ERROR_INVALID_CALL:
                    result = "unsuccessful. Policy can not be modified in current NCP state.";
                    break;
                default:
                    result = "UNKNOWN";
                    break;
            }
            out.println("Writing Ember NCP policy " + policyId.toString() + " was " + result);
        }
    }
}
