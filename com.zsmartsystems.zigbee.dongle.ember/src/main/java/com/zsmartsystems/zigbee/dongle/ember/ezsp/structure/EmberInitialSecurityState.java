package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.ArrayList;
import java.util.List;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 * The security data used to set the configuration for the stack, or the retrieved configuration currently in use.
 *
 * @author Chris Jackson
 *
 */
public class EmberInitialSecurityState extends EmberStructure {

    /**
     * A bitmask indicating the security state used to indicate what the security configuration will be when the device
     * forms or joins the network.
     */
    private List<EmberInitialSecurityBitmask> bitmask = new ArrayList<EmberInitialSecurityBitmask>();

    /**
     * The pre-configured Key data that should be used when forming or joining the network. The security bitmask must be
     * set with the EMBER_HAVE_PRECONFIGURED_KEY bit to indicate that the key contains valid data.
     */
    private EmberKeyData preconfiguredKey;

    /**
     * The Network Key that should be used by the Trust Center when it forms the network, or the Network Key currently
     * in use by a joined device. The security bitmask must be set with EMBER_HAVE_NETWORK_KEY to indicate that the key
     * contains valid data.
     */
    private EmberKeyData networkKey;

    /**
     * The sequence number associated with the network key. This is only valid if the EMBER_HAVE_NETWORK_KEY has been
     * set in the security bitmask.
     */
    private int networkKeySequenceNumber;

    /**
     * This is the long address of the trust center on the network that will be joined. It is usually NOT set prior to
     * joining the network and instead it is learned during the joining message exchange. This field is only examined if
     * EMBER_HAVE_TRUST_CENTER_EUI64 is set in the EmberInitialSecurityState::bitmask. Most devices should clear that
     * bit and leave this field alone. This field must be set when using commissioning mode.
     */
    private IeeeAddress preconfiguredTrustCenterEui64;

    public EmberInitialSecurityState() {
    }

    public EmberInitialSecurityState(int[] inBuffer, int inPosition) {
        super(inBuffer, inPosition);

        int mask = inputUInt16();
        preconfiguredKey = inputEmberKeyData();
        networkKey = inputEmberKeyData();
        networkKeySequenceNumber = inputUInt8();
        preconfiguredTrustCenterEui64 = inputEmberEui64();

        for (EmberInitialSecurityBitmask bit : EmberInitialSecurityBitmask.values()) {
            if ((mask & bit.getKey()) != 0) {
                bitmask.add(bit);
            }
        }
    }

    @Override
    public int[] getOutputBuffer() {
        resetOutputBuffer();

        int mask = 0;

        for (EmberInitialSecurityBitmask bit : bitmask) {
            mask |= bit.getKey();
        }

        if (preconfiguredKey == null) {
            preconfiguredKey = new EmberKeyData();
        }

        if (networkKey == null) {
            networkKey = new EmberKeyData();
        }

        if (preconfiguredTrustCenterEui64 == null) {
            preconfiguredTrustCenterEui64 = new IeeeAddress(0);
        }

        outputUInt16(mask);
        outputEmberKeyData(preconfiguredKey);
        outputEmberKeyData(networkKey);
        outputUInt8(networkKeySequenceNumber);
        outputEmberEui64(preconfiguredTrustCenterEui64);

        return super.getOutputBuffer();
    }

    /**
     * @return the bitmask
     */
    public List<EmberInitialSecurityBitmask> getBitmask() {
        return bitmask;
    }

    /**
     * @param bitmask the bitmask to set
     */
    public void setBitmask(List<EmberInitialSecurityBitmask> bitmask) {
        this.bitmask = bitmask;
    }

    public void addBitmask(EmberInitialSecurityBitmask bitmask) {
        if (this.bitmask.contains(bitmask)) {
            return;
        }

        this.bitmask.add(bitmask);
    }

    /**
     * @return the preconfiguredKey
     */
    public EmberKeyData getPreconfiguredKey() {
        return preconfiguredKey;
    }

    /**
     * @param preconfiguredKey the preconfiguredKey to set
     */
    public void setPreconfiguredKey(EmberKeyData preconfiguredKey) {
        this.preconfiguredKey = preconfiguredKey;
    }

    /**
     * @return the networkKey
     */
    public EmberKeyData getNetworkKey() {
        return networkKey;
    }

    /**
     * @param networkKey the networkKey to set
     */
    public void setNetworkKey(EmberKeyData networkKey) {
        this.networkKey = networkKey;
    }

    /**
     * @return the networkKeySequenceNumber
     */
    public int getNetworkKeySequenceNumber() {
        return networkKeySequenceNumber;
    }

    /**
     * @param networkKeySequenceNumber the networkKeySequenceNumber to set
     */
    public void setNetworkKeySequenceNumber(int networkKeySequenceNumber) {
        this.networkKeySequenceNumber = networkKeySequenceNumber;
    }

    /**
     * @return the preconfiguredTrustCenterEui64
     */
    public IeeeAddress getPreconfiguredTrustCenterEui64() {
        return preconfiguredTrustCenterEui64;
    }

    /**
     * @param preconfiguredTrustCenterEui64 the preconfiguredTrustCenterEui64 to set
     */
    public void setPreconfiguredTrustCenterEui64(IeeeAddress preconfiguredTrustCenterEui64) {
        this.preconfiguredTrustCenterEui64 = preconfiguredTrustCenterEui64;
    }

}
