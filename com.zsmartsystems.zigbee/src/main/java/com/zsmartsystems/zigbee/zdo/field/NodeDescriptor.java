/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.field;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Describes the node descriptor. The node descriptor contains information about the capabilities of the ZigBee node and
 * is mandatory for each node. There shall be only one node descriptor in a node.
 *
 * @author Chris Jackson
 *
 */
public class NodeDescriptor {
    /**
     * The APS flags field of the node descriptor is three bits in length and specifies the application support
     * sub-layer capabilities of the node.
     * This field is currently not supported and shall be set to zero.
     */
    private int apsFlags;

    /**
     * The maximum buffer size field of the node descriptor is eight bits in length, with a valid range of 0x00-0x7f.
     * This field specifies the maximum size, in octets, of the network sub-layer data unit (NSDU) for this node. This
     * is the maximum size of data or commands passed to or from the application by the application support sublayer,
     * before any fragmentation or re-assembly.
     * This field can be used as a high-level indication for network management.
     */
    private int bufferSize;

    /**
     * The complex descriptor available field of the node descriptor is one bit in length and specifies whether a
     * complex descriptor is available on this device. If this field is set to 1, a complex descriptor is available. If
     * this field is set to 0, a complex descriptor is not available.
     */
    private boolean complexDescriptorAvailable;

    /**
     * The manufacturer code field of the node descriptor is sixteen bits in length and specifies a manufacturer code
     * that is allocated by the ZigBee Alliance, relating the manufacturer to the device.
     */
    private int manufacturerCode;

    /**
     * The logical type field of the node descriptor is three bits in length and specifies the device type of theZigBee
     * node.
     */
    private LogicalType logicalType = LogicalType.UNKNOWN;

    /**
     * The server mask field of the node descriptor is sixteen bits in length, with bit settings signifying the system
     * server capabilities of this node. It is used to facilitate discovery of particular system servers by other nodes
     * on the system.
     */
    private Set<ServerCapabilitiesType> serverCapabilities = new TreeSet<>();

    /**
     * The maximum transfer size field of the node descriptor is sixteen bits in length, with a valid range of
     * 0x0000-0x7fff. This field specifies the maximum size, in octets, of the application sub-layer data unit (ASDU)
     * that can be transferred to this node in one single message transfer. This value can exceed the value of the node
     * maximum buffer size field through the use of fragmentation.
     */
    private int incomingTransferSize;

    /**
     * The maximum transfer size field of the node descriptor is sixteen bits in length, with a valid range of
     * 0x0000-0x7fff. This field specifies the maximum size, in octets, of the application sub-layer data unit
     * (ASDU) that can be transferred from this node in one single message transfer. This value can exceed the
     * value of the node maximum buffer size field through the use of fragmentation.
     */
    private int outgoingTransferSize;

    /**
     * The user descriptor available field of the node descriptor is one bit in length and specifies whether a user
     * descriptor is available on this device. If this field is set to 1, a user descriptor is available. If this field
     * is set to 0, a user descriptor is not available.
     */
    private boolean userDescriptorAvailable;

    /**
     * The frequency band field of the node descriptor is five bits in length and specifies the frequency bands that
     * are supported by the underlying IEEE 802.15.4 radio utilized by the node.
     */
    private Set<FrequencyBandType> frequencyBands = new HashSet<FrequencyBandType>();

    /**
     * The MAC capability flags field is eight bits in length and specifies the node capabilities, as required by the
     * IEEE 802.15.4-2003 MAC sub-layer.
     */
    private final Set<MacCapabilitiesType> macCapabilities = new TreeSet<>();

    /**
     * Extended Active Endpoint List Available
     */
    private boolean extendedEndpointListAvailable;

    /**
     * Extended Simple Descriptor List Available
     */
    private boolean extendedSimpleDescriptorListAvailable;

    /**
     * These bits indicate the revision of the ZigBee Pro Core specification that the running stack is implemented to.
     * Prior to revision 21 of the specification these bits were reserved and thus set to 0.
     */
    private int stackCompliance;

    private final static int R21_BITMASK = 0xFE00;
    private final static int R21_BITSHIFT = 9;

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

    /**
     * The APS flags field of the node descriptor is three bits in length and specifies the application support
     * sub-layer capabilities of the node.
     * This field is currently not supported and shall be set to zero.
     *
     * @return the APS flags
     */
    public int getApsFlags() {
        return apsFlags;
    }

