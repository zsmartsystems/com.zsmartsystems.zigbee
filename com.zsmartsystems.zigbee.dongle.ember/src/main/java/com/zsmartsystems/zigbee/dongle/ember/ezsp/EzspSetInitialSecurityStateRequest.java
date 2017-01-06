package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import java.util.Arrays;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberInitialSecurityState;

/**
 * Sets the security state that will be used by the device when it forms or joins the network. This call should not be
 * used when restoring saved network state via networkInit as this will result in a loss of security data and will cause
 * communication problems when the device re-enters the network.
 *
 * @author Chris Jackson
 *
 */
public class EzspSetInitialSecurityStateRequest extends EzspFrameRequest {
    /**
     * The security configuration to be set.
     */
    private EmberInitialSecurityState state;

    /**
     * Creates an EZSP <i>setInitialSecurityState</i> request frame
     * 
     */
    public EzspSetInitialSecurityStateRequest() {
        super(FRAME_ID_SET_INITIAL_SECURITY_STATE);
    }

    public EmberInitialSecurityState getState() {
        return state;
    }

    public void setState(EmberInitialSecurityState state) {
        this.state = state;
    }

    @Override
    public int[] getOutputBuffer() {
        initialiseOutputBuffer();
        outputStructure(state);
        return Arrays.copyOfRange(buffer, 0, length);
    }

    @Override
    public boolean processResponse(EzspFrameResponse ezspResponse) {
        if (getSequenceNumber() != ezspResponse.getSequenceNumber()) {
            return false;
        }
        if (getFrameId() != ezspResponse.getFrameId()) {
            return false;
        }

        emberStatus = ezspResponse.getEmberStatus();

        return true;
    }

}
