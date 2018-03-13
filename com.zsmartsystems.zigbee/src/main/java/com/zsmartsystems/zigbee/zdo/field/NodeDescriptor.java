/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.field;

import java.util.HashSet;
import java.util.Set;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Describes the node descriptor
 *
 * @author Chris Jackson
 *
 */
public class NodeDescriptor {
    private int apsFlags;
    private int bufferSize;
    private boolean complexDescriptorAvailable;
    private int manufacturerCode;
    private LogicalType logicalType = LogicalType.UNKNOWN;
    private Set<ServerCapabilitiesType> serverCapabilities = new HashSet<ServerCapabilitiesType>();
    private int incomingTransferSize;
    private int outgoingTransferSize;
    private boolean userDescriptorAvailable;
    private Set<FrequencyBandType> frequencyBands = new HashSet<FrequencyBandType>();
    private final Set<MacCapabilitiesType> macCapabilities = new HashSet<MacCapabilitiesType>();
    private boolean extendedEndpointListAvailable;
    private boolean extendedSimpleDescriptorListAvailable;
    private int stackCompliance;

    final private int R21_BITMASK = 0xFE00;
    final private int R21_BITSHIFT = 9;

    public enum LogicalType {
        COORDINATOR,
        ROUTER,
        END_DEVICE,
        UNKNOWN
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

    public NodeDescriptor() {
        // Default constructor - does nothing
    }

    public NodeDescriptor(int apsFlags, int bufferSize, int macCapabilities, boolean complexDescriptorAvailable,
            int manufacturerCode, int logicalType, int serverMask, int transferSize, boolean userDescriptorAvailable,
            int frequencyBands) {

        this.complexDescriptorAvailable = complexDescriptorAvailable;
        this.userDescriptorAvailable = userDescriptorAvailable;
        this.manufacturerCode = manufacturerCode;
        this.bufferSize = bufferSize;
        this.incomingTransferSize = transferSize;
        setLogicalType(logicalType);
        setMacCapabilities(macCapabilities);
        setFrequencyBands(frequencyBands);
        setServerCapabilities(serverMask);

        this.apsFlags = apsFlags;
    }

