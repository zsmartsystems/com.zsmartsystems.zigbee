/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

/**
 * A listener interface to return data to a user of the Manufacturing Library
 *
 * @author Chris Jackson
 *
 */
public interface EmberMfglibListener {
    /**
     *
     * @param linkQuality
     * @param rssi
     * @param data
     */
    void emberMfgLibPacketReceived(int linkQuality, int rssi, int[] data);
}
