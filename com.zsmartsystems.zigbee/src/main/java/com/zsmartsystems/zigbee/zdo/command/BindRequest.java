/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeBroadcastDestination;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import com.zsmartsystems.zigbee.zdo.command.BindResponse;
import com.zsmartsystems.zigbee.zdo.field.BindingTable;

/**
 * Bind Request value object class.
 * <p>
 * <p>
 * The Bind_req is generated from a Local Device wishing to create a Binding Table entry for the
 * source and destination addresses contained as parameters. The destination addressing on
 * this command shall be unicast only, and the destination address shall be that of a Primary
 * binding table cache or to the SrcAddress itself. The Binding Manager is optionally
 * supported on the source device (unless that device is also the ZigBee Coordinator) so that
 * device shall issue a NOT_SUPPORTED status to the Bind_req if not supported.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2022-06-14T10:37:18Z")
public class BindRequest extends ZdoRequest implements ZigBeeTransactionMatcher {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x0021;

    /**
     * Binding Table Entry command message field.
     * <p>
     * The binding tsble entry.
     */
    private BindingTable bindingTableEntry;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public BindRequest() {
        clusterId = CLUSTER_ID;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param bindingTableEntry {@link BindingTable} Binding Table Entry
     */
    public BindRequest(
            BindingTable bindingTableEntry) {

        clusterId = CLUSTER_ID;

        this.bindingTableEntry = bindingTableEntry;
    }

    /**
     * Gets Binding Table Entry.
     * <p>
     * The binding tsble entry.
     *
     * @return the Binding Table Entry
     */
    public BindingTable getBindingTableEntry() {
        return bindingTableEntry;
    }

    /**
     * Sets Binding Table Entry.
     * <p>
     * The binding tsble entry.
     *
     * @param bindingTableEntry the Binding Table Entry
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setBindingTableEntry(final BindingTable bindingTableEntry) {
        this.bindingTableEntry = bindingTableEntry;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(bindingTableEntry, ZclDataType.BINDING_TABLE);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        bindingTableEntry = deserializer.deserialize(ZclDataType.BINDING_TABLE);
    }

    @Override
    public boolean isTransactionMatch(ZigBeeCommand request, ZigBeeCommand response) {
        if (!(response instanceof BindResponse)) {
            return false;
        }

        ZigBeeAddress destinationAddress = ((ZdoRequest) request).getDestinationAddress();
        ZigBeeAddress sourceAddress = ((ZdoResponse) response).getSourceAddress();
        ZigBeeEndpointAddress localCoordinator = new ZigBeeEndpointAddress(0, 0);

        if(!ZigBeeBroadcastDestination.isBroadcast(destinationAddress.getAddress())) {
            if (!localCoordinator.equals(sourceAddress) && !destinationAddress.equals(sourceAddress)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(51);
        builder.append("BindRequest [");
        builder.append(super.toString());
        builder.append(", bindingTableEntry=");
        builder.append(bindingTableEntry);
        builder.append(']');
        return builder.toString();
    }

}
