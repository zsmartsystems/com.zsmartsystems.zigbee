package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.CommandResponseMatcher;
import com.zsmartsystems.zigbee.zdo.command.ManagementRoutingResponse;

/**
 * Management Routing Request value object class.
 * <p>
 * The Mgmt_Rtg_req is generated from a Local Device wishing to retrieve the
 * contents of the Routing Table from the Remote Device. The destination
 * addressing on this command shall be unicast only and the destination address
 * must be that of the ZigBee Router or ZigBee Coordinator.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementRoutingRequest extends ZdoRequest implements CommandResponseMatcher {
    /**
     * StartIndex command message field.
     */
    private Integer startIndex;

    /**
     * Default constructor.
     */
    public ManagementRoutingRequest() {
        clusterId = 0x0032;
    }

    /**
     * Gets StartIndex.
     *
     * @return the StartIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * Sets StartIndex.
     *
     * @param startIndex the StartIndex
     */
    public void setStartIndex(final Integer startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(startIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        startIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public boolean isMatch(Command request, Command response) {
        if (!(response instanceof ManagementRoutingResponse)) {
            return false;
        }

        return (((ManagementRoutingRequest) request).getDestinationAddress()
                .equals(((ManagementRoutingResponse) response).getSourceAddress()));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ManagementRoutingRequest ");
        builder.append(super.toString());
        builder.append(", startIndex=");
        builder.append(startIndex);
        return builder.toString();
    }

}
