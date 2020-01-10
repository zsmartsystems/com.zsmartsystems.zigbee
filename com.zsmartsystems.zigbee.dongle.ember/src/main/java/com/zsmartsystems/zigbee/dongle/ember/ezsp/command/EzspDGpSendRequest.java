/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpAddress;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>dGpSend</b>.
 * <p>
 * Adds/removes an entry from the GP Tx Queue.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspDGpSendRequest extends EzspFrameRequest {
    public static final int FRAME_ID = 0xC6;

    /**
     * The action to perform on the GP TX queue (true to add, false to remove).
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     */
    private boolean action;

    /**
     * Whether to use ClearChannelAssessment when transmitting the GPDF.
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     */
    private boolean useCca;

    /**
     * The Address of the destination GPD.
     * <p>
     * EZSP type is <i>EmberGpAddress</i> - Java type is {@link EmberGpAddress}
     */
    private EmberGpAddress addr;

    /**
     * The GPD command ID to send.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int gpdCommandId;

    /**
     * The GP command payload.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     */
    private int[] gpdAsdu;

    /**
     * The handle to refer to the GPDF.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int gpepHandle;

    /**
     * How long to keep the GPDF in the TX Queue.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     */
    private int gpTxQueueEntryLifetimeMs;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspDGpSendRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * The action to perform on the GP TX queue (true to add, false to remove).
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     *
     * @return the current action as {@link boolean}
     */
    public boolean getAction() {
        return action;
    }

    /**
     * The action to perform on the GP TX queue (true to add, false to remove).
     *
     * @param action the action to set as {@link boolean}
     */
    public void setAction(boolean action) {
        this.action = action;
    }

    /**
     * Whether to use ClearChannelAssessment when transmitting the GPDF.
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     *
     * @return the current useCca as {@link boolean}
     */
    public boolean getUseCca() {
        return useCca;
    }

    /**
     * Whether to use ClearChannelAssessment when transmitting the GPDF.
     *
     * @param useCca the useCca to set as {@link boolean}
     */
    public void setUseCca(boolean useCca) {
        this.useCca = useCca;
    }

    /**
     * The Address of the destination GPD.
     * <p>
     * EZSP type is <i>EmberGpAddress</i> - Java type is {@link EmberGpAddress}
     *
     * @return the current addr as {@link EmberGpAddress}
     */
    public EmberGpAddress getAddr() {
        return addr;
    }

    /**
     * The Address of the destination GPD.
     *
     * @param addr the addr to set as {@link EmberGpAddress}
     */
    public void setAddr(EmberGpAddress addr) {
        this.addr = addr;
    }

    /**
     * The GPD command ID to send.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current gpdCommandId as {@link int}
     */
    public int getGpdCommandId() {
        return gpdCommandId;
    }

    /**
     * The GPD command ID to send.
     *
     * @param gpdCommandId the gpdCommandId to set as {@link int}
     */
    public void setGpdCommandId(int gpdCommandId) {
        this.gpdCommandId = gpdCommandId;
    }

    /**
     * The GP command payload.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     *
     * @return the current gpdAsdu as {@link int[]}
     */
    public int[] getGpdAsdu() {
        return gpdAsdu;
    }

    /**
     * The GP command payload.
     *
     * @param gpdAsdu the gpdAsdu to set as {@link int[]}
     */
    public void setGpdAsdu(int[] gpdAsdu) {
        this.gpdAsdu = gpdAsdu;
    }

    /**
     * The handle to refer to the GPDF.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current gpepHandle as {@link int}
     */
    public int getGpepHandle() {
        return gpepHandle;
    }

    /**
     * The handle to refer to the GPDF.
     *
     * @param gpepHandle the gpepHandle to set as {@link int}
     */
    public void setGpepHandle(int gpepHandle) {
        this.gpepHandle = gpepHandle;
    }

    /**
     * How long to keep the GPDF in the TX Queue.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     *
     * @return the current gpTxQueueEntryLifetimeMs as {@link int}
     */
    public int getGpTxQueueEntryLifetimeMs() {
        return gpTxQueueEntryLifetimeMs;
    }

    /**
     * How long to keep the GPDF in the TX Queue.
     *
     * @param gpTxQueueEntryLifetimeMs the gpTxQueueEntryLifetimeMs to set as {@link int}
     */
    public void setGpTxQueueEntryLifetimeMs(int gpTxQueueEntryLifetimeMs) {
        this.gpTxQueueEntryLifetimeMs = gpTxQueueEntryLifetimeMs;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeBool(action);
        serializer.serializeBool(useCca);
        serializer.serializeEmberGpAddress(addr);
        serializer.serializeUInt8(gpdCommandId);
        serializer.serializeUInt8(gpdAsdu.length);
        serializer.serializeUInt8Array(gpdAsdu);
        serializer.serializeUInt8(gpepHandle);
        serializer.serializeUInt16(gpTxQueueEntryLifetimeMs);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(221);
        builder.append("EzspDGpSendRequest [action=");
        builder.append(action);
        builder.append(", useCca=");
        builder.append(useCca);
        builder.append(", addr=");
        builder.append(addr);
        builder.append(", gpdCommandId=");
        builder.append(gpdCommandId);
        builder.append(", gpdAsdu=");
        for (int cnt = 0; cnt < gpdAsdu.length; cnt++) {
            if (cnt > 0) {
                builder.append(' ');
            }
            builder.append(String.format("%02X", gpdAsdu[cnt]));
        }
        builder.append(", gpepHandle=");
        builder.append(gpepHandle);
        builder.append(", gpTxQueueEntryLifetimeMs=");
        builder.append(gpTxQueueEntryLifetimeMs);
        builder.append(']');
        return builder.toString();
    }
}
