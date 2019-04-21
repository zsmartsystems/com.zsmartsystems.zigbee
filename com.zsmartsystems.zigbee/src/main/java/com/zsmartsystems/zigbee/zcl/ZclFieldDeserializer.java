/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import java.util.ArrayList;
import java.util.List;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * ZCL field deserializer.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class ZclFieldDeserializer {
    /**
     * Delegate deserializer.
     */
    private ZigBeeDeserializer deserializer;

    /**
     * Constructor for setting the payload and start index.
     *
     * @param deserializer the {@link ZigBeeDeserializer} to use for deserialization
     */
    public ZclFieldDeserializer(final ZigBeeDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    /**
     * Gets the remaining payload length
     *
     * @return the number of bytes remaining in the input stream
     */
    public int getRemainingLength() {
        return deserializer.getSize() - deserializer.getPosition();
    }

    /**
     * Checks if there are further bytes to be read
     *
     * @return true if we are at the end of the input stream
     */
    public boolean isEndOfStream() {
        return deserializer.isEndOfStream();
    }

    /**
     * Deserializes a field.
     *
     * @param dataType the {@link ZclDataType} of the field.
     * @return the value
     */
    public Object deserialize(final ZclDataType dataType) {
        if (ZclListItemField.class.isAssignableFrom(dataType.getDataClass())) {
            final Class<?> dataTypeClass = dataType.getDataClass();
            final List<ZclListItemField> list = new ArrayList<ZclListItemField>();
            try {
                while (deserializer.getSize() - deserializer.getPosition() > 0) {
                    final ZclListItemField item;
                    try {
                        item = (ZclListItemField) dataTypeClass.newInstance();
                    } catch (final Exception e) {
                        throw new IllegalArgumentException("Error deserializing field: " + dataType.toString(), e);
                    }
                    item.deserialize(this.deserializer);
                    list.add(item);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // Eat the exception - this terminates the list!
            }
            return list;
        }

        return deserializer.readZigBeeType(dataType);
    }

}
