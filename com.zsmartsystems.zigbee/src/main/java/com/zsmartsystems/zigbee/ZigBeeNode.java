package com.zsmartsystems.zigbee;

import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.FrequencyBandType;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.LogicalType;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.MacCapabilitiesType;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.ServerCapabilitiesType;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor;

/**
 * Defines a ZigBee Node. A node is a physical entity on the network and will
 * contain one or more {@link ZigBeeDevice}s.
 * <p>
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNode {
    private IeeeAddress ieeeAddress;
    private Integer networkAddress;
    private NodeDescriptor nodeDescriptor;
    private PowerDescriptor powerDescriptor;

    /**
     * Sets the {@link IeeeAddress} of the device
     *
     * param ieeeAddress the {@link IeeeAddress} of the device
     */
    public void setIeeeAddress(IeeeAddress ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    /**
     * Gets the {@link IeeeAddress} of the device
     *
     * @return the {@link IeeeAddress} of the device
     */
    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    /**
     * Sets the 16 bit network address of the device.
     *
     * @param networkAddress
     */
    public void setNetworkAddress(Integer networkAddress) {
        this.networkAddress = networkAddress;
    }

    /**
     * Gets the 16 bit network address of the device.
     *
     * @return networkAddress
     */
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

    /**
     * A FFD (Full Function Device) is a device that has full levels of functionality.
     * It can be used for sending and receiving data, but it can also route data from other nodes.
     * FFDs are Coordinators and Routers
     *
     * @return true if the device is a Full Function Device
     */
    public boolean isFullFuntionDevice() {
        return nodeDescriptor.getMacCapabilities().contains(MacCapabilitiesType.FullFunctionDevice);
    }

    /**
     * An RFD (Reduced Function Device) is a device that has a reduced level of functionality.
     * Typically it is an end node which may be typically a sensor or switch. RFDs can only talk to FFDs
     * as they contain no routing functionality. These devices can be very low power devices because they
     * do not need to route other traffic and they can be put into a sleep mode when they are not in use.
     *
     * @return true if the device is a Reduced Function Device
     */
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

    /**
     * Gets the {@link LogicalType} of the device.
     * <p>
     * Possible types are -:
     * <ul>
     * <li>{@link LogicalType#Coordinator}
     * <li>{@link LogicalType#Router}
     * <li>{@link LogicalType#EndDevice}
     * <ul>
     *
     * @return the {@link LogicalType} of the device
     */
    public LogicalType getLogicalType() {
        return nodeDescriptor.getLogicalType();
    }

    @Override
    public String toString() {
        if (nodeDescriptor == null) {
            return "IEEE=" + ieeeAddress + " NWK=" + networkAddress;
        }
        return "IEEE=" + ieeeAddress + ", NWK=" + networkAddress + ", Type=" + nodeDescriptor.getLogicalType()
                + ", Bands=" + nodeDescriptor.getFrequencyBands();
    }
}
