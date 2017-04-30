package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.CommandResponseMatcher;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressResponse;

/**
 * IEEE Address Request value object class.
 * <p>
 * The IEEE_addr_req is generated from a Local Device wishing to inquire as to the
 * 64-bit IEEE address of the Remote Device based on their known 16-bit address.
 * The destination addressing on this command shall be unicast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class IeeeAddressRequest extends ZdoRequest implements CommandResponseMatcher {
    /**
     * NWKAddrOfInterest command message field.
     */
    private Integer nwkAddrOfInterest;

    /**
     * RequestType command message field.
     */
    private Integer requestType;

    /**
     * StartIndex command message field.
     */
    private Integer startIndex;

    /**
     * Default constructor.
     */
    public IeeeAddressRequest() {
        clusterId = 0x0001;
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

    /**
     * Gets RequestType.
     *
     * @return the RequestType
     */
    public Integer getRequestType() {
        return requestType;
    }

    /**
     * Sets RequestType.
     *
     * @param requestType the RequestType
     */
    public void setRequestType(final Integer requestType) {
        this.requestType = requestType;
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

        serializer.serialize(nwkAddrOfInterest, ZclDataType.NWK_ADDRESS);
        serializer.serialize(requestType, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(startIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        nwkAddrOfInterest = (Integer) deserializer.deserialize(ZclDataType.NWK_ADDRESS);
        requestType = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        startIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public boolean isMatch(Command request, Command response) {
        if (!(response instanceof IeeeAddressResponse)) {
            return false;
        }

        return (((IeeeAddressRequest) request).getDestinationAddress()
                .equals(((IeeeAddressResponse) response).getSourceAddress()));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("IeeeAddressRequest [");
        builder.append(super.toString());
        builder.append(", nwkAddrOfInterest=");
        builder.append(nwkAddrOfInterest);
        builder.append(", requestType=");
        builder.append(requestType);
        builder.append(", startIndex=");
        builder.append(startIndex);
        builder.append("]");
        return builder.toString();
    }

}
