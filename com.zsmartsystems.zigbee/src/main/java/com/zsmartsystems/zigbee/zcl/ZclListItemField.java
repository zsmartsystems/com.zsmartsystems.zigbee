/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;

/**
 * ZclSerializableField class for non primitive field types.
 *
 * @author Tommi S.E Laukkanen
 * @author Chris Jackson
 */
public interface ZclListItemField {
    /**
     * Serializes the field.
     *
     * @param serializer the serializer
     */
    void serialize(ZigBeeSerializer serializer);

    /**
     * Deserializes the field.
     *
     * @param deserializer the deserializer.
     */
    void deserialize(ZigBeeDeserializer deserializer);
}
