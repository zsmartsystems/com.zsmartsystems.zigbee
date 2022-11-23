/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>findAndRejoinNetwork</b>.
 * <p>
 * The application may call this function when contact with the network has been lost. The most
 * common usage case is when an end device can no longer communicate with its parent and wishes to
 * find a new one. Another case is when a device has missed a Network Key update and no longer has
 * the current Network Key. The stack will call ezspStackStatusHandler to indicate that the
 * network is down, then try to re-establish contact with the network by performing an active
 * scan, choosing a network with matching extended pan id, and sending a ZigBee network rejoin
 * request. A sec- ond call to the ezspStackStatusHandler callback indicates either the
 * success or the failure of the attempt. The process takes approximately 150 milliseconds per
 * channel to complete. This call replaces the emberMobileNodeHasMoved API from EmberZNet
 * 2.x, which used MAC association and consequently took half a second longer to complete.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspFindAndRejoinNetworkRequest extends EzspFrameRequest {
    public static final int FRAME_ID = 0x21;

    /**
     * This parameter tells the stack whether to try to use the current network key. If it has the
     * current network key it will perform a secure rejoin (encrypted). If this fails the device
     * should try an unsecure rejoin. If the Trust Center allows the rejoin then the current Network
     * Key will be sent encrypted using the device's Link Key. The unsecured rejoin is only
     * supported in the Commercial Security Library.
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     */
    private boolean haveCurrentNetworkKey;

    /**
     * A mask indicating the channels to be scanned. See emberStartScan for format details. A value
     * of 0 is reinterpreted as the mask for the current channel.
     * <p>
     * EZSP type is <i>uint32_t</i> - Java type is {@link int}
     */
    private int channelMask;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspFindAndRejoinNetworkRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * This parameter tells the stack whether to try to use the current network key. If it has the
     * current network key it will perform a secure rejoin (encrypted). If this fails the device
     * should try an unsecure rejoin. If the Trust Center allows the rejoin then the current Network
     * Key will be sent encrypted using the device's Link Key. The unsecured rejoin is only
     * supported in the Commercial Security Library.
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     *
     * @return the current haveCurrentNetworkKey as {@link boolean}
     */
    public boolean getHaveCurrentNetworkKey() {
        return haveCurrentNetworkKey;
    }

    /**
     * This parameter tells the stack whether to try to use the current network key. If it has the
     * current network key it will perform a secure rejoin (encrypted). If this fails the device
     * should try an unsecure rejoin. If the Trust Center allows the rejoin then the current Network
     * Key will be sent encrypted using the device's Link Key. The unsecured rejoin is only
     * supported in the Commercial Security Library.
     *
     * @param haveCurrentNetworkKey the haveCurrentNetworkKey to set as {@link boolean}
     */
    public void setHaveCurrentNetworkKey(boolean haveCurrentNetworkKey) {
        this.haveCurrentNetworkKey = haveCurrentNetworkKey;
    }

    /**
     * A mask indicating the channels to be scanned. See emberStartScan for format details. A value
     * of 0 is reinterpreted as the mask for the current channel.
     * <p>
     * EZSP type is <i>uint32_t</i> - Java type is {@link int}
     *
     * @return the current channelMask as {@link int}
     */
    public int getChannelMask() {
        return channelMask;
    }

    /**
     * A mask indicating the channels to be scanned. See emberStartScan for format details. A value
     * of 0 is reinterpreted as the mask for the current channel.
     *
     * @param channelMask the channelMask to set as {@link int}
     */
    public void setChannelMask(int channelMask) {
        this.channelMask = channelMask;
    }

    @Override
    public int[] serialize(int ezspVersion) {
        // Serialize the header
        serializeHeader(ezspVersion, serializer);

        // Serialize the fields
        serializer.serializeBool(haveCurrentNetworkKey);
        serializer.serializeUInt32(channelMask);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(109);
        builder.append("EzspFindAndRejoinNetworkRequest [networkId=");
        builder.append(networkId);
        builder.append(", haveCurrentNetworkKey=");
        builder.append(haveCurrentNetworkKey);
        builder.append(", channelMask=");
        builder.append(String.format("%08X", channelMask));
        builder.append(']');
        return builder.toString();
    }
}
