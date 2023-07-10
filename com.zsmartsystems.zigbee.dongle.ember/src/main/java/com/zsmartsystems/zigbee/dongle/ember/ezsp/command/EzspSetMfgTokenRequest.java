/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspMfgTokenId;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>setMfgToken</b>.
 * <p>
 * Sets a manufacturing token in the Customer Information Block (CIB) area of the NCP if that
 * token currently unset (fully erased). Cannot be used with EZSP_STACK_CAL_DATA,
 * EZSP_STACK_CAL_FILTER, EZSP_MFG_ASH_CONFIG, or EZSP_MFG_CBKE_DATA token.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspSetMfgTokenRequest extends EzspFrameRequest {
    public static final int FRAME_ID = 0x0C;

    /**
     * Which manufacturing token to set.
     * <p>
     * EZSP type is <i>EzspMfgTokenId</i> - Java type is {@link EzspMfgTokenId}
     */
    private EzspMfgTokenId tokenId;

    /**
     * The manufacturing token data.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     */
    private int[] tokenData;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspSetMfgTokenRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * Which manufacturing token to set.
     * <p>
     * EZSP type is <i>EzspMfgTokenId</i> - Java type is {@link EzspMfgTokenId}
     *
     * @return the current tokenId as {@link EzspMfgTokenId}
     */
    public EzspMfgTokenId getTokenId() {
        return tokenId;
    }

    /**
     * Which manufacturing token to set.
     *
     * @param tokenId the tokenId to set as {@link EzspMfgTokenId}
     */
    public void setTokenId(EzspMfgTokenId tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * The manufacturing token data.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     *
     * @return the current tokenData as {@link int[]}
     */
    public int[] getTokenData() {
        return tokenData;
    }

    /**
     * The manufacturing token data.
     *
     * @param tokenData the tokenData to set as {@link int[]}
     */
    public void setTokenData(int[] tokenData) {
        this.tokenData = tokenData;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeEzspMfgTokenId(tokenId);
        serializer.serializeUInt8(tokenData.length);
        serializer.serializeUInt8Array(tokenData);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(125);
        builder.append("EzspSetMfgTokenRequest [networkId=");
        builder.append(networkId);
        builder.append(", tokenId=");
        builder.append(tokenId);
        builder.append(", tokenData=");
        for (int cnt = 0; cnt < tokenData.length; cnt++) {
            if (cnt > 0) {
                builder.append(' ');
            }
            builder.append(String.format("%02X", tokenData[cnt]));
        }
        builder.append(']');
        return builder.toString();
    }
}
