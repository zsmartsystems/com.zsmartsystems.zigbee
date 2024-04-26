/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspPolicyId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;

/**
 * This class provides utility functions to configure, and read the configuration from the Ember stack.
 *
 * @author Chris Jackson
 *
 */
public class EmberStackConfiguration {
    /**
     * The {@link EmberNcp} used to send the EZSP frames to the NCP
     */
    private EmberNcp ncp;

    /**
     * Constructor to set the {@link EmberNcp}
     *
     * @param ncp the {@link EmberNcp} used to communicate with the NCP
     */
    public EmberStackConfiguration(EmberNcp ncp) {
        this.ncp = ncp;
    }

    /**
     * Configuration utility. Takes a {@link Map} of {@link EzspConfigId} to {@link Integer} and will work through
     * setting them before returning.
     *
     * @param configuration {@link Map} of {@link EzspConfigId} to {@link Integer} with configuration to set
     * @return true if all configuration were set successfully
     */
    public boolean setConfiguration(Map<EzspConfigId, Integer> configuration) {
        boolean success = true;

        for (Entry<EzspConfigId, Integer> config : configuration.entrySet()) {
            if (ncp.setConfiguration(config.getKey(), config.getValue()) != EzspStatus.EZSP_SUCCESS) {
                success = false;
            }
        }
        return success;
    }

    /**
     * Configuration utility. Takes a {@link Set} of {@link EzspConfigId} and will work through
     * requesting them before returning.
     *
     * @param configuration {@link Set} of {@link EzspConfigId} to request
     * @return map of configuration data mapping {@link EzspConfigId} to {@link Integer}. Value will be null if error
     *         occurred.
     */
    public Map<EzspConfigId, Integer> getConfiguration(Set<EzspConfigId> configuration) {
        Map<EzspConfigId, Integer> response = new HashMap<>();

        for (EzspConfigId configId : configuration) {
            response.put(configId, ncp.getConfiguration(configId));
        }

        return response;
    }

    /**
     * Configuration utility. Takes a {@link Map} of {@link EzspConfigId} to {@link EzspDecisionId} and will work
     * through setting them before returning.
     *
     * @param policies {@link Map} of {@link EzspPolicyId} to {@link EzspDecisionId} with configuration to set
     * @return true if all policies were set successfully
     */
    public boolean setPolicy(Map<EzspPolicyId, EzspDecisionId> policies) {
        boolean success = true;

        for (Entry<EzspPolicyId, EzspDecisionId> policy : policies.entrySet()) {
            if (ncp.setPolicy(policy.getKey(), policy.getValue()) != EzspStatus.EZSP_SUCCESS) {
                success = false;
            }
        }
        return success;
    }

    /**
     * Configuration utility. Takes a {@link Set} of {@link EzspPolicyId} and will work through
     * requesting them before returning.
     *
     * @param policies {@link Set} of {@link EzspPolicyId} to request
     * @return map of configuration data mapping {@link EzspPolicyId} to {@link Integer}. Value will be null if
     *         error occurred.
     */
    public Map<EzspPolicyId, Integer> getPolicy(Set<EzspPolicyId> policies) {
        Map<EzspPolicyId, Integer> response = new HashMap<>();

        for (EzspPolicyId policyId : policies) {
            response.put(policyId, ncp.getPolicy(policyId));
        }

        return response;
    }

}
