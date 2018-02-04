/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.ember.internal.ash.AshFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetConfigurationValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetConfigurationValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetPolicyRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspGetPolicyResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetConfigurationValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetConfigurationValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetPolicyRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspSetPolicyResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspPolicyId;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspStatus;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.transaction.EzspSingleResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.transaction.EzspTransaction;

/**
 * This class provides utility functions to configure, and read the configuration from the Ember stack.
 *
 * @author Chris Jackson
 *
 */
public class EmberStackConfiguration {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(EmberStackConfiguration.class);

    private AshFrameHandler ashHandler;

    /**
     * Constructor to set the {@link AshFrameHandler}
     *
     * @param ashHandler the {@link AshFrameHandler} used to communicate with the NCP
     */
    public EmberStackConfiguration(AshFrameHandler ashHandler) {
        this.ashHandler = ashHandler;
    }

    /**
     * Set a configuration value
     *
     * @param configId the {@link EzspConfigId} to set
     * @param value the value to set
     * @return true if the configuration returns ok
     */
    public boolean setConfiguration(EzspConfigId configId, Integer value) {
        EzspSetConfigurationValueRequest configValue = new EzspSetConfigurationValueRequest();
        configValue.setConfigId(configId);
        configValue.setValue(value);
        logger.debug(configValue.toString());

        EzspTransaction configTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(configValue, EzspSetConfigurationValueResponse.class));
        EzspSetConfigurationValueResponse configResponse = (EzspSetConfigurationValueResponse) configTransaction
                .getResponse();
        logger.debug(configResponse.toString());

        return configResponse.getStatus() == EzspStatus.EZSP_SUCCESS;
    }

    /**
     * Get a configuration value
     *
     * @param configId the {@link EzspConfigId} to set
     * @return the configuration value as {@link Integer} or null on error
     */
    public Integer getConfiguration(EzspConfigId configId) {
        EzspGetConfigurationValueRequest configValue = new EzspGetConfigurationValueRequest();
        configValue.setConfigId(configId);

        EzspTransaction configTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(configValue, EzspGetConfigurationValueResponse.class));
        EzspGetConfigurationValueResponse configResponse = (EzspGetConfigurationValueResponse) configTransaction
                .getResponse();
        logger.debug(configResponse.toString());

        if (configResponse.getStatus() != EzspStatus.EZSP_SUCCESS) {
            return null;
        }

        return configResponse.getValue();
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

        for (EzspConfigId configId : configuration.keySet()) {
            if (!setConfiguration(configId, configuration.get(configId))) {
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
        Map<EzspConfigId, Integer> response = new HashMap<EzspConfigId, Integer>();

        for (EzspConfigId configId : configuration) {
            response.put(configId, getConfiguration(configId));
        }

        return response;
    }

    /**
     * Set a policy used by the NCP to make fast decisions.
     *
     * @param policyId the {@link EzspPolicyId} to set
     * @param decisionId the {@link EzspDecisionId} to set to
     * @return true if the policy setting was successful
     */
    public boolean setPolicy(EzspPolicyId policyId, EzspDecisionId decisionId) {
        EzspSetPolicyRequest setPolicyRequest = new EzspSetPolicyRequest();
        setPolicyRequest.setPolicyId(policyId);
        setPolicyRequest.setDecisionId(decisionId);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(setPolicyRequest,
                EzspSetPolicyResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspSetPolicyResponse setPolicyResponse = (EzspSetPolicyResponse) transaction.getResponse();
        logger.debug(setPolicyResponse.toString());
        if (setPolicyResponse.getStatus() != EzspStatus.EZSP_SUCCESS) {
            logger.debug("Error during setting policy: {}", setPolicyResponse);
            return false;
        }

        return true;
    }

    /**
     * Get a policy used by the NCP to make fast decisions.
     *
     * @param policyId the {@link EzspPolicyId} to set
     * @return the returned {@link EzspDecisionId} if the policy was retrieved successfully or null if there was an
     *         error
     */
    public EzspDecisionId getPolicy(EzspPolicyId policyId) {
        EzspGetPolicyRequest getPolicyRequest = new EzspGetPolicyRequest();
        getPolicyRequest.setPolicyId(policyId);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(getPolicyRequest,
                EzspGetPolicyResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspGetPolicyResponse getPolicyResponse = (EzspGetPolicyResponse) transaction.getResponse();
        logger.debug(getPolicyResponse.toString());
        if (getPolicyResponse.getStatus() != EzspStatus.EZSP_SUCCESS) {
            logger.debug("Error getting policy: {}", getPolicyResponse);
            return null;
        }

        return getPolicyResponse.getDecisionId();
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

        for (EzspPolicyId policyId : policies.keySet()) {
            if (!setPolicy(policyId, policies.get(policyId))) {
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
     * @return map of configuration data mapping {@link EzspPolicyId} to {@link EzspDecisionId}. Value will be null if
     *         error occurred.
     */
    public Map<EzspPolicyId, EzspDecisionId> getPolicy(Set<EzspPolicyId> policies) {
        Map<EzspPolicyId, EzspDecisionId> response = new HashMap<EzspPolicyId, EzspDecisionId>();

        for (EzspPolicyId policyId : policies) {
            response.put(policyId, getPolicy(policyId));
        }

        return response;
    }

}
