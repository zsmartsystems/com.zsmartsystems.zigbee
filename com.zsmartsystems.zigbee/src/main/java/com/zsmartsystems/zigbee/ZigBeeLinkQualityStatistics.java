/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * An interface that allows clients to retrieve link quality information available from a node
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeeLinkQualityStatistics {
    /**
     * Returns the LQI value from the last received packet
     *
     * @return the last received LQI value
     */
    public Integer getLastReceivedLqi();

    /**
     * Returns the RSSI value from the last received packet
     *
     * @return the last received RSSI value in dBm
     */
    public Integer getLastReceivedRssi();

}
