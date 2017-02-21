package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Request Own Location Command value object class.
 * <p>
 * Cluster: <b>RSSI Location</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class RequestOwnLocationCommand extends ZclCommand {
    /**
     * Requesting Address command message field.
     */
    private Long requestingAddress;

    /**
     * Default constructor.
     */
    public RequestOwnLocationCommand() {
        genericCommand = false;
        clusterId = 11;
        commandId = 7;
        commandDirection = false;
    }

    /**
     * Gets Requesting Address.
     *
     * @return the Requesting Address
     */
    public Long getRequestingAddress() {
        return requestingAddress;
    }

    /**
     * Sets Requesting Address.
     *
     * @param requestingAddress the Requesting Address
     */
    public void setRequestingAddress(final Long requestingAddress) {
        this.requestingAddress = requestingAddress;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(requestingAddress, ZclDataType.IEEE_ADDRESS);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        requestingAddress = (Long) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", requestingAddress=");
        builder.append(requestingAddress);
        return builder.toString();
    }

}
