/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import com.zsmartsystems.zigbee.ZigBeeLinkQualityStatistics;

/**
 * Handler to record and manage link quality information for a node
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNodeLinkQualityHandler implements ZigBeeLinkQualityStatistics {
    Integer lastRssi;
    Integer lastLqi;

    /**
     * Updates the Link Quality Indicator value with the value from the latest received packet
     *
     * @param lqi the last received LQI value
     */
    public void updateReceivedLqi(Integer lqi) {
        if (lqi == null) {
            return;
        }
        lastLqi = lqi;
    }

    /**
     * Updates the Received Signal Strength Indicator value with the value from the latest received packet
     *
     * @param rssi the last received RSSI value in dBm
     */
    public void updateReceivedRssi(Integer rssi) {
        if (rssi == null) {
            return;
        }
        lastRssi = rssi;
    }

    @Override
    public Integer getLastReceivedLqi() {
        return lastLqi;
    }

    @Override
    public Integer getLastReceivedRssi() {
        return lastRssi;
    }
}
