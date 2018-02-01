/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer;

import java.util.Arrays;
import java.util.Set;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsOption;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberBindingTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberBindingType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberConcentratorType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberConfigTxPowerMode;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCurrentSecurityBitmask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberInitialSecurityBitmask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberInitialSecurityState;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberJoinMethod;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNodeType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberOutgoingMessageType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspNetworkScanType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspPolicyId;

/**
 * The EmberZNet Serial Protocol Data Representation
 *
 * This class contains low level methods for serialising Ember data packets and
 * structures to the array for sending
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
        // If we pass in null, then send a null key.
        // Note that ember will not accept this key, so this check is here to prevent the system
        // throwing an NPE - the application needs to resolve this so it sends a valid key.
        if (keyData == null || keyData.getContents() == null) {
            serializeUInt8Array(new int[] { 0, 0, 0, 0, 0, 0, 0, 0 });
            return;
        }
        serializeUInt8Array(keyData.getContents());
    }

    public void serializeEmberEui64(IeeeAddress address) {
        buffer[length++] = address.getValue()[0];
        buffer[length++] = address.getValue()[1];
        buffer[length++] = address.getValue()[2];
        buffer[length++] = address.getValue()[3];
        buffer[length++] = address.getValue()[4];
        buffer[length++] = address.getValue()[5];
        buffer[length++] = address.getValue()[6];
        buffer[length++] = address.getValue()[7];
    }

    public void serializeEmberNetworkParameters(EmberNetworkParameters networkParameters) {
        networkParameters.serialize(this);
    }

    public void serializeEmberApsFrame(EmberApsFrame apsFrame) {
        apsFrame.serialize(this);
    }

    public void serializeEmberApsOption(Set<EmberApsOption> options) {
        int val = 0;
        for (EmberApsOption option : options) {
            val += option.getKey();
        }
        buffer[length++] = val & 0xFF;
        buffer[length++] = (val >> 8) & 0xFF;
    }

    public void serializeEzspNetworkScanType(EzspNetworkScanType scanType) {
        buffer[length++] = scanType.getKey();
    }

    public void serializeEmberBindingTableEntry(EmberBindingTableEntry tableEntry) {
        tableEntry.serialize(this);
    }

    public void serializeEmberInitialSecurityState(EmberInitialSecurityState securityState) {
        securityState.serialize(this);
    }

    public void serializeEmberOutgoingMessageType(EmberOutgoingMessageType messageType) {
        buffer[length++] = messageType.getKey();
    }

    public void serializeEmberConcentratorType(EmberConcentratorType concentratorType) {
        serializeUInt16(concentratorType.getKey());
    }

    public void serializeEzspPolicyId(EzspPolicyId policyId) {
        buffer[length++] = policyId.getKey();
    }

    public void serializeEmberNodeType(EmberNodeType nodeType) {
        buffer[length++] = nodeType.getKey();
    }

    public void serializeEzspConfigId(EzspConfigId configId) {
        buffer[length++] = configId.getKey();
    }

    public void serializeEzspDecisionId(EzspDecisionId decisionId) {
        buffer[length++] = decisionId.getKey();
    }

    public void serializeEmberBindingType(EmberBindingType bindingType) {
        buffer[length++] = bindingType.getKey();
    }

    public void serializeEmberCurrentSecurityBitmask(Set<EmberCurrentSecurityBitmask> securityBitmask) {
        int value = 0;
        for (EmberCurrentSecurityBitmask bitmask : securityBitmask) {
            value |= bitmask.getKey();
        }
        buffer[length++] = value & 0xFF;
        buffer[length++] = (value >> 8) & 0xFF;
    }

    public void serializeEmberInitialSecurityBitmask(Set<EmberInitialSecurityBitmask> securityBitmask) {
        int value = 0;
        for (EmberInitialSecurityBitmask bitmask : securityBitmask) {
            value |= bitmask.getKey();
        }
        buffer[length++] = value & 0xFF;
        buffer[length++] = (value >> 8) & 0xFF;
    }

    public void serializeEmberJoinMethod(EmberJoinMethod joinMethod) {
        buffer[length++] = joinMethod.getKey();
    }

    
    /**
     * Adds a EmberConfigTxPowerMode into the output stream
     *
     * @param val
     */
    public void serializeEmberConfigTxPowerMode(EmberConfigTxPowerMode val) {
    	int value = val.getKey();
        buffer[length++] = value & 0xFF;
        buffer[length++] = (value >> 8) & 0xFF;
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
