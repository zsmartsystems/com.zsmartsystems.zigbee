package com.zsmartsystems.zigbee.dongle.ember.ezsp;

/**
 * Returns the current network parameters.
 * 
 * @author Chris Jackson
 *
 */
public class EzspGetNetworkParametersRequest extends EzspFrameRequest {
    public EzspGetNetworkParametersRequest() {
        super(FRAME_ID_GET_NETWORK_PARAMETERS);
    }

    @Override
    public boolean processResponse(EzspFrameResponse ezspResponse) {
        if (getSequenceNumber() != ezspResponse.getSequenceNumber()) {
            return false;
        }
        if (getFrameId() != ezspResponse.getFrameId()) {
            return false;
        }

        response = ezspResponse;
        emberStatus = ezspResponse.getEmberStatus();

        return true;
    }

    @Override
    public String toString() {
        return "EzspGetNetworkParameters ["
                + emberStatus
                + ((response != null) ? (", nodeType=" + ((EzspGetNetworkParametersResponse) response).getNodeType()
                        + " parameters=" + ((EzspGetNetworkParametersResponse) response).getParameters()) : "") + "]";
    }
}
