package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.CommandResponseMatcher;
import com.zsmartsystems.zigbee.zdo.command.ActiveEndpointsResponse;

/**
 * Active Endpoints Request value object class.
 * <p>
 * The Active_EP_req command is generated from a local device wishing to acquire
 * the list of endpoints on a remote device with simple descriptors. This command
 * shall be unicast either to the remote device itself or to an alternative device that
 * contains the discovery information of the remote device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ActiveEndpointsRequest extends ZdoRequest implements CommandResponseMatcher {
    /**
     * NWKAddrOfInterest command message field.
     */
    private Integer nwkAddrOfInterest;

    /**
     * Default constructor.
     */
    public ActiveEndpointsRequest() {
        clusterId = 0x0005;
    }

    /**
     * Gets NWKAddrOfInterest.
     *
     * @return the NWKAddrOfInterest
     */
    public Integer getNwkAddrOfInterest() {
        return nwkAddrOfInterest;
    }

    /**
     * Sets NWKAddrOfInterest.
     *
     * @param nwkAddrOfInterest the NWKAddrOfInterest
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
    public boolean isMatch(Command request, Command response) {
        if (!(response instanceof ActiveEndpointsResponse)) {
            return false;
        }

        if (!((ActiveEndpointsRequest) request).getNwkAddrOfInterest()
                .equals(((ActiveEndpointsResponse) response).getNwkAddrOfInterest())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ActiveEndpointsRequest ");
        builder.append(super.toString());
        builder.append(", nwkAddrOfInterest=");
        builder.append(nwkAddrOfInterest);
        return builder.toString();
    }

}
