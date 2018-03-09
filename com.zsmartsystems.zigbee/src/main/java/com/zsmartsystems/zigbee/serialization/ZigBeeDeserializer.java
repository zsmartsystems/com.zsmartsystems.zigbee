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
 * The interface for deserialization of ZCL frame fields on array of integers
 *
 * @author Chris Jackson
 */
public interface ZigBeeDeserializer {

    public boolean isEndOfStream();

    public Object readZigBeeType(ZclDataType type);

    public int getPosition();

    public void skip(int bytes);

    int getSize();
}
