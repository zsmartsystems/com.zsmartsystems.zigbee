/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberAesMmoHashContext;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * Class to implement the Ember EZSP command <b>aesMmoHash</b>.
 * <p>
 * This routine processes the passed chunk of data and updates the hash context based on it. If
 * the 'finalize' parameter is not set, then the length of the data passed in must be a multiple of
 * 16. If the 'finalize' parameter is set then the length can be any value up 1-16, and the final
 * hash value will be calculated.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspAesMmoHashResponse extends EzspFrameResponse {
    public static final int FRAME_ID = 0x6F;

    /**
     * The result of the operation.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     */
    private EmberStatus status;

    /**
     * The updated hash context.
     * <p>
     * EZSP type is <i>EmberAesMmoHashContext</i> - Java type is {@link EmberAesMmoHashContext}
     */
    private EmberAesMmoHashContext returnContext;

    /**
     * Response and Handler constructor
     */
    public EzspAesMmoHashResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        status = deserializer.deserializeEmberStatus();
        returnContext = deserializer.deserializeEmberAesMmoHashContext();
    }

    /**
     * The result of the operation.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     *
     * @return the current status as {@link EmberStatus}
     */
    public EmberStatus getStatus() {
        return status;
    }

    /**
     * The result of the operation.
     *
     * @param status the status to set as {@link EmberStatus}
     */
    public void setStatus(EmberStatus status) {
        this.status = status;
    }

    /**
     * The updated hash context.
     * <p>
     * EZSP type is <i>EmberAesMmoHashContext</i> - Java type is {@link EmberAesMmoHashContext}
     *
     * @return the current returnContext as {@link EmberAesMmoHashContext}
     */
    public EmberAesMmoHashContext getReturnContext() {
        return returnContext;
    }

    /**
     * The updated hash context.
     *
     * @param returnContext the returnContext to set as {@link EmberAesMmoHashContext}
     */
    public void setReturnContext(EmberAesMmoHashContext returnContext) {
        this.returnContext = returnContext;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(100);
        builder.append("EzspAesMmoHashResponse [networkId=");
        builder.append(networkId);
        builder.append(", status=");
        builder.append(status);
        builder.append(", returnContext=");
        builder.append(returnContext);
        builder.append(']');
        return builder.toString();
    }
}
