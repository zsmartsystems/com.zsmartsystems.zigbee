/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.field;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Child Info structure included in Parent Announce command.
 */
public class ParentAnnounceChildInfo {

    /**
     * The IEEE address of the child bound to the parent.
     */
    private IeeeAddress ieeeAddress;

    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(ieeeAddress, ZclDataType.IEEE_ADDRESS);
    }

    public void deserialize(final ZigBeeDeserializer deserializer) {
        ieeeAddress = (IeeeAddress) deserializer.readZigBeeType(ZclDataType.IEEE_ADDRESS);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ieeeAddress == null) ? 0 : ieeeAddress.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ParentAnnounceChildInfo other = (ParentAnnounceChildInfo) obj;
        if (ieeeAddress == null) {
            if (other.ieeeAddress != null) {
                return false;
            }
        } else if (!ieeeAddress.equals(other.ieeeAddress)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(120);
        builder.append("ParentAnnounceChildInfo [ieeeAddress=");
        builder.append(ieeeAddress);
        builder.append("]");
        return builder.toString();
    }
}
