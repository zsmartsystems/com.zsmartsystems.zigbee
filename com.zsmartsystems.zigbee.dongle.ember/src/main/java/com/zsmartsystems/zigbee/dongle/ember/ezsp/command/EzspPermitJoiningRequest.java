/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to implement the Ember EZSP command <b>permitJoining</b>.
 * <p>
 * Tells the stack to allow other nodes to join the network with this node as their parent.
 * Joining is initially disabled by default.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspPermitJoiningRequest extends EzspFrameRequest {
    public static int FRAME_ID = 0x22;

    /**
     * A value of 0x00 disables joining. A value of 0xFF enables joining. Any other value enables
     * joining for that number of seconds.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int duration;

    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspPermitJoiningRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * A value of 0x00 disables joining. A value of 0xFF enables joining. Any other value enables
     * joining for that number of seconds.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current duration as {@link int}
     */
    public int getDuration() {
        return duration;
    }

    /**
     * A value of 0x00 disables joining. A value of 0xFF enables joining. Any other value enables
     * joining for that number of seconds.
     *
     * @param duration the duration to set as {@link int}
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeUInt8(duration);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspPermitJoiningRequest [duration=");
        builder.append(duration);
        builder.append("]");
        return builder.toString();
    }
}
