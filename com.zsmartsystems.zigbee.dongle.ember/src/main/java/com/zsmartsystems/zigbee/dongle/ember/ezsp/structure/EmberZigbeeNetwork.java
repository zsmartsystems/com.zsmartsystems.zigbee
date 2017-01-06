package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

/**
 * The parameters of a ZigBee network
 * 
 * @author Chris Jackson
 *
 */
public class EmberZigbeeNetwork extends EmberStructure {

    public EmberZigbeeNetwork(int[] inBuffer, int inPosition) {
        super(inBuffer, inPosition);

        channel = inputUInt8();
        panId = inputUInt16();
        extendedPanId = inputUInt8Array(8);
        allowingJoin = inputBool();
        stackProfile = inputUInt16();
        nwkUpdateId = inputUInt8();
    }

    /**
     * The 802.15.4 channel associated with the network.
     */
    int channel;

    /**
     * The network's PAN identifier.
     */
    int panId;

    /**
     * The network's extended PAN identifier.
     */
    int[] extendedPanId;

    /**
     * Whether the network is allowing MAC associations.
     */
    boolean allowingJoin;

    /**
     * The Stack Profile associated with the network.
     */
    int stackProfile;

    /**
     * The instance of the Network.
     */
    int nwkUpdateId;

    @Override
    public int[] getOutputBuffer() {
        resetOutputBuffer();

        outputUInt8(channel);
        outputUInt16(panId);
        outputUInt8Array(extendedPanId);
        outputBool(allowingJoin);
        outputUInt16(stackProfile);
        outputUInt8(nwkUpdateId);

        return super.getOutputBuffer();
    }
}
