package com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer;

import java.util.HashSet;
import java.util.Set;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsOption;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberBindingTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberBindingType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCurrentSecurityBitmask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCurrentSecurityState;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberIncomingMessageType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberInitialSecurityBitmask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberJoinMethod;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNeighborTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNodeType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberOutgoingMessageType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberRouteTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberZigbeeNetwork;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;

/**
 * The EmberZNet Serial Protocol Data Representation
 *
 * This class contains low level methods for deserialising Ember data packets and
 * structures from the incoming received array
 *
 * @author Chris Jackson
 *
 */
public class EzspDeserializer {
    private int[] buffer = new int[131];
    private int position = 0;

    public EzspDeserializer(int[] inputBuffer) {
        buffer = inputBuffer;
    }

    /**
     * Reads a uint8_t from the output stream
     *
     * @return value read from input
     */
    public int deserializeUInt8() {
        return buffer[position++];
    }

    /**
     * Reads a int8s from the output stream
     *
     * @return value read from input
     */
    public int deserializeInt8S() {
        return buffer[position] > 127 ? buffer[position++] - 256 : buffer[position++];
    }

    /**
     * Reads a boolean from the output stream
     *
     * @return value read from input
     */
    public boolean deserializeBool() {
        return buffer[position++] == 0 ? false : true;
    }

    /**
     * Reads an Eui64 address from the stream
     *
     * @return value read from input
     */
    public IeeeAddress deserializeEmberEui64() {
        long address = buffer[position++] + ((long) buffer[position++] << 8) + ((long) buffer[position++] << 16)
                + ((long) buffer[position++] << 24) + ((long) buffer[position++] << 32)
                + ((long) buffer[position++] << 40) + ((long) buffer[position++] << 48)
                + ((long) buffer[position++] << 56);
        return new IeeeAddress(address);
    }

    /**
     * Reads a uint16_t from the output stream
     *
     * @return value read from input
     */
    public int deserializeUInt16() {
        return buffer[position++] + (buffer[position++] << 8);
    }

    public int[] deserializeUInt8Array(int length) {
        int[] val = new int[length];

        for (int cnt = 0; cnt < length; cnt++) {
            val[cnt] = deserializeUInt8();
        }

        return val;
    }

    public int[] deserializeUInt16Array(int length) {
        int[] val = new int[length];

        for (int cnt = 0; cnt < length; cnt++) {
            val[cnt] = deserializeUInt16();
        }

        return val;
    }

    public EmberKeyData deserializeEmberKeyData() {
        return new EmberKeyData(this);
    }

    public EzspStatus deserializeEzspStatus() {
        return EzspStatus.getEzspStatus(deserializeUInt8());
    }

    /**
     * Reads a uint32_t from the output stream
     *
     * @return value read from input
     */
    public int deserializeUInt32() {
        return buffer[position++] + (buffer[position++] << 8) + (buffer[position++] << 16) + (buffer[position++] << 24);
    }

    public EmberStatus deserializeEmberStatus() {
        return EmberStatus.getEmberStatus(deserializeUInt8());
    }

    public EmberNodeType deserializeEmberNodeType() {
        return EmberNodeType.getEmberNodeType(deserializeUInt8());
    }

    public EmberBindingType deserializeEmberBindingType() {
        return EmberBindingType.getEmberBindingType(deserializeUInt8());
    }

    public EmberBindingTableEntry deserializeEmberBindingTableEntry() {
        return new EmberBindingTableEntry(this);
    }

    public EmberCurrentSecurityState deserializeEmberCurrentSecurityState() {
        return new EmberCurrentSecurityState(this);
    }

    public EmberNeighborTableEntry deserializeEmberNeighborTableEntry() {
        return new EmberNeighborTableEntry(this);
    }

    public EmberNetworkParameters deserializeEmberNetworkParameters() {
        return new EmberNetworkParameters(this);
    }

    public EzspDecisionId deserializeEzspDecisionId() {
        return EzspDecisionId.getEzspDecisionId(deserializeUInt8());
    }

    public EzspConfigId deserializeEmberConfigId() {
        return EzspConfigId.getEzspConfigId(deserializeUInt8());
    }

    public EmberRouteTableEntry deserializeEmberRouteTableEntry() {
        return new EmberRouteTableEntry(this);
    }

    public EmberIncomingMessageType deserializeEmberIncomingMessageType() {
        return EmberIncomingMessageType.getEmberIncomingMessageType(deserializeUInt8());
    }

    public EmberOutgoingMessageType deserializeEmberOutgoingMessageType() {
        return EmberOutgoingMessageType.getEmberOutgoingMessageType(deserializeUInt8());
    }

    public EmberApsFrame deserializeEmberApsFrame() {
        return new EmberApsFrame(this);
    }

    public Set<EmberApsOption> deserializeEmberApsOption() {
        int val = deserializeUInt16();
        Set<EmberApsOption> options = new HashSet<EmberApsOption>();
        for (EmberApsOption option : EmberApsOption.values()) {
            if (option == EmberApsOption.UNKNOWN) {
                continue;
            }

            if ((option.getKey() & val) != 0) {
                options.add(option);
            }
        }
        return options;
    }

    public EmberZigbeeNetwork deserializeEmberZigbeeNetwork() {
        return new EmberZigbeeNetwork(this);
    }

    public EmberCurrentSecurityBitmask deserializeEmberCurrentSecurityBitmask() {
        return EmberCurrentSecurityBitmask.getEmberCurrentSecurityBitmask(deserializeUInt8());
    }

    public EmberJoinMethod deserializeEmberJoinMethod() {
        return EmberJoinMethod.getEmberJoinMethod(deserializeUInt8());
    }

    public EmberInitialSecurityBitmask deserializeEmberInitialSecurityBitmask() {
        return EmberInitialSecurityBitmask.getEmberInitialSecurityBitmask(deserializeUInt8());
    }
}
