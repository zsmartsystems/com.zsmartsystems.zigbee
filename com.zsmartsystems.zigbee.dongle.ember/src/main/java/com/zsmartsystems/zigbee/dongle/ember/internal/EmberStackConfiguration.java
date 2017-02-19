package com.zsmartsystems.zigbee.dongle.ember.internal;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetConfigurationValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetConfigurationValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction.EzspSingleResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction.EzspTransaction;

/**
 * This class provides utility functions to configure the Ember stack.
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

    private boolean setConfiguration(EzspConfigId configId, Integer value) {
        EzspSetConfigurationValueRequest configValue = new EzspSetConfigurationValueRequest();
        configValue.setConfigId(configId.getKey());
        configValue.setValue(value);

        EzspTransaction configTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(configValue, EzspSetConfigurationValueResponse.class));
        EzspSetConfigurationValueResponse configResponse = (EzspSetConfigurationValueResponse) configTransaction
                .getResponse();
        logger.debug(configResponse.toString());

        return configResponse.getStatus() == EzspStatus.EZSP_SUCCESS;
    }

    /**
     * Configuration utility. Takes a {@link Map} of {@link EzspConfigId} to {@link Integer} and will work through
     * setting them before returning.
     *
     * @param configuration {@link Map} of {@link EzspConfigId} to {@link Integer} with configuration to set
     * @return true if all configuration were set successfully
     */
    public boolean doConfiguration(Map<EzspConfigId, Integer> configuration) {
        boolean success = true;

        for (EzspConfigId configId : configuration.keySet()) {
            if (!setConfiguration(configId, configuration.get(configId))) {
                success = false;
            }
        }
        return success;
    }
}