    public int getApsFlags() {
        return apsFlags;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    private void setMacCapabilities(int macCapabilities) {
        this.macCapabilities.clear();
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

    public boolean isExtendedEndpointListAvailable() {
        return extendedEndpointListAvailable;
    }

    public boolean isExtendedSimpleDescriptorListAvailable() {
        return extendedSimpleDescriptorListAvailable;
    }

    private void setLogicalType(int logicalType) {
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
                this.logicalType = LogicalType.UNKNOWN;
                break;
        }
    }

    public LogicalType getLogicalType() {
        return logicalType;
    }

    private void setServerCapabilities(int serverMask) {
        this.serverCapabilities.clear();
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

        stackCompliance = (serverMask & R21_BITMASK) >> R21_BITSHIFT;
    }

    public Set<ServerCapabilitiesType> getServerCapabilities() {
        return serverCapabilities;
    }

    public int getOutGoingTransferSize() {
        return outgoingTransferSize;
    }

    public int getIncomingTransferSize() {
        return incomingTransferSize;
    }

    public boolean isUserDescriptorAvailable() {
        return userDescriptorAvailable;
    }

    private void setFrequencyBands(int frequencyBands) {
        this.frequencyBands.clear();
        if ((frequencyBands & 0x01) != 0) {
            this.frequencyBands.add(FrequencyBandType.FREQ_868_MHZ);
        }
        if ((frequencyBands & 0x04) != 0) {
            this.frequencyBands.add(FrequencyBandType.FREQ_902_MHZ);
        }
        if ((frequencyBands & 0x08) != 0) {
            this.frequencyBands.add(FrequencyBandType.FREQ_2400_MHZ);
        }
    }

    public Set<FrequencyBandType> getFrequencyBands() {
        return frequencyBands;
    }

    /**
     * Serialise the contents of the structure.
     *
     * @param serializer the {@link ZclFieldSerializer} used to serialize
     */
    public int[] serialize(ZclFieldSerializer serializer) {
        // Serialize the fields
        serializer.serialize(logicalType, ZclDataType.SIGNED_8_BIT_INTEGER);
        serializer.serialize(logicalType, ZclDataType.SIGNED_8_BIT_INTEGER);
        serializer.serialize(logicalType, ZclDataType.SIGNED_8_BIT_INTEGER);
        serializer.serialize(logicalType, ZclDataType.SIGNED_8_BIT_INTEGER);
        serializer.serialize(logicalType, ZclDataType.SIGNED_8_BIT_INTEGER);
        serializer.serialize(logicalType, ZclDataType.SIGNED_8_BIT_INTEGER);
        serializer.serialize(logicalType, ZclDataType.SIGNED_8_BIT_INTEGER);

        return serializer.getPayload();
    }

    /**
     * Deserialise the contents of the structure.
     *
     * @param deserializer the {@link ZigBeeDeserializer} used to deserialize
     */
    public void deserialize(ZigBeeDeserializer deserializer) {
        // Deserialize the fields
        // logicalType = (LogicalType) deserializer.deserialize(ZclDataType.SIGNED_8_BIT_INTEGER);

        // Some flags...
        int value1 = (int) deserializer.readZigBeeType(ZclDataType.DATA_8_BIT);
        int value2 = (int) deserializer.readZigBeeType(ZclDataType.DATA_8_BIT);
        int value3 = (int) deserializer.readZigBeeType(ZclDataType.DATA_8_BIT);

        setLogicalType(value1 & 0x07);
        complexDescriptorAvailable = (value1 & 0x08) != 0;
        userDescriptorAvailable = (value1 & 0x10) != 0;

        setFrequencyBands((value2 & 0xf8) >> 3);
        setMacCapabilities(value3);

        // complexDescriptorAvailable = (Boolean) deserializer.deserialize(ZclDataType.BOOLEAN);
        // userDescriptorAvailable = (Boolean) deserializer.deserialize(ZclDataType.BOOLEAN);
        manufacturerCode = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        bufferSize = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        incomingTransferSize = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);

        setServerCapabilities((int) deserializer.readZigBeeType(ZclDataType.SIGNED_16_BIT_INTEGER));
        outgoingTransferSize = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        int descriptorCapabilities = (int) deserializer.readZigBeeType(ZclDataType.SIGNED_8_BIT_INTEGER);

        extendedEndpointListAvailable = (descriptorCapabilities & 0x01) != 0;
        extendedSimpleDescriptorListAvailable = (descriptorCapabilities & 0x02) != 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + apsFlags;
        result = prime * result + bufferSize;
        result = prime * result + (complexDescriptorAvailable ? 1231 : 1237);
        result = prime * result + (extendedEndpointListAvailable ? 1231 : 1237);
        result = prime * result + (extendedSimpleDescriptorListAvailable ? 1231 : 1237);
        result = prime * result + ((frequencyBands == null) ? 0 : frequencyBands.hashCode());
        result = prime * result + incomingTransferSize;
        result = prime * result + ((logicalType == null) ? 0 : logicalType.hashCode());
        result = prime * result + ((macCapabilities == null) ? 0 : macCapabilities.hashCode());
        result = prime * result + manufacturerCode;
        result = prime * result + outgoingTransferSize;
        result = prime * result + ((serverCapabilities == null) ? 0 : serverCapabilities.hashCode());
        result = prime * result + (userDescriptorAvailable ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NodeDescriptor other = (NodeDescriptor) obj;
        if (apsFlags != other.apsFlags) {
            return false;
        }
        if (bufferSize != other.bufferSize) {
            return false;
        }
        if (complexDescriptorAvailable != other.complexDescriptorAvailable) {
            return false;
        }
        if (extendedEndpointListAvailable != other.extendedEndpointListAvailable) {
            return false;
        }
        if (extendedSimpleDescriptorListAvailable != other.extendedSimpleDescriptorListAvailable) {
            return false;
        }
        if (frequencyBands == null) {
            if (other.frequencyBands != null) {
                return false;
            }
        } else if (!frequencyBands.equals(other.frequencyBands)) {
            return false;
        }
        if (incomingTransferSize != other.incomingTransferSize) {
            return false;
        }
        if (logicalType != other.logicalType) {
            return false;
        }
        if (macCapabilities == null) {
            if (other.macCapabilities != null) {
                return false;
            }
        } else if (!macCapabilities.equals(other.macCapabilities)) {
            return false;
        }
        if (manufacturerCode != other.manufacturerCode) {
            return false;
        }
        if (outgoingTransferSize != other.outgoingTransferSize) {
            return false;
        }
        if (serverCapabilities == null) {
            if (other.serverCapabilities != null) {
                return false;
            }
        } else if (!serverCapabilities.equals(other.serverCapabilities)) {
            return false;
        }
        if (userDescriptorAvailable != other.userDescriptorAvailable) {
            return false;
        }
        return true;
    }

    /**
     * Get the stack compliance level
     *
     * @return the stack compliance level
     */
    public int getStackCompliance() {
        return stackCompliance;
    }

    @Override
    public String toString() {
        return "NodeDescriptor [apsFlags=" + apsFlags + ", bufferSize=" + bufferSize + ", complexDescriptorAvailable="
                + complexDescriptorAvailable + ", manufacturerCode=" + manufacturerCode + ", logicalType=" + logicalType
                + ", serverCapabilities=" + serverCapabilities + ", incomingTransferSize=" + incomingTransferSize
                + ", outgoingTransferSize=" + outgoingTransferSize + ", userDescriptorAvailable="
                + userDescriptorAvailable + ", frequencyBands=" + frequencyBands + ", macCapabilities="
                + macCapabilities + ", extendedEndpointListAvailable=" + extendedEndpointListAvailable
                + ", extendedSimpleDescriptorListAvailable=" + extendedSimpleDescriptorListAvailable
                + ", stackCompliance=" + stackCompliance + "]";
    }

}
