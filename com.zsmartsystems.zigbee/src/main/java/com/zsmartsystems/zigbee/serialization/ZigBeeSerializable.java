/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.serialization;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;

/**
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeeSerializable {
    /**
     * Serialization method
     * 
     * @param serializer the {@link ZclFieldSerializer}
     */
    public void serialize(final ZclFieldSerializer serializer);

    /**
     * Deserialization method
     * 
     * @param deserializer the {@link ZclFieldDeserializer}
     */
    public void deserialize(final ZclFieldDeserializer deserializer);

}
