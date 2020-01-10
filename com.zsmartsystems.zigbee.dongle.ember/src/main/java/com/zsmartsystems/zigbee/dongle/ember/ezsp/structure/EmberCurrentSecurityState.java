/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspDeserializer;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to implement the Ember Structure <b>EmberCurrentSecurityState</b>.
 * <p>
 * The security options and information currently used by the stack.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EmberCurrentSecurityState {

    /**
     * A bitmask indicating the security options currently in use by a device joined in the network.
     * <p>
     * EZSP type is <i>EmberCurrentSecurityBitmask</i> - Java type is {@link EmberCurrentSecurityBitmask}
     * Parameter allows multiple options so implemented as a {@link Set}.
     */
    private Set<EmberCurrentSecurityBitmask> bitmask = new HashSet<EmberCurrentSecurityBitmask>();

    /**
     * The IEEE Address of the Trust Center device.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     */
    private IeeeAddress trustCenterLongAddress;

    /**
     * Default Constructor
     */
    public EmberCurrentSecurityState() {
    }

    public EmberCurrentSecurityState(EzspDeserializer deserializer) {
        deserialize(deserializer);
    }

    /**
     * A bitmask indicating the security options currently in use by a device joined in the network.
     * <p>
     * EZSP type is <i>EmberCurrentSecurityBitmask</i> - Java type is {@link EmberCurrentSecurityBitmask}
     *
     * @return the current bitmask as {@link Set} of {@link EmberCurrentSecurityBitmask}
     */
    public Set<EmberCurrentSecurityBitmask> getBitmask() {
        return bitmask;
    }

    /**
     * A bitmask indicating the security options currently in use by a device joined in the network.
     *
     * @param bitmask the bitmask to add to the {@link Set} as {@link EmberCurrentSecurityBitmask}
     */
    public void addBitmask(EmberCurrentSecurityBitmask bitmask) {
        this.bitmask.add(bitmask);
    }

    /**
     * A bitmask indicating the security options currently in use by a device joined in the network.
     *
     * @param bitmask the bitmask to remove to the {@link Set} as {@link EmberCurrentSecurityBitmask}
     */
    public void removeBitmask(EmberCurrentSecurityBitmask bitmask) {
        this.bitmask.remove(bitmask);
    }

    /**
     * The IEEE Address of the Trust Center device.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     *
     * @return the current trustCenterLongAddress as {@link IeeeAddress}
     */
    public IeeeAddress getTrustCenterLongAddress() {
        return trustCenterLongAddress;
    }

    /**
     * The IEEE Address of the Trust Center device.
     *
     * @param trustCenterLongAddress the trustCenterLongAddress to set as {@link IeeeAddress}
     */
    public void setTrustCenterLongAddress(IeeeAddress trustCenterLongAddress) {
        this.trustCenterLongAddress = trustCenterLongAddress;
    }

    /**
     * Serialise the contents of the EZSP structure.
     *
     * @param serializer the {@link EzspSerializer} used to serialize
     */
    public int[] serialize(EzspSerializer serializer) {
        // Serialize the fields
        serializer.serializeEmberCurrentSecurityBitmask(bitmask);
        serializer.serializeEmberEui64(trustCenterLongAddress);
        return serializer.getPayload();
    }

    /**
     * Deserialise the contents of the EZSP structure.
     *
     * @param deserializer the {@link EzspDeserializer} used to deserialize
     */
    public void deserialize(EzspDeserializer deserializer) {
        // Deserialize the fields
        bitmask = deserializer.deserializeEmberCurrentSecurityBitmask();
        trustCenterLongAddress = deserializer.deserializeEmberEui64();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(78);
        builder.append("EmberCurrentSecurityState [bitmask=");
        builder.append(bitmask);
        builder.append(", trustCenterLongAddress=");
        builder.append(trustCenterLongAddress);
        builder.append(']');
        return builder.toString();
    }
}
