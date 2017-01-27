package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

/**
 * The parameters of a ZigBee network
 *
 * @author Chris Jackson
 *
 */
public class EmberZigbeeNetwork extends EmberStructure {
    /**
     * The 802.15.4 channel associated with the network.
     */
    private int channel;

    /**
     * The network's PAN identifier.
     */
    private int panId;

    /**
     * The network's extended PAN identifier.
     */
    private int[] extendedPanId;

    /**
     * Whether the network is allowing MAC associations.
     */
    private boolean allowingJoin;

    /**
     * The Stack Profile associated with the network.
     */
    private int stackProfile;

    /**
     * The instance of the Network.
     */
    private int nwkUpdateId;

    public EmberZigbeeNetwork(int[] inBuffer, int inPosition) {
        super(inBuffer, inPosition);

        channel = inputUInt8();
        panId = inputUInt16();
        extendedPanId = inputUInt8Array(8);
        allowingJoin = inputBool();
        stackProfile = inputUInt16();
        nwkUpdateId = inputUInt8();
    }

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
