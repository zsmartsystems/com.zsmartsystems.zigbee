package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNodeType;

/**
 * Returns the current network parameters.
 * 
 * @author Chris Jackson
 *
 */
public class EzspGetNetworkParametersResponse extends EzspFrameResponse {
    /**
     * The NodeType that the NCP is set to
     */
    private EmberNodeType nodeType;

    /**
     * Identifies which configuration value to read.
     */
    private EmberNetworkParameters parameters;

    /**
     * 
     * @param configId
     *            Identifies which configuration value to read.
     */
    public EzspGetNetworkParametersResponse(int[] inputBuffer) {
        super(inputBuffer);

        emberStatus = inputEmberStatus();
        nodeType = inputEmberNodeType();
        parameters = (EmberNetworkParameters) inputStructure(EmberNetworkParameters.class);
    }

    public EmberNetworkParameters getParameters() {
        return parameters;
    }

    public EmberNodeType getNodeType() {
        return nodeType;
    }

    @Override
    public String toString() {
        return "EzspGetNetworkParameters RESPONSE [" + emberStatus + ": nodeType=" + nodeType + ", parameters="
                + parameters + "]";
    }
}
