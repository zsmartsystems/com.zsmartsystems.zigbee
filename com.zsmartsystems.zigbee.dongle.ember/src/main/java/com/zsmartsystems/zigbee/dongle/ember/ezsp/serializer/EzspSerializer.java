package com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer;

import java.util.Arrays;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberBindingTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberInitialSecurityState;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberOutgoingMessageType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStructure;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspNetworkScanType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspPolicyId;

/**
 * The EmberZNet Serial Protocol Data Representation
 *
 * This class contains low level methods for serialising Ember data packets and
 * structures
 *
 * @author Chris Jackson
 *
 */
public class EzspSerializer {
    protected int[] buffer = new int[131];
    protected int length = 0;
    protected int position = 0;

    /**
     * Adds a uint8_t into the output stream
     *
     * @param val
     */
    public void serializeUInt8(int val) {
        buffer[length++] = val & 0xFF;
    }

    /**
     * Adds a uint16_t into the output stream
     *
     * @param val
     */
    public void serializeUInt16(int val) {
        buffer[length++] = val & 0xFF;
        buffer[length++] = (val >> 8) & 0xFF;
    }

    /**
     * Adds a uint32_t into the output stream
     *
     * @param val
     */
    public void serializeUInt32(int val) {
        buffer[length++] = val & 0xFF;
        buffer[length++] = (val >> 8) & 0xFF;
        buffer[length++] = (val >> 16) & 0xFF;
        buffer[length++] = (val >> 24) & 0xFF;
    }

    public void serializeStructure(EmberStructure structure) {
        for (int val : structure.getOutputBuffer()) {
            buffer[length++] = val;
        }
    }

    public void serializeUInt16Array(int[] array) {
        for (int val : array) {
            serializeUInt16(val);
        }
    }

    public void serializeUInt8Array(int[] array) {
        for (int val : array) {
            serializeUInt8(val);
        }
    }

    public void serializeBool(boolean val) {
        buffer[length++] = val ? 1 : 0;
    }

    public void serializeEmberKeyData(EmberKeyData keyData) {
        serializeUInt8Array(keyData.getKey());
    }

    public void serializeEmberEui64(IeeeAddress address) {
        long val = address.getLong();

        buffer[length++] = (int) (val & 0xFF);
        buffer[length++] = (int) ((val >> 8) & 0xFF);
        buffer[length++] = (int) ((val >> 16) & 0xFF);
        buffer[length++] = (int) ((val >> 24) & 0xFF);
        buffer[length++] = (int) ((val >> 32) & 0xFF);
        buffer[length++] = (int) ((val >> 40) & 0xFF);
        buffer[length++] = (int) ((val >> 48) & 0xFF);
        buffer[length++] = (int) ((val >> 56) & 0xFF);
    }

    public void serializeEmberNetworkParameters(EmberNetworkParameters networkParameters) {
    }

    public void serializeEmberApsFrame(EmberApsFrame apsFrame) {
    }

    public void serializeEzspNetworkScanType(EzspNetworkScanType scanType) {
    }

    public void serializeEmberBindingTableEntry(EmberBindingTableEntry tableEntry) {
    }

    public void serializeEmberInitialSecurityState(EmberInitialSecurityState securityState) {
    }

    public void serializeEmberOutgoingMessageType(EmberOutgoingMessageType messageType) {
    }

    public void serializeEzspPolicyId(EzspPolicyId policyId) {
    }

    public void serializeEzspDecisionId(EzspDecisionId decisionId) {
    }

    /**
     * Returns the data to be sent to the NCP
     *
     * @return integer array of data to be sent
     */
    public int[] getPayload() {
        return Arrays.copyOfRange(buffer, 0, length);
    }

}
