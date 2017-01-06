package com.zsmartsystems.zigbee.zdo.descriptors;

import java.util.HashSet;
import java.util.Set;

/**
 * Describes the node descriptor
 * 
 * @author Chris Jackson
 *
 */
public class NodeDescriptor {
    private final int apsFlags;
    private final int bufferSize;
    private final boolean complexDescriptorAvailable;
    private final int manufacturerCode;
    private final LogicalType logicalType;
    private final Set<ServerCapabilitiesType> serverCapabilities;
    private final int transferSize;
    private final boolean userDescriptorAvailable;
    private final Set<FrequencyBandType> frequencyBands;
    private final Set<MacCapabilitiesType> macCapabilities;

    public enum LogicalType {
        Coordinator,
        Router,
        EndDevice
    }

    public enum FrequencyBandType {
        Freq868MHz,
        Freq902MHz,
        Freq2400MHz
    }

    public enum ServerCapabilitiesType {
        PrimaryTrustCenter,
        BackupTrustCenter,
        PrimaryBindingTableCache,
        BackupBindingTableCache,
        PrimaryDiscoveryCache,
        BackupDiscoveryCache,
        NetworkManager
    }

    public enum DescriptorCapabilityType {
        ExtendedActiveEnpointList,
        ExtendedSimpleDescriptor
    }

    public enum MacCapabilitiesType {
        AlternativePAN,
        FullFunctionDevice,
        ReducedFunctionDevice,
        MainsPower,
        ReceiverOnWhenIdle,
        SecurityCapable,
        AddressAllocation
    }

    public NodeDescriptor(int apsFlags, int bufferSize, int macCapabilities, boolean complexDescriptorAvailable,
            int manufacturerCode, int logicalType, int serverMask, int transferSize, boolean userDescriptorAvailable,
            int frequencyBand) {

        this.complexDescriptorAvailable = complexDescriptorAvailable;
        this.userDescriptorAvailable = userDescriptorAvailable;
        this.manufacturerCode = manufacturerCode;
        this.bufferSize = bufferSize;
        this.transferSize = transferSize;

        switch (logicalType) {
            case 0:
                this.logicalType = LogicalType.Coordinator;
                break;
            case 1:
                this.logicalType = LogicalType.Router;
                break;
            case 2:
                this.logicalType = LogicalType.EndDevice;
                break;
            default:
                this.logicalType = null;
                break;
        }

        this.macCapabilities = new HashSet<MacCapabilitiesType>();
        if ((macCapabilities & 0x01) != 0) {
            this.macCapabilities.add(MacCapabilitiesType.AlternativePAN);
        }
        if ((macCapabilities & 0x02) != 0) {
            this.macCapabilities.add(MacCapabilitiesType.FullFunctionDevice);
        } else {
            this.macCapabilities.add(MacCapabilitiesType.ReducedFunctionDevice);
        }
        if ((macCapabilities & 0x04) != 0) {
            this.macCapabilities.add(MacCapabilitiesType.MainsPower);
        }
        if ((macCapabilities & 0x08) != 0) {
            this.macCapabilities.add(MacCapabilitiesType.ReceiverOnWhenIdle);
        }
        if ((macCapabilities & 0x40) != 0) {
            this.macCapabilities.add(MacCapabilitiesType.SecurityCapable);
        }

        this.frequencyBands = new HashSet<FrequencyBandType>();
        if ((frequencyBand & 0x01) != 0) {
            this.frequencyBands.add(FrequencyBandType.Freq868MHz);
        }
        if ((frequencyBand & 0x04) != 0) {
            this.frequencyBands.add(FrequencyBandType.Freq902MHz);
        }
        if ((frequencyBand & 0x08) != 0) {
            this.frequencyBands.add(FrequencyBandType.Freq2400MHz);
        }

        this.serverCapabilities = new HashSet<ServerCapabilitiesType>();
        if ((serverMask & 0x01) != 0) {
            this.serverCapabilities.add(ServerCapabilitiesType.PrimaryTrustCenter);
        }
        if ((serverMask & 0x02) != 0) {
            this.serverCapabilities.add(ServerCapabilitiesType.BackupTrustCenter);
        }
        if ((serverMask & 0x04) != 0) {
            this.serverCapabilities.add(ServerCapabilitiesType.PrimaryBindingTableCache);
        }
        if ((serverMask & 0x08) != 0) {
            this.serverCapabilities.add(ServerCapabilitiesType.BackupBindingTableCache);
        }
        if ((serverMask & 0x10) != 0) {
            this.serverCapabilities.add(ServerCapabilitiesType.PrimaryDiscoveryCache);
        }
        if ((serverMask & 0x20) != 0) {
            this.serverCapabilities.add(ServerCapabilitiesType.BackupDiscoveryCache);
        }
        if ((serverMask & 0x40) != 0) {
            this.serverCapabilities.add(ServerCapabilitiesType.NetworkManager);
        }

        this.apsFlags = apsFlags;
    }

    public int getApsFlags() {
        return apsFlags;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public Set<MacCapabilitiesType> getMacCapabilities() {
        return macCapabilities;
    }

    public int getManufacturerCode() {
        return manufacturerCode;
    }

    public boolean isComplexDescriptorAvailable() {
        return complexDescriptorAvailable;
    }

    public LogicalType getLogicalType() {
        return logicalType;
    }

    public Set<ServerCapabilitiesType> getServerCapabilities() {
        return serverCapabilities;
    }

    public int getTransferSize() {
        return transferSize;
    }

    public boolean isUserDescriptorAvailable() {
        return userDescriptorAvailable;
    }

    public Set<FrequencyBandType> getFrequencyBands() {
        return frequencyBands;
    }

    @Override
    public String toString() {
        return "Type=" + getLogicalType() + ", Bands=" + getFrequencyBands();
    }
}
