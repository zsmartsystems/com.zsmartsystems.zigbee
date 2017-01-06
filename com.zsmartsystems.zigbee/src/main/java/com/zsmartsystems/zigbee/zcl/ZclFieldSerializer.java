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

import java.util.List;

import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * ZCL field serializer.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class ZclFieldSerializer {
    private ZigBeeSerializer serializer;

    public ZclFieldSerializer(ZigBeeSerializer serializer) {
        this.serializer = serializer;
    }

    /**
     * Serializes field value.
     *
     * @param value the field value
     * @param dataType the data type
     */
    public void serialize(final Object value, final ZclDataType dataType) {
        if (ZclListItemField.class.isAssignableFrom(dataType.getDataClass())) {
            final List<ZclListItemField> list = (List<ZclListItemField>) value;
            for (final ZclListItemField item : list) {
                item.serialize(serializer);
            }
            return;
        }

        serializer.appendZigBeeType(value, dataType);
    }

    /**
     * Gets payload.
     *
     * @return the payload
     */
    public int[] getPayload() {
        return serializer.getPayload();
    }
}
