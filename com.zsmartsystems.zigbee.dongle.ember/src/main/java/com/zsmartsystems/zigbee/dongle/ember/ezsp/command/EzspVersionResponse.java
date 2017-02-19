/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspDeserializer;

/**
 * Class to implement the Ember EZSP command <b>version</b>.
 * <p>
 * The command allows the Host to specify the desired EZSP version and must be sent before any
 * other command. This document describes EZSP version 4 and stack type 2 (mesh). The response
 * provides information about the firmware running on the NCP.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspVersionResponse extends EzspFrameResponse {
    public static int FRAME_ID = 0x00;

    /**
     * The EZSP version the NCP is using
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int protocolVersion;

    /**
     * The type of stack running on the NCP
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int stackType;

    /**
     * The version number of the stack
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     */
    private int stackVersion;

    /**
     * Response and Handler constructor
     */
    public EzspVersionResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        protocolVersion = deserializer.deserializeUInt8();
        stackType = deserializer.deserializeUInt8();
        stackVersion = deserializer.deserializeUInt16();
    }

    /**
     * The EZSP version the NCP is using
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current protocolVersion as {@link int}
     */
    public int getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * The EZSP version the NCP is using
     *
     * @param protocolVersion the protocolVersion to set as {@link int}
     */
    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    /**
     * The type of stack running on the NCP
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current stackType as {@link int}
     */
    public int getStackType() {
        return stackType;
    }

    /**
     * The type of stack running on the NCP
     *
     * @param stackType the stackType to set as {@link int}
     */
    public void setStackType(int stackType) {
        this.stackType = stackType;
    }

    /**
     * The version number of the stack
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     *
     * @return the current stackVersion as {@link int}
     */
    public int getStackVersion() {
        return stackVersion;
    }

    /**
     * The version number of the stack
     *
     * @param stackVersion the stackVersion to set as {@link int}
     */
    public void setStackVersion(int stackVersion) {
        this.stackVersion = stackVersion;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspVersionResponse [protocolVersion=");
        builder.append(protocolVersion);
        builder.append(", stackType=");
        builder.append(stackType);
        builder.append(", stackVersion=");
        builder.append(stackVersion);
        builder.append("]");
        return builder.toString();
    }
}
