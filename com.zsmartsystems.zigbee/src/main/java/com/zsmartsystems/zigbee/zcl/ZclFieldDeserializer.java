/**
 * Copyright 2016 Tommi S.E. Laukkanen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
     */
    public ZclFieldDeserializer(final ZigBeeDeserializer deserializer) {
        this.deserializer = deserializer;
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
     * @param dataType the data type of the field.
     * @return the value
     */
    public Object deserialize(final ZclDataType dataType) {
        if (ZclListItemField.class.isAssignableFrom(dataType.getDataClass())) {
            final Class dataTypeClass = dataType.getDataClass();
            final List<ZclListItemField> list = new ArrayList<ZclListItemField>();
            while (deserializer.getSize() - deserializer.getPosition() > 0) {
                final ZclListItemField item;
                try {
                    item = (ZclListItemField) dataTypeClass.newInstance();
                } catch (final Exception e) {
                    throw new IllegalArgumentException("Error deserializing field: " + dataType.getLabel(), e);
                }
                item.deserialize(this.deserializer);
                list.add(item);
            }
            return list;
        }

        return deserializer.readZigBeeType(dataType);
    }

}
