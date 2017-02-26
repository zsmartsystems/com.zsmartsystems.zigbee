package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Management Bind Request value object class.
 * <p>
 * The Mgmt_Bind_req is generated from a Local Device wishing to retrieve the
 * contents of the Binding Table from the Remote Device. The destination
 * addressing on this command shall be unicast only and the destination address
 * must be that of a Primary binding table cache or source device holding its own
 * binding table.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementBindRequest extends ZdoRequest {
    /**
     * StartIndex command message field.
     */
    private Integer startIndex;

    /**
     * Default constructor.
     */
    public ManagementBindRequest() {
        clusterId = 0x0033;
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
        serializer.serialize(startIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        startIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ManagementBindRequest");
        builder.append(super.toString());
        builder.append(", startIndex=");
        builder.append(startIndex);
        return builder.toString();
    }

}
