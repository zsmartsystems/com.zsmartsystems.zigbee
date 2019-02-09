/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.zdo.command.PowerDescriptorResponse;

/**
 * Power Descriptor Request value object class.
 * <p>
 * <p>
 * The Power_Desc_req command is generated from a local device wishing to inquire as to the
 * power descriptor of a remote device. This command shall be unicast either to the remote
 * device itself or to an alternative device that contains the discovery information of the
 * remote device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class PowerDescriptorRequest extends ZdoRequest implements ZigBeeTransactionMatcher {
    /**
     * NWK Addr Of Interest command message field.
     */
    private Integer nwkAddrOfInterest;

    /**
     * Default constructor.
     */
    public PowerDescriptorRequest() {
        clusterId = 0x0003;
    }

    /**
     * Gets NWK Addr Of Interest.
     *
     * @return the NWK Addr Of Interest
     */
    public Integer getNwkAddrOfInterest() {
        return nwkAddrOfInterest;
    }

    /**
     * Sets NWK Addr Of Interest.
     *
     * @param nwkAddrOfInterest the NWK Addr Of Interest
     */
    public void setNwkAddrOfInterest(final Integer nwkAddrOfInterest) {
        this.nwkAddrOfInterest = nwkAddrOfInterest;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(nwkAddrOfInterest, ZclDataType.NWK_ADDRESS);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        nwkAddrOfInterest = (Integer) deserializer.deserialize(ZclDataType.NWK_ADDRESS);
    }

    @Override
    public boolean isTransactionMatch(ZigBeeCommand request, ZigBeeCommand response) {
        if (!(response instanceof PowerDescriptorResponse)) {
            return false;
        }

        return (((PowerDescriptorRequest) request).getNwkAddrOfInterest()
                .equals(((PowerDescriptorResponse) response).getNwkAddrOfInterest()));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(62);
        builder.append("PowerDescriptorRequest [");
        builder.append(super.toString());
        builder.append(", nwkAddrOfInterest=");
        builder.append(nwkAddrOfInterest);
        builder.append(']');
        return builder.toString();
    }

}
