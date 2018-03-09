/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
