package com.zsmartsystems.zigbee;

import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.FrequencyBandType;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.MacCapabilitiesType;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.ServerCapabilitiesType;

/**
 * Defines a ZigBee Node. A node is a physical entity on the network and will
 * contain one or more {@link ZigBeeDevice}s.
 * 
 * @author Chris Jackson
 *
 */
public class ZigBeeNode {
    private IeeeAddress ieeeAddress;
    private Integer networkAddress;
    private NodeDescriptor nodeDescriptor;
    private PowerDescriptor powerDescriptor;

    public void setIeeeAddress(IeeeAddress ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    public void setNetworkAddress(Integer networkAddress) {
        this.networkAddress = networkAddress;
    }

    public Integer getNetworkAddress() {
        return networkAddress;
    }

    public void setNodeDescriptor(NodeDescriptor nodeDescriptor) {
        this.nodeDescriptor = nodeDescriptor;
    }

    public NodeDescriptor getNodeDescriptor() {
        return nodeDescriptor;
    }

    public void setPowerDescriptor(PowerDescriptor powerDescriptor) {
        this.powerDescriptor = powerDescriptor;
    }

    public PowerDescriptor getPowerDescriptor() {
        return powerDescriptor;
    }

    public boolean isFullFuntionDevice() {
        return nodeDescriptor.getMacCapabilities().contains(MacCapabilitiesType.FullFunctionDevice);
    }

    public boolean isReducedFuntionDevice() {
        return nodeDescriptor.getMacCapabilities().contains(MacCapabilitiesType.ReducedFunctionDevice);
    }

    public boolean isSecurityCapable() {
        return nodeDescriptor.getMacCapabilities().contains(MacCapabilitiesType.SecurityCapable);
    }

    public boolean isPrimaryTrustCenter() {
        return nodeDescriptor.getServerCapabilities().contains(ServerCapabilitiesType.PrimaryTrustCenter);
    }

    public boolean supportsFrequencyBand868MHz() {
        return nodeDescriptor.getFrequencyBands().contains(FrequencyBandType.Freq868MHz);
    }

    public boolean supportsFrequencyBand902MHz() {
        return nodeDescriptor.getFrequencyBands().contains(FrequencyBandType.Freq902MHz);
    }

    public boolean supportsFrequencyBand2400MHz() {
        return nodeDescriptor.getFrequencyBands().contains(FrequencyBandType.Freq2400MHz);
    }

    @Override
    public String toString() {
        if (nodeDescriptor == null) {
            return "IEEE=" + ieeeAddress + " NWK=" + networkAddress;
        }
        return "IEEE=" + ieeeAddress + " NWK=" + networkAddress + " Type=" + nodeDescriptor.getLogicalType() + " Bands="
                + nodeDescriptor.getFrequencyBands();
    }
}
