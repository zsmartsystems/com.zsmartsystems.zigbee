package com.zsmartsystems.zigbee.dongle.ember.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetConfigurationValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetConfigurationValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetConfigurationValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetConfigurationValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction.EzspSingleResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction.EzspTransaction;

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
}
