/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.serializer;

import java.util.HashSet;
import java.util.Set;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberAesMmoHashContext;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsOption;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberBindingTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberBindingType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCertificate283k1Data;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCertificateData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberConcentratorType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCurrentSecurityBitmask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCurrentSecurityState;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberDeviceUpdate;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpAddressUnion;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpApplicationId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpKeyType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpProxyTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpProxyTableEntryStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpSecurityLevel;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpSinkListEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpSinkTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpSinkTableEntryStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberIncomingMessageType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberInitialSecurityBitmask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberJoinDecision;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberJoinMethod;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyStruct;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyStructBitmask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberLibraryStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberMacPassthroughType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNeighborTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNodeType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberOutgoingMessageType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberPublicKey283k1Data;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberPublicKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberRouteTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberSmacData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberTransientKeyData;
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
    private int[] buffer = new int[220];
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
        int address[] = new int[8];
        for (int cnt = 0; cnt < 8; cnt++) {
            address[cnt] = buffer[position++];
        }
        return new IeeeAddress(address);
    }
    
    //debug
    public int[] deserializeRawAddress() {
        int address[] = new int[8];
        for (int cnt = 0; cnt < 8; cnt++) {
            address[cnt] = buffer[position++];
        }
        return address;
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

    public EmberConcentratorType deserializeEmberConcentratorType() {
        return EmberConcentratorType.getEmberConcentratorType(deserializeUInt16());
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

    public Set<EmberCurrentSecurityBitmask> deserializeEmberCurrentSecurityBitmask() {
        Set<EmberCurrentSecurityBitmask> list = new HashSet<EmberCurrentSecurityBitmask>();
        int value = deserializeUInt16();
        for (EmberCurrentSecurityBitmask bitmask : EmberCurrentSecurityBitmask.values()) {
            // Ignore UNKNOWN
            if (bitmask == EmberCurrentSecurityBitmask.UNKNOWN) {
                continue;
            }
            if ((value & bitmask.getKey()) != 0) {
                list.add(bitmask);
            }
        }

        return list;
    }

    public EmberMacPassthroughType deserializeEmberMacPassthroughType() {
        return EmberMacPassthroughType.getEmberMacPassthroughType(deserializeUInt8());
    }

    public EmberJoinMethod deserializeEmberJoinMethod() {
        return EmberJoinMethod.getEmberJoinMethod(deserializeUInt8());
    }

    public EmberNetworkStatus deserializeEmberNetworkStatus() {
        return EmberNetworkStatus.getEmberNetworkStatus(deserializeUInt8());
    }

    public EmberDeviceUpdate deserializeEmberDeviceUpdate() {
        return EmberDeviceUpdate.getEmberDeviceUpdate(deserializeUInt8());
    }

    public EmberJoinDecision deserializeEmberJoinDecision() {
        return EmberJoinDecision.getEmberJoinDecision(deserializeUInt8());
    }

    public Set<EmberInitialSecurityBitmask> deserializeEmberInitialSecurityBitmask() {
        Set<EmberInitialSecurityBitmask> list = new HashSet<EmberInitialSecurityBitmask>();
        int value = deserializeUInt16();
        for (EmberInitialSecurityBitmask bitmask : EmberInitialSecurityBitmask.values()) {
            // Ignore UNKNOWN
            if (bitmask == EmberInitialSecurityBitmask.UNKNOWN) {
                continue;
            }
            if ((value & bitmask.getKey()) != 0) {
                list.add(bitmask);
            }
        }

        return list;
    }

    public ExtendedPanId deserializeExtendedPanId() {
        return new ExtendedPanId(deserializeUInt8Array(8));
    }

    public EmberKeyType deserializeEmberKeyType() {
        return EmberKeyType.getEmberKeyType(deserializeUInt8());
    }

    public Set<EmberKeyStructBitmask> deserializeEmberKeyStructBitmask() {
        Set<EmberKeyStructBitmask> list = new HashSet<EmberKeyStructBitmask>();
        int value = deserializeUInt16();
        for (EmberKeyStructBitmask bitmask : EmberKeyStructBitmask.values()) {
            // Ignore UNKNOWN
            if (bitmask == EmberKeyStructBitmask.UNKNOWN) {
                continue;
            }
            if ((value & bitmask.getKey()) != 0) {
                list.add(bitmask);
            }
        }

        return list;
    }

    public EmberKeyStruct deserializeEmberKeyStruct() {
        return new EmberKeyStruct(this);
    }

    // public EmberGpProxyTableEntry deserializeEmberGpProxyTableEntry() {
    // return new EmberGpProxyTableEntry(this);
    // }

    public EmberGpAddress deserializeEmberGpAddress() {
        return new EmberGpAddress(this);
    }

    public EmberGpSecurityLevel deserializeEmberGpSecurityLevel() {
        return EmberGpSecurityLevel.getEmberGpSecurityLevel(deserializeUInt8());
    }

    public EmberGpKeyType deserializeEmberGpKeyType() {
        return EmberGpKeyType.getEmberGpKeyType(deserializeUInt8());
    }

    public EmberAesMmoHashContext deserializeEmberAesMmoHashContext() {
        return new EmberAesMmoHashContext(this);
    }

    public EmberCertificateData deserializeEmberCertificateData() {
        return new EmberCertificateData(this);
    }

    public EmberCertificate283k1Data deserializeEmberCertificate283k1Data() {
        return new EmberCertificate283k1Data(this);
    }

    public EmberSmacData deserializeEmberSmacData() {
        return new EmberSmacData(this);
    }

    public EmberPublicKeyData deserializeEmberPublicKeyData() {
        return new EmberPublicKeyData(this);
    }

    public EmberPublicKey283k1Data deserializeEmberPublicKey283k1Data() {
        return new EmberPublicKey283k1Data(this);
    }

    public EmberLibraryStatus deserializeEmberLibraryStatus() {
        return EmberLibraryStatus.getEmberLibraryStatus(deserializeUInt8());
    }

    public EmberTransientKeyData deserializeEmberTransientKeyData() {
        return new EmberTransientKeyData(this);
    }

	public EmberGpProxyTableEntryStatus deserializeEmberGpProxyTableEntryStatus() {
		return EmberGpProxyTableEntryStatus.getEmberGpProxyTableEntryStatus(deserializeUInt8());
	}

	public EmberGpSinkListEntry[] deserializeEmberGpSinkListEntry() {
		EmberGpSinkListEntry[] array = new EmberGpSinkListEntry[2];
		array[0] =  new EmberGpSinkListEntry(this);
		array[1] =  new EmberGpSinkListEntry(this);
		return array;
	}

	public EmberGpSinkTableEntry deserializeEmberGpSinkTableEntry() {
		return new EmberGpSinkTableEntry(this);
	}

	public EmberGpSinkTableEntryStatus deserializeEmberGpSinkTableEntryStatus() {
		return EmberGpSinkTableEntryStatus.getEmberGpSinkTableEntryStatus(deserializeUInt8());
	}

	public EmberGpProxyTableEntry deserializeEmberGpProxyTableEntry() {
		return new EmberGpProxyTableEntry(this);
	}

	public EmberGpApplicationId deserializeEmberGpApplicationId() {
		return EmberGpApplicationId.getEmberGpApplicationId(deserializeUInt8());
	}

	public EmberGpAddressUnion deserializeEmberGpAddressUnion() {
		return new EmberGpAddressUnion(this);
	}

}
