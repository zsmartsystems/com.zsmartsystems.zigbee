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
        COORDINATOR,
        ROUTER,
        END_DEVICE
    }

    public enum FrequencyBandType {
        FREQ_868_MHZ,
        FREQ_902_MHZ,
        FREQ_2400_MHZ
    }

    public enum ServerCapabilitiesType {
        PRIMARY_TRUST_CENTER,
        BACKUP_TRUST_CENTER,
        PRIMARY_BINDING_TABLE_CACHE,
        BACKUP_BINDING_TABLE_CACHE,
        PRIMARY_DISCOVERY_CACHE,
        BACKUP_DISCOVERY_CACHE,
        NETWORK_MANAGER
    }

    public enum DescriptorCapabilityType {
        EXTENDED_ACTIVE_ENDPOINT_LIST,
        EXTENDED_SIMPLE_DESCRIPTER
    }

    public enum MacCapabilitiesType {
        ALTERNATIVE_PAN,
        FULL_FUNCTION_DEVICE,
        REDUCED_FUNCTION_DEVICE,
        MAINS_POWER,
        RECEIVER_ON_WHEN_IDLE,
        SECURITY_CAPABLE,
        ADDRESS_ALLOCATION
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
                this.logicalType = LogicalType.COORDINATOR;
                break;
            case 1:
                this.logicalType = LogicalType.ROUTER;
                break;
            case 2:
                this.logicalType = LogicalType.END_DEVICE;
                break;
            default:
                this.logicalType = null;
                break;
        }

        this.macCapabilities = new HashSet<MacCapabilitiesType>();
        if ((macCapabilities & 0x01) != 0) {
            this.macCapabilities.add(MacCapabilitiesType.ALTERNATIVE_PAN);
        }
        if ((macCapabilities & 0x02) != 0) {
            this.macCapabilities.add(MacCapabilitiesType.FULL_FUNCTION_DEVICE);
        } else {
            this.macCapabilities.add(MacCapabilitiesType.REDUCED_FUNCTION_DEVICE);
        }
        if ((macCapabilities & 0x04) != 0) {
            this.macCapabilities.add(MacCapabilitiesType.MAINS_POWER);
        }
        if ((macCapabilities & 0x08) != 0) {
            this.macCapabilities.add(MacCapabilitiesType.RECEIVER_ON_WHEN_IDLE);
        }
        if ((macCapabilities & 0x40) != 0) {
            this.macCapabilities.add(MacCapabilitiesType.SECURITY_CAPABLE);
        }

        this.frequencyBands = new HashSet<FrequencyBandType>();
        if ((frequencyBand & 0x01) != 0) {
            this.frequencyBands.add(FrequencyBandType.FREQ_868_MHZ);
        }
        if ((frequencyBand & 0x04) != 0) {
            this.frequencyBands.add(FrequencyBandType.FREQ_902_MHZ);
        }
        if ((frequencyBand & 0x08) != 0) {
            this.frequencyBands.add(FrequencyBandType.FREQ_2400_MHZ);
        }

        this.serverCapabilities = new HashSet<ServerCapabilitiesType>();
        if ((serverMask & 0x01) != 0) {
            this.serverCapabilities.add(ServerCapabilitiesType.PRIMARY_TRUST_CENTER);
        }
        if ((serverMask & 0x02) != 0) {
            this.serverCapabilities.add(ServerCapabilitiesType.BACKUP_TRUST_CENTER);
        }
        if ((serverMask & 0x04) != 0) {
            this.serverCapabilities.add(ServerCapabilitiesType.PRIMARY_BINDING_TABLE_CACHE);
        }
        if ((serverMask & 0x08) != 0) {
            this.serverCapabilities.add(ServerCapabilitiesType.BACKUP_BINDING_TABLE_CACHE);
        }
        if ((serverMask & 0x10) != 0) {
            this.serverCapabilities.add(ServerCapabilitiesType.PRIMARY_DISCOVERY_CACHE);
        }
        if ((serverMask & 0x20) != 0) {
            this.serverCapabilities.add(ServerCapabilitiesType.BACKUP_DISCOVERY_CACHE);
        }
        if ((serverMask & 0x40) != 0) {
            this.serverCapabilities.add(ServerCapabilitiesType.NETWORK_MANAGER);
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
