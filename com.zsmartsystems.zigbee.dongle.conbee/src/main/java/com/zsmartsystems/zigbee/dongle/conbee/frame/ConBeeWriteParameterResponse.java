package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeWriteParameterResponse extends ConBeeFrameResponse {
    private ConBeeNetworkParameter parameter;

    ConBeeWriteParameterResponse(final int[] response) {
        super(response);

        if (deserializeUInt8() != WRITE_PARAMETER) {
            throw new IllegalArgumentException();
        }
        sequence = deserializeUInt8();
        status = deserializeStatus();
        deserializeUInt16();
        deserializeUInt16();
        parameter = ConBeeNetworkParameter.getParameterType(deserializeUInt8());
    }

    /**
     * @return the parameter
     */
    public ConBeeNetworkParameter getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WriteParameterResponse [parameter=");
        builder.append(parameter);
        builder.append(']');
        return builder.toString();
    }

}