    /**
     * The maximum buffer size field of the node descriptor is eight bits in length, with a valid range of 0x00-0x7f.
     * This field specifies the maximum size, in octets, of the network sub-layer data unit (NSDU) for this node. This
     * is the maximum size of data or commands passed to or from the application by the application support sublayer,
     * before any fragmentation or re-assembly.
     * This field can be used as a high-level indication for network management.
     *
     * @return the maximum buffer size
     */
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

    /**
     * The manufacturer code field of the node descriptor is sixteen bits in length and specifies a manufacturer code
     * that is allocated by the ZigBee Alliance, relating the manufacturer to the device.
     *
     * @return the 16 bit manufacturer code
     */
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

    /**
     * The logical type field of the node descriptor is three bits in length and specifies the device type of the ZigBee
     * node.
     *
     * @param logicalType the logical type of the node from the descriptor
     */
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

    /**
     * The logical type field of the node descriptor is three bits in length and specifies the device type of the ZigBee
     * node.
     *
     * @return the {@link LogicalType} of the node
     */
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

    /**
     * The maximum transfer size field of the node descriptor is sixteen bits in length, with a valid range of
     * 0x0000-0x7fff. This field specifies the maximum size, in octets, of the application sub-layer data unit
     * (ASDU) that can be transferred from this node in one single message transfer. This value can exceed the
     * value of the node maximum buffer size field through the use of fragmentation.
     *
     * @return the maximum outgoing transfer size
     */
    public int getOutGoingTransferSize() {
        return outgoingTransferSize;
    }

    /**
     * The maximum transfer size field of the node descriptor is sixteen bits in length, with a valid range of
     * 0x0000-0x7fff. This field specifies the maximum size, in octets, of the application sub-layer data unit
     * (ASDU) that can be transferred from this node in one single message transfer. This value can exceed the
     * value of the node maximum buffer size field through the use of fragmentation.
     *
     * @return the maximum incoming transfer size
     */
    public int getIncomingTransferSize() {
        return incomingTransferSize;
    }

    /**
     * Gets the flag indicating if the node provides a {@link UserDescriptor}
     * 
     * @return true if a user descriptor is available
     */
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

    /**
     * The frequency band field of the node descriptor is five bits in length and specifies the frequency bands that
     * are supported by the underlying IEEE 802.15.4 radio utilized by the node.
     *
     * @return a Set of the supported frequency bands
     */
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

        // Some flags...
        int value1 = deserializer.readZigBeeType(ZclDataType.DATA_8_BIT);
        int value2 = deserializer.readZigBeeType(ZclDataType.DATA_8_BIT);
        int value3 = deserializer.readZigBeeType(ZclDataType.DATA_8_BIT);

        setLogicalType(value1 & 0x07);
        complexDescriptorAvailable = (value1 & 0x08) != 0;
        userDescriptorAvailable = (value1 & 0x10) != 0;

        apsFlags = value2 & 0x07;
        setFrequencyBands((value2 & 0xf8) >> 3);
        setMacCapabilities(value3);

        manufacturerCode = deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        bufferSize = deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        incomingTransferSize = deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);

        setServerCapabilities((int) deserializer.readZigBeeType(ZclDataType.SIGNED_16_BIT_INTEGER));
        outgoingTransferSize = deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        int descriptorCapabilities = deserializer.readZigBeeType(ZclDataType.SIGNED_8_BIT_INTEGER);

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
        return userDescriptorAvailable == other.userDescriptorAvailable;
    }

    /**
     * Get the stack compliance level. Note that this was added at R21, and will be 0 for devices that are older than
     * R21.
     *
     * @return the stack compliance level
     */
    public int getStackCompliance() {
        return stackCompliance;
    }

    @Override
    public String toString() {
        return "NodeDescriptor [apsFlags=" + apsFlags + ", bufferSize=" + bufferSize + ", complexDescriptorAvailable="
                + complexDescriptorAvailable + ", manufacturerCode=" + String.format("%04X", manufacturerCode)
                + ", logicalType=" + logicalType + ", serverCapabilities=" + serverCapabilities
                + ", incomingTransferSize=" + incomingTransferSize + ", outgoingTransferSize=" + outgoingTransferSize
                + ", userDescriptorAvailable=" + userDescriptorAvailable + ", frequencyBands=" + frequencyBands
                + ", macCapabilities=" + macCapabilities + ", extendedEndpointListAvailable="
                + extendedEndpointListAvailable + ", extendedSimpleDescriptorListAvailable="
                + extendedSimpleDescriptorListAvailable + ", stackCompliance=" + stackCompliance + "]";
    }

}
