/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.serialization;

import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * The interface for serialization of a ZCL frame to array of integers
 *
 * @author Chris Jackson
 */
public interface ZigBeeSerializer {

    /**
     * @param data {@link Object} containing the value to append
     * @param type {@link ZclDataType} to select of data has to be appended
     */
    public void appendZigBeeType(Object data, ZclDataType type);

    /**
     * @return a copy of the payload
     */
    public int[] getPayload();
}
