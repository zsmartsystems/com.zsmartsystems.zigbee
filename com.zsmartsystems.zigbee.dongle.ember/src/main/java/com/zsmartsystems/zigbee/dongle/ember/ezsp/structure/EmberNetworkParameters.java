package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

/**
 * Defines the network parameters for an Ember network
 *
 * @author Chris Jackson
 *
 */
public class EmberNetworkParameters extends EmberStructure {

    /**
     * The network's extended PAN identifier.
     */
    private int[] extendedPanId;

    /**
     * The network's PAN identifier.
     */
    private int panId;

    /**
     * A power setting, in dBm.
     */
    private int radioTxPower;

    /**
     * A radio channel.
     */
    private int radioChannel;

    /**
     * The method used to initially join the network.
     */
    private EmberJoinMethod joinMethod;

    /**
     * NWK Manager ID. The ID of the network manager in the current network.
     * This may only be set at joining when using EMBER_USE_NWK_COMMISSIONING as
     * the join method.
     */
    private int nwkManagerId;

    /**
     * NWK Update ID. The value of the ZigBee nwkUpdateId known by the stack.
     * This is used to determine the newest instance of the network after a PAN
     * ID or channel change. This may only be set at joining when using
     * EMBER_USE_NWK_COMMISSIONING as the join method.
     */
    private int nwkUpdateId;

    /**
     * NWK channel mask. The list of preferred channels that the NWK manager has
     * told this device to use when searching for the network. This may only be
     * set at joining when using EMBER_USE_NWK_COMMISSIONING as the join method.
     */
    private int channels;

    public EmberNetworkParameters() {
    }

    public EmberNetworkParameters(int[] inBuffer, int inPosition) {
        super(inBuffer, inPosition);

        extendedPanId = inputUInt8Array(8);
        panId = inputUInt16();
        radioTxPower = inputUInt8();
        radioChannel = inputUInt8();
        joinMethod = EmberJoinMethod.getEmberJoinMethod(inputUInt8());
        nwkManagerId = inputUInt16();
        nwkUpdateId = inputUInt8();
        channels = inputUInt32();
    }

    public int[] getExtendedPanId() {
        return extendedPanId;
    }

    public void setExtendedPanId(int[] extendedPanId) {
        this.extendedPanId = extendedPanId;
    }

    public int getPanId() {
        return panId;
    }

    public void setPanId(int panId) {
        this.panId = panId;
    }

    public int getRadioTxPower() {
        return radioTxPower;
    }

    public void setRadioTxPower(int radioTxPower) {
        this.radioTxPower = radioTxPower;
    }

    public int getRadioChannel() {
        return radioChannel;
    }

    public void setRadioChannel(int radioChannel) {
        this.radioChannel = radioChannel;
    }

    public EmberJoinMethod getJoinMethod() {
        return joinMethod;
    }

    public void setJoinMethod(EmberJoinMethod joinMethod) {
        this.joinMethod = joinMethod;
    }

    public int getNwkManagerId() {
        return nwkManagerId;
    }

    /**
     * NWK Manager ID. The ID of the network manager in the current network.
     * This may only be set at joining when using EMBER_USE_NWK_COMMISSIONING as
     * the join method.
     *
     * @param nwkManagerId
     */
    public void setNwkManagerId(int nwkManagerId) {
        this.nwkManagerId = nwkManagerId;
    }

    public int getNwkUpdateId() {
        return nwkUpdateId;
    }

    /**
     * NWK Update ID. The value of the ZigBee nwkUpdateId known by the stack.
     * This is used to determine the newest instance of the network after a PAN
     * ID or channel change. This may only be set at joining when using
     * EMBER_USE_NWK_COMMISSIONING as the join method.
     *
     * @param nwkUpdateId
     */
    public void setNwkUpdateId(int nwkUpdateId) {
        this.nwkUpdateId = nwkUpdateId;
    }

    public int getChannels() {
        return channels;
    }

    /**
     * NWK channel mask. The list of preferred channels that the NWK manager has
     * told this device to use when searching for the network. This may only be
     * set at joining when using EMBER_USE_NWK_COMMISSIONING as the join method.
     *
     * @param channels
     */
    public void setChannels(int channels) {
        this.channels = channels;
    }

    @Override
    public int[] getOutputBuffer() {
        resetOutputBuffer();

        outputUInt8Array(extendedPanId);
        outputUInt16(panId);
        outputUInt8(radioTxPower);
        outputUInt8(radioChannel);
        outputUInt8(joinMethod.getKey());
        outputUInt16(nwkManagerId);
        outputUInt8(nwkUpdateId);
        outputUInt32(channels);

        return super.getOutputBuffer();
    }

    @Override
    public String toString() {
        return "EmberNetworkParameters [extendedPanId="
                + String.format("%02X %02X %02X %02X %02X %02X %02X %02X", extendedPanId[0], extendedPanId[1],
                        extendedPanId[2], extendedPanId[3], extendedPanId[4], extendedPanId[5], extendedPanId[6],
                        extendedPanId[7])
                + ", panId=" + panId + ", radioTxPower=" + radioTxPower + ", radioChannel=" + radioChannel
                + ", joinMethod=" + joinMethod + ", nwkManagerId=" + nwkManagerId + ", nwkUpdateId=" + nwkUpdateId
                + ", channels=" + channels + "]";
    }
}
