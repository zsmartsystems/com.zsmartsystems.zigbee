package com.zsmartsystems.zigbee.dongle.ember.ezsp;

/**
 * Configures endpoint information on the NCP. The NCP does not remember these settings after a reset. Endpoints can be
 * added by the Host after the NCP has reset. Once the status of the stack changes to EMBER_NETWORK_UP, endpoints can no
 * longer be added and this command will respond with EZSP_ERROR_INVALID_CALL.
 *
 * @author Chris Jackson
 *
 */
public class EzspAddEndpointResponse extends EzspFrameResponse {
    /**
     * 
     * @param inputBuffer
     *            the received response data
     */
    public EzspAddEndpointResponse(int[] inputBuffer) {
        super(inputBuffer);

        emberStatus = inputEmberStatus();
    }

    @Override
    public String toString() {
        return "EzspAddEndpoint RESPONSE [" + emberStatus + "]";
    }
}
