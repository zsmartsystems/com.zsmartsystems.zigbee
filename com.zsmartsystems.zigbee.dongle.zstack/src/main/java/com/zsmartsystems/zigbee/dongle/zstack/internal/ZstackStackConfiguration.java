/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.zsmartsystems.zigbee.dongle.zstack.ZstackNcp;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackResponseCode;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackConfigId;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;

/**
 * This class provides utility functions to configure, and read the configuration from the Ember stack.
 *
 * @author Chris Jackson
 *
 */
public class ZstackStackConfiguration {
    /**
     * The {@link ZstackNcp} used to send the EZSP frames to the NCP
     */
    private ZstackNcp ncp;

    /**
     * Constructor to set the {@link ZstackNcp}
     *
     * @param ncp the {@link ZstackNcp} used to communicate with the NCP
     */
    public ZstackStackConfiguration(ZstackNcp ncp) {
        this.ncp = ncp;
    }

    /**
     * Configuration utility. Takes a {@link Map} of {@link ConfigId} to {@link ByteArray} and will work through
     * setting them before returning.
     *
     * @param configuration {@link Map} of {@link ConfigId} to {@link Integer} with configuration to set
     * @return true if all configuration were set successfully
     */
    public boolean setConfiguration(Map<ZstackConfigId, int[]> configuration) {
        boolean success = true;

        for (Entry<ZstackConfigId, int[]> config : configuration.entrySet()) {
            if (ncp.writeConfiguration(config.getKey(), config.getValue()) != ZstackResponseCode.SUCCESS) {
                success = false;
            }
        }
        return success;
    }

    /**
     * Configuration utility. Takes a {@link Set} of {@link EzspConfigId} and will work through
     * requesting them before returning.
     *
     * @param configuration {@link Set} of {@link ConfigId} to request
     * @return map of configuration data mapping {@link ConfigId} to int[]. Value will be null if error
     *         occurred.
     */
    public Map<ZstackConfigId, int[]> getConfiguration(Set<ZstackConfigId> configuration) {
        Map<ZstackConfigId, int[]> response = new HashMap<>();

        for (ZstackConfigId configId : configuration) {
            response.put(configId, ncp.readConfiguration(configId));
        }

        return response;
    }
}
